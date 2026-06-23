# Chapter 3 — Linked List (ANSWER KEY)

**Q1 — C. `removeLast()`.** In a singly list each node only has a `next` pointer. To delete the last node you must set the `next` of the node *before* tail to `null`, but you cannot reach that node directly — you must traverse from `head`. (`addFirst`/`removeFirst` = O(1); `addLast` with a tail ref = O(1).)

**Q2 — D. Truly delete (unlink) it without traversing from `head`.** A singly list cannot move backward to fix the previous node's `next`, so unlinking requires walking from `head`. You *can* still read `next`, null the element, and update `size`.

**Q3 — C. Both the `next` and `prev` pointers of the adjacent nodes.** A middle insert in a doubly list rewires four pointers: `newNode.prev`, `newNode.next`, the previous node's `next`, and the next node's `prev`.

**Q4 — B. A doubly linked list with `head` and `tail`.** O(1) removal from the tail (oldest) and O(1) move-to-front for recently used nodes; pair it with a HashMap for O(1) key lookup. This is exactly the LeetCode 146 design.

**Q5 — C. O(n).** Must traverse from `head` to find the node before tail (no `prev` pointer in a singly list).

**Q6 — B. `head == tail == null`.** Both references are null when the list holds no nodes.

**Q7 — C. It stores a `prev` pointer, so it needs no traversal to find the previous node.** This is the doubly list's main advantage. (A is false — it uses *more* memory; B/D are false — it traverses both ways and touches *more* pointers.)

**Q8 — B. Circular singly linked list.** One pointer per node, and the last node's `next` points back to the first node.

**Q9 — A. O(1) random access by index.** Arrays support direct indexed access. Middle insertion in an array is O(n) (shifting), so B is false; growth requires copying, so C/D are false.

**Q10 — C. `B`.** Initial `A -> B -> C`, `head = A`.
- Line (1) `head = head.next` → `head` now = **B**. Reachable from head: `B -> C`.
- Line (2) `head.next = head.next.next` → B's `next` was C, and C's `next` is `null`, so B's `next` becomes `null`. Reachable from head: just `B`.
Printing from `head` gives **`B`** → option C.

**Q11 — B. `3 2 1`.** Standard iterative reversal: `prev` walks the list flipping each `next` backward; final `head = prev` points at the old tail (3). Result `3 -> 2 -> 1`.

**Q12 — B. `10 20 40`.** `p` starts at 10, then `p = p.next` moves it to 20. `p.next = p.next.next` sets 20's `next` from 30 to 40, unlinking node 30. `head` is unchanged (still 10), and 10's `next` is still 20. Traversing from `head`: `10 -> 20 -> 40`.

**Q13 — A. Both equal `newNode`'s element.** After the rewiring, `x.next` points to `newNode` and `y.prev` points to `newNode`, so both `x.next.element` and `y.prev.element` are `newNode`'s element. The list is `x <-> newNode <-> y` (no cycle).

**Q14 — B. `5 6`.** The loop stops `cur` at node 6 (the node whose `next` is tail 7). Setting `cur.next = null` and `tail = cur` drops node 7. Result: `5 -> 6`. This illustrates why singly `removeLast()` is O(n).
