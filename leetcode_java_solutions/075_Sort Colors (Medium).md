### description    
  Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.  
    
  Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.  
    
  Note: You are not suppose to use the library's sort function for this problem.  
    
  Example:  
    
  Input: [2,0,2,1,1,0]  
  Output: [0,0,1,1,2,2]  
  Follow up:  
    
  A rather straight forward solution is a two-pass algorithm using counting sort.  
  First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.  
  Could you come up with a one-pass algorithm using only constant space?  
    
  荷兰国旗问题，按颜色进行排序，题目描述：只有 0/1/2 三种颜色。  
### solution    
```    
  class Solution {  
      public void sortColors(int[] nums) {  
          int zero = -1;  
          int two = nums.length;  
          int one = 0;  
          while(one < two) {  
              if(nums[one] == 0) {  
                  swap(nums, ++zero, one++);  
              } else if(nums[one] == 2) {  
                  swap(nums, --two, one);  
              } else {  
                  one++;  
              }  
          }  
      }  
    
      private void swap(int[] nums, int i, int j) {  
          int t = nums[i];  
          nums[i] = nums[j];  
          nums[j] = t;  
      }  
  }  
```    
    
### 个人解读    
  想了想，用双指针并不能很好的解决问题。  
  三切分问题，只使用三个指针。  
  0,1,2， 循环条件是while(one < two)，  
  0和2的指针在界外，每次0和2必须先运算，后取值++0和--2.  
  if(nums[one]==0)，1和0交换时候，要么原地，要么传过来的是1，所以此时one有++。  
  if(nums[one]==2)时，2和0交换，从2交换过来的未知，所以one没有++。  
  ==1时，直接one++。  
    
  应该算是一类成熟的标准写法了。  
    
tags:    
  -  三切分问题  
