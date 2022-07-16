package leetcode.p745;

import java.util.ArrayList;
import java.util.List;

// 两个字典树，一个存前缀（正着存），一个存后缀（反着存）
// 字典树节点的内容存的是 包含该前缀或后缀的字符串在字符串数组中的序号 的列表
// 查询时，前缀查一下，后缀查一下，如果两个查到的序号列表中有一样的，即找到了
public class WordFilter3 {
    class Node {
        private Node[] next;
        // 存放以字典树中该字符串为前缀的字符串在数组中的索引
        // 因为是按照数组的索引顺序来遍历生成字典树的，所以同一个节点的indexes列表中的索引是有序的
        private ArrayList<Integer> indexes;

        public Node() {
            next = new Node[26];
            indexes = new ArrayList<>();
        }
    }

    // 前缀字典树
    private Node prefixRoot = new Node();
    // 后缀字典树
    private Node suffixRoot = new Node();

    public WordFilter3(String[] words) {
        // 构造前缀字典树
        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            Node cur = prefixRoot;
            for (int j = 0; j < word.length(); j++) {
                int c = word.charAt(j) - 'a';
                if (cur.next[c] == null) {
                    cur.next[c] = new Node();
                    cur.next[c].indexes.add(i);
                }
                cur = cur.next[c];
                cur.indexes.add(i);
            }
        }

        // 构造后缀字典树
        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            Node cur = suffixRoot;
            for (int j = word.length() - 1; j >= 0; j--) {
                int c = word.charAt(j) - 'a';
                if (cur.next[c] == null) {
                    cur.next[c] = new Node();
                    cur.next[c].indexes.add(i);
                }
                cur = cur.next[c];
                cur.indexes.add(i);
            }
        }
    }

    public int f(String pref, String suff) {
        // 查询前缀字典树
        Node cur = prefixRoot;
        for (int i = 0; i < pref.length(); i++) {
            int c = pref.charAt(i) - 'a';
            if (cur.next[c] == null) {
                // 前缀没匹配到
                return -1;
            }
            cur = cur.next[c];
        }
        // 以pref为前缀的字符串在数组中的索引
        List<Integer> prefixIndexList = cur.indexes;

        // 查询后缀字典树
        cur = suffixRoot;
        for (int i = suff.length() - 1; i >= 0; i--) {
            int c = suff.charAt(i) - 'a';
            if (cur.next[c] == null) {
                // 后缀没匹配到
                return -1;
            }
            cur = cur.next[c];
        }
        // 以pref为前缀的字符串在数组中的索引
        List<Integer> suffixIndexList = cur.indexes;

        // 在两个索引列表中找到最大的相同的（两个列表都是有序的，双指针从最后往前找到最大相同的元素）
        int p = prefixIndexList.size() - 1;
        int s = suffixIndexList.size() - 1;
        while (p >= 0 && s >= 0) {
            int pV = prefixIndexList.get(p);
            int sV = suffixIndexList.get(s);
            if (pV == sV) {
                return pV;
            } else if (pV > sV) {
                p--;
            } else {
                s--;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        WordFilter3 wordFilter3 = new WordFilter3(new String[] {"apple"});
        System.out.println(wordFilter3.f("d", "e"));
    }
}
