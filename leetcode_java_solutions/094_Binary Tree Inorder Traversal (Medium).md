### description  
  Given a binary tree, return the inorder traversal of its nodes' values.
  
  Example:
  
  Input: [1,null,2,3]
     1
      \
       2
      /
     3
  
  Output: [1,3,2]
  Follow up: Recursive solution is trivial, could you do it iteratively?
    非递归实现二叉树的中序遍历

### solution  
```  
  public List<Integer> inorderTraversal(TreeNode root) {
          List<Integer> ret = new ArrayList<>();
          if (root == null) return ret;
          Stack<TreeNode> stack = new Stack<>();
          TreeNode cur = root;
          while (cur != null || !stack.isEmpty()) {
              while (cur != null) {
                  stack.push(cur);
                  cur = cur.left;
              }
              TreeNode node = stack.pop();
              ret.add(node.val);
              cur = node.right;
          }
          return ret;
      }
```  
  
### 个人解读  
  重点记忆吧。
  在真正处理前，连同根节点，把所有的左节点全扔进stack里面。
  然后执行pop操作(中)，然后另cur=cur.right，进行"右"操作
  
tags:  
   -  树  
   -  非递归  
   -  DFS  
