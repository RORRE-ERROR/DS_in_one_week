# Chapter 0 — Programming Fundamentals & OOP (Java)

**What this chapter is about:** the foundation everything else stands on — how Java programs are organised around *objects*, plus the little arithmetic and text "gotchas" that examiners love to trick you with. Don't memorise blindly; once you *get* the idea behind each one, the answers become obvious.

**Why it matters for the exam:** these show up as warm-up code-reading and multiple-choice questions on the final. They're easy marks *if* you've seen the traps before.

> 📌 Exam anchor: the mid-term "Animal/Dog `sound()`" question is the classic one → the answer is **Method overriding**. Remember this one.

---

## What is OOP, in one sentence?

OOP (Object-Oriented Programming) is a way of writing code by bundling **data** (what something *has*) and **behaviour** (what something *does*) together into "objects" — like little self-contained machines.

**Analogy:** think of a `Car` object. It *has* data (speed, fuel) and it *does* things (accelerate, brake). You interact with the car through its controls; you don't reach into the engine. OOP organises programs the same way.

There are 4 big ideas ("pillars") that make OOP work. We'll meet each one gently below.

---

## The 4 OOP Pillars

Quick overview first, then we'll unpack the two trickiest ones.

> **How to read this table:** column 1 is the name, column 2 is the plain meaning, column 3 is the clue that tells you "this is the answer" when you see it in a multiple-choice question.

| Pillar | One-line definition | Keyword clue (what to look for in the MCQ) |
|---|---|---|
| **Encapsulation** | Hide data behind `private` fields + public getters/setters (accessor/mutator). | `private` field + `getX()/setX()` |
| **Inheritance** | A subclass reuses/extends a superclass. | `class Dog extends Animal` |
| **Polymorphism** | The *same* method call behaves differently depending on the object's actual type. | overriding resolved **at runtime** |
| **Abstraction** | Expose *what* it does, hide *how*. Modelled with interfaces / abstract classes. | `interface`, `abstract` |

Mnemonic: **"A PIE"** — Abstraction, Polymorphism, Inheritance, Encapsulation.

### Encapsulation — the "TV remote" idea

**What & why:** you make the data *private* so nobody can mess with it directly. They have to go through methods you control. This protects the object from being put into a bad state.

**Analogy:** a TV remote. You press *buttons* (public methods); you never touch the *circuit board* inside (private data). The buttons are the safe, allowed way to change the TV.

```java
// Encapsulation: data is private, accessed only via methods
class BankAccount {
    private double balance;                 // hidden field
    public double getBalance() { return balance; }      // accessor
    public void setBalance(double b) { balance = b; }   // mutator
}
```

### Inheritance — "is-a" reuse

**What & why:** instead of rewriting shared code, a child class *inherits* from a parent. `class Dog extends Animal` means "a Dog **is an** Animal" and automatically gets Animal's stuff, while adding/changing its own.

**Analogy:** a `Dog` is a kind of `Animal`. It inherits "has legs, can breathe" from Animal, and adds "can bark".

### Polymorphism — "many forms"

**What & why:** *polymorphism* (poly = many, morph = forms) means the *same* command produces *different* behaviour depending on what object actually receives it. Calling `.sound()` makes a Dog bark but a Cat meow — same call, different result.

**Analogy:** press the "play" button on different devices — a CD player, a phone, a record player. Same button, each plays in its own way.

### Abstraction — "what, not how"

**What & why:** show *what* something can do, hide *how* it does it. You define a contract (`interface` / `abstract class`) without the messy details.

**Analogy:** driving a car — you know the pedal makes it go (the *what*); you don't need to know how the engine combusts fuel (the *how*).

🧠 **Quick check:** A class hides its fields with `private` and exposes `getName()/setName()`. Which pillar is this?
<details><summary>Answer</summary>Encapsulation.</details>

---

## Overriding vs Overloading (very common MCQ trap)

These two sound almost the same but mean opposite things, and the exam *loves* to test whether you can tell them apart.

- **Overriding** = a child class **replaces** a parent's method (same name, *same* parameters). Think "over-RIDE = take over."
- **Overloading** = *several* methods share a name but take **different** parameters. Think "over-LOAD = load up the same name with many versions."

