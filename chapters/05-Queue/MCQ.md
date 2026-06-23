# Chapter 5 — Queue & Priority Queue (MCQ)

> 12 questions. Choose ONE answer (A–D). Answers + explanations in `ANSWER_KEY.md`.

---

**Q1.** A queue follows which ordering?
- A. LIFO (Last-In, First-Out)
- B. FIFO (First-In, First-Out)
- C. Highest-priority-first
- D. Random access by index

---

**Q2.** In a queue, elements are inserted at the ____ and removed from the ____.
- A. front; rear
- B. top; top
- C. rear; front
- D. middle; front

---

**Q3.** The following operations are performed on an empty queue:
`enqueue(1); enqueue(2); enqueue(3); dequeue(); enqueue(4); dequeue();`
Which element is now at the **front** of the queue?
- A. 1
- B. 2
- C. 3
- D. 4

---

**Q4.** Which data structure is **most efficient** to implement a queue, and why?
- A. ArrayList, because adding at the end is O(1)
- B. LinkedList, because removing from the front avoids shifting all elements
- C. Array, because it gives O(1) random access by index
- D. Binary search tree, because operations are O(log n)

---

**Q5.** Why is an **array** a poor choice for implementing a queue?
- A. Arrays cannot store objects
- B. Removing from the front requires shifting all remaining elements → O(n)
- C. Arrays do not allow duplicate elements
- D. Arrays have no fixed size

---

**Q6.** What is the **difference** between `poll()` and `peek()` on a Java queue/priority queue?
- A. They are identical
- B. `poll()` retrieves and removes the head; `peek()` retrieves but does not remove it
- C. `peek()` removes the head; `poll()` only views it
- D. `poll()` adds an element; `peek()` removes one

---

**Q7.** In a **priority queue**, which element is removed first?
- A. The element that was inserted first (oldest)
- B. The element that was inserted last (newest)
- C. The element with the highest priority
- D. A randomly chosen element

---

**Q8.** A Java `PriorityQueue<Integer>` is created with the **default** (natural) ordering. The following are added:
`offer(50); offer(20); offer(40); offer(10);`
What is the result of three successive `poll()` calls, in order?
- A. 50, 40, 20
- B. 10, 20, 40
- C. 50, 20, 40
- D. 10, 40, 50

---

**Q9.** A hospital emergency room uses a priority queue where **the higher the number, the higher the priority**. Patients arrive with priorities `3, 7, 1, 5`. Which patient is treated **first**?
- A. The patient with priority 1
- B. The patient with priority 3 (arrived first)
- C. The patient with priority 7
- D. The patient with priority 5

---

**Q10.** Which statement about Java's `java.util.PriorityQueue` is **TRUE**?
- A. It implements the `Queue<E>` interface
- B. It always returns elements in FIFO order
- C. `peek()` returns `void`
- D. `offer(E e)` returns the element added

---

**Q11.** Which pairing of structure → ordering → best implementation is **correct**?
- A. Stack → FIFO → LinkedList
- B. Queue → LIFO → ArrayList
- C. Queue → FIFO → LinkedList
- D. Stack → FIFO → ArrayList

---

**Q12.** When designing a `GenericQueue<E>` class using a `LinkedList`, which design uses **composition** (the preferred approach)?
- A. `class GenericQueue<E> extends LinkedList<E>`
- B. `class GenericQueue<E> implements LinkedList<E>`
- C. `class GenericQueue<E>` with a `private LinkedList<E>` field used internally
- D. `class GenericQueue<E>` that copies all LinkedList code by hand
