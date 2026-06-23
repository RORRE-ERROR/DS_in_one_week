# Chapter 8 — Searching & Sorting (NOTES)

> Exam = all MCQ: concept, complexity (Big-O), "which sort", identify-algorithm-from-description, binary-search precondition, trace a pass.

## What is this chapter about? (read me first)

Two everyday jobs that computers do constantly:

- **Searching** = "is this value in my list, and where?"
- **Sorting** = "put the list in order (smallest → largest)."

The interesting part is *how fast* each method is. We measure speed with **Big-O notation**. Once you understand Big-O, the rest of this chapter is mostly common sense. So let's start there.

---

## 0. Big-O in plain English (the key that unlocks everything)

**Big-O is just a way to say "how does the work grow as the list gets bigger?"** We don't count exact seconds — we count *roughly how many steps* it takes for a list of `n` items. `n` just means "the number of items."

Think of `n` as the size of your problem. Here are the four you must know:

| Big-O | Plain meaning | Picture it as... | If `n` doubles, work... |
|---|---|---|---|
| **O(log n)** | Each step throws away **half** the remaining work | Finding a word in a dictionary by opening to the middle | grows by just **one** step |
| **O(n)** | You touch **every** item once | Checking every drawer one by one | **doubles** |
| **O(n log n)** | Do an O(n) amount of work, O(log n) times | The "good" sorts (merge sort) | a bit more than doubles |
| **O(n²)** | For each item, you loop over the items again (loop inside a loop) | Comparing everyone in a room to everyone else | **quadruples** (4×) |

**How to read this:** smaller-growing is faster for big lists. The ranking from fastest to slowest is:

```
O(log n)  <  O(n)  <  O(n log n)  <  O(n²)
 fastest                              slowest
```

> 🧠 **Quick check:** A list of 1000 items. Linear search (O(n)) checks up to ~1000. Binary search (O(log n)) checks about how many?
> **Answer:** ~10 (because you can halve 1000 only about 10 times: 1000→500→250→…→1). That's the power of O(log n).

---

## 1. Searching: Linear vs Binary

**What & why:** You have a list and a value (the "key") you're hunting for. Two strategies:

- **Linear search** = start at the front and check every item one by one, like opening every drawer in a cabinet until you find your socks. Works on *any* list. Slow but foolproof: **O(n)**.
- **Binary search** = repeatedly look at the **middle** and throw away the half that can't contain your key — exactly like the "guess my number between 1 and 100" game where each guess of the midpoint cuts the range in half. Fast (**O(log n)**) — *but it only works if the list is already sorted.*

**How to read this table:** "Precondition" = what must be true before you're allowed to use it. "If NOT found" = what value the method hands back.

| | **Linear Search** | **Binary Search** |
|---|---|---|
| **Precondition** | **None** (works on any array) | **Array MUST be sorted** |
| **Method** | Compare key sequentially with each element | Compare key with the **middle** element, then halve the search range |
| **Time (worst/avg)** | **O(n)** | **O(log n)** |
| **If found** | Returns the index | Returns the index |
| **If NOT found** | Returns **-1** | Returns **`-(insertion point) - 1`** (a negative value related to where the key would be inserted) |

### Why binary search NEEDS a sorted array

Binary search works by looking at the middle item and deciding *which half to keep*. That decision —"my key is bigger, so it must be on the right" — is **only true if the list is in order**. On an unsorted list, "bigger than the middle" tells you nothing about which side the key is on, so throwing away a half would be a wild guess. **No order → no halving → binary search is meaningless.** If the data isn't sorted, you must use linear search (or sort it first).

### Binary search — the three cases

Each step, compare your `key` to the middle element (`middle`):

- `key < middle`  → search the **first (left) half**.
- `key == middle` → **found**, stop.
- `key > middle`  → search the **second (right) half**.

### Binary search trace (key = 54)

We keep two markers: `low` (left edge) and `high` (right edge). `mid` is the middle index between them. Watch how the search range shrinks each step.

