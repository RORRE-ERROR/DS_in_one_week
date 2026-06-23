# Chapter 6 — Graph: 14-Day LeetCode Plan (Day 14 Capstone)

This is **Day 14**, the capstone day for the Graph chapter. The graph traversal mindset (DFS = stack/recursion deep, BFS = queue level-by-level) is exactly what these problems test. Master the four below and you can handle most grid/graph interview questions.

---

## Day 14 Capstone — 200. Number of Islands (DFS / BFS on a grid)

- **Link:** https://leetcode.com/problems/number-of-islands/
- **Approach:** Treat the grid as a graph where each `'1'` cell is a vertex connected to its 4-directional `'1'` neighbors. Scan every cell; when you find an unvisited `'1'`, increment the island count and run DFS (or BFS) to "sink" the whole connected component (mark visited / set to `'0'`). The number of times you launch a traversal = number of islands. This is **connected-components counting** — a direct DFS application.
- **Complexity:** Time **O(m·n)** (each cell visited once), Space **O(m·n)** worst case (recursion stack / explicit stack for an all-land grid).

```java
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length, cols = grid[0].length, count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    dfs(grid, r, c);   // sink the whole island
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length
                || grid[r][c] != '1') return;
        grid[r][c] = '0';              // mark visited
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}
```

---

## 547. Number of Provinces (DFS / Union-Find)

- **Link:** https://leetcode.com/problems/number-of-provinces/
- **Approach:** `isConnected` is an **adjacency matrix**. A "province" is a connected component of cities. Run DFS from each unvisited city, marking every city reachable through `isConnected[i][j] == 1`; each new DFS launch = one province. (Union-Find is an alternative: union connected cities, then count distinct roots.)
- **Complexity:** DFS — Time **O(n²)** (scanning the whole matrix), Space **O(n)** for the visited array + recursion.

```java
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                provinces++;
                dfs(isConnected, visited, i);
            }
        }
        return provinces;
    }

    private void dfs(int[][] g, boolean[] visited, int i) {
        visited[i] = true;
        for (int j = 0; j < g.length; j++) {
            if (g[i][j] == 1 && !visited[j]) {
                dfs(g, visited, j);
            }
        }
    }
}
```

---

## 133. Clone Graph (DFS with a hash map)

- **Link:** https://leetcode.com/problems/clone-graph/
- **Approach:** Deep-copy an undirected graph. Use a `HashMap<Node, Node>` mapping each original node to its clone to avoid infinite loops on cycles. DFS: if a node is already cloned, return its clone; otherwise create the clone, record it in the map, then recursively clone all neighbors.
- **Complexity:** Time **O(V + E)**, Space **O(V)** for the map + recursion stack.

```java
// Definition for a Node.
// class Node { public int val; public List<Node> neighbors; ... }

class Solution {
    private Map<Node, Node> seen = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (seen.containsKey(node)) return seen.get(node);

        Node clone = new Node(node.val);
        seen.put(node, clone);                 // record before recursing (handles cycles)
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }
        return clone;
    }
}
```

---

## 994. Rotting Oranges (BFS — multi-source, level by level)

- **Link:** https://leetcode.com/problems/rotting-oranges/
- **Approach:** Classic **multi-source BFS**. Enqueue every initially rotten orange (value 2) as the starting level and count fresh oranges. Each BFS "level" = one minute: dequeue current-level oranges, rot their fresh 4-directional neighbors and enqueue them. Because BFS expands level by level, the number of levels = minutes elapsed (BFS gives the fewest-steps answer). If any fresh orange remains, return -1.
- **Complexity:** Time **O(m·n)**, Space **O(m·n)** for the queue.

```java
import java.util.*;

class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length, fresh = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) queue.offer(new int[]{r, c});
                else if (grid[r][c] == 1) fresh++;
            }
        if (fresh == 0) return 0;

        int minutes = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!queue.isEmpty() && fresh > 0) {
            minutes++;
            int size = queue.size();           // process one level (one minute)
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                for (int[] d : dirs) {
                    int nr = cell[0] + d[0], nc = cell[1] + d[1];
                    if (nr >= 0 && nc >= 0 && nr < rows && nc < cols
                            && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;       // rot it
                        fresh--;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        return fresh == 0 ? minutes : -1;
    }
}
```

---

## Resources

- **NeetCode — Graphs playlist:** https://neetcode.io/courses/dsa-for-beginners (Graphs section) and the [NeetCode 150 Graphs](https://neetcode.io/practice) problem set — clear video walkthroughs of islands, provinces, clone graph, and BFS-on-grid patterns.
- **labuladong — DFS/BFS framework:** https://labuladong.online/algo/en/ — the "Graph DFS/BFS traversal framework" and "BFS framework" articles; the core idea is one reusable `visited[]` + traverse template that maps directly to DFS (stack/recursion) vs BFS (queue, level-by-level).
- **Reminder of the chapter rule:** DFS = stack (go deep), BFS = queue (level by level, fewest edges). Every problem above is one of those two templates applied to a grid or matrix.
