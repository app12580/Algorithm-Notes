### description    
  Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.  
    
  Example:  
  Given a binary tree   
            1  
           / \  
          2   3  
         / \       
        4   5      
  Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].  
    
  Note: The length of path between two nodes is represented by the number of edges between them.  
  返回树中任意两节点的最长路径  
### solution    
```    
  class Solution {  
   private int max;  
      public int diameterOfBinaryTree(TreeNode root) {  
          helper(root);  
          return max;  
      }     
        
      private int helper(TreeNode node) {  
          if(node == null) {  
              return 0;  
          }  
          int lh = helper(node.left);  
          int rh = helper(node.right);  
          int cur = lh + rh;  
          max = Math.max(cur, max);  
          return Math.max(lh, rh) + 1;  
      }  
  }  
```    
    
### 个人解读    
  树的问题一般都要想到递归。  
  然后判断：需要增加一个中间帮助函数。  
  对于一个数node而言，想要获取最长路径需要知道两个变量：左右两子树中的最长路径值；加了本节点后的最长路径是多少(左子树长度+右子树长度+1)。  
  因为中间变量函数需要两个结果，而java只能有一个返回值，所以暂且把max放在全局变量里面。  
    
tags:    
  -  树  
  -  递归  
  -  中间函数  
