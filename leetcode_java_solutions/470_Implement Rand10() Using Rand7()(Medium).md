### description    
  Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.  
    
  Do NOT use system's Math.random().  
    
     
    
  Example 1:  
    
  Input: 1  
  Output: [7]  
  Example 2:  
    
  Input: 2  
  Output: [8,4]  
  Example 3:  
    
  Input: 3  
  Output: [8,1,10]  
     
    
  Note:  
    
  rand7 is predefined.  
  Each testcase has one argument: n, the number of times that rand10 is called.  
     
    
  Follow up:  
    
  What is the expected value for the number of calls to rand7() function?  
  Could you minimize the number of calls to rand7()?  
### solution    
```    
  
Runtime: 4 ms, faster than 99.12% of Java online submissions for Implement Rand10() Using Rand7().  
Memory Usage: 37.2 MB, less than 100.00% of Java online submissions for Implement Rand10() Using Rand7().  
  
  
  class Solution extends SolBase {  
      public int rand10() {  
          boolean isOdd = false;  
          int oddNum = 7;  
          while(oddNum == 7) {  
              oddNum = rand7();  
          }  
          isOdd = oddNum % 2 == 0;  
          int noNum = 6;  
          while(noNum > 5) {  
              noNum = rand7();  
          }  
          int start = isOdd ? 1 : 2;  
          return start + 2 * (noNum - 1);  
      }  
  }  
```    
    
### 个人解读    
  这题目主要是数学问题，要做到完全随机。rand7是分母为7，ran10是分母为10，总不能让rand7运行十次把？？？然而根据概率论，两个rand7相加并不是线性均匀平分的。  
  只能根据rand7的结果用来做乘法。  
  下面这个是做加法的结果  
  2  1  
  3  2  
  4  3  
  5  4  
  6  5  
  7  6  
  8  7  
  9  6  
  10 5  
  11 4  
  12 3  
  13 2  
  14 1  
    
  感觉如果直接作废某部分的结果，好像也是可以的。  
    
  最后解法只要一个关键词：作废。  
    
tags:    
  -  随机数  
