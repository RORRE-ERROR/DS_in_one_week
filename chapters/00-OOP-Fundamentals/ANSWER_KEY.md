# Chapter 0 — OOP Fundamentals: Answer Key

| Q | Answer | Explanation |
|---|---|---|
| Q1 | **B** | Subclass redefines a parent method with the *same signature* → **method overriding** (runtime polymorphism). Overloading would be same name, *different* params. |
| Q2 | **B** | Encapsulation = hiding data with `private` fields exposed only through public accessors/mutators. A is inheritance, C is abstraction, D is overloading. |
| Q3 | **B** | `Cat` overrides `sound()`. The reference type is `Animal` but the actual object is a `Cat`, so dynamic dispatch calls `Cat.sound()` → prints `Meow`. |
| Q4 | **A** | `"Pi"+3.14` → `Pi3.14` (number becomes text). For `1 + 2 + " = sum"`, `1+2` is computed as ints **first** (=3) then concatenated → `3 = sum`. |
| Q5 | **B** | `7/2` is int division = 3; `7%2` = 1; casting makes `(double)7 / 2 = 3.5`. |
| Q6 | **A** | Valid overloading (different parameter counts). `add(2,3)=5`, `add(2,3,4)=9` → `5 9`. |
| Q7 | **C** | `count` is `static` (shared by all objects). Three constructor calls increment it to `3`. |
| Q8 | **C** | Rate is a percentage so divide by 100, then by 12 for the monthly rate: `/100/12`. |
| Q9 | **B** | Keyboard input uses `new Scanner(System.in)`. A reads a file; `System.out` is output, not input. |
| Q10 | **C** | `static` members belong to the class and are shared. A/D are false (`static` has no `this`, can't touch instance fields directly); B is false (a `final` method **cannot** be overridden). |
| Q11 | **B** | Reusing/extending an existing class is exactly what inheritance (`extends`) is for. Copy-paste and public-everything break reuse/encapsulation. |
| Q12 | **C** | Correct definitions: overloading = same name + different params (no `extends` needed, compile-time); overriding = same signature in a subclass (runtime). A, B, D are reversed/wrong. |
