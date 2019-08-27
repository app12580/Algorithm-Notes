### description    
  Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the given depth d. The root node is at depth 1.  
    
  The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two tree nodes with value v as N's left subtree root and right subtree root. And N's original left subtree should be the left subtree of the new left subtree root, its original right subtree should be the right subtree of the new right subtree root. If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v as the new root of the whole original tree, and the original tree is the new root's left subtree.  
    
  Example 1:  
  Input:   
  A binary tree as following:  
         4  
       /   \  
      2     6  
     / \   /   
    3   1 5     
    
  v = 1  
    
  d = 2  
    
  Output:   
         4  
        / \  
       1   1  
      /     \  
     2       6  
    / \     /   
   3   1   5     
    
  Example 2:  
  Input:   
  A binary tree as following:  
        4  
       /     
      2      
     / \     
    3   1      
    
  v = 1  
    
  d = 3  
    
  Output:   
        4  
       /     
      2  
     / \      
    1   1  
   /     \    
  3       1  
  Note:  
  The given d is in range [1, maximum depth of the given tree + 1].  
  The given binary tree has at least one tree node.  
### solution    
```    
  
Runtime: 1 ms, faster than 52.81% of Java online submissions for Add One Row to Tree.  
Memory Usage: 36.9 MB, less than 100.00% of Java online submissions for Add One Row to Tree.  
  
  class Solution {  
      public TreeNode addOneRow(TreeNode root, int v, int d) {  
          if(root == null) {  
              return null;  
          }  
          if(d == 1) {  
              TreeNode n = new TreeNode(v);  
              n.left = root;  
              return n;  
          }  
          Queue<TreeNode> queue = new LinkedList<>();  
          queue.offer(root);  
          for(int i = 1; i <= d - 2; i++) {     //注意这一行的"d-2"  
              int size = queue.size();  
              for(int j = 0; j <size; j++) {  
                  TreeNode poll = queue.poll();  
                  if(poll.left != null) {  
                      queue.offer(poll.left);  
                  }  
                  if(poll.right != null) {  
                      queue.offer(poll.right);  
                  }  
              }  
          }  
          int size = queue.size();  
          for(int i = 0; i < size; i++) {  
              TreeNode poll = queue.poll();  
              TreeNode left = poll.left;  
              poll.left = new TreeNode(v);  
              poll.left.left = left;  
              TreeNode right = poll.right;  
              poll.right = new TreeNode(v);  
              poll.right.right = right;  
          }  
          return root;  
      }  
  }  
```    
    
### 个人解读    
  先层次遍历，找到添加层次的父一层，然后跳出循环，对该层进行比那里处理。  
    
tags:    
  -  树  
  -  层次遍历  