Sorted list (indices 0–12):
```
[0] [1] [2] [3] [4] [5] [6] [7] [8] [9] [10][11][12]
 2   4   7  10  11  45  50  59  60  66  69  70  79
```
| Step | low | high | mid | list[mid] | Decision |
|---|---|---|---|---|---|
| 1 | 0 | 12 | 6 | 50 | 54 > 50 → go right |
| 2 | 7 | 12 | 9 | 66 | 54 < 66 → go left |
| 3 | 7 | 8 | 7 | 59 | 54 < 59 → go left |
| 4 | 7 | 6 | — | — | low > high → **not found** |

Notice the range collapsing: 13 items → 6 → 2 → 1 → empty. That handful of steps (instead of checking all 13) *is* O(log n).

54 is not in the list. It would be inserted at index 7, so binary search returns `-(7) - 1 = -8`.

> 🧠 **Quick check:** Can you binary-search the list `{9, 3, 7, 1}`?
> **Answer:** No — it isn't sorted. You'd have to sort it first, or use linear search.

---

## 2. Sorting: the four algorithms

**What & why:** Sorting puts items in order. There are many ways to do it; they differ in *how* they move items and *how fast* they are. Here's the one-line intuition for each before any detail:

- **Selection sort** — each pass, *pick the smallest* remaining item and put it in front.
- **Insertion sort** — like sorting playing cards in your hand: take the next card and *slide it back* until it sits in the right spot among the ones already sorted.
- **Bubble sort** — repeatedly *swap neighbors* that are out of order; the biggest value "bubbles/sinks" to the end each pass.
- **Merge sort** — *split* the list in half, sort each half, then *merge* the two sorted halves back together.

(One more you may hear named: **Quicksort** — *pick a "pivot" value, partition* the list into "smaller than pivot" and "bigger than pivot," then sort each side. Not coded below; just know the one-liner.)

**How to read this table:** "Stable?" means: if two items are equal, does the sort keep them in their original relative order? (Stable = yes, it preserves their order.)

| Algorithm | Core idea | Time (avg) | Stable? | Note |
|---|---|---|---|---|
| **Selection sort** | Repeatedly find the **MINIMUM** of the unsorted part and place it next | **O(n²)** | No | Simple |
| **Insertion sort** | Take each element and **insert it into the sorted sublist** at the right place | **O(n²)** | Yes | Best on **nearly-sorted / small** lists |
| **Bubble sort** | Compare **ADJACENT pairs**, swap if out of order; large values "sink" to the end | **O(n²)** | Yes | Optimize with a **boolean flag** (stop if no swap in a pass) |
| **Merge sort** | **Divide-and-conquer**: split in half, sort each half recursively, then **merge** | **O(n log n)** | **Yes** | Only O(n log n) algorithm here |

- The **three simple sorts** (selection, insertion, bubble) are all **O(n²)** — each one has a loop inside a loop.
- **Merge sort** is the **only O(n log n)** sort here and is **stable** (preserves the relative order of equal elements).

### Bubble sort — one pass on `{5, 2, 4, 1}`

