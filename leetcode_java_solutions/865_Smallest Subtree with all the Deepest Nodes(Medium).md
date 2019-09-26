### description    
  Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.  
    
  A node is deepest if it has the largest depth possible among any node in the entire tree.  
    
  The subtree of a node is that node, plus the set of all descendants of that node.  
    
  Return the node with the largest depth such that it contains all the deepest nodes in its subtree.  
    
     
    
  Example 1:  
    
  Input: [3,5,1,6,2,0,8,null,null,7,4]  
  Output: [2,7,4]  
  Explanation:  
    
    
    
  We return the node with value 2, colored in yellow in the diagram.  
  The nodes colored in blue are the deepest nodes of the tree.  
  The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.  
  The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.  
  Both the input and output have TreeNode type.  
     
    
  Note:  
    
  The number of nodes in the tree will be between 1 and 500.  
  The values of each node are unique.  
### solution    
```    
  
//这个内存消耗不科学啊。。。  
 Runtime: 1 ms, faster than 66.60% of Java online submissions for Smallest Subtree with all the Deepest Nodes.  
 Memory Usage: 35.6 MB, less than 100.00% of Java online submissions for Smallest Subtree with all the Deepest Nodes.  
   
   
  class Solution {  
      private Map<TreeNode, Integer> map = new HashMap<>();  
      private int max;  
      public TreeNode subtreeWithAllDeepest(TreeNode root) {  
          if(root == null) return root;  
          maxDeep(root, 1);  
          return dfs(root);  
    
      }  
    
      private TreeNode dfs(TreeNode root) {  
          if(map.get(root) == max) return root;  
          boolean left = hasMax(root.left);  
          boolean right = hasMax(root.right);  
          if(left && right) {  
              return root;  
          } else if(left) {  
              return dfs(root.left);  
          } else {  
              return dfs(root.right);  
          }  
      }  
    
      private boolean hasMax(TreeNode node) {  
          if(node == null) {  
              return false;  
          }  
          if(map.get(node) == max) return true;  
          return hasMax(node.left) || hasMax(node.right);  
      }  
    
      private void maxDeep(TreeNode root, int val) {  
          if(root == null) return;  
          map.put(root, val);  
          if(root.left != null) {  
              maxDeep(root.left, val + 1);  
          }  
          if(root.right != null) {  
              maxDeep(root.right, val + 1);  
          }  
          max = Math.max(max, val);  
      }  
    
  }  
```    
    
### 个人解读    
  这个题目也好棘手啊  
  思路一： 两次遍历，第一次遍历获取最深节点，第二次遍历去找共同祖先  
  思路二： 通过递归，在顶部去判断时候，然后返回结果。  
    
  总结，使用一个map存储全部节点深度，然后计算。  
    
  令人惊讶的是速度60+也就算了，为啥空间是100%。。。。  
  而且运算过程中有迭代重复计算，提交前感觉是很影响效率的才对。  
    
tags:    
  -  树  
  -  全部树Map  
