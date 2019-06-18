### description    
  Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.  
    
  The same repeated number may be chosen from candidates unlimited number of times.  
    
  Note:  
    
  All numbers (including target) will be positive integers.  
  The solution set must not contain duplicate combinations.  
  Example 1:  
    
  Input: candidates = [2,3,6,7], target = 7,  
  A solution set is:  
  [  
    [7],  
    [2,2,3]  
  ]  
  Example 2:  
    
  Input: candidates = [2,3,5], target = 8,  
  A solution set is:  
  [  
    [2,2,2,2],  
    [2,3,3],  
    [3,5]  
  ]  
    
  组合求和，数组元素可重复  
### solution    
```    
  
Runtime: 5 ms, faster than 51.75% of Java online submissions for Combination Sum.  
Memory Usage: 37.3 MB, less than 99.76% of Java online submissions for Combination Sum.  
  
  
  class Solution {  
       public List<List<Integer>> combinationSum(int[] candidates, int target) {  
          List<List<Integer>> res = new ArrayList<>();  
          if(candidates == null || candidates.length == 0 || target == 0) {  
              return res;  
          }  
          dfs(0, new ArrayList<Integer>(), res, candidates, target, 0);  
          return res;  
    
      }  
    
      private void dfs(int curSum, List<Integer> curList, List<List<Integer>> res, int[] candidates, int target, int startIndex) {  
          if(curSum == target) {  
              res.add(new ArrayList<>(curList));  
              return;  
          }  
          if(curSum > target) {  
              return;  
          }  
          for(int i = startIndex; i < candidates.length; i++) {  
              int c = candidates[i];  
              curSum += c;  
              curList.add(c);  
              dfs(curSum, curList, res, candidates, target, i);  
              curSum -= c;  
              curList.remove(curList.size() - 1);  
          }  
      }  
  }  
```    
    
### 个人解读    
  DFS，回溯问题  
  方法参数有当前总和，结果集，总要求。因为数组可重复，所以不需要标记法。  
  第一次思路，没有考虑重复的情况：223和322。额外设置一个startIndex即可。  
    
tags:    
  -  组合  
  -  回溯  
  -  DFS  
