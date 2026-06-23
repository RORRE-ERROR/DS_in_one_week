# Chapter 8 — Searching & Sorting (LEETCODE 14-Day Plan)

> Maps the Search & Sort chapter to practice problems. Core focus: **binary search** (Day 9) and **merge sort** (Day 10).

## 14-Day Schedule

| Day | Problem | Focus |
|---|---|---|
| 9 | **704. Binary Search** | Binary search on a sorted array — O(log n) |
| 10 | **912. Sort an Array** | Implement **merge sort** — O(n log n) |
| 11 | **35. Search Insert Position** | Binary search insertion point |
| 12 | **88. Merge Sorted Array** | The "merge" step of merge sort |
| 13 | **278. First Bad Version** | Binary search on a predicate |
| 14 | Review: re-solve Day 9 & 10 from scratch | Lock in binary search + merge sort |

---

## Day 9 — 704. Binary Search
Link: https://leetcode.com/problems/binary-search/

**Approach.** Classic binary search on a sorted array. Keep `low`/`high` pointers; compare the key with `nums[mid]`; if equal return `mid`; if key is smaller go left (`high = mid - 1`), else go right (`low = mid + 1`). Return -1 if not found.

**Complexity.** Time **O(log n)**, space **O(1)**.

```java
class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2; // avoids overflow
            if (nums[mid] == target)      return mid;
            else if (nums[mid] < target)  low = mid + 1;
            else                          high = mid - 1;
        }
        return -1;
    }
}
```

---

## Day 10 — 912. Sort an Array (implement MERGE SORT)
Link: https://leetcode.com/problems/sort-an-array/

**Approach.** Divide-and-conquer **merge sort**: recursively split the array into halves, sort each half, then merge the two sorted halves into a temp array and copy back. Stable, O(n log n) — avoids the O(n²) blowup that gets a naive quicksort TLE'd on adversarial input.

**Complexity.** Time **O(n log n)**, space **O(n)** (temp buffer).

```java
class Solution {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] a, int left, int right) {
        if (left >= right) return;            // base case: 0 or 1 element
        int mid = left + (right - left) / 2;
        mergeSort(a, left, mid);              // sort first half
        mergeSort(a, mid + 1, right);         // sort second half
        merge(a, left, mid, right);           // merge the two halves
    }

    private void merge(int[] a, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) temp[k++] = a[i++]; // <= keeps it STABLE
            else              temp[k++] = a[j++];
        }
        while (i <= mid)   temp[k++] = a[i++];
        while (j <= right) temp[k++] = a[j++];
        System.arraycopy(temp, 0, a, left, temp.length);
    }
}
```

---

## Day 11 — 35. Search Insert Position
Link: https://leetcode.com/problems/search-insert-position/

**Approach.** Binary search; if found return the index. If not found, `low` ends up at the **insertion point** (the index where the target would go to keep the array sorted), so return `low`. This mirrors how the chapter's binary search returns insertion-point information when the key is absent.

**Complexity.** Time **O(log n)**, space **O(1)**.

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target)      return mid;
            else if (nums[mid] < target)  low = mid + 1;
            else                          high = mid - 1;
        }
        return low; // insertion point
    }
}
```

---

## Day 12 — 88. Merge Sorted Array
Link: https://leetcode.com/problems/merge-sorted-array/

**Approach.** This is the **merge** step of merge sort. nums1 has trailing space. Merge **from the back**: compare the largest unmerged elements of nums1 and nums2 and place the larger at the end of nums1. Working backwards avoids overwriting unprocessed nums1 values.

**Complexity.** Time **O(m + n)**, space **O(1)** (in place).

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) nums1[k--] = nums1[i--];
            else                               nums1[k--] = nums2[j--];
        }
    }
}
```

---

## Day 13 — 278. First Bad Version
Link: https://leetcode.com/problems/first-bad-version/

**Approach.** Binary search on a **monotonic predicate** (`isBadVersion` flips from false to true and stays true). Find the first `true`: if `mid` is bad, the answer is `mid` or earlier (`high = mid`); else search right (`low = mid + 1`). Loop until `low == high`.

**Complexity.** Time **O(log n)**, space **O(1)**.

```java
/* The isBadVersion API is defined in the parent class VersionControl.
   boolean isBadVersion(int version); */
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int low = 1, high = n;
        while (low < high) {
            int mid = low + (high - low) / 2; // avoids overflow
            if (isBadVersion(mid)) high = mid;     // answer is mid or earlier
            else                   low = mid + 1;  // first bad is to the right
        }
        return low;
    }
}
```

---

## Resources
- Linear search animation: https://yongdanielliang.github.io/animation/web/LinearSearchNew.html
- Binary search animation: https://yongdanielliang.github.io/animation/web/BinarySearchNew.html
- Selection sort animation: https://yongdanielliang.github.io/animation/web/SelectionSortNew.html
- Insertion sort animation: https://yongdanielliang.github.io/animation/web/InsertionSortNew.html
- Bubble sort animation: https://yongdanielliang.github.io/animation/web/BubbleSortNew.html
- Merge sort animation: https://yongdanielliang.github.io/animation/web/MergeSortNew.html
- LeetCode Binary Search study plan: https://leetcode.com/explore/learn/card/binary-search/
- Chapter notes: `NOTES.md` (same folder)
