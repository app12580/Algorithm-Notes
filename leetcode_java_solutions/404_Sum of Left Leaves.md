### description    
  Find the sum of all left leaves in a given binary tree.  
    
  Example:  
    
      3  
     / \  
    9  20  
      /  \  
     15   7  
    
  There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.  
  获取所有左叶子节点的和。  
### solution    
```    
  class Solution {  
       public int sumOfLeftLeaves(TreeNode root) {  
          if(root == null) {  
              return 0;  
          }  
          if(root.left != null && root.left.left == null && root.left.right == null) {  
              return root.left.val + sumOfLeftLeaves(root.right);  
          } else {  
              return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);  
          }  
      }  
  }  
```    
    
### 个人解读    
  判断： f(root) = f(root.right) + 叶子 或者 f(root) = f(root.right) + f(root.left)   
    
tags:    
  -  树  
  -  递归  
  -  分条件递归  
