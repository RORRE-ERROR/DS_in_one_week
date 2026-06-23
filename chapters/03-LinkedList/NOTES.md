# Chapter 3 — Linked List (NOTES)

> WIA1002 Data Structure. List ADT: stores data in **sequential order**. Two implementations: **array** and **linked list**.

## What is this chapter about? (read this first)

A **List** is just "a bunch of items kept in order." You can build that list in two very different ways:

- **Array** — like a row of **numbered lockers** bolted to a wall. Every locker is the same size, side by side. Because they're numbered, you can walk *straight* to locker #5 instantly. But adding a locker in the middle means shifting everything over.
- **Linked List** — like a **treasure hunt**. Each clue (we call it a **node**) holds one item AND a note telling you *where the next clue is*. There are no locker numbers. To reach the 5th clue you must follow clues 1 → 2 → 3 → 4 → 5 in order. But slipping a new clue into the middle is easy: you just rewrite two notes.

**Why does the linked list exist?** Because inserting/removing in the middle of an array is slow (you must shift everything). A linked list fixes that — to add a node you only rewrite a couple of "next" notes, no shifting. The trade-off: you lose instant jump-to-index access.

> **Jargon up front:**
> - **Node** = one box that holds your data + a pointer (a "link") to another node.
> - **Pointer / reference** = a note that says "the next item lives over there." In Java this is just a variable holding another object.
> - **`null`** = "points to nothing" — used to mark the end of the list.

---

## 1. Node structure

**What & why:** A node is the single building block of a linked list. It bundles together (a) your actual data and (b) the link(s) to its neighbour(s). Think of one clue in the treasure hunt: it holds an item, and it holds directions to the next clue.

Picture one singly node as a two-part box `[data | next]`:

```
 element   next
+--------+------+
|  "A"   |  ---------> (the next node, or null)
+--------+------+
```

### Singly linked node
```java
class Node<E> {
    E element;        // the data
    Node<E> next;     // reference to the NEXT node
    public Node(E o) { element = o; }
}
```

### Doubly linked node
A **doubly** node adds a *backward* note too, so each box knows both neighbours: `[prev | data | next]`.

```
   prev    element   next
 (-----)  +--------+ (-----)
 <-------- |  "A"   | -------->
          +--------+
```

```java
class Node<E> {
    E element;        // the data
    Node<E> next;     // forward pointer (next node)
    Node<E> prev;     // backward pointer (previous node)
    public Node(E o) { element = o; }
}
```

- **Singly** node = `element` + `next`.
- **Doubly** node = `element` + `next` + `prev`.

🧠 **Quick check:** What's the difference between a singly and doubly node? *Answer: the doubly node has an extra `prev` pointer to its previous node.*

---

## 2. head / tail

**What & why:** A treasure hunt needs a *starting* clue, otherwise you'd never know where to begin. `head` is that start. `tail` is a shortcut pointer to the very last node so you don't have to walk the whole list just to add at the end.

```
 head                                   tail
  |                                      |
  v                                      v
[A|next] -> [B|next] -> [C|next] -> [D|null]
```

- `head` → first node in the list.
- `tail` → last node in the list.
- **Empty list → `head == tail == null`.**
- The **last node's `next` is `null`** → this is how you detect the end of the list.

🧠 **Quick check:** How do you know you've reached the end while walking the list? *Answer: when the current node's `next` is `null`.*

---

## 3. Traversal loop (forward, both list types)

**What & why:** "Traversal" just means *walking the list from start to end, one node at a time*, like following the treasure hunt clue by clue. Because there are no index numbers, this loop is the ONLY way to visit nodes.

How it works: start a marker at `head`, do something with the current node, then hop to `current.next`. Stop when you fall off the end (`null`).

```
current
  |
  v
[A] -> [B] -> [C] -> null
       ^
       | (after current = current.next)
     current ... and so on until current == null
```

```java
Node<E> current = head;
while (current != null) {
    // visit current.element
    current = current.next;
}
```
A doubly linked list can also be traversed **backward** starting from `tail` and following `prev`.

---

## 4. Operation cost table (singly linked list)

**What & why:** "Cost" here means *how much work* an operation takes as the list grows. **O(1)** = constant, instant, doesn't care how big the list is. **O(n)** = grows with the list — if there are n nodes you may have to walk past all of them. The big lesson: anything that needs you to *find* a node first is O(n), because there's no jump-to-index.

