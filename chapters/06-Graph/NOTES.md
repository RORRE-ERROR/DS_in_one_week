# Chapter 6 — Graph (Exam Notes)

## What is this chapter about? (read me first)

A **graph** is just a way to describe **things that are connected to other things**.

Think of a **map of cities**: each city is a dot, and each road between two cities is a line. In graph language:

- the dots (cities) are called **vertices** (also called **nodes**),
- the lines (roads) are called **edges**.

That's the whole idea. Once you can draw dots and connect them with lines, you understand the heart of graphs. Other real examples:

- **Social network:** people = vertices, friendships = edges.
- **Flight network:** airports = vertices, flights = edges.
- **Web pages:** pages = vertices, links = edges.

The two big things this chapter tests are: (1) how we **store** a graph in code (adjacency **matrix** vs **list**), and (2) how we **walk through** a graph visiting every vertex (**DFS** vs **BFS**). The DFS-vs-BFS part is the most heavily tested — we spend the most time on it below.

---

## 1. Concept

**What & why:** Before we store or traverse a graph, we need the vocabulary. These few words show up in every exam question, so learn them once and they pay off everywhere.

- A **graph** is both a math concept and a data structure: a set of **vertices** (nodes) and **edges**.
- Formal definition: **G = (V, E)** — `V` = set of vertices, `E` = set of edges. (Read it as: "a graph G is made of a vertex set V and an edge set E.")
- Entities → **nodes/vertices**; interactions between entities → **edges**. (Example: people are entities → vertices; "is friends with" is an interaction → edge.)
- **Adjacent:** two vertices are *adjacent* if they **share the same edge** — i.e. there's a direct line between them. (Cities directly joined by one road are adjacent; cities you can only reach by passing through another city are *not* adjacent.)
- **Path:** starting from vertex `p`, if after travelling along one or more edges we reach vertex `q`, there is a **path** from `p` to `q`. (A path is a route, possibly passing through several vertices.)

### Graph types

**What & why:** Edges can come in flavours. Knowing the flavour tells you how to read and store the graph.

- **Directed** = one-way streets. An edge `A → B` lets you go from A to B but not back.
- **Undirected** = two-way streets. An edge `A — B` works both ways.
- **Weighted** = each road has a number on it, like a distance or cost (e.g. "120 km").
- **Unweighted** = roads have no number; we only care that a connection exists.

| Property | Meaning |
|---|---|
| **Directed** | edges have a direction (one-way) |
| **Undirected** | edges have no direction (two-way) |
| **Unweighted** | edges carry no value |
| **Weighted** | each edge carries a **value = weight** of the edge |

