### description    
  Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.  
    
  Example:  
    
  Input:  [1,2,1,3,2,5]  
  Output: [3,5]  
  Note:  
    
  The order of the result is not important. So in the above example, [5, 3] is also correct.  
  Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?  
### solution    
```    
  public int[] singleNumber(int[] nums) {  
      int diff = 0;  
      for (int num : nums) diff ^= num;  
      diff &= -diff;  // 得到最右一位  关键点  
      int[] ret = new int[2];  
      for (int num : nums) {  
          if ((num & diff) == 0) ret[0] ^= num;  
          else ret[1] ^= num;  
      }  
      return ret;  
  }  
```    
    
### 个人解读    
  这种题目，有两类算法，第一类是通用算法，无论你是2个不重复还是n个不重复，都能算出来，但是这种往往开销会更大一些；第二类就是只能应用这一种情况(比如只有一个不重复，可以全部^计算)。  
  如果是通用算法的话，那么就是创建一个中间存储过程，然后把每次循环的结果存进去。  
    
  看过答案：想办法把数字分为两部分，一部分"^"得出来第一个答案，然后另一部分再"^"得到另一个答案（二切问题）。  
    
    
tags:    
  -  位运算  
  -  二切问题  