> **How to read this:** the **Cost** column is the speed, the **Why** column tells you what work forces that cost.

| Operation | Cost | Why |
|---|---|---|
| `addFirst(e)` | **O(1)** | only touch `head` |
| `addLast(e)` (with tail ref) | **O(1)** | `tail` is known |
| `removeFirst()` | **O(1)** | only move `head` forward |
| `removeLast()` | **O(n)** | must traverse to find the node **before** tail (no `prev` pointer) |
| `get(index)` | **O(n)** | no random access — must traverse |
| `add(index, e)` / `remove(index)` | **O(n)** | must traverse to the position |

### Why is `removeLast()` O(n) on a singly list? (step by step)

To delete the last node, you must make the **second-to-last** node's `next` become `null`. But a singly node has no `prev` — so from the tail you can't step *backward* to reach that node. You have no choice but to walk all the way from `head`:

```
Before:  [A] -> [B] -> [C] -> [D] -> null
                              ^tail

Step 1: start at head, walk until current.next.next == null
        (i.e. find C, the node BEFORE tail)

         [A] -> [B] -> [C] -> [D] -> null
                        ^
                     found it (C is the node before tail)

Step 2: cut the link and move tail back
        C.next = null;   tail = C;

After:   [A] -> [B] -> [C] -> null
                        ^tail     ([D] is now unreferenced → garbage collected)
```

Because Step 1 may walk past every node, the cost is **O(n)**. (On a *doubly* list this is O(1) — see Section 7.)

🧠 **Quick check:** Why is `removeFirst()` cheap but `removeLast()` expensive on a singly list? *Answer: `removeFirst` just moves `head` forward (O(1)); `removeLast` has no back pointer, so you must traverse from head to find the node before tail (O(n)).*

---

## 5. Array vs Linked List

**What & why:** This is the heart of the chapter. Lockers (array) win when you need instant access by number. The treasure hunt (linked list) wins when you're constantly inserting/removing, because rewriting a couple of links beats shifting a whole row of lockers.

> **How to read this:** each row is one operation; compare the two columns to see which structure is faster for it.

| | Array | Linked List |
|---|---|---|
| Random access `get(i)`/`set(i)` | **O(1)** (direct/indexed) | **O(n)** (no random access — sequential only) |
| `add` at end | O(1) | O(1) (with tail ref) |
| `add(index)` / `remove(index)` | **O(n)** — must **shift** elements | O(n) to find spot, then O(1) relink |
| Insert/remove anywhere | slow (shifting) | **efficient** (just relink pointers; local operation) |
| Memory | contiguous, fixed-capacity (resize = copy) | nodes anywhere in memory, grows dynamically; pointer overhead |

**Rule:** index/random access fast → **array**. Insert/remove anywhere or at front → **linked list**.

🧠 **Quick check:** You need fast `get(index)` lookups by position — array or linked list? *Answer: array (O(1) random access).*

---

## 6. The four types of linked list

**What & why:** Once you understand a basic (singly) list, the other three are just small variations: add a back pointer (doubly), or loop the end back to the start (circular). "Circular" means the last node points back to the first instead of `null` — useful for things that cycle round and round.

```
Singly:           [A] -> [B] -> [C] -> null

Circular singly:  [A] -> [B] -> [C] --+
                   ^------------------+   (C.next loops back to A)

Doubly:    null <- [A] <=> [B] <=> [C] -> null

Circular doubly:   +--> [A] <=> [B] <=> [C] <--+
                   +---------------------------+   (wraps both ways)
```

| Type | Pointers | Key property |
|---|---|---|
| **Singly** | `next` only | forward only; not a direct-access structure (sequential) |
| **Circular singly** | `next` only | last node's `next` points back to the **first** node |
| **Doubly** | `next` + `prev` | can traverse **both** directions |
| **Circular doubly** | `next` + `prev` | `last.next → first` AND `first.prev → last` |

---

## 7. Doubly Linked List — advantages & disadvantages

**What & why:** A doubly list gives every node a backward note (`prev`), so you can walk in both directions and, crucially, jump *back* one node without re-walking from head. That's exactly what made `removeLast()` slow on a singly list — and here it becomes instant.

