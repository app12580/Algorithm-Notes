### description    
  Given a collection of distinct integers, return all possible permutations.  
    
  Example:  
    
  Input: [1,2,3]  
  Output:  
  [  
    [1,2,3],  
    [1,3,2],  
    [2,1,3],  
    [2,3,1],  
    [3,1,2],  
    [3,2,1]  
  ]  
    
  distinct integers    
  排列    
### solution    
```    
  
// 方法一： 标记法  
nice啊  
Runtime: 1 ms, faster than 99.45% of Java online submissions for Permutations.  
Memory Usage: 37.4 MB, less than 95.94% of Java online submissions for Permutations.  
  
  class Solution {  
      public List<List<Integer>> permute(int[] nums) {  
          List<List<Integer>> res = new ArrayList<>();  
          if(nums == null || nums.length == 0) {  
              return res;  
          }  
          boolean[] mark = new boolean[nums.length];  
          dfs(new ArrayList<Integer>(), res, nums, mark);  
          return res;  
      }  
    
      private void dfs(ArrayList<Integer> curList, List<List<Integer>> res, int[] nums, boolean[] mark) {  
          if(curList.size() == nums.length) {  
              res.add(copyList(curList));  
              return;  
          }  
          for(int i = 0; i < mark.length; i++) {  
              if(!mark[i]) {  
                  mark[i] = true;  
                  curList.add(nums[i]);  
                  dfs(curList, res, nums, mark);  
                  mark[i] = false;  
                  curList.remove(curList.size() - 1);  
              }  
          }  
      }  
    
     private List<Integer> copyList(ArrayList<Integer> src){  
          List<Integer> copy = new ArrayList();  
          copy.addAll(src);  
          return copy;  
      }  
  }  
```    
    
### 个人解读    
  DFS，遍历。  
  注意遍历的顺序，根据标记位来。全部的增加操作和回溯操作都在for循环内部。  
  注意List的copy写法。  
  // permutes.add(new ArrayList<>(permuteList)); // 重新构造一个 List  
 
    
tags:    
  -  DFS  
  -  回溯  
  -  排列  
