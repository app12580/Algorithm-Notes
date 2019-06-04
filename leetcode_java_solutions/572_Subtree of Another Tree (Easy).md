### description    
  Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.  
    
  Example 1:  
  Given tree s:  
    
       3  
      / \  
     4   5  
    / \  
   1   2  
  Given tree t:  
     4   
    / \  
   1   2  
  Return true, because t has the same structure and node values with a subtree of s.  
  Example 2:  
  Given tree s:  
    
       3  
      / \  
     4   5  
    / \  
   1   2  
      /  
     0  
  Given tree t:  
     4  
    / \  
   1   2  
  Return false.  
    
  判断一个树是否是另一个树的子树  
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
        public boolean isSubtree(TreeNode s, TreeNode t) {  
          // t 是 s的子树  
            if(s == null) return false;  
          return isSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);  
      }  
    
      public boolean isSame(TreeNode t1, TreeNode t2) {  
          if(t1 == null && t2 == null) {  
              return true;  
          }  
          if(t1 == null && t2 != null) {  
              return false;  
          }  
          if(t1 != null && t2 == null) {  
              return false;  
          }  
          return t1.val == t2.val && isSame(t1.left, t2.left) && isSame(t1.right, t2.right);  
      }  
  }  
```    
    
### 个人解读    
  判断：需要中间函数。  
  中间函数定义： 两树是否完全一致。  
    
tags:    
  -  树  
  -  递归  
  -  中间函数  
