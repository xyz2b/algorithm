package leetcode.p676;

import javax.naming.NoPermissionException;

public class MagicDictionary {
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

    private void add(String s) {
        Node cur = root;
        for(int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';

            if(cur.next[c] == null) {
                cur.next[c] = new Node();
            }

            cur = cur.next[c];
        }

        if (!cur.isWord) {
            cur.isWord = true;
        }
    }

    public MagicDictionary() {
        root = new Node();
    }

    public void buildDict(String[] dictionary) {
        for(int i = 0; i < dictionary.length; i++) {
            add(dictionary[i]);
        }
    }

    public boolean search(String searchWord) {
        Node cur = root;

        int flag = 0;
        int i = 0;
        for(; i < searchWord.length(); i++) {
            int c = searchWord.charAt(i) - 'a';

            if (cur.next[c] == null) {
                flag = 1;
                break;
            }

            cur = cur.next[c];
        }

        boolean result = false;
        if (flag == 1) {
            i++;

            for (int j = 0; j < 26; j++) {
                if (cur.next[j] == null) {
                    continue;
                }

                cur = cur.next[j];

                if(i == searchWord.length() - 1) {  // 如果经过上面的遍历，searchWord只有最后一个字符不一样
                    if (cur.isWord) {
                        result = true;
                        break;
                    }
                }

                for(; i < searchWord.length(); i++) {
                    int c = searchWord.charAt(i) - 'a';
                    if (cur.next[c] == null) {
                        return result;
                    }

                    cur = cur.next[c];
                }

                if (cur.isWord) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }
}
