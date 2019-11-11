### description    
  Given a binary tree, we install cameras on the nodes of the tree.   
    
  Each camera at a node can monitor its parent, itself, and its immediate children.  
    
  Calculate the minimum number of cameras needed to monitor all nodes of the tree.  
    
     
    
  Example 1:  
    
    
  Input: [0,0,null,0,0]  
  Output: 1  
  Explanation: One camera is enough to monitor all nodes if placed as shown.  
  Example 2:  
    
    
  Input: [0,0,null,0,null,0,null,null,0]  
  Output: 2  
  Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.  
    
  Note:  
    
  The number of nodes in the given tree will be in the range [1, 1000].  
  Every node has value 0.  
### solution    
```    
  // 参考答案  
    
  Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Cameras.  
  Memory Usage: 36.5 MB, less than 100.00% of Java online submissions for Binary Tree Cameras.  
    
   // Return 0 if it's a leaf.  // 必须要等待父节点安装摄像头  
   // Return 1 if it's a parent of a leaf, with a camera on this node.  
   // Return 2 if it's coverd, without a camera on this node. 或者2是null的  
   class Solution {  
        int res = 0;  
      public int minCameraCover(TreeNode root) {  
          return (dfs(root) < 1 ? 1 : 0) + res;  
      }  
    
      public int dfs(TreeNode root) {  
          if (root == null) return 2;  
          int left = dfs(root.left), right = dfs(root.right);  
          if (left == 0 || right == 0) {  
              res++;  
              return 1;  
          }  
          return left == 1 || right == 1 ? 2 : 0;  
      }  
  }  
    
```    
    
### 个人解读    
  感觉这题目需要递归，很类似于rob house的。  
    
  思路一：  
  整体只有两种状态，奇数行装摄像头和偶数行装摄像头。   
  因此可以令辅助函数直接确定隔行为摄像头  
    
  然而画了一下图，发现并不是这么简单  
    
  思路二：  
  完善一下。两个方法，一个是原始函数，另外一个是让root安装，同时让剩下的也满足条件。  
  失败了，关键点： 某一个节点如果已经被监控了，并不代表这不用安装摄像头了。  
    
  Lee215 : 贪心算法，每次从底下向上，然后先给父亲节点安装  
  重点；1、贪婪算法的发现 2、通过DFS控制从下向上的遍历  
  
    
  参考答案一： python的优质写法  
  https://leetcode.com/problems/binary-tree-cameras/discuss/211269/C%2B%2B-Simple-DP-similar-to-House-Robber-III  
  ```  
  int minCameraCover(TreeNode* root) {  
          auto t = helper(root);  
          return min(get<1>(t), get<2>(t));  
      }  
  	  
      tuple<int, int, int> helper(TreeNode *root) {  
          if (!root) {  
              return {0, 0, 1};  
          }  
          int left_not_covered, left_covered, left_parent_covered;  
          tie(left_not_covered, left_covered, left_parent_covered) = helper(root->left);  
          int right_not_covered, right_covered, right_parent_covered;  
          tie(right_not_covered, right_covered, right_parent_covered) = helper(root->right);  
          return {left_covered + right_covered,   
                  min(left_not_covered + right_not_covered + 1,  
                      min(left_parent_covered + right_covered, left_covered + right_parent_covered)),   
                  min(left_not_covered, left_covered) + min(right_not_covered, right_covered) + 1};  
      }  
  ```  
    
tags:    
  -  重点数学  
  -  树  
  -  DFS  
