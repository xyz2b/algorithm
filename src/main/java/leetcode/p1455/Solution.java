package leetcode.p1455;

// 字典树
public class Solution {
    class Node {
        private int[] Node;
        private int value;

        public Node(int value) {
            Node = new int[26];
            this.value = value;
        }

    }

    private Node root;


    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] strings = sentence.split(" ");

        int flag = 1;
        for(int i = 0; i < strings.length; i++) {
            if(strings[i].length() < searchWord.length()) {
                continue;
            }


            for(int j = 0; j < searchWord.length(); j++) {
                if(searchWord.charAt(j) != strings[i].charAt(j)) {
                    flag = 0;
                    break;
                }
            }

            if(flag == 1) {
                return i + 1;
            } else {
                flag = 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String sentence = "i am tired";
        String searchWord = "pro";

        Solution solution = new Solution();
        System.out.println(solution.isPrefixOfWord(sentence, searchWord));
    }

}
