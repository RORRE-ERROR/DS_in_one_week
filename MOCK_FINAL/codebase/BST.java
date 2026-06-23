import java.util.ArrayList;
import java.util.List;

/**
 * Generic binary search tree.
 *
 * BST invariant: for every node, all values in the left subtree are smaller
 * and all values in the right subtree are larger (no duplicates kept).
 * Inorder traversal therefore yields elements in ascending sorted order.
 *
 * search/insert/delete are O(h): O(log n) when balanced, O(n) when skewed.
 *
 * @param <E> element type, must be Comparable
 */
public class BST<E extends Comparable<E>> {

    private static class TreeNode<E> {
        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        TreeNode(E element) {
            this.element = element;
        }
    }

    private TreeNode<E> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** Iterative search. O(h). */
    public boolean search(E e) {
        TreeNode<E> current = root;
        while (current != null) {
            int cmp = e.compareTo(current.element);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    /** Insert e; returns false if it was already present (no duplicates). */
    public boolean insert(E e) {
        if (root == null) {
            root = new TreeNode<>(e);
            size++;
            return true;
        }
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            int cmp = e.compareTo(current.element);
            if (cmp < 0) {
                parent = current;
                current = current.left;
            } else if (cmp > 0) {
                parent = current;
                current = current.right;
            } else {
                return false; // duplicate
            }
        }
        if (e.compareTo(parent.element) < 0) {
            parent.left = new TreeNode<>(e);
        } else {
            parent.right = new TreeNode<>(e);
        }
        size++;
        return true;
    }

    /** Inorder (Left, Node, Right) -> ascending sorted order. */
    public List<E> inorder() {
        List<E> out = new ArrayList<>();
        inorder(root, out);
        return out;
    }

    private void inorder(TreeNode<E> node, List<E> out) {
        if (node == null) {
            return;
        }
        inorder(node.left, out);
        out.add(node.element);
        inorder(node.right, out);
    }

    /** Preorder (Node, Left, Right) -> root first. */
    public List<E> preorder() {
        List<E> out = new ArrayList<>();
        preorder(root, out);
        return out;
    }

    private void preorder(TreeNode<E> node, List<E> out) {
        if (node == null) {
            return;
        }
        out.add(node.element);
        preorder(node.left, out);
        preorder(node.right, out);
    }

    /** Postorder (Left, Right, Node) -> root last. */
    public List<E> postorder() {
        List<E> out = new ArrayList<>();
        postorder(root, out);
        return out;
    }

    private void postorder(TreeNode<E> node, List<E> out) {
        if (node == null) {
            return;
        }
        postorder(node.left, out);
        postorder(node.right, out);
        out.add(node.element);
    }

    /** Height in edges; an empty tree is -1, a single node is 0. */
    public int height() {
        return height(root);
    }

    private int height(TreeNode<E> node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }
}
