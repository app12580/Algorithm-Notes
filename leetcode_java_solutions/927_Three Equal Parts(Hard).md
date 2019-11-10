### description    
  Given an array A of 0s and 1s, divide the array into 3 non-empty parts such that all of these parts represent the same binary value.  
    
  If it is possible, return any [i, j] with i+1 < j, such that:  
    
  A[0], A[1], ..., A[i] is the first part;  
  A[i+1], A[i+2], ..., A[j-1] is the second part, and  
  A[j], A[j+1], ..., A[A.length - 1] is the third part.  
  All three parts have equal binary value.  
  If it is not possible, return [-1, -1].  
    
  Note that the entire part is used when considering what binary value it represents.  For example, [1,1,0] represents 6 in decimal, not 3.  Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.  
    
     
    
  Example 1:  
    
  Input: [1,0,1,0,1]  
  Output: [0,3]  
  Example 2:  
    
  Input: [1,1,0,1,1]  
  Output: [-1,-1]  
     
    
  Note:  
    
  3 <= A.length <= 30000  
  A[i] == 0 or A[i] == 1  
### solution    
```    
// 流水账 ： 虽然效率低，能做出来就完事了  
Runtime: 10 ms, faster than 11.28% of Java online submissions for Three Equal Parts.  
Memory Usage: 43.7 MB, less than 50.00% of Java online submissions for Three Equal Parts.  
  
  class Solution {  
      public int[] threeEqualParts(int[] A) {  
          int[] none = {-1, -1};  
          int count = 0;  
          for (int a : A) {  
              if (a == 1) count++;  
          }  
          if (count % 3 != 0) return none;  
          if (count == 0) return new int[]{0, 2};  
          count /= 3;  
          int left = 0, right = 0;  
          int t = 0;  
          for (int i = 0; i < A.length; i++) {  
              if (A[i] == 1) {  
                  t++;  
                  if (t == count) left = i;  
                  if (t == count + count) right = i;  
              }  
          }  
          t = A.length - 1;  
          while (A[t--] == 0) {  
              if (A[right + 1] == 1) return none;  
              if (A[left + 1] == 1) return none;  
              right++;  
              left++;  
          }  
          right++;  
          //然后开始判断{left, right}是否符合要求  
          String s1 = helper(A, 0, left);  
          String s2 = helper(A, left + 1, right - 1);  
          String s3 = helper(A, right, A.length - 1);  
          if(!s1.equals(s2) || !s2.equals(s3)) return none;  
          return new int[]{left, right};  
      }  
    
      private String helper(int[] A, int start, int end) {  
          StringBuilder builder = new StringBuilder();  
          boolean flag = true;  
          for(int i = start; i <= end; i++) {  
              int cur = A[i];  
              if(cur == 0) {  
                  if(flag) continue;  
                  builder.append(cur);  
              } else {  
                  flag = false;  
                  builder.append(cur);  
              }  
          }  
          return builder.toString();  
      }  
    
  }  
```    
    
### 个人解读    
    
  思路历程：  
  1、首先考虑三分法，固定中间的，然后再看两边  
  2、可以观察1的个数，如果不能三等分return -1， 否则，就让中间的区间先锁定中间的1，然后可以向两边扩展0  
  3、向两边扩展0的可以有数学办法，源数组的前导0就是中间区间的0个数，同理后导0就是向后扩展的数量。  
  修正： 前面的0不影响，主要看后面的0  
  4、然后再看剩下的中间部分是不是一致了，可以直接用一个String来描述  
    
tags:    
  -  流水账  
  -  三分法  
