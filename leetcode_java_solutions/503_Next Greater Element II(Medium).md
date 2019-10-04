### description    
  Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.  
    
  Example 1:  
  Input: [1,2,1]  
  Output: [2,-1,2]  
  Explanation: The first 1's next greater number is 2;   
  The number 2 can't find next greater number;   
  The second 1's next greater number needs to search circularly, which is also 2.  
  Note: The length of given array won't exceed 10000.  
### solution    
```    
  class Solution {  
      public int[] nextGreaterElements(int[] nums) {  
          int len = nums.length;  
          int[] res = new int[len];  
          Arrays.fill(res, -1);  
          Stack<Integer> stack = new Stack<>();  //里面存储着索引 //如果只存储值则无法回溯索引了  
          for(int i = 0; i < len * 2; i++) {  
              int val = nums[i % len];  
              while(!stack.isEmpty() && val > nums[stack.peek()]) {  
                  Integer pop = stack.pop();  
                  res[pop] = val;  
              }  
              if(i < len) {  
                  stack.push(i);  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  比起与非循环数组类似[739](739_Daily%20Temperatures(Medium).md)，难点主要集中在循环数组上。  
  想到的思路一：两次循环(或者数组重复两次拼接起来)。  
  把数组拼接后，需要区分入栈的条件，第二次循环的变量不应该入栈。  
    
tags:    
  -  栈  
  -  单调栈  
  -  循环数组  