🧠 **Quick check:** A graph of Twitter "follows" — directed or undirected?
*Answer:* Directed (you can follow someone who doesn't follow you back).

---

## 2. Modelling a Graph — Adjacency Matrix vs Adjacency List (★)

**What & why:** A graph is a picture in our heads, but a computer needs it as data. There are two standard ways to store it. Both describe the *same* graph; they just trade memory for speed differently.

Let's use **one small example graph** for the rest of these notes so you can see everything on the same picture. It's **undirected** with 5 vertices (0–4):

```
        0
       / \
      1   2
       \ /
        3
        |
        4

Edges: 0-1, 0-2, 1-3, 2-3, 3-4
```

### The same graph, two ways

**Adjacency matrix** = an `n × n` grid (here 5×5). Put a `1` in cell `[i][j]` if there's an edge between `i` and `j`, otherwise `0`. (For an undirected graph the grid is symmetric — `[i][j]` mirrors `[j][i]`.)

```
        0  1  2  3  4
      +---------------
   0  | 0  1  1  0  0
   1  | 1  0  0  1  0
   2  | 1  0  0  1  0
   3  | 0  1  1  0  1
   4  | 0  0  0  1  0
```
Read row 0 left to right: "vertex 0 connects to 1 and 2" → matches the picture.

**Adjacency list** = for each vertex, keep a list of its neighbours only.

```
   0 -> [1, 2]
   1 -> [0, 3]
   2 -> [0, 3]
   3 -> [1, 2, 4]
   4 -> [3]
```
Same information, but we only write down connections that actually exist (no rows full of zeros).

**How to read the comparison table below:** "Space" means how much memory it costs. `n` = number of vertices, `V` = vertices, `E` = edges. **Sparse** = few edges (lots of zeros in the matrix). **Dense** = lots of edges.

| | **Adjacency Matrix** | **Adjacency List** |
|---|---|---|
| Structure | `n × n` 2D array; `edge[i][j]=1` if an edge exists (or the **weight**), else `0` | one linked list per vertex holding its neighbors |
| Space | **O(n²)** — wasteful for sparse graphs | **O(V + E)** — efficient for sparse graphs |
| Best for | **DENSE** graphs (many edges) | **SPARSE** graphs (few edges, many 0s) |
| Strength | fast, easy edge lookup | saves memory |

- **Reading the matrix:** `edge[2][5]=1` and `edge[2][6]=0` → vertex 2 is **adjacent to 5 but NOT to 6**.
- **Weighted graph:** store the **weight** in the cell instead of `1`.
- **Sparse matrix** = many zeros → big waste of memory → prefer the **linked list (adjacency list)**.
- **Dense matrix** = many edges → the `O(n²)` matrix is **justifiable**.
- Mnemonic: **Dense → Matrix, Sparse → List.**

🧠 **Quick check:** In the matrix above, what is `edge[3][4]`, and what does it mean?
*Answer:* `1` — vertex 3 is adjacent to vertex 4 (there's an edge 3-4).

---

## 3. Degrees

**What & why:** The **degree** of a vertex just counts how many edges touch it — how "popular" or "connected" that node is. For **directed** graphs, we split this into edges pointing *in* vs *out*.

- **in-degree** = number of edges coming **into** a vertex (arrows pointing at it).
- **out-degree** = number of edges going **out** of a vertex (arrows leaving it).
- Relevant mainly for **directed** graphs. (In an undirected graph there's just one "degree" = number of edges touching the vertex.)
- Lookup methods return **-1** when the vertex is not found.

*Example (directed):* if `A → B` and `C → B`, then B has in-degree 2. If B has no outgoing arrows, its out-degree is 0.

---

## 4. Graph Traversal — DFS vs BFS (★★ MEMORIZE)

**What & why:** **Traversal** means "visit every vertex in the graph, one by one." Imagine exploring every room in a building — you need a system so you don't miss a room or revisit one forever. There are two classic systems, and they differ in *one core idea*: which helper structure you use to remember "where to go next."

> **The single thing to never forget:**
> **DFS = Stack = goes DEEP.** **BFS = Queue = goes level-by-level (broad).**

Both DFS and BFS produce a **spanning tree** (the set of edges actually used to reach each vertex during the walk).

**How to read the table below:** each row compares the two methods on one feature. The first row is the most important fact in the chapter.

| | **DFS (Depth-First Search)** | **BFS (Breadth-First Search)** |
|---|---|---|
| **Data structure** | **STACK** | **QUEUE** |
| Order | goes **deep** along one branch first | visits **level by level** |
| Result | spanning tree | spanning tree |
| Memory | uses **less** memory | uses **MORE** memory than DFS |

**Why stack vs queue causes "deep" vs "level-by-level":**
- A **stack** is LIFO (Last In, First Out) — like a pile of plates, you take the *most recent* one. So DFS keeps diving into the newest neighbour → it goes deep.
- A **queue** is FIFO (First In, First Out) — like a line at a shop, you serve the *oldest* one first. So BFS finishes all the close neighbours before moving outward → it spreads level by level.

### DFS algorithm (uses a STACK)
1. **Push** any start vertex onto the **stack**.
2. **Pop** the top item → add it to the **visited** list.
3. List that vertex's adjacent nodes; **push** the ones **not yet visited** onto the top of the stack.
4. Repeat steps 2–3 until the **stack is empty**.

### BFS algorithm (uses a QUEUE)
1. **Enqueue** any start vertex at the back of the **queue**.
2. **Dequeue** the front item → add it to the **visited** list.
3. List that vertex's adjacent nodes; **enqueue** the ones **not yet visited** at the back of the queue.
4. Repeat steps 2–3 until the **queue is empty**.

### Step-by-step trace on our example graph (start at vertex 0)

Reminder of the graph and neighbour lists:

```
   0 -> [1, 2]
   1 -> [0, 3]
   2 -> [0, 3]
   3 -> [1, 2, 4]
   4 -> [3]
```

#### DFS trace (STACK). We pop the top, then push its unvisited neighbours.

| Step | Action | Stack (top is right) | Visited (in order) |
|---|---|---|---|
| 1 | push start 0 | `[0]` | — |
| 2 | pop 0, visit; push 1, 2 | `[1, 2]` | 0 |
| 3 | pop 2, visit; push 3 | `[1, 3]` | 0, 2 |
| 4 | pop 3, visit; push 1, 4 (0,2 done) | `[1, 1, 4]` | 0, 2, 3 |
| 5 | pop 4, visit; no new neighbours | `[1, 1]` | 0, 2, 3, 4 |
| 6 | pop 1, visit | `[1]` | 0, 2, 3, 4, 1 |
| 7 | pop 1, already visited → skip | `[]` | 0, 2, 3, 4, 1 |

**DFS visit order: 0 → 2 → 3 → 4 → 1.** Notice it dove from 0 straight down to 2, 3, 4 (deep!) before coming back for 1.

*(Note: the exact order depends on the order neighbours are pushed; the key idea is the "go deep first" behaviour, not the specific tie-breaks.)*

#### BFS trace (QUEUE). We dequeue the front, then enqueue its unvisited neighbours.

| Step | Action | Queue (front is left) | Visited (in order) |
|---|---|---|---|
| 1 | enqueue start 0 | `[0]` | 0 |
| 2 | dequeue 0; enqueue 1, 2 | `[1, 2]` | 0 |
| 3 | dequeue 1; enqueue 3 | `[2, 3]` | 0, 1 |
| 4 | dequeue 2; (3 already queued) | `[3]` | 0, 1, 2 |
| 5 | dequeue 3; enqueue 4 | `[4]` | 0, 1, 2, 3 |
| 6 | dequeue 4; no new neighbours | `[]` | 0, 1, 2, 3, 4 |

**BFS visit order: 0 → 1 → 2 → 3 → 4.** Notice it visited *both* of 0's neighbours (1 and 2) before going deeper — level by level, like ripples spreading out from 0.

> **Make it unforgettable:** DFS uses a **stack** and tunnels **deep**; BFS uses a **queue** and sweeps **level-by-level**. Stack→Deep, Queue→Layers.

### Applications

**What & why:** Once you can traverse, you can answer real questions. DFS and BFS each shine at different ones.

- **DFS:**
  - Detect whether a graph is **connected** — if `#vertices visited == #vertices in graph` → **connected**; otherwise **not connected**. (If your walk reached everyone, the graph is one piece.)
  - Detect whether a **path** exists between two vertices / **find a path** between two vertices.
  - Detect whether there is a **cycle** in the graph (a loop that returns to where it started).
- **BFS:**
  - Find the path with the **fewest number of edges** (NOT smallest weight/cost!) between two vertices. (Because BFS reaches near things first, it naturally finds the shortest "number of hops".)
  - Check whether a graph is **bipartite** (vertices split into two disjoint sets with no edge inside a set).
  - Note: BFS is **less memory-efficient** than DFS.

🧠 **Quick check:** You want the route between two cities that passes through the *fewest cities* (ignoring distances). DFS or BFS?
*Answer:* BFS — it finds the fewest-edges (fewest-hops) path.

---

## 5. Java Skeleton — Adjacency List + DFS/BFS

**What & why:** Here's the example graph and both traversals written in real Java. Notice the only structural difference between `dfs` and `bfs` is **stack vs queue** — exactly the fact you're memorizing. Read the comments; they map straight back to the algorithm steps above.

```java
import java.util.*;

// Undirected graph backed by an adjacency list
class Graph {
    private Map<Integer, List<Integer>> adj = new HashMap<>();

    void addEdge(int u, int v) {                 // undirected: add both directions
        adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    // DFS uses a STACK -> goes deep
    List<Integer> dfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int v = stack.pop();                 // pop the top
            if (visited.contains(v)) continue;
            visited.add(v);
            order.add(v);
            for (int n : adj.getOrDefault(v, List.of()))
                if (!visited.contains(n)) stack.push(n);   // push unvisited neighbors
        }
        return order;
    }

    // BFS uses a QUEUE -> level by level
    List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);                      // enqueue at back
        visited.add(start);
        while (!queue.isEmpty()) {
            int v = queue.poll();                // dequeue the front
            order.add(v);
            for (int n : adj.getOrDefault(v, List.of()))
                if (!visited.contains(n)) { visited.add(n); queue.offer(n); }
        }
        return order;
    }
}
```

---

## ★ Exam Tips

1. **DFS = Stack, BFS = Queue.** (The single most-tested fact in this chapter.)
2. **DFS goes deep** along a branch; **BFS goes level by level**.
3. **BFS finds the fewest-EDGES path** (unweighted shortest path) — **NOT** the least-weight path.
4. **Adjacency matrix = O(n²) space**; sparse graphs waste memory in a matrix.
5. **Sparse graph → adjacency list; dense graph → adjacency matrix.**
6. **DFS connectivity test:** `#visited == #vertices` → connected.
7. Both DFS and BFS produce a **spanning tree**.
8. **BFS uses more memory than DFS.**
9. `edge[i][j]=1` → `i` adjacent to `j`; weighted graphs store the **weight** instead of 1.
