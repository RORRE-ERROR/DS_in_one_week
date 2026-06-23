# Chapter 2 — Abstract Data Type (ADT) & Bag

> Exam = all MCQ. Focus: definitions ("which is true of an ADT/Bag"), reading an interface, and "except / cannot / NOT" wording.

## What this chapter is really about (read this first)

This chapter has ONE big idea, and almost every exam question circles back to it:

> **An ADT describes WHAT you can do. A data structure describes HOW it's done.**

Think of a **restaurant menu** vs the **kitchen**:
- The **menu** tells you *what* you can order (burger, salad, soda). It says nothing about *how* the chef cooks it. → This is the **ADT** (the contract / the interface).
- The **kitchen** is where the cooking actually happens — pots, pans, recipes, the exact steps. → This is the **data structure** (the implementation).

You can completely remodel the kitchen, and as long as the menu stays the same, the customer never notices. That single sentence is the heart of this chapter. If you only remember one thing for the exam, remember **WHAT vs HOW**.

The chapter then introduces one specific ADT — the **Bag** — and shows two different "kitchens" (an array and a linked list) that can both serve the same Bag "menu".

---

## 1. ADT vs Data Structure (★ the #1 definition trap)

**What & why:** Beginners often think "ADT" and "data structure" are fancy synonyms. They are not. They sit on two different sides of a wall, and the exam loves to test which side a given statement belongs to.

### The simple version

An **Abstract Data Type (ADT)** is just a *description on paper* of:
1. **WHAT** data is stored, and
2. **WHAT** operations you can perform on it.

It deliberately stays silent about **HOW**. ("Abstract" literally means *we ignore the irrelevant details* — like how the data is actually stored in memory.) Because it never commits to a "how," an ADT is **implementation-independent** and **language-independent**: it describes *what* should happen, not the machinery behind it.

A **Data Structure** is the **actual implementation** — real code, in a real language, that decides **HOW** the data is stored and **HOW** each operation runs.

```
        ADT  (the menu)                 Data Structure (the kitchen)
   ┌──────────────────────┐        ┌──────────────────────────────┐
   │ WHAT can I do?        │        │ HOW is it actually done?      │
   │ add, remove, contains │  --->  │ store in an array? a chain    │
   │ (just the promises)   │        │ of linked nodes? real code.   │
   └──────────────────────┘        └──────────────────────────────┘
        public, visible                 private, hidden inside
```

**How to read the table below:** the left column is the *question to ask*; the middle is the ADT's answer; the right is the data structure's answer. Same row = same topic, two sides of the wall.

| | ADT | Data Structure |
|---|---|---|
| Question it answers | **WHAT** (what data + what operations) | **HOW** (how stored, how done) |
| Nature | Conceptual model / specification | Concrete implementation |
| Visibility to client | Public interface | Private internal logic (hidden) |
| Language | **Independent** of any language | Written in a specific language |
| Example | List ADT, Stack ADT, Queue ADT, Bag ADT | Array, Linked list |

> One ADT can have **many** data structures. e.g. a **Stack ADT** (LIFO: push/pop) can be implemented by an **array OR a linked list**; a **Queue ADT** (FIFO: enqueue/dequeue) likewise.

(LIFO = Last In, First Out — like a stack of plates. FIFO = First In, First Out — like a queue/line of people.)

🧠 **Quick check:** "X is independent of any programming language and doesn't say how data is stored." Is X an ADT or a data structure?
*Answer: an ADT — it only describes the WHAT.*

---

## 2. Collection vs Container

**What & why:** These two words show up next to ADT/data structure and trip people up because they sound interchangeable. They are not — they map cleanly onto the WHAT/HOW wall you just learned.

- **Collection** = a *general term* for an **ADT** that stores a **group** of objects. It defines what operations can be performed on the group (add, remove, search...). → This lives on the **WHAT** side.
- **Container** = a **class** that **implements** a collection — it provides the actual implementation in a specific programming language. → This lives on the **HOW** side.

> Collection = the abstract idea (ADT side). Container = the concrete class (data-structure side).

