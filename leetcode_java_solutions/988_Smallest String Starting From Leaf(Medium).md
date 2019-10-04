### description    
  Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value of 0 represents 'a', a value of 1 represents 'b', and so on.  
    
  Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.  
    
  (As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)  
    
     
    
  Example 1:  
    
    
    
  Input: [0,1,2,3,4,3,4]  
  Output: "dba"  
  Example 2:  
    
    
    
  Input: [25,1,3,1,3,0,2]  
  Output: "adz"  
  Example 3:  
    
    
    
  Input: [2,2,1,null,1,0,null,0]  
  Output: "abc"  
     
    
  Note:  
    
  The number of nodes in the given tree will be between 1 and 8500.  
  Each node in the tree will have a value between 0 and 25.  
### solution    
```    
  Runtime: 2 ms, faster than 64.92% of Java online submissions for Smallest String Starting From Leaf.  
  Memory Usage: 37.2 MB, less than 100.00% of Java online submissions for Smallest String Starting From Leaf.  
    
  class Solution {  
      
      private String res = null;  
      public String smallestFromLeaf(TreeNode root) {  
          StringBuilder builder = new StringBuilder();  
          dfs(builder, root);  
          return new StringBuilder(res).reverse().toString();  
      }  
    
      private void dfs(StringBuilder builder, TreeNode root) {  
          char cur = (char) ('a' + root.val);  
          builder.append(cur);  
          if(root.left == null && root.right == null) {  
              if(res == null || check(builder)) {  
                  res = builder.toString();  
              }  
              builder.deleteCharAt(builder.length() - 1);  
              return;  
    
          }  
    
          if(root.left != null) {  
              dfs(builder, root.left);  
          }  
          if(root.right != null) {  
              dfs(builder, root.right);  
          }  
          builder.deleteCharAt(builder.length() - 1);  
      }  
    
      //if true ，说明当前builder更小  
      private boolean check(StringBuilder builder) {  
          int resIndex = res.length() - 1;  
          int bIndex = builder.length() - 1;  
          int min = Math.min(resIndex, bIndex);   
          while(min >= 0) {     // error: 一开始少了=  
              if(builder.charAt(bIndex) == res.charAt(resIndex)) {  
                  bIndex--;  
                  resIndex--;  
                  min--;        //error： 少了这行  
                  continue;  
              } else if(builder.charAt(bIndex) < res.charAt(resIndex)) {  
                  return true;  
              } else {  
                  return false;  
              }  
    
          }  
          return builder.length() - res.length() < 0;  
      }  
  }  
```    
    
### 个人解读    
  思路一：暴力法，每个支线都遍历一遍，然后看结果。  
  为了效率，reverse放在最后提交时候。  
    
  总结：   
  1、万事不决先暴力法试试  
  2、注意小细节  
    
tags:    
  -  字符串  
  -  流水账  
