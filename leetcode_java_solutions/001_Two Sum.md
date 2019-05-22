### descption
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

### solution
```
    public int[] twoSum(int[] nums, int target) {
        //key: 数组中的值 value:数组中的下标
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            int left = target - nums[i];
            if(map.containsKey(left)) {
                return new int[]{map.get(left), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    @Test
    public void test() {
        int[] arr = {2, 7 ,11 , 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(arr, target)));
    }

```

### 个人解读
常见两种方案：
+ 通过hash表作为遍历的历史记录，利用hash算法可以快速寻址
+ 先对数组进行排序，然后通过双游标，start = 0，end = len - 1，将start和end的和与target比较，如果比target大，则end--，比target小，则start++，如果相等，则return

可以用反证法来说明双游标做法的正确性：
```
需要证明这种方式不会错过正确答案：
假设正确答案为r1和r2(如果有多组答案，那么r1和r2可以为任何一组)
假设程序运行到任一时刻的游标为i和j
如果错过正确答案，那么i>r1或者j<r2，
又因为错过是一次循环的事情，所以在循环的那一次循环有i< r1 < r2 = j
此时根据计算计算逻辑是不会发生j--而错过答案的

```
tags:
  - 数组
  - 双指针
  - Hash表