**Analogy:** Overriding is a child redecorating the *exact same room* they inherited. Overloading is having several doors all labelled "add" but each opening to a different-sized room (different parameters).

> **How to read this table:** each row is one feature; compare the Overriding column against the Overloading column to see how they differ.

| | **Overriding** | **Overloading** |
|---|---|---|
| Where | Subclass redefines a parent method | Same class (or anywhere) |
| Method name | **Same** | **Same** |
| Parameter list | **Same** (same signature) | **Different** (count/types/order) |
| Return type | Same (or covariant) | Can differ |
| Bound | **Runtime** (dynamic dispatch) | **Compile-time** (static) |
| Polymorphism kind | Runtime polymorphism | Compile-time polymorphism |
| Needs `extends`? | **Yes** | No |

```java
// OVERRIDING: same signature, subclass replaces parent behaviour (needs extends)
class Animal { void sound() { System.out.print("Animal sound"); } }
class Dog extends Animal { void sound() { System.out.print("Bark"); } } // OVERRIDES
// new Dog().sound()  ->  prints "Bark"   => the OOP concept is METHOD OVERRIDING
```

```java
// OVERLOADING: same name, different parameter lists (compile-time)
class MathUtil {
    int add(int a, int b)         { return a + b; }
    int add(int a, int b, int c)  { return a + b + c; }   // different #params
    double add(double a, double b){ return a + b; }       // different types
}

// CONSTRUCTOR OVERLOADING: multiple constructors with different params
class Point {
    int x, y;
    Point()            { x = 0; y = 0; }        // no-arg constructor
    Point(int x, int y){ this.x = x; this.y = y; }  // parameterised constructor
}
```

**Exam tip:** "`extends` + same method name + same params" = **overriding**. "Same name, different params" = **overloading**. Multiple constructors with different params = **constructor overloading**.

🧠 **Quick check:** `int add(int a, int b)` and `double add(double a, double b)` in the same class — overriding or overloading?
<details><summary>Answer</summary>Overloading (same name, different parameter types, no <code>extends</code> involved).</details>

---

## Access Modifiers

**What & why:** these keywords control *who is allowed to see/use* a field or method. They go from most secretive (`private`) to fully open (`public`). They're how you actually *enforce* encapsulation.

**Analogy:** think of rooms in a building. `private` = your locked bedroom (only you). default = rooms open to your flatmates (same package). `protected` = also open to family who visit (subclasses). `public` = the public lobby (anyone).

> **How to read this table:** ✅ means "code in that location CAN access the member"; ❌ means it cannot. Read each row left to right: visibility widens as you go down.

| Modifier | Same class | Same package | Subclass (other pkg) | Anywhere |
|---|---|---|---|---|
| `private` | ✅ | ❌ | ❌ | ❌ |
| *(default / package-private)* | ✅ | ✅ | ❌ | ❌ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

- `private` = information hiding; only visible inside its own class (core of **encapsulation**).
- `protected` = visible to subclasses + same package.
- `public` = visible everywhere.

Memory aid for the ordering (narrow → wide): `private` < default < `protected` < `public`.

---

## Accessor / Mutator / Constructor

**What & why:** since fields are usually `private`, you need controlled "doors" to read and change them. Those doors are accessors and mutators. Constructors are the special methods that *build* the object in the first place.

- **Accessor (getter):** returns a field's value — `getBalance()`. No parameter, has a return type. *(Gets data OUT.)*
- **Mutator (setter):** changes a field's value — `setBalance(double b)`. Has a parameter, usually `void`. *(Puts data IN.)*
- **No-arg constructor:** creates an object with default values.
- **Parameterised constructor:** sets fields from arguments at creation time.
- A constructor has **no return type** and has the **same name as the class**.

---

## `this`, `static`, `final`

Three small keywords with big exam consequences. Here's the gut feeling for each before the table:

- `this` = "*this very object I'm in right now*." Used when a parameter and a field share a name and you need to point at the field.
- `static` = "*belongs to the class as a whole, not to any one object*." One shared copy.
- `final` = "*lock it — it can't change.*"

**Analogy for `static`:** imagine a school. Each student has their *own* name (instance field), but the *school name* is shared by everyone (static field) — there's only one copy of it for the whole class.

