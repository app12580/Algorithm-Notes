### description    
  Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.  
    
  Example 1:  
  Input: [0,1]  
  Output: 2  
  Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.  
  Example 2:  
  Input: [0,1,0]  
  Output: 2  
  Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.  
  Note: The length of the given binary array will not exceed 50,000.  
### solution    
```    
  class Solution {  
        public int findMaxLength(int[] nums) {  
          for (int i = 0; i < nums.length; i++) {  
              if (nums[i] == 0) nums[i] = -1;  
          }  
    
          Map<Integer, Integer> sumToIndex = new HashMap<>();  
          sumToIndex.put(0, -1);  
          int sum = 0, max = 0;  
    
          for (int i = 0; i < nums.length; i++) {  
              sum += nums[i];  
              if (sumToIndex.containsKey(sum)) {  
                  max = Math.max(max, i - sumToIndex.get(sum));  
              }  
              else {  
                  sumToIndex.put(sum, i);       //每种求和的最小索引位置  
              }  
          }  
    
          return max;  
      }  
  }  
```    
    
### 个人解读    
  curLen问题，但是问题在于curLen的限定条件是有跨度的，搞不好要用到动态规划一类的东西去解决。  
    
  curlen很难解决，因此需要用其他的办法。  
  1、区间求和问题，可以用[0,j]-[0,i]剩下的就是[i,j]了  
  2、本题的特殊点，只有01两个数字，可以另0为-1，这样当出现了同样和的时候，就是二者数量相等的情况。  
    
tags:    
  -  curLen问题  
  -  数学模型  
  -  重点数学  
  -  差值法9  
