# Chapter 1 — Generics (WIA1002 Data Structure)

> Exam = all MCQ. Four flavours: **(1) concept**, **(2) code-output / trace**, **(3) "which is most efficient/best"**, **(4) tricky "cannot / except / always / never"** wording. Read negative words twice.

---

## 0. The big picture (read this first)

**What are generics?** A way to tell a class or method, *"you will work with some type, but I'll tell you which exact type later."* You leave a blank — written as `<E>` or `<T>` — and fill it in when you use the class.

**What problem do they solve?** Before generics, a list could hold *anything*. You might put a `String` in, then accidentally pull it out as an `Integer`, and the program would crash **while running** (the worst time to find a bug). Generics catch that mistake **while you compile**, before the program ever runs.

**Analogy — a labeled storage box.** Imagine a box labeled `Box<Books>`. The label is a promise: only books go in. If you try to pack a shoe, you're stopped *at packing time* — you don't have to wait until someone opens the box later and gets confused. A plain unlabeled box (`Box`) accepts anything, so problems only surface when you open it. Generics are the label; the compiler is the person checking what you pack.

```
Box<Books>   ->  put a Book  ✅   put a Shoe  ❌ (compiler stops you NOW)
Box          ->  put anything ✅  ... surprise crash LATER when you read it
```

Keep that box image in mind — almost every rule below is "what the label lets the compiler check."

---

## 1. Definition

**What it is:** Generics let you *parameterize types* — i.e. treat a type itself as something you fill in, the same way a method fills in a value through a parameter.

- **Generics = the capability to parameterize types.** You define a class or method with a *type parameter* that the compiler substitutes with a concrete type.
- The compiler does the substitution and the type checking.
- **Key distinction:** inputs to **formal parameters are *values*** (e.g. `5`, `"hi"`); inputs to **type parameters are *types*** (e.g. `String`, `Integer`).

Think of it this way: a normal parameter answers *"what value?"*; a type parameter answers *"what type?"*.

```java
// One generic class -> many concrete types
ArrayList<String>  cities  = new ArrayList<>(); // holds Strings
ArrayList<Integer> numbers = new ArrayList<>(); // holds Integers
```

One class (`ArrayList`) becomes many specific, type-safe versions just by changing the `<...>` label.

🧠 **Quick check:** Is `String` a value or a type in `ArrayList<String>`?
*Answer: a **type** — it's the type argument that fills the type parameter.*

---

## 2. Why generics?

**The pain they remove:** without generics you cast constantly and discover type bugs at runtime. With generics the compiler guarantees what's inside, so you skip the casts and catch mistakes early.

**How to read this table:** left column = the benefit, right column = why it matters.

| Benefit | Detail |
|---|---|
| **Stronger type checking at compile time** | Type mismatches are caught **at compile time**, before the program runs → fewer runtime errors, more reliable code. |
| **Eliminates casting** | No need to cast on retrieval. |

```java
ArrayList<String> list = new ArrayList<>();
list.add("hello");
String s = list.get(0);          // NO cast needed (with generics)

// Pre-JDK 1.5 raw style required a cast:
// ArrayList list = new ArrayList();
// String s = (String) list.get(0);   // cast required, else compile error
```

Why no cast? Because the label `<String>` already promised the compiler that everything inside is a `String`, so `get(0)` is *known* to return a `String`.

★ **Exam Tip:** "benefit of generics?" → **compile-time type checking + elimination of casting**. If you `add` an incompatible type to a typed list, it is a **compile error** (not a runtime error).

🧠 **Quick check:** You write `list.add(42)` on an `ArrayList<String>`. When do you find out it's wrong?
*Answer: at **compile time** — it won't even build.*

---

## 3. Syntax to memorize

**What this section covers:** the three places you can put a type parameter — on a class, on an interface, or on a single method.

Generics can be defined for a **Class**, an **Interface**, or a **Method**.

```java
public class GenericStack<E> { ... }              // (1) generic class
public interface Comparable<E> { ... }            // (2) generic interface
public static <E> void print(E[] list) { ... }    // (3) generic method
```

### Generic method — the `<E>` goes BEFORE the return type

A *generic method* declares its own type blank, and that blank must appear **before the return type** so the compiler knows `E` is a placeholder, not a real class.

