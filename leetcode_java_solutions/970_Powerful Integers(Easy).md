### description    
  Given two positive integers x and y, an integer is powerful if it is equal to x^i + y^j for some integers i >= 0 and j >= 0.  
    
  Return a list of all powerful integers that have value less than or equal to bound.  
    
  You may return the answer in any order.  In your answer, each value should occur at most once.  
    
     
    
  Example 1:  
    
  Input: x = 2, y = 3, bound = 10  
  Output: [2,3,4,5,7,9,10]  
  Explanation:   
  2 = 2^0 + 3^0  
  3 = 2^1 + 3^0  
  4 = 2^0 + 3^1  
  5 = 2^1 + 3^1  
  7 = 2^2 + 3^1  
  9 = 2^3 + 3^0  
  10 = 2^0 + 3^2  
  Example 2:  
    
  Input: x = 3, y = 5, bound = 15  
  Output: [2,4,6,8,10,14]  
     
    
  Note:  
    
  1 <= x <= 100  
  1 <= y <= 100  
  0 <= bound <= 10^6  
    
    
### solution    
```    
  
速度波动  
Runtime: 2 ms, faster than 38.31% of Java online submissions for Powerful Integers.  
Memory Usage: 33.7 MB, less than 9.38% of Java online submissions for Powerful Integers.  
  
Runtime: 1 ms, faster than 99.77% of Java online submissions for Powerful Integers.  
Memory Usage: 33.7 MB, less than 9.11% of Java online submissions for Powerful Integers.  
  
  
  class Solution {  
       public List<Integer> powerfulIntegers(int x, int y, int bound) {  
          Set<Integer> set = new HashSet<>();  
          dfs(set, x, y, bound, 1, 1);  
          List<Integer> res = new ArrayList<>();  
          for(int i: set) {  
              res.add(i);  
          }  
          return res;  
      }  
    
      private void dfs(Set<Integer> set, int x, int y, int bound, int xx, int yy) {  
          int v = xx + yy;  
          if(v > bound) {  
              return;  
          }  
          set.add(v);  
          if(x != 1) {  
              dfs(set, x, y, bound, xx * x, yy);  
          }  
          if(y != 1) {  
              dfs(set, x, y, bound, xx, yy * y);  
          }  
      }  
    
  }  
```    
    
### 个人解读    
  dfs遍历，获取所有的解。  
  为了防止重复，使用set。  
  注意x,y等于1的情况  
    
tags:    
  -  dfs  
