### description    
  Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.  
    
  If there isn't any rectangle, return 0.  
    
     
    
  Example 1:  
    
  Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]  
  Output: 4  
  Example 2:  
    
  Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]  
  Output: 2  
     
    
  Note:  
    
  1 <= points.length <= 500  
  0 <= points[i][0] <= 40000  
  0 <= points[i][1] <= 40000  
  All points are distinct.  
### solution    
```    
Runtime: 247 ms, faster than 54.72% of Java online submissions for Minimum Area Rectangle.  
Memory Usage: 51.5 MB, less than 90.00% of Java online submissions for Minimum Area Rectangle.  
  
  
  class Solution {  
      public int minAreaRect(int[][] points) {  
          Map<Integer, Set<Integer>> map = new HashMap<>();  
          for (int[] p : points) {  
              if (!map.containsKey(p[0])) {  
                  map.put(p[0], new HashSet<>());  
              }  
              map.get(p[0]).add(p[1]);  
          }  
          int min = Integer.MAX_VALUE;  
          for (int[] p1 : points) {  
              for (int[] p2 : points) {  
                  if (p1[0] == p2[0] || p1[1] == p2[1]) { // if have the same x or y  
                      continue;  
                  }  
                  if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) { // find other two points  
                      min = Math.min(min, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));  
                  }  
              }  
          }  
          return min == Integer.MAX_VALUE ? 0 : min;  
      }  
  }  
```    
    
### 个人解读    
  除了暴力法外，还有其他办法么。。  
    
  关键点：  
  ```  
  其中矩形的边平行于 x 轴和 y 轴。  
  ```  
    
  想办法用空间换时间，把每条边的信息都存起来。  
  java遍历Map<Integer, List<>>这种真的很麻烦。。。。  
    
  先用暴力法测试一下效率把。结果TLE  
    
  反思，自己太拘泥与看矩形的四条边了，总是在想办法如何去把矩形的四条边用数据来描述。然而，解法是可以从对角线入手的。为什么自己会一直在想用四条边来描述，因为在这前一步，矩形是由四个顶点决定的。如果不用边，就要用四个点，会是一个四维遍历。大脑直接跳过了。这样不好。  
    
  总结：  
  1、预处理，通过hashmap存储每个点的位置信息，可以快速通过x或者y去找到  
  2、二维遍历，判断这两个点作为对角线是否会构成矩形。  
  其实这题目挺简单的，只是这个时候的自己蠢得发指。。  
    
    
  ```  
    暴力算法  
     public int minAreaRect(int[][] points) {  
            Map<Integer, List<Integer>> row = new HashMap<>();  
            Set<String> flags = new HashSet<>();  
            for (int i = 0; i < points.length; i++) {  
                int x = points[i][0];  
                int y = points[i][1];  
                if (!row.containsKey(x)) {  
                    row.put(x, new ArrayList<>());  
                }  
                row.get(x).add(y);  
            }  
            int res = Integer.MAX_VALUE;  
            for (Map.Entry<Integer, List<Integer>> entry : row.entrySet()) {  
                int r1 = entry.getKey();  
                List<Integer> l1 = entry.getValue();  
                for (Map.Entry<Integer, List<Integer>> list : row.entrySet()) {  
                    int r2 = list.getKey();  
                    if(r1 == r2 || flags.contains(r1+"-"+r2)) continue;  
                    List<Integer> l2 = list.getValue();  
                    res = Math.min(res, helper(l1, l2, Math.abs(r1 - r2)));  
                    flags.add((r1+"-"+r2));  
                    flags.add((r2+"-"+r1));  
                }  
            }  
            return Integer.MAX_VALUE == res ? 0 : res;  
        }  
      
        private int helper(List<Integer> l1, List<Integer> l2, int abs) {  
            //先去找l1和l2中最小的重合线段  
            List<Integer> same = new ArrayList<>();  
            for(int i: l1) {  
                if(l2.contains(i)) same.add(i);  
            }  
            if(same.size() <= 1) {  
                return Integer.MAX_VALUE;  
            }  
            int min = Integer.MAX_VALUE;  
            for(int i = 0; i < same.size(); i++) {  
                for(int j = i + 1; j <same.size(); j++) {  
                    min = Math.min(min, Math.abs(same.get(i) - same.get(j)));  
                }  
            }  
            return min * abs;  
        }  
  ```  
    
tags:    
  -  数学  
  -  矩阵  
