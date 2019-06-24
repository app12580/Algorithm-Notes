### description    
  Given two arrays, write a function to compute their intersection.  
    
  Example 1:  
    
  Input: nums1 = [1,2,2,1], nums2 = [2,2]  
  Output: [2,2]  
  Example 2:  
    
  Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]  
  Output: [4,9]  
  Note:  
    
  Each element in the result should appear as many times as it shows in both arrays.  
  The result can be in any order.  
  Follow up:  
    
  What if the given array is already sorted? How would you optimize your algorithm?  
  What if nums1's size is small compared to nums2's size? Which algorithm is better?  
  What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?  
### solution    
```    
  class Solution {  
      public int[] intersect(int[] nums1, int[] nums2) {  
          List<Integer> list = new ArrayList<>();  
          Map<Integer, Integer> map = new HashMap<>();  
          for(int num: nums1) {  
              map.put(num, map.getOrDefault(num, 0) + 1);  
          }  
          for(int num: nums2) {  
              if(map.containsKey(num) ) {  
                  list.add(num);  
                  Integer count = map.get(num);  
                  if(count == 1) {  
                      map.remove(num);  
                  } else {  
                      map.put(num, count - 1);  
                  }  
              }  
          }  
            
          int[] res = new int[list.size()];  
          int i = 0;  
          for(int num: list) {  
              res[i++] = num;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  注意结果可以重复的。  
    思路一： 预处理排序，想了想效率不高。  
    思路二： 搞个Map作为中间存储结果。  
    
tags:    
  -  Hash表  
