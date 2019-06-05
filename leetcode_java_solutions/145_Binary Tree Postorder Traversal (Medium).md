### description  
  Given a binary tree, return the postorder traversal of its nodes' values.
  
  Example:
  
  Input: [1,null,2,3]
     1
      \
       2
      /
     3
  
  Output: [3,2,1]
  Follow up: Recursive solution is trivial, could you do it iteratively?
  
   非递归实现二叉树的后序遍历
### solution  
```  
  // 反转法
  class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
          // 后序：  左 右 中
          List<Integer> res = new ArrayList<>();
          if(root == null) {
              return res;
          }
          Stack<TreeNode> stack = new Stack<>();
          stack.push(root);
          while(!stack.isEmpty()) {
              TreeNode pop = stack.pop();
              res.add(pop.val);
              if(pop.left != null) {
                  stack.push(pop.left);
              }
              if(pop.right != null) {
                  stack.push(pop.right);
              }
          }
          Collections.reverse(res);
          return res;
  
      }
  }
```  
  
### 个人解读  
  后序：  左 右 中，stack的主要问题在于，拿到pop时候就算"中"了，所以反转后变成中右左，这样就方便不少。

  
tags:  
   -  树  
   -  非递归  
   -  DFS  

