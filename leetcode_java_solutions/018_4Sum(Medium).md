### description    
  Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.  
    
  Note:  
    
  The solution set must not contain duplicate quadruplets.  
    
  Example:  
    
  Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.  
    
  A solution set is:  
  [  
    [-1,  0, 0, 1],  
    [-2, -1, 1, 2],  
    [-2,  0, 0, 2]  
  ]  
### solution    
```    
  class Solution {  
      public List<List<Integer>> fourSum(int[] nums, int target) {  
          ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();  
          int len = nums.length;  
          if (nums == null || len < 4)  
              return res;  
    
          Arrays.sort(nums);  
    
          int     
 = nums[len - 1];  
          if (4 * nums[0] > target || 4 *     
 < target)  
              return res;  
    
          int i, z;  
          for (i = 0; i < len; i++) {  
              z = nums[i];  
              if (i > 0 && z == nums[i - 1])// avoid duplicate  
                  continue;  
              if (z + 3 *     
 < target) // z is too small  
                  continue;  
              if (4 * z > target) // z is too large  
                  break;  
              if (4 * z == target) { // z is the boundary     // 通过这个用来回避四重复  
                  if (i + 3 < len && nums[i + 3] == z)  
                      res.add(Arrays.asList(z, z, z, z));  
                  break;      //为什么要break:因为当前4z是最小值  
              }  
    
              threeSumForFourSum(nums, target - z, i + 1, len - 1, res, z);  
          }  
    
          return res;  
      }  
    
      public void threeSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,  
                                     int z1) {  
          if (low + 1 >= high)  
              return;  
    
          int     
 = nums[high];  
          if (3 * nums[low] > target || 3 *     
 < target)  
              return;  
    
          int i, z;  
          for (i = low; i < high - 1; i++) {  
              z = nums[i];  
              if (i > low && z == nums[i - 1]) // avoid duplicate  
                  continue;  
              if (z + 2 *     
 < target) // z is too small  
                  continue;  
    
              if (3 * z > target) // z is too large  
                  break;  
    
              if (3 * z == target) { // z is the boundary  
                  if (i + 1 < high && nums[i + 2] == z)  
                      fourSumList.add(Arrays.asList(z1, z, z, z));  
                  break;  
              }  
    
              twoSumForFourSum(nums, target - z, i + 1, high, fourSumList, z1, z);  
          }  
    
      }  
    
      public void twoSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,  
                                   int z1, int z2) {  
    
          if (low >= high)  
              return;  
    
          if (2 * nums[low] > target || 2 * nums[high] < target)  
              return;  
    
          int i = low, j = high, sum, x;  
          while (i < j) {  
              sum = nums[i] + nums[j];  
              if (sum == target) {  
                  fourSumList.add(Arrays.asList(z1, z2, nums[i], nums[j]));  
    
                  x = nums[i];  
                  while (++i < j && x == nums[i]) // avoid duplicate  
                      ;  
                  x = nums[j];  
                  while (i < --j && x == nums[j]) // avoid duplicate  
                      ;  
              }  
              if (sum < target)  
                  i++;  
              if (sum > target)  
                  j--;  
          }  
          return;  
      }  
  }  
```    
    
### 个人解读    
  4Sum，第一思路是直接复用3Sum的代码，但是仍然有问题。  
  如何优雅的控制数字不重复。  
  分为2重复、3重复、4重复，不光从逻辑顺序上分析，还可以从分析结果上考虑，因为4重复的时候，属于极值，可以单独考虑。  
    
  回避法总结：  
  1、分两个地方回避：  
   ```  
     进入循环的，只计算重复数字的第一个  
     if (i > 0 && z == nums[i - 1])// avoid duplicate  
                     continue;  
       
     进去循环以后，                  
      x = nums[i];  
      while (++i < j && x == nums[i]); //  
      x = nums[j];  
      while (i < --j && x == nums[j]); // i变成了最后一个值的下一个索引  
   ```  
    
tags:    
  -  数组  
  -  回避重复  
