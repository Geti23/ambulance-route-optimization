package org;

import org.algorithm.HungarianPlanner;
import org.model.*;
import org.model.CityMap.Print;

import java.util.List;
import java.util.Map;

public class Main {

    private static double totalDistance = 0.0;
    private static long totalWaitingTime = 0;

    public static void main(String[] args) throws Exception{
        String cityFileName = "test1.pddl";

        HungarianPlanner planner = new HungarianPlanner();
        CityMap map = CityParser.parse(cityFileName);

        System.out.println(map.represent(CityMap.Print.ADJ_MATRIX));
        System.out.println(map.represent(CityMap.Print.SHORTEST_DISTANCES_MATRIX));
        System.out.println(map.represent(CityMap.Print.SHORTEST_PATHS));
        System.out.println(map.represent(CityMap.Print.AMBULANCES_LOCATIONS));
        System.out.println(map.represent(CityMap.Print.HOSPITAL_LOCATIONS));
        System.out.println(map.represent(CityMap.Print.PATIENT_LOCATIONS));
        System.out.println(map.represent(CityMap.Print.DEMANDS));

        Map<Ambulance, List<Action>> plan = null;
        int step = 0;
        boolean replanningNeeded = true;
        do {
            System.out.println("\n--------------------------------------------------");
            System.out.println("Step: " + step);
            step++;

            if (replanningNeeded) {
                System.out.println("Replanning...");
                plan = planner.solve(map);
                replanningNeeded = false;
            }

            System.out.print(map.represent(CityMap.Print.AMBULANCES_LOCATIONS));
            System.out.print(map.represent(CityMap.Print.PATIENT_LOCATIONS));

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
        } while (replanningNeeded || !plan.values().stream().allMatch(List::isEmpty));

        System.out.println("\nDone!");
        System.out.println(map.represent(Print.AMBULANCES_LOCATIONS));
        System.out.println("\nMetrics:");
        System.out.println("  Total distance travelled: " + totalDistance);
        System.out.println("  Total time patients waited: " + totalWaitingTime);
    }
}