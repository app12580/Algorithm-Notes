### description      
Given an array of non-negative integers, you are initially positioned at the first index of the array.    
    
Each element in the array represents your maximum jump length at that position.    
    
Your goal is to reach the last index in the minimum number of jumps.    
    
Example:    
    
Input: [2,3,1,1,4]    
Output: 2    
Explanation: The minimum number of jumps to reach the last index is 2.    
    Jump 1 step from index 0 to 1, then 3 steps to the last index.    
Note:    
    
You can assume that you can always reach the last index.    
      
### solution      
```      
Runtime: 1 ms, faster than 99.99% of Java online submissions for Jump Game II.    
Memory Usage: 37.7 MB, less than 100.00% of Java online submissions for Jump Game II.    
    
  class Solution {    
      public int jump(int[] nums) {    
          int res = 0;    
          int right = 0;    
          int left = 0;    
          int target = nums.length - 1;    
          if(target == 0) return 0; //注意res的位置和特殊色情况    
          while (true) {    
              res++;    
              int max = 0;    
              for (int i = right; i >= left; i--) {    
                  max = Math.max(max, i + nums[i]);    
                  if (max >= target) return res;    
              }    
              left = right + 1;    
              right = max;    
          }    
      }    
  }    
```      
      
### 个人解读      
  怎么感觉一个BFS就完事了啊。。。    
  然而TLE了，一个长度25000，从25000一直到0的超大数组不通过了。。。    
      
  BFS不行，可能太拖沓了，那么换DFS试一试。然而题目是要求最小值，所以DFS并没有卵用    
      
  稍加思考后，想到一个O(N)的方法，就是每次去找当前活动范围中的最大值，然后重复此过程。    
  0->[1,4]->从1,4里面找最大[5,7]    
      
  注意细节，关于res，for循环顺序，先大后小    
    
  总结： 有些Hard题目是真的好简单。。。    
      
  ```    
  class Solution {    
      public int jump(int[] nums) {    
          Queue<Integer> queue = new LinkedList<>();    
          queue.offer(0);    
          Set<Integer> set = new HashSet<>();    
          int res = 0;    
          int len = nums.length;    
          while(!queue.isEmpty()) {    
              int size = queue.size();    
              for(int i = 0; i < size; i++) {    
                  int poll = queue.poll();    
                  if(poll == len - 1) return res;    
                  int step = nums[poll];    
                  for(int j = 1; j <= step; j++) {    
                      int next = poll + j;    
                      if(!set.contains(next)) {    
                          set.add(next);    
                          queue.offer(next);    
                      }    
                  }    
              }    
              res++;    
          }    
          return res;    
      }    
  }    
  ```    
      
tags:      
  -  数学    
