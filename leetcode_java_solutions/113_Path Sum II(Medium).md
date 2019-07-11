### description    
  Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.  
    
  Note: A leaf is a node with no children.  
    
  Example:  
    
  Given the below binary tree and sum = 22,  
    
        5  
       / \  
      4   8  
     /   / \  
    11  13  4  
   /  \    / \  
  7    2  5   1  
  Return:  
    
  [  
     [5,4,11,2],  
     [5,8,4,5]  
  ]  
### solution    
```    
  class Solution {  
      public List<List<Integer>> pathSum(TreeNode root, int sum) {  
          List<List<Integer>> res = new ArrayList<>();  
          if(root == null) {  
              return res;  
          }  
          List<Integer> curList = new ArrayList<>();  
          dfs(curList, res, root, sum);  
          return res;  
      }  
    
      private void dfs(List<Integer> curList, List<List<Integer>> res, TreeNode root, int sum) {  
          if(root.left == null && root.right == null) {  
              if(sum == root.val) {  
                  curList.add(root.val);  
                  res.add(new ArrayList<>(curList));  
                  curList.remove(curList.size() - 1);  
              }  
              return;  
          }  
          if(root.left != null) {  
              curList.add(root.val);  
              dfs(curList, res, root.left, sum - root.val);  
              curList.remove(curList.size() - 1);  
          }  
          if(root.right != null) {  
              curList.add(root.val);  
              dfs(curList, res, root.right, sum - root.val);  
              curList.remove(curList.size() - 1);  
          }  
    
      }  
  }  
```    
    
### 个人解读    
  dfs，获取结果集。  
  判断是否回溯：通过List作为中间集合，所以需要回溯。\  
  注意：叶子节点一定要有同时判断left和right，不然会在单支树的某一中间点，因为right为null而被统计在内。  
    
  总结： 唉，经过训练后，这种题目都是套模板的啊。  
    
tags:    
  -  树  
  -  回溯  
  -  DFS  
