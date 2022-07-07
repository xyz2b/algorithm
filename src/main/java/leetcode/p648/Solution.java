package leetcode.p648;

import java.util.Arrays;
import java.util.List;

// Trie，字典树
public class Solution {
    class Node {
        private boolean isWord;
        private Node[] next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new Node[26];
        }

        public Node() {
            this(false);
        }
    }

    private Node root;

    public Solution() {
        root = new Node();
    }

    private void add(String s) {
        Node cur = root;
        for(int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (cur.next[c] == null) {
                cur.next[c] = new Node();
            }
            cur = cur.next[c];
        }
        if (!cur.isWord) {
            cur.isWord = true;
        }
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        // 根据所有词根创建字典树
        for(int i = 0; i < dictionary.size(); i++) {
            add(dictionary.get(i));
        }

        // 将字符串根据空格分割成多个单词
        String[] words = sentence.split(" ");

        // 遍历多个单词
        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            // 对于某个单词，去字典树中去匹配这个单词的词根(前缀字符串)
            Node cur = root;
            // j是单词中的字符索引
            int j = 0;
            for (; j < word.length(); j++) {
                int c = word.charAt(j) - 'a';
                if (cur.next[c] == null) {
                    break;
                }

                cur = cur.next[c];

                // 如果已经找到了前缀字符串(词根)，就不往下找了，保证是最短前缀
                if(cur.isWord) {
                    break;
                }
            }

            if(cur.isWord) {
                // 如果字典树中有单词的词根，j就是该词根最后一个字符在单词中的索引，所以直接根据这个索引切分字符串即可
                String replaced = word.substring(0, j + 1);
                words[i] = replaced;
            }

        }

        return String.join(" ", words);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] dictionary = new String[] {"cat","bat","rat"};
        String[] dictionary2 = new String[] {"a","b","c"};
        String sentence = "the cattle was rattled by the battery";
        String sentence2 = "aadsfasf absbs bbab cadsfafs";
        System.out.println(solution.replaceWords(Arrays.asList(dictionary2), sentence2));
    }
}
