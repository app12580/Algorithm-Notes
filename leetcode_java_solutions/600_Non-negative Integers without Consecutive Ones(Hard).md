### description    
  Given a positive integer n, find the number of non-negative integers less than or equal to n, whose binary representations do NOT contain consecutive ones.  
    
  Example 1:  
  Input: 5  
  Output: 5  
  Explanation:   
  Here are the non-negative integers <= 5 with their corresponding binary representations:  
  0 : 0  
  1 : 1  
  2 : 10  
  3 : 11  
  4 : 100  
  5 : 101  
  Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.   
  Note: 1 <= n <= 109  
### solution    
```    
Runtime: 2 ms, faster than 37.69% of Java online submissions for Non-negative Integers without Consecutive Ones.
Memory Usage: 33.7 MB, less than 16.67% of Java online submissions for Non-negative Integers without Consecutive Ones.

    class Solution {
          public int findIntegers(int num) {
            StringBuilder sb = new StringBuilder(Integer.toBinaryString(num)).reverse();
            int n = sb.length();
    
            int a[] = new int[n]; //末尾0
            int b[] = new int[n]; //末尾1
            a[0] = b[0] = 1;
            for (int i = 1; i < n; i++) {
                a[i] = a[i - 1] + b[i - 1];
                b[i] = a[i - 1];
            }
    
            int result = a[n - 1] + b[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                // i+1 是更大的那一边的
                if (sb.charAt(i) == '1' && sb.charAt(i + 1) == '1') break;
                if (sb.charAt(i) == '0' && sb.charAt(i + 1) == '0') result -= b[i];
            }
    
            return result;
        }
    }
```    
    
### 个人解读    
  给这类问题起个名字吧，就叫N-th问题。  
  这题目问题是不能分治，很难递归啊。  
    
  如果动态规划，按照数位进行DP呢？  
    
  zero[n] 表示最后一位是0的数字 ，n表示范围是小于等于111....1111(n+1个1)  
  one[n]  表示最后一位是1的数字   
    
  zero[n] = one[n - 1] + zero[n - 1];  
  one[n] = zero[n - 1];  
    
  题目描述理解错了，不是去找第n个数字，而是要知道低于某个数字有多少个。  
    
  思路一：  
  1、先找出是 位数num  
    //通过前面的DP  
  2、找出num位数中的第k个  
     这里就可以考虑使用递归了  
     从大到小去找，找到了归属就知道  
    
  卡住了，算了，看答案把     
       
       
   总结：  
   整体思路  ：  
   1、获取当前n个1下，一共有多少种结果。  
   2、然后刨去小于等于n个1，大于num的数量  
   3、刨去逻辑：如果10或者是01序列，属于最大化的序列，不需要管  
     如果遇到00，说明需要管理  
     100xxxxx  
       ^  
      ji  (j = i + 1)   
     说明1xxxx需要刨去，也就是sum[i];    
       
   如果遇到11  
     1011xxxx    
       ji  
     说明，不会再出现比目前大的情况了  
       
         
tags:    
  -  N-th问题  
  -  重点数学  
  -  动态规划  
  -  数字逻辑  
