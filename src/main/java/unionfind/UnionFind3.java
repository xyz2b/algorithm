package unionfind;

// 加上rank优化
public class UnionFind3 {
    // 索引是id，索引的值是其指向的父节点的id
    private int[] parent;
    private int[] rank;

    public UnionFind3(int size) {
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
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
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
