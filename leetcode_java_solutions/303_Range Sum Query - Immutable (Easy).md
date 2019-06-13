### description    
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.  
  
Example:  
Given nums = [-2, 0, 3, -5, 2, -1]  
  
sumRange(0, 2) -> 1  
sumRange(2, 5) -> -1  
sumRange(0, 5) -> -3  
Note:  
You may assume that the array does not change.  
There are many calls to sumRange function.  
  
  数组区间和  
    
    
### solution    
```    
  class NumArray {  
    
      private int[] sums;  
    
      public NumArray(int[] nums) {  
          sums = new int[nums.length];  
          for(int i = 0; i < nums.length; i++) {  
              if(i == 0) {  
                  sums[0] = nums[0];  
              } else {  
                  sums[i] = sums[i - 1] + nums[i];  
              }  
          }  
      }  
    
      public int sumRange(int i, int j) {  
          if(i == 0) {  
              return sums[j];  
          } else {  
              return sums[j] - sums[i - 1];  
          }  
      }  
  }  
    
  /**  
   * Your NumArray object will be instantiated and called as such:  
   * NumArray obj = new NumArray(nums);  
   * int param_1 = obj.sumRange(i,j);  
   */  
```    
    
### 个人解读    
  因为会多次调用，所以需要存储变量。n^2的不应考虑，所以需要考虑O(n)的结果。  
  O(n)的话，肯定是一个数组，里面存储着和。  
  无所谓length是n还是n+1。  
    
tags:    
  -  数组  
  -  模拟  
