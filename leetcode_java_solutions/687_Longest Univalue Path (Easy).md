### description    
  Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.  
    
  The length of path between two nodes is represented by the number of edges between them.  
    
  Example 1:  
    
  Input:  
    
                5  
               / \  
              4   5  
             / \   \  
            1   1   5  
  Output: 2  
    
  Example 2:  
    
  Input:  
    
                1  
               / \  
              4   5  
             / \   \  
            4   4   5  
  Output: 2  
    
  Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.  
    
  统计相同节点的最长路径  
### solution    
```    
//特别差的算法， 不过值得欣慰的是这个算法是写完一次成功的  
Runtime: 13 ms, faster than 5.91% of Java online submissions for Longest Univalue Path.  
Memory Usage: 40.8 MB, less than 97.53% of Java online submissions for Longest Univalue Path.  
  
  public int longestUnivaluePath(TreeNode root) {  
          if(root == null) {  
              return 0;  
          }  
          int max = Math.max(longestUnivaluePath(root.left), longestUnivaluePath(root.right));  
          return Math.max(max, helper(root));  
      }  
    
      public int helper(TreeNode node) {  
          if(node == null) {  
              return 0;  
          }  
          int val = node.val;  
          int lh = helper2(node.left, val);  
          int rh = helper2(node.right, val);  
          return lh + rh;  
            
      }  
        
      public int helper2(TreeNode node, int target) {  
          if(node == null) {  
              return 0;  
          }  
          if(node.val == target) {  
              return 1 + Math.max(helper2(node.left, target), helper2(node.right, target));  
          } else {  
             return 0;   
          }  
      }  
        
        
      //方法二 DFS  
      private int path = 0;  
        
      public int longestUnivaluePath(TreeNode root) {  
          dfs(root);  
          return path;  
      }  
        
      // 从某一节点延伸出来的最长路径  
      private int dfs(TreeNode root){  
          if (root == null) return 0;  
          int left = dfs(root.left);  
          int right = dfs(root.right);  
          int leftPath = root.left != null && root.left.val == root.val ? left + 1 : 0;  
          int rightPath = root.right != null && root.right.val == root.val ? right + 1 : 0;  
          path = Math.max(path, leftPath + rightPath);  
          return Math.max(leftPath, rightPath);  
      }  
```    
    
### 个人解读    
  判断： f(root) = Max(f(root.left), f(root.right), startWithRoot(root))  
  所以需要中间函数  
  中间函数定义：经过根节点的最长路径。   
  //发现，这个中间函数好像不是很好写啊。。。。  
    
  反思：过于执着的去找fn的递归规律了，然而忽略了单行就能找到结果。类似于无脑DP的弊端。  
  每个节点可以获取左边最长路径，和右边最长路径，然后left+right为以此节点为根节点的最长路径；max(left, right)为此节点的最长单向路径长。  
tags:    
  -  树  
  -  DFS  
