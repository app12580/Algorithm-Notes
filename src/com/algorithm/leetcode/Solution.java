package com.algorithm.leetcode;

import org.junit.Test;

import java.util.*;

public class Solution {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        // [1,2] 表示1指向2
        int[] parent = new int[2001];
        for (int i = 0; i < parent.length; i++) parent[i] = i;

        for (int[] edge: edges){
            int f = edge[0], t = edge[1];
            //如果f和t有相同的祖先，那么本次的边就是结果
            if (find(parent, f) == find(parent, t)) return edge;
            else parent[find(parent, f)] = find(parent, t);     //让f的祖先降级，改成t的祖先
        }

        return new int[2];
    }

    private int find(int[] parent, int f) {
        if (f != parent[f]) {
            parent[f] = find(parent, parent[f]);
        }
        return parent[f];
    }

    @Test
    public void test1() {
//        int[][] arr = {{2,1},{3,1},{4,2},{1,4}};
        int[][] arr = {{1,2}, {2,3}, {3,4}, {4,1}, {1,5}};
        System.out.println(Arrays.toString(findRedundantDirectedConnection(arr)));
     }

}