> **How to read this table:** column 2 is the plain meaning; column 3 is the specific fact examiners test.

| Keyword | Meaning | Exam note |
|---|---|---|
| `this` | Reference to the **current object**. Used to disambiguate a field from a same-named parameter (`this.x = x`), or call another constructor (`this(...)`). | Cannot be used in a `static` method. |
| `static` | Belongs to the **class**, not an instance. Shared by all objects. Called as `ClassName.member`. | A `static` method **cannot** access instance (non-static) fields or `this` directly. |
| `final` | Constant / cannot change. `final` variable = assign once; `final` method = cannot be overridden; `final` class = cannot be extended. | `final` field must be initialised exactly once. |

```java
class Counter {
    static int count = 0;     // shared across ALL Counter objects
    final int id;             // constant per object, set once
    Counter(int id) { this.id = id; count++; }   // this.id distinguishes field from param
}
```

Why can't a `static` method use `this`? Because a static method belongs to the *class*, and there's no particular object for `this` to point at. Make sense once you picture the "school name vs student name" idea.

🧠 **Quick check:** Can a `static` method directly read a non-static (instance) field?
<details><summary>Answer</summary>No — there's no specific object instance for it to read from.</details>

---

## String Concatenation Behaviour (★ classic trace question)

**What & why:** the `+` symbol does *two different jobs* in Java. With numbers it does **math**; but as soon as a `String` is involved, it does **gluing text together** (concatenation). The exam tricks you by mixing both in one line, so you must trace it carefully.

The `+` operator with a `String` operand converts the other operand to its String form and **concatenates** (it does NOT do math).

```java
"Pi" + 3.14          // -> "Pi3.14"   (number becomes text)
"Result: " + 1 + 2   // -> "Result: 12"   (left-to-right: ("Result: "+1)+2)
1 + 2 + " = sum"     // -> "3 = sum"   (1+2 done as ints FIRST, then concatenated)
'A' + 1              // -> 66          (no String present -> char promoted to int!)
"" + 'A' + 1         // -> "A1"        (String present -> char stays a character)
```

**Step-by-step for the two tricky ones:**

`"Result: " + 1 + 2` — Java reads **left to right**:
1. `"Result: " + 1` → a String is on the left, so glue: `"Result: 1"`.
2. `"Result: 1" + 2` → still a String, so glue: `"Result: 12"`. ✅

`1 + 2 + " = sum"` — again **left to right**:
1. `1 + 2` → both are numbers, no String yet, so do **math**: `3`.
2. `3 + " = sum"` → now a String appears, so glue: `"3 = sum"`. ✅

And the char one, `'A' + 1`: there's **no String anywhere**, so `'A'` is treated as its numeric code (65) and `65 + 1 = 66`. Add `""` in front (`"" + 'A' + 1`) and now a String leads, so `'A'` stays a letter → `"A1"`.

**Exam tip:** Evaluation is **left to right**. Once a `String` appears, everything after it is concatenated. Numbers added *before* any String are computed numerically first.

🧠 **Quick check:** What does `1 + 2 + "!"` produce?
<details><summary>Answer</summary><code>"3!"</code> — <code>1+2</code> is math (no String yet) = 3, then glued to <code>"!"</code>.</details>

---

## Integer Division (★ trace trap)

**What & why:** when you divide two **whole numbers (ints)** in Java, the result is also a whole number — any fractional part is **thrown away** (truncated toward zero), *not* rounded. This catches everyone at least once.

