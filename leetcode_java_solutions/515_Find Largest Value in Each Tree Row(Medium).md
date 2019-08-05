### description    
  You need to find the largest value in each row of a binary tree.  
    
  Example:  
  Input:   
    
            1  
           / \  
          3   2  
         / \   \    
        5   3   9   
    
  Output: [1, 3, 9]  
### solution    
```    
  class Solution {  
      public List<Integer> largestValues(TreeNode root) {  
          List<Integer> res =  new ArrayList<>();  
          if(root == null) return res;  
          Queue<TreeNode> queue = new LinkedList<>();  
          queue.offer(root);  
          while(!queue.isEmpty()) {  
              int cur = Integer.MIN_VALUE;  
              int size = queue.size();  
              for(int i = 0; i < size; i++) {  
                  TreeNode poll = queue.poll();  
                  if(poll.left != null) {  
                      queue.offer(poll.left);  
                  }  
                  if(poll.right != null) {  
                      queue.offer(poll.right);  
                  }  
                  cur = Math.max(cur, poll.val);  
              }  
              res.add(cur);  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  标准的层次遍历。这也配Medium？  
    
tags:    
  -  树  
  -  层次遍历  
