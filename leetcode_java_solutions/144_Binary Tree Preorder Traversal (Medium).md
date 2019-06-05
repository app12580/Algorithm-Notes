### description    
  Given a binary tree, return the preorder traversal of its nodes' values.  
    
  Example:  
    
  Input: [1,null,2,3]  
     1  
      \  
       2  
      /  
     3  
    
  Output: [1,2,3]  
  Follow up: Recursive solution is trivial, could you do it iteratively?  
  非递归实现二叉树的前序遍历  
### solution    
```    
  class Solution {  
      public List<Integer> preorderTraversal(TreeNode root) {  
           List<Integer> res = new ArrayList<>();  
          if(root == null) {  
              return res;  
          }  
          Stack<TreeNode> stack = new Stack<>();  
          stack.push(root);  
          // 前序： 中 左 右  
          while(!stack.isEmpty()) {  
              TreeNode pop = stack.pop();  
              res.add(pop.val);  
              if(pop.right != null) {  
                  stack.push(pop.right);  
              }  
              if(pop.left != null) {  
                  stack.push(pop.left);  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  使用栈作为数据结构。  
  前序遍历：中左右。  
  visit(root)  
  dfs(left)  
  dfs(right)  
  换成栈的话就是  
  visit(root)  
  push(right)  
  push(left)  
    
  在栈里面，当stack.pop()的时候，就是执行visit的时候。  
    
tags:    
  -  树  
  -  非递归  
  -  DFS  
