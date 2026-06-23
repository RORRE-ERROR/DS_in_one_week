# Chapter 5 — Queue & Priority Queue (NOTES)

> Exam = all MCQ: concept definitions, code-output/trace, "which is most efficient", method behavior (poll vs peek), FIFO vs priority ordering. Watch negative words: *not / except / cannot / always*.

---

## What is this chapter about? (read me first)

A **queue** is a way of storing things in a **line**, where the rule is simple: **the first one to arrive is the first one to leave.** That rule has a name — **FIFO** (First-In, First-Out).

> **Real-world analogy:** A queue is exactly like the **line at a shop checkout** or the **queue at a bank**. You join at the **back**, you get served at the **front**, and nobody can jump the line. First come, first served.

Later in the chapter we meet a special cousin, the **priority queue**, where the rule changes: instead of "oldest leaves first", it's **"most important leaves first"** — like a **hospital emergency room**, where the most urgent patient is seen first no matter who walked in earlier.

By the end you should be able to:
- Explain FIFO and trace enqueue/dequeue by hand.
- Know which methods *remove* vs which only *look* (poll vs peek).
- Say why a queue prefers a **LinkedList** but a stack prefers an **ArrayList**.
- Tell apart **Queue (FIFO)** vs **Stack (LIFO)** vs **Priority Queue (by priority)**.

---

## 1. The Queue concept — FIFO

**What & why:** A queue keeps elements in **arrival order** and always lets the *oldest* one out first. We use it whenever order of arrival matters and must be respected — print jobs, customer requests, breadth-first search, etc.

- A **Queue = FIFO (First-In, First-Out)**. Think of a **waiting line** at a bank: the first person to join is the first served.
- A queue is a **special kind of list** where:
  - Elements are **inserted at the rear** (also called **back / tail**) — *this is "enqueue".*
  - Elements are **accessed and removed from the front** (also called **head / beginning**) — *this is "dequeue".*
- The element removed is always the **first one that was added**.

> **Jargon check:** *enqueue* = add to the back. *dequeue* = remove from the front. *front/head* = the next one to leave. *rear/back/tail* = where new ones join.

```
        enqueue (add at rear)
                 |
   front  [ A ][ B ][ C ][ D ]  rear
     |
   dequeue (remove from front)  ->  A leaves first
```

For `A, B, C, D` enqueued in that order: **A is first, D is last**. Dequeue order = `A, B, C, D`.

### Step-by-step trace (watch the line grow and shrink)

```
start:        front [            ] rear      (empty)
enqueue(A):   front [ A          ] rear
enqueue(B):   front [ A  B       ] rear
enqueue(C):   front [ A  B  C    ] rear
dequeue() -> A   front [ B  C       ] rear   (A was first in, so first out)
dequeue() -> B   front [ C          ] rear
peek()    -> C   front [ C          ] rear   (C is just LOOKED at, still there)
```

🧠 **Quick check:** You enqueue 5, 6, 7. Then dequeue once. What comes out, and what is now at the front?
*Answer: 5 comes out (oldest). 6 is now at the front.*

---

## 2. Core operations

**What & why:** These are the handful of methods every queue gives you. The most important distinction is **"does it remove?"** — `dequeue/poll` removes, `peek` does not.

**How to read this:** the left column lists the different *names* (synonyms) you may see on the exam for the same action.

| Method (synonyms)              | Action                                  | Where  |
|--------------------------------|-----------------------------------------|--------|
| `enqueue` / `offer` / `add`    | insert an element                       | rear   |
| `dequeue` / `poll`             | remove **and return** an element        | front  |
| `peek`                         | view front element **without removing** | front  |
| `isEmpty`                      | test whether the queue is empty         | —      |
| `size`                         | number of elements                      | —      |

- **`enqueue` and `dequeue` are the two essential queue operations.**
- **`peek` vs `dequeue/poll`:** peek only *looks* at the front; dequeue/poll *retrieves and removes* it.

> Think of peek as **glancing at the front of the line** without sending that person away.

---

## 3. Why a LinkedList implementation? (★ frequent MCQ)

