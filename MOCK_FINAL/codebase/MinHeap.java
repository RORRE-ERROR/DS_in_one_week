import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Array-backed binary min-heap (the engine behind a priority queue).
 *
 * Heap property: every parent is <= its children, so the minimum is always at
 * index 0. For a node at index i:
 *   parent(i) = (i - 1) / 2
 *   left(i)   = 2*i + 1
 *   right(i)  = 2*i + 2
 *
 * peek = O(1); offer (siftUp) and poll (siftDown) = O(log n).
 *
 * @param <E> element type, must be Comparable
 */
public class MinHeap<E extends Comparable<E>> {

    private final List<E> data = new ArrayList<>();

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /** Return the minimum without removing it. O(1). */
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("heap is empty");
        }
        return data.get(0);
    }

    private void swap(int i, int j) {
        E tmp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, tmp);
    }

    /** Add a value and restore the heap property upward. O(log n). */
    public void offer(E value) {
        data.add(value);
        int i = data.size() - 1;
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (data.get(i).compareTo(data.get(parent)) >= 0) {
                break;
            }
            swap(i, parent);
            i = parent;
        }
    }

    /** Remove and return the minimum, then restore downward. O(log n). */
    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("heap is empty");
        }
        E min = data.get(0);
        E last = data.remove(data.size() - 1);
        if (!data.isEmpty()) {
            data.set(0, last);
            siftDown(0);
        }
        return min;
    }

    private void siftDown(int i) {
        int n = data.size();
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;
            if (left < n && data.get(left).compareTo(data.get(smallest)) < 0) {
                smallest = left;
            }
            if (right < n && data.get(right).compareTo(data.get(smallest)) < 0) {
                smallest = right;
            }
            if (smallest == i) {
                break;
            }
            swap(i, smallest);
            i = smallest;
        }
    }
}
