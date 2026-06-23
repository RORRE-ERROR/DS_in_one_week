# Chapter 4 — Stack (NOTES)

> WIA1002 Data Structure. Final exam = all MCQ: concept, postfix-evaluation trace, "which is most efficient", push/pop sequence trace.

---

## What is this chapter about? (read me first)

A **stack** is one of the simplest data structures, and it shows up everywhere — undo buttons, the "back" button, and even how Java runs your methods. The whole idea fits in one rule:

> **You can only touch the TOP.** Add to the top, remove from the top, peek at the top. That's it.

Think of a **stack of plates** in a cafeteria, or a **Pringles can / a can of tennis balls**:

```
   ___
  | C |   <- you can ONLY take this one (the top)
  | B |
  | A |   <- this was put in FIRST, comes out LAST
  |___|
```

The **last** plate you put on is the **first** one you take off. That single fact is called **LIFO = Last In, First Out.** If you remember nothing else from this chapter, remember the can of Pringles. 🍟

In this chapter you'll learn:
- the LIFO concept and the 5 operations (`push`, `pop`, `peek`, `isEmpty`, `getSize`),
- how to build a stack in Java (and why we use an `ArrayList`),
- the big applications — especially **postfix expression evaluation** (a guaranteed exam trace).

---

## 1. Concept — LIFO

**What & why.** A stack restricts a normal list so you can only work at one end. Why throw away flexibility on purpose? Because that restriction makes the structure *fast and predictable*, and it perfectly matches problems that are naturally "last thing first" — like undoing your most recent action.

The one open end is called the **`top`**.

- A **stack** is a special, restricted type of **list** where elements are accessed, inserted, and deleted **only from one end**, called the **`top`**.
- It holds data in a **LIFO** (**Last-In, First-Out**) fashion: the last item pushed is the first popped.
- A stack is an **adapter** — it exposes a *restricted set* of list methods. ("Adapter" just means: it wraps a fuller structure and only hands you the few buttons it wants you to press.)

**Picture the two basic moves** (the top is always the open end):

```
PUSH C onto [A, B]            POP from [A, B, C]
                              (returns C, stack shrinks)

  top -> C  (new)               top -> C        top -> B
         B                             B   --->        A
         A                             A
                                     pop() = C
```

Pushing A, then B, then C gives:

```
push order:  A, B, C            top -> C
                                       B
                                       A   <- bottom
```

🧠 **Quick check.** You push 1, 2, 3 then pop once. What comes out?
*Answer: 3 — it was the last one in. (LIFO!)*

---

## 2. Operations

**What & why.** A stack only needs five operations. Each one does its job at the top, so each one is instant — **O(1)** (constant time: it does NOT matter how big the stack is, the work is the same).

**How to read this table:** column 1 is the method name you call, column 2 is what it does, column 3 is its speed (O(1) = instant, no looping).

| Method        | Action                                        | Cost |
|---------------|-----------------------------------------------|------|
| `push(x)`     | Add item to the **top**                       | O(1) |
| `pop()`       | **Remove and return** the top item            | O(1) |
| `peek()` / `top()` | View the top item **without removing**   | O(1) |
| `isEmpty()`   | Test whether the stack has no elements        | O(1) |
| `getSize()`   | Number of elements                            | O(1) |

The trap students fall for: **`pop` vs `peek`.** They both give you the top item, but —

`peek` and `pop` differ on ONE thing: **`pop` removes, `peek` does not.**

(Memory hook: **peek = just a peek**, you look but don't take. **pop = pop it off**, it's gone.)

---

## 3. Implementation — why **ArrayList**

**What & why.** To build a stack we reuse a structure we already have. Java gives us `ArrayList` (a resizable array) and `LinkedList` (a chain of nodes). Which is better *for a stack*? The exam answer is **ArrayList** — and here's the intuition: a stack only ever touches the **end** of the list (the end = the top). Adding/removing at the end of an ArrayList is instant, with no shuffling of other elements.

- **Use an `ArrayList` to implement a stack — it is MORE efficient than a linked list.**
- Reason: all stack operations (insert/delete) happen **only at the end** of the list. Adding/removing at the **end** of an ArrayList is O(1) with **no element shifting**.
- (Contrast: a stack never inserts/deletes at the *front*, so the linked-list advantage — cheap front operations — gives no benefit here. Queue is the opposite: it uses a LinkedList.)

Why no shifting? Picture the list `[A, B, C]`. Removing from the **front** (A) forces B and C to slide left — slow. Removing from the **end** (C) touches nobody else — fast. A stack always works at the end, so the slow case never happens.

