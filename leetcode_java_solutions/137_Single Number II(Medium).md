### description    
  Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.  
    
  Note:  
    
  Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?  
    
  Example 1:  
    
  Input: [2,2,3,2]  
  Output: 3  
  Example 2:  
    
  Input: [0,1,0,1,0,1,99]  
  Output: 99  
### solution    
```    
// 方法一： 每次统计某一位上是否有。  
  class Solution {  
      public int singleNumber(int[] nums) {  
      int ans = 0;  
      for(int i = 0; i < 32; i++) {  
          int sum = 0;  
          for(int j = 0; j < nums.length; j++) {  
              if(((nums[j] >> i) & 1) == 1) {  
                  sum++;  
                  sum %= 3;  
              }  
          }  
          if(sum != 0) {  
              ans |= sum << i;  
          }  
      }  
      return ans;  
  }  
  }  
    
  // 方法二： 位运算  
  public int singleNumber(int[] A) {  
      int ones = 0;//记录只出现过1次或3次的bits  
      int twos = 0;//记录只出现过2次的bits  
      int threes;  
      for(int i = 0; i < A.length; i++){  
          int t = A[i];  
          twos |= ones&t;// 出现2次，或者出现三次  
          ones ^= t;  //出现1次， 或者出现3次  
          threes = ones&twos;//ones和twos中都为1即出现了3次  
          ones &= ~threes;//抹去出现了3次的bits  
          twos &= ~threes;  
      }  
      return ones;      //最后剩下一个只出现一次的  
  }  
```    
    
### 个人解读    
  两种思路：  
  思路一： 一位一位的去统计，因为直接用HashMap长度和元素不确定，而int的32位是可以确定的  
  思路二： 类似于异或的操作    
    
tags:    
  -  重点数学  
  -  位运算  
  
  
  
