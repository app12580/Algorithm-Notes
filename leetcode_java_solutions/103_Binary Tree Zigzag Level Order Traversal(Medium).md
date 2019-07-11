### description    
  Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).  
    
  For example:  
  Given binary tree [3,9,20,null,null,15,7],  
      3  
     / \  
    9  20  
      /  \  
     15   7  
  return its zigzag level order traversal as:  
  [  
    [3],  
    [20,9],  
    [15,7]  
  ]  
### solution    
```    
  class Solution {  
      public List<List<Integer>> zigzagLevelOrder(TreeNode root) {  
          List<List<Integer>> res = new ArrayList<>();  
          if(root == null) return res;  
          Queue<TreeNode> queue = new LinkedList<>();  
          queue.offer(root);  
          boolean flag = false;  
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
              if(flag) {  
                  Collections.reverse(list);  
              }  
              flag = !flag;  
              res.add(list);  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  无聊的题目，在层次遍历上增加一个标记即可。  
    
    
tags:    
  -  层次遍历  
  -  树  
