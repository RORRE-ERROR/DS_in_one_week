import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Evaluates a postfix (Reverse Polish Notation) expression using a stack.
 *
 * Rules: scan tokens left to right. Push operands. On an operator, pop the
 * right operand first, then the left operand, compute (left OP right), push.
 *
 * IMPORTANT for - and /: the FIRST value popped is the RIGHT operand.
 * Tokens are separated by single spaces, e.g. "6 2 3 + -".
 *
 * This mirrors the lecture's postfix evaluation algorithm.
 */
public class PostfixEvaluator {

    public static int evaluate(String expression) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : expression.trim().split("\\s+")) {
            switch (token) {
                case "+":
                case "-":
                case "*":
                case "/":
                    int right = stack.pop();
                    int left = stack.pop();
                    stack.push(apply(left, right, token));
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    private static int apply(int left, int right, String op) {
        switch (op) {
            case "+": return left + right;
            case "-": return left - right;
            case "*": return left * right;
            case "/": return left / right;
            default: throw new IllegalArgumentException("bad operator: " + op);
        }
    }
}
