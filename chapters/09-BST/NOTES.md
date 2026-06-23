# Chapter 9 — Binary Search Tree (BST)

## What is this chapter about? (read me first)

A **Binary Search Tree (BST)** is a way of storing values so you can **find, add, and remove them quickly** — without scanning everything.

**The big idea — like a guess-the-number game.** Imagine I'm thinking of a number and you have to guess it. A smart strategy is: guess the middle, and if you're told "too high," ignore the whole upper half; if "too low," ignore the lower half. Each guess throws away half the possibilities. A BST stores numbers in exactly that shape so the computer can do the same thing.

**Another picture — a well-organized family tree.** Every value sits at a spot. From any spot, **smaller values live down-and-to-the-left**, **bigger values live down-and-to-the-right**. To find a value, you keep moving in *one* direction (left or right) — you never have to look back. That's why it's fast.

It's basically **binary search, but shaped like a tree** instead of done on a sorted array.

This chapter covers: tree vocabulary, the BST rule, how to **search / insert / delete**, the three **traversals** (the way you "walk" the whole tree), and **why speed depends on the tree's shape**.

---

## 1. Tree Terminology (★ definitions)

### What & why
Before anything else you need the words people use to talk about trees. Let's anchor them to a picture. Here is a small tree:

```
            60          ← root (the top node; depth 0)
          /    \
        55      100     ← these are 60's children; 55 and 100 are siblings
       /  \
     45    57           ← 45 and 57 are leaves (no children)
```

Read it top-down: the **root** is at the top, lines (**edges**) go *down* to **children**.

