### description      
  Students are asked to stand in non-decreasing order of heights for an annual photo.    
      
  Return the minimum number of students not standing in the right positions.  (This is the number of students that must move in order for all students to be standing in non-decreasing order of height.)    
      
       
      
  Example 1:    
      
  Input: [1,1,4,2,1,3]    
  Output: 3    
  Explanation:     
  Students with heights 4, 3 and the last 1 are not standing in the right positions.    
       
      
  Note:    
      
  1 <= heights.length <= 100    
  1 <= heights[i] <= 100    
### solution      
```      
    
// 方法一： 预处理，先排序    
Runtime: 1 ms, faster than 93.77% of Java online submissions for Height Checker.    
Memory Usage: 34.6 MB, less than 100.00% of Java online submissions for Height Checker.    
    
  class Solution {    
      public int heightChecker(int[] heights) {    
          int[] copy = new int[heights.length];    
          System.arraycopy(heights, 0, copy, 0, heights.length);    
          Arrays.sort(copy);    
          int res = 0;    
          for(int i = 0; i < heights.length; i++) {    
              if(heights[i] != copy[i]) res++;    
          }    
          return res;    
      }    
  }    
  
  // 方法二：  计数法
  class Solution {
      public int heightChecker(int[] heights) {
          int[] arr = new int[101];
          for (int height : heights) {
              arr[height]++;
          }
          int count = 0;
          for (int i = 1, j = 0; i < arr.length; i++) {
              while (arr[i]-- > 0) {
                  if (heights[j++] != i) count++;
              }
          }
          return count;
      }
  }
  
```      
      
### 个人解读      
  第一时间想到了[581](581_Shortest%20Unsorted%20Continuous%20Subarray(Easy).md)    
      
  本题的题目描述有问题，什么学生排队，什么一句move就结束了。    
      
  本题有个重要前提没有讲清楚：那就是，所有的学生能站的位置是固定的，不能超出范围，也不能插进成小数的索引位置。    
      
tags:      
  -  题目描述    
  -  预处理    
  -  优化    
