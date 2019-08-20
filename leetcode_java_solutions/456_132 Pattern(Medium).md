### description    
  Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.  
    
  Note: n will be less than 15,000.  
    
  Example 1:  
  Input: [1, 2, 3, 4]  
    
  Output: False  
    
  Explanation: There is no 132 pattern in the sequence.  
  Example 2:  
  Input: [3, 1, 4, 2]  
    
  Output: True  
    
  Explanation: There is a 132 pattern in the sequence: [1, 4, 2].  
  Example 3:  
  Input: [-1, 3, 2, 0]  
    
  Output: True  
    
  Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].  
### solution    
```    

// 方法一：On2 先凑合凑合看吧
class Solution {
   public boolean find132pattern(int[] nums) {
    for (int j = 0, min = Integer.MAX_VALUE; j < nums.length; j++) {
         min = Math.min(nums[j], min);
         if (min == nums[j]) continue;
         
         for (int k = nums.length - 1; k > j; k--) {
             if (min < nums[k] && nums[k] < nums[j]) return true;
         }
     }
     
     return false;
}
     
}


// 方法二： 看不懂的


  class Solution {  
       public boolean find132pattern(int[] nums) {  
          int[] arr = Arrays.copyOf(nums, nums.length);  
    
          for (int i = 1; i < nums.length; i++) {  
              arr[i] = Math.min(nums[i - 1], arr[i - 1]);  
          }  
    
          int top = nums.length;  
          //arr[i] 的含义： 为nums从0到i-1的最小值  
          for (int j = nums.length - 1; j >= 0; j--) {  
              if (nums[j] <= arr[j]) continue;    // 如果当前数组要比前面的"1"还要小，则跳过。  
    
              //能运行到这里，说明nums[j] > arr[i]，即当前数字比前面的最小值大。  
              while (top < nums.length && arr[top] <= arr[j]) {  
                  top++;  
              }  
              if (top < nums.length && nums[j] > arr[top]) {  
                  //如果if通过，这里的nums[j]为3, arr[top]为2  
                  // 所以猜测，top为j后面的，比min大的值中最小的那一个。  
                  return true;  
              }  
              arr[--top] = nums[j];  //把top-1换成那个当前的值， arr这个数组有两重含义  
          }  
    
          return false;  
      }  
  }  
```    
    
### 个人解读    
  总感觉这题目使用标记法的话，和之前某个题目很类似，那个题目是找最小值，和倒数第二小。通过标记法和各种ifelse结局。(334题)  
  通过标记法，争取On时间解决。  
  标记法最大的问题就是难以理解，情况太复杂。  
    
  标记法并不推荐。  
    
  学到了一句话：  
  ```  
  It's easier to understand the code than to understand your explanation.  
  ```  
  // https://leetcode.com/problems/132-pattern/discuss/94089/Java-solutions-from-O(n3)-to-O(n)-for-%22132%22-pattern-(updated-with-one-pass-slution)  
    
  因为家里原因，没心情继续研究了，烦！！！！  
    
tags:    
  -  没看懂解答  
  -  ？？  
