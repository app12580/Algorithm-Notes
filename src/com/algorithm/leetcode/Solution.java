package com.algorithm.leetcode;

import org.junit.Test;

import java.util.*;

public class Solution {

    public boolean isSubsequence(String s, String t) {
        //判断s是否是t的子字符串
        int i =0, j =0;
        while(i < s.length() && j < t.length()) {
            if(s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }



}



class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
