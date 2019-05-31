### description    
  Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].  
    
  Example:  
    
  Input:  [1,2,3,4]  
  Output: [24,12,8,6]  
  Note: Please solve it without division and in O(n).  
    
  Follow up:  
  Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)  
  给定一个数组，创建一个新数组，新数组的每个元素为原始数组中除了该位置上的元素之外所有元素的乘积。  
    
  要求时间复杂度为 O(N)，并且不能使用除法。  
### solution    
```    
  class Solution {  
      public int[] productExceptSelf(int[] nums) {  
          int[] res = new int[nums.length];  
          Arrays.fill(res, 1);  
    
          int left = 1;  
          for(int i = 1; i <nums.length; i++) {  
              left *= nums[i - 1];  
              res[i] *= left;  
          }  
          int right = 1;  
          for(int i = nums.length - 2; i >= 0; i--) {  
              right *= nums[i + 1];  
              res[i] *= right;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  如果能用除法的话，就轻松很多了，全部乘起来再除以就完事了。  
  如果不能用除法的话，想实现目标只能用乘法。  
  如果用乘法的话，该位置上的结果就是他左边的乘积，再乘以右边的乘积。然后就会想到设立两个数组，一个存左边，一个存右边。  
  left[i] 表示从nums[0]*nums[1]...nums[i-1]  
  right[i] 表示从nums[i+1]....nums[len-2]*nums[len-1]  
  然后又需要想一个问题，这两个数组是必须的吗?能否只用两个int，就能代替两个int[]?  
  还是可以的呀，因为最后返回的是res[i] = left[i] * right[i]，类似于自然数组乘以-1放在原数组作为一个bool[]的功效，**中间存储数据得到即使用**，因此没有必要开辟新数组空间。  
    
  // 注意：关于int越界问题： 不管他，测试用例里面没有就当不存在鸟  
    
tags:    
  -  数组  
