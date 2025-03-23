package org.algorithm;

import java.util.Arrays;

/**
 * Solver for an assignment problem using a version of
 * Hungarian algorithm with asymptotic O(N^3).
 */
public class AssignmentProblemSolver {

    /**
     * Should be greater than any number in the given matrix.
     */
    private static final int INF = Integer.MAX_VALUE / 10;

    /**
     * Solving method.
     *
     * @param a - matrix of size NxM (values should be non-negative)
     * @return array of size N
     */
    public static int[] solve(int[][] a) {
        int n = a.length; // Number of rows (workers)
        if (n == 0)
            return new int[0]; // If there are no rows, return an empty array
        int m = a[0].length; // Number of columns (jobs)

        int[] u = new int[n + 1]; // Potential for rows (workers)
        int[] v = new int[m + 1]; // Potential for columns (jobs)
        int[] p = new int[m + 1]; // Maximum matching: for ith row, p[i] - matching column
        int[] way = new int[m + 1]; // way[j] = argmin_i {a[i][j] - u[i] - v[j]}

        // Iterate over each row (worker)
        for (int i = 1; i <= n; i++) {
            p[0] = i; // Start with the current worker
            int j0 = 0; // Initialize the column index
            int[] minv = new int[m + 1]; // minv[j] = min_i {a[i][j] - u[i] - v[j]}
            Arrays.fill(minv, INF); // Fill minv with a large value (infinity)
            boolean[] used = new boolean[m + 1]; // Track used columns

            // Main loop to find the minimum cost matching
            do {
                used[j0] = true; // Mark the current column as used
                int i0 = p[j0]; // Get the current worker
                int delta = INF; // Initialize delta to infinity
                int j1 = -1; // Initialize the next column index

                // Iterate over all columns to find the minimum cost
                for (int j = 1; j <= m; j++) {
                    if (!used[j]) { // If the column is not used
                        // Calculate the reduced cost
                        int cur = a[i0 - 1][j - 1] - u[i0] - v[j];
                        if (cur < minv[j]) { // If the current cost is less than the minimum found
                            minv[j] = cur; // Update the minimum cost
                            way[j] = j0; // Update the way to reach this column
                        }
                        // Update delta and j1 for the minimum cost found
                        if (minv[j] < delta) {
                            delta = minv[j];
                            j1 = j; // Update the column index with the minimum cost
                        }
                    }
                }

                // Update potentials and minimum values
                for (int j = 0; j <= m; j++) {
                    if (used[j]) {
                        u[p[j]] += delta; // Update the potential for the matched worker
                        v[j] -= delta; // Update the potential for the column
                    } else {
                        minv[j] -= delta; // Reduce the minimum value for unused columns
                    }
                }
                j0 = j1; // Move to the next column
            } while (p[j0] != 0); // Continue until we reach a column with no match

            // Update the matching based on the way found
            do {
                int j1 = way[j0]; // Get the previous column index
                p[j0] = p[j1]; // Update the matching for the current column
                j0 = j1; // Move to the previous column
            } while (j0 != 0); // Continue until we reach the starting column
        }

        int[] ans = new int[n]; // Array to store the final matching result
        for (int j = 1; j <= m; j++) {
            if (p[j] == 0) {
                continue; // Skip if there is no matching for this column
            }
            ans[p[j] - 1] = j - 1; // Store the matched column for the worker
        }

        return ans; // Return the array of matched columns
    }
}
