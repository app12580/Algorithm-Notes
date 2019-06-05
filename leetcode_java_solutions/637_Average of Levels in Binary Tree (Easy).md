### description    
  Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.  
  Example 1:  
  Input:  
      3  
     / \  
    9  20  
      /  \  
     15   7  
  Output: [3, 14.5, 11]  
  Explanation:  
  The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].  
  Note:  
  The range of node's value is in the range of 32-bit signed integer.  
    
  获取二叉树每一层级的平均数  
### solution    
```    
  class Solution {  
      public List<Double> averageOfLevels(TreeNode root) {  
          List<Double> res = new ArrayList<>();  
          if(root == null) {  
              return res;  
          }  
          Queue<TreeNode> queue = new LinkedList<>();  
          queue.offer(root);  
          while(!queue.isEmpty()) {  
              int cnt = queue.size();  
              double sum = 0;  
              for(int i = 0;  i< cnt; i++) {  
                  TreeNode poll = queue.poll();  
                  if(poll.left != null) {  
                      queue.offer(poll.left);  
                  }  
                  if(poll.right != null) {  
                      queue.offer(poll.right);  
                  }  
                  sum += poll.val;  
              }  
              res.add(sum / cnt);  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  使用 BFS 进行层次遍历。不需要使用两个队列来分别存储当前层的节点和下一层的节点，因为在开始遍历一层的节点时，当前队列中的节点数就是当前层的节点数，只要控制遍历这么多节点数，就能保证这次遍历的都是当前层的节点。  
    
tags:    
  -  树  
  -  层次遍历  
