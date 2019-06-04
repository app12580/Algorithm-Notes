### description    
  Given a binary tree, determine if it is height-balanced.  
    
  For this problem, a height-balanced binary tree is defined as:  
    
  a binary tree in which the depth of the two subtrees of every node never differ by more than 1.  
    
  Example 1:  
    
  Given the following tree [3,9,20,null,null,15,7]:  
    
      3  
     / \  
    9  20  
      /  \  
     15   7  
  Return true.  
    
  Example 2:  
    
  Given the following tree [1,2,2,3,3,null,null,4,4]:  
    
         1  
        / \  
       2   2  
      / \  
     3   3  
    / \  
   4   4  
  Return false.  
    
  判断一个树是否是平衡二叉树  
### solution    
```    
  class Solution {  
       public boolean isBalanced(TreeNode root) {  
          return helper(root) != -1;  
      }  
    
      private int helper(TreeNode node) {  
          if(node == null) {  
              return 0;  
          }  
          int lh = helper(node.left);  
          int rh = helper(node.right);  
          if(lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) {  
              return -1;  
          }  
          return Math.max(lh, rh) + 1;  
      }  
  }  
```    
    
### 个人解读    
  如果一个树的左右子树都是平衡二叉树，能否通过某种关联运算推出该树是平衡二叉树吗？  
  并不能，所以需要其他中间函数。  
  中间函数选取： 判断某一个树是否是平衡二叉树，并且返回该树的最大长度。  
  发现问题了，中间函数需要返回两个变量，由于java是单返回值的，这个时候解决办法：  
  Cyc：设立一个全局变量boolean  
  统一变量： 返回值大于0时候表示是平衡二叉树，且返回高度；返回值如果是-1则表示不是平衡二叉树。  
    
tags:    
  -  树  
  -  平衡二叉树  
  -  递归中间函数  