### Two designs

**What & why.** There are two ways to make your stack class reuse an ArrayList. One inherits from it (and accidentally exposes too much); the other hides it inside as a private field (clean). The exam-preferred answer is **composition**.

**How to read this table:** "How" = the Java relationship; "Note" = the trade-off, and ★ marks the preferred one.

| Design | How | Note |
|--------|-----|------|
| **Inheritance** | `class GenericStack<E> extends ArrayList<E>` | exposes all ArrayList methods (e.g. `add(index,...)`) — leaky |
| **Composition** ★ | ArrayList as a **private field** inside the stack | **preferred** — only exposes push/pop/peek |

Why is inheritance "leaky"? If your stack `extends ArrayList`, the outside world can call `add(0, x)` and sneak an item into the *middle* — breaking the LIFO rule. Composition hides the list, so the only doors are `push`/`pop`/`peek`.

### EmptyStackException

**What & why.** What should happen if someone calls `pop()` on an empty stack? There's nothing to remove, so we refuse loudly by throwing an error. This is a common MCQ.

`pop()` and `peek()` require **at least one element**. On an empty stack they must **throw `java.util.EmptyStackException`**:

```java
public E pop() {
    if (isEmpty()) {
        throw new EmptyStackException();
    }
    return list.remove(getSize() - 1);
}
```

### Composition-based GenericStack (the lecture's design)

Read this slowly — every method just delegates to the inner `list`. Note `getSize() - 1` is the index of the **last** element (the top), because list indexes start at 0.

```java
import java.util.ArrayList;
import java.util.EmptyStackException;

public class GenericStack<E> {
    private ArrayList<E> list = new ArrayList<>();   // composition: list is a FIELD

    public int getSize()      { return list.size(); }
    public boolean isEmpty()  { return list.isEmpty(); }

    public void push(E o)     { list.add(o); }       // add at end = top, O(1)

    public E peek() {
        if (isEmpty()) throw new EmptyStackException();
        return list.get(getSize() - 1);              // view top, no removal
    }

    public E pop() {
        if (isEmpty()) throw new EmptyStackException();
        return list.remove(getSize() - 1);           // remove top
    }
}
```

### Push/pop trace (classic checkpoint)

Let's walk it slowly. Stack shown **top → bottom**, top item on the left.

```
push A            [A]
push B            [B, A]
push C            [C, B, A]
pop  -> C out     [B, A]
pop  -> B out     [A]
push D            [D, A]
```

Push A, B, C → pop (C out) → pop (B out) → push D.
Final stack top→bottom: **D, A**.

🧠 **Quick check.** Why is `ArrayList` preferred over `LinkedList` for a stack?
*Answer: a stack only adds/removes at the end, which is O(1) on an ArrayList with no element shifting — so the LinkedList's cheap-front advantage is wasted.*

---

## 4. Postfix (Reverse Polish Notation) ★★

**What & why.** This is the most exam-heavy part of the chapter — expect a trace question. Normally we write math **infix**: `2 + 3` (operator *between* operands). Computers find that annoying because of precedence and parentheses. **Postfix** writes the operator *after* its operands: `2 3 +`. A stack can evaluate postfix in one clean left-to-right pass — no precedence rules, no brackets.

- **Postfix / RPN:** the **operator comes AFTER its operands**.
- Postfix expressions are naturally **evaluated using a stack**.

The idea in one line: **see a number → stash it on the stack; see an operator → grab the last two numbers, combine them, put the result back.**

### Advantages of postfix
- **No precedence rules needed.**
- **No parentheses needed.**
- **Reduces (fewer) computer memory accesses.**

### Infix → Postfix conversions (memorize)

**How to read this table:** "Infix" is normal human notation; "Postfix" is the stack-friendly rewrite; "Why" explains the reordering. Higher-precedence operations get written first because they happen first.

| Infix             | Postfix         | Why |
|-------------------|-----------------|-----|
| `a + b * c`       | `a b c * +`     | `*` has higher precedence than `+` |
| `(a + b) * c`     | `a b + c *`     | parentheses force `a b +` first |
| `(a*b + c) / d + e` | `a b * c + d / e +` | subexpression `a b * c +`, then `/ d`, then `+ e` |

### Evaluation algorithm

Scan **left → right**:
1. **Operand → push** it on the stack.
2. **Operator →**
   - pop one operand as **`x`** (this is the **RIGHT** operand — first popped),
   - pop another operand as **`y`** (the **LEFT** operand),
   - compute **`(y OP x)`**,
   - push the result.
