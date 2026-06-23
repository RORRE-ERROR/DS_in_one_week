import java.util.HashMap;
import java.util.Map;

/**
 * Trie (prefix tree) for lowercase strings.
 *
 * Each node holds a map from the next character to a child node, plus a flag
 * marking whether a complete word ends at that node. Search and insert are
 * O(L) where L is the length of the word (independent of how many words are
 * stored). This is LeetCode 208.
 */
public class Trie {

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord = false;
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /** Insert a word. O(L). */
    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            cur = cur.children.computeIfAbsent(c, k -> new TrieNode());
        }
        cur.isWord = true;
    }

    /** Walk down following the characters of s; null if a step is missing. */
    private TrieNode walk(String s) {
        TrieNode cur = root;
        for (char c : s.toCharArray()) {
            cur = cur.children.get(c);
            if (cur == null) {
                return null;
            }
        }
        return cur;
    }

    /** True only if the exact word was inserted. O(L). */
    public boolean search(String word) {
        TrieNode node = walk(word);
        return node != null && node.isWord;
    }

    /** True if any inserted word starts with the prefix. O(L). */
    public boolean startsWith(String prefix) {
        return walk(prefix) != null;
    }
}
