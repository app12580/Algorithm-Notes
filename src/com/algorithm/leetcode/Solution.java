package com.algorithm.leetcode;

import org.junit.Test;

import java.util.*;

public class Solution {

    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] indexs = new ListNode[k];
        ListNode init = new ListNode(-1);
        init.next = root;
        for(int i = 0; i < k; i++) {
            //初始化节点
            indexs[i] = init.next;
            if(init.next == null) {
                for(int j = 0; j < i; j++) {
                    indexs[j].next = null;
                }
                return indexs;
            }
            init = init.next;
        }
        int c = -2;
        while((c = gogogo(indexs[k -1], k)) == -2) {
            for(int i = 0; i < k; i++) {
                int j = i + 1;
                while(j-- > 0) {
                    indexs[i] = indexs[i].next;
                }
            }
        }
        for(int i = 0; i < c; i++) {
            //前面的推进了以后，后续的也需要跟着向后移动一步
            for(int m = i; m < k; m++) {
                indexs[m] = indexs[m].next;
            }
        }
        //现在indexs里面装的都是每一个切片里面最末尾的那一个了
        ListNode[] res = new ListNode[k];
        res[0] = root;
        for(int i = 0; i < k - 1; i++) {
            ListNode next = indexs[i].next;
            indexs[i].next = null;
            res[i + 1] = next;
        }
        return res;
    }

    /**
     * n表示移动几步
     * @param node
     * @param n
     * @return -2 表示成功， 其他的表示失败，返回前面还有几个node
     */
    public int gogogo(ListNode node, int n) {
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(node == null) {
                return count - 1;
            } else {
                count++;
                node = node.next;
            }
        }

        return -2;
    }


    @Test
    public  void test1() {
        ListNode listNode = ListNode.makeListNode(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        ListNode[] listNodes = splitListToParts(listNode, 3);
    }
    
}



class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    public static ListNode makeListNode(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode next = null;
        ListNode cur = null;
        for (int i = arr.length - 1; i >= 0; i--) {
            cur = new ListNode(arr[i]);
            cur.next = next;
            next = cur;
        }
        return cur;
    }

    @Override
    public String toString() {
        List<Integer> list = new ArrayList<>();
        ListNode cur = this;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        return list.toString();
    }
}
