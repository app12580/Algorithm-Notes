### description
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.

Note:

Your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution and you may not use the same element twice.
Example:

```
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
```

### solution
```
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while(i < j) {
            int sum = numbers[i] + numbers[j];
            if(sum < target) {
                i++;
            } else if(sum > target) {
                j--;
            } else {
                return new int[]{i + 1, j + 1};
            }
        }
        return null;
    }
}
```

### 个人解读
参见[001](../001_Two%20Sum.md)

tags:
  - 数组
  - 双指针
