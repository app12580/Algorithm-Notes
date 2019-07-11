### description    
  Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.  
    
  For example, given the following triangle  
    
  [  
       [2],  
      [3,4],  
     [6,5,7],  
    [4,1,8,3]  
  ]  
  The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).  
    
  Note:  
    
  Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.  
### solution    
```    
    
  // 方法一： 暴力DFS 超时  
  class Solution {  
      int res = Integer.MAX_VALUE;  
    
      public int minimumTotal(List<List<Integer>> triangle) {  
          int size = triangle.size();  
          int[] arr = new int[size];  
          dfs(arr, triangle, 1);  
          return res;  
      }  
    
      private void dfs(int[] arr, List<List<Integer>> triangle, int index) {  
          if(index == triangle.size()) {  
              int cur = 0;  
              for(int i = 0; i < index; i++) {  
                  cur += triangle.get(i).get(arr[i]);  
              }  
              res = Math.min(res, cur);  
              return;  
          }  
          int now = arr[index - 1];  
          for(int i = now; i <= now + 1; i++) {  
              arr[index] = i;              
              dfs(arr, triangle, index + 1);  
          }  
      }  
    
  }  
    
    
  // 方法二： DP的收束  
  class Solution {  
        // A[j] 表示每一行时，到达第j个点的最小结果。  
      public int minimumTotal(List<List<Integer>> triangle) {  
          int[] A = new int[triangle.size()+1];  
          for(int i=triangle.size()-1;i>=0;i--){  
              for(int j=0;j<triangle.get(i).size();j++){  
                  A[j] = Math.min(A[j],A[j+1])+triangle.get(i).get(j);  
              }  
          }  
          return A[0];  
      }  
  }  
    
    
```    
    
### 个人解读    
  DFS， 回溯，比较min。  
  因为超时了，想办法有没有优化。  
  突然蹦出来了一个念头：《剪枝》  
    
  想出来了一个关键点，除了首行外，每一行只用01两个数字去描述，  
  先全部选择0，然后比较，看哪些能右移一个，完成此操作后，从头开始，另第一个0变成1，然后再去重复此操作。  
  还是不太行额。。。  
    
  方法二的核心要点在于两个：  
  1、DP方向的收束  
  2、dp是每一行都在变化  
    
tags:    
  -  DP的收束（方向变化）  
  -  DP的变动    
