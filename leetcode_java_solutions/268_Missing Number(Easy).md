### description      
  Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.    
      
  Example 1:    
      
  Input: [3,0,1]    
  Output: 2    
  Example 2:    
      
  Input: [9,6,4,2,3,5,7,0,1]    
  Output: 8    
  Note:    
  Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?    
### solution      
```      
    class Solution {  
        public int missingNumber(int[] nums) {  
             int res = 0;  
            for(int i = 0; i < nums.length; i++) {  
                res = res ^ nums[i] ^ i;  
            }  
            return res ^ nums.length;  
        }  
    }  
```      
      
### 个人解读      
  
  第一反应，与[645](645_Set%20Mismatch%20(Easy).md)类似，在自然数组中找到缺失的那一个，那道题的解法是一边遍历，一边把遍历结果作为-1存进源数组去。  
    而本题可以进行优化，与[136](136_Single%20Number%20(Easy).md)联系起来，通过^操作。  
    反思这么想的理由：645那种，缺失了一个，重复了一个，有两个干扰项，而本题只有一个缺失，是一个干扰项，通过^取出数据后，容易处理出来结果。  
tags:      
  -  自然数组      
  -  位运算优化  
