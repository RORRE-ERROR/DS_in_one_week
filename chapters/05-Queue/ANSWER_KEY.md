# Chapter 5 — Queue & Priority Queue (ANSWER KEY)

---

**Q1 → B.** A queue is **FIFO** (First-In, First-Out): the first element added is the first removed. LIFO is a stack; highest-priority-first is a priority queue.

**Q2 → C.** Elements are **inserted at the rear** (back/tail) via enqueue, and **removed/accessed from the front** (head/beginning) via dequeue/peek.

**Q3 → C (front element is 3).** Trace (front … rear):
- `enqueue(1)` → `[1]`
- `enqueue(2)` → `[1, 2]`
- `enqueue(3)` → `[1, 2, 3]`
- `dequeue()` removes front → removes **1** → `[2, 3]`
- `enqueue(4)` → `[2, 3, 4]`
- `dequeue()` removes front → removes **2** → `[3, 4]`
Front is now **3**.

**Q4 → B.** A **LinkedList** is most efficient: queue deletions happen at the **front**, and a linked list removes the front in **O(1)** by relinking a pointer. (A is true for a *stack*, not the deciding factor for a queue; C/D are irrelevant to FIFO access.)

**Q5 → B.** With an array, removing the **front** element forces every remaining element to **shift down one position → O(n)** per dequeue. That is exactly why a LinkedList is preferred.

**Q6 → B.** `poll()` **retrieves AND removes** the head; `peek()` **retrieves but does NOT remove** it. Both return the element type `E` (or `null` if empty).

**Q7 → C.** A priority queue removes the element with the **highest priority** first — **not** the oldest (that would be FIFO) and not the newest.

**Q8 → B (10, 20, 40).** A Java `PriorityQueue` with default natural ordering is a **MIN-heap**, so the **smallest** value polls out first. After adding 50, 20, 40, 10, successive polls give **10, then 20, then 40** (50 remains).

**Q9 → C (priority 7).** The rule states **higher number = higher priority**, so the patient with priority **7** has the highest priority and is treated first. Arrival order is irrelevant in a priority queue.

**Q10 → A.** `java.util.PriorityQueue` **implements `Queue<E>`**. It does NOT return elements in FIFO order (B false); `peek()` returns `E`, not `void` (C false); `offer(E e)` returns a `boolean`, not the element (D false).

**Q11 → C.** **Queue → FIFO → LinkedList** is the correct triple. Stack is LIFO and best implemented with an ArrayList; the other options mismatch ordering or implementation.

**Q12 → C.** **Composition** = holding a `LinkedList<E>` as a **private field** and delegating to it (exposes only chosen queue methods). Option A (`extends`) is the **inheritance** design (leaks all LinkedList methods); B is invalid (LinkedList is a class, not an interface to implement); D is needless duplication.
