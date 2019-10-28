### description    
  Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].  
    
  You need to return the number of important reverse pairs in the given array.  
    
  Example1:  
    
  Input: [1,3,2,3,1]  
  Output: 2  
  Example2:  
    
  Input: [2,4,3,5,1]  
  Output: 3  
  Note:  
  The length of the given array will not exceed 50,000.  
  All the numbers in the input array are in the range of 32-bit integer.  
### solution    
```    
  
//方法一： 归并排序  
Runtime: 52 ms, faster than 54.42% of Java online submissions for Reverse Pairs.  
Memory Usage: 50.8 MB, less than 100.00% of Java online submissions for Reverse Pairs.  
  
  class Solution {  
      public int reversePairs(int[] nums) {  
          if(nums == null || nums.length == 0) return 0;  
          return mergeSort(nums, 0, nums.length - 1);  
      }  
    
        private int mergeSort(int[] nums, int l, int h) {  
          if(l >= h) return 0;  
          int m = l + (h - l) / 2;  
          int left = mergeSort(nums, l, m);  
          int right = mergeSort(nums, m + 1, h);  
          int[] temp = new int[h - l + 1];  
          int i = l, j = m + 1, index = 0, doubleLeft = l;  
          int count = 0;  
          while(i <= m && j <= h) {  
              if(nums[i] <= nums[j]) {  
                  temp[index] = nums[i];  
                  index++;  
                  i++;  
              } else {  
                  //左边的大  
                  temp[index] = nums[j];  
                  //这时候要弹出右边的，所以还需要一个变量去告知需要加上的数量  
                  while(doubleLeft <= m && nums[doubleLeft] <= (long)2 * nums[j]) {  
                      doubleLeft++;  
                  }  
                  count += m + 1 - doubleLeft;  
                  index++;  
                  j++;  
              }  
          }  
          while(i <= m) {  
              temp[index++] = nums[i++];  
          }  
          while(j <= h) {  
              while(doubleLeft <= m && nums[doubleLeft] <= (long)2 * nums[j]) {  
                  doubleLeft++;  
              }  
              count += m + 1 - doubleLeft;  
              temp[index++] = nums[j++];  
          }  
          System.arraycopy(temp, 0, nums, l, h - l + 1);  
          return count + left + right;  
      }  
    
    
  }  
```    
    
### 个人解读    
  逆序对个数的进阶题目。  
  
  思路一：归并排序  
  感觉也得先去翻翻模板。  
  关键点：  
  1、每次弹出数据的时候，加上的数据不一样  
  2、负数的影响[-5,-5]这种的输出也是1  
      刚开始以为需要特殊处理归并条件，后来发现是在后续的while里面补充即可  
  3、int超限  
    
tags:    
  -  归并排序  
