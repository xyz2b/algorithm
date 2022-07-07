package trie;

import java.util.TreeMap;

// 每个节点表示字符串中的一个字符
// 每个节点都有指向下面一个字符节点的指针集合（因为字符串中下一个字符可能是任意字符，所以是节点指针集合）
// 如果仅仅是包含小写字母的字符串，这个字符节点指针集合可以使用26大小的数组
public class Trie {
    class Node {
        private boolean isWord;
        private TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public void add(String s) {
        Node cur = root;
        for(int i = 0; i < s.length(); i++) {
            if(cur.next.get(s.charAt(i)) == null) {
                cur.next.put(s.charAt(i), new Node());
            }
            cur = cur.next.get(s.charAt(i));
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    public boolean contains(String s) {
        Node cur = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }

        return cur.isWord;
    }

    public boolean prefix(String s) {
        Node cur = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }

        return true;
    }
}