**Advantages**
- Each node stores its `prev`, so there is **no need to traverse to find the previous node** → efficient removal (e.g. `removeLast()` in **O(1)**).
- Can be traversed **forward and backward**.
- Insert/delete operations are **simpler** (no tracking the previous node during traversal).

**Disadvantages**
- **Extra `prev` pointer per node → more memory.**
- Insert/delete requires **more pointer updates** (a bit slower per operation).

🧠 **Quick check:** Why is `removeLast()` O(1) on a doubly list? *Answer: `tail.prev` gives the second-to-last node directly — no traversal needed.*

---

## 8. Middle insertion — pointer updates

**What & why:** This is where pointers really click. To slip a new node into the middle, you carefully **rewire the notes** so nothing gets lost. Do it in the right order, or you'll cut off the rest of the list! A singly list only has forward notes to fix; a doubly list has notes pointing *both* ways, so there's more to update.

### Singly linked list (insert before `temp`, with `prev` = node before)

We want to put `newNode` between `prev` and `temp`.

```
Before:   [prev] --------------> [temp] -> ...
                                  ^ (we insert newNode in front of temp)

Step 1: newNode.next = prev.next;   // newNode points to temp
        [prev] --------------> [temp] -> ...
            \                    ^
             \-> [newNode] ------/

Step 2: prev.next = newNode;        // prev now points to newNode
        [prev] -> [newNode] -> [temp] -> ...
```

```java
newNode.next = prev.next;   // newNode points to temp
prev.next = newNode;        // previous node points to newNode
```
Only the **single forward link** of the previous node changes.

### Doubly linked list (insert `newNode` before `temp`)

Now both neighbours have notes that must be fixed. We're inserting `newNode` between `temp.prev` (the node before) and `temp`.

```
Before:  ... <=> [P] <=> [temp] <=> ...        (P is temp.prev)

We want: ... <=> [P] <=> [newNode] <=> [temp] <=> ...

Step 1: newNode.prev = temp.prev;   // newNode looks back to P
Step 2: newNode.next = temp;        // newNode looks forward to temp
Step 3: temp.prev.next = newNode;   // P now points forward to newNode
Step 4: temp.prev = newNode;        // temp now looks back to newNode

After:   ... <=> [P] <=> [newNode] <=> [temp] <=> ...
```

A middle insert in a doubly list must update **BOTH the `next` and `prev` pointers of the adjacent nodes**:
```java
newNode.prev = temp.prev;   // newNode's back pointer -> node before temp
newNode.next = temp;        // newNode's forward pointer -> temp
temp.prev.next = newNode;   // previous node's NEXT -> newNode
temp.prev = newNode;        // temp's PREV -> newNode
```
That is 4 pointer assignments touching both neighbors.

🧠 **Quick check:** Singly middle insert changes how many forward links? *Answer: just one — `prev.next`. The doubly version needs 4 pointer updates because both neighbours point both ways.*

---

## ★ Exam Tips (real mid-term answers)

1. **Singly list — op that needs traversal to find the previous node = `removeLast()`** (no back pointer, so you must walk from `head` to the node before tail). **O(n).**
2. **Delete a node when you only have a reference to it (not its previous) in a singly list → you CANNOT delete it without traversing from head.** A singly list can't go backward to fix the previous node's `next`. (You *can* still read its `next`, null its element, update size — but not truly unlink it without the previous node.)
3. **Doubly list — inserting in the middle must update BOTH the `next` and `prev` pointers of the adjacent nodes.**
4. **LRU cache where the oldest is removed from the tail → most efficient = a doubly linked list with head & tail** (O(1) removal from the tail, O(1) move-to-front; pair with a HashMap for O(1) lookup).
5. **Cost recap:** `addFirst`/`removeFirst` = O(1); `addLast` (tail ref) = O(1); `removeLast` (singly) = **O(n)**; array random access = O(1) but insert/remove = O(n) (shifting); linked list = **no random access** (sequential).
6. **Empty list → `head == tail == null`.** Last node's `next == null`.
7. **Four types:** singly, circular singly, doubly, circular doubly.

> MCQ watch-words: *cannot / except / always / never / only* flip the answer. For "which is most efficient" match the operation to the structure's strength.
