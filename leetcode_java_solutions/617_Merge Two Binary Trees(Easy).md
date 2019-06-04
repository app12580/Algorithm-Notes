### description    
  Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.  
    
  You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.  
    
  Example 1:  
    
  Input:   
  	Tree 1                     Tree 2                    
            1                         2                               
           / \                       / \                              
          3   2                     1   3                          
         /                           \   \                        
        5                             4   7                    
  Output:   
  Merged tree:  
  	     3  
  	    / \  
  	   4   5  
  	  / \   \   
  	 5   4   7  
     
    
  Note: The merging process must start from the root nodes of both trees.  
    
  归并两棵树，如果重复，则归并取和  
### solution    
```    
  class Solution {  
      public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {  
          if(t1 == null && t2 == null) {  
              return null;  
          }  
          if(t1 == null) {  
              return t2;  
          }  
          if(t2 == null) {  
              return t1;  
          }  
          t1.left = mergeTrees(t1.left, t2.left);  
          t1.right = mergeTrees(t1.right, t2.right);  
          t1.val = t1.val + t2.val;  
          return t1;  
      }  
  }  
```    
    
### 个人解读    
  首先需要判断是否需要中间函数：因为归并两个root，可以左子树归并，右子树归并，然后返回根节点。所以不需要中间函数。  
  主要问题在于null的情况如何优雅的处理。最差的处理就是ifelse区分四种情况，然后分别处理。  
  写的时候遇到一个疑问，要不要修改原有的对象还是新建一个节点，想了想，感觉直接修改了也无所谓的。  
    
tags:    
  -  树  
  -  递归  
    
