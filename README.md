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
- While primarily designed for the assignment problem, the Hungarian algorithm can be adapted to find the minimum cost in transportation problems.
- Unlike some optimization algorithms that rely on heuristics or approximations, the Hungarian algorithm provides a precise solution without the need for trial-and-error or guesswork.

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

**Flowchart** - Please find flowchart of hungarian algorithm in base directory 'Flowchart-hungarian-algo.png'

**Complexity analysis** 
1. Hungarian Algorithm (Minimum Cost Bipartite Matching)
- Time Complexity:
The Hungarian algorithm written to find the minimum cost from the inputted matrix,
has a time complexity of O(n^3), where n is the number of workers (ambulances) in the bipartite graph:
  - The outer loop runs n times (for each worker).
  - Inside the outer loop, there is a nested loop that iterates over all columns (jobs), 
  which runs m times (where m is the number of jobs).
  - The inner operations involve finding minimum values and updating potentials, which can also be done in O(m) time.
  - Therefore, the overall complexity is O(n⋅m⋅m)=O(n⋅m^2). In the case of a square matrix (where n=m), 
  this simplifies to O(n^3).
- Space Complexity:
The space complexity of the Hungarian algorithm is O(n+m):
  - Arrays for potentials u and v require O(n+m) space.
  - The array p for matching requires O(m) space.
  - The array way also requires O(m) space.
  - Thus, the total space complexity is O(n+m).

