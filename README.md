# ambulance-route-optimization

**Input**: Graph of city .pddl file (Planning Domain Definition Language) containing all the necessary information:
- nodes,
- roads,
- ambulances,
- hospitals,
- patients,
- severity of patients ...

Example of what a pddl file would look like (not final):
    
    (define (problem routeOptimization)
        (:domain ambulances)
        (:objects
            ambulance1 - ambulance
            node1 node2 - location
            patient1 - patient
        )
 		(:init
			(at ambulance1 node1)
    		(at patient1 node2)
  		)
  		(:goal
            (at ambulance1 patient1)
        )
	)

**Output**: Step by step description with the optimized routes for each ambulance and its patient

**Algorithm**: Consider a set of workers (ambulances, rows) and a set of different tasks (patients, columns). To each worker performing a task 	is associated a cost:

    [
        [ 1 _5_ 5  7]
        [_2_ 6  8  9]
        [ 8  9  1 _5_]
        [ 9  6 _2_ 9]
    ]
choose one element in each row in a such way that each column has no more than one element chosen and sum of elements is minimized.

**The Hungarian algorithm** will be implemented to solve the assignment problem:
- The algorithm is relatively straightforward to understand and implement.
- While primarily designed for the assignment problem, the Hungarian algorithm can be adapted to find the minimum cost in 		  transportation problems.
- Unlike some optimization algorithms that rely on heuristics or approximations, the Hungarian algorithm provides a 			  precise solution without the need for trial-and-error or guesswork.

**Pseudocode**:

    function hungarianAlgorithm(costMatrix):
    n = number of rows in costMatrix
    m = number of columns in costMatrix
    
        // Step 1: Subtract row minimums
        for i from 1 to n:
            minRow = min(costMatrix[i])
            for j from 1 to m:
                costMatrix[i][j] = costMatrix[i][j] - minRow
    
        // Step 2: Subtract column minimums
        for j from 1 to m:
            minCol = min(costMatrix[:, j]) // Get minimum of column j
            for i from 1 to n:
                costMatrix[i][j] = costMatrix[i][j] - minCol
    
        // Step 3: Cover all zeros with a minimum number of lines
        while true:
            lines = coverZeros(costMatrix)
            if lines == n: // If the number of lines equals the number of rows
                break
            else:
                // Step 4: Adjust the matrix
                minUncovered = findMinimumUncovered(costMatrix)
                for i from 1 to n:
                    for j from 1 to m:
                        if not coveredByLine(i, j):
                            costMatrix[i][j] = costMatrix[i][j] - minUncovered
                        if coveredByLine(i, j) and coveredTwice(i, j):
                            costMatrix[i][j] = costMatrix[i][j] + minUncovered
    
        // Step 5: Find the optimal assignment
        assignment = []
        for i from 1 to n:
            for j from 1 to m:
                if costMatrix[i][j] == 0 and not assigned(j):
                    assignment[i] = j
                    markAssigned(i, j)
                    break
    
    return assignment
