### description  
  Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
  
  The update(i, val) function modifies nums by updating the element at index i to val.
  
  Example:
  
  Given nums = [1, 3, 5]
  
  sumRange(0, 2) -> 9
  update(1, 2)
  sumRange(0, 2) -> 8
  Note:
  
  The array is only modifiable by the update function.
  You may assume the number of calls to update and sumRange function is distributed evenly.
### solution  
```  
  class NumArray {
  
      private int[] arr;
      
      public NumArray(int[] nums) {
          int len = nums.length;
          int sum = 0;
          arr = new int[len + 1];
          for(int i = 0; i < len; i++) {
              sum += nums[i];
              arr[i + 1] = sum;
          }
      }
  
      public void update(int i, int val) {
          int pre = arr[i + 1] - arr[i];
          int sub = val - pre;
          for(int k = i; k < arr.length - 1; k++) {
              arr[k+1] += sub;
          }
      }
  
      public int sumRange(int i, int j) {
          // [1,2,3] 1,2 arr[3] - arr[1]
          // [0,1,3,6]
          return arr[j + 1] - arr[i];
      }
  }
  
  /**
   * Your NumArray object will be instantiated and called as such:
   * NumArray obj = new NumArray(nums);
   * obj.update(i,val);
   * int param_2 = obj.sumRange(i,j);
   */
```  
  
### 个人解读  
  需要创建一个数组，然后每次做差值。
  
tags:  
  -  模拟
