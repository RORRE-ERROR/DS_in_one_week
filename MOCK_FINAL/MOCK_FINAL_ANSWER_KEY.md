# Mock Final — Answer Key & Explanations

Score yourself: **/100**. Any chapter where you miss >2 → reread that chapter's `NOTES.md`.

## Quick answer grid

| Q | A | Q | A | Q | A | Q | A | Q | A |
|---|---|---|---|---|---|---|---|---|---|
| 1 | C | 21 | B | 41 | B | 61 | C | 81 | C |
| 2 | A | 22 | A | 42 | C | 62 | B | 82 | D |
| 3 | C | 23 | C | 43 | A | 63 | A | 83 | B |
| 4 | B | 24 | D | 44 | B | 64 | C | 84 | B |
| 5 | A | 25 | A | 45 | C | 65 | C | 85 | B |
| 6 | C | 26 | B | 46 | D | 66 | D | 86 | C |
| 7 | D | 27 | C | 47 | B | 67 | B | 87 | B |
| 8 | A | 28 | D | 48 | B | 68 | C | 88 | C |
| 9 | B | 29 | A | 49 | B | 69 | B | 89 | A |
| 10 | C | 30 | B | 50 | A | 70 | D | 90 | D |
| 11 | A | 31 | C | 51 | C | 71 | B | 91 | A |
| 12 | D | 32 | D | 52 | C | 72 | B | 92 | B |
| 13 | A | 33 | A | 53 | A | 73 | A | 93 | C |
| 14 | C | 34 | D | 54 | D | 74 | C | 94 | C |
| 15 | A | 35 | B | 55 | B | 75 | A | 95 | B |
| 16 | B | 36 | C | 56 | C | 76 | C | 96 | A |
| 17 | C | 37 | A | 57 | B | 77 | B | 97 | C |
| 18 | D | 38 | B | 58 | D | 78 | C | 98 | B |
| 19 | A | 39 | C | 59 | A | 79 | D | 99 | B |
| 20 | D | 40 | D | 60 | C | 80 | B | 100 | C |

---

## Section A — Code Reading (Q1–Q35)

1. **C** — `removeLast()` calls `unlink(tail.prev)`; the sentinel gives O(1) access, no traversal.
2. **A** — Sentinels guarantee every real node has non-null `prev`/`next`, so add/remove need no first/last special cases.
3. **C** — addLast 1,2 → `[1,2]`; addFirst 0 → `[0,1,2]`.
4. **B** — `removeFirst()` throws `NoSuchElementException` when empty.
5. **A** — `get(1)` makes 1 most-recent; `put(3,3)` evicts the LRU (key 2); `get(2)` → `-1`.
6. **C** — key 2 was least-recently-used, so it's evicted.
7. **D** — HashMap (O(1) lookup) + doubly linked list (usage order).
8. **A** — `tail.prev` is the least-recently-used victim.
9. **B** — missing key → returns `-1`.
10. **C** — "app" was never marked `isWord`, so `search` is `false` (only `startsWith` would be true).
11. **A** — "apple" was inserted, so the prefix "app" exists → `true`.
12. **D** — O(L), proportional to word length, independent of stored-word count.
13. **A** — a min-heap pops the smallest each time → `1 2 3 5 8 9`.
14. **C** — `peek` reads index 0 (O(1)); `offer` sifts up (O(log n)).
15. **A** — parent of `i` is `(i-1)/2`; children are `2i+1`, `2i+2`.
16. **B** — {0,1,2} and {3,4} merge into 2 sets (started at 5, three successful unions → 5−3=2).
17. **C** — 0 and 4 are in different sets → `false`.
18. **D** — path compression (in `find`) + union by rank (in `union`).
19. **A** — DFS from 0 with sorted neighbours → `[0,1,3,2,4]`.
20. **D** — BFS visits level by level → `[0,1,2,3,4]`.
21. **B** — `dfs` uses an explicit `Deque` as a **stack** (`push`/`pop`).
22. **A** — `bfs` uses a `Deque` as a **queue** (`offer`/`poll`).
23. **C** — connected iff `dfs(start).size()` equals the vertex count.
24. **D** — inorder of a BST = ascending sorted: `[45,55,57,60,67,100,107]`.
25. **A** — preorder (Node,Left,Right): `[60,55,45,57,100,67,107]`.
26. **B** — postorder (Left,Right,Node): `[45,57,55,67,107,100,60]`.
27. **C** — duplicate insert returns `false`, tree unchanged.
28. **D** — sorted insertion → skewed tree → search is O(n).
29. **A** — LIFO: `pop` returns `C`, `peek` then sees `B`.
30. **B** — FIFO: `dequeue` returns the first enqueued, `A`.
31. **C** — stack works at the list end (O(1)); a queue removes from the front, O(1) on a linked list but O(n) on an array.
32. **D** — `2+3=5`, then `6−5=1`.
33. **A** — the first value popped is the RIGHT operand (so it computes `left OP right`).
34. **D** — 7 is absent → this `binarySearch` returns `-1`.
35. **B** — mergeSort is O(n log n); selection/insertion/bubble are O(n²).

