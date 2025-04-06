package org.algorithm.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PatientProvider {

    private BufferedReader in;
    private CityMap map;
    private Patient newPatient;

    public PatientProvider(CityMap map) {
        in = new BufferedReader(new InputStreamReader(System.in));
        this.map = map;
    }

    public Patient getNewPatient() {
        Patient p = newPatient;
        newPatient = null;
        return p;
    }

    public boolean hasNewPatient() {
        try {
            System.out.printf("\nWant to add a patient?\n(enter to continue, syntax: [node(0-%d) severity(1-3)])\n > ", map.nodesCount());
            String answer = in.readLine();
            if (answer != null && !answer.trim().isEmpty()) {
                // parse user input
                String[] tokens = answer.trim().split(" ");
                for (int i = 0; i < tokens.length; i += 2) {
                    int node = Integer.parseInt(tokens[i]);
                    int severity = Integer.parseInt(tokens[i + 1]);
                    if (node < map.nodesCount() && severity >= 1 && severity <= 3) {
                        newPatient = new Patient(node, severity);
                    } else {
                        System.out.println("Nope");
                    }
                }
            }
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Input error");
        }
        return newPatient != null;
    }
}
