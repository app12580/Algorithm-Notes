### description    
  Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.  
    
  Note:  
  The array size can be very large. Solution that uses too much extra space will not pass the judge.  
    
  Example:  
    
  int[] nums = new int[] {1,2,3,3,3};  
  Solution solution = new Solution(nums);  
    
  // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.  
  solution.pick(3);  
    
  // pick(1) should return 0. Since in the array only nums[0] is equal to 1.  
  solution.pick(1);  
### solution    
```    
  
  
Runtime: 101 ms, faster than 60.70% of Java online submissions for Random Pick Index.  
Memory Usage: 54.3 MB, less than 51.20% of Java online submissions for Random Pick Index.  
  
  
  class Solution {  
    
      private int[] arr;  
      private Random random = new Random();  
      public Solution(int[] nums) {  
          arr = nums;  
      }  
    
      public int pick(int target) {  
          List<Integer> list = new ArrayList<>();  
          for(int i = 0; i < arr.length; i++) {  
              if(arr[i] == target) {  
                  list.add(i);  
              }  
          }  
          return list.get(random.nextInt(list.size()));  
      }  
        
  }  
    
  /**  
   * Your Solution object will be instantiated and called as such:  
   * Solution obj = new Solution(nums);  
   * int param_1 = obj.pick(target);  
   */  
```    
    
### 个人解读    
  获取每次获取最大索引和最小索引，然后返回区间中的随机数。  
  题目要求不让用多余空间，所以每次只能当场查询。  
    
  一开始没注意数组未排序。但是未排序的难道让我O(N)去查询么。  
    
  黑人问号？？这题目真就On遍历了啊，那这道题目图个啥啊？  
    
tags:    
  -  模拟  
