import java.util.HashMap;

/**
 * Least-Recently-Used cache with O(1) get and put.
 *
 * The classic design: a HashMap for O(1) key lookup, combined with a doubly
 * linked list that keeps entries in usage order. The most-recently-used entry
 * sits right after the head sentinel; the least-recently-used entry (the
 * eviction victim) sits right before the tail sentinel.
 *
 * This is LeetCode 146 in its canonical hand-rolled form.
 */
public class LRUCache {

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final HashMap<Integer, Node> map;
    private final Node head; // sentinel: head.next == most recently used
    private final Node tail; // sentinel: tail.prev == least recently used

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /** Insert node directly after head (mark as most recently used). */
    private void insertFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    /** Return value for key, or -1 if absent. Marks key as most recently used. */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        remove(node);
        insertFront(node);
        return node.value;
    }

    /** Insert or update key. Evicts the LRU entry if over capacity. */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            remove(node);
            insertFront(node);
            return;
        }
        if (map.size() == capacity) {
            Node lru = tail.prev;     // least recently used
            remove(lru);
            map.remove(lru.key);
        }
        Node node = new Node(key, value);
        insertFront(node);
        map.put(key, node);
    }

    public int size() {
        return map.size();
    }
}
