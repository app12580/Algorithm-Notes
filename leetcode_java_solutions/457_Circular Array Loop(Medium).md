### description    
You are given a circular array nums of positive and negative integers. If a number k at an index is positive, then move forward k steps. Conversely, if it's negative (-k), move backward k steps. Since the array is circular, you may assume that the last element's next element is the first element, and the first element's previous element is the last element.  
  
Determine if there is a loop (or a cycle) in nums. A cycle must start and end at the same index and the cycle's length > 1. Furthermore, movements in a cycle must all follow a single direction. In other words, a cycle must not consist of both forward and backward movements.  
  
   
  
Example 1:  
  
Input: [2,-1,1,2,2]  
Output: true  
Explanation: There is a cycle, from index 0 -> 2 -> 3 -> 0. The cycle's length is 3.  
Example 2:  
  
Input: [-1,2]  
Output: false  
Explanation: The movement from index 1 -> 1 -> 1 ... is not a cycle, because the cycle's length is 1. By definition the cycle's length must be greater than 1.  
Example 3:  
  
Input: [-2,1,-1,-2,-2]  
Output: false  
Explanation: The movement from index 1 -> 2 -> 1 -> ... is not a cycle, because movement from index 1 -> 2 is a forward movement, but movement from index 2 -> 1 is a backward movement. All movements in a cycle must follow a single direction.  
   
  
Note:  
  
-1000 ≤ nums[i] ≤ 1000  
nums[i] ≠ 0  
1 ≤ nums.length ≤ 5000    
### solution    
```    
Runtime: 1 ms, faster than 59.37% of Java online submissions for Circular Array Loop.  
Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Circular Array Loop.  
  
  
  class Solution {  
      public boolean circularArrayLoop(int[] nums) {  
         int len = nums.length;  
          boolean[] flags = new boolean[len];  
          Set<Integer> curPro = new HashSet<>();  
          for (int i = 0; i < len; i++) {  
              if (flags[i]) continue;  
              curPro.clear();  
              int cur = i;  
              boolean isPositive = nums[i] > 0;  
              boolean stop = false;  
              while (!curPro.contains(cur)) {  
                  int val = nums[cur];  
                  if(val % len == 0){  
                      flags[cur] = true;  
                      stop = true;  
                      break;  
                  }  
                  if (isPositive && val < 0 || !isPositive && val > 0) {  
                      stop = true;  
                      break;  
                  }  
                  curPro.add(cur);  
                  flags[cur] = true;  
                  cur = (val + cur) % len;  
                  while(cur < 0) cur += len;  
              }  
              if (!stop && curPro.size() > 1) return true;  
          }  
          return false;  
      }  
  }  
```    
    
### 个人解读    
  用一个boolean[]来进行标记，用一个Set表示当前进度。  
  循环的时候有三种情况：1、循环了，但是长度为1；2、遇到不同的符号；3、成功。  
    
  //没有考虑这种情况：  
  [-1,-2,-3,-4,-5]  
  【0,4,4,4,4】这种的不算循环  
   
 增加了下列的判断   
  ```  
  if(val % len == 0){  
    flags[cur] = true;  
    stop = true;  
    break;  
}  
  ```  
    
tags:    
  -  数组  
  -  数学  
  -  数字逻辑  