**What & why:** A queue removes from the **front** a lot. The data structure you build it on top of decides whether each removal is cheap or expensive. This is a favourite exam question, so understand *why* — don't just memorise.

> **A queue is best implemented with a LinkedList, not an array.**

- Deletions happen at the **front/beginning** of the queue.
- With an **array/ArrayList**, removing the front element forces every remaining element to **shift down one slot → O(n)** per dequeue.

  ```
  array remove front:
  [ A ][ B ][ C ][ D ]
    ^ remove A, then EVERYTHING shifts left:
  [ B ][ C ][ D ][   ]      <- O(n) work every time. Slow.
  ```

- With a **LinkedList**, removing the front is just a pointer relink → **O(1)**, and `addLast` (with a tail reference) is **O(1)** too.

  ```
  linked list remove front:
  head -> (A) -> (B) -> (C)
          just move head to B:
  head -------> (B) -> (C)   <- O(1). Fast, no shifting.
  ```

Two ways to **design** the queue class on top of `LinkedList`:
- **Inheritance:** `class GenericQueue<E> extends LinkedList<E>` — exposes *all* LinkedList methods (leaky: users could call list methods that break queue rules).
- **Composition (preferred):** keep a `LinkedList<E>` as a private **field** — exposes only the queue operations you choose.

> **Plain English:** *Inheritance* says "a queue IS a linked list" (and accidentally inherits every list trick). *Composition* says "a queue HAS a linked list inside it" and only shows the doors you choose. Composition is safer.

### Composition-based generic queue

```java
import java.util.LinkedList;

public class GenericQueue<E> {
    private LinkedList<E> list = new LinkedList<>();   // composition: LinkedList as a field

    public void enqueue(E e) { list.addLast(e); }      // insert at REAR  -> O(1)
    public E dequeue()       { return list.removeFirst(); } // remove FRONT -> O(1)
    public E peek()          { return list.getFirst(); }    // view front, no remove
    public boolean isEmpty() { return list.isEmpty(); }
    public int size()        { return list.size(); }

    @Override
    public String toString() { return "Queue: " + list.toString(); }
}
```

🧠 **Quick check:** Why not just use an ArrayList for a queue?
*Answer: removing from the front of an ArrayList shifts every other element down — O(n) per dequeue. A LinkedList front removal is O(1).*

---

## 4. Priority Queue (★)

**What & why:** Sometimes "first come first served" is the *wrong* rule. In a hospital ER, a patient with a heart attack must be seen before someone with a sprained finger, even if the finger arrived earlier. A **priority queue** lets the **most important element leave first**, regardless of arrival order.

> **Analogy to lock in:**
> - **Normal queue** = checkout line. Order out = order in (FIFO).
> - **Priority queue** = hospital ER. The most urgent patient goes first, no matter who arrived first.

- In a **priority queue**, every element has a **priority**. When you remove, the element with the **HIGHEST priority comes out first — NOT the oldest**. So it is **not FIFO**.
- Default behavior is described as **"largest-in, first-out"**, BUT you must **double-check whether larger = higher priority, or smaller = higher priority** for the given problem.
  - Example — hospital **emergency room**: each patient gets a priority number, and the assumption is **the higher the number, the higher the priority**, so the highest-number patient is treated first.
- A priority queue is a collection in which **all elements have a comparison (priority) ordering**; a deletion **always removes the element of highest priority**.

### Why a priority queue often uses a heap (slow walk-through)

To always hand back the highest-priority element fast, the priority queue needs to *find the best element quickly* — even as items are added and removed.