```java
public static <E> returnType methodName(E parameter) { ... }
public static <E> void print(E[] list) { ... }
public <E> boolean isFilled(E filled) { ... }
```
- **To declare:** place `<E>` **before the return type**.
- **To invoke:** `MyClass.<ActualType>print(arr);` — or just call it normally and let the compiler infer the type.

In practice you almost always let the compiler *infer* the type from the argument, so you rarely write the `.<ActualType>` part — but you should recognize it.

### Bounded type parameter — `<E extends X>`

**Why this exists:** sometimes "any type" is too loose. If a method needs to compare or do math, the type must *support* that. A **bound** says "the fill-in type must be `X` or a subtype of `X`," so you're allowed to use `X`'s methods.

**Walk-through of `<E extends Comparable<E>>`:**
1. `E` is the blank — the type the caller supplies.
2. `extends Comparable<E>` means "`E` must be a type that knows how to compare itself" (it has `compareTo`).
3. Because of that promise, inside the method you may safely call `a.compareTo(b)`. Without the bound, the compiler wouldn't know `E` has that method.

> Note: here `extends` is reused to mean "is a subtype of" — for both classes *and* interfaces. (`Comparable` is an interface, but you still write `extends`, not `implements`.) The **upper bound** is the most general type `E` is allowed to be.

```java
public <U extends Number> void inspect(U u) { ... }      // U must be Number or a subtype
public static <E extends Comparable<E>> E max(E a, E b) { // E must be comparable
    return (a.compareTo(b) > 0) ? a : b;
}
```
- **Unbounded `<E>` is the same as `<E extends Object>`.** (Every type is a subtype of `Object`, so "no bound" really means "bounded by `Object`.")

### Notes
- A generic class **can have multiple type parameters:** `<E1, E2, E3>`, e.g. `Pair<K, V>`.
- The constructor has **no** type parameter in its name: `public GenericStack() {...}` (not `GenericStack<E>()`).
- You can define a class/interface as a subtype: `class String implements Comparable<String>`.

🧠 **Quick check:** Why does `max` need `<E extends Comparable<E>>` instead of just `<E>`?
*Answer: so it's allowed to call `compareTo` — the bound guarantees `E` has that method.*

---

## 4. Naming convention

