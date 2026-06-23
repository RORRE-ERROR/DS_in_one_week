# Mock Final — Codebase Reading Guide

This folder is the **code base you must read** to answer the code-reading questions
(Section A, Q1–Q35) of `../MOCK_FINAL_QUESTIONS.md`.

It is a self-contained set of **canonical, idiomatic Java data-structure implementations** —
the kind experienced programmers study. Every file compiles and runs. There is no `main`
in the structures themselves; they are libraries.

> **How to read for the exam:** for each question, open the named file, find the named
> method, and trace it by hand. Pay attention to (1) what a method *returns*, (2) its
> **Big-O**, (3) which underlying data structure it uses, and (4) edge cases (empty,
> capacity, duplicates).

## Class map

| File | Class | What it is | Underlying structure | Key methods |
|---|---|---|---|---|
| `DoublyLinkedList.java` | `DoublyLinkedList<E>` | Generic doubly linked list with **sentinel** head/tail | nodes w/ `prev`+`next` | `addFirst/addLast/removeFirst/removeLast` O(1), `indexOf` O(n) |
| `LRUCache.java` | `LRUCache` | Least-Recently-Used cache, O(1) get/put | **HashMap + doubly linked list** | `get`, `put` (evicts `tail.prev` = LRU) |
| `Trie.java` | `Trie` | Prefix tree for words | nodes w/ `Map<Character,node>` | `insert/search/startsWith` O(L) |
| `MinHeap.java` | `MinHeap<E extends Comparable<E>>` | Array-backed binary min-heap (priority queue engine) | `ArrayList` as heap array | `offer`/`poll` O(log n), `peek` O(1) |
| `UnionFind.java` | `UnionFind` | Disjoint Set Union | `parent[]` + `rank[]` | `find` (path compression), `union` (by rank), `count` |
| `Graph.java` | `Graph` | Undirected graph | **adjacency list** (`TreeMap`) | `dfs` (uses a **stack**), `bfs` (uses a **queue**), `isConnected` |
| `BST.java` | `BST<E extends Comparable<E>>` | Binary search tree | linked `TreeNode`s | `insert/search` O(h), `inorder/preorder/postorder`, `height` |
| `GenericStack.java` | `GenericStack<E>` | LIFO stack by **composition** | `ArrayList` (top = end) | `push/pop/peek`, throws `EmptyStackException` |
| `GenericQueue.java` | `GenericQueue<E>` | FIFO queue by **composition** | `LinkedList` | `enqueue/dequeue/peek` |
| `PostfixEvaluator.java` | `PostfixEvaluator` | Evaluates RPN expression | a stack (`ArrayDeque`) | `evaluate(String)` — first pop = RIGHT operand |
| `Sorts.java` | `Sorts` | Search + sort algorithms | `int[]` | `binarySearch` O(log n), `selection/insertion/bubble` O(n²), `mergeSort` O(n log n) |

## Verified reference outputs (so you can self-check your traces)

```
DoublyLinkedList: add 1,2 then addFirst 0  -> [0, 1, 2]; removeFirst=0; removeLast=2
LRUCache(2): put(1,1),put(2,2),get(1)=1,put(3,3) evicts key 2 -> get(2)=-1, get(3)=3
Trie: insert("apple") -> search("apple")=true, search("app")=false, startsWith("app")=true
MinHeap: offer 5,3,8,1,9,2 -> poll order = 1 2 3 5 8 9
UnionFind(5): union(0,1),(1,2),(3,4) -> count=2, connected(0,2)=true, connected(0,4)=false
Graph edges 0-1,0-2,1-3,2-3,3-4: DFS(0)=[0,1,3,2,4]   BFS(0)=[0,1,2,3,4]
BST insert 60,55,100,45,57,67,107:
   inorder   = [45, 55, 57, 60, 67, 100, 107]   (sorted!)
   preorder  = [60, 55, 45, 57, 100, 67, 107]
   postorder = [45, 57, 55, 67, 107, 100, 60]
   height    = 2 ; insert(60) again = false (duplicate)
GenericStack push A,B,C -> pop=C, peek=B
GenericQueue enqueue A,B,C -> dequeue=A, peek=B
PostfixEvaluator: "6 2 3 + -" = 1 ; "4 3 5 * +" = 19
Sorts.mergeSort {2,9,5,4,8,1,6} -> [1,2,4,5,6,8,9]; binarySearch(8)=5, binarySearch(7)=-1
```

## How to run it yourself

There is no JDK `javac` on this machine, only the Java 21 runtime, but the runtime can
compile-and-run a **single source file** that contains many top-level classes. To experiment,
copy the class(es) you want plus a small `public class Run { public static void main... }`
into one file and run `java Run.java`.
