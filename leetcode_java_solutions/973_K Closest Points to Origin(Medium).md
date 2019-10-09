### description    
  We have a list of points on the plane.  Find the K closest points to the origin (0, 0).  
    
  (Here, the distance between two points on a plane is the Euclidean distance.)  
    
  You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)  
    
     
    
  Example 1:  
    
  Input: points = [[1,3],[-2,2]], K = 1  
  Output: [[-2,2]]  
  Explanation:   
  The distance between (1, 3) and the origin is sqrt(10).  
  The distance between (-2, 2) and the origin is sqrt(8).  
  Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.  
  We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].  
  Example 2:  
    
  Input: points = [[3,3],[5,-1],[-2,4]], K = 2  
  Output: [[3,3],[-2,4]]  
  (The answer [[-2,4],[3,3]] would also be accepted.)  
     
    
  Note:  
    
  1 <= K <= points.length <= 10000  
  -10000 < points[i][0] < 10000  
  -10000 < points[i][1] < 10000  
### solution    
```    
Runtime: 54 ms, faster than 35.89% of Java online submissions for K Closest Points to Origin.  
Memory Usage: 55.9 MB, less than 92.55% of Java online submissions for K Closest Points to Origin.  
  
  class Solution {  
     class Point{  
          int x;  
          int y;  
          int dis;  
          public Point(int x, int y) {  
              this.x = x;  
              this.y = y;  
              this.dis = x * x + y * y;  
          }  
      }  
      public int[][] kClosest(int[][] points, int K) {  
          PriorityQueue<Point> queue = new PriorityQueue<Point>((p1,p2) -> p2.dis - p1.dis);  //从大到小排序，poll出来的是最大的  
          for(int[] p: points) {  
              int x = p[0];  
              int y = p[1];  
              queue.offer(new Point(x, y));  
              if(queue.size() > K) {  
                  queue.poll();  
              }  
          }  
          int[][] res = new int[queue.size()][2];  
          int i = 0;  
          for(Point p: queue) {  
              res[i][0] = p.x;  
              res[i][1] = p.y;  
              i++;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  Kth问题，但是由于只调用一次，所以很容易。  
  考察数据结构使用，采用PriorityQueue优先队列  
    
tags:    
  -  数据结构  
  -  优先队列  
  -  Kth问题  
