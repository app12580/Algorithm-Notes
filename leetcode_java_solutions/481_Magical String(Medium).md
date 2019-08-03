### description    
  A magical string S consists of only '1' and '2' and obeys the following rules:  
    
  The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string S itself.  
    
  The first few elements of string S is the following: S = "1221121221221121122……"  
    
  If we group the consecutive '1's and '2's in S, it will be:  
    
  1 22 11 2 1 22 1 22 11 2 11 22 ......  
    
  and the occurrences of '1's or '2's in each group are:  
    
  1 2	2 1 1 2 1 2 2 1 2 2 ......  
    
  You can see that the occurrence sequence above is the S itself.  
    
  Given an integer N as input, return the number of '1's in the first N number in the magical string S.  
    
  Note: N will not exceed 100,000.  
    
  Example 1:  
  Input: 6  
  Output: 3  
  Explanation: The first 6 elements of magical string S is "12211" and it contains three 1's, so return 3.  
    
    
### solution    
```    
//方法一： 流水账 竟然速度最快  
Runtime: 2 ms, faster than 100.00% of Java online submissions for Magical String.  
Memory Usage: 35.3 MB, less than 15.38% of Java online submissions for Magical String.  
  
  class Solution {  
      public int magicalString(int n) {  
          //        1221121221221121122  
          if(n == 0) return 0;  
           if(n <= 3) return 1;  
          int fast = 2;  
          int slow = 1;  
          int sum = 0;  
          int[] nums = new int[n];  
          nums[0] = 1;  
          nums[1] = 2;  
          nums[2] = 2;  
          // 先判断当前slow，然后再移动fast  
          while(fast < n - 1) {  
              int count = nums[++slow];  
              int cur = nums[fast];  
              if(count == 1) {  
                  nums[++fast] = 3 - cur;  
              } else {  
                  nums[++fast] = 3 - cur;  
                  if(fast < n - 1) nums[++fast] = 3 - cur;  
              }  
          }  
          for(int num: nums) {  
              sum += num;  
          }  
    
          return 2 * n - sum;  
      }  
  }  
```    
    
### 个人解读    
本题目还少了一点要求：神奇字符串必须要以1开头  
  
如果有数学方法，可以直接简单计算出来结果最好了，但没发现。  
不管怎么样，先流水账一下看看效率怎样。  
需要使用双指针方法。  
  
为了避免麻烦，不妨假设slow会远远落后于fast，所以初始位置设置的远一些，避免需要判断是在当前2还是后面加2的窘态。  
    
tags:    
  -  细节的极值  
  -  双指针  
