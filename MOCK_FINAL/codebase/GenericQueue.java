import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Course-style generic queue built by COMPOSITION over a LinkedList.
 *
 * A LinkedList is preferred over an ArrayList here because dequeue removes from
 * the FRONT; on an array that would shift every remaining element (O(n)),
 * whereas a linked list removes the head in O(1). FIFO order.
 *
 * @param <E> element type
 */
public class GenericQueue<E> {

    private final LinkedList<E> list = new LinkedList<>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    /** Add to the rear/back. */
    public void enqueue(E value) {
        list.addLast(value);
    }

    /** Remove and return from the front/head. */
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return list.removeFirst();
    }

    /** View the front without removing it. */
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return list.getFirst();
    }

    @Override
    public String toString() {
        return "queue(front->back): " + list;
    }
}