**Working example of base algorithm implementation** - Based on the input graph made of ambulances, patients, hospitals, nodes and roads on the file 'test1.pddl'
the result is found to be the following:
  
    adjMatrix
    	0	1	2	3	4	5	6
    0	0,0	2,6	-	2,7	-	-	-
    1	2,6	0,0	6,0	-	6,5	-	-
    2	-	6,0	0,0	-	-	-	-
    3	2,7	-	-	0,0	-	-	-
    4	-	6,5	-	-	0,0	4,5	3,3
    5	-	-	-	-	4,5	0,0	-
    6	-	-	-	-	3,3	-	0,0
    shortestDistances
    	0	1	2	3	4	5	6
    0	0,0	2,6	8,7	2,7	9,1	13,6	12,5
    1	2,6	0,0	6,0	5,3	6,5	11,0	9,8
    2	8,7	6,0	0,0	11,3	12,5	17,0	15,9
    3	2,7	5,3	11,3	0,0	11,8	16,3	15,1
    4	9,1	6,5	12,5	11,8	0,0	4,5	3,3
    5	13,6	11,0	17,0	16,3	4,5	0,0	7,9
    6	12,5	9,8	15,9	15,1	3,3	7,9	0,0
    Shortest paths
    N0 -> N0 (0,000): N0
    N0 -> N1 (2,634): N0,N1
    N0 -> N2 (8,662): N0,N1,N2
    N0 -> N3 (2,655): N0,N3
    N0 -> N4 (9,129): N0,N1,N4
    N0 -> N5 (13,649): N0,N1,N4,N5
    N0 -> N6 (12,467): N0,N1,N4,N6
    N1 -> N0 (2,634): N1,N0
    N1 -> N1 (0,000): N1
    N1 -> N2 (6,028): N1,N2
    N1 -> N3 (5,289): N1,N0,N3
    N1 -> N4 (6,495): N1,N4
    N1 -> N5 (11,015): N1,N4,N5
    N1 -> N6 (9,833): N1,N4,N6
    N2 -> N0 (8,662): N2,N1,N0
    N2 -> N1 (6,028): N2,N1
    N2 -> N2 (0,000): N2
    N2 -> N3 (11,317): N2,N1,N0,N3
    N2 -> N4 (12,523): N2,N1,N4
    N2 -> N5 (17,043): N2,N1,N4,N5
    N2 -> N6 (15,861): N2,N1,N4,N6
    N3 -> N0 (2,655): N3,N0
    N3 -> N1 (5,289): N3,N0,N1
    N3 -> N2 (11,317): N3,N0,N1,N2
    N3 -> N3 (0,000): N3
    N3 -> N4 (11,784): N3,N0,N1,N4
    N3 -> N5 (16,304): N3,N0,N1,N4,N5
    N3 -> N6 (15,123): N3,N0,N1,N4,N6
    N4 -> N0 (9,129): N4,N1,N0
    N4 -> N1 (6,495): N4,N1
    N4 -> N2 (12,523): N4,N1,N2
    N4 -> N3 (11,784): N4,N1,N0,N3
    N4 -> N4 (0,000): N4
    N4 -> N5 (4,520): N4,N5
    N4 -> N6 (3,338): N4,N6
    N5 -> N0 (13,649): N5,N4,N1,N0
    N5 -> N1 (11,015): N5,N4,N1
    N5 -> N2 (17,043): N5,N4,N1,N2
    N5 -> N3 (16,304): N5,N4,N1,N0,N3
    N5 -> N4 (4,520): N5,N4
    N5 -> N5 (0,000): N5
    N5 -> N6 (7,858): N5,N4,N6
    N6 -> N0 (12,467): N6,N4,N1,N0
    N6 -> N1 (9,833): N6,N4,N1
    N6 -> N2 (15,861): N6,N4,N1,N2
    N6 -> N3 (15,123): N6,N4,N1,N0,N3
    N6 -> N4 (3,338): N6,N4
    N6 -> N5 (7,858): N6,N4,N5
    N6 -> N6 (0,000): N6

    Hospitals:
    H_0 @ N4 (3)
    H_1 @ N0 (3)
    
    Demands:  26.0	37.0	44.0	39.0	37.0	34.0	9.0
    Nodes:    0	1	2	3	4	5	6
    
    --------------------------------------------------
    
    Step:0
      Replanning...
      move(A0 5 -> 4)
      move(A1 4 -> 1)
    Step:1
      move(A0 4 -> 1)
      move(A1 1 -> 0)
    Step:2
      pick(A0 P2 @ N1)
      move(A1 0 -> 3)
    Step:3
      move(A0 1 -> 0)
      pick(A1 P1 @ N3)
    Step:4
      drop(A0 P2 @ N0)
      move(A1 3 -> 0)
    Step:5
      Replanning...
      move(A0 0 -> 1)
      drop(A1 P1 @ N0)
    Step:6
      Replanning...
      move(A1 0 -> 1)
    Step:7
      move(A1 1 -> 2)
    Step:8
      pick(A1 P0 @ N2)
    Step:9
      move(A1 2 -> 1)
    Step:10
      move(A1 1 -> 0)
    Step:11
      drop(A1 P0 @ N0)
    Step:12
      Replanning...
      move(A0 1 -> 4)
    
    Done!
    Ambulances:
    A0 @ N4 [] *
    A1 @ N0 [] *
    
    
    Metrics:
      Total distance travelled: 54.5411552485237
      Total time patients waited: 13

**Debugging utilities** 
- Each time the application is executed the results will be saved on the logs folder. 
The results will be archived as a detailed description in the respective .descr files. Meanwhile, the step-by-step plan
is saved in the correspondent .plan file. Of course, these can be accessed anytime for checking and/or comparing results
between different input graphs.
- On top of that, in the cityDump.txt file, we save the current adjacency matrix (the nodes and relations between them),
which can be viewed as a graph if pasted on the recommended webpage inside this file.

**Dynamic updates** - The ability to add new patients in-between steps is crucial for any ambulance route optimization
application. This added feature enables:
- *Dynamic Response to Emergencies*: Emergencies can arise at any moment, and the ability to add new patients in 
real-time allows the system to adapt quickly to changing situations. This ensures that ambulances can respond to 
urgent calls without delay.
- *Improved Resource Allocation*: By allowing new patients to be added on-the-fly, the application can optimize routes 
based on the current demand for services. This helps in efficiently allocating resources and ensuring that ambulances 
are dispatched to the most critical cases first.
- *Increased Operational Efficiency*: The application can continuously recalculate routes and schedules as new patients 
are added, leading to more efficient use of time and fuel. This can reduce response times and improve overall operational
efficiency.
- *Scalability*: As the demand for ambulance services grows, the ability to add patients in real-time allows the system
to scale effectively, accommodating more patients without requiring a complete overhaul of existing processes.
