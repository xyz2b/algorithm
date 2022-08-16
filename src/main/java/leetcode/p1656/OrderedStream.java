package leetcode.p1656;

import java.util.ArrayList;
import java.util.List;

public class OrderedStream {
    private String[] s;
    private int ptr;

    public OrderedStream(int n) {
        s = new String[n];
        ptr = 1;
    }

    public List<String> insert(int idKey, String value) {
        List<String> rst = new ArrayList<>();

        s[idKey - 1] = value;
        if (idKey == ptr) {
            for(int i = ptr - 1; i < s.length; i++) {
                if(s[i] != null) {
                    rst.add(s[i]);
                    ptr++;
                } else {
                    break;
                }
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        OrderedStream os= new OrderedStream(5);
        System.out.println(os.insert(3, "ccccc").toString());
        System.out.println(os.insert(1, "aaaaa").toString());
        System.out.println(os.insert(2, "bbbbb").toString());
        System.out.println(os.insert(5, "eeeee").toString());
        System.out.println(os.insert(4, "ddddd").toString());
    }
}
