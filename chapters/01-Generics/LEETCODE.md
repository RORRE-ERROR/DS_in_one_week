# Chapter 1 — Generics: Practice via "Designing Type-Safe Structures"

> Generics aren't a LeetCode tag, so we practise by **designing generic / type-safe data structures**. These two are the classic "design" problems that map onto the generics + ADT mindset: you control the API, the backing store, and the type discipline.

---

## 706. Design HashMap
🔗 https://leetcode.com/problems/design-hashmap/

Design a `HashMap` **without using built-in hash table libraries**: `put(key,value)`, `get(key)`, `remove(key)`.

### Approach
- Use **separate chaining**: a fixed-size array of buckets, each bucket a `LinkedList` of `[key, value]` entries.
- **Hash** the key into a bucket index with `key % size` (choose a prime-ish size, e.g. 769, to spread collisions).
- `put`: hash → bucket; if the key already exists, update its value; else append a new entry.
- `get`: hash → bucket; scan for the key; return value or `-1`.
- `remove`: hash → bucket; scan and remove the matching entry.
- **Generics connection:** the real `HashMap<K,V>` parameterizes key/value types; this problem fixes them to `int` so you focus on the bucket/collision mechanics. (Note: `int` is a primitive — a *generic* map would require `Integer`, a reference type.)

### Complexity
- **Time:** average **O(1)** per operation; worst case **O(n)** if everything collides into one bucket.
- **Space:** **O(n + k)** for n entries over k buckets.

```java
class MyHashMap {
    private static final int SIZE = 769;     // a prime to reduce collisions
    private LinkedList<int[]>[] buckets;     // each bucket: list of [key, value]

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) buckets[i] = new LinkedList<>();
    }

    private int hash(int key) { return key % SIZE; }

    public void put(int key, int value) {
        LinkedList<int[]> bucket = buckets[hash(key)];
        for (int[] entry : bucket) {
            if (entry[0] == key) { entry[1] = value; return; } // update
        }
        bucket.add(new int[]{key, value});                     // insert
    }

    public int get(int key) {
        LinkedList<int[]> bucket = buckets[hash(key)];
        for (int[] entry : bucket) {
            if (entry[0] == key) return entry[1];
        }
        return -1;
    }

    public void remove(int key) {
        LinkedList<int[]> bucket = buckets[hash(key)];
        Iterator<int[]> it = bucket.iterator();
        while (it.hasNext()) {
            if (it.next()[0] == key) { it.remove(); return; }
        }
    }
}
```

---

## 155. Min Stack
🔗 https://leetcode.com/problems/min-stack/

Design a stack supporting `push`, `pop`, `top`, and **`getMin` in constant time**.

### Approach
- A plain stack gives O(1) push/pop/top but `getMin` would be O(n).
- Trick: keep a **second stack** that tracks the minimum so far. On each `push(x)`, push `min(x, currentMin)` onto the min-stack; pop both together.
- The top of the min-stack is always the current minimum → **O(1) getMin**.
- **Generics connection:** this is the classic **Stack ADT** (LIFO; access only at `top`). A production version would be `MinStack<T extends Comparable<T>>`; here values are fixed to `int`.

### Complexity
- **Time:** **O(1)** for every operation (push, pop, top, getMin).
- **Space:** **O(n)** for the two parallel stacks.

```java
class MinStack {
    private Deque<Integer> stack = new ArrayDeque<>();
    private Deque<Integer> minStack = new ArrayDeque<>();

    public MinStack() { }

    public void push(int val) {
        stack.push(val);
        int min = minStack.isEmpty() ? val : Math.min(val, minStack.peek());
        minStack.push(min);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
```

---

## Resources
- **NeetCode** — design problems & stack/hashmap explanations: https://neetcode.io/
- **Baeldung — The Basics of Java Generics:** https://www.baeldung.com/java-generics
- **Oracle — Java Generics Tutorial:** https://docs.oracle.com/javase/tutorial/java/generics/types.html
