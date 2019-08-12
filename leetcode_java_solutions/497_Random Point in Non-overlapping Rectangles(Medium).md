### description    
  Given a list of non-overlapping axis-aligned rectangles rects, write a function pick which randomly and uniformily picks an integer point in the space covered by the rectangles.  
    
  Note:  
    
  An integer point is a point that has integer coordinates.   
  A point on the perimeter of a rectangle is included in the space covered by the rectangles.   
  ith rectangle = rects[i] = [x1,y1,x2,y2], where [x1, y1] are the integer coordinates of the bottom-left corner, and [x2, y2] are the integer coordinates of the top-right corner.  
  length and width of each rectangle does not exceed 2000.  
  1 <= rects.length <= 100  
  pick return a point as an array of integer coordinates [p_x, p_y]  
  pick is called at most 10000 times.  
  Example 1:  
    
  Input:   
  ["Solution","pick","pick","pick"]  
  [[[[1,1,5,5]]],[],[],[]]  
  Output:   
  [null,[4,1],[4,1],[3,3]]  
  Example 2:  
    
  Input:   
  ["Solution","pick","pick","pick","pick","pick"]  
  [[[[-2,-2,-1,-1],[1,0,3,0]]],[],[],[],[],[]]  
  Output:   
  [null,[-1,-2],[2,0],[-2,-1],[3,0],[-2,-2]]  
  Explanation of Input Syntax:  
    
  The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array of rectangles rects. pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.  
### solution    
```    
//  方法一： 失败  
  大数不通过。  
  class Solution {  
  private int[][] rects;  
      private List<Integer> areaList;  
      private int sum = 0;  
      private Random random = new Random();  
    
      public Solution(int[][] rects) {  
          this.rects = rects;  
          areaList = new ArrayList<>(rects.length);  
          for(int i = 0; i < rects.length; i++) {  
              int[] rect = rects[i];  
              int s = (rect[2] - rect[0] + 1)*(rect[3] - rect[1] + 1);  
              sum += s;  
              areaList.add(s);  
          }  
      }  
    
      public int[] pick() {  
          int index = 0;  
          int r = random.nextInt(sum);  
          int cur = 0;  
          for(int num: areaList) {  
              if(cur + num <= r) {  
                  index++;  
                  cur += num;  
              }  
          }  
          int chaju = r - cur;  
          int[] rect = rects[index];  
          int x = rect[0] + (chaju % (rect[2] - rect[0] +1));  
          int y = rect[1] + (chaju / (rect[2] - rect[0] +1));  
          return new int[]{x, y};  
      }  
  }  
    
  // 方法二 二分法  
  感觉除了多了一个二分法外，和方法一是一样子的啊。。。  
  class Solution {  
      private Random random;  
      private int[] sums;  
      private int[][] rects;  
        
      public Solution(int[][] rects) {  
          random = new Random();  
          this.rects = rects;  
          sums = new int[rects.length];  
          for (int i = 0; i < rects.length; i++) {  
              sums[i] = i == 0  
                      ? (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1)  
                      : (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1) + sums[i - 1];  
          }  
      }  
        
      public int[] pick() {  
          int index = random.nextInt(sums[sums.length - 1]) + 1;  
          int left = 0, right = sums.length, mid = 0;  
          while (left < right) {  
              mid = ((right - left) >> 1) + left;  
              if (sums[mid] < index) {  
                  left = mid + 1;  
              } else {  
                  right = mid;  
              }  
          }  
          if (left == sums.length) left--;  
    
          int count = left == 0 ? sums[left] : sums[left] - sums[left - 1];  
          int rect_index = random.nextInt(count);  
          int rect_long = rects[left][2] - rects[left][0] + 1;  
          return new int[]{rects[left][0] + rect_index % rect_long, rects[left][1] + rect_index / rect_long};  
      }  
        
  }  
     
```    
    
### 个人解读    
  随机数的应用，本题在于如何把不同区间中的点“平等的分配”，从题意上面理解，肯定是根据全部的点个数来作为平均的基数的。  
  不能先确定矩阵或者x坐标，因为这样子会导致不平均了。只能先面积选，然后再从矩阵里面选点。  
    
  方法二整体思路和方法一一致，只是多了一个二分法查询的步骤，待查明为啥没通过。
  这种不通过真的不知道问题在哪里。    
    
tags:    
  -  随机数  
  -  模拟  
  -  待查明原因  
