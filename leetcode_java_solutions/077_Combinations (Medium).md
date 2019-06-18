### description    
  Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.  
    
  Example:  
    
  Input: n = 4, k = 2  
  Output:  
  [  
    [2,4],  
    [3,4],  
    [2,3],  
    [1,2],  
    [1,3],  
    [1,4],  
  ]  
    
  组合  
### solution    
```    
// 嘿嘿嘿，一遍成~  
  
Runtime: 2 ms, faster than 95.49% of Java online submissions for Combinations.  
Memory Usage: 39.5 MB, less than 73.73% of Java online submissions for Combinations.  
  
  
  class Solution {  
       public List<List<Integer>> combine(int n, int k) {  
          List<List<Integer>> res = new ArrayList<>();  
          if(n <= 0 || k <= 0) {  
              return res;  
          }  
          dfs(new ArrayList<Integer>(), res, 1, n, k);  
          return res;  
      }  
    
      private void dfs(ArrayList<Integer> curList, List<List<Integer>> res, int start, int end, int k) {  
          if(k == 0) {  
              res.add(new ArrayList(curList));  
              return;  
          }  
          if(end - start + 1 < k ) {  
              return;  
          }  
          for(int i = start; i <= end; i++) {  
              curList.add(i);  
              dfs(curList, res, i + 1, end, k - 1);  
              curList.remove(curList.size() - 1);  
          }  
      }  
  }  
```    
    
### 个人解读    
  先写一个例子：  
  [1,2,3,4]  
  1,2  
  1,3,  
  1,4  
  2,3  
  2,4  
  3,4  
    
  从左往右开始选取数字进行组合  
    
tags:    
  -  DFS  
  -  组合  
  -  回溯  
