### description  
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.  
  
Example:  
  
Input: [-2,1,-3,4,-1,2,1,-5,4],  
Output: 6  
Explanation: [4,-1,2,1] has the largest sum = 6.  
Follow up:  
  
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.  
  
进阶:  
  
如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。  
### solution  
```  
class Solution {  
    public int maxSubArray(int[] nums) {  
      int maxValue = nums[0];  
        int preSum = 0;  
        for(int i = 0; i < nums.length; i++) {  
            int t = preSum + nums[i];  
            if(nums[i] >= 0 ) {  
                maxValue = Math.max(maxValue, t);  
                preSum += nums[i];  
            } else {  
                preSum = Math.max(0, t);  
                maxValue = Math.max(maxValue, nums[i]);  
            }  
        }  
        return maxValue;  
  
    }  
}  
```  
  
### 个人解读  
无脑DP：fn为含有n的最大值，gn为不含有n的最大值  
从左往右遍历，会有arr[i],问题是要怎么用  
猜测可能有的中间存储变量：   
+ 肯定要有的maxValue  
+ 延续的前面的和t， 关键点，if arr[i] >= 0, t += arr[i]，然后和maxValue比较  
+ if arr[i] < 0， t = Max(t += arr[i],0)  
  
注意： 需要考虑负数数组的情况  
tags:  
  - 数组  
  - 双指针  
