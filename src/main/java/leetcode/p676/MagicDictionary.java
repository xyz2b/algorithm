package leetcode.p676;

// 字典树
// 递归+回溯
public class MagicDictionary {
    // 字典树
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
        return dfs(searchWord, root, 0, false);
    }

    // 递归 + 回溯
    // 使用递归函数 dfs(node,pos,modified)，其中的变量分别表示：当前遍历到的字典树上的节点是 node 以及待查询字符串 searchWord 的第 pos 个字符，
    // 并且在之前的遍历中是否已经替换过恰好一个字符（如果替换过，那么 modified 为true，否则为 false）
    private boolean dfs(String searchWord, Node node, int pos, boolean modified) {
        // 结束条件，遍历到searchWord结尾
        if (pos == searchWord.length()) {
            return modified && node.isWord;
        }

        // dfs 深度优先遍历
        int c = searchWord.charAt(pos) - 'a';
        if (node.next[c] != null) {
            if (dfs(searchWord, node.next[c], pos + 1, modified)) {
                return true;
            }
        }

        // 回溯
        // 逻辑走到这里有两种情况
        // 1.到searchWord的pos位置在字典树中都是完全匹配的（之后去匹配字典树中那些pos位置不匹配的字符串，并且除了pos位置不匹配外，pos+1位置往后都匹配）
        // 2.searchWord的pos位置的字符在字典树中不匹配（除了pos位置不匹配外，pos+1位置往后都匹配）
        // 对于上面两种情况，此时就从pos+1位置往后，如果它们除了pos位置不匹配外，pos+1位置往后都匹配，则满足条件
        if (!modified) {
            for(int i = 0; i < 26; i++) {
                // 在字典树中找到和searchWord的pos位置不匹配的字符的Node，继续往后遍历字典树（注意此时已经是替换过一个字符了，所以modified是true）
                // 如果本身searchWord的pos位置的字符在字典树中不匹配，那么node.next[pos]是null的
                // 如果本身searchWord的pos位置的字符在字典树中是匹配的，要跳过字典树中这个字符，即i != pos
                if (i != c && node.next[i] != null) {
                    if (dfs(searchWord, node.next[i], pos + 1, true)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[] {"hello","hallo","leetcode"});
        System.out.println(magicDictionary.search("hello")); // 返回 False
    }
}