- If it kept everything fully sorted, every insert would cost a lot of shifting.
- Instead it uses a **heap**: a tree-shaped structure that doesn't fully sort everything, but always keeps the **best element at the top**.
- That gives **O(log n)** insert and remove, and **O(1)** to peek at the top — a good balance. (You don't need heap internals here, just the idea: *heap = cheap access to the best element.*)

### Java `java.util.PriorityQueue`

- `import java.util.PriorityQueue;`
- It **implements `java.util.Queue<E>`**.
- **By default it is a MIN-heap:** `poll()`/`peek()` return the **smallest** element (natural ordering). Pass a `Comparator` (e.g. `Collections.reverseOrder()`) for a max-heap.

> **Watch out (common trap):** Java's default `PriorityQueue` is a **MIN**-heap — the **smallest** value comes out first, not the largest. If a question expects "largest first", a `Comparator` must have been supplied.

| Method            | Returns   | Behavior                                                |
|-------------------|-----------|---------------------------------------------------------|
| `offer(E e)`      | `boolean` | add an element                                          |
| `poll()`          | `E`       | retrieve **AND remove** the head (highest priority)     |
| `peek()`          | `E`       | retrieve, but **do NOT remove** the head                |
| `remove(Object o)`| `boolean` | remove a specific element                               |
| `clear()`         | `void`    | remove all elements                                     |
| `size()`          | `int`     | number of elements                                      |
| `contains(Object o)` | `boolean` | whether `o` is present                              |

```java
import java.util.PriorityQueue;

PriorityQueue<Integer> pq = new PriorityQueue<>();  // min-heap by default
pq.offer(30);
pq.offer(10);
pq.offer(20);

pq.peek();   // 10  -> smallest, NOT removed
pq.poll();   // 10  -> retrieved AND removed
pq.poll();   // 20
pq.poll();   // 30
// poll order: 10, 20, 30   (smallest priority value out first)
```

Notice the elements went **in** as 30, 10, 20 but come **out** as 10, 20, 30 — proof that a priority queue ignores arrival order and obeys priority (here, smallest-first).

🧠 **Quick check:** You `offer(5)`, `offer(1)`, `offer(9)` into a default Java `PriorityQueue`. What does the first `poll()` return?
*Answer: 1 — the smallest, because the default is a min-heap.*

---

## 5. Stack vs Queue (★ comparison table)

**What & why:** These two look similar but follow opposite rules. Don't mix them up.

- **Queue = FIFO** (first-in-first-out) → checkout line.
- **Stack = LIFO** (last-in-first-out) → a **stack of plates**: you add to the top and take from the top, so the *last* plate you put on is the *first* you take off.
- **Priority Queue = by priority** → ER, most urgent first.

**How to read this:** compare the same row across both columns to see how each rule changes the operations.

| Aspect              | **Stack**                       | **Queue**                          |
|---------------------|---------------------------------|------------------------------------|
| Ordering            | **LIFO** (Last-In, First-Out)   | **FIFO** (First-In, First-Out)     |
| Insert operation    | `push` (at **top**)             | `enqueue`/`offer` (at **rear**)    |
| Remove operation    | `pop` (from **top**)            | `dequeue`/`poll` (from **front**)  |
| View without remove | `peek` (top)                    | `peek` (front)                     |
| Access point(s)     | **one end** (top)               | **two ends** (rear in, front out)  |
| Best implementation | **ArrayList** (add/remove at end → O(1), no shifting) | **LinkedList** (remove at front → O(1), array would shift) |
| Insert / remove cost| O(1)                            | O(1)                               |

> **One-line memory hook:** Stack uses **one end** (top in, top out → LIFO). Queue uses **two ends** (rear in, front out → FIFO).

---

## ★ Exam Tips

- **Queue = FIFO.** Insert at **rear** (enqueue/offer/add), remove/access at **front** (dequeue/poll/peek).
- **Best queue implementation = LinkedList** (array would **shift all elements O(n)** on front removal). **Stack = ArrayList.**
- A **priority queue removes the HIGHEST priority first, NOT the oldest** → it is **not** FIFO. Default = "largest-in-first-out", but **check if larger or smaller means higher priority** (ER: higher number = higher priority).
- Java `PriorityQueue` **implements `Queue<E>`** and is a **MIN-heap by default** (smallest out first).
- **`poll()` removes**, **`peek()` does not.** Both retrieve the head; only `poll` deletes it.
- `offer` returns `boolean`; `poll` and `peek` return `E` (the element type).
- For "which DS to implement a queue" → **LinkedList**. For "which to implement a stack" → **ArrayList**.
