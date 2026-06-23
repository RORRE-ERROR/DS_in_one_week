# Chapter 8 — Searching & Sorting (ANSWER KEY)

| Q | Answer |
|---|---|
| 1 | C |
| 2 | B |
| 3 | B |
| 4 | C |
| 5 | B |
| 6 | C |
| 7 | C |
| 8 | B |
| 9 | C |
| 10 | D |
| 11 | B |
| 12 | B |
| 13 | B |
| 14 | B |

---

**Q1 — C. O(n).** Linear search compares the key with each element sequentially. In the worst case (key absent or last) it checks all n elements → **O(n)**. No precondition required.

**Q2 — B. O(log n).** Binary search halves the search range each step, giving **O(log n)**.

**Q3 — B. The array must be sorted.** Binary search relies on comparing with the middle element and discarding half; this only works if the data is **ordered**. Duplicates, parity, and sign are irrelevant.

**Q4 — C. Selection sort.** Its defining idea: repeatedly find the **minimum** of the unsorted portion and place it at the front of that portion.

**Q5 — B. Insertion sort.** It builds a sorted sublist and **inserts** each new element into its correct position within that sublist.

**Q6 — C. Bubble sort.** It compares **adjacent** pairs and swaps out-of-order ones; large values "sink" to the end pass by pass.

**Q7 — C. O(n log n).** Merge sort splits into halves (log n levels) and merges (O(n) per level) → **O(n log n)**.

**Q8 — B. Selection, insertion, bubble.** The three simple sorts are all **O(n²)**. Merge sort is O(n log n), and binary is a search, not a sort.

**Q9 — C. Merge sort.** Divide-and-conquer: split in half, sort each half recursively, then **merge** the two sorted halves.

**Q10 — D. Merge sort.** It is the only one of the four that is both **stable** and **O(n log n)**.

**Q11 — B. `{2, 4, 1, 5}`.** One pass of bubble sort on `{5,2,4,1}`:
`(5,2)`→swap `2 5 4 1`; `(5,4)`→swap `2 4 5 1`; `(5,1)`→swap `2 4 1 5`. The largest value (5) has bubbled to the last position.

**Q12 — B. -1.** Linear search returns the index when found, or **-1** when the key is not in the array.

**Q13 — B. The left (first) half.** If `key < middle`, the key (if present) must be in the lower half, so the search continues on the **left half** (`high = mid - 1`).

**Q14 — B. A boolean flag.** A `needNextPass` flag is reset to `false` each pass and set to `true` only when a swap occurs; if a pass makes no swap, the array is sorted and the algorithm stops early.
