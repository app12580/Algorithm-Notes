### description    
  Given a binary tree, return the tilt of the whole tree.  
    
  The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.  
    
  The tilt of the whole tree is defined as the sum of all nodes' tilt.  
    
  Example:  
  Input:   
           1  
         /   \  
        2     3  
  Output: 1  
  Explanation:   
  Tilt of node 2 : 0  
  Tilt of node 3 : 0  
  Tilt of node 1 : |2-3| = 1  
  Tilt of binary tree : 0 + 0 + 1 = 1  
  Note:  
    
  The sum of node values in any subtree won't exceed the range of 32-bit integer.  
  All the tilt values won't exceed the range of 32-bit integer.  
    
    
### solution    
```    
  class Solution {  
       int res = 0;  
    
      public int findTilt(TreeNode root) {  
          calSum(root);  
          return res;  
      }  
    
      private int calSum(TreeNode root) {  
          if(root == null) {  
              return 0;  
          }  
          if(root.left == null && root.right == null) { //这一个判断也可以省略掉  
              return root.val;  
          }  
          int left = calSum(root.left);  
          int right = calSum(root.right);  
          res += Math.abs(left - right);  
          return left + right + root.val;  
      }  
    
  }  
```    
    
### 个人解读    
  主要疑虑在于效率上，为了防止指数扩展，感觉需要一个辅助函数。  
  因为在遍历子树的时候，既需要统计和，也需要计算tilt，限于java的单返回值，考虑全局变量。  
    
tags:    
  -  递归  
  -  树  
  -  全局变量  