Easy mnemonic: a **Collection** is the *idea* of holding things; a **Container** is the *real box* you put them in.

---

## 3. Advantages of ADTs — Information Hiding (★)

**What & why:** Why bother separating the menu from the kitchen at all? The payoff has a name: **information hiding**. It's the main *advantage* the exam wants you to know.

- An ADT **shares data and operations** through a well-defined interface.
- **Information hiding** = the ADT **hides the implementation details** of its data and operations from the user. You can **use the operations without knowing how they are implemented**. The public interface tells you *what* operations exist, while *how* they work stays private and out of your way.

In plain English: you get to use something powerful without needing to understand its guts. That keeps your code simpler and lets the implementation change later without breaking you.

### Vending-machine analogy (★ exam favorite)

Using an ADT is like using a **vending machine**. You press a button (an operation) and get a snack — you never open the machine or learn how its internal mechanics work.

**How to read this table:** the left column is a fact about a real vending machine; the right column is the matching fact about an ADT. Read each row left-to-right as "this... is like this."

| Vending machine | ADT (e.g. Bag) |
|---|---|
| Can only perform tasks its interface presents | Can only perform tasks specific to the ADT |
| You must understand those tasks | Must follow the ADT's operation specifications |
| Cannot access the inside of the machine | Cannot access the data inside without ADT operations |
| Usable even though you don't know what happens inside | Use the ADT even if you don't know how data is stored |
| **Usable even with new insides** | **Usable even with a new implementation** |

That last row is the punchline: swap the machine's internals (or the ADT's implementation) and you still use it exactly the same way.

🧠 **Quick check:** What is the key *advantage* of using an ADT?
*Answer: information hiding — you use the operations without knowing how they're implemented.*

---

## 4. The Bag ADT (★)

**What & why:** A **Bag** is the first concrete ADT in this course. Picture a real **shopping bag**: you toss items in, the **order doesn't matter**, and you're allowed to put in **two of the same thing** (duplicates). That mental image gives you every defining property below.

**Definition:** A Bag is a **finite collection of objects in NO particular order** that **CAN contain duplicate items**. There is **no inherent/standard size limit** by definition.

