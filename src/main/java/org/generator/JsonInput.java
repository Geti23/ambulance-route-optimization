package org.generator;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

class JsonInput {

    /**
     * Parse the input file and store the values.
     * Catches exceptions if something goes wrong (no input file, wrong arguments...)
     *
     * @param path Input file
     * @return String with output path
     */
    public static ProblemParameters readArguments(String path){
        try {
            ProblemParameters result = new ProblemParameters();
            JSONObject o = new JSONObject(Files.readString(Paths.get(path)));

            // Random cities
            result.nodes = o.getInt("nodes"); // Number of nodes
            result.roads = o.getInt("roads"); // Number of roads
            result.noise = o.getDouble("noise"); // Max noise
            result.demand = o.getInt("demand"); // Max demand

            // Patients
            result.patients = o.getInt("patients"); // Number of patients
            result.severity1prob = o.getDouble("severity1prob"); // Prob for priority 1
            result.severity2prob = o.getDouble("severity2prob"); // Prob for priority 2
            result.severity3prob = o.getDouble("severity3prob"); // Prob for priority 3

            // Ambulance and hospitals
            result.hospitals = o.getInt("hospitals");
            result.ambulances = o.getInt("ambulances");

            // Output
            result.output = o.getString("output");

            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static class ProblemParameters {
        int nodes;
        int roads;
        double noise;
        int demand;

        int ambulances;
        int hospitals;

        int patients;
        double severity1prob;
        double severity2prob;
        double severity3prob;

        String output;
    }
}
