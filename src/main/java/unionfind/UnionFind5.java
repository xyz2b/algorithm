package unionfind;

// 加上路径压缩到高度为2的优化
public class UnionFind5 {
    // 索引是id，索引的值是其指向的父节点的id
    private int[] parent;
    private int[] rank;

    public UnionFind5(int size) {
        parent = new int[size];
        rank = new int[size];
        for(int i = 0; i < parent.length; i++) {
            // 初始时，每个节点都是指向自己，自成一颗树
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 找到id为p的节点对应的根节点
    // 根节点指向自己
    private int find(int p) {
        if (p != parent[p]) {
            // 递归，让当前节点 指向 当前节点父节点的 根节点，即让当前节点执行当前节点的根节点
            parent[p] = find(parent[p]);
        }
        // 最后压缩完，树高度只有2，当前节点的父节点就是根节点
        return parent[p];
    }

    // 将p和q根节点连接在一起
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if(qRoot == pRoot) return;

        if(rank[qRoot] < rank[pRoot]) {
            parent[qRoot] = pRoot;
        } else if (rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        } else {    // rank[pRoot] == rank[qRoot]
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }

    // 查看p和q是否同属于一个集合
    public boolean isConnect(int p, int q) {
        return find(p) == find(q);
    }
}
