# 🧾 DS Final — One-Page Cram Sheet (WIA1002, Java)

> Day-before review. Every line is a most-tested fact. Read top to bottom once, then quiz yourself.

---

## ⚡ Master Complexity Table (memorize this)

| Structure / Algorithm | Access/Search | Insert | Delete | Notes |
|---|---|---|---|---|
| **Array** | O(1) random access | O(n) (shift) | O(n) (shift) | contiguous, fixed capacity |
| **Linked List** | **O(n)** (no random access) | O(1) at front/end* | O(1) once located | *addLast O(1) with tail ref |
| **Stack** (ArrayList) | O(1) top only | O(1) push | O(1) pop | LIFO |
| **Queue** (LinkedList) | O(1) front | O(1) enqueue | O(1) dequeue | FIFO |
| **BST (balanced)** | O(log n) | O(log n) | O(log n) | O(h); **skewed → O(n)** |
| **Linear search** | O(n) | — | — | no precondition; returns **-1** |
| **Binary search** | **O(log n)** | — | — | **needs SORTED array** |
| Selection / Insertion / Bubble sort | — | — | — | **O(n²)** |
| **Merge sort** | — | — | — | **O(n log n), STABLE** |

**Big-O speed (fast → slow):** `O(1) < O(log n) < O(n) < O(n log n) < O(n²)`

---

