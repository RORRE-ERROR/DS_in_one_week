import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Chapter 6 - Graph pattern.
 *
 * Adjacency-list undirected graph with:
 *   - dfs(start): uses an explicit Deque as a STACK  -> goes deep
 *   - bfs(start): uses a Deque as a QUEUE            -> level by level
 *
 * Remember for the exam: DFS = Stack, BFS = Queue.
 *
 * Compile & run:  java Pattern.java
 */
public class Pattern {

    public static void main(String[] args) {
        Graph g = new Graph();
        // Build a small undirected graph:
        //        0
        //       / \
        //      1   2
        //     / \   \
        //    3   4   5
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);

        System.out.println("DFS (stack) from 0: " + g.dfs(0));
        System.out.println("BFS (queue) from 0: " + g.bfs(0));
    }
}

/** Undirected graph backed by an adjacency list (Map<Integer, List<Integer>>). */
class Graph {
    private final Map<Integer, List<Integer>> adj = new HashMap<>();

    /** Add an undirected edge (both directions). */
    void addEdge(int u, int v) {
        adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    /** Depth-First Search using an explicit Deque as a STACK. Returns visit order. */
    List<Integer> dfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);                       // push start
        while (!stack.isEmpty()) {
            int v = stack.pop();                 // pop the top
            if (visited.contains(v)) continue;
            visited.add(v);
            order.add(v);
            for (int n : adj.getOrDefault(v, new ArrayList<>())) {
                if (!visited.contains(n)) {
                    stack.push(n);               // push unvisited neighbors
                }
            }
        }
        return order;
    }

    /** Breadth-First Search using a Deque as a QUEUE. Returns visit order. */
    List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);                      // enqueue at the back
        visited.add(start);
        while (!queue.isEmpty()) {
            int v = queue.poll();                // dequeue the front
            order.add(v);
            for (int n : adj.getOrDefault(v, new ArrayList<>())) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    queue.offer(n);              // enqueue unvisited neighbors
                }
            }
        }
        return order;
    }
}
