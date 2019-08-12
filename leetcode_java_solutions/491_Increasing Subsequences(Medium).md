### description    
  Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2.  
    
     
    
  Example:  
    
  Input: [4, 6, 7, 7]  
  Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]  
     
    
  Note:  
    
  The length of the given array will not exceed 15.  
  The range of integer in the given array is [-100,100].  
  The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.  
    
    
### solution    
```    
Runtime: 25 ms, faster than 20.76% of Java online submissions for Increasing Subsequences.  
Memory Usage: 59.3 MB, less than 14.29% of Java online submissions for Increasing Subsequences.  
  
  
  class Solution {  
      public List<List<Integer>> findSubsequences(int[] nums) {  
          List<List<Integer>> res = new ArrayList<>();  
          Set<String> hasSet = new HashSet<>();  
          dfs(new ArrayList<Integer>(), res, hasSet, nums, 0);  
          return res;  
      }  
    
      private void dfs(List<Integer> integers, List<List<Integer>> res, Set<String> hasSet, int[] nums, int start) {  
          if(start == nums.length) return;  
          int min = integers.size() == 0 ? Integer.MIN_VALUE : integers.get(integers.size() - 1);  
          for(int i = start; i < nums.length; i++) {  
              int cur = nums[i];  
              if(cur >= min) {  
                  if(integers.size() == 0) {  
                    integers.add(cur);  
                    dfs(integers, res, hasSet, nums, i + 1);  
                    integers.remove(integers.get(integers.size() - 1));  
                  } else {  
                    String s = listToString(integers) + cur;    
                    if(!hasSet.contains(s)) {  
                        integers.add(cur);  
                        res.add(new ArrayList<>(integers));  
                        hasSet.add(s);  
                        dfs(integers, res, hasSet, nums, i + 1);  
                        integers.remove(integers.get(integers.size() - 1));  
                    }  
                  }  
              }  
          }  
      }  
    
      private String listToString(List<Integer> integers) {  
          StringBuilder builder = new StringBuilder(integers.size());  
          for(int num: integers) {  
              builder.append(num + "-");  
          }  
          return builder.toString();  
      }  
  }  
```    
    
### 个人解读    
  DFS，结果集合类型，需要回溯。  
  如何防止重复的元素？一开始想的是遇到连续的77777，只放入最后的一个7，然而源数组没说是排序的。  
    
tags:    
  -  待优化  
  -  DFS  
  -  回溯  
