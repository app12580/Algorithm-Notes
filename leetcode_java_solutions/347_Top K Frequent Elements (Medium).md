### description    
  Given a non-empty array of integers, return the k most frequent elements.  
    
  Example 1:  
    
  Input: nums = [1,1,1,2,2,3], k = 2  
  Output: [1,2]  
  Example 2:  
    
  Input: nums = [1], k = 1  
  Output: [1]  
  Note:  
    
  You may assume k is always valid, 1 ≤ k ≤ number of unique elements.  
  Your algorithm's time complexity must be better than O(n log n), where n is the array's size.  
    
    
  出现频率最多的 k 个元素  
### solution    
```    
  
Runtime: 11 ms, faster than 91.55% of Java online submissions for Top K Frequent Elements.  
Memory Usage: 40.4 MB, less than 56.23% of Java online submissions for Top K Frequent Elements.  
  
  class Solution {  
      public List<Integer> topKFrequent(int[] nums, int k) {  
          Map<Integer, Integer> frequencyForNum = new HashMap<>();  
          for (int num : nums) {  
              frequencyForNum.put(num, frequencyForNum.getOrDefault(num, 0) + 1);  
          }  
          List<Integer>[] buckets = new ArrayList[nums.length + 1];  
          for (int key : frequencyForNum.keySet()) {  
              int frequency = frequencyForNum.get(key);  
              if (buckets[frequency] == null) {  
                  buckets[frequency] = new ArrayList<>();  
              }  
              buckets[frequency].add(key);  
          }  
          List<Integer> topK = new ArrayList<>();  
          for (int i = buckets.length - 1; i >= 0 && topK.size() < k; i--) {    // i>=0 并且还有topK还没装满，还有剩余位置  
              if (buckets[i] == null) {  
                  continue;  
              }  
              if (buckets[i].size() <= (k - topK.size())) {  
                  topK.addAll(buckets[i]);  
              } else {  
                  topK.addAll(buckets[i].subList(0, k - topK.size()));  //剩余的  
              }  
          }  
          return topK;  
      }  
  }  
```    
    
### 个人解读    
  Kth问题好像并没有什么特别好的算法。  
  先预处理一遍，获取每个数字的出现频率，同时再填充一个List数组，存着每个出现频率的数字。  
  然后把数组从大往小遍历，取出前k个频率最高的数字。  
    
tags:    
  -  Kth  
  -  预处理  
