### description    
  Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.  
    
  Example:  
    
  Input: [0,1,0,3,12]  
  Output: [1,3,12,0,0]  
  Note:  
    
  You must do this in-place without making a copy of the array.  
  Minimize the total number of operations.  
### solution    
```    
    class Solution {
        public void moveZeroes(int[] nums) {
            int inx = 0;
            for(int num: nums) {
                if(num != 0) {
                    nums[inx++] = num;
                }
            }
            for(int i = inx; i < nums.length; i++) {
                nums[i] = 0;
            }
        }
    }
```    
    
### 个人解读    
+ 思路一：双指针问题，一个为0，一个为非0  
+ 思路二：特殊比较器的数组排序问题  
+ 思路三：因为0是特殊变量，所以直接填充前面元素，最后再填写0  
    
tags:    
  -     
