### description    
  Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.  
    
    
  In Pascal's triangle, each number is the sum of the two numbers directly above it.  
    
  Example:  
    
  Input: 5  
  Output:  
  [  
       [1],  
      [1,1],  
     [1,2,1],  
    [1,3,3,1],  
   [1,4,6,4,1]  
  ]  
### solution    
```    
    
  Runtime: 0 ms, faster than 100.00% of Java online submissions for Pascal's Triangle.  
  Memory Usage: 34.1 MB, less than 5.06% of Java online submissions for Pascal's Triangle.  
    
    
  class Solution {  
      public List<List<Integer>> generate(int numRows) {  
          List<List<Integer>> res = new ArrayList<>();  
          if(numRows < 1) {  
              return res;  
          }  
          List<Integer> pre = new ArrayList<>();  
          pre.add(1);  
          res.add(pre);  
          // 遍历 numRows-1 次循环  
          for(int i = 1; i < numRows; i++) {  
              List<Integer> next = new ArrayList<>();  
              int size = pre.size();  
              next.add(1);  
              for(int j = 0; j < i - 1; j++) {  
                  next.add(pre.get(j) + pre.get(j+1));  
              }  
              next.add(1);  
              res.add(next);  
              pre = next;  
          }  
          return res;  
      }  
  }  
    
    
```    
    
### 个人解读    
  杨辉三角  
  因为是返回全部的，所以从头到尾开始遍历就完事了。  
    
tags:    
  -  遍历  
