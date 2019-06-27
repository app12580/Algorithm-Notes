### description    
  Given a n-ary tree, find its maximum depth.  
    
  The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.  
    
  For example, given a 3-ary tree:  
    
     
    
  We should return its max depth, which is 3.  
    
     
    
  Note:  
    
  The depth of the tree is at most 1000.  
  The total number of nodes is at most 5000.  
    
    
### solution    
```    
  class Solution {  
      public int maxDepth(Node root) {  
          if(root == null) {  
              return 0;  
          }  
          if(root.children == null) {  
              return 1;  
          }  
          int max = 0;  
          for(Node node: root.children) {  
              max = Math.max(max, maxDepth(node));  
          }  
          return max + 1;  
      }  
  }  
```    
    
### 个人解读    
  与二叉树获取高度长度类似，标准的递归。  
    
tags:    
  -  树  
  -  递归  
    
