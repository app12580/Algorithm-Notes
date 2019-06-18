### description    
  Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.  
    
  Note:  
    
  All numbers will be positive integers.  
  The solution set must not contain duplicate combinations.  
  Example 1:  
    
  Input: k = 3, n = 7  
  Output: [[1,2,4]]  
  Example 2:  
    
  Input: k = 3, n = 9  
  Output: [[1,2,6], [1,3,5], [2,3,4]]  
    
  使用k个数字(1-9)组合成目标数n  
  不可重复  
### solution    
```    
  Runtime: 0 ms, faster than 100.00% of Java online submissions for Combination Sum III.  
  Memory Usage: 33.7 MB, less than 11.56% of Java online submissions for Combination Sum III.  
    
    
  class Solution {  
      public List<List<Integer>> combinationSum3(int k, int n) {  
          List<List<Integer>> res = new ArrayList<>();  
          if(k == 0 || n == 0) {  
              return res;  
          }  
          dfs(0, new ArrayList<Integer>(), res, k, n, 1);  
    
          return res;  
      }  
    
      private void dfs(int curSum, ArrayList<Integer> curList, List<List<Integer>> res, int k, int n, int startIndex) {  
          if(n == curSum && k == 0) {  
              res.add(new ArrayList(curList));  
              return;  
          }  
          if(n < curSum || k == 0 || k * 9 + curSum < n) {  
              return;  
          }  
          for(int i = startIndex; i <= 9; i++) {  
              curSum += i;  
              curList.add(i);  
              dfs(curSum, curList, res, k - 1, n, i + 1);  
              curSum -= i;  
              curList.remove(curList.size() - 1);  
          }  
    
      }  
  }  
    
    
    
    
  // 理解错了题目，当成可重复，并且不是必须k个数字而是至多k个数字   
  class Solution {  
      public List<List<Integer>> combinationSum3(int k, int n) {  
          List<List<Integer>> res = new ArrayList<>();  
          if(k == 0 || n == 0) {  
              return res;  
          }  
          dfs(0, new ArrayList<Integer>(), res, k, n, 1);  
    
          return res;  
      }  
    
      private void dfs(int curSum, ArrayList<Integer> curList, List<List<Integer>> res, int k, int n, int startIndex) {  
          if(n == curSum) {  
              res.add(new ArrayList(curList));  
              return;  
          }  
          if(n < curSum || k == 0 || k * 9 + curSum < n) {  
              return;  
          }  
          for(int i = startIndex; i <= 9; i++) {  
              curSum += i;  
              curList.add(i);  
              dfs(curSum, curList, res, k - 1, n, i);  
              curSum -= i;  
              curList.remove(curList.size() - 1);  
          }  
    
      }  
  }  
```    
    
### 个人解读    
    
  开始理解错了题目，当成可重复，并且不是必须k个数字而是至多k个数字   
  然后可以做修正。  
    
    
    
tags:    
  -  DFS  
  -  组合  
  -  回溯  
