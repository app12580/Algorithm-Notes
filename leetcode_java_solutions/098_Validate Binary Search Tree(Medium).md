### description    
  Given a binary tree, determine if it is a valid binary search tree (BST).  
    
  Assume a BST is defined as follows:  
    
  The left subtree of a node contains only nodes with keys less than the node's key.  
  The right subtree of a node contains only nodes with keys greater than the node's key.  
  Both the left and right subtrees must also be binary search trees.  
     
    
  Example 1:  
    
      2  
     / \  
    1   3  
    
  Input: [2,1,3]  
  Output: true  
  Example 2:  
    
      5  
     / \  
    1   4  
       / \  
      3   6  
    
  Input: [5,1,4,null,null,3,6]  
  Output: false  
  Explanation: The root node's value is 5 but its right child's value is 4.  
### solution    
```    
  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Validate Binary Search Tree.  
Memory Usage: 39.5 MB, less than 73.14% of Java online submissions for Validate Binary Search Tree.  
  
  
  class Solution {  
     private Integer pre = null;  
      public boolean isValidBST(TreeNode root) {  
          if(root == null) return true;  
          if(!helper(root)) {  
              return false;  
          }  
          return true;  
      }  
    
      private boolean helper(TreeNode root) {  
          if(root.left != null) {  
              if(!helper(root.left)) {  
                  return false;  
              }  
          }  
          int val = root.val;  
          if(pre == null) {  
              pre = val;  
          } else {  
              if(pre >= val) {  
                  return false;  
              }  
              pre = val;  
          }  
          if(root.right != null) {  
              if(!helper(root.right)) {  
                  return false;  
              }  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  BST，中序遍历即可。  
    
tags:    
  -  BST  
  -  中序遍历  
