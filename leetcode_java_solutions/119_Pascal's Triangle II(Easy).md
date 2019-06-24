### description    
  Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.  
    
  Note that the row index starts from 0.  
    
    
  In Pascal's triangle, each number is the sum of the two numbers directly above it.  
  
  杨辉三角  
### solution    
```    
  class Solution {  
      public List<Integer> getRow(int rowIndex) {  
           List<Integer> res = new ArrayList<>();  
          if(rowIndex < 0) {  
              return res;  
          }  
          Integer[] arr = new Integer[rowIndex + 1];  
          Arrays.fill(arr, 0);  
          arr[0] = 1;  
          for(int i = 1; i <= rowIndex; i++) {  
              for(int j = i; j > 0; j--) {  
                  arr[j] = arr[j] + arr[j - 1];  
              }  
          }  
          return Arrays.asList(arr);  
      }  
  }  
```    
    
### 个人解读    
  
杨辉三角  
看数组，然后找规律  
0 [1]  
1 [1, 1]  
2 [1, 2, 1]  
3 [1, 3, 3, 1]  
4 [1, 4, 6, 4, 1]    //C41 C42  
  
为了节省空间，在一个数组里面实现，会发现arr[j] = arr[j] + arr[j-1]  
为了规避新一层数据的影响，从后往前遍历  
   
tags:    
  -  数组  
  -  遍历  
