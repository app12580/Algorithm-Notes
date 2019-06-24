### description    
  Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).  
    
  For example:  
  Given binary tree [3,9,20,null,null,15,7],  
      3  
     / \  
    9  20  
      /  \  
     15   7  
  return its bottom-up level order traversal as:  
  [  
    [15,7],  
    [9,20],  
    [3]  
  ]  
### solution    
```    
  class Solution {  
      public List<List<Integer>> levelOrderBottom(TreeNode root) {  
          List<List<Integer>> res = new ArrayList<>();  
          if(root == null) {  
              return res;  
          }  
          Queue<TreeNode> queue = new LinkedList<>();  
          queue.offer(root);  
          while(!queue.isEmpty()){  
              int size = queue.size();  
              List<Integer> list = new ArrayList<>();  
              for(int i = 0; i < size; i++) {  
                  TreeNode poll = queue.poll();  
                  list.add(poll.val);  
                  if(poll.left != null) {  
                      queue.offer(poll.left);  
                  }  
                  if(poll.right != null) {  
                      queue.offer(poll.right);  
                  }  
              }  
              res.add(list);  
          }  
          Collections.reverse(res);  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  标准的层次遍历，最后来个反转就完事了。  
    
tags:    
  -  BFS  
  -  树  
  -  层次遍历  
