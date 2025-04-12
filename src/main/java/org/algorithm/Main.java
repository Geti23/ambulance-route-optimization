package org.algorithm;

import java.io.File;

import org.algorithm.model.*;
import org.algorithm.model.CityMap.Print;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static double totalDistance = 0.0;
    private static long totalWaitingTime = 0;

    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        // getting the file name containing the input problem graph
        System.out.println("Enter input file name:");
        String cityFileName = scanner.nextLine();
        cityFileName = cityFileName.endsWith(".pddl") ? cityFileName : cityFileName + ".pddl";

        /* true if the file is from resources folder/generated-graphs
         * false if the file is from base folder */
        System.out.println("Is this file located in the 'resources/generated-graphs' folder? (y/n):");
        boolean isResourceFile = scanner.nextLine().equalsIgnoreCase("y");
        String fullFilePath = isResourceFile ? "src/main/resources/generated-graphs/" + cityFileName : cityFileName;

        // debugging utilities
        new File("logs").mkdir();
        String now = new SimpleDateFormat(".yyyy-MM-dd_HH.mm.ss").format(new Date());
        String easyToReadDescription = "logs/" + cityFileName.split(".pddl")[0] + now + ".descr";
        String solution = "logs/" + cityFileName.split(".pddl")[0] + now + ".plan";

        // initial set up
        HungarianPlanner planner = new HungarianPlanner();
        CityMap map = CityParser.parse(fullFilePath);
        Files.copy(new File(fullFilePath).toPath(), new File(easyToReadDescription).toPath());

        PrintWriter solutionWriter = new PrintWriter(solution);
        PrintWriter eventsWriter = new PrintWriter(easyToReadDescription);

        PatientProvider patientProvider = new PatientProvider(map);

        System.out.println(map.represent(CityMap.Print.ADJ_MATRIX));
        System.out.println(map.represent(CityMap.Print.SHORTEST_DISTANCES_MATRIX));
        System.out.println(map.represent(CityMap.Print.SHORTEST_PATHS));
        System.out.println(map.represent(CityMap.Print.AMBULANCES_LOCATIONS));
        System.out.println(map.represent(CityMap.Print.HOSPITAL_LOCATIONS));
        System.out.println(map.represent(CityMap.Print.PATIENT_LOCATIONS));
        System.out.println(map.represent(CityMap.Print.DEMANDS));

        eventsWriter.println(map.represent(Print.ADJ_MATRIX));
        eventsWriter.println(map.represent(Print.SHORTEST_DISTANCES_MATRIX));
        eventsWriter.println(map.represent(Print.SHORTEST_PATHS));
        eventsWriter.println(map.represent(Print.HOSPITAL_LOCATIONS));
        eventsWriter.println(map.represent(Print.DEMANDS));
        eventsWriter.println("\n--------------------------------------------------\n");

        Map<Ambulance, List<Action>> plan = null;
        int step = 0;
        boolean replanningNeeded = true;
        do {
            System.out.println("\n--------------------------------------------------");
            System.out.println("Step: " + step);
            solutionWriter.println("Step:" + step);
            eventsWriter.println("Step:" + step);
            step++;

            // if we don't have a plan make one
            if (replanningNeeded) {
                System.out.println("Replanning...");
                eventsWriter.println("  Replanning...");
                plan = planner.solve(map);
                replanningNeeded = false;
            }

            System.out.print(map.represent(CityMap.Print.AMBULANCES_LOCATIONS));
            System.out.print(map.represent(CityMap.Print.PATIENT_LOCATIONS));

            // print full plan
            for (Ambulance amb : plan.keySet()) {
                System.out.println("Actions for " + amb);
                if (!plan.get(amb).isEmpty()) {
                    for (Action a : plan.get(amb)) {
                        System.out.println("   " + a);
                    }
                } else {
                    System.out.println("   nop");
                }
            }

            for (Ambulance amb : plan.keySet()) {
                if (!plan.get(amb).isEmpty()) {
                    Action a = plan.get(amb).remove(0);
                    System.out.println("Executing: " + a);
                    solutionWriter.println("  " + a);
                    eventsWriter.println("  " + a);
                    map.performAction(a);
                    if (a instanceof ActionMove) {
                        int from = ((ActionMove) a).getFrom();
                        int to= ((ActionMove) a).getTo();
                        totalDistance += map.shortestDistance(from, to);
                    }
                    if (a instanceof ActionDrop && planner.replanAfterDropAction()) {
                        replanningNeeded = true;
                    }
                }
            }

            totalWaitingTime += map.getPatients().stream().filter(Patient::isWaiting).count();

            // uncomment to use feature of adding new patients in between each step
            /*if (patientProvider.hasNewPatient()) {
                Patient patient = patientProvider.getNewPatient();
                System.out.println("Added " + patient);
                eventsWriter.println("  Added " + patient);
                map.spawn(patient);
                replanningNeeded = true;
            }*/
        } while (replanningNeeded || !plan.values().stream().allMatch(List::isEmpty));

        System.out.println("\nDone!");
        System.out.println(map.represent(Print.AMBULANCES_LOCATIONS));
        System.out.println("\nMetrics:");
        System.out.println("  Total distance travelled: " + totalDistance);
        System.out.println("  Total time patients waited: " + totalWaitingTime);

        eventsWriter.println("\nDone!");
        eventsWriter.println(map.represent(Print.AMBULANCES_LOCATIONS));
        eventsWriter.println("\nMetrics:");
        eventsWriter.println("  Total distance travelled: " + totalDistance);
        eventsWriter.println("  Total time patients waited: " + totalWaitingTime);

        solutionWriter.close();
        eventsWriter.close();
    }
}