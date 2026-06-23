/**
 * Classic searching and sorting algorithms over int[].
 *
 * Complexity summary:
 *   binarySearch  O(log n)  -- requires a SORTED array
 *   selectionSort O(n^2)
 *   insertionSort O(n^2)    -- fast on nearly-sorted input
 *   bubbleSort    O(n^2)    -- early-exit when a pass makes no swaps
 *   mergeSort     O(n log n) -- divide and conquer, stable
 */
public class Sorts {

    /** Returns index of key in sorted array, or -1 if absent. */
    public static int binarySearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (key < a[mid]) {
                high = mid - 1;
            } else if (key > a[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(a, i, min);
            }
        }
    }

    public static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int cur = a[i];
            int k = i - 1;
            while (k >= 0 && a[k] > cur) {
                a[k + 1] = a[k];
                k--;
            }
            a[k + 1] = cur;
        }
    }

    public static void bubbleSort(int[] a) {
        boolean needNextPass = true;
        for (int i = 1; i < a.length && needNextPass; i++) {
            needNextPass = false; // assume sorted until a swap proves otherwise
            for (int j = 0; j < a.length - i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    needNextPass = true;
                }
            }
        }
    }

    public static void mergeSort(int[] a) {
        if (a.length <= 1) {
            return;
        }
        int mid = a.length / 2;
        int[] left = new int[mid];
        int[] right = new int[a.length - mid];
        System.arraycopy(a, 0, left, 0, mid);
        System.arraycopy(a, mid, right, 0, a.length - mid);
        mergeSort(left);
        mergeSort(right);
        merge(a, left, right);
    }

    private static void merge(int[] a, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            a[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length) {
            a[k++] = left[i++];
        }
        while (j < right.length) {
            a[k++] = right[j++];
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
