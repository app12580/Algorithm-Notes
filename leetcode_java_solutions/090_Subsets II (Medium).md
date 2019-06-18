### description    
  Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).  
    
  Note: The solution set must not contain duplicate subsets.  
    
  Example:  
    
  Input: [1,2,2]  
  Output:  
  [  
    [2],  
    [1],  
    [1,2,2],  
    [2,2],  
    [1,2],  
    []  
  ]  
    
  含有重复元素的子集  
### solution    
```    
Runtime: 1 ms, faster than 100.00% of Java online submissions for Subsets II.  
Memory Usage: 37.3 MB, less than 98.54% of Java online submissions for Subsets II.  
  
  class Solution {  
      public List<List<Integer>> subsetsWithDup(int[] nums) {  
          List<List<Integer>> res = new ArrayList<>();  
          if(nums == null || nums.length == 0) {  
              return res;  
          }  
          Arrays.sort(nums);  
          boolean[] mark = new boolean[nums.length];  
          dfs(new ArrayList<Integer>(), res, nums, mark, 0);  
          return res;  
      }  
    
      private void dfs(ArrayList<Integer> curList, List<List<Integer>> res, int[] nums, boolean[] mark, int index) {  
          if(index == nums.length) {  
              res.add(new ArrayList(curList));  
              return;  
          }  
    
          dfs(curList, res, nums, mark, index + 1);  
          if(index > 0 && nums[index] == nums[index - 1] && !mark[index - 1]) {  
                
          } else {  
              curList.add(nums[index]);  
              mark[index] = true;  
              dfs(curList, res, nums, mark, index + 1);  
              curList.remove(curList.size() - 1);  
              mark[index] = false;  
          }  
      }  
  }  
```    
    
### 个人解读    
  主要问题在于重复元素，不知道组合那里的技巧还能否使用。  
  想了想，好像可以用。  
    
tags:    
  -  DFS  
  -  回溯  
