# Chapter 5 — Queue & Priority Queue (LeetCode 14-Day Plan)

> **Day 4 of 14.** Core: implement a queue and understand FIFO vs priority (heap) ordering.
> Each problem: link, approach, complexity, and a complete Java solution.

---

## Day 4 — Main: 232. Implement Queue using Stacks

**Link:** https://leetcode.com/problems/implement-queue-using-stacks/

**Approach:** Use **two stacks** — `in` and `out`. `push` always goes onto `in`. For `pop`/`peek`, if `out` is empty, **dump all of `in` into `out`** (this reverses LIFO → FIFO order), then take from the top of `out`. Each element is moved at most once between stacks → **amortized O(1)**.

**Complexity:** `push` O(1); `pop`/`peek` amortized O(1) (worst case O(n)); space O(n).

```java
import java.util.Stack;

class MyQueue {
    private Stack<Integer> in = new Stack<>();
    private Stack<Integer> out = new Stack<>();

    public MyQueue() { }

    public void push(int x) {
        in.push(x);
    }

    public int pop() {
        peek();                 // ensure out has the front on top
        return out.pop();
    }

    public int peek() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) out.push(in.pop());
        }
        return out.peek();
    }

    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}
```

---

## 225. Implement Stack using Queues

**Link:** https://leetcode.com/problems/implement-stack-using-queues/

**Approach:** Use a single `Queue`. On `push`, add the element, then **rotate** the queue: dequeue and re-enqueue every *previous* element so the newest element ends up at the **front**. This makes `pop`/`top` (which read the front) behave like a stack's top → **push O(n), pop/top O(1)**.

**Complexity:** `push` O(n); `pop`/`top`/`empty` O(1); space O(n).

```java
import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    private Queue<Integer> q = new LinkedList<>();

    public MyStack() { }

    public void push(int x) {
        q.offer(x);
        for (int i = 0; i < q.size() - 1; i++) {
            q.offer(q.poll());      // rotate so newest moves to front
        }
    }

    public int pop()  { return q.poll(); }
    public int top()  { return q.peek(); }
    public boolean empty() { return q.isEmpty(); }
}
```

---

## 933. Number of Recent Calls

**Link:** https://leetcode.com/problems/number-of-recent-calls/

**Approach:** Keep a **queue** of timestamps. On each `ping(t)`, enqueue `t`, then **poll** from the front all timestamps older than `t - 3000`. The queue size is the count of pings in the window `[t-3000, t]`. This is a textbook **sliding-window with a FIFO queue**.

**Complexity:** Each `ping` amortized O(1) (every timestamp is enqueued/dequeued once); space O(n) for the window.

```java
import java.util.LinkedList;
import java.util.Queue;

class RecentCounter {
    private Queue<Integer> q = new LinkedList<>();

    public RecentCounter() { }

    public int ping(int t) {
        q.offer(t);
        while (q.peek() < t - 3000) {   // drop calls outside the 3000ms window
            q.poll();
        }
        return q.size();
    }
}
```

---

## 215. Kth Largest Element in an Array (heap)

**Link:** https://leetcode.com/problems/kth-largest-element-in-an-array/

**Approach:** Maintain a **min-heap** (`PriorityQueue`) of size `k`. Push each number; when the heap exceeds size `k`, `poll()` the smallest. After processing all numbers, the heap holds the `k` largest, and its head (`peek`) is the **k-th largest**. This uses the priority-queue/heap property directly.

**Complexity:** O(n log k) time, O(k) space.

```java
import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();  // min-heap
        for (int n : nums) {
            minHeap.offer(n);
            if (minHeap.size() > k) {
                minHeap.poll();          // remove smallest -> keep k largest
            }
        }
        return minHeap.peek();           // k-th largest is the heap's smallest
    }
}
```

---

## 703. Kth Largest Element in a Stream

**Link:** https://leetcode.com/problems/kth-largest-element-in-a-stream/

**Approach:** Same min-heap-of-size-`k` idea, but maintained **across streaming `add` calls**. Initialize the heap with the given array, trimming to size `k`. Each `add(val)` offers the value, trims back to size `k`, and returns `peek()` — the current k-th largest.

**Complexity:** Constructor O(n log k); each `add` O(log k); space O(k).

```java
import java.util.PriorityQueue;

class KthLargest {
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int n : nums) {
            minHeap.offer(n);
            if (minHeap.size() > k) minHeap.poll();
        }
    }

    public int add(int val) {
        minHeap.offer(val);
        if (minHeap.size() > k) minHeap.poll();
        return minHeap.peek();
    }
}
```

---

## Resources

- Liang, *Introduction to Java Programming*, 10th Ed. — **Chapters 19 & 24** (Queues, Priority Queues, Heaps).
- Java API — `java.util.Queue`: https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html
- Java API — `java.util.LinkedList`: https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html
- Java API — `java.util.PriorityQueue`: https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html
- Queue animation: https://yongdanielliang.github.io/animation/web/Queue.html
- LeetCode tag — **Queue**: https://leetcode.com/tag/queue/  •  **Heap (Priority Queue)**: https://leetcode.com/tag/heap-priority-queue/
