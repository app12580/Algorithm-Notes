### description    
  Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.  
    
  For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.  
    
  Return the sum of these numbers.  
    
     
    
  Example 1:  
    
    
    
  Input: [1,0,1,0,1,0,1]  
  Output: 22  
  Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22  
     
    
  Note:  
    
  The number of nodes in the tree is between 1 and 1000.  
  node.val is 0 or 1.  
  The answer will not exceed 2^31 - 1.  
    
### solution    
```    
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
    private int res = 0;  
    
      public int sumRootToLeaf(TreeNode root) {  
          if(root == null) {  
              return 0;  
          }  
          dfs(root, 0);  
          return res;  
      }  
    
      private void dfs(TreeNode root, int pre) {  
          int val = pre * 2 + root.val;  
          if(root.left == null && root.right == null) {  
              res += val;  
              return;  
          }  
          if(root.left != null) {  
              dfs(root.left, val);  
          }  
          if(root.right != null) {  
              dfs(root.right, val);  
          }  
    
      }  
  }  
```    
    
### 个人解读    
  第一思路(非常迅速的pass掉)：获取所有二进制数，然后再转化成int，很麻烦  
  第二思路：类似于 res = res * 2 + cur;  
    
  注意：每次循环需要一个变量表示有几个支路。支路这个太难弄了，导致BFS破产，还是要用DFS。  
  问题在于子树与父节点的联系，既需要返回子节点的层级，也需要返回子节点的统计。  
    
  然而整理了下思路，发现每条支路好像只能一条一条的判断了。  
    
  最终决定，使用DFS的统计应用(DFS递归获取所有可求解  )。在中间结果中存储当前value。  
  需要控制dfs参数，不能让null进去。  
    
tags:    
  -  树  
  -  DFS  
