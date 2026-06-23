# Chapter 2 — LeetCode: Implementing a Collection ADT

> Theme: an **ADT** says *what* a collection does; here you supply the *how* (the data structure). Both problems ask you to implement a collection's interface from scratch — exactly the ArrayBag/LinkedBag exercise.

---

## 705. Design HashSet
https://leetcode.com/problems/design-hashset/

**ADT framing:** A *Set* is a collection that stores elements with **no duplicates**. You implement the operations `add`, `contains`, `remove` — the interface — while hiding *how* (an array of buckets) from the user. (Contrast with a Bag, which *allows* duplicates.)

**Approach:** Use **separate chaining**. Keep a fixed array of buckets; hash a key to a bucket index with `key % numBuckets`; each bucket is a linked list (`LinkedList`) holding the keys that collide there. `add`/`remove`/`contains` operate only within the relevant bucket.

**Complexity:** average **O(1)** per operation (with a good spread), worst case O(n/k) per bucket. Space O(n + k) where k = number of buckets.

```java
class MyHashSet {
    private static final int BUCKETS = 1009; // prime reduces collisions
    private final java.util.LinkedList<Integer>[] table;

    @SuppressWarnings("unchecked")
    public MyHashSet() {
        table = new java.util.LinkedList[BUCKETS];
        for (int i = 0; i < BUCKETS; i++) table[i] = new java.util.LinkedList<>();
    }

    private int hash(int key) { return key % BUCKETS; }

    public void add(int key) {
        java.util.LinkedList<Integer> bucket = table[hash(key)];
        if (!bucket.contains(key)) bucket.add(key); // no duplicates
    }

    public void remove(int key) {
        table[hash(key)].remove(Integer.valueOf(key));
    }

    public boolean contains(int key) {
        return table[hash(key)].contains(key);
    }
}
```

---

## 707. Design Linked List
https://leetcode.com/problems/design-linked-list/

> (1206. Design Skiplist is too hard for this chapter — we use 707 instead, which mirrors the **LinkedBag** node-based implementation.)

**ADT framing:** A *List* ADT supports adding, removing, and accessing elements by index. Here you implement it with a **linked-node data structure** (the same node idea behind LinkedBag), proving one ADT can be built from linked nodes.

**Approach:** A **singly linked list** with a `size` counter and a `head`. To reach index `i`, traverse `i` nodes from the head. Insertion/deletion just relink the `next` pointers of the node **before** the target. A dummy head can simplify edge cases; below uses explicit head handling.

**Complexity:** `get`, `addAtIndex`, `deleteAtIndex` are **O(n)** (must traverse); `addAtHead` is **O(1)**; `addAtTail` is O(n) without a tail pointer. Space O(n).

```java
class MyLinkedList {
    private static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    private Node head;
    private int size;

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        Node cur = head;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur.val;
    }

    public void addAtHead(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;
        size++;
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;
        if (index == 0) { addAtHead(val); return; }
        Node prev = head;
        for (int i = 0; i < index - 1; i++) prev = prev.next;
        Node node = new Node(val);
        node.next = prev.next;
        prev.next = node;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        if (index == 0) { head = head.next; size--; return; }
        Node prev = head;
        for (int i = 0; i < index - 1; i++) prev = prev.next;
        prev.next = prev.next.next; // unlink the target node
        size--;
    }
}
```

---

## Resources
- **NeetCode** — https://neetcode.io (Design HashSet & Linked List walkthroughs; arrays/linked-list patterns).
- **Carrano, *Data Structures and Abstractions with Java* (4e), Chapter 1** — Bags, the `BagInterface`, ArrayBag & LinkedBag (the source for this chapter).
- **GeeksforGeeks — Abstract Data Types** — https://www.geeksforgeeks.org/abstract-data-types/ (ADT vs data structure, List/Stack/Queue ADTs).
