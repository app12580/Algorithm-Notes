### description    
  Given a binary tree, find its maximum depth.  
    
  The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.  
    
  Note: A leaf is a node with no children.  
    
  Example:  
    
  Given binary tree [3,9,20,null,null,15,7],  
    
      3  
     / \  
    9  20  
      /  \  
     15   7  
  return its depth = 3.  
### solution    
```    
  class Solution {  
      public int maxDepth(TreeNode root) {  
           if(root == null) return 0;  
          return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;  
      }  
  }  
```    
    
### 个人解读    
  树的基本应用，主要使用递归思想。  
    
tags:    
  -  树  
  -  递归  
  -  中间函数  
