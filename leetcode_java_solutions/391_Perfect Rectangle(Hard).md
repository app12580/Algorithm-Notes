### description    
  Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.  
    
  Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).  
    
    
  Example 1:  
    
  rectangles = [  
    [1,1,3,3],  
    [3,1,4,2],  
    [3,2,4,4],  
    [1,3,2,4],  
    [2,3,3,4]  
  ]  
    
  Return true. All 5 rectangles together form an exact cover of a rectangular region.  
     
  Example 2:  
    
  rectangles = [  
    [1,1,2,3],  
    [1,3,2,4],  
    [3,1,4,2],  
    [3,2,4,4]  
  ]  
    
  Return false. Because there is a gap between the two rectangular regions.  
    
  Example 3:  
    
  rectangles = [  
    [1,1,3,3],  
    [3,1,4,2],  
    [1,3,2,4],  
    [3,2,4,4]  
  ]  
    
  Return false. Because there is a gap in the top center.  
### solution    
```    
  // 参考答案： 根据点的个数  
  class Solution {  
        
      public boolean isRectangleCover(int[][] rectangles) {  
    
          if (rectangles.length == 0 || rectangles[0].length == 0) return false;  
    
          int x1 = Integer.MAX_VALUE;  
          int x2 = Integer.MIN_VALUE;  
          int y1 = Integer.MAX_VALUE;  
          int y2 = Integer.MIN_VALUE;  
            
          HashSet<String> set = new HashSet<String>();  
          int area = 0;  
            
          for (int[] rect : rectangles) {  
              x1 = Math.min(rect[0], x1);  
              y1 = Math.min(rect[1], y1);  
              x2 = Math.max(rect[2], x2);  
              y2 = Math.max(rect[3], y2);  
                
              area += (rect[2] - rect[0]) * (rect[3] - rect[1]);  
                
              String s1 = rect[0] + " " + rect[1];  
              String s2 = rect[0] + " " + rect[3];  
              String s3 = rect[2] + " " + rect[3];  
              String s4 = rect[2] + " " + rect[1];  
                
              if (!set.add(s1)) set.remove(s1);  
              if (!set.add(s2)) set.remove(s2);  
              if (!set.add(s3)) set.remove(s3);  
              if (!set.add(s4)) set.remove(s4);  
          }  
            
          if (!set.contains(x1 + " " + y1) || !set.contains(x1 + " " + y2) || !set.contains(x2 + " " + y1) || !set.contains(x2 + " " + y2) || set.size() != 4) return false;  
            
          return area == (x2-x1) * (y2-y1);  
      }  
        
  }  
    
  // 低效率解法，暴力遍历  
  Runtime: 645 ms, faster than 5.09% of Java online submissions for Perfect Rectangle.  
  Memory Usage: 47 MB, less than 100.00% of Java online submissions for Perfect Rectangle.  
  class Solution {  
     public boolean isRectangleCover(int[][] rectangles) {  
          if (rectangles == null || rectangles.length == 0) return false;  
          int minLeft = Integer.MAX_VALUE;  
          int minBottom = Integer.MAX_VALUE;  
          int maxRight = Integer.MIN_VALUE;  
          int maxTop = Integer.MIN_VALUE;  
          int curArea = 0;  
          for (int i = 0; i < rectangles.length; i++) {  
              int[] r = rectangles[i];  
              if (r[0] < minLeft) {  
                  minLeft = r[0];  
              }  
              if (r[1] < minBottom) {  
                  minBottom = r[1];  
              }  
              if (r[2] > maxRight) {  
                  maxRight = r[2];  
              }  
              if (r[3] > maxTop) {  
                  maxTop = r[3];  
              }  
              curArea += (r[2] - r[0]) * (r[3] - r[1]);  
              for(int j = 0; j < i; j++) {  
                  if(cross(rectangles, i, j)) return false;  
              }  
          }  
          int width = maxRight - minLeft;  
          int height = maxTop - minBottom;  
          if (curArea != width * height) return false;  
          return true;  
      }  
    
      private boolean cross(int[][] rectangles, int i, int j) {  
          int[] r1 = rectangles[i];  
          int[] r2 = rectangles[j];  
          return r1[0] < r2[2] && r2[0] < r1[2] && r1[1] < r2[3] && r2[1] < r1[3];  
      }  
  }  
```    
    
### 个人解读    
  感觉不存在什么好的数学方法，只能想办法暴力了。  
  思路一：先遍历一遍，然后获取最左下点和最右上点。还有覆盖面积也顺便算一下。然后模拟一下所有的格子。  
  结果Memory Limit Exceeded。一个很大的测试用例。  
    
  思路二：  
  用一个数据结构存储所有的int[]。只要遍历完全部，满足以下条件即可返回true:  
  1、面积一致 2、不存在重合的。  
  感觉直接O(N^2)遍历即可。  
  然而5%的效率。  
  还行吧，至少过了hard题目。。。。  
    
  思路三：  
  在思路二的基础上，先预处理下，比如排个序什么的，然后就不用暴力比较了。  
  然而并没有什么卵用  
    
  参考答案：  
   1、面积一致 2、所有点除了四个端点，出现次数为偶数次。  
   这个是那种说一下点破就能秒懂的。  
    
tags:    
  -  数学  
  -  矩阵  
  -  图形学  
