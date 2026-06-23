# Chapter 3 — Linked List (LeetCode, 14-Day Plan Days 5–8)

Standard node used throughout:
```java
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
```

---

## Day 5 — 206. Reverse Linked List
Link: https://leetcode.com/problems/reverse-linked-list/

**Approach (iterative, 3 pointers).** Walk the list keeping `prev`, `cur`, `nxt`. For each node, save `nxt = cur.next`, flip `cur.next = prev`, then advance `prev = cur` and `cur = nxt`. When `cur` is null, `prev` is the new head.

**Complexity:** Time **O(n)**, Space **O(1)**.

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode nxt = cur.next; // save next
            cur.next = prev;         // reverse the link
            prev = cur;              // advance prev
            cur = nxt;               // advance cur
        }
        return prev; // new head (old tail)
    }
}
```

---

## Day 6 — 21. Merge Two Sorted Lists
Link: https://leetcode.com/problems/merge-two-sorted-lists/

**Approach (dummy head + two pointers).** Use a `dummy` node and a `tail` pointer. Repeatedly attach the smaller of `l1.val`/`l2.val` to `tail`, advance that list and `tail`. When one list runs out, attach the remaining list. Return `dummy.next`.

**Complexity:** Time **O(n + m)**, Space **O(1)**.

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) { tail.next = l1; l1 = l1.next; }
            else                  { tail.next = l2; l2 = l2.next; }
            tail = tail.next;
        }
        tail.next = (l1 != null) ? l1 : l2; // attach the remainder
        return dummy.next;
    }
}
```

---

## Day 7 — 141. Linked List Cycle
Link: https://leetcode.com/problems/linked-list-cycle/

**Approach (Floyd's fast & slow pointers).** `slow` moves 1 step, `fast` moves 2 steps. If there is a cycle, `fast` eventually laps and meets `slow`. If `fast` (or `fast.next`) hits null, there is no cycle. O(1) space, no hash set needed.

**Complexity:** Time **O(n)**, Space **O(1)**.

```java
class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;        // 1 step
            fast = fast.next.next;   // 2 steps
            if (slow == fast) return true; // they met -> cycle
        }
        return false; // reached the end -> no cycle
    }
}
```

---

## Day 8 — 146. LRU Cache  (ties to the mid-term LRU question)
Link: https://leetcode.com/problems/lru-cache/

**Approach (HashMap + doubly linked list).** This is exactly the mid-term answer: an **LRU cache where the oldest is removed from the tail is best served by a doubly linked list with head & tail, plus a hash map**.
- A **doubly linked list** orders nodes by recency: most-recently-used sits just after `head`, least-recently-used sits just before `tail`. The `prev` pointers make removing any node O(1).
- A **HashMap<key, node>** gives O(1) lookup of a node.
- `get`: look up the node, move it to the front (most recent), return its value.
- `put`: if present, update and move to front; else insert at front. If over capacity, evict the node before `tail` (the oldest) and remove it from the map.

Use **sentinel** `head` and `tail` dummy nodes so insert/remove never has null-edge special cases.

**Complexity:** Time **O(1)** for both `get` and `put`, Space **O(capacity)**.

```java
import java.util.HashMap;

class LRUCache {
    // Doubly linked node
    private static class Node {
        int key, value;
        Node prev, next;
        Node(int k, int v) { key = k; value = v; }
    }

    private final int capacity;
    private final HashMap<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(0, 0); // sentinel: most-recent side
    private final Node tail = new Node(0, 0); // sentinel: oldest side

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    // unlink a node from the list
    private void remove(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    // insert a node right after head (mark as most-recently-used)
    private void addFront(Node n) {
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
    }

    public int get(int key) {
        Node n = map.get(key);
        if (n == null) return -1;
        remove(n);
        addFront(n); // touch -> most recent
        return n.value;
    }

    public void put(int key, int value) {
        Node n = map.get(key);
        if (n != null) {           // update existing
            n.value = value;
            remove(n);
            addFront(n);
            return;
        }
        if (map.size() == capacity) {     // evict the oldest (node before tail)
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key);
        }
        Node fresh = new Node(key, value);
        map.put(key, fresh);
        addFront(fresh);
    }
}
```

---

## Resources

- **NeetCode.io** — watch the linked-list pattern videos:
  - *Reverse Linked List* (206) — pointer-rewiring walkthrough.
  - *Merge Two Sorted Lists* (21) — dummy-head two-pointer merge.
  - *Linked List Cycle* (141) — Floyd's fast & slow pointer (tortoise and hare).
  - *LRU Cache* (146) — HashMap + doubly linked list with sentinel head/tail.
  - NeetCode also groups these under its "Linked List" roadmap section: https://neetcode.io/roadmap
- **LeetCode editorials** (official solution + complexity, on each problem page under the "Editorial" tab):
  - https://leetcode.com/problems/reverse-linked-list/editorial/
  - https://leetcode.com/problems/merge-two-sorted-lists/editorial/
  - https://leetcode.com/problems/linked-list-cycle/editorial/
  - https://leetcode.com/problems/lru-cache/editorial/
- **labuladong** — "Linked List" two-pointer techniques and "LRU algorithm" chapter: https://labuladong.online/algo/en/
