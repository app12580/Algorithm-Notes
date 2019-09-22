### description    
  Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)  
    
  We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.  
    
  (Note also that a translation does not include any kind of rotation.)  
    
  What is the largest possible overlap?  
    
  Example 1:  
    
  Input: A = [[1,1,0],  
              [0,1,0],  
              [0,1,0]]  
         B = [[0,0,0],  
              [0,1,1],  
              [0,0,1]]  
  Output: 3  
  Explanation: We slide A to right by 1 unit and down by 1 unit.  
  Notes:   
    
  1 <= A.length = A[0].length = B.length = B[0].length <= 30  
  0 <= A[i][j], B[i][j] <= 1  
### solution    
```    
Runtime: 59 ms, faster than 46.93% of Java online submissions for Image Overlap.  
Memory Usage: 38.2 MB, less than 100.00% of Java online submissions for Image Overlap.  
  
  class Solution {  
     public int largestOverlap(int[][] A, int[][] B) {  
          int N = A.length;  
          List<Integer> LA = new ArrayList<>(),  LB = new ArrayList<>();  
          HashMap<Integer, Integer> count = new HashMap<>();  
          for (int i = 0; i < N * N; ++i)  
              if (A[i / N][i % N] == 1)  
                  LA.add(i / N * 100 + i % N);    //A0B 表示[A,B]位置的地方  
          for (int i = 0; i < N * N; ++i)  
              if (B[i / N][i % N] == 1)  
                  LB.add(i / N * 100 + i % N);  
          for (int i : LA) for (int j : LB)  
              count.put(i - j, count.getOrDefault(i - j, 0) + 1); //偏移量就是数字的操作方式  
          int res = 0;  
          for (int i : count.values())  
              res = Math.max(res, i);  
          return res;  
      }  
    
  }  
```    
    
### 个人解读    
  感觉不可能去每个步骤都去遍历的。  
  先想一个题外话，如果3X3，有多少种操作。。  
  感觉也是一个非常麻烦的事情。  
  感觉这个题目毫无头绪啊。。。  
    
  需要将平移操作量化，然后通过一个数字来作为特征量来描述是否能够满足条件。  
  然后下一步就需要意识到，所谓特征值就是i和j的差距。  
    
tags:    
  -  重点数学  
  -  矩阵  
