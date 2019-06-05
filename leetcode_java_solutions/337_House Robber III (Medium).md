### description    
  The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.  
    
  Determine the maximum amount of money the thief can rob tonight without alerting the police.  
    
  Example 1:  
    
  Input: [3,2,3,null,3,null,1]  
    
       3  
      / \  
     2   3  
      \   \   
       3   1  
    
  Output: 7   
  Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.  
  Example 2:  
    
  Input: [3,4,5,1,3,null,1]  
    
       3  
      / \  
     4   5  
    / \   \   
   1   3   1  
    
  Output: 9  
  Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.  
### solution    
```    
//方法一： 递归 从大到小  
Runtime: 565 ms, faster than 28.01% of Java online submissions for House Robber III.  
Memory Usage: 38.3 MB, less than 88.45% of Java online submissions for House Robber III.  
 //问题所在： 出现了指数扩展现象  
  class Solution {  
     public int rob(TreeNode root) {  
              if(root == null) {  
                return 0;  
            }  
            int robCur = root.val;  
            if(root.left != null) {  
                robCur += rob(root.left.left) + rob(root.left.right);  
            }  
            if(root.right != null) {  
                robCur += rob(root.right.left) + rob(root.right.right);  
            }  
            int notRobCur = rob(root.left) + rob(root.right);  
            return Math.max(robCur, notRobCur);  
        }  
  }  
    
  //方法二： 优化  
  class Solution {  
      public int rob(TreeNode root) {  
          int[] res = helper(root);  
          return Math.max(res[0], res[1]);         
      }  
        
      private int[] helper(TreeNode root){  
          if(root == null) return new int[2];  
          int[] left = helper(root.left);  
          int[] right = helper(root.right);  
            
          int[] res = new int[2];  
        res[0] = left[1] + right[1] + root.val;     //res[0] 包含本节点  
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); //res[1] 不包含本节点  
            
          return res;  
      }  
  }  
```    
    
### 个人解读    
  第一反应还是动态规划，然而树形的动态规划。。。有点头大诶。  
  根据指标，是否rob当前节点。  
    
tags:    
  -  树  
  -  DP  
