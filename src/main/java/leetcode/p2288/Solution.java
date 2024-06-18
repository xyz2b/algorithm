package leetcode.p2288;

public class Solution {
    public String discountPrices(String sentence, int discount) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        while (i <= sentence.length()) {
            if(((i - 2 >= 0 && sentence.charAt(i - 2) == ' ') || (i - 1 == 0)) && sentence.charAt(i - 1) == '$') {
                sb.append(sentence.charAt(i-1));
                if(i >= sentence.length()) {
                    break;
                }
                if(sentence.charAt(i) >= '0' && sentence.charAt(i) <= '9') {
                    // 遇到空格之前是否有其他字符，如果有就不是价格
                    boolean notNum = false;
                    long price = sentence.charAt(i) - '0';
                    StringBuilder sbNum = new StringBuilder();
                    sbNum.append(sentence.charAt(i));
                    int j = i + 1;
                    while (j < sentence.length()) {
                        if(sentence.charAt(j) == ' ') {
                            break;
                        }
                        if(sentence.charAt(j) < '0' || sentence.charAt(j) > '9') {
                            notNum = true;
                            break;
                        }
                        sbNum.append(sentence.charAt(j));
                        price = price * 10 + (sentence.charAt(j) - '0');
                        j++;
                    }

                    // 遇到空格之前有不是数字的字符
                    if(notNum) {
                        sb.append(sbNum);
                    } else {    // 价格
                        sb.append(String.format("%.2f", price * ((100 - discount) * 0.01)));
                    }
                    i = j;
                } else {
                    sb.append(sentence.charAt(i));
                    i++;
                }
            } else {
                sb.append(sentence.charAt(i-1));
            }
            i++;
        }

        return sb.toString();
    }


    /**
     * 首先我们将给定的字符串 sentence 根据空格进行分割，得到其中的每一个单词。随后我们遍历每个单词，如果该单词：
          以 $ 开头；
          后续至少有一个字符，且均在 [0,9] 中；
     * 那么该单词就表示一个价格。我们提取后续的字符，转换成整数，计算折扣（即乘上 1 - discount / 100.0），保留两位小数，再转换回字符串，并添加开头的 $ 即可。
     * 当所有单词遍历完成之后，我们就可以再加上空格，得到最终的字符串。
     * */
    public String discountPrices2(String sentence, int discount) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.charAt(0) == '$' && isNumeric(word.substring(1))) {
                double price = Long.parseLong(word.substring(1)) * (1 - discount / 100.0);
                words[i] = String.format("$%.2f", price);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(words[i]);
        }
        return sb.toString();
    }

    public boolean isNumeric(String s) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String sentence = "$2$3 $10 $100 $1 200 $33 33$ $$ $99 $99999 $9999999999";
        int discount = 0;
        Solution solution = new Solution();
        System.out.println(solution.discountPrices(sentence, discount));
    }
}
