import java.util.ArrayList;
import java.util.List;

/**
 * Chapter 9 — Binary Search Tree (BST)
 *
 * Single-file, compilable demo. Run with:  java Pattern.java
 *
 * Builds the BST by inserting 60, 55, 100, 45, 57, 67, 107 and prints
 * inorder / preorder / postorder / height, matching the verified values:
 *   inorder   = [45, 55, 57, 60, 67, 100, 107]   (sorted ascending)
 *   preorder  = [60, 55, 45, 57, 100, 67, 107]   (root first)
 *   postorder = [45, 57, 55, 67, 107, 100, 60]   (root last)
 *   height    = 2
 */
public class Pattern {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        int[] values = {60, 55, 100, 45, 57, 67, 107};
        for (int v : values) tree.insert(v);

        System.out.println("Inserted:  [60, 55, 100, 45, 57, 67, 107]");
        System.out.println("Inorder:   " + tree.inorder());    // sorted ascending
        System.out.println("Preorder:  " + tree.preorder());   // root first
        System.out.println("Postorder: " + tree.postorder());  // root last
        System.out.println("Height:    " + tree.height());     // 2

        System.out.println("search(57):  " + tree.search(57));  // true
        System.out.println("search(999): " + tree.search(999)); // false
    }
}

/** A generic Binary Search Tree (no duplicates). */
class BST<E extends Comparable<E>> {

    /** Inner tree node. */
    static class TreeNode<E> {
        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        TreeNode(E element) { this.element = element; }
    }

    private TreeNode<E> root;
    private int size;

    /** Returns true if the element is in the tree. O(h). */
    public boolean search(E element) {
        TreeNode<E> current = root;
        while (current != null) {
            int cmp = element.compareTo(current.element);
            if (cmp < 0)      current = current.left;
            else if (cmp > 0) current = current.right;
            else              return true;
        }
        return false;
    }

    /** Inserts an element; returns false if it is a duplicate. O(h). */
    public boolean insert(E element) {
        if (root == null) {
            root = new TreeNode<>(element);
        } else {
            TreeNode<E> parent = null, current = root;
            while (current != null) {
                int cmp = element.compareTo(current.element);
                if (cmp < 0)      { parent = current; current = current.left; }
                else if (cmp > 0) { parent = current; current = current.right; }
                else              return false; // duplicate not inserted
            }
            if (element.compareTo(parent.element) < 0)
                parent.left = new TreeNode<>(element);
            else
                parent.right = new TreeNode<>(element);
        }
        size++;
        return true;
    }

    /** Inorder (Left, Node, Right) — sorted ascending for a BST. */
    public List<E> inorder() {
        List<E> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode<E> node, List<E> out) {
        if (node == null) return;
        inorder(node.left, out);
        out.add(node.element);
        inorder(node.right, out);
    }

    /** Preorder (Node, Left, Right) — root first. */
    public List<E> preorder() {
        List<E> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(TreeNode<E> node, List<E> out) {
        if (node == null) return;
        out.add(node.element);
        preorder(node.left, out);
        preorder(node.right, out);
    }

    /** Postorder (Left, Right, Node) — root last. */
    public List<E> postorder() {
        List<E> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(TreeNode<E> node, List<E> out) {
        if (node == null) return;
        postorder(node.left, out);
        postorder(node.right, out);
        out.add(node.element);
    }

    /** Height = number of edges on the longest root-to-leaf path. Empty tree = -1, single node = 0. */
    public int height() {
        return height(root);
    }

    private int height(TreeNode<E> node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int size() { return size; }
}
