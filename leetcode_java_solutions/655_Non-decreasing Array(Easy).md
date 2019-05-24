### description
Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

Example 1:
Input: [4,2,3]
Output: True
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
Example 2:
Input: [4,2,1]
Output: False
Explanation: You can't get a non-decreasing array by modify at most one element.
### solution
```
class Solution {
 public boolean checkPossibility(int[] nums) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] < nums[i - 1]) {
                return helper(nums, i) || helper(nums, i - 1);
            }
        }
        return true;
    }

    private boolean helper(int[] nums, int ignore) {
        if(ignore == nums.length - 1) {
            return true;
        }
        if(ignore != 0 && nums[ignore - 1] > nums[ignore + 1]){     //开始时候少了"ignore != 0"
            return false;
        }
        for(int i = ignore + 2; i < nums.length; i++) {
            if(nums[i] < nums[i - 1]) {
                return false;
            }
        }
        return true;
    }

}
```

```
其他解法
class Solution {
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                count++;
                if (count >= 2) return false;
                if (i < 2 || nums[i -2] < nums[i]) {                   
                    nums[i - 1] = nums[i];      //132形式，此时需要改成122
                } else {
                    nums[i] = nums[i-1];        //优先的操作
                }
            }
        }
        return true;
    }
}

class Solution {
    public boolean checkPossibility(int[] nums) {
        if (nums.length <= 1) return true; 
        int count = 0;
        
        for(int i=0; i<nums.length-1; ++i) {
            if(nums[i] > nums[i+1]) {
                count += 1;
                if(i > 0 && nums[i+1] < nums[i-1]) nums[i+1] = nums[i];
                else nums[i] = nums[i+1];
            }   
        }
        return count <= 1;
    }
}

```

### 个人解读
从头开始遍历，当出现arr[i] < arr[i - 1]的时候，就需要做处理
此时要么处理arr[i]，要么处理arr[i-1]，
+ 我的想法： 直接把两种情况都判断一下，然后"||"一下
+ Cyc做法： 通过分析情景，分析出来要处理哪一个

tags:
  - 数组
  - 双指针
