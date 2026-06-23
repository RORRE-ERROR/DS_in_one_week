# Chapter 2 — ADT & Bag: Answer Key

**Q1 — B.** An ADT is a *conceptual model* defining **what** data + **what** operations, not **how**. (A/D describe memory/primitives; C describes a concrete data structure.)

**Q2 — C.** A data structure is the actual implementation: it specifies **how** data is stored and **how** operations work. (A/B/D describe the ADT/interface side.)

**Q3 — C (FALSE).** An ADT does **NOT** specify how operations are coded internally — that is precisely what it hides. A, B, D are all true of ADTs.

**Q4 — B.** **Collection** = general term for an ADT storing a group of objects; **Container** = a class that *implements* a collection. (A reverses them.)

**Q5 — B.** A Bag is a finite collection in **no particular order** that **allows duplicates**, with no inherent size limit. (A forbids order/dupes; C is a stack; D auto-removes dupes.)

**Q6 — C.** `peek()` is a **Stack** operation, not part of the Bag ADT. Bag operations: getCurrentSize, isEmpty, add, remove(), remove(anEntry), clear, getFrequencyOf, contains, toArray.

**Q7 — B.** The two `remove` methods are **overloaded**: `remove()` removes an unspecified entry and returns it (type **T**), while `remove(T anEntry)` removes a specific entry and returns a **boolean**. (A is wrong — different parameter lists = legal overloading.)

**Q8 — B.** A class with an `implements` clause **must define all** interface methods or fail to compile (unless declared `abstract`). Interfaces just declare; they provide no working code (C wrong). Interfaces can be generic (D wrong).

**Q9 — B.** Information hiding — using operations without knowing their implementation. (Inheritance/recursion/type erasure are unrelated concepts.)

**Q10 — B.** The vending-machine analogy: you operate it via its interface without accessing the internals, and it keeps working even if the internals (implementation) change.

**Q11 — D (FALSE).** Both bags implement the **same** interface, so the client calls the **same** operations regardless of which one is chosen — that is the whole point of information hiding. A, B, C are true.

**Q12 — D (EXCEPT).** An ADT specifies the data, the operations, and their signatures (return types/parameters), but **NOT** the exact algorithm/code used internally — that belongs to the data structure.
