# Chapter 7 — Recursion (LEETCODE)

## 14-Day Plan — Day 11: Recursion

Today's focus is recognizing the **base case** and the **recursive case** in real problems, then writing clean recursive solutions. The headline problem is **509. Fibonacci Number**; the rest reinforce recursion on numbers, strings, trees, and linked lists.

---

## ★ Day 11 Main — 509. Fibonacci Number
Link: https://leetcode.com/problems/fibonacci-number/

**Approach**
- **Base case:** `fib(0) = 0`, `fib(1) = 1`.
- **Recursive case:** `fib(n) = fib(n-1) + fib(n-2)` for `n >= 2`.
- Plain recursion is `O(2^n)`; the memoized version below is `O(n)` and worth knowing.

**Complexity**
- Plain recursion: Time `O(2^n)`, Space `O(n)` (call stack depth).
- Memoized: Time `O(n)`, Space `O(n)`.

```java
class Solution {
    public int fib(int n) {
        if (n < 2) return n;                 // base cases: fib(0)=0, fib(1)=1
        return fib(n - 1) + fib(n - 2);      // recursive case
    }

    // O(n) memoized variant
    public int fibMemo(int n) {
        return helper(n, new int[n + 1], new boolean[n + 1]);
    }
    private int helper(int n, int[] memo, boolean[] seen) {
        if (n < 2) return n;
        if (seen[n]) return memo[n];
        memo[n] = helper(n - 1, memo, seen) + helper(n - 2, memo, seen);
        seen[n] = true;
        return memo[n];
    }
}
```

---

## 70. Climbing Stairs
Link: https://leetcode.com/problems/climbing-stairs/

**Approach**
- To reach step `n` you either came from step `n-1` (1 step) or step `n-2` (2 steps), so `ways(n) = ways(n-1) + ways(n-2)` — the Fibonacci recurrence.
- **Base case:** `n == 1 → 1`, `n == 2 → 2`.
- **Recursive case:** `climb(n) = climb(n-1) + climb(n-2)`. Memoize to avoid exponential blowup.

**Complexity**
- Memoized: Time `O(n)`, Space `O(n)`.

```java
class Solution {
    public int climbStairs(int n) {
        return climb(n, new int[n + 1]);
    }
    private int climb(int n, int[] memo) {
        if (n <= 2) return n;            // base case: 1->1, 2->2
        if (memo[n] != 0) return memo[n];
        memo[n] = climb(n - 1, memo) + climb(n - 2, memo);  // recursive case
        return memo[n];
    }
}
```

---

## 344. Reverse String (recursive)
Link: https://leetcode.com/problems/reverse-string/

**Approach**
- Swap the two ends, then recurse inward.
- **Base case:** pointers meet or cross (`left >= right`) → done.
- **Recursive case:** swap `s[left]` and `s[right]`, then recurse on `(left+1, right-1)`.

**Complexity**
- Time `O(n)`, Space `O(n)` for the recursion stack.

```java
class Solution {
    public void reverseString(char[] s) {
        reverse(s, 0, s.length - 1);
    }
    private void reverse(char[] s, int left, int right) {
        if (left >= right) return;        // base case
        char tmp = s[left];
        s[left] = s[right];
        s[right] = tmp;
        reverse(s, left + 1, right - 1);  // recursive case (smaller window)
    }
}
```

---

## 104. Maximum Depth of Binary Tree
Link: https://leetcode.com/problems/maximum-depth-of-binary-tree/

**Approach**
- A tree is inherently recursive: depth = 1 + the deeper of the two subtrees.
- **Base case:** `root == null` → depth `0`.
- **Recursive case:** `1 + max(depth(left), depth(right))`.

**Complexity**
- Time `O(n)` (visit every node), Space `O(h)` where `h` is tree height (recursion stack).

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;                       // base case
        int left  = maxDepth(root.left);                  // recursive case
        int right = maxDepth(root.right);
        return 1 + Math.max(left, right);
    }
}
```

---

## 206. Reverse Linked List (recursive variant)
Link: https://leetcode.com/problems/reverse-linked-list/

**Approach**
- **Base case:** empty list or single node (`head == null || head.next == null`) → that node is the new head.
- **Recursive case:** reverse the rest of the list, then make `head.next` point back to `head` and detach `head.next`.

**Complexity**
- Time `O(n)`, Space `O(n)` for the recursion stack.

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;  // base case
        ListNode newHead = reverseList(head.next);           // recursive case
        head.next.next = head;   // make the next node point back to head
        head.next = null;        // detach to avoid a cycle
        return newHead;
    }
}
```

---

## Resources
- Liang, *Introduction to Java Programming*, 10th Edition, **Chapter 18 — Recursion**.
- LeetCode **Recursion I** Explore card: https://leetcode.com/explore/learn/card/recursion-i/
- Course chapter notes: see `NOTES.md` in this folder for base case / recursive case patterns, the factorial & Fibonacci traces, and the StackOverflowError discussion.
- Tip: for every problem, write the **base case first**, then the **recursive case**, and confirm each call moves toward the base case.
