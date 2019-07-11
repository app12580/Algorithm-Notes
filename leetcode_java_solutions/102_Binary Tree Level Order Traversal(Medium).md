### description    
  Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).  
    
  For example:  
  Given binary tree [3,9,20,null,null,15,7],  
      3  
     / \  
    9  20  
      /  \  
     15   7  
  return its level order traversal as:  
  [  
    [3],  
    [9,20],  
    [15,7]  
  ]  
### solution    
```    
  class Solution {  
      public List<List<Integer>> levelOrder(TreeNode root) {  
           List<List<Integer>> res = new ArrayList<>();  
          if(root == null) return res;  
          Queue<TreeNode> queue = new LinkedList<>();  
          queue.offer(root);  
          while(!queue.isEmpty()) {  
              int size = queue.size();  
              List<Integer> list = new ArrayList<>();  
              for(int i = 0; i < size; i++) {  
                  TreeNode poll = queue.poll();  
                  if(poll.left != null) {  
                      queue.offer(poll.left);  
                  }  
                  if(poll.right != null) {  
                      queue.offer(poll.right);  
                  }  
                  list.add(poll.val);  
              }  
              res.add(list);  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  层次遍历，为啥这题会是Medium啊？？  
    
tags:    
  -  层次遍历  
  -  树  
