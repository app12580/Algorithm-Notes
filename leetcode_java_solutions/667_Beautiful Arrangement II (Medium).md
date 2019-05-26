### description    
  Given two integers n and k, you need to construct a list which contains n different positive integers ranging from 1 to n and obeys the following requirement:   
  Suppose this list is [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.  
    
  If there are multiple answers, print any of them.  
    
  Example 1:  
  Input: n = 3, k = 1  
  Output: [1, 2, 3]  
  Explanation: The [1, 2, 3] has three different positive integers ranging from 1 to 3, and the [1, 1] has exactly 1 distinct integer: 1.  
  Example 2:  
  Input: n = 3, k = 2  
  Output: [1, 3, 2]  
  Explanation: The [1, 3, 2] has three different positive integers ranging from 1 to 3, and the [2, 1] has exactly 2 distinct integers: 1 and 2.  
    
  题目描述：输入两个参数n和k，输出一个数组，元素为1,2,3...n，并且满足条件：所有相邻的差值的绝对值放在一块，不重复的个数必须等于k。  
### solution    
```    
  class Solution {  
       public int[] constructArray(int n, int k) {  
          int[] res = new int[n];  
          for(int i = 0; i < k + 1; i++) {  
  //            1, k+1, 2, k, 3  
              if(i % 2 == 0) {  
                  res[i] = i / 2 + 1;  
              } else {  
                  res[i] = k + 1 - i/2;  
              }  
          }  
          // 0 1  ... k    k+1  
          //          k+1  k+2  
          for(int i = k + 2; i <= n; i++) {  
              res[i - 1] = i;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  最好的办法，就是先弄出来一个满足k的最小的自然数长度，然后剩下的以步长为1一直走下去  
  先举个例子手工试一试  
  n = 6, k = 3  
  1 4 2 3 5 6  
  n = 8, k = 4  
  1 5 2 4 3 6 7 8  
    
  让前 k+1 个元素构建出 k 个不相同的差值，序列为：1 k+1 2 k 3 k-1 ... k/2 k/2+1.  
    
  这种题目主要还是找规律，找到规律答案就出来了  
    
tags:    
  -     
