import java.util.NoSuchElementException;

/**
 * Generic doubly linked list with sentinel (dummy) head and tail nodes.
 *
 * Using sentinels removes all the "is this the first/last node?" special
 * cases: every real node always has a non-null prev and next, so add/remove
 * are uniform O(1) splices.
 *
 * @param <E> element type
 */
public class DoublyLinkedList<E> {

    /** Internal node. Package-private fields for concise splicing. */
    static class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        Node(E value) {
            this.value = value;
        }
    }

    private final Node<E> head; // sentinel before the first real node
    private final Node<E> tail; // sentinel after the last real node
    private int size;

    public DoublyLinkedList() {
        head = new Node<>(null);
        tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** Splice newNode in between p and p.next. */
    private void linkBetween(Node<E> newNode, Node<E> p, Node<E> q) {
        newNode.prev = p;
        newNode.next = q;
        p.next = newNode;
        q.prev = newNode;
        size++;
    }

    /** Unlink x from the list and return its value. O(1). */
    private E unlink(Node<E> x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        x.prev = null;
        x.next = null;
        size--;
        return x.value;
    }

    /** Add to the front. O(1). */
    public void addFirst(E value) {
        linkBetween(new Node<>(value), head, head.next);
    }

    /** Add to the back. O(1). */
    public void addLast(E value) {
        linkBetween(new Node<>(value), tail.prev, tail);
    }

    /** Remove and return the front element. O(1). */
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        return unlink(head.next);
    }

    /** Remove and return the back element. O(1). */
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        return unlink(tail.prev);
    }

    /** Linear search; returns index or -1. O(n). */
    public int indexOf(E value) {
        int i = 0;
        for (Node<E> cur = head.next; cur != tail; cur = cur.next) {
            if (cur.value == null ? value == null : cur.value.equals(value)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Node<E> cur = head.next; cur != tail; cur = cur.next) {
            sb.append(cur.value);
            if (cur.next != tail) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }
}
