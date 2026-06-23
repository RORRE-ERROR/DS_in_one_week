// Chapter 3 — Linked List pattern reference.
// Single compilable file. Run with:  java Pattern.java
//
// Implements:
//   - MyLinkedList<E>        : generic SINGLY linked list
//   - MyDoublyLinkedList<E>  : generic DOUBLY linked list (O(1) removeLast)

public class Pattern {
    public static void main(String[] args) {
        System.out.println("=== Singly Linked List (MyLinkedList) ===");
        MyLinkedList<String> s = new MyLinkedList<>();
        s.addLast("B");
        s.addLast("C");
        s.addFirst("A");      // A B C
        s.addLast("D");       // A B C D
        System.out.println("after adds:        " + s + "  size=" + s.size());
        System.out.println("indexOf(\"C\"):      " + s.indexOf("C"));
        System.out.println("indexOf(\"Z\"):      " + s.indexOf("Z"));
        System.out.println("removeFirst() -> " + s.removeFirst() + ", list: " + s); // A
        System.out.println("removeLast()  -> " + s.removeLast()  + ", list: " + s); // D (O(n))
        System.out.println("final:             " + s + "  size=" + s.size());

        System.out.println();
        System.out.println("=== Doubly Linked List (MyDoublyLinkedList) ===");
        MyDoublyLinkedList<Integer> d = new MyDoublyLinkedList<>();
        d.addLast(10);
        d.addLast(20);
        d.addFirst(5);        // 5 10 20
        d.addLast(30);        // 5 10 20 30
        System.out.println("after adds:        " + d + "  size=" + d.size());
        System.out.println("removeLast() -> " + d.removeLast() + ", list: " + d); // 30, O(1)
        System.out.println("removeLast() -> " + d.removeLast() + ", list: " + d); // 20, O(1)
        System.out.println("final:             " + d + "  size=" + d.size());
    }
}

// ---------------------------------------------------------------------------
// Generic SINGLY linked list
// ---------------------------------------------------------------------------
class MyLinkedList<E> {
    private class Node {
        E element;
        Node next;
        Node(E e) { element = e; }
    }

    private Node head;   // first node
    private Node tail;   // last node
    private int size;

    // O(1)
    public void addFirst(E e) {
        Node n = new Node(e);
        if (head == null) {          // empty list: head == tail == null
            head = tail = n;
        } else {
            n.next = head;
            head = n;
        }
        size++;
    }

    // O(1) with tail reference
    public void addLast(E e) {
        Node n = new Node(e);
        if (tail == null) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
    }

    // O(1)
    public E removeFirst() {
        if (head == null) return null;
        E e = head.element;
        head = head.next;
        if (head == null) tail = null; // list became empty
        size--;
        return e;
    }

    // O(n): must traverse to find the node BEFORE tail (no prev pointer)
    public E removeLast() {
        if (tail == null) return null;
        E e = tail.element;
        if (head == tail) {            // single node
            head = tail = null;
        } else {
            Node cur = head;
            while (cur.next != tail) {  // stop at node before tail
                cur = cur.next;
            }
            cur.next = null;
            tail = cur;
        }
        size--;
        return e;
    }

    // O(n): returns index of first match, or -1
    public int indexOf(E e) {
        int i = 0;
        for (Node cur = head; cur != null; cur = cur.next, i++) {
            if (cur.element == null ? e == null : cur.element.equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public int size() { return size; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur.element);
            if (cur.next != null) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}

// ---------------------------------------------------------------------------
// Generic DOUBLY linked list (O(1) removeLast via prev pointer)
// ---------------------------------------------------------------------------
class MyDoublyLinkedList<E> {
    private class Node {
        E element;
        Node next;
        Node prev;
        Node(E e) { element = e; }
    }

    private Node head;
    private Node tail;
    private int size;

    // O(1)
    public void addFirst(E e) {
        Node n = new Node(e);
        if (head == null) {
            head = tail = n;
        } else {
            n.next = head;
            head.prev = n;
            head = n;
        }
        size++;
    }

    // O(1)
    public void addLast(E e) {
        Node n = new Node(e);
        if (tail == null) {
            head = tail = n;
        } else {
            tail.next = n;
            n.prev = tail;
            tail = n;
        }
        size++;
    }

    // O(1): the prev pointer means no traversal is needed
    public E removeLast() {
        if (tail == null) return null;
        E e = tail.element;
        if (head == tail) {            // single node
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return e;
    }

    public int size() { return size; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur.element);
            if (cur.next != null) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}