## Section B — Concepts (Q36–Q100)

36. **C** — same signature redefined in a subclass = overriding.
37. **A** — same name, different parameter lists = overloading.
38. **B** — string + double concatenates left-to-right → `Pi3.14`.
39. **C** — integer division truncates: `7/2 = 3`.
40. **D** — private fields + getters/setters = encapsulation.
41. **B** — a no-arg constructor sets defaults and takes no parameters (it can coexist with others; constructors return nothing).
42. **C** — erasure exists for backward compatibility with raw-type legacy code.
43. **A** — unbounded `<T>` erases to `Object` (a bounded one erases to its bound).
44. **B** — generic args must be reference types; `ArrayList<int>` is illegal.
45. **C** — exactly one `ArrayList` class is loaded regardless of type args.
46. **D** — `new E()` is forbidden (E is unknown at runtime). The cast-array form in C compiles with a warning.
47. **B** — `? extends Number` = unknown subtype of Number (upper bound).
48. **B** — a generic class cannot extend `Throwable`, so this is illegal.
49. **B** — an ADT defines *what* (data + operations), not *how*.
50. **A** — a data structure is the concrete implementation (the "how").
51. **C** — a Bag is unordered and allows duplicates.
52. **C** — implementing an interface requires defining all its methods.
53. **A** — `getFrequencyOf` counts occurrences.
54. **D** — using without knowing internals = vending machine analogy.
55. **B** — `removeLast()` in a singly list must traverse to find the second-to-last node.
56. **C** — with only the node reference you can't unlink it correctly without reaching its predecessor from the head.
57. **B** — middle insertion in a doubly list updates both adjacent nodes' `next` and `prev`.
58. **D** — doubly linked list with head & tail gives O(1) removal at both ends.
59. **A** — `addFirst` is O(1).
60. **C** — in a circular singly list the last node's `next` points back to the first.
61. **C** — linked lists excel at insert/remove anywhere without shifting (arrays win at random access/binary search/cache locality).
62. **B** — stack = LIFO.
63. **A** — push A,B,C → top C; pop,pop removes C,B; push D → bottom-to-top A,D; top-to-bottom `D,A`.
64. **C** — ArrayList: all ops at the end, no shifting.
65. **C** — `(a+b)*c` → `a b + c *`.
66. **D** — `3*5=15`, `4+15=19`.
67. **B** — empty pop throws `EmptyStackException`.
68. **C** — queue = FIFO.
69. **B** — insert at rear, remove from front.
70. **D** — LinkedList (front removal is O(1)).
71. **B** — a priority queue removes the highest-priority element first.
72. **B** — `peek()` retrieves without removing; `poll()` removes.
73. **A** — FIFO: first dequeue returns `1`.
74. **C** — DFS uses a stack.
75. **A** — BFS uses a queue.
76. **C** — an adjacency matrix is O(n²).
77. **B** — sparse graphs are better as adjacency lists.
78. **C** — BFS finds the path with the fewest *edges* (unweighted shortest path).
79. **D** — connected iff DFS visits every vertex (#visited == #vertices).
80. **B** — recursion needs a base case and a recursive case.
81. **C** — no base case → unbounded stack growth → `StackOverflowError`.
82. **D** — `4+3+2+1+0 = 10`.
83. **B** — series 0,1,1,2,3,5,8 → `fib(6)=8`.
84. **B** — each call adds a stack frame (extra memory).
85. **B** — prints `7`, `3`, `5` → `735`.
86. **C** — directory size (nested subdirectories) is naturally recursive.
87. **B** — binary search requires sorted data.
88. **C** — binary search is O(log n).
89. **A** — "find the smallest, place it next" = selection sort.
90. **D** — "compare adjacent pairs, swap" = bubble sort.
91. **A** — merge sort is O(n log n) and stable.
92. **B** — linear search returns `-1` when not found.
93. **C** — merge sort = divide-and-conquer (split, sort halves, merge).
94. **C** — inorder of a BST = ascending sorted order.
95. **B** — preorder (Node,Left,Right): `50 30 20 40 70 80`.
96. **A** — postorder (Left,Right,Node): `20 40 30 80 70 50`.
97. **C** — inorder: `20 30 40 50 70 80`.
98. **B** — height = longest root-to-leaf path in edges.
99. **B** — BST ops are O(log n) only when reasonably balanced (O(n) when skewed).
100. **C** — delete-with-left-child replaces the node with the rightmost (largest) node of its left subtree.

---

### Answer distribution (sanity check)
A ≈ 22, B ≈ 30, C ≈ 31, D ≈ 17 — no single letter dominates, so don't pattern-guess.
