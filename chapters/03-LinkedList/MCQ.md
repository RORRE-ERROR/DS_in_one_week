# Chapter 3 — Linked List (MCQ)

> 14 questions. Choose ONE best answer (A–D). Answers in `ANSWER_KEY.md`.

---

**Q1.** In a **singly** linked list, which operation requires traversing the list to find the *previous* node before it can complete?
- A. `addFirst()`
- B. `removeFirst()`
- C. `removeLast()`
- D. `addLast()` (with a tail reference)

---

**Q2.** You are given **only a reference to a node** (not its previous node) in a **singly** linked list and asked to delete that node. Which of the following can you **NOT** do?
- A. Read the node's `next` reference
- B. Set the node's element to `null`
- C. Update the list's `size` field
- D. Truly delete (unlink) it without traversing from `head`

---

**Q3.** When inserting a new node in the **middle** of a **doubly** linked list, which pointers must be updated?
- A. Only the `next` pointer of the previous node
- B. Only the `prev` pointer of the next node
- C. Both the `next` and `prev` pointers of the adjacent nodes
- D. The `next` pointer of every node in the list

---

**Q4.** For an **LRU cache** where the *oldest* entry is removed from the **tail**, which structure is most efficient (combined with a hash map)?
- A. A singly linked list
- B. A doubly linked list with `head` and `tail`
- C. A fixed-size array
- D. A circular singly linked list

---

**Q5.** What is the time complexity of `removeLast()` in a **singly** linked list (with only `head` and `tail` references, no `prev` pointers)?
- A. O(1)
- B. O(log n)
- C. O(n)
- D. O(n²)

---

**Q6.** Which condition correctly identifies an **empty** linked list?
- A. `head == null && tail != null`
- B. `head == tail == null`
- C. `head.next == null`
- D. `tail.next == head`

---

**Q7.** Which statement about a **doubly** linked list compared to a singly linked list is **TRUE**?
- A. It uses less memory per node
- B. It can only be traversed forward
- C. It stores a `prev` pointer, so it needs no traversal to find the previous node
- D. Insertion always touches fewer pointers

---

**Q8.** In which linked list does the **last node's `next` point back to the first node**, with each node having only one pointer?
- A. Singly linked list
- B. Circular singly linked list
- C. Doubly linked list
- D. Circular doubly linked list

---

**Q9.** Which is the strongest advantage of an **array** over a linked list?
- A. O(1) random access by index
- B. O(1) insertion at an arbitrary middle index
- C. Dynamic growth with no copying
- D. No memory overhead for pointers and grows automatically

---

**Q10 (code trace).** A singly linked list holds `A -> B -> C` (head = A). The following runs:
```java
head = head.next;        // (1)
head.next = head.next.next;  // (2)
```
After both lines, printing the list from `head` gives:
- A. `A B C`
- B. `B C`
- C. `B`
- D. `A C`

---

**Q11 (code trace).** Given a singly list `1 -> 2 -> 3 -> null`, this reversal runs to completion:
```java
Node<Integer> prev = null, cur = head;
while (cur != null) {
    Node<Integer> nxt = cur.next;
    cur.next = prev;
    prev = cur;
    cur = nxt;
}
head = prev;
```
Printing from the new `head` gives:
- A. `1 2 3`
- B. `3 2 1`
- C. `2 1 3`
- D. `null` (empty list)

---

**Q12 (code trace).** A singly list is `10 -> 20 -> 30 -> 40` (head = 10). After:
```java
Node<Integer> p = head;
p = p.next;          // p at 20
p.next = p.next.next; // (rewire)
```
Traversing from `head` prints:
- A. `10 20 30 40`
- B. `10 20 40`
- C. `10 30 40`
- D. `20 30 40`

---

**Q13 (code trace).** Two new nodes are wired in a doubly list. `x` and `y` already exist as `... x <-> y ...`. A `newNode` is inserted between them:
```java
newNode.prev = y.prev;   // y.prev is x
newNode.next = y;
y.prev.next = newNode;   // x.next = newNode
y.prev = newNode;
```
After this, what is `x.next.element` and `y.prev.element`?
- A. Both equal `newNode`'s element
- B. `x.next` = y's element; `y.prev` = x's element
- C. `x.next` = null; `y.prev` = newNode
- D. The list becomes corrupted (cycle)

---

**Q14 (code trace).** A singly list `5 -> 6 -> 7` (head = 5, tail = 7). The student attempts `removeLast()` like this:
```java
Node<Integer> cur = head;
while (cur.next != tail) cur = cur.next; // stop at node BEFORE tail
cur.next = null;
tail = cur;
```
After running, traversing from `head` prints:
- A. `5 6 7`
- B. `5 6`
- C. `6 7`
- D. `5`
