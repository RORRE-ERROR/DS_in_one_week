# Chapter 7 — Recursion (MCQ)

14 questions. Choose the single best answer (A–D).

---

**Q1.** Recursion is best described as:
- A. A loop that repeats a fixed number of times
- B. A method that calls itself to solve a smaller version of the problem
- C. A method that calls another method in a different class
- D. A technique for sorting data in place

---

**Q2.** Every recursive method must contain which TWO essential parts?
- A. A loop and a counter
- B. A base case and a recursive case
- C. A try block and a catch block
- D. An interface and an implementation

---

**Q3.** What is the role of the base case in a recursive method?
- A. It calls the method on a larger input
- B. It is the stopping condition, solved directly without further recursion
- C. It increases the size of the call stack on purpose
- D. It is optional and only improves performance

---

**Q4.** Which is the correct recursive definition of factorial?
- A. `0! = 0;  n! = n + (n-1)!`
- B. `0! = 1;  n! = n * (n-1)!`
- C. `1! = 0;  n! = n - (n-1)!`
- D. `0! = 1;  n! = (n-1)! / n`

---

**Q5.** Consider this method:
```java
public static long factorial(int n) {
    return n * factorial(n - 1);   // no base case
}
```
What happens when `factorial(5)` is called?
- A. It returns 120
- B. It returns 0
- C. It runs infinitely and causes a `StackOverflowError`
- D. It does not compile

---

**Q6.** What does each recursive call require that an iteration does NOT?
- A. A new thread
- B. Extra space on the call stack (a new stack frame) for its parameters and locals
- C. A separate class file
- D. A network connection

---

**Q7.** Given the Fibonacci series `0 1 1 2 3 5 8 13 21 34 55 ...` (0-based index), what is the value of `fib(7)`?
- A. 8
- B. 13
- C. 21
- D. 11

---

**Q8.** Trace this method. What is the output of `mystery(537)`?
```java
public static void mystery(int n) {
    if (n <= 0) return;          // base case
    mystery(n / 10);             // recurse first
    System.out.print(n % 10);    // then print
}
```
- A. 735
- B. 537
- C. 75
- D. (nothing — infinite recursion)

---

**Q9.** In Q8, what is the base case?
- A. `n % 10`
- B. `n / 10`
- C. `n <= 0`
- D. There is no base case

---

**Q10.** What does this method return for `mystery2(4)`?
```java
public static int mystery2(int n) {
    if (n == 0) return 0;
    return n + mystery2(n - 1);
}
```
- A. 4
- B. 10
- C. 24
- D. 0

---

**Q11.** Trace `fib(4)` using `fib(0)=0, fib(1)=1, fib(n)=fib(n-1)+fib(n-2)`. What is the result?
- A. 2
- B. 3
- C. 5
- D. 4

---

**Q12.** Which statement about recursion vs iteration is TRUE?
- A. Recursion can solve problems that iteration fundamentally cannot
- B. Anything done with a loop can be done with recursion and vice-versa
- C. Iteration always uses more memory than recursion
- D. Recursion never terminates

---

**Q13.** A directory's size is the sum of the sizes of its files PLUS the sizes of all its subdirectories. This problem is best solved with:
- A. A single `for` loop
- B. Recursion, because the structure is inherently recursive
- C. Binary search
- D. A stack of integers only

---

**Q14.** Which statement correctly describes a difference between recursion and iteration?
- A. Recursion stops when its loop condition becomes false
- B. Recursion stops at a base case and uses extra stack memory per call; iteration stops on a false condition with no extra memory
- C. Iteration causes a `StackOverflowError`; recursion causes an infinite loop
- D. Both always use exactly the same amount of memory
