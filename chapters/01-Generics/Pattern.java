import java.util.List;
import java.util.Arrays;

/**
 * Chapter 1 — Generics reference patterns.
 * Demonstrates: a generic class, a bounded generic method, and a wildcard method.
 * Run with:  java Pattern.java
 */
public class Pattern {

    // (1) Generic class: Box<T> holds one value of any reference type.
    static class Box<T> {
        private T item;
        public Box(T item) { this.item = item; }
        public T get() { return item; }
        public void set(T item) { this.item = item; }
    }

    // (2) Bounded generic method: T must be Comparable<T>. <T> goes before the return type.
    static <T extends Comparable<T>> T max(T a, T b) {
        return (a.compareTo(b) > 0) ? a : b;
    }

    // (3) Wildcard method: accepts a List of ANY type ( <?> == <? extends Object> ).
    static void printAll(List<?> list) {
        for (Object o : list) {
            System.out.print(o + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // --- Generic class Box<T> ---
        Box<String> strBox = new Box<>("Hello Generics");
        Box<Integer> intBox = new Box<>(42);
        System.out.println("strBox holds: " + strBox.get());
        System.out.println("intBox holds: " + intBox.get());

        // --- Bounded generic method max() ---
        System.out.println("max(\"apple\", \"banana\") = " + max("apple", "banana")); // banana
        System.out.println("max(7, 3) = " + max(7, 3));                               // 7
        System.out.println("max(2.5, 9.1) = " + max(2.5, 9.1));                       // 9.1

        // --- Wildcard method printAll(List<?>) ---
        System.out.print("List<String>:  ");
        printAll(Arrays.asList("red", "green", "blue"));
        System.out.print("List<Integer>: ");
        printAll(Arrays.asList(1, 2, 3, 4));
    }
}
