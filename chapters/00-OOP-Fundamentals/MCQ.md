# Chapter 0 — OOP Fundamentals: MCQ Practice

> 12 questions in the mid-term styles: (1) concept, (2) code-output/trace, (3) most-efficient/best, (4) tricky cannot/except/always/never. Answers are in `ANSWER_KEY.md`.

---

**Q1.** A class `Dog extends Animal` and redefines the parent's `sound()` method with the exact same signature. Which OOP concept does this demonstrate?

- A. Method overloading
- B. Method overriding
- C. Encapsulation
- D. Abstraction

---

**Q2.** Which set of features best describes **encapsulation**?

- A. `extends` keyword and a shared superclass
- B. `private` data fields accessed through public getters and setters
- C. An `interface` with only abstract methods
- D. Multiple methods with the same name and different parameters

---

**Q3.** What is printed?

```java
class Animal { void sound() { System.out.print("Generic"); } }
class Cat extends Animal { void sound() { System.out.print("Meow"); } }
public class Test {
    public static void main(String[] a) {
        Animal x = new Cat();
        x.sound();
    }
}
```

- A. Generic
- B. Meow
- C. GenericMeow
- D. Compile error

---

**Q4.** What does this print?

```java
System.out.println("Pi" + 3.14);
System.out.println(1 + 2 + " = sum");
```

- A. `Pi3.14` then `3 = sum`
- B. `Pi3.14` then `12 = sum`
- C. `3.14Pi` then `3 = sum`
- D. `Pi 3.14` then `1 2 = sum`

---

**Q5.** What is the output?

```java
int a = 7, b = 2;
System.out.println(a / b);
System.out.println(a % b);
System.out.println((double) a / b);
```

- A. `3.5`, `1`, `3.5`
- B. `3`, `1`, `3.5`
- C. `3`, `1`, `3`
- D. `4`, `0`, `3.5`

---

**Q6.** What is printed?

```java
class Adder {
    int add(int a, int b)        { return a + b; }
    int add(int a, int b, int c) { return a + b + c; }
}
public class Test {
    public static void main(String[] x) {
        Adder ad = new Adder();
        System.out.println(ad.add(2, 3) + " " + ad.add(2, 3, 4));
    }
}
```

- A. `5 9`
- B. `9 5`
- C. `5 5`
- D. Compile error (duplicate method name)

---

**Q7.** What is printed?

```java
class Counter {
    static int count = 0;
    Counter() { count++; }
}
public class Test {
    public static void main(String[] a) {
        new Counter(); new Counter(); new Counter();
        System.out.println(Counter.count);
    }
}
```

- A. `0`
- B. `1`
- C. `3`
- D. Compile error

---

**Q8.** An account stores its interest rate as the **percentage** `annualInterestRate = 4.5`. Which expression returns the correct **monthly** interest rate?

- A. `annualInterestRate / 12`
- B. `annualInterestRate / 100`
- C. `annualInterestRate / 100 / 12`
- D. `annualInterestRate * 12 / 100`

---

**Q9.** You need to read input typed by the user on the **keyboard** with a `Scanner`. Which statement is correct?

- A. `new Scanner(new File("input.txt"))`
- B. `new Scanner(System.in)`
- C. `new Scanner(System.out)`
- D. `new Scanner("keyboard")`

---

**Q10.** Which statement about `static`, `this`, and `final` is **TRUE**? (Watch the wording.)

- A. A `static` method can directly access this object's instance (non-static) fields via `this`.
- B. A `final` method can always be overridden by a subclass.
- C. A `static` member belongs to the class and is shared by all instances.
- D. The `this` keyword can be used inside a `static` method.

---

**Q11.** A subclass should reuse and extend the behaviour of an existing class. Which approach is **most appropriate**?

- A. Copy and paste the code into a new class
- B. Use `extends` to inherit from the existing class
- C. Make all the original fields `public`
- D. Overload every method of the original class

---

**Q12.** Which statement about overriding and overloading is **CORRECT**?

- A. Overloading requires the `extends` keyword.
- B. Overriding requires the same method name but a different parameter list.
- C. Overloaded methods have the same name but different parameter lists; overridden methods have the same signature in a subclass.
- D. Overriding is resolved at compile time and overloading at runtime.
