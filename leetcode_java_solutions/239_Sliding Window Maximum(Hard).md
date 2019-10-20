### description      
  Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.    
      
  Example:    
      
  Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3    
  Output: [3,3,5,5,6,7]     
  Explanation:     
      
  Window position                Max    
  ---------------               -----    
  [1  3  -1] -3  5  3  6  7       3    
   1 [3  -1  -3] 5  3  6  7       3    
   1  3 [-1  -3  5] 3  6  7       5    
   1  3  -1 [-3  5  3] 6  7       5    
   1  3  -1  -3 [5  3  6] 7       6    
   1  3  -1  -3  5 [3  6  7]      7    
  Note:    
  You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.    
      
  Follow up:    
  Could you solve it in linear time?    
      
### solution      
```      
// 方法一： 流水账    
Runtime: 77 ms, faster than 5.12% of Java online submissions for Sliding Window Maximum.    
Memory Usage: 40.8 MB, less than 92.19% of Java online submissions for Sliding Window Maximum.    
效率好差    
    
  class Solution {    
       public int[] maxSlidingWindow(int[] nums, int k) {    
          if(nums.length == 0 || nums.length - k + 1 <=0) return new int[0];    
          if(k == 1) return nums;    
          PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();    
          int[] res = new int[nums.length - k + 1];    
          int index = 0;    
          for(int i = 0; i < k - 1; i++) {    
              priorityQueue.offer(nums[i]);    
          }    
          for(int i = k - 1; i < nums.length; i++) {    
              priorityQueue.offer(nums[i]);    
              res[index++] = Collections.max(priorityQueue);    
              priorityQueue.remove(nums[index - 1]);    
          }    
          return res;    
      }    
  }    
      
      
  //方法二： TreeMap    
  Runtime: 35 ms, faster than 23.35% of Java online submissions for Sliding Window Maximum.    
  Memory Usage: 40.8 MB, less than 92.19% of Java online submissions for Sliding Window Maximum.    
      
  class Solution {    
       public int[] maxSlidingWindow(int[] nums, int k) {    
          if(nums.length == 0 || nums.length - k + 1 <=0) return new int[0];    
          if(k == 1) return nums;    
          TreeMap<Integer, Integer> treeMap = new TreeMap<>();    
          int[] res = new int[nums.length - k + 1];    
          int index = 0;    
          for(int i = 0; i < k - 1; i++) {    
              treeMap.put(nums[i], treeMap.getOrDefault(nums[i], 0) + 1);    
          }    
          for(int i = k - 1; i < nums.length; i++) {    
             treeMap.put(nums[i], treeMap.getOrDefault(nums[i], 0) + 1);    
              res[index++] = treeMap.lastKey();    
              int left = nums[index - 1];    
              if(treeMap.get(left) == 1) {    
                  treeMap.remove(left);    
              } else {    
                  treeMap.put(left, treeMap.get(left) - 1);    
              }    
          }    
          return res;    
      }    
  }    
    
  // 方法三： 双向队列， 忽略没用数据，单调队列  
  Runtime: 9 ms, faster than 83.18% of Java online submissions for Sliding Window Maximum.  
  Memory Usage: 40.4 MB, less than 93.75% of Java online submissions for Sliding Window Maximum.  
    
      class Solution {  
        ArrayDeque<Integer> deq = new ArrayDeque<Integer>();  
        int [] nums;  
    
        public void clean_deque(int i, int k) {  
          // remove indexes of elements not from sliding window  
          if (!deq.isEmpty() && deq.getFirst() == i - k)  
            deq.removeFirst();  
    
          // remove from deq indexes of all elements   
          // which are smaller than current element nums[i]  
          while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]) deq.removeLast();  
        }  
    
        public int[] maxSlidingWindow(int[] nums, int k) {  
          int n = nums.length;  
          if (n * k == 0) return new int[0];  
          if (k == 1) return nums;  
    
          // init deque and output  
          this.nums = nums;  
          int max_idx = 0;  
          for (int i = 0; i < k; i++) {  
            clean_deque(i, k);  
            deq.addLast(i);  
            // compute max in nums[:k]  
            if (nums[i] > nums[max_idx]) max_idx = i;  
          }  
          int [] output = new int[n - k + 1];  
          output[0] = nums[max_idx];  
    
          // build output  
          for (int i  = k; i < n; i++) {  
            clean_deque(i, k);  
            deq.addLast(i);  
            output[i - k + 1] = nums[deq.getFirst()];  
          }  
          return output;  
        }  
      }  
  
    
```      
      
### 个人解读      
  感觉怎么都是逃不开排序的啊，流水账试一下吧。    
      
  思路一：    
  1、使用PriorityQueue    
  // 不行，因为PriorityQueue只能返回最前面的，看来是还不是很了解的状态。    
  2、改用TreeSet    
  // 不行，TreeSet不可重复。。。    
  3、改用TreeMap，稍微好一点    
      
  怎么java的这么恶心啊，想要一个排序的List都没有现成的么。。。最后解锁新知识：Collections.max(collection);    
      
  然而流水账效率很低，开始想办法有没有啥线性的办法？    
  比如用一个int[]来存储不同位置为止的最大值。    
      
  参考答案：    
  双向队列： 通过一番操作，构造出来一个长度最大为k的数据结构，重点是里面存储的内容。    
  表示当前区间的所有不可忽略数据，并且从大到小排列。    
  忽略数据的条件：    
  举例子：假设前三个数字为312，k为3，那么中间的1就可以不要了。    
      
  总结：    
  1、排序有时候会遗留下来很多无用数据，需要适当删减    
  2、双向队列的使用（为啥做了700+题目了，现在才遇到。。）    
      
tags:      
  -  双向队列    
  -  单调队列    
