### description    
  Given a binary tree, return all root-to-leaf paths.  
    
  Note: A leaf is a node with no children.  
    
  Example:  
    
  Input:  
    
     1  
   /   \  
  2     3  
   \  
    5  
    
  Output: ["1->2->5", "1->3"]  
    
  Explanation: All root-to-leaf paths are: 1->2->5, 1->3  
    
   输出二叉树中所有从根到叶子的路径  
### solution    
```    
  
Runtime: 2 ms, faster than 32.28% of Java online submissions for Binary Tree Paths.  
Memory Usage: 36.6 MB, less than 100.00% of Java online submissions for Binary Tree Paths.  
  
  
  /**  
   * Definition for a binary tree node.  
   * public class TreeNode {  
   *     int val;  
   *     TreeNode left;  
   *     TreeNode right;  
   *     TreeNode(int x) { val = x; }  
   * }  
   */  
  class Solution {  
   public List<String> binaryTreePaths(TreeNode root) {  
          List<String> res = new ArrayList<>();  
          if(root == null) {  
              return res;  
          }  
          dfs(new StringBuilder(), res, root);  
          return res;  
      }  
    
      private void dfs(StringBuilder sb, List<String> res, TreeNode node) {  
          if(node == null) {  
              return;  
          }  
          String part = "";  
          if(sb.length() != 0) {  
              part = "->";  
          }  
          part += node.val;  
          sb.append(part);  
          if(node.left == null && node.right == null) {  
              res.add(sb.toString());  
          } else {  
              dfs(sb, res, node.left);  
              dfs(sb, res, node.right);  
          }  
          sb.delete(sb.length() - part.length(), sb.length());    //start:第一个删除的位置;end:第一个保留的位置  
      }  
  }  
```    
    
### 个人解读    
  dfs，遍历全部，回溯。  
  主要疑虑可能在于每次dfs是两次：left和right各一次，回溯操作需要什么处理。  
    
  提交出错！问题在于不知道为什么，默认数字只有个位数了。。。  
    
tags:    
  -  树  
  -  DFS  
  -  遍历结果集  
  -  待优化  
