# Data Structure — Final Exam Study System (WIA1002, Java)

Everything you need for the **all-MCQ final exam** plus a **14-day LeetCode track**.
Built from the lecture PDFs + the mid-term quiz style (concept, code-output/trace,
"which is most efficient", and tricky "cannot/except" wording).

## 📁 How this is organised

```
chapters/<NN-topic>/
  NOTES.md       distilled notes + ★ exam tips + worked examples
  MCQ.md         12–14 practice MCQs in mid-term style (questions only)
  ANSWER_KEY.md  answers + short explanations
  LEETCODE.md    the relevant LeetCode problems + full Java solutions + resources
  Pattern.java   a clean, copy-paste-ready TEMPLATE of that data structure
MOCK_FINAL/
  codebase/                 a hard, idiomatic Java codebase to READ
  MOCK_FINAL_QUESTIONS.md   100-question mock exam (35 code-reading + 65 concept)
  MOCK_FINAL_ANSWER_KEY.md  answers + explanations
```

### The chapters
| # | Topic | Folder |
|---|---|---|
| 00 | OOP Fundamentals | `chapters/00-OOP-Fundamentals/` |
| 01 | Generics | `chapters/01-Generics/` |
| 02 | ADT & Bag | `chapters/02-ADT-and-Bag/` |
| 03 | Linked List | `chapters/03-LinkedList/` |
| 04 | Stack | `chapters/04-Stack/` |
| 05 | Queue & Priority Queue | `chapters/05-Queue/` |
| 06 | Graph | `chapters/06-Graph/` |
| 07 | Recursion | `chapters/07-Recursion/` |
| 08 | Searching & Sorting | `chapters/08-Search-Sort/` |
| 09 | Binary Search Tree | `chapters/09-BST/` |

### The `Pattern.java` files
Each is a **runnable template** so you can recreate any structure from memory.
There's no JDK `javac` on this machine, but Java 21's runtime compiles-and-runs a single
file: `java chapters/04-Stack/Pattern.java`.

---

## 🗓️ 14-Day LeetCode Track (one problem/day, ramping up)

Do one per day in the two weeks before the exam. Full Java solutions are in each chapter's
`LEETCODE.md`. Problems chosen to reinforce exactly what the exam tests.

| Day | Topic | Problem | Difficulty | Chapter file |
|----|-------|---------|-----------|--------------|
| 1 | Stack | [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) | Easy | 04-Stack |
| 2 | Stack | [150. Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) | Medium | 04-Stack |
| 3 | Stack | [155. Min Stack](https://leetcode.com/problems/min-stack/) | Medium | 04-Stack |
| 4 | Queue | [232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/) | Easy | 05-Queue |
| 5 | Linked List | [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) | Easy | 03-LinkedList |
| 6 | Linked List | [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) | Easy | 03-LinkedList |
| 7 | Linked List | [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) | Easy | 03-LinkedList |
| 8 | Linked List ★ | [146. LRU Cache](https://leetcode.com/problems/lru-cache/) | Medium | 03-LinkedList |
| 9 | Search | [704. Binary Search](https://leetcode.com/problems/binary-search/) | Easy | 08-Search-Sort |
| 10 | Sort | [912. Sort an Array](https://leetcode.com/problems/sort-an-array/) (merge sort) | Medium | 08-Search-Sort |
| 11 | Recursion | [509. Fibonacci Number](https://leetcode.com/problems/fibonacci-number/) | Easy | 07-Recursion |
| 12 | BST | [700. Search in a BST](https://leetcode.com/problems/search-in-a-binary-search-tree/) + [701. Insert](https://leetcode.com/problems/insert-into-a-binary-search-tree/) | Easy | 09-BST |
| 13 | BST | [94. Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/) (+ [230. Kth Smallest](https://leetcode.com/problems/kth-smallest-element-in-a-bst/)) | Easy/Med | 09-BST |
| 14 | Graph ★ | [200. Number of Islands](https://leetcode.com/problems/number-of-islands/) | Medium | 06-Graph |

★ = a celebrated, interview-favourite problem. **Day 8 (LRU Cache)** is the doubly-linked-list +
hashmap design that the mid-term hinted at; **Day 14 (Number of Islands)** is the classic DFS/BFS capstone.

### Stretch problems (the "hard but loved" structures)
If you finish early or want more, each `LEETCODE.md` lists extras:
[208. Implement Trie](https://leetcode.com/problems/implement-trie-prefix-tree/),
[215. Kth Largest (heap)](https://leetcode.com/problems/kth-largest-element-in-an-array/),
[547. Number of Provinces (union-find/DFS)](https://leetcode.com/problems/number-of-provinces/),
[23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/),
[98. Validate BST](https://leetcode.com/problems/validate-binary-search-tree/).
These map directly to the implementations in `MOCK_FINAL/codebase/`.

---

## 📚 Resources

- **NeetCode** — https://neetcode.io (free roadmap + video for almost every problem above). The
  NeetCode roadmap order is: Arrays/Hashing → Stack → Binary Search → Linked List → Trees → Heap → Graphs.
- **LeetCode editorials/discuss** — official solution + top community solutions on each problem page.
- **labuladong's algo notes** — https://labuladong.online/en/ (clean DFS/BFS and LRU framework write-ups).
- **Baeldung** — https://www.baeldung.com (Java-specific: generics, collections, LRU cache).
- **GeeksforGeeks** — https://www.geeksforgeeks.org (ADT, every data structure, with Java).
- **Liang animations** (from the lectures): Stack, Queue, BST, the sort/search visualisers at
  `https://yongdanielliang.github.io/animation/animation.html`.

---

## ✅ Suggested 2-week plan

1. **Each day:** read one chapter's `NOTES.md` (≈20 min) → do that chapter's `MCQ.md` (≈15 min)
   → check `ANSWER_KEY.md` → solve the day's LeetCode problem in Java (≈30–45 min).
2. **Re-create from memory:** once per structure, type out its `Pattern.java` without looking.
3. **~3 days before the exam:** sit the full `MOCK_FINAL/` under timed conditions (100 min),
   reading the `codebase/` for Section A. Mark it, then reread any chapter you missed >2 on.
4. **Day before:** skim every `NOTES.md` "★ Exam Tips" section + the complexity cheat-sheet
   in `chapters/03`–`09`.

Good luck! 🍀
