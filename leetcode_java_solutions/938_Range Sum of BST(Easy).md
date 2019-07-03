### description    
  Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).  
    
  The binary search tree is guaranteed to have unique values.  
    
     
    
  Example 1:  
    
  Input: root = [10,5,15,3,7,null,18], L = 7, R = 15  
  Output: 32  
  Example 2:  
    
  Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10  
  Output: 23  
     
    
  Note:  
    
  The number of nodes in the tree is at most 10000.  
  The final answer is guaranteed to be less than 2^31.  
    
    
### solution    
```    
  /**  
   * Definition for a binary tree node.  
   * public class TreeNode {  
   *     int val;  
   *     TreeNode left;  
   *     TreeNode right;  
   *     TreeNode(int x) { val = x; }  
   * }  
   */  
  class Solution {  
      private int sum = 0;  
        
        
      public int rangeSumBST(TreeNode root, int L, int R) {  
          dfs(root, L, R);      
          return sum;  
      }  
    
      private void dfs(TreeNode root, int L, int R) {  
          if (root == null) {  
              return;  
          }  
          int val = root.val;  
          if(val < L) {  
              dfs(root.right, L, R);  
          } else if(val == L) {  
              sum += val;  
              dfs(root.right, L, R);  
          } else if(val < R) {  
              dfs(root.left, L, R);  
              sum += val;  
              dfs(root.right, L, R);  
          } else if(val == R) {  
              dfs(root.left, L, R);  
              sum += val;  
          } else {  
              dfs(root.left, L, R);  
          }  
            
      }  
  }  
```    
    
### 个人解读    
  BST，需要涉及到中序遍历。  
  为了效率，需要适时停止，本来想着通过两个flag标记，后来选择通过进入的时机控制哪些可以直接跳过。  
    
  因为只是返回sum，与顺序无关，其实无所谓中序了  
    
tags:    
  -  BST  
  -  树  
  -  中序遍历  
