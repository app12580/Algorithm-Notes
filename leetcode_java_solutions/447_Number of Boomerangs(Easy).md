### description    
  Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).  
    
  Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).  
    
  Example:  
    
  Input:  
  [[0,0],[1,0],[2,0]]  
    
  Output:  
  2  
    
  Explanation:  
  The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]  
    
  给定n个点，找出满足要求的点对的数量：  
  (点i,点j,点k)，使得ij两点的距离等于ik的距离。  
### solution    
```    
  //方法一 超时   
  class Solution {  
      public int numberOfBoomerangs(int[][] points) {  
          int res = 0;  
          for(int i = 0; i < points.length; i++) {  
              for(int j = 0; j < points.length; j++) {  
                  for(int k = 0; k < points.length; k++) {  
                      if(i == j || i == k || j == k) continue;  
                      if(check(points, i, j, k)) {  
                          res++;  
                      }  
                  }  
              }  
          }  
          return res;  
      }  
    
      private boolean check(int[][] points, int i, int j, int k) {  
          return disPlus(points[i], points[j]) == disPlus(points[i], points[k]);  
      }  
    
      private int disPlus(int[] p1, int[] p2) {  
          int x1 = p1[0];  
          int y1 = p1[1];  
          int x2 = p2[0];  
          int y2 = p2[1];  
          return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);  
      }  
  }  
    
  //方法二 优化成二重循环  
    
  Runtime: 109 ms, faster than 45.69% of Java online submissions for Number of Boomerangs.  
  Memory Usage: 47.9 MB, less than 42.02% of Java online submissions for Number of Boomerangs.  
    
    
  class Solution {  
       public int numberOfBoomerangs(int[][] points) {  
          int res = 0;  
          for(int i = 0; i < points.length; i++) {  
              Map<Integer, Integer> map = new HashMap<>();    //key:distance value:count  
              for(int j = 0; j < points.length; j++) {  
                  if(i == j) continue;  
                  int dis = disPlus(points[i], points[j]);  
                  int val = map.getOrDefault(dis, 0);  
                  res += val * 2;  
                  map.put(dis, val + 1);  
              }  
          }  
    
          return res;  
      }  
     
    
      private int disPlus(int[] p1, int[] p2) {  
          int x1 = p1[0];  
          int y1 = p1[1];  
          int x2 = p2[0];  
          int y2 = p2[1];  
          return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);  
      }  
  }  
    
    
  // 方法三 结构优化  
    
  Runtime: 55 ms, faster than 95.88% of Java online submissions for Number of Boomerangs.  
  Memory Usage: 43.5 MB, less than 70.31% of Java online submissions for Number of Boomerangs.  
    
  //只是把map的每一次初始化变成了clear，速度直接变成前列的了  
    
  class Solution {  
       public int numberOfBoomerangs(int[][] points) {  
          int res = 0;  
           Map<Integer, Integer> map = new HashMap<>();  
          for(int i = 0; i < points.length; i++) {  
                  //key:distance value:count  
              for(int j = 0; j < points.length; j++) {  
                  if(i == j) continue;  
                  int dis = disPlus(points[i], points[j]);  
                  int val = map.getOrDefault(dis, 0);  
                  res += val * 2;  
                  map.put(dis, val + 1);  
              }  
              map.clear();  
          }  
    
          return res;  
      }  
     
    
      private int disPlus(int[] p1, int[] p2) {  
          int x1 = p1[0];  
          int y1 = p1[1];  
          int x2 = p2[0];  
          int y2 = p2[1];  
          return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);  
      }  
  }  
```    
    
### 个人解读    
  大概直接一个三重循环就可以了吧。。  
  因为超时，想办法优化。  
  发现二重循环即可，然后把每次的距离扔进一个中间结构里面，需要注意的是res += val * 2  
    
tags:    
  -  数学  
  -  矩阵  
  -  算法优化  
