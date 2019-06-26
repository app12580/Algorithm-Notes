### description    
  Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".  
    
  Example 1:  
  Input: [5, 4, 3, 2, 1]  
  Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]  
  Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal".   
  For the left two athletes, you just need to output their relative ranks according to their scores.  
  Note:  
  N is a positive integer and won't exceed 10,000.  
  All the scores of athletes are guaranteed to be unique.  
### solution    
```    
  
Runtime: 6 ms, faster than 88.88% of Java online submissions for Relative Ranks.  
Memory Usage: 38.9 MB, less than 91.71% of Java online submissions for Relative Ranks.  
  
  class Solution {  
      public String[] findRelativeRanks(int[] nums) {  
          int len = nums.length;  
          int[] copy = new int[len];  
          System.arraycopy(nums, 0, copy, 0, len);  
    
          Arrays.sort(copy);  
          Map<Integer, Integer> map = new HashMap<>();  
          for(int i = 0; i < len; i++) {  
              int num = copy[i];  
              map.put(num, len - i);  
          }  
    
          String[] res = new String[len];  
          for(int i = 0; i < nums.length; i++) {  
              int rank = map.get(nums[i]);  
              if(rank == 1) {  
                  res[i] = "Gold Medal";  
              } else if(rank == 2) {  
                  res[i] = "Silver Medal";  
              } else if(rank == 3) {  
                  res[i] = "Bronze Medal";  
              } else {  
                  res[i] = "" + rank;  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  使用了Arrays.sort()方法，使用HasmMap存储中间结果。  
  发现效率竟然还可以。  
    
tags:    
  -  预处理  
  -  HashMap  
