# Chapter 9 — BST: MCQ (14 Questions)

> All questions are multiple choice (A–D). Answers and explanations are in `ANSWER_KEY.md`.

**Tree T** (used by several questions below) is built by inserting, in this order:
**60, 55, 100, 45, 57, 67, 107**

```
              60
          /        \
        55          100
       /  \        /    \
     45    57    67      107
```

---

**Q1.** In a binary tree, a node may have **at most** how many children?

A. 1
B. 2
C. 3
D. unlimited

---

**Q2.** What is the **depth** of the root node of any tree?

A. 1
B. equal to the height
C. 0
D. equal to the number of nodes

---

**Q3.** Which term describes a node that has **no children**?

A. root
B. sibling
C. leaf
D. parent

---

**Q4.** The **height** of Tree T above is:

A. 0
B. 1
C. 2
D. 3

---

**Q5.** The BST property states that, for **every** node:

A. the left child is a leaf and the right child is the root
B. all values in the left subtree < node < all values in the right subtree (no duplicates)
C. all values in the left subtree > node > all values in the right subtree
D. the tree must be perfectly balanced at all times

---

**Q6.** The **inorder** traversal of Tree T is:

A. 60, 55, 45, 57, 100, 67, 107
B. 45, 57, 55, 67, 107, 100, 60
C. 45, 55, 57, 60, 67, 100, 107
D. 107, 100, 67, 60, 57, 55, 45

---

**Q7.** The **preorder** traversal of Tree T is:

A. 60, 55, 45, 57, 100, 67, 107
B. 45, 55, 57, 60, 67, 100, 107
C. 45, 57, 55, 67, 107, 100, 60
D. 60, 100, 55, 107, 67, 57, 45

---

**Q8.** The **postorder** traversal of Tree T is:

A. 60, 55, 45, 57, 100, 67, 107
B. 45, 57, 55, 67, 107, 100, 60
C. 45, 55, 57, 60, 67, 100, 107
D. 55, 45, 57, 100, 67, 107, 60

---

**Q9.** Which traversal of a **binary search tree** produces the elements in **sorted ascending** order?

A. preorder
B. postorder
C. inorder
D. level-order

---

**Q10.** A **level-order (breadth-first)** traversal is most naturally implemented using which data structure?

A. a stack
B. a queue
C. a priority queue
D. a hash table

---

**Q11.** The level-order (breadth-first) traversal of Tree T is:

A. 60, 55, 100, 45, 57, 67, 107
B. 45, 55, 57, 60, 67, 100, 107
C. 60, 100, 55, 107, 67, 57, 45
D. 45, 57, 55, 67, 107, 100, 60

---

**Q12.** The time complexity of **search** and **insert** in a BST is best described as:

A. always O(1)
B. always O(log n) regardless of shape
C. O(h): O(log n) when balanced, O(n) when skewed
D. always O(n)

---

**Q13.** A BST is built by inserting values in **strictly increasing** order: 10, 20, 30, 40, 50. What is the resulting search/insert complexity, and why?

A. O(log n), because a BST is always balanced
B. O(n), because the tree degenerates into a right-skewed chain
C. O(1), because the values are sorted
D. O(n log n), because of the sorting

---

**Q14.** To delete a node that **has a left child** from a BST, the standard algorithm:

A. simply connects the parent to the node's right child
B. replaces the node's value with the **rightmost node of its left subtree**, then deletes that rightmost node
C. replaces the node's value with the leftmost node of its right subtree, then deletes the root
D. deletes the entire left subtree
