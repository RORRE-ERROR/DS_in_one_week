# Chapter 1 — Generics: MCQ Practice (14 Questions)

> Mid-term style. Mix of concept, code-output/trace, "most efficient", and "cannot/except/illegal". Answers in `ANSWER_KEY.md`.

---

**Q1 (Concept).** What is the main benefit of using generic types in Java?
- A. Faster runtime execution because type info is compiled into machine code
- B. Stronger type checking at compile time and elimination of casting
- C. Automatic conversion of primitive types into reference types
- D. Reduced memory usage because only one object is ever created

---

**Q2 (Concept).** The primary reason Java implements generics using **type erasure** is:
- A. To improve runtime performance and reduce memory
- B. To allow generic types to be used as primitives
- C. To remain backward-compatible with legacy code that uses raw types
- D. To allow `new E()` and `new E[]` at runtime

---

**Q3 (Cannot/Illegal).** Which of the following declarations is **illegal**?
- A. `ArrayList<Integer> a = new ArrayList<>();`
- B. `ArrayList<Double> b = new ArrayList<>();`
- C. `ArrayList<String> c = new ArrayList<>();`
- D. `ArrayList<int> d = new ArrayList<>();`

---

**Q4 (Concept).** During type erasure, an **unbounded** type parameter `<T>` is replaced by ________, while a **bounded** `<T extends Number>` is replaced by ________.
- A. `Object` ; `Number`
- B. `Number` ; `Object`
- C. `null` ; `Number`
- D. `Object` ; `Object`

---

**Q5 (Code-output / trace).** What does this print?
```java
class Pair<K, V> {
    K key; V value;
    Pair(K k, V v) { key = k; value = v; }
}
// ...
Pair<String, Double> p = new Pair<>("Pi", 3.14);
System.out.println(p.key + p.value);
```
- A. `Pi 3.14`
- B. `Pi3.14`
- C. `3.14Pi`
- D. Compile error — cannot concatenate String and Double

---

**Q6 (Code-output / trace).** What is the output?
```java
ArrayList<String>  list1 = new ArrayList<>();
ArrayList<Integer> list2 = new ArrayList<>();
System.out.println(list1 instanceof ArrayList);
System.out.println(list2 instanceof ArrayList);
```
- A. `true` / `false`
- B. `false` / `true`
- C. `true` / `true`
- D. `false` / `false`

---

**Q7 (Concept).** If a program uses both `ArrayList<String>` and `ArrayList<Date>`, how many `ArrayList` **classes** are loaded into the JVM?
- A. Zero — they are erased entirely
- B. One
- C. Two — one per type argument
- D. Depends on how many objects are created

---

**Q8 (Cannot/Illegal).** Which statement is **NOT allowed** in Java generics?
- A. `public class Box<T> { }`
- B. `public class MyException<T> extends Exception { }`
- C. `public <T extends Number> void f(T t) { }`
- D. `public interface Edible<E> { }`

---

**Q9 (Cannot/Illegal — "all of the following EXCEPT").** All of the following are restrictions on generics **EXCEPT**:
- A. You cannot write `new E()` inside a generic class
- B. You cannot write `new E[100]`
- C. You cannot use the class's type parameter in a static context
- D. You cannot give a generic class more than one type parameter

---

**Q10 (Code-output / trace — raw type).** What happens with this code?
```java
ArrayList list = new ArrayList();   // raw type
list.add("hello");
String s = (String) list.get(0);
System.out.println(s);
```
- A. Compile error — raw types are forbidden
- B. Prints `hello`, but the compiler emits an **unchecked warning** for the raw type
- C. Prints `hello` with no warning at all
- D. Runtime `ClassCastException`

---

**Q11 (Concept — wildcards).** The wildcard `<?>` is equivalent to:
- A. `<? super Object>`
- B. `<? extends Object>`
- C. `<Object extends ?>`
- D. `<T>`

---

**Q12 (Concept — wildcards).** Which wildcard form means "an unknown **supertype** of `T`" (lower bound)?
- A. `? extends T`
- B. `? super T`
- C. `?`
- D. `<T extends ?>`

---

**Q13 (Code-output / trace — generic method).** Given:
```java
public static <E extends Comparable<E>> E max(E a, E b) {
    return (a.compareTo(b) > 0) ? a : b;
}
// ...
System.out.println(max("apple", "banana"));
System.out.println(max(7, 3));
```
What is printed?
- A. `apple` / `7`
- B. `banana` / `7`
- C. `banana` / `3`
- D. Compile error — `int` is not a reference type

---

**Q14 (Concept — generic method syntax).** Which is the correct declaration of a **static generic method** that returns nothing and takes one parameter `a`?
- A. `public static void <E> myMethod(E a)`
- B. `public static <E> void myMethod(E a)`
- C. `public <E> static void myMethod(E a)`
- D. `public static void myMethod<E>(E a)`
