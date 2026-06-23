# Mock Final Exam — 100 MCQs (Data Structure)

**Format:** 100 multiple-choice questions, one correct answer each. Mid-term style.
**Time target:** ~100 minutes (≈1 min/question).
**Answers:** see `MOCK_FINAL_ANSWER_KEY.md` (don't peek until you finish a section).

- **Section A (Q1–Q35): Code reading.** Open `codebase/` and read the actual `.java` files.
  Use `codebase/CODEBASE_GUIDE.md` to navigate. These test tracing, complexity, and which
  structure is used — exactly like reading a real project (e.g. the `smartlibrary/` codebase).
- **Section B (Q36–Q100): Concepts & standalone code** across all 10 chapters.

Watch for **negative wording** (NOT, EXCEPT, CANNOT, always, only) — it flips the answer.

---

## SECTION A — Code Reading (`codebase/`)  Q1–Q35

### `DoublyLinkedList.java`

**Q1.** What is the time complexity of `removeLast()`?
A. O(n²)
B. O(log n)
C. O(1) because `tail.prev` is directly accessible via the sentinel
D. O(n) because it must traverse to find the previous node

**Q2.** Why does the class allocate two sentinel nodes (`head` and `tail`) in the constructor?
A. So every real node always has non-null `prev` and `next`, removing first/last special cases
B. To store the first and last user values
C. To save memory
D. They are required by the `Comparable` interface

**Q3.** After `addLast(1); addLast(2); addFirst(0);`, what does `toString()` return?
A. `[1, 2, 0]`   B. `[2, 1, 0]`   C. `[0, 1, 2]`   D. `[0, 2, 1]`

**Q4.** Calling `removeFirst()` on an empty `DoublyLinkedList` will:
A. return `null`   B. throw `NoSuchElementException`   C. return `0`   D. loop forever

### `LRUCache.java`

**Q5.** Given `LRUCache c = new LRUCache(2); c.put(1,1); c.put(2,2); c.get(1); c.put(3,3);` — what does `c.get(2)` return?
A. `-1`   B. `2`   C. `0`   D. `3`

**Q6.** In Q5, which key was evicted when `put(3,3)` ran?
A. key 1   B. key 3   C. key 2   D. nothing was evicted

**Q7.** Which two data structures does `LRUCache` combine to achieve O(1) `get`/`put`?
A. A `TreeMap` and an array
B. Two stacks
C. A min-heap and a queue
D. A `HashMap` and a doubly linked list

**Q8.** Inside `put`, when `map.size() == capacity` for a new key, which node is removed?
A. `tail.prev` (least recently used)
B. `head.next` (most recently used)
C. a random node
D. the node with the smallest key

**Q9.** `get(key)` for a key not in the cache returns:
A. `0`   B. `-1`   C. `null`   D. throws an exception

### `Trie.java`

**Q10.** After only `insert("apple")`, what does `search("app")` return?
A. `true`   B. `null`   C. `false`   D. throws an exception

**Q11.** After `insert("apple")`, what does `startsWith("app")` return?
A. `true`   B. `false`   C. `-1`   D. throws an exception

**Q12.** What is the time complexity of `insert(word)` where L = word length?
A. O(1)   B. O(L²)   C. O(number of words stored)   D. O(L)

### `MinHeap.java`

**Q13.** After `offer` of 5, 3, 8, 1, 9, 2, repeatedly calling `poll()` produces what order?
A. `1 2 3 5 8 9`   B. `5 3 8 1 9 2`   C. `9 8 5 3 2 1`   D. `2 1 9 8 3 5`

**Q14.** What is the complexity of `peek()` versus `offer()`?
A. both O(1)   B. both O(log n)   C. `peek` O(1), `offer` O(log n)   D. `peek` O(n), `offer` O(1)

**Q15.** For a node at index `i` in the heap array, its parent index is:
A. `(i - 1) / 2`   B. `2*i + 1`   C. `2*i + 2`   D. `i / 2 + 1`

### `UnionFind.java`

**Q16.** `UnionFind uf = new UnionFind(5); uf.union(0,1); uf.union(1,2); uf.union(3,4);` — what does `uf.count()` return?
A. `5`   B. `2`   C. `3`   D. `1`

**Q17.** In Q16, what does `uf.connected(0,4)` return?
A. `true`   B. `-1`   C. `false`   D. throws an exception

**Q18.** Which two optimizations does this `UnionFind` use?
A. Memoization and recursion
B. Binary search and hashing
C. Lazy deletion and rehashing
D. Path compression in `find` and union by rank in `union`

### `Graph.java`

**Q19.** With edges `0-1, 0-2, 1-3, 2-3, 3-4`, what does `dfs(0)` return?
A. `[0, 1, 3, 2, 4]`   B. `[0, 1, 2, 3, 4]`   C. `[0, 2, 3, 1, 4]`   D. `[4, 3, 2, 1, 0]`

**Q20.** With the same graph, what does `bfs(0)` return?
A. `[0, 1, 3, 2, 4]`   B. `[0, 2, 1, 3, 4]`   C. `[0, 4, 3, 2, 1]`   D. `[0, 1, 2, 3, 4]`

**Q21.** Which auxiliary structure does `dfs` use internally to control traversal order?
A. a queue (`offer`/`poll`)
B. a stack (`push`/`pop` on a `Deque`)
C. recursion only
D. a priority queue

**Q22.** Which auxiliary structure does `bfs` use internally?
A. a queue (`offer`/`poll`)   B. a stack   C. a `TreeMap`   D. a min-heap

**Q23.** `isConnected()` decides connectivity by:
A. counting edges
B. checking whether the adjacency map is non-empty
C. checking whether `dfs(start).size()` equals the number of vertices
D. comparing in-degree and out-degree

### `BST.java`

**Q24.** After inserting 60, 55, 100, 45, 57, 67, 107, what does `inorder()` return?
A. `[60, 55, 100, 45, 57, 67, 107]`
B. `[107, 100, 67, 60, 57, 55, 45]`
C. `[60, 55, 45, 57, 100, 67, 107]`
D. `[45, 55, 57, 60, 67, 100, 107]`

**Q25.** With the same tree, `preorder()` returns:
A. `[60, 55, 45, 57, 100, 67, 107]`
B. `[45, 55, 57, 60, 67, 100, 107]`
C. `[45, 57, 55, 67, 107, 100, 60]`
D. `[60, 100, 55, 107, 67, 57, 45]`

**Q26.** With the same tree, `postorder()` returns:
A. `[60, 55, 45, 57, 100, 67, 107]`
B. `[45, 57, 55, 67, 107, 100, 60]`
C. `[45, 55, 57, 60, 67, 100, 107]`
D. `[107, 67, 100, 57, 45, 55, 60]`

**Q27.** Calling `insert(60)` again on that tree returns:
A. `true` and adds a duplicate
B. throws an exception
C. `false` and changes nothing
D. `true` and replaces the root

**Q28.** What is the worst-case complexity of `search(e)` if elements are inserted in already-sorted order (skewed tree)?
A. O(1)   B. O(log n)   C. O(n²)   D. O(n)

### `GenericStack.java` / `GenericQueue.java`

**Q29.** `GenericStack<String> s; s.push("A"); s.push("B"); s.push("C");` — `s.pop()` then `s.peek()` give:
A. `C` then `B`   B. `A` then `B`   C. `C` then `A`   D. `A` then `C`

**Q30.** `GenericQueue<String> q; q.enqueue("A"); q.enqueue("B"); q.enqueue("C");` — `q.dequeue()` returns:
A. `C`   B. `A`   C. `B`   D. `null`

**Q31.** Why is `GenericStack` backed by an `ArrayList` while `GenericQueue` is backed by a `LinkedList`?
A. Purely stylistic; no performance difference
B. ArrayList cannot store generics
C. The stack only touches the list's END (O(1)); the queue removes from the FRONT, which is O(1) on a linked list but O(n) on an array
D. LinkedList cannot grow dynamically

### `PostfixEvaluator.java`

**Q32.** `PostfixEvaluator.evaluate("6 2 3 + -")` returns:
A. `7`   B. `5`   C. `-1`   D. `1`

**Q33.** In `evaluate`, when an operator is read, which operand is popped FIRST?
A. the right operand   B. the left operand   C. the operator   D. the result

### `Sorts.java`

**Q34.** `Sorts.binarySearch(a, 7)` where `a = {1,2,4,5,6,8,9}` returns:
A. `7`   B. the insertion index   C. `0`   D. `-1`

**Q35.** Which statement about `Sorts` is correct?
A. `mergeSort` is O(n²)
B. `mergeSort` is O(n log n) while `selectionSort`/`insertionSort`/`bubbleSort` are O(n²)
C. `binarySearch` works on any (even unsorted) array
D. `bubbleSort` here never terminates early

---

## SECTION B — Concepts & Standalone Code  Q36–Q100

### OOP Fundamentals (Q36–Q41)

**Q36.** A subclass `Dog extends Animal` redefines `void sound()` that `Animal` also defines. This is:
A. Overloading   B. Encapsulation   C. Overriding   D. Abstraction

**Q37.** Two methods `area(int)` and `area(int, int)` in the same class is an example of:
A. Overloading   B. Overriding   C. Inheritance   D. Polymorphism at runtime

**Q38.** `System.out.print("Pi" + 3.14);` prints:
A. `3.14Pi`   B. `Pi3.14`   C. `Pi + 3.14`   D. compile error

**Q39.** `System.out.print(7 / 2);` prints:
A. `3.5`   B. `4`   C. `3`   D. `3.0`

**Q40.** Declaring fields `private` and exposing `getX()/setX()` demonstrates:
A. Inheritance   B. Overloading   C. Recursion   D. Encapsulation (information hiding)

**Q41.** Which is TRUE about a `no-arg` constructor?
A. Every class must explicitly declare one
B. It initializes an object with default values and takes no parameters
C. It cannot coexist with a parameterized constructor
D. It must return a value

### Generics (Q42–Q48)

**Q42.** Why does Java erase generic type information after compile-time checking?
A. To speed up execution   B. To reduce JVM memory
C. To enable backward compatibility with legacy (raw-type) code   D. To allow multiple inheritance

**Q43.** During type erasure, an unbounded `<T>` is replaced with:
A. `Object`   B. `void`   C. `java.lang.Class`   D. the runtime type

**Q44.** Which is ILLEGAL?
A. `ArrayList<Integer>`   B. `ArrayList<int>`   C. `ArrayList<Double>`   D. `ArrayList<String>`

**Q45.** For `ArrayList<String> a` and `ArrayList<Integer> b`, how many `ArrayList` classes are loaded into the JVM?
A. 0   B. 2   C. 1   D. one per element added

**Q46.** Which is NOT allowed with a generic type parameter `E` of a class?
A. `E var;`   B. using `E` as a method return type   C. `E[] arr = (E[]) new Object[10];`   D. `new E()`

**Q47.** `<? extends Number>` means:
A. exactly `Number`   B. an unknown subtype of `Number`   C. an unknown supertype of `Number`   D. any object type

**Q48.** Can you declare `class MyException<T> extends Exception { }`?
A. Yes, always
B. No — generic classes cannot extend `Throwable`
C. Yes, but only if `T extends Throwable`
D. Yes, only in a static context

### ADT & Bag (Q49–Q54)

**Q49.** An Abstract Data Type (ADT) specifies:
A. exactly how data is stored in memory
B. what data is stored and what operations are available, not how they are implemented
C. only the programming language used
D. the CPU instructions used

**Q50.** A "data structure" differs from an ADT in that it:
A. is the concrete implementation (the "how")
B. is language-independent
C. hides all operations
D. cannot store objects

**Q51.** Which is TRUE of the Bag ADT?
A. Items are kept in sorted order
B. Duplicates are not allowed
C. It is an unordered collection that allows duplicates
D. It has a fixed maximum of 10 items

**Q52.** A class that includes `implements BagInterface<T>` must:
A. define at least one method   B. be abstract   C. define ALL methods declared in the interface   D. extend another class

**Q53.** Which Bag operation reports how many times an item appears?
A. `getFrequencyOf`   B. `contains`   C. `getCurrentSize`   D. `toArray`

**Q54.** Using an ADT without knowing its internals is most like:
A. rewiring a circuit   B. reading source code   C. compiling a program   D. using a vending machine

### Linked List (Q55–Q61)

**Q55.** In a SINGLY linked list, which operation requires traversing to find the previous node?
A. `addFirst(e)`   B. `removeLast()`   C. `removeFirst()`   D. `addFirst` after `peek`

**Q56.** In a singly linked list, given only a reference to a node (not its predecessor), you CANNOT:
A. read its element   B. access the next node   C. truly unlink it without traversing from the head   D. update the list size

**Q57.** Inserting a node in the middle of a DOUBLY linked list requires updating:
A. only the new node's pointers
B. both the `next` and `prev` pointers of the adjacent nodes
C. only the next pointer of the previous node
D. only the previous pointer of the next node

**Q58.** You need a cache that removes the oldest item from the tail efficiently and supports O(1) removal from both ends. Best choice:
A. singly linked list with only head   B. array   C. plain stack   D. doubly linked list with head & tail

**Q59.** In a singly linked list, `addFirst(e)` is:
A. O(1)   B. O(n)   C. O(log n)   D. O(n²)

**Q60.** A circular singly linked list differs from a normal singly linked list in that:
A. each node has a prev pointer   B. it cannot be traversed   C. the last node's `next` points back to the first node   D. it has no head

**Q61.** Compared to an array, a linked list is BETTER for:
A. random access by index   B. cache locality   C. inserting/removing at arbitrary positions without shifting   D. binary search

### Stack (Q62–Q67)

**Q62.** A stack follows which ordering?
A. FIFO   B. LIFO   C. priority   D. sorted

**Q63.** Push A, B, C; pop; pop; push D. From top to bottom the stack now holds:
A. `D, A`   B. `A, D`   C. `D, C`   D. `A, B`

**Q64.** Which structure is most efficient to implement a stack, and why?
A. LinkedList, because of prev pointers
B. a BST, because of O(log n) operations
C. ArrayList, because all operations happen at the end with no shifting
D. a queue, because of FIFO

**Q65.** Convert infix `(a + b) * c` to postfix:
A. `a b c * +`   B. `a + b c *`   C. `a b + c *`   D. `* + a b c`

**Q66.** Evaluate postfix `4 3 5 * +`:
A. `35`   B. `27`   C. `60`   D. `19`

**Q67.** Calling `pop()` on an empty stack should:
A. return null silently   B. throw `EmptyStackException`   C. return 0   D. resize the stack

### Queue (Q68–Q73)

**Q68.** A queue follows which ordering?
A. LIFO   B. priority   C. FIFO   D. random

**Q69.** In a queue, elements are inserted at the ____ and removed from the ____:
A. front; rear   B. rear; front   C. front; front   D. rear; rear

**Q70.** Which structure is most efficient to implement a queue?
A. ArrayList   B. BST   C. min-heap only   D. LinkedList

**Q71.** In a priority queue, which element is removed first?
A. the oldest inserted   B. the one with the highest priority   C. the newest inserted   D. a random element

**Q72.** For Java's `PriorityQueue`, which method retrieves but does NOT remove the head?
A. `poll()`   B. `peek()`   C. `offer()`   D. `remove()`

**Q73.** `enqueue(1); enqueue(2); enqueue(3); dequeue();` — what does `dequeue()` return?
A. `1`   B. `2`   C. `3`   D. `0`

### Graph (Q74–Q79)

**Q74.** Depth-First Search (DFS) is naturally implemented using a:
A. queue   B. min-heap   C. stack   D. hash map

**Q75.** Breadth-First Search (BFS) is naturally implemented using a:
A. queue   B. stack   C. priority queue   D. tree

**Q76.** An adjacency matrix for a graph with n vertices uses how much space?
A. O(n)   B. O(n log n)   C. O(n²)   D. O(1)

**Q77.** For a SPARSE graph (few edges), the better representation is:
A. adjacency matrix   B. adjacency list   C. a single array   D. a 3D matrix

**Q78.** BFS is especially good for finding:
A. the path with the smallest total weight
B. a cycle
C. the path with the fewest edges between two vertices
D. the maximum spanning tree

**Q79.** Using DFS, a graph is connected if:
A. it has no cycles
B. every vertex has degree 2
C. it is weighted
D. the number of vertices visited equals the total number of vertices

### Recursion (Q80–Q86)

**Q80.** Every recursive method must have:
A. a loop   B. a base case and a recursive case   C. a static field   D. two parameters

**Q81.** What happens if a recursive method never reaches its base case?
A. it returns 0   B. a compile error   C. a `StackOverflowError` at runtime   D. it returns null

**Q82.** Consider:
```java
static int f(int n) {
    if (n == 0) return 0;
    return n + f(n - 1);
}
```
What does `f(4)` return?
A. `4`   B. `24`   C. `0`   D. `10`

**Q83.** For the Fibonacci definition `fib(0)=0, fib(1)=1, fib(n)=fib(n-1)+fib(n-2)`, what is `fib(6)`?
A. `5`   B. `8`   C. `13`   D. `21`

**Q84.** Compared with iteration, each recursive call:
A. uses no extra memory
B. requires extra space on the call stack (a new frame)
C. is always faster
D. cannot be replaced by a loop

**Q85.** Consider:
```java
static void p(int n) {
    if (n <= 0) return;
    System.out.print(n % 10);
    p(n / 10);
}
```
What does `p(537)` print?
A. `537`   B. `735`   C. `753`   D. `375`

**Q86.** Which problem is most naturally solved with recursion?
A. summing a fixed array with a for-loop
B. reading a single line of input
C. computing the total size of a directory that contains subdirectories
D. incrementing a counter

### Searching & Sorting (Q87–Q93)

**Q87.** Binary search requires that the data is:
A. unsorted   B. sorted   C. stored in a linked list   D. all unique

**Q88.** The time complexity of binary search is:
A. O(n)   B. O(n log n)   C. O(log n)   D. O(1)

**Q89.** "Repeatedly find the smallest remaining element and place it next" describes:
A. selection sort   B. insertion sort   C. bubble sort   D. merge sort

**Q90.** "Compare adjacent pairs and swap if out of order, repeating passes" describes:
A. selection sort   B. merge sort   C. binary search   D. bubble sort

**Q91.** Which sorting algorithm runs in O(n log n) and is stable?
A. merge sort   B. selection sort   C. insertion sort   D. bubble sort

**Q92.** Linear search returns what when the key is not found?
A. `0`   B. `-1`   C. the last index   D. throws an exception

**Q93.** Merge sort is best described as:
A. greedy
B. brute force
C. divide-and-conquer: split, sort halves recursively, then merge
D. hashing

### Binary Search Tree (Q94–Q100)

**Q94.** For a BST, an INORDER traversal visits the elements in:
A. random order   B. descending sorted order   C. ascending sorted order   D. level order

**Q95.** Given the BST:
```
        50
       /  \
     30    70
    /  \     \
   20   40    80
```
The PREORDER traversal is:
A. `20 30 40 50 70 80`   B. `50 30 20 40 70 80`   C. `20 40 30 80 70 50`   D. `50 70 80 30 40 20`

**Q96.** For the same tree, the POSTORDER traversal is:
A. `20 40 30 80 70 50`   B. `50 30 20 40 70 80`   C. `20 30 40 50 70 80`   D. `80 70 50 40 30 20`

**Q97.** For the same tree, the INORDER traversal is:
A. `50 30 70 20 40 80`   B. `20 40 30 50 80 70`   C. `20 30 40 50 70 80`   D. `80 70 50 40 30 20`

**Q98.** The "height" of a binary tree is:
A. the number of nodes
B. the length (in edges) of the longest path from the root to a leaf
C. the number of leaves
D. the depth of the root

**Q99.** Searching or inserting in a BST is O(log n) only when the tree is:
A. skewed   B. reasonably balanced   C. empty   D. complete and full at all times

**Q100.** When deleting a node that HAS a left child, the standard algorithm replaces it with:
A. its right child directly
B. the root
C. the rightmost (largest) node of its LEFT subtree
D. a new null node

---

*End of mock final. Mark yourself with `MOCK_FINAL_ANSWER_KEY.md` and revisit any chapter where you missed more than 2 questions.*
