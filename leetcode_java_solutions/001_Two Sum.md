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