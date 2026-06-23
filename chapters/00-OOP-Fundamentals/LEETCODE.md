# Chapter 0 — LeetCode Warm-ups

> OOP itself is **not** a LeetCode category — the final is all-MCQ, no live coding. But writing a little Java now builds fluency with arrays, loops, and `HashMap`/`HashSet` that the later data-structure chapters lean on. Here are two gentle starters.

---

## 1. Two Sum
🔗 https://leetcode.com/problems/two-sum/

Given an array `nums` and a `target`, return the indices of the two numbers that add up to `target`.

**Approach:** One pass with a `HashMap` from value → index. For each element, check whether `target - num` was already seen; if so, return the stored index and the current index. This avoids the brute-force O(n²) double loop.

**Complexity:** Time **O(n)**, Space **O(n)** (the map).

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (seen.containsKey(complement)) {
                return new int[] { seen.get(complement), i };
            }
            seen.put(nums[i], i);
        }
        return new int[] {}; // no solution (problem guarantees one exists)
    }
}
```

---

## 2. Contains Duplicate
🔗 https://leetcode.com/problems/contains-duplicate/

Return `true` if any value appears at least twice in the array, `false` if every element is distinct.

**Approach:** Add elements to a `HashSet`. `HashSet.add` returns `false` when the value is already present — so the moment `add` fails, you have a duplicate. (Alternative: sort then compare neighbours, but that is O(n log n).)

**Complexity:** Time **O(n)**, Space **O(n)**.

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {  // add returns false if already present
                return true;
            }
        }
        return false;
    }
}
```

---

## Resources

- **NeetCode.io** — https://neetcode.io/ (curated problem roadmap + clear video walkthroughs)
- **LeetCode editorial** — each problem's "Editorial" tab gives the official solution and complexity analysis
- **Baeldung (Java)** — https://www.baeldung.com/ (concise, practical Java tutorials on collections, `HashMap`, `HashSet`, etc.)
