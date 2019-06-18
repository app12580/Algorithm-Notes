### description    
  Given a set of distinct integers, nums, return all possible subsets (the power set).  
    
  Note: The solution set must not contain duplicate subsets.  
    
  Example:  
    
  Input: nums = [1,2,3]  
  Output:  
  [  
    [3],  
    [1],  
    [2],  
    [1,2,3],  
    [1,3],  
    [2,3],  
    [1,2],  
    []  
  ]  
### solution    
```    
  class Solution {  
        public List<List<Integer>> subsets(int[] nums) {  
          List<List<Integer>> res = new ArrayList<>();  
          if(nums == null || nums.length == 0) {  
              return res;  
          }  
          dfs(new ArrayList<Integer>(), res, nums, 0);  
          return res;  
      }  
    
      private void dfs(ArrayList<Integer> curList, List<List<Integer>> res, int[] nums, int index) {  
          if(index == nums.length) {  
              res.add(new ArrayList<>(curList));  
              return;  
          }  
            
          dfs(curList, res, nums, index + 1);  
            
          curList.add(nums[index]);  
          dfs(curList, res, nums, index + 1);  
          curList.remove(curList.size() - 1);  
      }  
  }  
```    
    
### 个人解读    
  本题可能疑虑：空集合是否会出现重复的。  
  只要控制总次数，因为总次数每一次都是空的只有一个，所以并不会重复。  
    
    
tags:    
  -  DFS   
  -  回溯   