Walk left to right, compare each adjacent pair, swap if the left one is bigger:
```
(5,2) swap -> 2 5 4 1
(5,4) swap -> 2 4 5 1
(5,1) swap -> 2 4 1 5    <- 5 ("largest") has sunk to the end
```
After pass 1 the **last element is the largest** (it's locked in). After pass 2 the second-to-last is the second largest, and so on. That "for each pass, walk the whole list" is the loop-inside-a-loop that makes it O(n²).

### Selection sort — first selection on `{5, 2, 4, 1}`

Scan the whole array for the **minimum** (1) and swap it into position 0:
```
{5,2,4,1} -> minimum is 1 -> swap with index 0 -> {1,2,4,5}
```
Next pass scans positions 1 onward for the next-smallest, and so on.

> 🧠 **Quick check:** A description says "compares adjacent elements and swaps them; large values move to the end." Which sort?
> **Answer:** Bubble sort.

---

## 3. Code (Java)

### Binary search (returns index, or `-(insertion point)-1`)
```java
public static int binarySearch(int[] list, int key) {
    int low = 0;
    int high = list.length - 1;
    while (low <= high) {
        int mid = (low + high) / 2;
        if (key < list[mid])      high = mid - 1; // search left half
        else if (key > list[mid]) low  = mid + 1; // search right half
        else                      return mid;     // found
    }
    return -low - 1; // not found: -(insertion point) - 1
}
```

### Selection sort — find the minimum, place it next
```java
public static void selectionSort(int[] list) {
    for (int i = 0; i < list.length - 1; i++) {
        // Find the minimum in list[i..length-1]
        int currentMin = list[i];
        int currentMinIndex = i;
        for (int j = i + 1; j < list.length; j++) {
            if (currentMin > list[j]) {
                currentMin = list[j];
                currentMinIndex = j;
            }
        }
        if (currentMinIndex != i) {          // swap if necessary
            list[currentMinIndex] = list[i];
            list[i] = currentMin;
        }
    }
}
```

### Insertion sort — insert each element into the sorted sublist
```java
public static void insertionSort(int[] list) {
    for (int i = 1; i < list.length; i++) {
        int currentElement = list[i];
        int k;
        // shift larger elements of the sorted sublist to the right
        for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
            list[k + 1] = list[k];
        }
        list[k + 1] = currentElement;        // insert at the right place
    }
}
```

### Bubble sort — compare adjacent pairs, with a flag
```java
public static void bubbleSort(int[] list) {
    boolean needNextPass = true;
    for (int k = 1; k < list.length && needNextPass; k++) {
        needNextPass = false;                // assume sorted this pass
        for (int i = 0; i < list.length - k; i++) {
            if (list[i] > list[i + 1]) {     // compare adjacent pair
                int temp = list[i];          // swap
                list[i] = list[i + 1];
                list[i + 1] = temp;
                needNextPass = true;         // a swap happened -> not yet sorted
            }
        }
    }
}
```
*(The `needNextPass` flag is the optimization: if a whole pass makes no swaps, the list is already sorted and we stop early.)*

### Merge sort — divide and conquer, then merge
```java
public static void mergeSort(int[] list) {
    if (list.length > 1) {
        // split into two halves
        int[] firstHalf = new int[list.length / 2];
        System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
        mergeSort(firstHalf);                // sort first half recursively

        int secondLength = list.length - list.length / 2;
        int[] secondHalf = new int[secondLength];
        System.arraycopy(list, list.length / 2, secondHalf, 0, secondLength);
        mergeSort(secondHalf);               // sort second half recursively

        merge(firstHalf, secondHalf, list);  // merge the two sorted halves
    }
}

private static void merge(int[] a, int[] b, int[] out) {
    int i = 0, j = 0, k = 0;
    while (i < a.length && j < b.length) {
        if (a[i] <= b[j]) out[k++] = a[i++]; // <= keeps it STABLE
        else              out[k++] = b[j++];
    }
    while (i < a.length) out[k++] = a[i++];
    while (j < b.length) out[k++] = b[j++];
}
```

**Merge sort trace on `{5, 2, 4, 1}`** — split all the way down, then merge back up in order:
```
split:   {5, 2, 4, 1}
         /          \
      {5, 2}       {4, 1}
      /   \        /   \
    {5}   {2}    {4}   {1}      <- single items are "sorted"

merge:  {5}+{2} -> {2, 5}      {4}+{1} -> {1, 4}
        {2,5} + {1,4} -> {1, 2, 4, 5}   (compare fronts, take smaller each time)
```
The "split into halves" (about log n levels) combined with merging (n work per level) is exactly **O(n log n)**.

---

## ★ Exam Tips

- **Binary search needs a SORTED array** and runs in **O(log n)**. Linear search needs **no precondition** and is **O(n)**; returns **-1** when not found.
- The **three simple sorts** (selection, insertion, bubble) are all **O(n²)**. **Merge sort = O(n log n) and STABLE** (the only one here).
- Big-O speed ranking (fastest → slowest): **O(log n) < O(n) < O(n log n) < O(n²)**.
- Identify-the-algorithm from a description:
  - "**finds the smallest** / minimum and places it first" → **Selection sort**
  - "**inserts each element into a sorted sublist**" → **Insertion sort**
  - "compares **adjacent** elements and swaps; large values sink" → **Bubble sort**
  - "**divide and conquer**, split, recurse, then **merge**" → **Merge sort**
- **Bubble sort optimization** = a **boolean flag**: if no swap happens in a pass, the list is already sorted → stop early.
- **Insertion sort is good on nearly-sorted / small** data.
- After bubble-sort pass 1, the **largest element is at the end**. Selection sort's first step puts the **smallest at the front**.
