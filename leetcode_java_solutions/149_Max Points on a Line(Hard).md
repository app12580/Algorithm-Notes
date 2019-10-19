### description    
  Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.  
    
  Example 1:  
    
  Input: [[1,1],[2,2],[3,3]]  
  Output: 3  
  Explanation:  
  ^  
  |  
  |        o  
  |     o  
  |  o    
  +------------->  
  0  1  2  3  4  
  Example 2:  
    
  Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]  
  Output: 4  
  Explanation:  
  ^  
  |  
  |  o  
  |     o        o  
  |        o  
  |  o        o  
  +------------------->  
  0  1  2  3  4  5  6  
  NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.  
### solution    
```    
Runtime: 13 ms, faster than 59.53% of Java online submissions for Max Points on a Line.  
Memory Usage: 36.1 MB, less than 85.71% of Java online submissions for Max Points on a Line.  
  
  class Solution {  
      public int maxPoints(int[][] points) {  
          int len = points.length;  
          if(len <= 2) {  
              return len;  
          }  
    
          Map<String, Integer> counts = new HashMap<>();  
          int overlap = 0;  
          int res = 0;  
          for(int i = 0; i < len; i++) {  
              int[] point = points[i];  
              overlap = 0;  
              counts.clear();  
              for(int j = i + 1; j < len; j++) {  
                  int[] next = points[j];  
                  int x = next[0] - point[0];  
                  int y = next[1] - point[1];  
                  if(x == 0 && y == 0) {  
                      overlap++;  
                      continue;  
                  } else if(x == 0) {  
                      counts.put("x==0", counts.getOrDefault("x==0", 0) + 1);  
                      continue;  
                  } else if(y == 0) {  
                      counts.put("y==0", counts.getOrDefault("y==0", 0) + 1);  
                      continue;  
                  }   
                  int gcd = gcd(x, y);  
                  x /= gcd;  
                  y /= gcd;  
                  String key = "" + x + "/" + y;  
                  counts.put(key, counts.getOrDefault(key, 0) + 1);  
              }  
                  res = Math.max(res, overlap);  
              for(int val: counts.values()) {  
                  res = Math.max(res, val + overlap);  
              }  
          }  
          return res + 1;  
      }  
        
       private int gcd(int a,int b){  
          if (b==0) return a;  
          else return gcd(b,a%b);  
      }  
  }  
```    
    
### 个人解读    
  有种无可奈何，只能暴力法的感觉了。  
    
  看了一圈，确实只能用暴力法了。  
  方法一：  
  二重遍历，以i为起点，然后看剩下的所有点的斜率的是哪个。  
    
  总结：  
  总之暴力法就完事了  
    
tags:    
  -  无可奈何暴力法  