**Why single letters?** Type parameters are written as **single uppercase letters** so you can instantly tell a *type placeholder* apart from a real class or variable name. The letters are just conventions (the compiler doesn't care), but everyone uses them:

**How to read this table:** the letter is a hint about the *role* the type plays.

| Letter | Meaning |
|---|---|
| **E** | Element — used in collections, e.g. `List<E>` |
| **K** | Key — used in maps, e.g. `Map<K,V>` |
| **V** | Value — used in maps, e.g. `Map<K,V>` |
| **N** | Number — a numeric type |
| **T** | Type — general placeholder for any type |
| **S, U, V…** | Extra parameters when multiple are needed |

---

## 5. Generic type rules

**Two rules that trip beginners up — and the reasons behind them.**

- **The type argument MUST be a reference type** (`Integer`, `Double`, `String`, `MyPet`…). It **cannot be a primitive** (`int`, `double`, `char`). Primitives like `int` aren't objects, and generics work only with objects — which is exactly why we have wrapper classes like `Integer` for `int`.
  ```java
  ArrayList<Integer> x = new ArrayList<>(); // OK
  ArrayList<int>     y = new ArrayList<>(); // ILLEGAL — int is primitive
  ```
- Generics are **invariant**: `ArrayList<Integer>` is **NOT** an `ArrayList<Number>`, even though `Integer` is a subtype of `Number`. (This is *why* wildcards exist.)

**Why invariance?** It feels surprising — an `Integer` *is* a `Number`, so shouldn't a list of Integers be a list of Numbers? No. If `ArrayList<Integer>` were treated as `ArrayList<Number>`, someone could add a `Double` into it ("it's a Number!") and your "list of Integers" would secretly contain a Double — a bug. Java forbids it to keep the box's label honest. Wildcards (next section) are the safe escape hatch.

```
Integer IS-A Number                ✅ (objects)
ArrayList<Integer> IS-A ArrayList<Number>   ❌ (generics are invariant)
```

🧠 **Quick check:** Is `List<Dog>` usable where a `List<Animal>` is expected?
*Answer: No — generics are invariant. You'd need a wildcard like `List<? extends Animal>`.*

---

## 6. Wildcards `?`

**What it is:** the `?` is a *wildcard* — it stands for "some type I don't need to name." It's the escape hatch from the invariance rule above: it lets one method accept lists of *many* different element types safely.

A wildcard `?` represents an **unknown type**.

**How to read this table:** `extends` raises a ceiling (upper bound = subtypes); `super` sets a floor (lower bound = supertypes).

| Form | Name | Meaning |
|---|---|---|
| `?` | unbounded wildcard | any type. **`<?>` ≡ `<? extends Object>`** |
| `? extends T` | **upper bound** | an unknown **subtype** of T |
| `? super T` | **lower bound** | an unknown **supertype** of T |

**Walk-through — `? extends Number` (upper bound):**
- Reads as "some unknown type that is `Number` or below (`Integer`, `Double`, ...)."
- Good for *reading*: whatever you pull out is guaranteed to be at least a `Number`.

**Walk-through — `? super Integer` (lower bound):**
- Reads as "some unknown type that is `Integer` or above (`Number`, `Object`)."
- Good for *writing*: you can safely put an `Integer` in, because any such list is wide enough to hold one.

```java
public static void printAll(List<?> list) {        // accepts a List of any type
    for (Object o : list) System.out.println(o);
}
List<? extends Number> nums;   // some unknown subtype of Number
List<? super Integer>  sink;   // some unknown supertype of Integer
```

★ **Exam Tip:** `<?>` = `<? extends Object>`. `extends` = upper bound (subtype); `super` = lower bound (supertype).

🧠 **Quick check:** `? extends Number` vs `? super Number` — which is the *upper* bound?
*Answer: `? extends Number` (subtypes, ceiling). `super` is the lower bound (supertypes, floor).*

---

## 7. Raw types & backward compatibility

**What it is:** a *raw type* is a generic class used with no label at all — like grabbing the storage box and ripping the label off. It works, but you lose the compiler's safety check.

- **Raw type** = a generic class/interface used **without a type parameter**.
  ```java
  ArrayList list = new ArrayList();   // raw type
  // roughly equivalent to:
  ArrayList<Object> list = new ArrayList<Object>();
  ```
- Raw types are **unsafe** — no compile-time type check (you can get a runtime `ClassCastException`), and the compiler emits an **unchecked warning**.
- **Why do raw types exist at all?** → for **backward compatibility with legacy code** written before generics (JDK 1.5). Old code with no `<...>` still has to compile, so Java keeps raw types around.
- **Avoid** raw types: use `new ArrayList<ConcreteType>()` instead of `new ArrayList()`.

**The point of this example:** raw types push the error from *compile time* (safe, early) back to *runtime* (dangerous, late). The generic version refuses to compile the bad call — exactly what you want.

```java
// Raw-type max is UNSAFE: this compiles but FAILS at runtime
public static Comparable max(Comparable o1, Comparable o2) { ... }
Max.max("Welcome", 23);   // runtime error (23 autoboxed to Integer, can't compare to String)

// Generic version is SAFE: this is caught at COMPILE time
public static <E extends Comparable<E>> E max(E o1, E o2) { ... }
Max.max("Welcome", 23);   // COMPILE error
```

---

## 8. ★ Type Erasure (heavily tested)

**What it is — in one sentence:** the compiler uses the `<...>` labels to check your code, then *throws the labels away* before producing the running program. At runtime, the type information is simply gone.

**Why on earth would Java do that?** To stay **backward-compatible** with all the pre-2004 code that had no generics. By erasing generics down to plain old types, new generic code produces the same kind of bytecode old code did — so they coexist. (It is **NOT** for speed and **NOT** for memory — a classic exam trap.)

**Step-by-step, what erasure does:**
1. You write `ArrayList<String>`.
2. Compiler checks every `add`/`get` against `String`. Catches your mistakes here.
3. Compiler **erases** `<String>`, leaving a plain `ArrayList` (working internally with `Object`).
4. The `.class` file that runs has no idea it was ever "of String."

- Generics are implemented by **type erasure**: the compiler **uses** the generic type info to check the code, then **erases** it.
- ⇒ **Generic type information is NOT available at run time.**
- **Why erase?** → **to be backward-compatible with legacy (raw-type) code.** (NOT for speed, NOT for memory.)

### What does the compiler replace the type with?

**How to read this:** the label is replaced by the "widest type still allowed" — `Object` if there's no bound, otherwise the bound itself.

| Generic declaration | Erased to |
|---|---|
| **Unbounded** `<T>` | **`Object`** |
| **Bounded** `<T extends Number>` | the **bound** (`Number`) |

### Only ONE class is loaded into the JVM

Because the labels are erased, the JVM never sees "String version" and "Integer version" as different classes. There is just **one** `ArrayList` class, shared by every instance.

```java
ArrayList<String>  list1 = new ArrayList<>();
ArrayList<Integer> list2 = new ArrayList<>();
System.out.println(list1 instanceof ArrayList); // true
System.out.println(list2 instanceof ArrayList); // true
// ArrayList<String> and ArrayList<Integer> are two TYPES, but
// there is only ONE ArrayList CLASS loaded into the JVM.
```

★ **Exam Tip:** "Why erasure?" → **backward compatibility**. "Unbounded `<T>` becomes?" → **Object** (bounded → its bound). "Does the JVM load both `ArrayList<String>` and `ArrayList<Date>`?" → **No — one class only.**

🧠 **Quick check:** At runtime, can your program tell whether a list was an `ArrayList<String>` or `ArrayList<Integer>`?
*Answer: No — the type was erased; both are just `ArrayList` at runtime.*

---

## 9. ★ Restrictions on Generics (memorize the list)

**The theme behind all four:** every restriction comes from the same fact — *the type is gone at runtime (erasure)*. Anything that would need to know the real type *while running* is forbidden.

**How to read this table:** column 2 = the rule, column 3 = code that's illegal, column 4 = why / how to work around it.

| # | Restriction | Example (ILLEGAL) | Note / workaround |
|---|---|---|---|
| 1 | **Cannot create an instance** of a type parameter | `E obj = new E();` | `new E()` would run at runtime, but `E` is gone (erased). |
| 2 | **Cannot create a generic array** | `E[] a = new E[100];` | Workaround: `E[] a = (E[]) new Object[capacity];` → gives an **unchecked warning**. |
| 3 | **A class's generic type parameter is NOT allowed in a static context** | `static E field;` / static method using class's `E` | The class type param belongs to an instance, not the class. |
| 4 | **Exception classes cannot be generic** — a generic class **cannot extend `Throwable`** | `class MyException<T> extends Exception {}` | Because the type info is absent at runtime. |

**Why can't you do `new T[]`? (a favourite question)** To build an array, the JVM must know the *exact* element type at runtime to size and tag it correctly. But `T` was erased — at runtime there's no real type to make the array out of. So Java forbids `new T[]`. The standard workaround is to create an `Object[]` and cast it to `T[]`, which compiles with an *unchecked warning* (you're promising the compiler you know what you're doing).

★ **Exam Tip ("cannot/except" style):** You **cannot** do `new E()`, `new E[]`, a generic exception class, or use a class's type param in a static context. Generic type args **cannot** be primitives.

🧠 **Quick check:** What single fact explains why `new E()` and `new E[]` are both banned?
*Answer: **type erasure** — `E` doesn't exist at runtime, so neither operation knows what to create.*

---

## ★ Exam Tips — one-liners
1. **Type erasure exists for backward compatibility** (not speed/memory).
2. **Unbounded `<T>` erases to `Object`**; **bounded `<T extends X>` erases to `X`**.
3. **Only ONE class** is loaded in the JVM regardless of generic type.
4. **Cannot:** `new E()`, `new E[]`, generic exception (`extends Throwable`), class type param in static context.
5. **Type argument must be a reference type**, never a primitive (`ArrayList<int>` is illegal).
6. Wildcards: `?` (any), `? extends T` (upper bound/subtype), `? super T` (lower bound/supertype); **`<?>` ≡ `<? extends Object>`**.
7. Benefits = **compile-time type checking + no casting**. Raw types exist for **backward compatibility**.
8. Generic method: **`<E>` goes before the return type.** Bounded uses **`extends`**.
9. **Code trace:** `Pair<String,Double> p = new Pair<>("Pi",3.14);` → `p.key + p.value` = string concatenation → **`Pi3.14`**.
