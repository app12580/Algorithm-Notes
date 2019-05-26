### description    
  Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.  
    
  Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.  
    
  Example 1:  
  Input: [1, 2, 2, 3, 1]  
  Output: 2  
  Explanation:   
  The input array has a degree of 2 because both elements 1 and 2 appear twice.  
  Of the subarrays that have the same degree:  
  [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]  
  The shortest length is 2. So return 2.  
  Example 2:  
  Input: [1,2,2,3,1,4,2]  
  Output: 6  
  Note:  
    
  nums.length will be between 1 and 50,000.  
  nums[i] will be an integer between 0 and 49,999.  
### solution    
```    
  //第一次的失败提交：  
  class Solution {  
      public int findShortestSubArray(int[] nums) {  
          Map<Integer, Integer> countMap = new HashMap<>();  
          Map<Integer, Integer> firstIndexMap = new HashMap<>();  
          Map<Integer, Integer> lastIndexMap = new HashMap<>();  
          int maxCountNum = 0;  
          for(int i = 0; i < nums.length; i++) {  
              int num = nums[i];  
              countMap.put(num, countMap.getOrDefault(num, 0) + 1);  
              lastIndexMap.put(num, i);  
              if(!firstIndexMap.containsKey(num)) {  
                  firstIndexMap.put(num, i);  
              }  
              if(countMap.get(num) > countMap.getOrDefault(maxCountNum, 0)) {  
                  maxCountNum = num;  
              }  
          }  
          return lastIndexMap.get(maxCountNum) - firstIndexMap.get(maxCountNum) + 1;  
      }  
  }  
  //反思： 错误原因， 没有考虑相同次数时候，选择数量最少的那个  
    class Solution {
        
          public int findShortestSubArray(int[] nums) {
            Map<Integer, Integer> countMap = new HashMap<>();
            Map<Integer, Integer> firstIndexMap = new HashMap<>();
            Map<Integer, Integer> lastIndexMap = new HashMap<>();
            for(int i = 0; i < nums.length; i++) {
                int num = nums[i];
                countMap.put(num, countMap.getOrDefault(num, 0) + 1);
                lastIndexMap.put(num, i);
                if(!firstIndexMap.containsKey(num)) {
                    firstIndexMap.put(num, i);
                }
            }
    
            int res = 0;
            int maxCount = 0;
            for(Map.Entry<Integer, Integer> entry: countMap.entrySet()) {
                Integer curCount = entry.getValue();
                Integer key = entry.getKey();
                if(curCount > maxCount) {
                    res = lastIndexMap.get(key) - firstIndexMap.get(key) + 1;
                    maxCount = curCount;
                } else if(curCount == maxCount) {
                    int t = lastIndexMap.get(key) - firstIndexMap.get(key) + 1;
                    res = Math.min(t, res);
                }
            }
    
            return res;
        }
        
    }
```    
    
### 个人解读    
  遍历问题诶，这种问题呢，就是不能贪，该有的中间存储结构还是要有。  
  所以：一旦确认这个算法有几个map用来存储，问题就解决了。  
  本题放三个： count，first，last  
    
tags:    
  -   hashmap存储  
  -   数组  
