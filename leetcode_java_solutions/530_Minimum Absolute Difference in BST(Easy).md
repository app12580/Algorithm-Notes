### description    
  Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.  
    
  Example:  
    
  Input:  
    
     1  
      \  
       3  
      /  
     2  
    
  Output:  
  1  
    
  Explanation:  
  The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).  
     
    
  Note: There are at least two nodes in this BST.  
### solution    
```    
  class Solution {  
      private int minDiff = Integer.MAX_VALUE;  
      private TreeNode preNode = null;  
    
      public int getMinimumDifference(TreeNode root) {  
          inOrder(root);  
          return minDiff;  
      }  
    
      private void inOrder(TreeNode node) {  
          if (node == null) return;  
          inOrder(node.left);  
          if (preNode != null) minDiff = Math.min(minDiff, node.val - preNode.val);  
          preNode = node;  
          inOrder(node.right);  
      }  
  }  
```    
    
### 个人解读    
  联想到中序遍历的结果。  
  差的绝对值最小，一定出现在中序遍历的顺序上。  
    
tags:    
  -  树  
  -  中序遍历特性  