Three defining yes/no questions (memorize these — they're MCQ gold):

- Should items be stored in a specific order? → **No** (unordered).
- Can you keep repetitive (duplicate) items in the same bag? → **Yes** (duplicates allowed).
- Is there a standard limit on the number of items? → **No** inherent limit.

### Full operation list (signatures)

**How to read this table:** each row is one operation the Bag promises. The first column is the operation in UML notation `name(parameter: type): returnType`. The second column repeats the **return type** (what you get back). The third explains in words. UML is just a standard way of writing method signatures on paper.

| Operation (UML) | Returns | Description |
|---|---|---|
| `getCurrentSize(): int` | int | Current number of objects in the bag. |
| `isEmpty(): boolean` | boolean | `true` if the bag is empty. |
| `add(newEntry: T): boolean` | boolean | Add an object; `true` if added successfully. |
| `remove(): T` | T | Remove an **unspecified** object; returns it, or `null`. |
| `remove(anEntry: T): boolean` | boolean | Remove a **specific** object; `true` if removed. |
| `clear(): void` | void | Remove **all** objects. |
| `getFrequencyOf(anEntry: T): int` | int | How many times `anEntry` appears in the bag. |
| `contains(anEntry: T): boolean` | boolean | `true` if `anEntry` is in the bag. |
| `toArray(): T[]` | T[] | A **new array** containing all elements of the bag. |

(`T` is a *generic type* — a placeholder meaning "whatever type you store in the bag," e.g. `String` or `Integer`. You pick the real type when you create the bag.)

#### Slow walk-through: the two `remove` methods (the classic trap)

There are **two** methods both named `remove`. Having two methods with the same name but different parameters is called **overloading**. They behave differently and — critically — **return different things**:

- `remove()` — takes **no argument**. It grabs *any* item (you don't choose which), pulls it out, and **returns that item (type `T`)**. If the bag is empty it returns `null`.
- `remove(anEntry)` — takes **one argument** (the specific item you want gone). It tries to remove that exact item and **returns a `boolean`** (`true` = found and removed, `false` = wasn't there).

Memory hook: *no argument → returns the thing*; *give an argument → returns true/false*.

> Note the **two** `remove` methods (overloaded): `remove()` removes *any* item and returns the **item (T)**; `remove(anEntry)` removes a *specific* item and returns a **boolean**.

### Java interface example

In Java, an ADT's contract is written as an `interface`. Notice it lists *only* method signatures (the WHAT) — no bodies, no actual code (no HOW). That's exactly the ADT idea, expressed in Java.

```java
public interface BagInterface<T> {
    public int getCurrentSize();
    public boolean isEmpty();
    public boolean add(T newEntry);
    public T remove();                  // removes an unspecified entry, returns it
    public boolean remove(T anEntry);   // removes a specific entry
    public void clear();
    public int getFrequencyOf(T anEntry);
    public boolean contains(T anEntry);
    public T[] toArray();
}
```

> **Rule:** When a class header includes an **`implements`** clause, the class **must define ALL of the methods** declared in the interface (otherwise it must be declared `abstract` / fails to compile).

Why? Because the interface is a *promise*. If your class claims to `implement` it, Java forces you to actually keep every promise — write every method — or it won't compile.

🧠 **Quick check:** `remove()` vs `remove("apple")` — which returns a `boolean`?
*Answer: `remove("apple")` (the one with an argument). `remove()` returns the item itself (`T`).*

---

## 5. ArrayBag vs LinkedBag

**What & why:** Here's the big idea made concrete. The Bag is one ADT (one menu), but we can build it with two different "kitchens":
- **ArrayBag** stores the items in an **array** (think numbered shelves in a row).
- **LinkedBag** stores them as a **chain of linked nodes** (think paper-clipped notes, each pointing to the next).

Both serve the *exact same menu* (`BagInterface`). The customer (client code) can't tell which kitchen is cooking.

**How to read this table:** each row compares the same aspect across the two implementations. Same row = same topic, two different "how"s.

| | **ArrayBag** | **LinkedBag** |
|---|---|---|
| Storage | A (fixed-size) **array** | Chain of **linked nodes** |
| Fields | `T[] bag`, `int numberOfEntries`, capacity | `Node firstNode`, `int numberOfEntries` |
| Size | Fixed capacity (can resize array) | Grows as needed (allocate nodes) |
| Same interface? | Yes — both `implements BagInterface<T>` | Yes |

```
ArrayBag:   [ A ][ B ][ C ][   ][   ]   <- items sit in slots of one array
LinkedBag:  firstNode -> [A] -> [B] -> [C] -> null   <- each node points to the next
```

> Both give **identical behavior** to the client (same interface) — only the *how* differs. This is information hiding in action: swap ArrayBag for LinkedBag and client code is unchanged.

🧠 **Quick check:** If you switch your program from `ArrayBag` to `LinkedBag`, what happens to the code that *uses* the bag?
*Answer: nothing changes — both implement the same `BagInterface`, so the WHAT stays the same even though the HOW differs.*

---

## ★ Exam Tips
- **ADT = WHAT (interface/concept); Data Structure = HOW (implementation).** "Independent of programming language" / "does not say how stored" → **ADT**.
- **Collection** = ADT storing a group; **Container** = class that implements it.
- **Bag** = unordered + **duplicates allowed** + no inherent size limit.
- Key advantage of an ADT = **information hiding** (use operations without knowing internals) → **vending machine** analogy.
- A class with `implements` **must define all** interface methods.
- `remove()` returns **T** (the item); `remove(anEntry)` returns **boolean**.
- ArrayBag (array) and LinkedBag (linked nodes) implement the **same** BagInterface — only the implementation differs.
- Watch "EXCEPT / NOT / cannot": e.g. "An ADT specifies all EXCEPT ___" → answer is the *how/implementation*.
