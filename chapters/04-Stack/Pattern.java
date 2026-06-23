import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Chapter 4 — Stack.
 * Single compilable file: run with `java Pattern.java`.
 *
 * Demonstrates:
 *   1. A composition-based generic stack (ArrayList field).
 *   2. Postfix (Reverse Polish Notation) evaluation using that stack.
 */
public class Pattern {

    // ---------------------------------------------------------------
    // Composition-based generic stack: ArrayList as a data FIELD
    // (preferred over inheritance — does not expose ArrayList methods).
    // All access happens at the END of the list = the TOP of the stack,
    // so push/pop/peek are O(1) with no element shifting.
    // ---------------------------------------------------------------
    static class GenericStack<E> {
        private ArrayList<E> list = new ArrayList<>();

        public int getSize() {
            return list.size();
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        /** Add an item to the top (end of the list). */
        public void push(E o) {
            list.add(o);
        }

        /** View the top item without removing it. */
        public E peek() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return list.get(getSize() - 1);
        }

        /** Remove and return the top item. */
        public E pop() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return list.remove(getSize() - 1);
        }

        @Override
        public String toString() {
            return "stack: " + list.toString();
        }
    }

    // ---------------------------------------------------------------
    // Postfix (RPN) evaluation.
    // Scan left -> right:
    //   operand   -> push
    //   operator  -> pop x (RIGHT operand), pop y (LEFT operand),
    //                compute (y OP x), push the result.
    // At the end exactly ONE value must remain = the answer.
    // Order matters for '-' and '/': first popped is the RIGHT operand.
    // ---------------------------------------------------------------
    public static int evaluatePostfix(String expr) {
        GenericStack<Integer> stack = new GenericStack<>();
        String[] tokens = expr.trim().split("\\s+");

        for (String token : tokens) {
            if (isOperator(token)) {
                // Operator needs two operands already on the stack.
                if (stack.getSize() < 2) {
                    // Too few operands (e.g. "3 8 + * 9").
                    throw new IllegalArgumentException(
                        "Invalid postfix expression: too few operands for '" + token + "'");
                }
                int x = stack.pop();   // RIGHT operand (first popped)
                int y = stack.pop();   // LEFT operand
                stack.push(apply(y, x, token));   // compute (y OP x)
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        // Exactly one value should be left.
        if (stack.getSize() != 1) {
            // Too many operands (e.g. "9 8 + 7" leaves 2 elements).
            throw new IllegalArgumentException(
                "Invalid postfix expression: too many operands");
        }
        return stack.pop();
    }

    private static boolean isOperator(String t) {
        return t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/");
    }

    private static int apply(int y, int x, String op) {
        switch (op) {
            case "+": return y + x;
            case "-": return y - x;
            case "*": return y * x;
            case "/": return y / x;
            default:  throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }

    public static void main(String[] args) {
        // --- Demo 1: push / pop / peek ---
        GenericStack<String> stack = new GenericStack<>();
        stack.push("Tom");
        stack.push("Susan");
        stack.push("Kim");
        System.out.println("(1) " + stack);          // [Tom, Susan, Kim]
        System.out.println("(2) peek -> " + stack.peek());  // Kim
        System.out.println("(3) pop  -> " + stack.pop());   // Kim
        System.out.println("(4) pop  -> " + stack.pop());   // Susan
        System.out.println("(5) " + stack);          // [Tom]

        // --- Demo 2: postfix evaluation ---
        System.out.println("(6) evaluatePostfix(\"6 2 3 + -\") = "
                + evaluatePostfix("6 2 3 + -"));      // 1
        System.out.println("(7) evaluatePostfix(\"4 3 5 * +\") = "
                + evaluatePostfix("4 3 5 * +"));      // 19
    }
}
