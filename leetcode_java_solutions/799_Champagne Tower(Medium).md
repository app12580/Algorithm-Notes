### description    
  We stack glasses in a pyramid, where the first row has 1 glass, the second row has 2 glasses, and so on until the 100th row.  Each glass holds one cup (250ml) of champagne.  
    
  Then, some champagne is poured in the first glass at the top.  When the top most glass is full, any excess liquid poured will fall equally to the glass immediately to the left and right of it.  When those glasses become full, any excess champagne will fall equally to the left and right of those glasses, and so on.  (A glass at the bottom row has it's excess champagne fall on the floor.)  
    
  For example, after one cup of champagne is poured, the top most glass is full.  After two cups of champagne are poured, the two glasses on the second row are half full.  After three cups of champagne are poured, those two cups become full - there are 3 full glasses total now.  After four cups of champagne are poured, the third row has the middle glass half full, and the two outside glasses are a quarter full, as pictured below.  
    
    
    
  Now after pouring some non-negative integer cups of champagne, return how full the j-th glass in the i-th row is (both i and j are 0 indexed.)  
    
     
    
  Example 1:  
  Input: poured = 1, query_glass = 1, query_row = 1  
  Output: 0.0  
  Explanation: We poured 1 cup of champange to the top glass of the tower (which is indexed as (0, 0)). There will be no excess liquid so all the glasses under the top glass will remain empty.  
    
  Example 2:  
  Input: poured = 2, query_glass = 1, query_row = 1  
  Output: 0.5  
  Explanation: We poured 2 cups of champange to the top glass of the tower (which is indexed as (0, 0)). There is one cup of excess liquid. The glass indexed as (1, 0) and the glass indexed as (1, 1) will share the excess liquid equally, and each will get half cup of champange.  
     
    
  Note:  
    
  poured will be in the range of [0, 10 ^ 9].  
  query_glass and query_row will be in the range of [0, 99].  
### solution    
```    
  class Solution {  
     public double champagneTower(int poured, int query_row, int query_glass) {  
          double[] res = new double[query_row + 2]; //只用一行代替多行数据，因为需要计算最后一个杯子的扩散，所以比正常要多1  
          //需要注意：query_row为i行索引，此时行索引是query_row，最后一列索引是query_row，长度是query_row+1  
          res[0] = poured;  
          //坐标都是从0开始的  
          for (int row = 1; row <= query_row; row++)  //row=1表示从第二行开始遍历  
              for (int i = row; i >= 0; i--) {  
                  //第row的话，最后一个杯子的坐标也是row  
                  //判断第i个杯子会不会漫出去， 每次循环计算时候  
                  res[i] = Math.max(0.0, (res[i] - 1) / 2);  
                  res[i + 1] += res[i];  这个一半一半是重要规律  
              }  
          return Math.min(res[query_glass], 1.0);  
      }  
  }  
```    
    
### 个人解读    
  很难从数学角度分析每次的进度和获取能力，所以觉得必须要一瓶一瓶的去倒，同时记录数据。  
    
  第一印象是需要两个数据结构，一个存当前获取能力，另一个存储已经获得了多少。  
    
tags:    
  -  重点数学  
