# Chapter 9 — BST: LeetCode 14-Day Plan

All solutions use the standard LeetCode `TreeNode`:

```java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val; this.left = left; this.right = right;
    }
}
```

The 14-day plan culminates in BST problems on **Day 12–13** (with a bonus and two extras), once you have warmed up on traversals earlier in the week.

| Day | Focus |
|---|---|
| 1–11 | (earlier chapters: arrays, linked lists, stacks/queues, recursion, etc.) |
| **12** | **700. Search in a BST** + **701. Insert into a BST** |
| **13** | **94. Binary Tree Inorder Traversal** (bonus: **230. Kth Smallest Element in a BST**) |
| **14** | Review + **98. Validate BST**, **450. Delete Node in a BST** |

---

## Day 12 — Problem 700: Search in a BST

Link: https://leetcode.com/problems/search-in-a-binary-search-tree/

**Approach:** Use the BST property. Compare the target with the current node: if equal, return the node; if smaller, go left; if larger, go right. Stop when you fall off the tree.

**Complexity:** Time O(h) — O(log n) balanced, O(n) skewed. Space O(1) iterative.

```java
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode current = root;
        while (current != null) {
            if (val == current.val)      return current;
            else if (val < current.val)  current = current.left;
            else                         current = current.right;
        }
        return null; // not found
    }
}
```

---

## Day 12 — Problem 701: Insert into a BST

Link: https://leetcode.com/problems/insert-into-a-binary-search-tree/

**Approach:** Walk down like search, tracking the parent. When you reach an empty spot, attach the new node there. If the tree is empty, the new node is the root. (The problem guarantees the value is not already present.)

**Complexity:** Time O(h). Space O(1) iterative.

```java
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root == null) return node;

        TreeNode parent = null, current = root;
        while (current != null) {
            parent = current;
            if (val < current.val) current = current.left;
            else                   current = current.right;
        }
        if (val < parent.val) parent.left = node;
        else                  parent.right = node;
        return root;
    }
}
```

---

## Day 13 — Problem 94: Binary Tree Inorder Traversal

Link: https://leetcode.com/problems/binary-tree-inorder-traversal/

**Approach:** Inorder = Left, Node, Right. Shown iteratively with an explicit **stack**: push the entire left spine, pop and visit a node, then move to its right child. (For a BST this produces sorted order.)

**Complexity:** Time O(n). Space O(h) for the stack.

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {        // push the left spine
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();           // visit node
            result.add(current.val);
            current = current.right;         // then go right
        }
        return result;
    }
}
```

---

## Day 13 (Bonus) — Problem 230: Kth Smallest Element in a BST

Link: https://leetcode.com/problems/kth-smallest-element-in-a-bst/

**Approach:** An inorder traversal of a BST visits values in ascending order, so the **k-th visited node** is the k-th smallest. Stop as soon as the counter reaches k.

**Complexity:** Time O(h + k). Space O(h).

```java
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            if (--k == 0) return current.val;  // k-th smallest in inorder
            current = current.right;
        }
        return -1; // k out of range (per constraints, unreachable)
    }
}
```

---

## Day 14 — Problem 98: Validate Binary Search Tree

Link: https://leetcode.com/problems/validate-binary-search-tree/

**Approach:** Recurse with an allowed `(low, high)` open range. Each node's value must be strictly between its bounds; the left child tightens the upper bound, the right child tightens the lower bound. Use `Long` bounds to avoid `Integer.MIN_VALUE`/`MAX_VALUE` edge cases.

**Complexity:** Time O(n). Space O(h) recursion.

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private boolean valid(TreeNode node, long low, long high) {
        if (node == null) return true;
        if (node.val <= low || node.val >= high) return false;
        return valid(node.left, low, node.val)
            && valid(node.right, node.val, high);
    }
}
```

---

## Day 14 — Problem 450: Delete Node in a BST

Link: https://leetcode.com/problems/delete-node-in-a-bst/

**Approach:** Search for the key. Once found:
- If it has no left child → return its right child to its parent (Case 1).
- If it has no right child → return its left child.
- If it has both children → replace its value with the **largest value in the left subtree** (the rightmost node of the left subtree, matching the lecture's Case 2), then recursively delete that value from the left subtree.

**Complexity:** Time O(h). Space O(h) recursion.

```java
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {                                  // found the node
            if (root.left == null)  return root.right;   // Case 1
            if (root.right == null) return root.left;
            // Case 2: replace with rightmost (largest) of the left subtree
            TreeNode rightMost = root.left;
            while (rightMost.right != null) rightMost = rightMost.right;
            root.val = rightMost.val;
            root.left = deleteNode(root.left, rightMost.val);
        }
        return root;
    }
}
```

---

## Resources

- LeetCode "Binary Search Tree" topic tag: https://leetcode.com/tag/binary-search-tree/
- LeetCode Explore — Trees / BST cards: https://leetcode.com/explore/learn/
- Visualgo (interactive BST visualizer): https://visualgo.net/en/bst
- Liang animation referenced in lecture: https://liveexample.pearsoncmg.com/dsanimation/BSTeBook.html
- Course reference: Liang, *Introduction to Java Programming*, 10th Ed., Ch. 25; Carrano & Henry, *Data Structures and Abstractions with Java*, 4th Ed., Ch. 23.
