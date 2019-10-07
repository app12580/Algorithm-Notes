### description    
  Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].  The width of such a ramp is j - i.  
    
  Find the maximum width of a ramp in A.  If one doesn't exist, return 0.  
    
     
    
  Example 1:  
    
  Input: [6,0,8,2,1,5]  
  Output: 4  
  Explanation:   
  The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.  
  Example 2:  
    
  Input: [9,8,1,0,1,9,4,0,4,1]  
  Output: 7  
  Explanation:   
  The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.  
     
    
  Note:  
    
  2 <= A.length <= 50000  
  0 <= A[i] <= 50000  
### solution    
```    
Runtime: 21 ms, faster than 70.39% of Java online submissions for Maximum Width Ramp.  
Memory Usage: 49.6 MB, less than 100.00% of Java online submissions for Maximum Width Ramp.  
  
  
  class Solution {  
      public int maxWidthRamp(int[] A) {  
          int res = 0;  
          List<Integer> list = new ArrayList<>();  
          list.add(0);  
          for(int i = 1; i < A.length; i++) {  
              int cur = A[i];  
              if(cur < A[list.get(list.size() - 1)]) {  
                  list.add(i);  
              } else {  
                  //去找到第一个小于等于cur的点，可以使用二分法  
                  int j = search(A, list, cur);  
                  res = Math.max(res, i - j);  
              }  
          }  
          return res;  
      }  
    
      private int search(int[] A, List<Integer> list, int cur) {  
          int l = 0;  
          int h = list.size() - 1;  
          while(l < h) {  
              int m = l + (h - l) / 2;  
              if(A[list.get(m)] <= cur) {  
                  h = m;  
              } else {  
                  l = m + 1;  
              }  
          }  
          return list.get(l);   //error: 刚开始return成l了  
      }  
  }  
```    
    
### 个人解读    
  连续子数组问题，可选方案有：单调栈，双指针递归。  
  思路一：双指针递归。  
  需要注意，递归会出现指数扩散，需要中间结果存储。  
  结果：TLE。  
  想要解决，感觉需要O(N)才行啊  
    
  思路二：单调栈  
  首先判断是单调减栈，因为单调增后面大的数字没意义  
   [9,8,1,0,1,9,4,0,4,1]  
   刚开始很纠结，因为无论单调增还是单调减都很难办，一旦弹栈就over了，然后发现一件关键因素：可以不用弹栈。  
   这样子甚至可以用List代替栈。  
    
  总结：  
  如果在一开始就意识到："对于连续子数组问题，要么单调栈要么双指针"这一点，问题会变得很容易解决。  
    
  错误算法： TLE   
  ```  
  class Solution {  
      private Map<String, Integer> map;  
      public int maxWidthRamp(int[] A) {  
          map = new HashMap<>();  
          return helper(A, 0, A.length - 1);  
      }  
    
      private int helper(int[] A, int start, int end) {  
          if(start >= end) return 0;  
          String key = start + ":" + end;  
          if(map.containsKey(key)) {  
              return map.get(key);  
          }  
          if(A[start] <= A[end]) {  
              map.put(key, end - start);  
              return end - start;  
          }  
            int res = Math.max(helper(A, start, end - 1), helper(A, start + 1, end));  
            map.put(key, res);  
            return res;          
            }  
  }  
  ```  
    
tags:    
  -  单调栈  
