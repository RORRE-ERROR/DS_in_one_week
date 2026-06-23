/**
 * Union-Find / Disjoint Set Union (DSU) over n elements 0..n-1.
 *
 * Two optimizations make near-constant-time operations:
 *   - Path compression in find(): flatten the tree as we walk up.
 *   - Union by rank in union(): attach the shorter tree under the taller one.
 *
 * Used to track connected components (e.g. LeetCode 547 Number of Provinces,
 * Kruskal's MST). count tracks how many disjoint sets remain.
 */
public class UnionFind {

    private final int[] parent;
    private final int[] rank;
    private int count; // number of disjoint sets

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i; // each element starts as its own root
        }
    }

    /** Find the root of x with path compression. */
    public int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]]; // point to grandparent
            x = parent[x];
        }
        return x;
    }

    /** Merge the sets of a and b. Returns false if already joined. */
    public boolean union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) {
            return false;
        }
        if (rank[ra] < rank[rb]) {
            int tmp = ra;
            ra = rb;
            rb = tmp;
        }
        parent[rb] = ra;
        if (rank[ra] == rank[rb]) {
            rank[ra]++;
        }
        count--;
        return true;
    }

    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }

    /** Number of disjoint sets remaining. */
    public int count() {
        return count;
    }
}
