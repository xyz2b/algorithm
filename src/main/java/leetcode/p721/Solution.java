package leetcode.p721;

import java.util.*;

public class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 对所有不同的邮箱地址进行编号，存储到一个hash表中，key为邮箱地址，value为编号
        // 将邮箱地址-用户名 存储到一个hash表中，key为邮箱地址，value为用户名，只存第一个出现的邮箱地址-用户名，重复的邮箱地址不存储

        // 使用并查集，针对邮箱地址的编号进行合并，存在相同邮箱地址的不同用户下的所有邮箱地址都会被合并到一起，同属于同一个编号
        // 并查集需要提前知道数据量的大小，所以上面还需要统计出来不同邮件地址的数量

        Map<String, Integer> emailToIndex = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        int emailCount = 0;
        for(List<String> account : accounts) {
            String name = account.get(0);

            for(int i = 1; i < account.size(); i++) {
                if(!emailToIndex.containsKey(account.get(i))) {
                    String email = account.get(i);
                    emailToIndex.put(email, emailCount++);
                    emailToName.put(email, name);
                }
            }
        }

        UnionFind uf = new UnionFind(emailCount);
        for(List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailToIndex.get(firstEmail);

            for(int i = 2; i < account.size(); i++) {
                String nextEmail = account.get(i);
                int nextIndex = emailToIndex.get(nextEmail);
                uf.union(firstIndex, nextIndex);
            }
        }

        Map<Integer, List<String>> indexToEmails = new HashMap<Integer, List<String>>();
        for(String email : emailToIndex.keySet()) {
            int index = uf.find(emailToIndex.get(email));
            List<String> account = indexToEmails.getOrDefault(index, new ArrayList<String>());
            account.add(email);
            indexToEmails.put(index, account);
        }

        List<List<String>> merged = new ArrayList<List<String>>();
        for(List<String> emails : indexToEmails.values()) {
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            List<String> account = new ArrayList<>();
            account.add(name);
            account.addAll(emails);
            merged.add(account);
        }
        return merged;
    }
}


class UnionFind {
    int[] parent;

    public UnionFind(int size) {
        parent = new int[size];

        for(int i = 0; i < size; i++) {
            // 初始时，每个节点都是指向自己，自成一颗树
            parent[i] = i;
        }
    }

    // 找到id为p的节点对应的根节点
    // 根节点指向自己
    public int find(int p) {
        while (parent[p] != p) {
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if(qRoot == pRoot) return;

        parent[qRoot] = pRoot;
    }
}