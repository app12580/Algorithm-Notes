### description    
  Given two binary trees, write a function to check if they are the same or not.  
    
  Two binary trees are considered the same if they are structurally identical and the nodes have the same value.  
    
  Example 1:  
    
  Input:     1         1  
            / \       / \  
           2   3     2   3  
    
          [1,2,3],   [1,2,3]  
    
  Output: true  
  Example 2:  
    
  Input:     1         1  
            /           \  
           2             2  
    
          [1,2],     [1,null,2]  
    
  Output: false  
### solution    
```    
  class Solution {  
      public boolean isSameTree(TreeNode p, TreeNode q) {  
         if(p == null && q == null) {  
              return true;  
          }  
          if(p == null || q == null) {  
              return false;  
          }  
          if(p.val != q.val) {  
              return false;  
          }  
          return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);  
      }  
  }  
```    
    
### 个人解读    
  树的解法套路：递归。  
  判断：不需要辅助函数。  
  这种题目熟练了以后，就跟套公式一样的。  
    
tags:    
  -  树  
  -  递归  
