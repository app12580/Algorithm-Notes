### description    
  Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.  
    
  Example :  
    
  Input: root = [4,2,6,1,3,null,null]  
  Output: 1  
  Explanation:  
  Note that root is a TreeNode object, not an array.  
    
  The given tree [4,2,6,1,3,null,null] is represented by the following diagram:  
    
            4  
          /   \  
        2      6  
       / \      
      1   3    
    
  while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.  
  Note:  
    
  The size of the BST will be between 2 and 100.  
  The BST is always valid, each node's value is an integer, and each node's value is different.  
### solution    
```    
  class Solution {  
      Integer res = Integer.MAX_VALUE, pre = null;  
      public int minDiffInBST(TreeNode root) {  
          if (root.left != null) minDiffInBST(root.left);  
          if (pre != null) res = Math.min(res, root.val - pre);  
          pre = root.val;  
          if (root.right != null) minDiffInBST(root.right);  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  第一反应，不太好弄，因为需要跨度很大的去比较，稍微不小心，就会发生指数扩展现象。  
  全局变量是肯定要用的。  
    
  然后意识到，这个是BST，遇到BST就要想到中序遍历。  
    
tags:    
  -  BST  
  -  中序遍历  
