# Chapter 7 — Recursion (NOTES)

## What is this chapter about? (read me first)

**Recursion** is just a method that *calls itself*. That sounds strange at first — a method using its own name inside its own body — but the idea is simple once you see the picture.

> **Russian nesting dolls 🪆** — You open a doll and find a *smaller identical doll* inside. You open that one and find an even smaller one. You keep going until you reach the **tiniest solid doll** that can't be opened. That tiniest doll is the **base case**. Opening each doll to reach the next is the **recursive case**.

Recursion solves a big problem by **shrinking it into a smaller version of the same problem**, over and over, until the problem is so small you can answer it instantly.

By the end of this chapter you should be able to:
- Explain what recursion is and name its two parts.
- Read a recursion *trace* by hand (the kind exams love to ask).
- Know why a missing base case crashes with `StackOverflowError`.
- Compare recursion with loops (iteration).

---

## Definition

- **Recursion = a method that calls itself** (also called **self-invocation**) to solve a smaller version of the same problem.
- It is an alternative form of program control — **repetition without a loop**. (A *loop* like `for`/`while` repeats by looping back; recursion repeats by re-calling itself.)

---

## The TWO essential parts (every recursive method needs both)

Think of standing in a long cinema queue 🎬. You can't see the screen, so you tap the person in front and ask *"what row number am I in?"* They don't know either, so they ask the person in front of *them*... and so on.

- Eventually the question reaches the **front person**, who can see they're in **row 1** — they answer immediately. That instant answer is the **base case**.
- Everyone else just takes the answer they hear and adds 1 before passing it back. That "ask the person ahead" step is the **recursive case**.

Every recursive method needs **both** of these:

1. **Base Case** — the simplest scenario, solved **directly** without calling itself again. It is the **stopping condition** that prevents infinite recursion. *(The front person who just knows the answer.)*
2. **Recursive Case** — the method calls **itself** on a **smaller / simpler** input. Each recursive call must move the problem **closer to the base case**. *(Each person passing the question forward.)*

> If either part is missing or wrong (the base case is never reached), recursion never stops — and your program crashes (see `StackOverflowError` below).

> **🧠 Quick check:** What goes wrong if the recursive case makes the input *bigger* instead of smaller?
> *Answer:* It moves *away* from the base case, so the base case is never hit → infinite recursion → `StackOverflowError`.

---

## Classic example 1 — Factorial

**What & why:** `n!` ("n factorial") means `n × (n-1) × (n-2) × ... × 1`. For example `4! = 4×3×2×1 = 24`. Notice that `4!` is just `4 × 3!`, and `3!` is just `3 × 2!`... each factorial is built from a *smaller* factorial. That "smaller version of the same problem" pattern is exactly what recursion is made for.

Recursive definition:
```
0! = 1                 // Base Case
n! = n * (n - 1)!      // Recursive Case (n > 0)
```

```java
public static long factorial(int n) {
    if (n == 0) return 1;          // base case
    return n * factorial(n - 1);   // recursive case
}
```

### How to read a trace

A **trace** shows every step the method takes. Read it like this:
- **Calls expand downward.** Each line breaks one call into a multiplication times a *smaller* call, because `factorial(n)` can't finish until `factorial(n-1)` gives it an answer.
- **The base case is the turning point.** When we hit `factorial(0)`, it returns `1` directly — no more expanding.
- **Results combine back up.** Once we have the bottom value, we plug it back in and multiply our way upward to the final answer.

**Trace of `factorial(4)`** (calls go *down*, results come back *up*):
```
factorial(4) = 4 * factorial(3)
             = 4 * (3 * factorial(2))
             = 4 * (3 * (2 * factorial(1)))
             = 4 * (3 * (2 * (1 * factorial(0))))   // base case hit: returns 1
             = 4 * (3 * (2 * (1 * 1)))
             = 4 * (3 * (2 * 1))
             = 4 * (3 * 2)
             = 4 * 6
             = 24
```

The top half (going down) is the "asking" phase; the bottom half (coming up) is the "answering" phase.

---

## Classic example 2 — Fibonacci

**What & why:** The Fibonacci series starts with `0` and `1`, and every number after that is the **sum of the two before it**. So `0+1=1`, `1+1=2`, `1+2=3`, and so on. Because each value depends on two smaller values, it's naturally recursive.

Series (0-based index): `0 1 1 2 3 5 8 13 21 34 55 89 ...`
```
index: 0 1 2 3 4 5 6  7  8  9  10 11
```
Recursive definition (note: this one has **two** base cases):
```
fib(0) = 0                          // Base Case
fib(1) = 1                          // Base Case
fib(n) = fib(n-1) + fib(n-2)        // Recursive Case (n >= 2)
```

```java
public static long fib(int n) {
    if (n < 2) return n;               // base cases: fib(0)=0, fib(1)=1
    return fib(n - 1) + fib(n - 2);    // recursive case
}
```

