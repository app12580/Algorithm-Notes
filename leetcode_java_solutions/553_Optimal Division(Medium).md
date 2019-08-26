### description    
  Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 2 / 3 / 4.  
    
  However, you can add any number of parenthesis at any position to change the priority of operations. You should find out how to add parenthesis to get the maximum result, and return the corresponding expression in string format. Your expression should NOT contain redundant parenthesis.  
    
  Example:  
  Input: [1000,100,10,2]  
  Output: "1000/(100/10/2)"  
  Explanation:  
  1000/(100/10/2) = 1000/((100/10)/2) = 200  
  However, the bold parenthesis in "1000/((100/10)/2)" are redundant,   
  since they don't influence the operation priority. So you should return "1000/(100/10/2)".   
    
  Other cases:  
  1000/(100/10)/2 = 50  
  1000/(100/(10/2)) = 50  
  1000/100/10/2 = 0.5  
  1000/100/(10/2) = 2  
  Note:  
    
  The length of the input array is [1, 10].  
  Elements in the given array will be in range [2, 1000].  
  There is only one optimal division for each test case.  
### solution    
```    
Runtime: 0 ms, faster than 100.00% of Java online submissions for Optimal Division.  
Memory Usage: 34.5 MB, less than 100.00% of Java online submissions for Optimal Division.  
  
  
  class Solution {  
      public String optimalDivision(int[] nums) {  
          if(nums == null || nums.length == 0) return "";  
          StringBuilder builder = new StringBuilder();  
          builder.append(nums[0]);  
          for(int i = 1; i < nums.length; i++) {  
              builder.append('/');  
              if(i == 1 && nums.length > 2) builder.append('(');  
              builder.append(nums[i]);  
              if(i == nums.length - 1 && nums.length > 2) builder.append(')');  
          }  
          return builder.toString();  
      }  
  }  
```    
    
### 个人解读    
    
  猜测：  
  1、括号只需要一层就够了  
  2、类似于计算器，当确定完下一个数字时候，才把前一个数字进行处理。  
    
  然而阅读题目，发现是 positive integers，这样不管是什么都是要算在括号里面的，而不需要比较是乘法还是除法。  
    
tags:    
  -  不完整的题目  
