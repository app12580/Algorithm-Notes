package com.algorithm.leetcode;

import org.junit.Test;

import java.awt.*;
import java.util.*;
import java.util.List;

class Fraction {
    long n, d;  //numerator 分子 denominator 分母

    Fraction(long n, long d) {
        long g = gcd(n, d);
        this.n = n / g;
        this.d = d / g;
    }

    public void iadd(Fraction other) {
        long numerator = this.n * other.d + this.d * other.n;
        long denominator = this.d * other.d;
        long g = Fraction.gcd(numerator, denominator);
        this.n = numerator / g;
        this.d = denominator / g;
    }

    static long gcd(long x, long y) {
        return x != 0 ? gcd(y % x, x) : y;
    }
}

public class Solution {





    @Test
    public void test4() {
        String[] split = "2312.123".split("\\.");
        int[] arr = {1,2,0};
    }

    @Test
    public void test3() {

//        int[][] a = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};    
        int[][] a = {{1}};

        ListNode head = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;

    }


    @Test
    public void test2() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(4);
        queue.offer(1);
        queue.offer(12);
        queue.offer(4);
        queue.offer(8);
        queue.offer(2);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }

    @Test
    public void test1() {
        int[] arr = {3, 3, 7, 7, 10, 11, 11};
        System.out.println(4 % 3);
        System.out.println(-4 % 3);
        System.out.println(4 % -3);
        System.out.println(-4 % -3);

        Objects.equals(1, 2);
//        System.out.println(-1 << 2);    
//        System.out.println(Integer.toBinaryString(0));    
//        System.out.println(Integer.toBinaryString(-1));    
//        System.out.println(Integer.toBinaryString(-2));    
//        System.out.println(Integer.toBinaryString(-4));    
    }

    public TreeNode transArrToNode(Integer[] arr) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        queue.offer(root);
        for (int i = 1; i < arr.length; i++) {
            TreeNode poll = queue.poll();
            if (arr[i] == null) {
                poll.left = null;
            } else {
                poll.left = new TreeNode(arr[i]);
                queue.offer(poll.left);
            }
            if (i == arr.length - 1) continue;
            if (arr[i + 1] == null) {
                poll.right = null;
            } else {
                poll.right = new TreeNode(arr[i + 1]);
                queue.offer(poll.right);
            }
            i++;
        }
        return root;
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}


class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {
    }

    public Node(int _val, Node _next, Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
}    
    