**Trace of `fib(3)`** — same idea: expand down to the base cases, then add back up:
```
fib(3) = fib(2) + fib(1)
       = (fib(1) + fib(0)) + fib(1)
       = (1 + 0) + fib(1)
       = 1 + 1
       = 2
```
Useful values: `fib(4)=3`, `fib(6)=8`, `fib(7)=13`, `fib(10)=55`.

> **🧠 Quick check:** Why does `fib` need *two* base cases?
> *Answer:* The recursive case looks back **two** steps (`fib(n-1)` and `fib(n-2)`), so you must stop at both `fib(1)` and `fib(0)`, otherwise `fib(1)` would try to compute `fib(-1)`.

---

## Infinite recursion → StackOverflowError ★

**The slow version of why this happens:** Every time a method is called — including a recursive call — the computer sets aside a small block of memory called a **stack frame** to hold that call's parameters and local variables. These frames pile up on top of each other on the **call stack**. A frame is only removed when its call *finishes and returns*.

In normal recursion, the base case lets the deepest call finish, so frames start getting removed and the pile shrinks back down. But with **no base case**, no call ever finishes — so the pile only ever grows:

```
            ... (never stops growing) ...
   ┌──────────────────────┐
   │ factorial(-2)         │   ← still waiting
   ├──────────────────────┤
   │ factorial(-1)         │   ← still waiting
   ├──────────────────────┤
   │ factorial(0)          │   ← no base case, so it keeps going
   ├──────────────────────┤
   │ factorial(1)          │   ← still waiting
   ├──────────────────────┤
   │ factorial(2)          │   ← still waiting for factorial(1)
   └──────────────────────┘
        call stack fills up → JVM throws StackOverflowError
```

- If the base case is **never reached**, the problem is not reduced toward the base case → **infinite recursion**.
- The **call stack keeps growing** forever. The OS limits the stack height, so when a program's stack exceeds that limit the JVM throws a **`StackOverflowError`**, which typically **crashes the program**.

```java
public static long factorial(int n) {
    return n * factorial(n - 1);   // NO base case → runs forever → StackOverflowError
}
```

---

## Recursion vs Iteration ★

**What & why:** *Iteration* is the loop way of repeating (`for`, `while`). *Recursion* is the self-calling way. Both can solve the same problems — this table shows how they differ.

**How to read this:** each row is one point of comparison; the middle column is recursion, the right column is the loop equivalent.

| | **Recursion** | **Iteration (loop)** |
|---|---|---|
| Stops when | the **base case** is reached | the loop **condition is false** |
| Memory | **each call needs extra stack space** (a new stack frame for params/locals) | **no extra space** per step |
| If it never ends | **StackOverflowError** | infinite loop runs forever, **no extra memory** created |

- Recursion and loops are **interchangeable**: anything done with a loop can be done with recursion and vice-versa.
- Factorial with a loop (same result, no recursion):
```java
public static long factorialLoop(int n) {
    long result = 1;
    while (n > 0) { result *= n; n--; }
    return result;
}
```

---

## Call stack / memory note

**Plain English:** the "call stack" is the computer's stack of unfinished method calls. Picture stacking plates: the last call you make sits on top, and it must be cleared off *before* the call beneath it can continue.

- Each recursive call places a **new stack frame** on the call stack holding that call's **parameters and local variables**.
- A call is **temporarily suspended** until the call it made completes; the **deepest call (base case) completes first**, then results unwind back up. *(Last in, first out — just like plates.)*
- This gives recursion **substantial overhead**: the system must allocate space for every call's locals/parameters, consuming memory and extra time to manage it.

---

## When to use recursion

**Rule of thumb:** use recursion when the problem is *itself* defined in terms of smaller copies of itself — then the recursive code reads almost like the definition. Use a loop when the task is simple counting/accumulation and you want to avoid the memory overhead.

- Best for problems that are **inherently recursive**, where the recursive solution is **cleaner / simpler** than a loop:
  - **trees** (traversal, depth)
  - **directory size** (size = sum of file sizes + sizes of subdirectories, defined recursively)
  - **H-trees** (VLSI clock distribution networks)
- For simple counting/accumulation, a loop is often more efficient (no stack overhead).

---

## ★ Exam Tips
- Every recursive method has **exactly two ingredients: base case + recursive case** — be ready to **identify the base case** in given code.
- **No base case / base case never reached → `StackOverflowError`** (not an infinite loop, not "wrong answer" — it crashes).
- **Each recursive call uses extra stack memory** (a new frame). Iteration does **not**.
- Know the Fibonacci series by heart: `0 1 1 2 3 5 8 13 21 34 55`. `fib(7) = 13`.
- For trace questions, write the unwinding by hand: calls expand **down**, values combine **back up**.
- Recursion and iteration are **interchangeable in principle**; recursion has overhead but is cleaner for trees/directories/H-trees.
