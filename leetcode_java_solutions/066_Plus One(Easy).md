### description    
  Given a non-empty array of digits representing a non-negative integer, plus one to the integer.  
    
  The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.  
    
  You may assume the integer does not contain any leading zero, except the number 0 itself.  
    
  Example 1:  
    
  Input: [1,2,3]  
  Output: [1,2,4]  
  Explanation: The array represents the integer 123.  
  Example 2:  
    
  Input: [4,3,2,1]  
  Output: [4,3,2,2]  
  Explanation: The array represents the integer 4321.  
### solution    
```    
  class Solution {  
      public int[] plusOne(int[] digits) {  
                int carry = 0;  
          int sum = 0;  
          for(int i = digits.length - 1; i >= 0; i--) {  
              sum = digits[i] + carry;  
              if(i == digits.length - 1) sum += 1;  
    
              carry = sum / 10;  
              digits[i] = sum % 10;  
          }  
          if(carry == 1) {  
              int[] res = new int[digits.length + 1];  
              res[0] = 1;  
              System.arraycopy(digits, 0, res, 1, digits.length);  
              return res;  
          }  
          return digits;    
      }  
  }  
```    
    
### 个人解读    
  标准的加法运算，只需要加一个变量int carry即可。  
  没啥好说 的，注意一下数组复制的方法即可。  
    
tags:    
  -  数组  
  -  求和  
