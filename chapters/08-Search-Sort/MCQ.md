# Chapter 8 — Searching & Sorting (MCQ)

> 14 questions. Choose the single best answer (A–D). Answers in `ANSWER_KEY.md`.

---

**Q1.** What is the worst-case time complexity of **linear search** on an array of n elements?
- A. O(1)
- B. O(log n)
- C. O(n)
- D. O(n²)

**Q2.** What is the time complexity of **binary search**?
- A. O(n)
- B. O(log n)
- C. O(n log n)
- D. O(n²)

**Q3.** Which is the **required precondition** for binary search to work correctly?
- A. The array must contain no duplicates
- B. The array must be **sorted**
- C. The array must have an even number of elements
- D. The array must contain only positive numbers

**Q4.** A sorting algorithm "repeatedly finds the **smallest** element in the unsorted part and places it next." Which algorithm is this?
- A. Insertion sort
- B. Bubble sort
- C. Selection sort
- D. Merge sort

**Q5.** A sorting algorithm "takes each element and **inserts it into the already-sorted sublist** at its correct position." Which algorithm is this?
- A. Selection sort
- B. Insertion sort
- C. Bubble sort
- D. Merge sort

**Q6.** A sorting algorithm "repeatedly compares **adjacent** pairs and swaps them if out of order, so large values sink to the end." Which algorithm is this?
- A. Selection sort
- B. Insertion sort
- C. Bubble sort
- D. Merge sort

**Q7.** What is the average time complexity of **merge sort**?
- A. O(n)
- B. O(log n)
- C. O(n log n)
- D. O(n²)

**Q8.** Which group of sorts are **all O(n²)** on average?
- A. Selection, insertion, merge
- B. Selection, insertion, bubble
- C. Bubble, merge, binary
- D. Insertion, merge, bubble

**Q9.** Which algorithm uses a **divide-and-conquer** strategy — split the array in half, sort each half recursively, then merge?
- A. Selection sort
- B. Bubble sort
- C. Merge sort
- D. Insertion sort

**Q10.** Which sorting algorithm among the four studied is **stable** AND runs in **O(n log n)**?
- A. Selection sort
- B. Bubble sort
- C. Insertion sort
- D. Merge sort

**Q11.** Given the array `{5, 2, 4, 1}`, what is the array **after one full pass** of bubble sort (comparing adjacent pairs left to right, swapping if left > right)?
- A. `{1, 2, 4, 5}`
- B. `{2, 4, 1, 5}`
- C. `{2, 4, 5, 1}`
- D. `{5, 4, 2, 1}`

**Q12.** When a linear search does **not** find the key, what does it return?
- A. 0
- B. -1
- C. null
- D. the array length

**Q13.** During binary search on a sorted array, the key is **less than** the middle element. What happens next?
- A. The search ends with "not found"
- B. The search continues in the **left (first) half**
- C. The search continues in the **right (second) half**
- D. The middle index is returned

**Q14.** Which optimization lets **bubble sort** stop early when the array is already sorted?
- A. Switching to recursion
- B. A **boolean flag** that detects when no swap occurred in a pass
- C. Sorting only the first half
- D. Doubling the array size each pass
