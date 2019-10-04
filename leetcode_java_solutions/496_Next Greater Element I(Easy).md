### description    
  You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.  
    
  The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.  
    
  Example 1:  
  Input: nums1 = [4,1,2], nums2 = [1,3,4,2].  
  Output: [-1,3,-1]  
  Explanation:  
      For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.  
      For number 1 in the first array, the next greater number for it in the second array is 3.  
      For number 2 in the first array, there is no next greater number for it in the second array, so output -1.  
  Example 2:  
  Input: nums1 = [2,4], nums2 = [1,2,3,4].  
  Output: [3,-1]  
  Explanation:  
      For number 2 in the first array, the next greater number for it in the second array is 3.  
      For number 4 in the first array, there is no next greater number for it in the second array, so output -1.  
  Note:  
  All elements in nums1 and nums2 are unique.  
  The length of both nums1 and nums2 would not exceed 1000.  
### solution    
```    
  class Solution {  
      public int[] nextGreaterElement(int[] nums1, int[] nums2) {  
           Map<Integer, Integer> map = new HashMap<>();  
          Stack<Integer> stack = new Stack<>();  
          for(int i = 0; i < nums2.length; i++) {  
              //出栈条件  
              int num = nums2[i];  
              while(!stack.isEmpty() && num > stack.peek()) {  
                  Integer pop = stack.pop();  
                  map.put(pop, num);  
              }  
              stack.push(num);  
          }  
          int[] res = new int[nums1.length];  
          for(int i = 0; i < res.length; i++) {  
              int num = nums1[i];  
              if(map.containsKey(num)) {  
                  res[i] = map.get(num);  
              } else {  
                  res[i] = -1;  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  看样子是单调栈法。  
  先遍历nums2，把每个数字的结果求出来，存进一个Map里面。  
  这种题目，如果不经过训练，可能很难，但稍微训练一下，其实本质和套公式没区别。  
    
    
tags:    
  -  单调栈法  
