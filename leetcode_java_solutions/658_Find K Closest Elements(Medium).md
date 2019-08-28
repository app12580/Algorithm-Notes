### description    
  Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.  
    
  Example 1:  
  Input: [1,2,3,4,5], k=4, x=3  
  Output: [1,2,3,4]  
  Example 2:  
  Input: [1,2,3,4,5], k=4, x=-1  
  Output: [1,2,3,4]  
  Note:  
  The value k is positive and will always be smaller than the length of the sorted array.  
  Length of the given array is positive and will not exceed 104  
  Absolute value of elements in the array and x will not exceed 104  
  
### solution    
```    
Runtime: 11 ms, faster than 37.85% of Java online submissions for Find K Closest Elements.  
Memory Usage: 39 MB, less than 91.18% of Java online submissions for Find K Closest Elements.  
  
  class Solution {  
     public List<Integer> findClosestElements(int[] arr, int k, int x) {  
          List<Integer> res = new ArrayList<>();  
          if(arr[0] >= x) {  
              for(int i = 0; i < Math.min(k, arr.length); i++) {  
                  res.add(arr[i]);  
              }  
              return res;  
          } else if(arr[arr.length - 1] <= x) {  
              int len = Math.min(k, arr.length);  
              for(int i = 0; i < len; i++) {  
                  res.add(arr[arr.length - len + i]);  
              }  
              return res;  
          }  
          int index = binarySearch(arr, x);  
          int i = index - 1;  
          int j = index;  
          int c = 0;  
          while(c < k) {  
              if(i < 0) {  
                  res.add( arr[j++]);  
              } else if(j >= arr.length) {  
                  res.add(0,arr[i--]);  
              } else {  
                  int left = arr[i];  
                  int right = arr[j];  
                  int s1 = x - left;  
                  int s2 = right - x;  
                  if(s1 <= s2) {  
                      res.add(0, arr[i--]);  
                  } else if(s1 > s2) {  
                      res.add(arr[j++]);  
                  }  
              }  
              c++;  
          }  
          return res;  
      }  
    
      private int binarySearch(int[] arr, int target) {  
          int l = 0;  
          int h = arr.length - 1;  
          while(l < h) {  
              int m = l + (h - l) / 2;  
              if(arr[m] >= target) {  
                  h = m;  
              } else {  
                  l = m + 1;  
              }  
          }  
          return l;  
      }  
  }  
```    
    
### 个人解读    
  先通过二分法找到最接近的，然后使用双指针进行处理。  
    
tags:    
  -  二分法  
  -  双指针  
