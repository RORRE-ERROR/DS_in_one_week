import java.util.Arrays;

/**
 * Chapter 8 - Searching & Sorting.
 * Static methods over int[]: binarySearch, selectionSort, insertionSort,
 * bubbleSort (with flag), mergeSort.
 *
 * Compile & run with:  java Pattern.java
 */
public class Pattern {

    // ---------------------------------------------------------------
    // SEARCH
    // ---------------------------------------------------------------

    /** Binary search on a SORTED array. Returns the index if found,
     *  otherwise -(insertion point) - 1 (a negative value). O(log n). */
    public static int binarySearch(int[] list, int key) {
        int low = 0;
        int high = list.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (key < list[mid])      high = mid - 1; // search left half
            else if (key > list[mid]) low = mid + 1;  // search right half
            else                      return mid;     // found
        }
        return -low - 1; // not found: -(insertion point) - 1
    }

    // ---------------------------------------------------------------
    // SORTS  (all sort in place)
    // ---------------------------------------------------------------

    /** Selection sort: repeatedly find the MINIMUM of the unsorted part. O(n^2). */
    public static void selectionSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            int currentMin = list[i];
            int currentMinIndex = i;
            for (int j = i + 1; j < list.length; j++) {
                if (currentMin > list[j]) {
                    currentMin = list[j];
                    currentMinIndex = j;
                }
            }
            if (currentMinIndex != i) {            // swap if necessary
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
    }

    /** Insertion sort: insert each element into the sorted sublist. O(n^2). */
    public static void insertionSort(int[] list) {
        for (int i = 1; i < list.length; i++) {
            int currentElement = list[i];
            int k;
            for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
                list[k + 1] = list[k];             // shift right
            }
            list[k + 1] = currentElement;          // insert
        }
    }

    /** Bubble sort with a boolean flag: compare ADJACENT pairs; stop early
     *  if a pass makes no swap. O(n^2). */
    public static void bubbleSort(int[] list) {
        boolean needNextPass = true;
        for (int k = 1; k < list.length && needNextPass; k++) {
            needNextPass = false;                  // assume sorted this pass
            for (int i = 0; i < list.length - k; i++) {
                if (list[i] > list[i + 1]) {       // adjacent pair out of order
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    needNextPass = true;           // a swap occurred
                }
            }
        }
    }

    /** Merge sort: divide and conquer, then merge. O(n log n), stable. */
    public static void mergeSort(int[] list) {
        if (list.length > 1) {
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);

            int secondLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondLength);
            mergeSort(secondHalf);

            merge(firstHalf, secondHalf, list);
        }
    }

    private static void merge(int[] a, int[] b, int[] out) {
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) out[k++] = a[i++];   // <= keeps it stable
            else              out[k++] = b[j++];
        }
        while (i < a.length) out[k++] = a[i++];
        while (j < b.length) out[k++] = b[j++];
    }

    // ---------------------------------------------------------------
    // DEMO
    // ---------------------------------------------------------------

    public static void main(String[] args) {
        int[] sample = {2, 9, 5, 4, 8, 1, 6, 7};
        System.out.println("Original : " + Arrays.toString(sample));

        int[] a = sample.clone();
        selectionSort(a);
        System.out.println("Selection: " + Arrays.toString(a));

        int[] b = sample.clone();
        insertionSort(b);
        System.out.println("Insertion: " + Arrays.toString(b));

        int[] c = sample.clone();
        bubbleSort(c);
        System.out.println("Bubble   : " + Arrays.toString(c));

        int[] d = sample.clone();
        mergeSort(d);
        System.out.println("Merge    : " + Arrays.toString(d));

        // Binary search on a sorted array (use the merge-sorted result).
        int[] sorted = d; // {1,2,4,5,6,7,8,9}
        System.out.println();
        System.out.println("Sorted   : " + Arrays.toString(sorted));
        System.out.println("binarySearch(6)  -> " + binarySearch(sorted, 6)  + "  (found, index 4)");
        System.out.println("binarySearch(3)  -> " + binarySearch(sorted, 3)  + "  (not found, negative)");

        // Show the conventional -1 'not found' signal via linear meaning.
        int key = 3;
        int idx = binarySearch(sorted, key);
        int notFoundSignal = (idx >= 0) ? idx : -1;
        System.out.println("Reported index for 3 (or -1 if absent): " + notFoundSignal);
    }
}
