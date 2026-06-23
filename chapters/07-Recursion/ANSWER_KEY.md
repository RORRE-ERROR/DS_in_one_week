# Chapter 7 — Recursion (ANSWER KEY)

---

**Q1 — B.** Recursion is a method that calls itself (self-invocation) to solve a smaller version of the same problem. It is repetition without a loop.

**Q2 — B.** Every recursive method needs a **base case** (stopping condition) and a **recursive case** (calls itself on smaller input). Loops/try-catch/interfaces are unrelated requirements.

**Q3 — B.** The base case is the simplest scenario, solved **directly**, and serves as the **stopping condition** that prevents infinite recursion. It is not optional.

**Q4 — B.** `0! = 1` (base case) and `n! = n * (n-1)!` (recursive case). A is wrong (0! ≠ 0); the others don't define factorial.

**Q5 — C.** With no base case, the problem never reduces to a stopping condition. The call stack grows without bound until the OS limit is exceeded → **`StackOverflowError`**, which crashes the program. (It compiles fine.)

**Q6 — B.** Each recursive call pushes a **new stack frame** holding its parameters and local variables — extra memory. Iteration reuses the same frame, so it uses no extra space per step.

**Q7 — B.** Counting from index 0: `fib(0)=0, fib(1)=1, fib(2)=1, fib(3)=2, fib(4)=3, fib(5)=5, fib(6)=8, fib(7)=13`. Answer = **13**.

**Q8 — B (537).** The method recurses *before* printing, so digits print most-significant first:
```
mystery(537): recurse mystery(53), then print 537 % 10 = 7
  mystery(53): recurse mystery(5), then print 53 % 10 = 3
    mystery(5): recurse mystery(0), then print 5 % 10 = 5
      mystery(0): n <= 0 → return (base case, prints nothing)
    prints 5
  prints 3
prints 7
```
Print order (unwinding): **5, 3, 7 → "537"**.

**Q9 — C.** The base case is `n <= 0` — when reached, the method returns immediately without recursing.

**Q10 — B (10).** This sums `n + (n-1) + ... + 0`:
```
mystery2(4) = 4 + mystery2(3)
            = 4 + (3 + mystery2(2))
            = 4 + (3 + (2 + mystery2(1)))
            = 4 + (3 + (2 + (1 + mystery2(0))))   // base case returns 0
            = 4 + 3 + 2 + 1 + 0
            = 10
```

**Q11 — B (3).** Trace:
```
fib(4) = fib(3) + fib(2)
       = (fib(2) + fib(1)) + (fib(1) + fib(0))
       = ((fib(1) + fib(0)) + fib(1)) + (fib(1) + fib(0))
       = ((1 + 0) + 1) + (1 + 0)
       = (2) + (1)
       = 3
```

**Q12 — B.** Recursion and loops are **interchangeable** in principle — anything done with one can be done with the other. A is false (no extra power), C/D are false.

**Q13 — B.** A directory contains files and subdirectories, and each subdirectory is itself a directory → the structure is **inherently recursive**, so recursion is the natural (and clean) solution.

**Q14 — B.** Recursion stops at a **base case** and consumes **extra stack memory** per call; iteration stops when its **condition is false** and needs **no extra memory**. The other options swap or confuse these facts.
