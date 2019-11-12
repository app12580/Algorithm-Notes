### description  
  Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.
  
  (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
  
  Return the number of good subarrays of A.
  
   
  
  Example 1:
  
  Input: A = [1,2,1,2,3], K = 2
  Output: 7
  Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
  Example 2:
  
  Input: A = [1,2,1,3,4], K = 3
  Output: 3
  Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
   
  
  Note:
  
  1 <= A.length <= 20000
  1 <= A[i] <= A.length
  1 <= K <= A.length
### solution  
```  
// 方法一： 滑动窗口
Runtime: 72 ms, faster than 44.58% of Java online submissions for Subarrays with K Different Integers.
Memory Usage: 41.7 MB, less than 78.95% of Java online submissions for Subarrays with K Different Integers.

  class Solution {
      public int subarraysWithKDistinct(int[] A, int K) {
          Window window1 = new Window();
          Window window2 = new Window();
          int ans = 0, left1 = 0, left2 = 0;
  
          for (int right = 0; right < A.length; ++right) {    
              int x = A[right];
              window1.add(x);
              window2.add(x);
  
              while (window1.different() > K)
                  window1.remove(A[left1++]);// 进入diff = K的第一个索引
              while (window2.different() >= K)
                  window2.remove(A[left2++]); // 进入diff = K-1的第一个索引
  
              ans += left2 - left1;   //每次都是以right为结尾的区间个数
          }
  
          return ans;
      }
  }
  
  class Window {
      Map<Integer, Integer> count;
      int nonzero;
  
      Window() {
          count = new HashMap();
          nonzero = 0;
      }
  
      void add(int x) {
          count.put(x, count.getOrDefault(x, 0) + 1);
          if (count.get(x) == 1)
              nonzero++;
      }
  
      void remove(int x) {
          count.put(x, count.get(x) - 1);
          if (count.get(x) == 0)
              nonzero--;
      }
  
      int different() {
          return nonzero;
      }
  } 
```  
  
### 个人解读  
  如果使用动态规划的话， 需要知道这么一些个元素：已经计算过的区间位置；区间内已经有的不同数字个数；区间内有哪些数字。
  
  然后又想到，滑动窗口是否可行？自己想的！
  然后第一次看到滑动窗口还专门弄一个类的。。。
  
  关键点： 以i为结尾的好数组的左端点，一定都是连续的
  A = [1,2,1,2,3], K = 2
  
  例如以i=3为例： 1212,212,12  是根据[0,2]这个区间来的。其中[0,3]的diff=2,[3,3]的区间diff=1
  主要是根据这个diff来判断的
  
  
  
tags:  
  -  全部连续子区间
