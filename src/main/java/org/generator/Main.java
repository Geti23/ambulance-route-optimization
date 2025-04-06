package org.generator;

/**
 * Generator of PDDL files fitting our domain
 * @author Team 14
 *
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Read file
            JsonInput.ProblemParameters params = JsonInput.readArguments("src/main/resources/json-inputs/input.json");

            // Generate problem
            String s = Generator.generateProblem(params);

            // Write output PDDL
            IO.printPDDL(params.output, s);
            System.out.println("\nGraph was generated successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

