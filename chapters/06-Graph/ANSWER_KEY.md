# Chapter 6 — Graph: Answer Key

**Q1 — B) Vertices and Edges.** A graph is formally G = (V, E): V is the set of vertices (nodes), E is the set of edges (interactions).

**Q2 — C) share the same edge.** Two vertices are *adjacent* exactly when an edge connects them.

**Q3 — B) Stack.** DFS uses a **stack**: push start, pop the top, push its unvisited neighbors, repeat until the stack is empty. The stack drives the "go deep first" behavior.

**Q4 — B) Queue.** BFS uses a **queue**: enqueue start, dequeue the front, enqueue its unvisited neighbors, repeat until the queue is empty — giving level-by-level order.

**Q5 — C) O(n²).** An adjacency matrix is an `n × n` 2D array, so it needs O(n²) space regardless of how many edges exist.

**Q6 — B) Adjacency list.** A sparse graph has few edges (many 0s in a matrix); the adjacency list uses only O(V + E) space and avoids the wasted zeros.

**Q7 — C) Vertex 2 is adjacent to vertex 5 but not vertex 6.** `edge[2][5]=1` means an edge exists between 2 and 5; `edge[2][6]=0` means no edge between 2 and 6.

**Q8 — C) fewest number of edges.** BFS finds the path with the smallest *edge count* (unweighted shortest path) — NOT the smallest weight/cost/distance.

**Q9 — B) 0, 2, 5, 1, 4, 3.** DFS with a stack, neighbors pushed in ascending order: push 0 → pop 0; push 1,2 (2 on top) → pop 2; push 5 → pop 5; pop 1; push 3,4 → pop 4 → pop 3. Visit order = 0, 2, 5, 1, 4, 3. (Pushing in ascending order means the larger-numbered neighbor sits on top and is explored first.)

**Q10 — A) 0, 1, 2, 3, 4, 5.** BFS with a queue, neighbors enqueued in ascending order: dequeue 0 → enqueue 1,2; dequeue 1 → enqueue 3,4; dequeue 2 → enqueue 5; then dequeue 3, 4, 5. Level-by-level visit order = 0, 1, 2, 3, 4, 5.

**Q11 — B) the number of vertices visited equals the total number of vertices.** Run DFS from any vertex; if every vertex was reached (#visited == #vertices), the graph is connected, otherwise it is not.

**Q12 — D) Finding the fewest-edges path between two vertices.** That is a **BFS** application. DFS is used for connectivity, cycle detection, and finding *a* path between two vertices.

**Q13 — B) Both produce a spanning tree.** Both DFS and BFS yield a spanning tree. A is false (DFS uses a stack), C is false (BFS uses *more* memory than DFS), D describes BFS not DFS.

**Q14 — B) The weight of the edge.** In a weighted adjacency matrix the cell stores the edge's weight instead of a plain 1.