## 0️⃣ OOP Fundamentals
- **4 pillars = "A PIE":** Abstraction, Polymorphism, Inheritance, Encapsulation.
- **Overriding** = same name + **same** params, in a subclass (`extends`), resolved at **runtime**. *(Animal/Dog `sound()` → overriding.)*
- **Overloading** = same name + **different** params, **compile-time**. (Multiple constructors = constructor overloading.)
- **Encapsulation** = `private` fields + public getters/setters. Visibility: `private` < default < `protected` < `public`.
- `static` = belongs to class (no `this`, can't touch instance fields). `final` = unchangeable (var/method/class).
- **String `+`:** once a String appears, rest is **concatenation**; numbers *before* it compute first. `"Pi"+3.14`→`"Pi3.14"`; `1+2+" "`→`"3 "`; `'A'+1`→`66` (no String → char promotes to int).
- **Integer `/` truncates:** `5/2 == 2`. Make one operand `double` for real division.
- Account interest: rate **/100** (percent) **/12** (monthly); `getMonthlyInterest()` = `balance × rate`.

## 1️⃣ Generics
- Purpose: **compile-time type checking + no casting**. Raw types & type erasure exist for **backward compatibility**.
- **Type erasure:** unbounded `<T>` → `Object`; bounded `<T extends X>` → `X`. **Only ONE class** in the JVM regardless of type arg.
- **Cannot:** `new E()`, `new E[]`, generic class extending `Throwable`, use class type-param in a `static` context.
- Type arg must be a **reference type** (no `ArrayList<int>`).
- Wildcards: `?` any · `? extends T` (upper bound / subtype) · `? super T` (lower bound / supertype). `<?>` ≡ `<? extends Object>`.
- Generic method: **`<E>` goes before the return type**; bounds use `extends`.

## 2️⃣ ADT & Bag
- **ADT = WHAT (the contract); Data Structure = HOW (the implementation).** "Independent of language / doesn't say how stored" → **ADT**.
- Key benefit of ADT = **information hiding** (vending-machine analogy).
- **Bag** = unordered + **duplicates allowed** + no size limit.
- A class with `implements` **must define all** interface methods.
- `remove()` → returns **T** (item); `remove(entry)` → returns **boolean**.
- ArrayBag & LinkedBag = same interface, different implementation.

## 3️⃣ Linked List
- Node = `element` + `next` (singly); + `prev` (doubly). **Empty → head == tail == null**; last node's `next == null`.
- Costs (singly): `addFirst`/`removeFirst`/`addLast`(tail) = **O(1)**; **`removeLast` = O(n)** (no back pointer); `get(i)` = O(n).
- **Array vs Linked:** random access → **array** (O(1)); insert/remove anywhere or at front → **linked list**.
- **4 types:** singly, circular singly, doubly, circular doubly.
- **Doubly:** O(1) `removeLast`, traverse both ways, but extra `prev` = more memory + more pointer updates. **Middle insert updates BOTH `next` and `prev`** of neighbors (4 assignments).
- **LRU cache** = doubly linked list (head+tail) **+ HashMap** → O(1) move-to-front & remove-from-tail.

## 4️⃣ Stack — **LIFO**
- All access at the **`top`**. `pop` removes; `peek` does not. Empty `pop`/`peek` → **`EmptyStackException`**.
- **Best implementation = `ArrayList`** (add/remove at end = O(1)). Prefer **composition** over inheritance.
- **Postfix eval:** for `-`/`/`, result = `second_popped OP first_popped` (`y OP x`, x = right operand). `6 2 3 + -` → **1**; `4 3 5 * +` → **19**.
- Postfix wins: no precedence, no parentheses. Uses: balanced symbols, expression eval, undo, the call stack.

## 5️⃣ Queue & Priority Queue — **FIFO**
- Insert at **rear** (enqueue/offer); remove/peek at **front** (dequeue/poll).
- **Best implementation = LinkedList** (array would shift O(n) on front removal). *(Stack = ArrayList; Queue = LinkedList.)*
- `poll()` removes; `peek()` doesn't. `offer` → `boolean`; `poll`/`peek` → `E`.
- **Priority queue removes HIGHEST priority first — NOT oldest, NOT FIFO.** Java `PriorityQueue` implements `Queue<E>` and is a **MIN-heap by default** (smallest out first). Check whether larger or smaller = higher priority.

## 6️⃣ Graph
- **G = (V, E).** Directed = one-way edges; weighted = edges carry a value. Adjacent = share an edge.
- **Adjacency matrix** = O(n²) space → **DENSE** graphs. **Adjacency list** = O(V+E) → **SPARSE** graphs. *(Dense→Matrix, Sparse→List.)*
- **DFS = STACK = goes DEEP. BFS = QUEUE = level-by-level.** ← single most-tested fact.
- **BFS finds fewest-EDGES path** (unweighted shortest path), **NOT** least weight.
- DFS connectivity test: `#visited == #vertices` → connected. Both DFS & BFS build a **spanning tree**. **BFS uses more memory.**

## 7️⃣ Recursion
- Every recursive method needs **base case** (stop) + **recursive case** (smaller subproblem moving toward base).
- **No / unreachable base case → `StackOverflowError`** (it crashes — not an infinite loop, not a wrong answer).
- Each call = a new **stack frame** (extra memory); iteration uses none. Trace: calls expand **down**, results combine **up**.
- Fibonacci: `0 1 1 2 3 5 8 13 21 34 55`; `fib(7)=13`. Best for trees, directory size, H-trees.

## 8️⃣ Searching & Sorting
- **Linear search:** O(n), no precondition, returns **-1** if absent. **Binary search:** O(log n), **requires sorted array**.
- Simple sorts (selection, insertion, bubble) = **O(n²)**. **Merge sort = O(n log n), STABLE.**
- Identify by description: smallest→front = **Selection**; insert into sorted sublist = **Insertion**; swap **adjacent** = **Bubble**; split/recurse/**merge** = **Merge**.
- Bubble optimization = **boolean flag** (no swaps in a pass → stop). Insertion sort great on **nearly-sorted/small**. After bubble pass 1, **largest is at the end**.

## 9️⃣ Binary Search Tree
- **BST property** at EVERY node: left subtree < node < right subtree; **no duplicates**.
- **Inorder (L, Node, R) of a BST = SORTED ASCENDING** ← most-loved MCQ.
- **Preorder = root FIRST** (N,L,R); **Postorder = root LAST** (L,R,N). **Level-order = QUEUE.**
- search/insert/delete = **O(h)**: O(log n) balanced, **O(n) skewed**. Depth of root = 0; height = edges on longest root-to-leaf path; leaf = no children.
- **Delete:** no left child → link parent to right child; has left child → replace with **rightmost node of left subtree**.

---

## 🚨 MCQ Trap Watch-Words
**cannot · except · not · always · never · only · most efficient** — these flip the answer. For "which is most **efficient**", match the operation to the structure's strength:

| If they ask... | Answer |
|---|---|
| Fast random access by index | **Array** |
| Fast insert/remove at front | **Linked list** |
| Implement a **stack** | **ArrayList** |
| Implement a **queue** | **LinkedList** |
| LRU cache | **Doubly linked list + HashMap** |
| Search a **sorted** array | **Binary search (O(log n))** |
| Graph: go **deep** | **DFS (stack)** |
| Graph: **fewest edges** path | **BFS (queue)** |
| BST values in sorted order | **Inorder traversal** |

**Good luck! 🍀** — if you only memorize three things: *Big-O ranking · DFS=Stack/BFS=Queue · Inorder of BST = sorted.*
