### description    
  We have two special characters. The first character can be represented by one bit 0. The second character can be represented by two bits (10 or 11).  
    
  Now given a string represented by several bits. Return whether the last character must be a one-bit character or not. The given string will always end with a zero.  
    
  Example 1:  
  Input:   
  bits = [1, 0, 0]  
  Output: True  
  Explanation:   
  The only way to decode it is two-bit character and one-bit character. So the last character is one-bit character.  
  Example 2:  
  Input:   
  bits = [1, 1, 1, 0]  
  Output: False  
  Explanation:   
  The only way to decode it is two-bit character and two-bit character. So the last character is NOT one-bit character.  
  Note:  
    
  1 <= len(bits) <= 1000.  
  bits[i] is always 0 or 1.  
    
  输入由1和0组成的int[]，从左到右以某种方式进行转化，'0'可以转化成A，连续的两位'10'和'11'可以转化成B，问int[]的最后一个int是否一定要转化成A才能完全转化整个数组。  
    
  注： int[]的最后一位一定是0。  
    
### solution    
```    
  
//方法一 ： 双DP数组  
Runtime: 1 ms, faster than 12.49% of Java online submissions for 1-bit and 2-bit Characters.  
Memory Usage: 36.1 MB, less than 100.00% of Java online submissions for 1-bit and 2-bit Characters.  
  
  class Solution {  
      public boolean isOneBitCharacter(int[] bits) {  
          int len = bits.length;  
          boolean[] A = new boolean[len];  
          boolean[] B = new boolean[len];  
          A[0] = B[0] = true;  
          for(int i = 0; i < len - 1; i++) {  
              int bit = bits[i];  
              if(bit == 0) {  
                  A[i + 1] = A[i] || B[i];  
              }  
              if(i > 0 && bits[i - 1] == 1) {  
                  B[i + 1] = A[i - 1] || B[i - 1];  
              }  
          }  
          return A[len - 1] || B[len - 1];  
      }  
  }  
    
    
  // 方法二 贪心算法  
  class Solution {  
      public boolean isOneBitCharacter(int[] bits) {  
           int i=0;  
          int n = bits.length;  
          while (i<n-1){  
              if(bits[i]==0){i++;}  
              else i+=2;  
          }   
          return(i == n-1);  
      }  
  }  
    
```    
    
### 个人解读    
  第一时间没看懂题目在说啥，看了中文的也没明白在说啥，最后结合测试用例才明白题目要干啥。  
  为了最后一位要转化成A，所以只需要判断出了[0,len-1]能否转化。  
  考虑使用dp，但是因为A、B两种情况，内存消耗略高。  
  那就不用数组dp了，以单个int结尾。这样不行，状态转移方程跨度高，管理有风险。  
    
    
  //方法二 贪心算法  
  贪心算法就是要两种字符选择一个，能选则选，经过判断，能A则A。最后恰好在len-1处听了下来。  
    
    
tags:    
  -  数学  
  -  动态规划  
  -  贪心算法    
