### description    
  Given a binary tree with N nodes, each node has a different value from {1, ..., N}.  
    
  A node in this binary tree can be flipped by swapping the left child and the right child of that node.  
    
  Consider the sequence of N values reported by a preorder traversal starting from the root.  Call such a sequence of N values the voyage of the tree.  
    
  (Recall that a preorder traversal of a node means we report the current node's value, then preorder-traverse the left child, then preorder-traverse the right child.)  
    
  Our goal is to flip the least number of nodes in the tree so that the voyage of the tree matches the voyage we are given.  
    
  If we can do so, then return a list of the values of all nodes flipped.  You may return the answer in any order.  
    
  If we cannot do so, then return the list [-1].  
    
     
    
  Example 1:  
    
    
    
  Input: root = [1,2], voyage = [2,1]  
  Output: [-1]  
  Example 2:  
    
    
    
  Input: root = [1,2,3], voyage = [1,3,2]  
  Output: [1]  
  Example 3:  
    
    
    
  Input: root = [1,2,3], voyage = [1,2,3]  
  Output: []  
     
    
  Note:  
    
  1 <= N <= 100  
### solution    
```    
  Runtime: 0 ms, faster than 100.00% of Java online submissions for Flip Binary Tree To Match Preorder Traversal.  
  Memory Usage: 39.7 MB, less than 37.50% of Java online submissions for Flip Binary Tree To Match Preorder Traversal.  
    
    
  class Solution {  
   public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {  
          List<Integer> res = new ArrayList<>();  
          if(root == null || voyage.length == 0) {  
              return res;  
          }  
          int aa = dfs(res, root, voyage, 0);  
          if(aa != voyage.length) {  
              res = new ArrayList<>();  
              res.add(-1);  
              return  res;  
          }  
          return res;  
      }  
    
      //return res > 0 表示下一个要考察的位置  
      private int dfs(List<Integer> res, TreeNode root, int[] voyage, int index) {  
          if(root == null || index == voyage.length) return index;  
          int cur = voyage[index];  
          if(cur != root.val) {  
              return -1;  
          }  
          if(root.left == null && root.right == null) {  
              return index + 1;  
          }  
          index++;  
          int l = dfs(res, root.left, voyage, index);  
          if(l < 0) {  
              int r = dfs(res, root.right, voyage, index);  
              if(r < 0) return -1;  
              res.add(cur);  
              return dfs(res, root.left, voyage, r);  
          } else {  
              return dfs(res, root.right, voyage, l);  
          }  
      }  
    
  }  
```    
    
### 个人解读    
  DFS树，然后获取单向的结果。  
    
  写的时候遇到了麻烦，在思考要不要不使用递归，可能会更容易控制一些。。  
    
  致命错误：原题目是要求和前序的结果一致，然而前序遍历结果并不是唯一的。不是一定确定就是左或右，可能  
  1,2 和1,null,2的遍历结果都是1,2  
    
  总结：  
  1、通过int返回值来取代全局变量的作用  
  2、需要明确好变量的作用，进入方法时，和离开方法时，尤其是+-1这种时候的  
  3、思路没有错，需要处理好细节  
    
tags:    
  -  树  
  -  递归  
