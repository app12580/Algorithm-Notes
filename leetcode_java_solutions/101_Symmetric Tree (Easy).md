### description    
  Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).  
    
  For example, this binary tree [1,2,2,3,4,4,3] is symmetric:  
    
      1  
     / \  
    2   2  
   / \ / \  
  3  4 4  3  
     
    
  But the following [1,2,2,null,3,null,3] is not:  
    
      1  
     / \  
    2   2  
     \   \  
     3    3  
### solution    
```    
  class Solution {  
      public boolean isSymmetric(TreeNode root) {  
          if(root == null) {  
              return true;  
          }  
          if(root.left == null && root.right == null) {  
              return true;  
          }  
          if(root.left == null || root.right == null) {  
              return false;  
          }  
          return isSymmetricHelper(root.left, root.right);  
      }  
        
      public boolean isSymmetricHelper(TreeNode t1, TreeNode t2) {  
          if(t1 == null &&  t2 == null) {  
              return true;  
          }  
          if(t1 == null || t2 == null) {  
              return false;  
          }  
          return t1.val == t2.val && isSymmetricHelper(t1.left, t2.right) && isSymmetricHelper(t1.right, t2.left);  
      }  
  }  
```    
    
### 个人解读    
  判断：因为参数只有一个TreeNode参数，所以肯定需要中间函数  
  中间函数定义：t1和t2是否完全对称的  
    
tags:    
  -  树  
  -  递归  
  -  中间函数  
