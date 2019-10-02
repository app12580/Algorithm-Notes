### description    
  Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.  
    
  Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.  
    
  You may return the answer in any order.  
    
     
    
  Example 1:  
    
  Input: N = 3, K = 7  
  Output: [181,292,707,818,929]  
  Explanation: Note that 070 is not a valid number, because it has leading zeroes.  
  Example 2:  
    
  Input: N = 2, K = 1  
  Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]  
     
    
  Note:  
    
  1 <= N <= 9  
  0 <= K <= 9  
### solution    
```    
// 方法一： DFS模板  
Runtime: 8 ms, faster than 38.29% of Java online submissions for Numbers With Same Consecutive Differences.  
Memory Usage: 40.9 MB, less than 25.00% of Java online submissions for Numbers With Same Consecutive Differences.  
  
  class Solution {  
     public int[] numsSameConsecDiff(int N, int K) {  
          Set<Integer> res = new HashSet<>();  
          List<Integer> firstList = new ArrayList<>();  
          for (int i = 1; i < 10; i++) {  
              firstList.add(i);  
          }  
          if(N == 1) res.add(0);  
          dfs(0, firstList, res, 0, N, K);  
          int[] arr = new int[res.size()];  
          int i = 0;  
          for (int r: res) {  
              arr[i++] = r;  
          }  
          return arr;  
      }  
    
      private void dfs(Integer preNum, List<Integer> numList, Set<Integer> res, int index, int n, int k) {  
          if (index == n) {  
              res.add(preNum);  
              return;  
          }  
          for (int next : numList) {  
              preNum = preNum * 10 + next;  
              dfs(preNum, getList(next, k), res, index + 1, n, k);  
              preNum /= 10;  
          }  
      }  
    
      private List<Integer> getList(int num, int k) {  
          List<Integer> list = new ArrayList<>();  
          if (num >= k) {  
              list.add(num - k);  
          }  
          if (num + k < 10) {  
              list.add(num + k);  
          }  
          return list;  
      }  
  }  
    
  // 方法二： 优化前面getList的错误  
  class Solution {  
      public int[] numsSameConsecDiff(int N, int K) {  
          List<Integer> res = new ArrayList<>();  
          List<Integer> firstList = new ArrayList<>();  
          for (int i = 1; i < 10; i++) {  
              firstList.add(i);  
          }  
          if(N == 1) res.add(0);  
          dfs(0, firstList, res, 0, N, K);  
          int[] arr = new int[res.size()];  
          for (int i = 0; i < arr.length; i++) {  
              arr[i] = res.get(i);  
          }  
          return arr;  
      }  
    
      private void dfs(Integer preNum, List<Integer> numList, List<Integer> res, int index, int n, int k) {  
          if (index == n) {  
              res.add(preNum);  
              return;  
          }  
          for (int next : numList) {  
              preNum = preNum * 10 + next;  
              dfs(preNum, getList(next, k), res, index + 1, n, k);  
              preNum /= 10;  
          }  
      }  
    
      private List<Integer> getList(int num, int k) {  
          List<Integer> list = new ArrayList<>();  
          if(k == 0) {  
              list.add(num);  
              return list;  
          }  
          if (num >= k) {  
              list.add(num - k);  
          }  
          if (num + k < 10) {  
              list.add(num + k);  
          }  
          return list;  
      }  
  }  
```    
    
### 个人解读    
  类似于排列组合，基本上可以套用模板的了。  
    
  总结：  
  1、注意0的特殊情况if(N == 1)  
  2、K==0时候，getList的错误导致重复数字了。  
    
tags:    
  -  DFS    
