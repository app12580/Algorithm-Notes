### description    
  Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.  
    
  Example:  
    
  Input: [1,2,3,null,5,null,4]  
  Output: [1, 3, 4]  
  Explanation:  
    
     1            <---  
   /   \  
  2     3         <---  
   \     \  
    5     4       <---  
### solution    
```    
   
 Runtime: 1 ms, faster than 97.74% of Java online submissions for Binary Tree Right Side View.  
 Memory Usage: 36.3 MB, less than 99.96% of Java online submissions for Binary Tree Right Side View.  
   
  class Solution {  
      public List<Integer> rightSideView(TreeNode root) {  
          List<Integer> res = new ArrayList<>();  
          if(root == null) {  
              return res;  
          }  
          Queue<TreeNode> queue = new LinkedList<>();  
          queue.offer(root);  
          while(!queue.isEmpty()) {  
              int cur = 0;  
              int size = queue.size();  
              for(int i = 0; i < size; i++) {  
                  TreeNode poll = queue.poll();  
                  if(poll.left != null) {  
                      queue.offer(poll.left);  
                  }  
                  if(poll.right != null) {  
                      queue.offer(poll.right);  
                  }  
                  cur = poll.val;  
              }  
              res.add(cur);  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  只要右边的内容，因为不需要回溯，是单行的，所以没必要使用递归。  
  理解错题目了，以为只是要最右子树。后来才意识到是每一层的最后一个。  
    
  这种每次遍历只要最后一个的做法，有没有什么统一的名称来命名。  
  顺其自然，每次都做，最后面留下来的就是结果。  
    
tags:    
  -  树  
  -  层次遍历  
