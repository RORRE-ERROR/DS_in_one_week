# Chapter 2 — ADT & Bag: MCQ Practice (Questions)

> 12 questions, mid-term style. Choose ONE answer (A–D). Watch the "FALSE / EXCEPT / cannot" wording.

---

**Q1.** Which best defines an **Abstract Data Type (ADT)**?
- A. A specific block of memory allocated for storing data
- B. A conceptual model defining *what* data is stored and *what* operations are allowed, but not *how*
- C. A Java class that implements a linked list
- D. A built-in primitive type such as `int` or `double`

---

**Q2.** A **data structure** differs from an ADT in that it primarily specifies:
- A. What operations are available to the client
- B. The conceptual behavior, independent of any language
- C. *How* the data is stored and *how* the operations are implemented
- D. Only the names of the operations, not their parameters

---

**Q3.** Which statement about ADTs is **FALSE**?
- A. An ADT is independent of any particular programming language
- B. An ADT hides its implementation details from the user
- C. An ADT specifies exactly how each operation must be coded internally
- D. The same ADT can be implemented by more than one data structure

---

**Q4.** In course terminology, a **collection** is ____ and a **container** is ____.
- A. a class that implements an ADT / a general term for an ADT storing a group
- B. a general term for an ADT storing a group of objects / a class that implements a collection
- C. a primitive type / a reference type
- D. an array / a linked list

---

**Q5.** Which is the correct definition of the **Bag** ADT?
- A. An ordered collection that forbids duplicates
- B. A finite collection in no particular order that allows duplicates
- C. A LIFO structure with a fixed capacity of 10
- D. A sorted collection that automatically removes repeated items

---

**Q6.** Which of the following is **NOT** an operation of the Bag ADT?
- A. `getFrequencyOf(anEntry)`
- B. `contains(anEntry)`
- C. `peek()`
- D. `toArray()`

---

**Q7.** Consider this interface:
```java
public interface BagInterface<T> {
    public T remove();
    public boolean remove(T anEntry);
    public int getFrequencyOf(T anEntry);
    public T[] toArray();
}
```
Which statement is **true**?
- A. `remove()` and `remove(T anEntry)` cannot both exist — this is a duplicate method error
- B. `remove()` returns the removed item, while `remove(T anEntry)` returns a `boolean` indicating success
- C. `getFrequencyOf` must return `boolean`
- D. `toArray()` returns a single object of type `T`

---

**Q8.** A class is declared `public class ArrayBag<T> implements BagInterface<T>`. Which statement is **true**?
- A. It may implement only the methods it finds convenient
- B. It must define **all** methods declared in `BagInterface<T>`, or it will not compile (unless declared abstract)
- C. It automatically inherits working code for every interface method
- D. An interface cannot be generic, so this will not compile

---

**Q9.** The key advantage of using an ADT, where you can use its operations without knowing how they are implemented, is called:
- A. inheritance
- B. information hiding
- C. recursion
- D. type erasure

---

**Q10.** "Using an ADT is like using a vending machine." This analogy best illustrates that:
- A. ADTs are expensive to build
- B. you can use the operations through the interface without accessing or knowing the internals, and it still works even with new internals
- C. ADTs can only store food-like data
- D. every ADT must use an array internally

---

**Q11.** Which statement about **ArrayBag** and **LinkedBag** is **FALSE**?
- A. Both implement the same `BagInterface<T>`
- B. ArrayBag stores entries in an array; LinkedBag stores them in linked nodes
- C. They present the same operations to the client
- D. Choosing LinkedBag over ArrayBag changes which operations the client may call

---

**Q12.** An ADT specifies all of the following **EXCEPT**:
- A. the type of data stored
- B. the operations that can be performed
- C. the return types and parameters of the operations
- D. the exact algorithm/code used to store the data internally
