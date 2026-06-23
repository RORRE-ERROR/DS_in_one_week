# Chapter 1 — Generics: Answer Key

| Q | Answer | Explanation |
|---|---|---|
| **Q1** | **B** | Generics give stronger **compile-time** type checking and **eliminate casting**. They do not change runtime speed/memory or handle primitives. |
| **Q2** | **C** | Erasure exists for **backward compatibility** with legacy raw-type code — not for speed or memory. |
| **Q3** | **D** | A type argument must be a **reference type**. `int` is a primitive → `ArrayList<int>` is illegal; use `ArrayList<Integer>`. |
| **Q4** | **A** | Unbounded `<T>` erases to **`Object`**; a bounded `<T extends Number>` erases to its **bound**, `Number`. |
| **Q5** | **B** | `+` with a `String` operand does **string concatenation**: `"Pi" + 3.14` → `"Pi3.14"` (no space). |
| **Q6** | **C** | Both print **`true`** — after erasure there is only one `ArrayList` class, so both instances are `instanceof ArrayList`. |
| **Q7** | **B** | **One** class only. `ArrayList<String>` and `ArrayList<Date>` are two *types* but share one *class* in the JVM. |
| **Q8** | **B** | A generic class **cannot extend `Throwable`/`Exception`** — exception classes cannot be generic (type info absent at runtime). |
| **Q9** | **D** | A generic class **can** have multiple type params (`<E1,E2,E3>`). A, B, C are real restrictions; D is not. |
| **Q10** | **B** | Raw types still compile and print `hello`, but produce an **unchecked warning**. They exist for backward compatibility. (No exception here since the element really is a String.) |
| **Q11** | **B** | By definition `<?>` ≡ **`<? extends Object>`** (unbounded wildcard = any type). |
| **Q12** | **B** | `? super T` = **lower bound** = unknown **supertype** of T. (`? extends T` = upper bound/subtype.) |
| **Q13** | **B** | `"apple".compareTo("banana") < 0` → returns `"banana"`; `7 > 3` → returns `7`. `int` is autoboxed to `Integer` (a reference type), so no error. |
| **Q14** | **B** | In a generic method the type parameter `<E>` goes **immediately before the return type**: `public static <E> void myMethod(E a)`. |
