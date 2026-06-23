# Chapter 9 — BST: Answer Key

**Reference Tree T** (insert order 60, 55, 100, 45, 57, 67, 107):

```
              60
          /        \
        55          100
       /  \        /    \
     45    57    67      107
```

- Inorder:   45, 55, 57, 60, 67, 100, 107
- Preorder:  60, 55, 45, 57, 100, 67, 107
- Postorder: 45, 57, 55, 67, 107, 100, 60
- Level-order: 60, 55, 100, 45, 57, 67, 107
- Height: 2

---

**Q1 — B (2).** A binary tree node has **at most two** children (left and right). That is the definition of "binary."

**Q2 — C (0).** Depth = number of edges from the root to the node. The root is 0 edges from itself, so its depth is **0**.

**Q3 — C (leaf).** A **leaf** is a node with no children. The root is the top node; siblings share a parent; a parent has at least one child.

**Q4 — C (2).** The longest root-to-leaf path in Tree T has 2 edges (e.g. 60→55→45 or 60→100→67). Height = **2**.

**Q5 — B.** BST property: for **every** node, all left-subtree values < node < all right-subtree values, with no duplicates. C reverses the order; D (balance) is not required by the BST property.

**Q6 — C.** **Inorder = Left, Node, Right.** Visiting Tree T this way gives `45, 55, 57, 60, 67, 100, 107` — sorted ascending. (A is preorder, B is postorder, D is reverse-sorted.)

**Q7 — A.** **Preorder = Node, Left, Right** → root first: visit 60, then left subtree (55, 45, 57), then right subtree (100, 67, 107) → `60, 55, 45, 57, 100, 67, 107`.

**Q8 — B.** **Postorder = Left, Right, Node** → root last: left subtree (45, 57, 55), right subtree (67, 107, 100), then root 60 → `45, 57, 55, 67, 107, 100, 60`.

**Q9 — C (inorder).** For a BST, **inorder traversal yields the values in sorted ascending order** — the single most common BST MCQ fact.

**Q10 — B (a queue).** Level-order / breadth-first visits nodes level by level; you enqueue the root, then repeatedly dequeue a node and enqueue its children. (A stack would give depth-first behavior.)

**Q11 — A.** Level by level: level 0 = 60; level 1 = 55, 100; level 2 = 45, 57, 67, 107 → `60, 55, 100, 45, 57, 67, 107` (which equals the insertion order here).

**Q12 — C.** Both search and insert walk one root-to-leaf path, so they are **O(h)**: O(log n) for a balanced tree, **O(n)** for a skewed tree.

**Q13 — B (O(n)).** Inserting strictly increasing values makes every new node a right child, producing a right-skewed chain of height n−1. Operations degrade to **O(n)**. (A BST is NOT automatically balanced.)

**Q14 — B.** Case 2 of BST deletion: when the node has a left child, copy the value of the **rightmost node of the left subtree** (the largest value in the left subtree) into the node, then delete that rightmost node. (A is Case 1 — no left child.)
