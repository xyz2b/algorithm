package leetcode.p901;


import java.util.ArrayList;
import java.util.List;

public class StockSpanner {
    class Node {
        // 前面比当前节点元素值大的第一个节点的索引（该节点向前的跨度终止于此）
        // -1就代表该节点前面的节点都小于等于该节点
        private int maxIndex;
        // 当前节点的元素值
        private int num;
        // 当前节点的跨度
        private int span;

        public Node(int num) {
            this.num = num;
            this.span = 1;
            maxIndex = -1;
        }

        public Node(int num, int maxIndex, int max, int span) {
            this.num = num;
            this.maxIndex = maxIndex;
            this.span = span;
        }
    }

    private List<Node> list;

    public StockSpanner() {
        list = new ArrayList<>();
    }

    public int next(int price) {
        // 没有节点时，新加一个节点，新加的这个节点跨度为1，因为只有它自己，前面没有其他元素
        // 第一个节点的maxIndex为-1，因为前面没有其他元素了，所以也不可能有元素比它还大，-1就代表该节点前面的节点都小于等于该节点
        if(list.isEmpty()) {
            list.add(new Node(price));
            return 1;
        }

        // 新建一个节点
        Node node = new Node(price);
        for(int i = list.size() - 1; i >= 0;) {
            if(price < list.get(i).num) {
                // 如果新加的price遇到第一个大于其的节点，此时将该节点index记录到新加节点的maxIndex处，
                // 表示新加节点前面第一个大于此节点的索引（向前的跨度终止于此），然后将新加节点加入到列表末尾，退出循环
                node.maxIndex = i;
                list.add(node);
                break;
            } else {
                // 如果新加的price大于等于当前遍历的节点，将新建节点的跨度值加上当前遍历节点的跨度值
                // 同时下一个应该遍历的节点 是 当前遍历节点向前跨度终止的节点，即当前遍历节点maxIndex节点，因为maxIndex节点是当前节点之前第一个大于当前节点的
                // maxIndex节点(不包括该节点)和当前节点之间的值都小于等于当前节点，又因为新加的节点大于等于当前节点，所以maxIndex节点(不包括该节点)和当前节点之间的值都小于等于新加的节点
                // 所以可以直接比较当前节点的maxIndex节点 与 新加的节点，中间的节点都可以略过，只累计跨度不需要访问，所以直接将遍历的索引i指向maxIndex，继续下一轮循环
                // 直到找到第一个大于新加元素的节点，或者直到找到前面第一个maxIndex为-1的节点
                // maxIndex为-1表示，该节点前面一直到第一个节点都没有比当前节点大的节点，所以如果遇到第一个maxIndex为-1的节点，也就不需要继续遍历了(说明maxIndex为-1的节点前面的节点都小于等于新加元素)，直接累加maxIndex为-1的节点的跨度退出循环即可
                node.span += list.get(i).span;
                if(list.get(i).maxIndex == -1) {
                    list.add(node);
                    break;
                }
                i = list.get(i).maxIndex;
            }
        }

        return node.span;
    }

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(1));
        System.out.println(stockSpanner.next(2));
        System.out.println(stockSpanner.next(3));
        System.out.println(stockSpanner.next(4));
        System.out.println(stockSpanner.next(5));
        System.out.println(stockSpanner.next(6));
        System.out.println(stockSpanner.next(7));
    }

}
