import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Undirected graph stored as an adjacency list (a map from vertex to its
 * neighbour list). Adjacency lists use O(V + E) space, which is far better
 * than an O(V^2) adjacency matrix for sparse graphs.
 *
 * Demonstrates the two canonical traversals:
 *   - DFS uses a STACK (here, an explicit ArrayDeque) -> go deep first.
 *   - BFS uses a QUEUE (ArrayDeque)                  -> go level by level.
 *
 * A TreeMap is used so neighbours come out in a deterministic (sorted) order,
 * which makes the traversal output predictable for tracing.
 */
public class Graph {

    private final Map<Integer, List<Integer>> adj = new TreeMap<>();

    public void addVertex(int v) {
        adj.computeIfAbsent(v, k -> new ArrayList<>());
    }

    /** Add an undirected edge u-v (creates vertices if needed). */
    public void addEdge(int u, int v) {
        addVertex(u);
        addVertex(v);
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public int vertexCount() {
        return adj.size();
    }

    /** Depth-first traversal from start, returning visit order. Uses a stack. */
    public List<Integer> dfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);
            order.add(node);
            // Push neighbours; reverse so the smallest is processed first.
            List<Integer> neighbours = adj.getOrDefault(node, new ArrayList<>());
            for (int i = neighbours.size() - 1; i >= 0; i--) {
                if (!visited.contains(neighbours.get(i))) {
                    stack.push(neighbours.get(i));
                }
            }
        }
        return order;
    }

    /** Breadth-first traversal from start, returning visit order. Uses a queue. */
    public List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node);
            for (int neighbour : adj.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.offer(neighbour);
                }
            }
        }
        return order;
    }

    /** Connected iff DFS from vertex 0 reaches every vertex. */
    public boolean isConnected() {
        if (adj.isEmpty()) {
            return true;
        }
        int start = adj.keySet().iterator().next();
        return dfs(start).size() == adj.size();
    }
}
