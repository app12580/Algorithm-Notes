### description    
  Given a binary tree, determine if it is a complete binary tree.  
    
  Definition of a complete binary tree from Wikipedia:  
  In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.  
    
     
    
  Example 1:  
    
    
    
  Input: [1,2,3,4,5,6]  
  Output: true  
  Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.  
  Example 2:  
    
    
    
  Input: [1,2,3,4,5,null,7]  
  Output: false  
  Explanation: The node with value 7 isn't as far left as possible.  
     
  Note:  
    
  The tree will have between 1 and 100 nodes.  
### solution    
```    
Runtime: 1 ms, faster than 91.90% of Java online submissions for Check Completeness of a Binary Tree.  
Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Check Completeness of a Binary Tree.  
  
  class Solution {  
     public boolean isCompleteTree(TreeNode root) {  
          if(root == null) return true;  
          Queue<TreeNode> queue = new LinkedList<>();  
          queue.offer(root);  
          boolean stop = false;  
          while(!queue.isEmpty()) {  
              int size = queue.size();  
              for(int i = 0; i <size; i++) {  
                  TreeNode poll = queue.poll();  
                  if(poll.left == null) {  
                      stop = true;  
                  } else {  
                      if(stop) {  
                          return false;  
                      }  
                      queue.offer(poll.left);  
                  }  
                  if(poll.right == null) {  
                      stop = true;  
                  } else {  
                      if(stop) {  
                          return false;  
                      }  
                      queue.offer(poll.right);  
                  }  
              }  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  看到完全二叉树可以考虑坐标法，但是这样子会破坏原有结构。  
  所以还是老实用BFS把。  
  PS.好像用坐标法也没有简单到哪里去。。。。  
    
  思路： BFS，遍历时候使用标记  
    
    
tags:    
  -  树  
  -  完全二叉树  
  -  BFS  
