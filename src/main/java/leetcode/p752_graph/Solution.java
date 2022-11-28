package leetcode.p752_graph;

import java.util.*;

// 状态转移-->图
// 从一个状态转移到另一个状态，就是图的两个顶点之间的边
// 求状态转移的最短路径就是使用bfs求无权图两个顶点之间的最短路径
// 字符串表示状态，就需要使用Hash Set作为visited，使用HashMap作为记录距离的信息和pre等
public class Solution {
    private HashMap<String, Integer> distance;

    private HashSet<String> deadset;
    private String source;
    private String target;

    // deadends不可达的顶点
    // target目标顶点
    // "0000"起始顶点
    public int openLock(String[] deadends, String target) {
        this.deadset = new HashSet<>(deadends.length);
        this.deadset.addAll(Arrays.asList(deadends));

        this.target = target;
        this.source = "0000";
        this.distance = new HashMap<>();

        if(deadset.contains(target)) return -1;
        if(deadset.contains(source)) return -1;
        if(target.equals(source)) return 0;

        bfs(source);

        return distance.get(target) != null ? distance.get(target) : -1;
    }

    private void bfs(String s) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(s);
        distance.put(s, 0);

        while (!queue.isEmpty()) {
            String curs = queue.poll();

            char[] curarray = curs.toCharArray();
            ArrayList<String> nextss = new ArrayList<>();
            // 计算下一个可能的结果，转动一位转盘，总共有4个转盘
            for(int i = 0; i < 4; i++) {
                // 保存下需要修改的位
                char o = curarray[i];

                // 每一位都可以+1或-1，0-1=9，9+1=0

                // +1
                // curarray[i] - '0' 将一位数字字符转成数字，9+1=10 --> %10 --> 0
                curarray[i] = Character.forDigit((curarray[i] - '0' + 1) % 10, 10);
                nextss.add(new String(curarray));

                // 恢复位
                curarray[i] = o;

                // -1
                // curarray[i] - '0' 将一位数字字符转成数字，-1可以转换为+9再%10
                curarray[i] = Character.forDigit((curarray[i] - '0' + 9) % 10, 10);
                nextss.add(new String(curarray));

                // 恢复位
                curarray[i] = o;
            }

            for(String nexts : nextss) {
                if(!distance.containsKey(nexts) && !deadset.contains(nexts)) {
                    queue.offer(nexts);
                    distance.put(nexts, distance.get(curs) + 1);

                    if(nexts.equals(target)) {
                        break;
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        Solution solution = new Solution();
        System.out.println(solution.openLock(deadends, target));
    }
}
