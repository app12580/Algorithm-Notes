### description    
  Given a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only 1 right child.  
    
  Example 1:  
  Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]  
    
         5  
        / \  
      3    6  
     / \    \  
    2   4    8  
   /        / \   
  1        7   9  
    
  Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]  
    
   1  
    \  
     2  
      \  
       3  
        \  
         4  
          \  
           5  
            \  
             6  
              \  
               7  
                \  
                 8  
                  \  
                   9    
  Note:  
    
  The number of nodes in the given tree will be between 1 and 100.  
  Each node will have a unique integer value from 0 to 1000.  
    
  重构BST  
### solution    
```    
  class Solution {  
      private TreeNode dual = new TreeNode(-1);  
      private TreeNode cur = dual;  
        
      public TreeNode increasingBST(TreeNode root) {  
          helper(root);  
          return dual.right;  
      }  
    
      private void helper(TreeNode root) {  
          if(root == null) {  
              return;  
          }  
          helper(root.left);  
          TreeNode next = new TreeNode(root.val);  
          cur.right = next;  
          cur = next;  
          helper(root.right);  
      }  
  }  
```    
    
### 个人解读    
  使用两个变量作为全局变量。  
    
tags:    
  -  树  
  -  中序遍历  
