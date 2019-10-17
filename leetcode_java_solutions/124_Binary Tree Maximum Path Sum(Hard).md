### description      
  Given a non-empty binary tree, find the maximum path sum.    
      
  For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.    
      
  Example 1:    
      
  Input: [1,2,3]    
      
         1    
        / \    
       2   3    
      
  Output: 6    
  Example 2:    
      
  Input: [-10,9,20,null,null,15,7]    
      
     -10    
     / \    
    9  20    
      /  \    
     15   7    
      
  Output: 42    
### solution      
```      
   //方法一： 递归  
   Runtime: 158 ms, faster than 5.07% of Java online submissions for Binary Tree Maximum Path Sum.  
   Memory Usage: 44 MB, less than 5.95% of Java online submissions for Binary Tree Maximum Path Sum.  
     
class Solution {  
   public int maxPathSum(TreeNode root) {  
       if(root == null) return Integer.MIN_VALUE;  
        if(root.left == null && root.right == null) return root.val;  
        int max = Math.max(maxPathSum(root.left), maxPathSum(root.right));  
        max = Math.max(max, root.val + helper(root.left) + helper(root.right));  
        return max;  
    }  
  
  
    private int helper(TreeNode root) {  
        //经过root的最大路径和，返回与0的较大值  
        if(root == null) return 0;  
        if(root.left == null && root.right == null) return Math.max(0, root.val);  
        int t = Math.max(helper(root.left), helper(root.right));  
        return Math.max(root.val + t, 0);  
    }  
}   
  
// 方法二： 同样是递归+辅助函数  
Runtime: 1 ms, faster than 100.00% of Java online submissions for Binary Tree Maximum Path Sum.  
Memory Usage: 40 MB, less than 98.81% of Java online submissions for Binary Tree Maximum Path Sum.  
class Solution {  
 public int maxPathSum(TreeNode root) {  
        return helper(root)[0];  
    }  
  
    //int[0] 最大路径和  int[1] 包含root的最大单向路径和  
    private int[] helper(TreeNode root) {  
         if(root == null) {  
            return new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};  
         }  
         if(root.left == null && root.right == null) {  
             return new int[]{root.val, Math.max(0, root.val)};  
         }  
         int[] left = helper(root.left);  
         int[] right = helper(root.right);  
  
        int x = Math.max(left[0], right[0]);  
        int y = root.val + Math.max(left[1], 0) + Math.max(0, right[1]);  
        int z = root.val + Math.max(left[1], right[1]);  
        return new int[]{Math.max(x, y), Math.max(z, 0)};  
    }  
  
}  
```      
      
### 个人解读      
  思路一：递归+辅助函数  
  效率很低，但还好，至少不是TLE。。。  
    
  思路二：递归  
  每次返回两个值  
    
  总结：  
  1、要意识到，思路一为什么慢，因为多次遍历了，每次只获取了部分信息  
  2、不要吝惜java的多返回值函数  
      
tags:      
  -  树  
  -  递归  
  -  多返回值函数  
