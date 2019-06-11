### description    
  Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.  
    
  Example:  
    
  Input: The root of a Binary Search Tree like this:  
                5  
              /   \  
             2     13  
    
  Output: The root of a Greater Tree like this:  
               18  
              /   \  
            20     13  
              
  把二叉查找树每个节点的值都加上比它大的节点的值  
### solution    
```    
  class Solution {  
     int sum = 0;  
      public TreeNode convertBST(TreeNode root) {  
          if(root == null) {  
              return null;  
          }  
          convertBST(root.right);  
          int temp = sum;  
          sum += root.val;  
          root.val += temp;  
          convertBST(root.left);  
          return root;  
      }  
  }  
```    
    
### 个人解读    
  递归，采用右中左的顺序。  
  设置一个全局变量。  
    
tags:    
  -  树  
  -  递归  
