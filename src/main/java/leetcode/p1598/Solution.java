package leetcode.p1598;

public class Solution {
    public int minOperations(String[] logs) {
        int rst = 0;

        for(int i = 0; i < logs.length; i++) {
            if(logs[i].equals("../")) { // 如果是遇到回到上一层目录，就代表返回主文件夹所需步数就会少一步。注意要考虑已经是在主文件的情况，步数就为0
                rst = rst == 0 ? 0 : rst - 1;
            } else if (logs[i].equals("./")) {  // 如果是遇到到本层目录，就什么都不需要做

            } else {        // 如果是遇到到下一层目录，就将返回主文件夹所需步数加一
                rst++;
            }
        }

        return rst;
    }
}