3. Repeat to the end. The single remaining value is the answer.

> ★ **Order matters for `-` and `/`.** The **first popped is the RIGHT operand**.
> So compute **`second_popped OP first_popped`** = `y OP x`.
> For `+` and `*` order doesn't matter, but use the same rule anyway.

**Why does the first pop become the RIGHT operand?** Because operands were pushed left-to-right, so the *right* one landed on top — it pops out first. Get this backwards and `-` / `/` give the wrong answer. This is exactly what the MCQ is testing.

### Worked example A — `6 2 3 + -` → **1**

Read each line as: *what we just read* → *what the stack looks like after*.

```
push 6                        stack: [6]
push 2                        stack: [6, 2]
push 3                        stack: [6, 2, 3]
'+': x=pop=3, y=pop=2 -> 2+3=5, push   stack: [6, 5]
'-': x=pop=5, y=pop=6 -> 6-5=1, push   stack: [1]
Result = 1
```
✅ **Answer = 1.** (Note the `-` order: `6 - 5`, not `5 - 6`.)

Slow walk-through of the tricky `-` step: the stack is `[6, 5]` with 5 on top. First pop = `5` = the RIGHT operand (`x`). Second pop = `6` = the LEFT operand (`y`). So it's `y - x = 6 - 5 = 1`, **not** `5 - 6`.

### Worked example B — `4 3 5 * +` → **19**

```
push 4                        stack: [4]
push 3                        stack: [4, 3]
push 5                        stack: [4, 3, 5]
'*': x=pop=5, y=pop=3 -> 3*5=15, push  stack: [4, 15]
'+': x=pop=15, y=pop=4 -> 4+15=19, push stack: [19]
Result = 19
```
✅ **Answer = 19.**

Notice multiplication happened *before* addition automatically — no precedence rules needed, the postfix ordering took care of it.

### Error detection (the stack state reveals it)

**What & why.** A malformed expression shows up as the stack having the *wrong number* of items. Two cases to recognize:

| Error | When detected | Example |
|-------|---------------|---------|
| **Too few operands** ("too many operators") | An operator is read while the stack has **< 2 elements** | `3 8 + * 9` — when `*` is read the stack has only one element |
| **Too many operands** | After processing the **whole expression** the stack has **> 1 element** | `9 8 + 7` — ends with two elements `[17, 7]` |

Intuition: every operator needs exactly **two** numbers to eat. If there aren't two waiting → too few operands. If extra numbers are left over at the very end → too many operands.

🧠 **Quick check.** Evaluate `5 1 2 + -`.
*Answer: `1 2 +` = 3, then `5 - 3` = 2. (Remember: first popped, 3, is the RIGHT operand.)*

---

## 5. Where stacks are used (intuition)

You don't need to memorize these as separate algorithms — they're all the same LIFO idea wearing different hats:

- **Undo / Redo (Ctrl+Z):** each action is pushed; undo pops the most recent one first. Naturally LIFO.
- **Browser Back button:** pages you visit get pushed; "Back" pops the last page.
- **Balanced symbols `()[]{}`:** push every opening bracket; on a closing bracket, pop and check it matches. If the stack is empty at the wrong time or not empty at the end → unbalanced.
- **The call stack:** when method A calls method B, Java *pushes* B's frame; when B finishes it *pops* and control returns to A. The most recently called method is the first to finish — LIFO again.

```
balanced check on  ( [ ] )
read '(' -> push     [ ( ]
read '[' -> push     [ [, ( ]
read ']' -> pop '['  matches [ ( ]
read ')' -> pop '('  matches [ ]
end: stack empty -> BALANCED ✅
```

---

## ★ Exam Tips
- **Stack = LIFO**; all access at the **`top`**. `pop` removes, `peek` does not.
- **Best implementation = `ArrayList`** (insert/delete at the end → O(1), no shifting). More efficient than a linked list for a stack.
- `pop`/`peek` on empty → **throw `EmptyStackException`**.
- **Composition** (ArrayList as a field) is the preferred design over inheritance.
- **Postfix `-`/`/`:** result = **`second_popped OP first_popped`** (`y OP x`, where `x` = first pop = right operand).
- `6 2 3 + -` → **1**;  `4 3 5 * +` → **19**.
- Postfix advantages: **no precedence, no parentheses, fewer memory accesses**.
- Error: operator with **<2 operands** = too few operands; **>1 element left** at end = too many operands.
