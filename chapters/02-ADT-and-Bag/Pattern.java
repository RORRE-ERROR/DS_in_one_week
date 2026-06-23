// Chapter 2 — ADT & Bag
// Demonstrates the Bag ADT (BagInterface<T>) implemented as a data structure (ArrayBag<T>).
// Compile & run with:  java Pattern.java
//
// Key idea: BagInterface says WHAT a bag does (the ADT); ArrayBag says HOW (the data
// structure, backed by an array). A Bag is unordered and ALLOWS duplicates.

public class Pattern {
    public static void main(String[] args) {
        // Declare via the ADT (interface) type — client never sees the implementation.
        BagInterface<String> bag = new ArrayBag<>(10);

        bag.add("apple");
        bag.add("banana");
        bag.add("apple");   // duplicate — Bags allow duplicates
        bag.add("apple");   // another duplicate

        System.out.println("Current size: " + bag.getCurrentSize());          // 4
        System.out.println("isEmpty: " + bag.isEmpty());                      // false
        System.out.println("Frequency of 'apple': " + bag.getFrequencyOf("apple")); // 3
        System.out.println("Frequency of 'banana': " + bag.getFrequencyOf("banana")); // 1
        System.out.println("Contains 'cherry': " + bag.contains("cherry"));   // false

        boolean removed = bag.remove("apple");   // remove one specific entry
        System.out.println("Removed one 'apple': " + removed);                // true
        System.out.println("Frequency of 'apple' now: " + bag.getFrequencyOf("apple")); // 2

        System.out.print("Contents: ");
        Object[] contents = bag.toArray(); // Object[] avoids a cast on the (T[]) new Object[] array
        for (Object item : contents) {
            System.out.print(item + " ");
        }
        System.out.println();
        System.out.println("Final size: " + bag.getCurrentSize());            // 3
    }
}

// ---- The ADT: defines WHAT a bag can do, not HOW (package-private) ----
interface BagInterface<T> {
    int getCurrentSize();
    boolean isEmpty();
    boolean add(T newEntry);
    boolean remove(T anEntry);   // removes a specific entry; true if removed
    int getFrequencyOf(T anEntry);
    boolean contains(T anEntry);
    T[] toArray();
}

// ---- A data structure: array-backed implementation of the Bag ADT ----
class ArrayBag<T> implements BagInterface<T> {
    private final T[] bag;
    private int numberOfEntries;

    @SuppressWarnings("unchecked")
    public ArrayBag(int capacity) {
        // Cannot do new T[capacity] (generic array creation is illegal),
        // so the standard workaround is an Object[] cast to T[].
        bag = (T[]) new Object[capacity];
        numberOfEntries = 0;
    }

    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean add(T newEntry) {
        if (numberOfEntries >= bag.length) {
            return false; // array full
        }
        bag[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean remove(T anEntry) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i].equals(anEntry)) {
                // fill the gap with the last entry, then shrink
                bag[i] = bag[numberOfEntries - 1];
                bag[numberOfEntries - 1] = null;
                numberOfEntries--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int count = 0;
        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i].equals(anEntry)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean contains(T anEntry) {
        return getFrequencyOf(anEntry) > 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] result = (T[]) new Object[numberOfEntries];
        for (int i = 0; i < numberOfEntries; i++) {
            result[i] = bag[i];
        }
        return result;
    }
}
