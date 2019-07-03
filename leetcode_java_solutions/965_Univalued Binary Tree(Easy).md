### description    
  A binary tree is univalued if every node in the tree has the same value.  
    
  Return true if and only if the given tree is univalued.  
    
     
    
  Example 1:  
    
    
  Input: [1,1,1,1,1,null,1]  
  Output: true  
  Example 2:  
    
    
  Input: [2,2,2,5,2]  
  Output: false  
     
    
  Note:  
    
  The number of nodes in the given tree will be in the range [1, 100].  
  Each node's value will be an integer in the range [0, 99].  
    
  返回一个树是否全部val相等。    
### solution    
```    
  class Solution {  
      public boolean isUnivalTree(TreeNode root) {  
          if(root == null) {  
              return false;  
          }  
          int val = root.val;  
          Queue<TreeNode> queue = new LinkedList<>();  
          queue.offer(root);  
          while(!queue.isEmpty()) {  
              TreeNode poll = queue.poll();  
              if(poll.val != val) {  
                  return false;  
              }  
              if(poll.left != null) {  
                  queue.offer(poll.left);  
              }  
              if(poll.right != null) {  
                  queue.offer(poll.right);  
              }  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  通过Queue是非递归中最方便的一种方法了。  
    
tags:    
  -  树  
  -  非递归  
