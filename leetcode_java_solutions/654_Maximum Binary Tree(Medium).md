### description    
  Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:  
    
  The root is the maximum number in the array.  
  The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.  
  The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.  
  Construct the maximum tree by the given array and output the root node of this tree.  
    
  Example 1:  
  Input: [3,2,1,6,0,5]  
  Output: return the tree root node representing the following tree:  
    
        6  
      /   \  
     3     5  
      \    /   
       2  0     
         \  
          1  
  Note:  
  The size of the given array will be in the range [1,1000].  
### solution    
```    
  
Runtime: 2 ms, faster than 99.69% of Java online submissions for Maximum Binary Tree.  
Memory Usage: 38.1 MB, less than 97.83% of Java online submissions for Maximum Binary Tree.  
  
  
  class Solution {  
       
      public TreeNode constructMaximumBinaryTree(int[] nums) {  
          if(nums == null || nums.length == 0) return null;  
          TreeNode root = build(nums, 0, nums.length - 1);  
          return root;  
      }  
    
      private TreeNode build(int[] nums, int start, int end) {  
          if(start > end) {  
              return null;  
          }  
          int maxIndex = getMaxIndex(nums, start, end);  
          TreeNode node = new TreeNode(nums[maxIndex]);  
          node.left = build(nums, start, maxIndex - 1);  
          node.right = build(nums, maxIndex + 1, end);  
          return node;  
    
      }  
    
      private int getMaxIndex(int[] nums, int i, int j) {  
          if(i > j) return -1;  
          int index = i;  
          for(int k = i; k <= j; k++) {  
              int cur = nums[k];  
              if(cur > nums[index]) {  
                  index = k;  
              }  
          }  
          return index;  
      }  
  }  
```    
    
### 个人解读    
  一个很简单的递归就完事了  
    
tags:    
  -  树  
  -  递归  
