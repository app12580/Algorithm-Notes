### description    
  In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.  
    
  Two nodes of a binary tree are cousins if they have the same depth, but have different parents.  
    
  We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.  
    
  Return true if and only if the nodes corresponding to the values x and y are cousins.  
    
     
    
  Example 1:  
    
    
  Input: root = [1,2,3,4], x = 4, y = 3  
  Output: false  
  Example 2:  
    
    
  Input: root = [1,2,3,null,4,null,5], x = 5, y = 4  
  Output: true  
  Example 3:  
    
    
    
  Input: root = [1,2,3,null,4], x = 2, y = 3  
  Output: false  
     
    
  Note:  
    
  The number of nodes in the tree will be between 2 and 100.  
  Each node has a unique integer value from 1 to 100.  
     
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
   public boolean isCousins(TreeNode root, int x, int y) {  
          if(root == null) {  
              return false;  
          }  
          boolean findOne = false;  
          int parent = -1;  
    
          Queue<TreeNode> queue = new LinkedList<>();  
          queue.offer(root);  
          while(!queue.isEmpty()) {  
              int size = queue.size();  
              for(int i = 0; i < size; i++) {  
                  TreeNode poll = queue.poll();  
    
                  // 利用源数据数字没有重复的  
                  if(poll.left != null && (poll.left.val == x || poll.left.val == y)) {  
                      if(findOne) {  
                          return poll.val != parent;  
                      } else {  
                          findOne = true;  
                          parent = poll.val;  
                      }  
                  }  
    
                  if(poll.right != null && (poll.right.val == x || poll.right.val == y)) {  
                      if(findOne) {  
                          return poll.val != parent;  
                      } else {  
                          findOne = true;  
                          parent = poll.val;  
                      }  
                  }  
                  if(poll.left != null) {  
                      queue.offer(poll.left);  
                  }  
                  if(poll.right != null) {  
                      queue.offer(poll.right);  
                  }  
              }  
              if(findOne) {  
                  return false;  
              }  
          }  
    
          return false;  
      }  
    
  }  
```    
    
### 个人解读    
  层次遍历的变种，需要在遍历过程中，设计好各种标记。  
    
  两个分别的发现标记，一个在poll中的标记。  
    
  写的时候遇到了问题，层次遍历时，当把节点扔进queue里面时候，丢失了父节点信息，所以需要在上一层次时候去判断。  
    
  优化后，只使用了一个boolean标记(当前层次是否发现)和一个int标记，上一个发现时候的父节点。  
    
tags:    
  -  树  
  -  层次遍历  
  -  优化  
