### description    
  Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.  
    
  Note:  
    
  1 <= w.length <= 10000  
  1 <= w[i] <= 10^5  
  pickIndex will be called at most 10000 times.  
  Example 1:  
    
  Input:   
  ["Solution","pickIndex"]  
  [[[1]],[]]  
  Output: [null,0]  
  Example 2:  
    
  Input:   
  ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]  
  [[[1,3]],[],[],[],[],[]]  
  Output: [null,0,1,1,1,0]  
  Explanation of Input Syntax:  
    
  The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.  
    
### solution    
```    
Runtime: 68 ms, faster than 53.73% of Java online submissions for Random Pick with Weight.  
Memory Usage: 43.1 MB, less than 100.00% of Java online submissions for Random Pick with Weight.  
  
  class Solution {  
   private int[] sum;  
      private Random random;  
    
      public Solution(int[] w) {  
          sum = new int[w.length];  
          random  = new Random();  
          int s = 0;  
          for(int i = 0; i < w.length; i++) {  
              s += w[i];  
              sum[i] = s;  
          }  
      }  
    
      public int pickIndex() {  
          int r = random.nextInt(sum[sum.length - 1]);  
          return binarySearch(sum, r);  
      }  
    
      public int binarySearch(int[] nums, int key) {  
          int l = 0, h = nums.length - 1;  
          while (l < h) {  
              int m = l + (h - l) / 2;  
              if (nums[m] > key) {              //这一行要不要等号非常关键，需要具体找个例子分析。  
                  h = m;      //如果nums[m] == key，  
              } else {  
                  l = m + 1;  
              }  
          }  
          return l;  
      }  
  }  
    
     
```    
    
### 个人解读    
  通过一个数组记录结果，然后每次随机数通过二分法去找到是多少。  
    
  // 1 2   3  
  // 1 3   6  
  // 0 12  345  
    
  所以是要找到大于targetd的最左值。//不含等号  
    
tags:    
  -  二分法  
  -  随机数  
