# Chapter 6 — Graph: MCQ Practice (14 Questions)

> All-MCQ final style. Choose the single best answer (A–D). Answers in `ANSWER_KEY.md`.

For the trace questions (Q9, Q10), use this **undirected graph G**:

```
        0
       / \
      1   2
     / \   \
    3   4   5

Adjacency lists (neighbors listed in ascending order):
0 -> 1, 2
1 -> 0, 3, 4
2 -> 0, 5
3 -> 1
4 -> 1
5 -> 2
```

---

**Q1.** A graph is formally defined as G = (V, E). What do V and E represent?
- A) Values and Elements
- B) Vertices and Edges
- C) Vectors and Entries
- D) Vertices and Endpoints

---

**Q2.** Two vertices are said to be *adjacent* when they:
- A) have the same in-degree
- B) belong to the same spanning tree
- C) share the same edge
- D) are both visited during DFS

---

**Q3.** Which data structure does **Depth-First Search (DFS)** use?
- A) Queue
- B) Stack
- C) Priority queue
- D) Binary search tree

---

**Q4.** Which data structure does **Breadth-First Search (BFS)** use?
- A) Stack
- B) Queue
- C) Hash map
- D) Linked list of stacks

---

**Q5.** What is the space complexity of representing a graph with `n` vertices using an **adjacency matrix**?
- A) O(n)
- B) O(log n)
- C) O(n²)
- D) O(V + E)

---

**Q6.** For a **sparse** graph (few edges, many zeros), which representation is most memory-efficient and recommended?
- A) Adjacency matrix
- B) Adjacency list
- C) A 2D array of weights
- D) A single one-dimensional array

---

**Q7.** An adjacency matrix has `edge[2][5] = 1` and `edge[2][6] = 0`. Which statement is true?
- A) Vertex 2 is adjacent to vertex 6 but not vertex 5
- B) Vertex 2 is adjacent to both 5 and 6
- C) Vertex 2 is adjacent to vertex 5 but not vertex 6
- D) Vertex 2 has no edges at all

---

**Q8.** BFS is especially useful for finding, between two vertices, the path with the:
- A) smallest total weight
- B) largest total weight
- C) fewest number of edges
- D) greatest number of vertices

---

**Q9.** Starting at vertex **0** in graph G above (neighbors pushed in ascending order), which is a valid **DFS** visit order?
- A) 0, 1, 2, 3, 4, 5
- B) 0, 2, 5, 1, 4, 3
- C) 0, 1, 3, 4, 2, 5
- D) 0, 5, 4, 3, 2, 1

---

**Q10.** Starting at vertex **0** in graph G above (neighbors enqueued in ascending order), which is the correct **BFS** visit order?
- A) 0, 1, 2, 3, 4, 5
- B) 0, 1, 3, 4, 2, 5
- C) 0, 2, 1, 5, 4, 3
- D) 0, 1, 2, 4, 3, 5

---

**Q11.** Using DFS to check connectivity, a graph is **connected** when:
- A) the stack becomes empty
- B) the number of vertices visited equals the total number of vertices
- C) every vertex has at least one edge
- D) the spanning tree has more edges than vertices

---

**Q12.** Which of the following is **NOT** a typical application of DFS?
- A) Detecting whether the graph is connected
- B) Detecting whether a cycle exists
- C) Finding a path between two vertices
- D) Finding the fewest-edges path between two vertices

---

**Q13.** Which statement about DFS and BFS is **TRUE**?
- A) Both use a queue
- B) Both produce a spanning tree
- C) BFS uses less memory than DFS
- D) DFS visits vertices level by level

---

**Q14.** In a **weighted** graph stored as an adjacency matrix, what is stored in `edge[i][j]` when an edge exists between `i` and `j`?
- A) Always the value 1
- B) The weight of the edge
- C) The in-degree of vertex j
- D) A reference to the next vertex