- **Binary tree:** a hierarchical structure where each node has **at most two children** (a left subtree and a right subtree). It is either **empty**, or consists of a **root** plus a left subtree and a right subtree (either may be empty).
- **Root:** the single node at the very top. Everything hangs off it.
- **Parent / child:** if an edge goes from node A *down* to node B, then A is B's **parent** and B is A's **child**. (Above: 60 is the parent of 55 and 100.)
- **Subtree:** any node *together with* everything hanging below it. (55, 45, 57 together form 60's *left subtree*.)
- **Path length** = number of **edges** in a path (e.g. path 60→45 has 2 edges → length 2).
- **Depth** of a node = path length (edges) from the **root** to that node. The **root has depth 0**.
- **Level** = the set of **all nodes at a given depth** (e.g. level 1 = the root's two children).
- **Siblings** = nodes that share the **same parent**.
- **Leaf** = a node with **no children** (the "dead ends" at the bottom — 45 and 57 above).
- **Height** of a tree = the length (in edges) of the **longest path from the root to a leaf**.

> 💡 **Quick way to remember depth vs height:** *depth* is measured **going down from the root** to a node; *height* is the **longest** of all those root-to-leaf distances.

🧠 **Quick check:** In the tree above, what is the depth of node 45, and what is the height of the whole tree?
<details><summary>Answer</summary>Depth of 45 = 2 (edges 60→55→45). Height = 2 (the longest root-to-leaf path).</details>

## 2. BST Property (★)

### What & why
This one rule is what makes the tree *searchable*. It's the "smaller goes left, bigger goes right" promise — and it must be true **everywhere**, not just at the top.

> For **EVERY** node in the tree: **all values in its left subtree < the node's value < all values in its right subtree**, and there are **no duplicates**.

This must hold at every node — not just the root.

**Why this matters for speed.** Because of this rule, when you're looking for a value and it's smaller than the current node, you know it *cannot* be on the right — so you safely throw away the entire right subtree and go left. Every step halves the search (when the tree is nicely balanced). That's the guess-the-number game again.

```
            60
          /    \
       <60      >60      ← everything left of 60 is smaller, everything right is bigger
```

🧠 **Quick check:** Could the value 70 ever appear in 60's *left* subtree?
<details><summary>Answer</summary>No. 70 > 60, so it must go to the right of 60. The left subtree only holds values < 60.</details>

## 3. Node Class

### What & why
A tree is built from **nodes** linked together. Each node holds one value (`element`) and two references — its **left** child and its **right** child. If a child doesn't exist, that reference is `null`. The whole tree is reached through a single `root` reference.

```java
class TreeNode<E> {
    E element;
    TreeNode<E> left;
    TreeNode<E> right;

    public TreeNode(E o) { element = o; }
}

// class BST<E extends Comparable<E>> { protected TreeNode<E> root; protected int size; }
```

> **Reading the generics:** `<E extends Comparable<E>>` just means "E is some type we can compare," so we can ask "is this value smaller or bigger?" using `compareTo`. (`a.compareTo(b)` returns a **negative** number if a < b, **0** if equal, **positive** if a > b.)

## 4. Search (O(h))

### What & why
**Goal:** answer "is this value in the tree?" Start at the root and keep moving in one direction: smaller → left, bigger → right, equal → found. If you walk off the bottom (`current == null`), it isn't there.

**Step-by-step: search for 57** in our tree:

```
            60        1) 57 < 60 → go LEFT
          /    \
        55      100   2) 57 > 55 → go RIGHT
       /  \
     45    57         3) 57 == 57 → FOUND ✓
```

Just 3 comparisons — we never even looked at 100, 45.

```java
public boolean search(E element) {
    TreeNode<E> current = root;               // start at the root
    while (current != null) {
        if (element.compareTo(current.element) < 0)
            current = current.left;           // smaller → go left
        else if (element.compareTo(current.element) > 0)
            current = current.right;          // larger  → go right
        else
            return true;                      // equal   → found
    }
    return false;                             // fell off the tree → not found
}
```

## 5. Insert (O(h))

### What & why
Inserting is just **searching for where the value *would* be**, then attaching it there. You walk down (smaller → left, bigger → right) while remembering the last node you stood on (the `parent`). When you fall off the tree, you hook the new node onto that parent.

- If the tree is **empty** → the new element becomes the **root**.
- Otherwise, walk down tracking a `parent`: go **left if smaller**, **right if larger**.
- When you fall off (`current == null`), attach the new node as `parent`'s left or right child.
- A **duplicate is NOT inserted** (return `false`).

**Step-by-step: insert 67** into our tree:

```
            60        1) 67 > 60 → go RIGHT (parent = 60)
          /    \
        55      100   2) 67 < 100 → go LEFT (parent = 100)
       /  \
     45    57         3) fell off (100 has no left child)
                         → attach 67 as 100's LEFT child
```

Result: 67 becomes the left child of 100.

```java
public boolean insert(E e) {
    if (root == null) { root = new TreeNode<>(e); }
    else {
        TreeNode<E> parent = null, current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0)      { parent = current; current = current.left; }
            else if (e.compareTo(current.element) > 0) { parent = current; current = current.right; }
            else return false;                          // duplicate → not inserted
        }
        if (e.compareTo(parent.element) < 0) parent.left  = new TreeNode<>(e);
        else                                 parent.right = new TreeNode<>(e);
    }
    size++;
    return true;
}
```

## 6. Tree Traversals (★★ — guaranteed exam question)

### What & why
A **traversal** is a recipe for **visiting every node exactly once**, in some order. The three classic ones differ only in *when you visit the node itself* relative to its left and right subtrees.

**The trick to remember them:** the word ("**in**order", "**pre**order", "**post**order") tells you when the **N**ode is visited:
- **pre** = node **first** (before its children)
- **in** = node **in between** (left, then node, then right)
- **post** = node **last** (after its children)

In all three, you always do **left subtree before right subtree** — only the node's position moves.

**How to read this table:** the "Order" column is the exact sequence you do for *every* node.

| Traversal | Order | Root visited | Notes |
|---|---|---|---|
| **Inorder** | **Left, Node, Right** | middle | For a **BST → SORTED ASCENDING order** |
| **Preorder** | **Node, Left, Right** | **first** | a.k.a. depth-first; root printed first |
| **Postorder** | **Left, Right, Node** | **last** | root printed last |
| **Level-order** | level by level (breadth-first) | — | uses a **QUEUE** |

**Mnemonic with `1 + 2`:** inorder = `1 + 2`, preorder = `+ 1 2` (root first), postorder = `1 2 +` (root last).

> ⭐ **The one fact examiners love:** doing an **inorder** traversal of a BST spits the values out **sorted from smallest to largest**. (Makes sense: at each node you take everything-smaller first, then the node, then everything-bigger.)

### Verified Worked Example

Insert in order: **60, 55, 100, 45, 57, 67, 107**. This builds:

```
              60
          /        \
        55          100
       /  \        /    \
     45    57    67      107
```

- **Inorder:**   `45, 55, 57, 60, 67, 100, 107`   ← sorted ascending!
- **Preorder:**  `60, 55, 45, 57, 100, 67, 107`   ← root (60) first
- **Postorder:** `45, 57, 55, 67, 107, 100, 60`   ← root (60) last
- **Height:**    `2`   (longest root-to-leaf path has 2 edges, e.g. 60→55→45)

**Walking the inorder traversal (Left, Node, Right) so you can see *why* it comes out sorted:**
1. Go all the way left from 60 → 55 → **45** (45 has no left child) → visit **45**.
2. Back up to **55**, visit it, then its right child **57**.
3. Back up to **60**, visit it (the root, sitting in the middle).
4. Now the right side: go into 100, all the way left to **67**, visit it.
5. Visit **100**, then its right child **107**.
   → `45, 55, 57, 60, 67, 100, 107` ✓ sorted.

🧠 **Quick check:** Which traversal would you use to print a BST's values in sorted order, and which uses a queue?
<details><summary>Answer</summary>Inorder gives sorted order. Level-order (breadth-first) uses a queue.</details>

## 7. Deletion (★ — two cases)

### What & why
Deleting is the trickiest operation, because you can't just rip a node out — its children would be orphaned. The strategy is to **patch the tree so the BST rule still holds**. First **locate** the target node (`current`) and its `parent`. Then there are two cases.

- **Case 1 — `current` has NO left child:** connect the parent directly to `current`'s **right child** (which may be `null`). If `current` is the root, the right child becomes the new root.

  *(Intuition: with no left child, the right subtree can just slide up into current's spot — it's already all-bigger-than-the-parent-side it needs to be.)*

- **Case 2 — `current` HAS a left child:** find the **rightmost node of the left subtree** (the **largest** value in the left subtree). **Copy that value into `current`**, then **delete the rightmost node** (connect its parent to the rightmost node's **left child** — the rightmost node has **no right child**, but may have a left child).

  *(Intuition: the largest value in the left subtree is the closest value still smaller than current. Putting it in current's place keeps everything-left smaller and everything-right bigger — the BST rule survives.)*

> **Why "rightmost of the left subtree"?** Keep going right and you always get bigger; the rightmost node of the left subtree is the biggest value that's still less than `current` — the perfect stand-in. (It can't have a right child, or that child would be even bigger.)

## 8. Complexity

### What & why — shape is everything
Every operation here (search / insert / delete) walks **down one path** from the root, so its cost is the tree's **height `h`**. The question is just: how tall is the tree?

**Balanced tree (good):** branches spread out evenly, so `h ≈ log n`. Each step roughly halves what's left — fast, just like the guess-the-number game.

```
        60
      /    \
    55      100      h ≈ log n  → fast
   / \      /  \
  45  57   67  107
```

**Degenerate / skewed tree (bad):** if you insert already-sorted values (10, 20, 30, 40...), every node only goes one way. The tree becomes a **"stick"** — basically a linked list — so `h ≈ n` and you lose all the speed.

```
  10
    \
     20
       \
        30          h ≈ n  → slow (no halving; you visit everything)
          \
           40
```

**How to read this table:** "Balanced" is the typical good case; the last column is the worst case when the tree is a stick.

| Operation | Balanced tree | Worst / skewed (degenerate) tree |
|---|---|---|
| search | O(log n) | **O(n)** |
| insert | O(log n) | **O(n)** |
| delete | O(log n) | **O(n)** |

All three are **O(h)** where `h` is the height. A balanced tree has `h ≈ log n`; a **skewed** tree (basically a linked list) has `h ≈ n`.

🧠 **Quick check:** You insert 1, 2, 3, 4, 5 into an empty BST in that order. What shape do you get, and what's the search cost?
<details><summary>Answer</summary>A right-leaning stick (degenerate tree). Search is O(n) — no faster than a linked list.</details>

---

## ★ Exam Tips

- **Inorder of a BST = SORTED ASCENDING.** (Most common BST MCQ.)
- **Preorder = root FIRST**; **Postorder = root LAST**; Inorder = root in the middle.
- **Level-order / breadth-first uses a QUEUE.** (DFS-style preorder uses a stack/recursion.)
- **search / insert / delete = O(h):** O(log n) when balanced, **O(n) when skewed**.
- BST property holds at **every** node: entire left subtree < node < entire right subtree, **no duplicates**.
- **Depth of root = 0**; **height = edges on the longest root-to-leaf path**; **leaf = no children**.
- **Deletion:** no left child → link parent to the **right** child; has left child → replace with the **rightmost node of the left subtree** (largest in left subtree).
