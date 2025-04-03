package org.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CityMap {
    public enum Print {
        ALL, ADJ_MATRIX, SHORTEST_DISTANCES_MATRIX, SHORTEST_PATHS, AMBULANCES_LOCATIONS, PATIENT_LOCATIONS, HOSPITAL_LOCATIONS, DEMANDS
    }

    public static final int X = 0;
    public static final int Y = 1;
    public static final int NUM_COORD = 2;

    private final double[][] adjMatrix;
    private final double[] demands;
    private final List<List<NodeContent>> contents;
    private final double[][] shortestDistances;
    private final ArrayList<?>[][] shortestsPaths;
    private final int nodeCount;

    private Map<Integer, Ambulance> ambulances = new HashMap<>();

    private Map<Integer, Patient> patients = new HashMap<>();

    private Map<Integer, Hospital> hospitals = new HashMap<>();

    private static void dump(double[][] adjMatrix) {
        try {
            PrintWriter pw = new PrintWriter("cityDump.txt");

            pw.println("Paste on https://graphonline.top/create_graph_by_matrix");
            pw.println(Arrays.stream(adjMatrix).map(
                            row -> Arrays.stream(row).mapToObj(v -> (int) Math.round(v) + "").collect(Collectors.joining(", ")))
                    .collect(Collectors.joining("\n")));

            pw.close();
        } catch (FileNotFoundException e) {
            // ignore
        }
    }

    public CityMap(double[][] adjMatrix, List<List<NodeContent>> contents, double[] demands) {
        dump(adjMatrix);

        nodeCount = adjMatrix.length;

        contents.stream().flatMap(Collection::stream).filter(nc -> nc instanceof Ambulance)
                .forEach(amb -> ambulances.put(amb.getId(), (Ambulance) amb));
        contents.stream().flatMap(Collection::stream).filter(nc -> nc instanceof Hospital)
                .forEach(hos -> hospitals.put(hos.getId(), (Hospital) hos));
        contents.stream().flatMap(Collection::stream).filter(nc -> nc instanceof Patient)
                .forEach(pat -> patients.put(pat.getId(), (Patient) pat));

        this.demands = demands;
        this.adjMatrix = adjMatrix;
        this.contents = contents;
        shortestDistances = new double[nodeCount][];
        shortestsPaths = new ArrayList<?>[nodeCount][nodeCount];

        computePaths();
    }

    public Set<Integer> adjacentNodes(int from) {
        return IntStream.range(0, nodeCount).filter(to -> adjMatrix[from][to] > 0).boxed().collect(Collectors.toSet());
    }

    public boolean areAdjacent(int from, int to) {
        return adjMatrix[from][to] > 0;
    }

    public int closestHospital(int from) {
        double min = Double.POSITIVE_INFINITY;
        int result = 0;
        for (int hosNode : getHospitals().stream().mapToInt(h -> h.getNode()).toArray()) {
            double dist = shortestDistance(from, hosNode);
            if (dist < min) {
                result = hosNode;
                min = dist;
            }
        }
        return result;
    }

    private void computePaths() {
        // iterate the single source Dijkstra Algorithm for every node
        for (int startNode = 0; startNode < nodeCount; startNode++) {
            Set<Integer> nodesToProcess = IntStream.range(0, nodeCount).boxed().collect(Collectors.toSet());

            double[] shortestDistancesFrom = new double[nodeCount];
            Arrays.fill(shortestDistancesFrom, Double.POSITIVE_INFINITY);
            shortestDistancesFrom[startNode] = 0;

            int[] previousNode = new int[nodeCount];
            Arrays.fill(previousNode, -1);

            while (!nodesToProcess.isEmpty()) {
                // i.e. find the node that has minimum distance between the
                // nodes that are not yet explored
                double minDistance = nodesToProcess.stream().mapToDouble(node -> shortestDistancesFrom[node]).min()
                        .getAsDouble();
                int nodeBeingProcessed = nodesToProcess.stream()
                        .filter(node -> shortestDistancesFrom[node] == minDistance).findFirst().get();

                nodesToProcess.remove(nodeBeingProcessed);

                // from this node try to reach all the adjacent and check if
                // the overall distance from the starting node decreases
                for (int adjacentNode : adjacentNodes(nodeBeingProcessed)) {
                    double alternativeDistance = shortestDistancesFrom[nodeBeingProcessed]
                            + adjMatrix[nodeBeingProcessed][adjacentNode];
                    if (alternativeDistance < shortestDistancesFrom[adjacentNode]) {
                        shortestDistancesFrom[adjacentNode] = alternativeDistance;
                        previousNode[adjacentNode] = nodeBeingProcessed;
                    }
                }
            }

            shortestDistances[startNode] = shortestDistancesFrom;

            // generate all paths backtracking on previousNode
            for (int endNode = 0; endNode < nodeCount; endNode++) {
                ArrayList<Integer> path = new ArrayList<Integer>();
                if (Double.isFinite(shortestDistances[startNode][endNode])) {
                    int intermediateNode = endNode;
                    while (intermediateNode >= 0 && previousNode[intermediateNode] >= 0) {
                        path.add(0, intermediateNode);
                        intermediateNode = previousNode[intermediateNode];
                    }
                    path.add(0, intermediateNode);
                }
                shortestsPaths[startNode][endNode] = path;
            }
        }
    }

    public List<Ambulance> getAmbulances() {
        return new ArrayList<>(ambulances.values());
    }

    public List<NodeContent> getContentAt(int node) {
        return contents.get(node);
    }

    public List<Double> getDemands() {
        return Arrays.stream(demands).boxed().collect(Collectors.toList());
    }

    public List<Hospital> getHospitals() {
        return new ArrayList<>(hospitals.values());
    }

    public List<Patient> getPatients() {
        return new ArrayList<>(patients.values());
    }

    public double[][] getShortestDistances() {
        return shortestDistances;
    }

    public void performAction(Action action) {
        action.performAction(this);
    }

    public int nodesCount() {
        return nodeCount;
    }

    public String represent(Print what) {
        StringBuilder sb = new StringBuilder();

        switch (what) {
            case ADJ_MATRIX:
            case SHORTEST_DISTANCES_MATRIX:
                double[][] matrix = what == Print.ADJ_MATRIX ? adjMatrix : shortestDistances;
                sb.append(what == Print.ADJ_MATRIX ? "adjMatrix\n" : "shortestDistances\n");
                sb.append(IntStream.range(0, nodeCount).mapToObj(Integer::toString)
                        .collect(Collectors.joining("\t", "\t", "\n")));
                sb.append(IntStream.range(0, nodeCount)
                        .mapToObj(from -> IntStream.range(0, nodeCount).mapToDouble(to -> matrix[from][to])
                                .mapToObj(d -> Double.isInfinite(d) || d < 0 ? "-" : String.format("%.1f", d))
                                .collect(Collectors.joining("\t", from + "\t", "")))
                        .collect(Collectors.joining("\n")));
                break;
            case SHORTEST_PATHS:
                sb.append("Shortest paths\n");
                IntStream.range(0, nodeCount)
                        .forEachOrdered(from -> IntStream.range(0, nodeCount).forEachOrdered(to -> sb.append(String.format(
                                "N%d -> N%d (%.3f): %s\n", from, to, shortestDistances[from][to],
                                shortestsPaths[from][to].stream().map(n -> "N" + n).collect(Collectors.joining(","))))));
                break;
            case AMBULANCES_LOCATIONS:
                sb.append(getAmbulances().stream().map(Ambulance::toString)
                        .collect(Collectors.joining("\n", "Ambulances:\n", "\n")));
                break;
            case HOSPITAL_LOCATIONS:
                sb.append(getHospitals().stream().map(Hospital::toString)
                        .collect(Collectors.joining("\n", "Hospitals:\n", "\n")));
                break;
            case PATIENT_LOCATIONS:
                sb.append(getPatients().stream().map(Patient::toString)
                        .collect(Collectors.joining("\n", "Patients:\n", "\n")));
                break;
            case DEMANDS:
                sb.append(IntStream.range(0, nodeCount).mapToDouble(n -> demands[n]).mapToObj(Double::toString)
                        .collect(Collectors.joining("\t", "Demands:  ", "\n")));
                sb.append(IntStream.range(0, nodeCount).mapToObj(Integer::toString)
                        .collect(Collectors.joining("\t", "Nodes:    ", "")));
                break;
            default:
                break;
        }
        return sb.toString();
    }

    public double shortestDistance(int from, int to) {
        return shortestDistances[from][to];
    }

    public ArrayList<Integer> shortestPath(int from, int to) {
        return (ArrayList<Integer>) shortestsPaths[from][to];
    }

    public void spawn(Patient patient) {
        contents.get(patient.getNode()).add(patient);
        patients.put(patient.getId(), patient);
    }
}
