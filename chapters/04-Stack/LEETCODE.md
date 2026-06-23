# Chapter 4 — Stack (LeetCode 14-Day Plan)

> Days 1–3 are the core stack problems. Day 2 (Evaluate RPN) **is** the postfix evaluation from the lecture. Bonus introduces the monotonic-stack pattern.

| Day | Problem | Pattern |
|-----|---------|---------|
| 1 | 20. Valid Parentheses | matching with a stack |
| 2 | 150. Evaluate Reverse Polish Notation | postfix evaluation (lecture) |
| 3 | 155. Min Stack | augmented stack |
| Bonus | 739. Daily Temperatures | monotonic stack |

---

## Day 1 — 20. Valid Parentheses
Link: https://leetcode.com/problems/valid-parentheses/

**Approach.** Scan the string. Push every opening bracket. On a closing bracket, the stack top must be the matching opener — if the stack is empty or the top doesn't match, it's invalid. After scanning, a **valid** string leaves an **empty** stack (this mirrors the postfix "too many operands" idea: leftovers = error). Use a map from closing → opening bracket.

**Complexity.** Time O(n), Space O(n).

```java
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

class Solution {
    public boolean isValid(String s) {
        Map<Character, Character> match = Map.of(')', '(', ']', '[', '}', '{');
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (match.containsKey(c)) {                 // closing bracket
                if (stack.isEmpty() || stack.pop() != match.get(c)) {
                    return false;
                }
            } else {                                    // opening bracket
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
```

---

## Day 2 — 150. Evaluate Reverse Polish Notation  ★ (this IS postfix)
Link: https://leetcode.com/problems/evaluate-reverse-polish-notation/

**Approach.** Exactly the lecture's postfix algorithm. Scan tokens left→right: an operand is pushed; an operator pops two operands and pushes the result. **Order matters for `-` and `/`:** the **first popped is the RIGHT operand**, so compute `y OP x` where `x` = first pop. Push `Integer.parseInt` of any non-operator token (handles negative numbers like `"-4"`).

**Complexity.** Time O(n), Space O(n).

```java
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String t : tokens) {
            switch (t) {
                case "+": case "-": case "*": case "/":
                    int x = stack.pop();   // RIGHT operand (first popped)
                    int y = stack.pop();   // LEFT operand
                    stack.push(apply(y, x, t));   // compute (y OP x)
                    break;
                default:
                    stack.push(Integer.parseInt(t));
            }
        }
        return stack.pop();
    }

    private int apply(int y, int x, String op) {
        switch (op) {
            case "+": return y + x;
            case "-": return y - x;
            case "*": return y * x;
            default:  return y / x;
        }
    }
}
```

---

## Day 3 — 155. Min Stack
Link: https://leetcode.com/problems/min-stack/

**Approach.** Support `push`, `pop`, `top`, and `getMin` all in O(1). Keep a **second stack of minimums** in parallel with the main stack. On `push(x)`, push `min(x, currentMin)` onto the min-stack; on `pop`, pop both. The min-stack top is always the minimum of the current elements.

**Complexity.** All operations O(1) time; O(n) space.

```java
import java.util.ArrayDeque;
import java.util.Deque;

class MinStack {
    private final Deque<Integer> data = new ArrayDeque<>();
    private final Deque<Integer> mins = new ArrayDeque<>();

    public MinStack() { }

    public void push(int val) {
        data.push(val);
        mins.push(mins.isEmpty() ? val : Math.min(val, mins.peek()));
    }

    public void pop() {
        data.pop();
        mins.pop();
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return mins.peek();
    }
}
```

---

## Bonus — 739. Daily Temperatures (monotonic stack)
Link: https://leetcode.com/problems/daily-temperatures/

**Approach.** A **monotonic (decreasing) stack** of indices. For each day, while the current temperature is warmer than the temperature at the index on top of the stack, pop that index and record the day-gap as the answer for it. Push the current index. Each index is pushed and popped at most once.

**Complexity.** Time O(n), Space O(n).

```java
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();   // holds indices, decreasing temps
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prev = stack.pop();
                answer[prev] = i - prev;
            }
            stack.push(i);
        }
        return answer;   // remaining indices stay 0 (no warmer future day)
    }
}
```

---

## Resources
- Liang, *Introduction to Java Programming*, 10th Ed. — Chapters 19 & 24 (Stacks, generic stack).
- Carrano, *Data Structures and Abstractions with Java*, 3rd Ed. — Chapter 5 (Stacks).
- Stack animation: https://yongdanielliang.github.io/animation/web/Stack.html
- Java API: `java.util.Deque` (preferred over the legacy `java.util.Stack`), `java.util.EmptyStackException`.
- LeetCode Stack tag: https://leetcode.com/tag/stack/
- Pattern: **monotonic stack** for "next greater / next warmer element" problems.
