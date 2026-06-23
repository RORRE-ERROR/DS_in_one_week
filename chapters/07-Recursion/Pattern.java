/**
 * Chapter 7 — Recursion
 * Demonstrates recursive: factorial, fibonacci, sumTo, and string reverse.
 * Compile & run:  java Pattern.java
 */
public class Pattern {

    // n! = n * (n-1)! ; base case 0! = 1
    public static long factorial(int n) {
        if (n == 0) return 1;            // base case
        return n * factorial(n - 1);     // recursive case
    }

    // fib(0)=0, fib(1)=1 ; fib(n)=fib(n-1)+fib(n-2)
    public static long fib(int n) {
        if (n < 2) return n;             // base cases
        return fib(n - 1) + fib(n - 2);  // recursive case
    }

    // sum of 0 + 1 + 2 + ... + n
    public static int sumTo(int n) {
        if (n == 0) return 0;            // base case
        return n + sumTo(n - 1);         // recursive case
    }

    // reverse a string recursively
    public static String reverse(String s) {
        if (s == null || s.length() <= 1) return s;          // base case
        return reverse(s.substring(1)) + s.charAt(0);        // recursive case
    }

    public static void main(String[] args) {
        System.out.println("factorial(5) = " + factorial(5));   // 120
        System.out.println("fib(7) = " + fib(7));               // 13
        System.out.println("sumTo(10) = " + sumTo(10));         // 55
        System.out.println("reverse(\"abc\") = " + reverse("abc")); // cba
    }
}
