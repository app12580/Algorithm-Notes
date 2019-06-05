### description    
  Given a binary tree, find the leftmost value in the last row of the tree.  
    
  Example 1:  
  Input:  
    
      2  
     / \  
    1   3  
    
  Output:  
  1  
  Example 2:   
  Input:  
    
          1  
         / \  
        2   3  
       /   / \  
      4   5   6  
         /  
        7  
    
  Output:  
  7  
  Note: You may assume the tree (i.e., the given root node) is not NULL.  
    
  获取树最下面一层的最左节点  
### solution    
```    
//方法一： 通过cnt控制层级
  class Solution {  
      public int findBottomLeftValue(TreeNode root) {  
           int res = 0;  
          if(root == null) {  
              return res;  
          }  
          Queue<TreeNode> queue = new LinkedList<>();  
          queue.offer(root);  
          while(!queue.isEmpty()) {  
              int cnt = queue.size();  
              for(int i = 0;  i< cnt; i++) {  
                  TreeNode poll = queue.poll();  
                  if(i == 0) {  
                      res = poll.val;  
                  }  
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
  
  //方法二： 
  // 每次先加right，然后再left，这样无需控制，最后一次的就是最后一层的left
  public int findBottomLeftValue(TreeNode root) {
      Queue<TreeNode> queue = new LinkedList<>();
      queue.add(root);
      while (!queue.isEmpty()) {
          root = queue.poll();
          if (root.right != null) queue.add(root.right);
          if (root.left != null) queue.add(root.left);
      }
      return root.val;
  }
```    
    
### 个人解读    
  树的层次遍历，与其判断什么时候才是最后一层，不如无脑刷新，最后留下来的肯定就是想要的结果了。  
    
tags:    
  -  树  
  -  层次遍历  
