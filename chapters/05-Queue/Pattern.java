import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Chapter 5 — Queue & Priority Queue pattern demo.
 *
 * Run with:  java Pattern.java
 *
 * Demonstrates:
 *   1) A composition-based generic queue (GenericQueue<E>) showing FIFO order.
 *   2) A java.util.PriorityQueue<Integer> showing poll order (min-heap default).
 */
public class Pattern {
    public static void main(String[] args) {
        // ---- 1) FIFO demo with composition-based GenericQueue ----
        System.out.println("=== GenericQueue (FIFO) ===");
        GenericQueue<String> queue = new GenericQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        System.out.println("After enqueue A,B,C,D -> " + queue);
        System.out.println("size = " + queue.size());
        System.out.println("peek (front, not removed) = " + queue.peek());

        System.out.print("Dequeue order (FIFO): ");
        while (!queue.isEmpty()) {
            System.out.print(queue.dequeue() + " ");
        }
        System.out.println();
        System.out.println("isEmpty now? " + queue.isEmpty());

        // ---- 2) PriorityQueue demo (min-heap by default) ----
        System.out.println("\n=== java.util.PriorityQueue<Integer> (min-heap default) ===");
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(30);
        pq.offer(10);
        pq.offer(40);
        pq.offer(20);
        System.out.println("offered: 30, 10, 40, 20");
        System.out.println("size = " + pq.size());
        System.out.println("peek (smallest, not removed) = " + pq.peek());
        System.out.println("contains(40)? " + pq.contains(40));

        System.out.print("poll order (smallest priority value out first): ");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();
    }
}

/**
 * Composition-based generic queue: holds a java.util.LinkedList as a field
 * (preferred over inheritance). FIFO: enqueue at the rear, dequeue from the front.
 */
class GenericQueue<E> {
    private LinkedList<E> list = new LinkedList<>();   // composition

    /** Insert at the REAR (O(1)). */
    public void enqueue(E e) {
        list.addLast(e);
    }

    /** Remove and return the FRONT element (O(1)). */
    public E dequeue() {
        return list.removeFirst();
    }

    /** View the FRONT element without removing it. */
    public E peek() {
        return list.getFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return "Queue(front->rear): " + list.toString();
    }
}