**Analogy:** sharing 5 sweets between 2 kids, where you can't cut a sweet — each gets 2, and the leftover 1 just doesn't count in the division (it's the remainder).

```java
5 / 2        // -> 2   (int / int truncates toward zero, NOT 2.5)
5 % 2        // -> 1   (remainder)
5.0 / 2      // -> 2.5 (one operand is double -> floating-point division)
7 / 2 * 2    // -> 6   ((7/2)=3, *2=6)  not 7
```

**Step-by-step for `7 / 2 * 2`:** same precedence, so left to right:
1. `7 / 2` → both ints → truncate → `3` (the `.5` is gone forever).
2. `3 * 2` → `6`.

You might *expect* 7 because algebra says `x/2*2 = x`, but the truncation in step 1 already lost information. **Tip:** make one operand a `double` (write `7.0 / 2`) if you want real division.

🧠 **Quick check:** What is `9 / 4` in Java?
<details><summary>Answer</summary><code>2</code> (not 2.25 — the fraction is truncated).</details>

---

## Reading Files with Scanner (lab fundamentals)

**What & why:** `Scanner` is Java's tool for *reading input*. Same class, two sources: it can read what a user types at the **keyboard**, or read text out of a **file**. The lab uses the file version.

```java
import java.io.File;
import java.util.Scanner;

Scanner input = new Scanner(new File("text1.txt"));   // read from a FILE
// Scanner kb = new Scanner(System.in);                // read from KEYBOARD
while (input.hasNextLine()) {
    String line = input.nextLine();
    System.out.println(line);
}
input.close();
```

How the loop reads: `hasNextLine()` asks "is there another line?" — keep looping while yes; `nextLine()` grabs that line. When the file ends, the loop stops, and `close()` releases the file.

- `new Scanner(System.in)` reads the **keyboard**; `new Scanner(new File(...))` reads a **file**.
- Common Scanner methods: `nextLine()`, `next()`, `nextInt()`, `nextDouble()`, `hasNextLine()`, `hasNext()`.
- **Appending vs overwriting:** appending adds to the end of existing content; overwriting replaces it. (The letter lab requires *appending* part 2 to part 1, not overwriting.)
- To split a delimited line: `line.split(",")` or `split("[,; ]+")` for multiple delimiters.

---

## The Account-Class Lab Pattern (★ formula must be memorized)

**What & why:** a recurring lab/exam class. The only tricky bit is the interest formula — the rate is given as a **percentage per year**, but you need it as a **fraction per month**, so you do two divisions. Get this conversion right and the rest is plug-and-play.

From the lab: an `Account` with `private int id`, `private double balance`, `private double annualInterestRate`, `private Date dateCreated`, a no-arg + parameterised constructor, accessors/mutators, and interest methods.

- `monthlyInterestRate = annualInterestRate / 12`
- The annual rate is a **percentage** (e.g. 4.5%), so **divide by 100**.
- `monthlyInterest = balance * monthlyInterestRate`

Think of it as two conversions: **÷100** turns "4.5%" into the fraction 0.045, then **÷12** turns "per year" into "per month."

```java
public double getMonthlyInterestRate() {
    return (annualInterestRate / 100) / 12;     // /100 for %, /12 for monthly
}
public double getMonthlyInterest() {
    return balance * getMonthlyInterestRate();  // interest, NOT the rate
}
public void withdraw(double amount) { balance -= amount; }
public void deposit(double amount)  { balance += amount; }
```

**Worked example, step by step:** start with $20,000, withdraw $2,500, deposit $3,000 → balance = 20,000 − 2,500 + 3,000 = **$20,500**. Rate 4.5%:
1. monthlyInterestRate = 4.5 / 100 / 12 = **0.00375**
2. monthlyInterest = 20,500 × 0.00375 = **$76.875**

⚠️ Don't confuse `getMonthlyInterest()` (the money earned, `balance * rate`) with `getMonthlyInterestRate()` (just the rate). The exam phrasing tests exactly this.

---

## ★ Exam Tips

1. **A PIE** = the 4 pillars: Abstraction, Polymorphism, Inheritance, Encapsulation.
2. **Overriding** = same signature in a subclass (`extends`, runtime). **Overloading** = same name + different params (compile-time). The Animal/Dog `sound()` case = **overriding**.
3. **Encapsulation** = `private` fields + public getters/setters.
4. `private` < default < `protected` < `public` (widening visibility).
5. `static` belongs to the class (no `this`, can't touch instance fields directly); `final` = unchangeable.
6. String `+`: once a `String` appears, the rest is **concatenation**; numbers before it compute first. `"Pi"+3.14` → `"Pi3.14"`.
7. Integer `/` **truncates**: `5/2 == 2`. Make one operand a `double` for real division.
8. Account interest: divide by **100** (percent) and **12** (monthly); `getMonthlyInterest()` returns interest = `balance * rate`, not the rate.
9. Watch tricky wording: *cannot / except / always / never*. A `static` method **cannot** use `this`; a `final` method **cannot** be overridden.
