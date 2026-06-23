import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Course-style generic stack built by COMPOSITION over an ArrayList
 * (an ArrayList is held as a field rather than extended).
 *
 * The top of the stack is the END of the ArrayList, so push/pop/peek are all
 * amortized O(1) with no element shifting. LIFO order.
 *
 * @param <E> element type
 */
public class GenericStack<E> {

    private final ArrayList<E> list = new ArrayList<>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    /** Push onto the top (end of the list). */
    public void push(E value) {
        list.add(value);
    }

    /** View the top without removing it. */
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(list.size() - 1);
    }

    /** Remove and return the top. */
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.remove(list.size() - 1);
    }

    @Override
    public String toString() {
        return "stack: " + list;
    }
}
