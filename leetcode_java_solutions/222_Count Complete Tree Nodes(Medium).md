### description    
  Given a complete binary tree, count the number of nodes.  
    
  Note:  
    
  Definition of a complete binary tree from Wikipedia:  
  In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.  
    
  Example:  
    
  Input:   
      1  
     / \  
    2   3  
   / \  /  
  4  5 6  
    
  Output: 6  
### solution    
```    
  
// 方法一： 无脑的层次遍历  
  class Solution {  
      public int countNodes(TreeNode root) {  
           if(root == null) return 0;  
          Queue<TreeNode> queue = new LinkedList<>();  
          queue.offer(root);  
          int res = 0;  
          while(!queue.isEmpty()) {  
              int size = queue.size();   
              for(int i = 0; i < size; i++) {  
                  TreeNode poll = queue.poll();  
                  res++;  
                  if(poll.left != null) {  
                      queue.offer(poll.left);  
                  }  
                  if(poll.right != null) {  
                      queue.offer(poll.right);  
                  }  
                    
              }  
          }  
          return res;  
      }  
  }  
    
  //方法二： 递归  
  class Solution {  
      public int countNodes(TreeNode root) {  
            if(root== null)  
              return 0;  
          return 1 + countNodes(root.left)+countNodes(root.right);  
      }  
  }  
```    
    
### 个人解读    
  为了效率，必须先找到最后一层。  
    
  感觉也没什么好优化的，因为想知道最后一层有多少个，也需要知道上一层有多少个。  
    
  反思： DFS和BFS做多了，结果导致忘记树的递归解法了  
    
tags:    
  -  树  
  -  层次遍历  
  -  递归  
