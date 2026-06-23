# Chapter 4 — Stack (MCQ)

> 14 questions. Choose the single best answer (A–D). Answers and full traces in `ANSWER_KEY.md`.

---

**Q1.** A stack stores data in which order?
- A. FIFO (First-In, First-Out)
- B. LIFO (Last-In, First-Out)
- C. Sorted ascending order
- D. Random order

---

**Q2.** In a stack, all insertions and deletions happen at:
- A. the front (head)
- B. both ends
- C. one end called the `top`
- D. any index chosen by the user

---

**Q3.** Which method removes **and returns** the top element of a stack?
- A. `peek()`
- B. `top()`
- C. `pop()`
- D. `push()`

---

**Q4.** What is the difference between `peek()` and `pop()`?
- A. `peek()` removes the top, `pop()` does not
- B. `pop()` removes and returns the top; `peek()` returns the top without removing it
- C. They are identical
- D. `peek()` works only on an empty stack

---

**Q5.** Which data structure is **most efficient** for implementing a stack, and why?
- A. A linked list, because front insertion is O(1)
- B. An array list, because all insertions/deletions happen at the end (no shifting)
- C. A binary search tree, because lookups are O(log n)
- D. A priority queue, because the top has the highest priority

---

**Q6.** Calling `pop()` or `peek()` on an empty stack should:
- A. return `null`
- B. return `0`
- C. throw an `EmptyStackException`
- D. silently do nothing

---

**Q7.** Two ways to design a `GenericStack` class with an `ArrayList` are inheritance and composition. The **preferred** design is composition because:
- A. it runs faster at the CPU level
- B. it keeps the ArrayList as a private field and exposes only stack operations (push/pop/peek)
- C. it allows generic exceptions
- D. inheritance cannot be used with generics

---

**Q8.** The following operations are performed on an initially empty stack:
`push A`, `push B`, `push C`, `pop`, `pop`, `push D`.
After all operations, the stack from **top to bottom** is:
- A. `A, D`
- B. `D, A`
- C. `D, C, A`
- D. `A, B, D`

---

**Q9.** In postfix (Reverse Polish Notation), an operator appears:
- A. before its operands
- B. between its operands
- C. after its operands
- D. only inside parentheses

---

**Q10.** Which is **NOT** an advantage of postfix notation?
- A. No precedence rules are needed
- B. No parentheses are needed
- C. Fewer computer memory accesses
- D. It is easier for humans to read than infix

---

**Q11.** Evaluate the postfix expression `6 2 3 + -`. The result is:
- A. 7
- B. 1
- C. -1
- D. 5

---

**Q12.** Evaluate the postfix expression `4 3 5 * +`. The result is:
- A. 35
- B. 23
- C. 19
- D. 12

---

**Q13.** Evaluate the postfix expression `10 2 / 3 -`. (Watch the operand order for `/` and `-`.) The result is:
- A. 2
- B. 8
- C. -2
- D. 5

---

**Q14.** Which postfix expression contains an error, and what kind?
- A. `9 8 + 7` — too many operands (more than one element left at the end)
- B. `6 2 3 + -` — too few operands
- C. `4 3 5 * +` — too many operators
- D. `2 3 +` — too many operands
