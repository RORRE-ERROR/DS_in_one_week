# Chapter 4 — Stack (ANSWER KEY)

> Letter + explanation for each MCQ. Postfix questions show the full stack trace.

---

**Q1 — B (LIFO, Last-In First-Out).**
The last element pushed is the first one popped. (FIFO is a queue.)

---

**Q2 — C (one end called the `top`).**
A stack is a restricted list: insert, delete, and peek all occur only at the `top`.

---

**Q3 — C (`pop()`).**
`pop()` removes and returns the top. `peek()`/`top()` only view it; `push()` adds.

---

**Q4 — B.**
`pop()` removes **and** returns the top element; `peek()` returns the top **without removing** it.

---

**Q5 — B (ArrayList — end operations, no shifting).**
A stack only ever inserts/deletes at the end, and ArrayList add/remove at the end is O(1) with no shifting, so it is **more efficient than a linked list** for a stack. (A linked list's cheap *front* operations are irrelevant since a stack never touches the front. Queue is the structure that prefers a LinkedList.)

---

**Q6 — C (throw `EmptyStackException`).**
`pop()`/`peek()` require at least one element:
```java
if (isEmpty()) throw new EmptyStackException();
```

---

**Q7 — B.**
Composition stores the ArrayList as a **private field** and exposes only push/pop/peek/isEmpty, so it does not leak unrelated ArrayList methods (e.g. `add(index, ...)`). That encapsulation is why it is preferred over inheritance.

---

**Q8 — B (`D, A` from top to bottom).**
Trace:
```
push A            [A]
push B            [A, B]
push C            [A, B, C]
pop  -> C out     [A, B]
pop  -> B out     [A]
push D            [A, D]
```
List bottom→top is `A, D`, so **top→bottom = D, A**.

---

**Q9 — C (after its operands).**
That is the definition of postfix / Reverse Polish Notation.

---

**Q10 — D.**
A, B, C are the real advantages (no precedence rules, no parentheses, fewer memory accesses). Postfix is generally **harder** for humans to read, so "easier to read" is NOT an advantage.

---

**Q11 — B (1).**
`6 2 3 + -`:
```
push 6                          [6]
push 2                          [6, 2]
push 3                          [6, 2, 3]
'+': x=pop=3, y=pop=2 -> 2+3=5  [6, 5]
'-': x=pop=5, y=pop=6 -> 6-5=1  [1]
Result = 1
```
Note the `-` order: `y - x = 6 - 5 = 1` (first popped is the RIGHT operand).

---

**Q12 — C (19).**
`4 3 5 * +`:
```
push 4                            [4]
push 3                            [4, 3]
push 5                            [4, 3, 5]
'*': x=pop=5, y=pop=3 -> 3*5=15   [4, 15]
'+': x=pop=15, y=pop=4 -> 4+15=19 [19]
Result = 19
```

---

**Q13 — A (2).**
`10 2 / 3 -` — order matters for both `/` and `-`:
```
push 10                            [10]
push 2                             [10, 2]
'/': x=pop=2, y=pop=10 -> 10/2=5   [5]
push 3                             [5, 3]
'-': x=pop=3, y=pop=5  -> 5-3=2    [2]
Result = 2
```
First popped is the RIGHT operand, so `10 / 2 = 5` then `5 - 3 = 2` — NOT `2/10` or `3-5`.

---

**Q14 — A.**
`9 8 + 7`:
```
push 9          [9]
push 8          [9, 8]
'+': 9+8=17     [17]
push 7          [17, 7]   <- end of expression, 2 elements left
```
At the end the stack has **more than one element** → **too many operands** error. (`6 2 3 + -`, `4 3 5 * +`, and `2 3 +` are all valid.)
