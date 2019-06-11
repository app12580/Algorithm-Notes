### description    
  Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.  
    
  Example 1:  
  Input:   
      1  
     / \  
    0   2  
    
    L = 1  
    R = 2  
    
  Output:   
      1  
        \  
         2  
  Example 2:  
  Input:   
      3  
     / \  
    0   4  
     \  
      2  
     /  
    1  
    
    L = 1  
    R = 3  
    
  Output:   
        3  
       /   
     2     
    /  
   1  
     
   裁剪一个BST，返回只属于[L,R]范围内的节点。  
### solution    
```    
  class Solution {  
      public TreeNode trimBST(TreeNode root, int L, int R) {  
              if(root == null) {  
              return null;  
          }  
          int val = root.val;  
          if(val < L) {  
              return trimBST(root.right, L, R);  
          } else if(val > R) {  
              return trimBST(root.left, L, R);  
          } else {  
              root.left = trimBST(root.left, L, R);  
              root.right = trimBST(root.right, L, R);  
              return root;  
          }  
      }  
  }  
```    
    
### 个人解读    
  判断：不需要中间函数，函数定义为修剪并返回根节点。  
  需要分情况判断：null,(-,L),[L,R],(R,+)  
  分析清晰后，竟然一次成功了，开森~  
    
tags:    
  -  树  
  -  递归  
