### description    
  Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.  
    
  An example is the root-to-leaf path 1->2->3 which represents the number 123.  
    
  Find the total sum of all root-to-leaf numbers.  
    
  Note: A leaf is a node with no children.  
    
  Example:  
    
  Input: [1,2,3]  
      1  
     / \  
    2   3  
  Output: 25  
  Explanation:  
  The root-to-leaf path 1->2 represents the number 12.  
  The root-to-leaf path 1->3 represents the number 13.  
  Therefore, sum = 12 + 13 = 25.  
  Example 2:  
    
  Input: [4,9,0,5,1]  
      4  
     / \  
    9   0  
   / \  
  5   1  
  Output: 1026  
  Explanation:  
  The root-to-leaf path 4->9->5 represents the number 495.  
  The root-to-leaf path 4->9->1 represents the number 491.  
  The root-to-leaf path 4->0 represents the number 40.  
  Therefore, sum = 495 + 491 + 40 = 1026.  
### solution    
```    
  class Solution {  
       
      int res = 0;  
      public int sumNumbers(TreeNode root) {  
          if(root == null) {  
              return 0;  
          }  
          dfs(root, 0);  
          return res;  
      }  
    
      private void dfs(TreeNode root, int pre) {  
          if(root.left == null && root.right == null) {  
              res += pre * 10 + root.val;  
              return;  
          }  
          if(root.left != null) {  
              dfs(root.left, pre * 10  + root.val);  
          }  
          if(root.right != null) {  
              dfs(root.right, pre * 10  + root.val);  
          }  
      }  
  }  
```    
    
### 个人解读    
  树，dfs，结果集，需要注意pre的处理，注意不要left修改一次pre，然后right再修改一次pre。  
    
tags:    
  -  树  
  -  DFS  
