### description    
  We have two integer sequences A and B of the same non-zero length.  
    
  We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.  
    
  At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)  
    
  Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.  
    
  Example:  
  Input: A = [1,3,5,4], B = [1,2,3,7]  
  Output: 1  
  Explanation:   
  Swap A[3] and B[3].  Then the sequences are:  
  A = [1, 3, 5, 7] and B = [1, 2, 3, 4]  
  which are both strictly increasing.  
  Note:  
    
  A, B are arrays with the same length, and that length will be in the range [1, 1000].  
  A[i], B[i] are integer values in the range [0, 2000].  
### solution    
```    
  //方法一： 标记法  
  Runtime: 2 ms, faster than 64.50% of Java online submissions for Minimum Swaps To Make Sequences Increasing.  
  Memory Usage: 40.9 MB, less than 12.50% of Java online submissions for Minimum Swaps To Make Sequences Increasing.  
    
  class Solution {  
     public int minSwap(int[] A, int[] B) {  
          int len = A.length;  
          int[] dp1 = new int[len];  
          int[] dp2 = new int[len];  
          dp2[0] = 1;  
          for(int i = 1; i < len; i++) {  
              int a = A[i];  
              int b = B[i];  
              //不可能同时成为-1  
              //下面的if会很复杂，一是要if前面结果二是要if当前处置  
              //如果当前可以不变(原始对应关系)  
              boolean flag1 = false;  
              boolean flag2 = false;  
              if(a > A[i-1] && b > B[i-1]) {  
                  if(dp1[i-1] >= 0) {  
                      flag1 = true;  
                      dp1[i] = dp1[i - 1];  
                  }  
                  if(dp2[i-1] >= 0) {  
                      flag2 = true;  
                      dp2[i] = dp2[i - 1] + 1;  
                  }  
              }  
              //如果需要交错关系  
              if(a > B[i-1] && b > A[i-1]) {  
                  if(dp1[i-1] >= 0) {  
                      if(flag2) {  
                          dp2[i] = Math.min(dp1[i - 1] + 1, dp2[i]);  
                      } else {  
                          dp2[i] = dp1[i - 1] + 1;  
                      }  
                      flag2 = true;  
                  }  
                  if(dp2[i-1] >= 0) {  
                      if(flag1) {  
                          dp1[i] = Math.min(dp2[i - 1], dp1[i]);  
                      } else {  
                          dp1[i] = dp2[i - 1];  
                      }  
                      flag1 = true;  
                  }  
              }  
              if(!flag1) dp1[i] = -1;  
              if(!flag2) dp2[i] = -1;  
          }  
    
          if(dp1[len - 1] == -1) {  
              return dp2[len-1];  
          } else if(dp2[len-1] == -1) {  
              return dp1[len-1];  
          } else {  
              return Math.min(dp1[len-1], dp2[len-1]);  
          }  
      }  
  }  
```    
  
//方法二： 官方解答，与我的思路基本一致  
 优化两点：  
1、使用int代替int[]  
2、使用Integer_Max来代替-1  
```  
class Solution {  
    public int minSwap(int[] A, int[] B) {  
        // n: natural, s: swapped  
        int n1 = 0, s1 = 1;  
        for (int i = 1; i < A.length; ++i)  {  
            int n2 = Integer.MAX_VALUE, s2 = Integer.MAX_VALUE;  
            if (A[i-1] < A[i] && B[i-1] < B[i]) {  
                n2 = Math.min(n2, n1);  
                s2 = Math.min(s2, s1 + 1);  
            }  
            if (A[i-1] < B[i] && B[i-1] < A[i]) {  
                n2 = Math.min(n2, s1);  
                s2 = Math.min(s2, n1 + 1);  
            }  
            n1 = n2;  
            s1 = s2;  
        }  
        return Math.min(n1, s1);  
    }  
}  
```  
  
    
### 个人解读    
  最好有什么贪婪算法，然后只需要一遍成即可。  
    
  首先指针可以只用一个，然后如果遇到问题了，要么交换i，要么交换i-1。然后问题来了，  
  1、是否存在贪婪算法，这两个一定会有哪一个比较好  
  2、会不会i-1也不够，要一直到i-2，-3.。。  
    
  1,3,8,9  
  1,2,4,7  
    
  因为输入的肯定会有解，说明了任意一个数字(索引为i)，它后面的i+1上一定会有一个比他大的。  
  1,2,X,X,Y  
  1,2,大,小,Y  
  这个时候调换的目的在于，让那个大的和后面的匹配。  
  因此，有两点结论：  
  1、调换i和i-1都能达到符合要求的目的  
  2、至于哪个好，再与后面的那一位，如果i+2引起了问题，后面至少还要再换一次，因此这两个  
    
  因为本题目不需要返回交换结果，只需要数字，所以不需要判断i和i-1哪个好。  
    
  反例：没有考虑到相等的情况。。。  
  0,7,8,10,10,11,12,13,19,18  
  4,4,5,7, 11,14,15,16,17,20  
    
1-3步：注意需要中间一段全换掉  
  0,4,5,7,10,11,12,13,19,18  
  4,7,8,10, 11,14,15,16,17,20  
4步：  
  0,4,5,7,10,11,12,13,17,18  
  4,7,8,10, 11,14,15,16,19,20    
  
  转变思路，贪心算法是不成了，必须想办法使用dp。  
  dp模型定义：  
  dp1[]: i位置与原来的一致时候的最小次数，  
  dp2[]: i位置与原来的不一致时候的最小次数，  
    
  错误算法：  
  下面这个思路应该是对的，但是控制if起来太麻烦，而且终止项的if也很难控制，  
  主要两点:  
  1、dp2[0] = 1  
  2、要用flag控制，不能根据0来控制  
  ```  
  public int minSwap(int[] A, int[] B) {  
          int len = A.length;  
          int[] dp1 = new int[len];  
          int[] dp2 = new int[len];  
          for(int i = 1; i < len; i++) {  
              int a = A[i];  
              int b = B[i];  
              //不可能同时成为-1  
              //下面的if会很复杂，一是要if前面结果二是要if当前处置  
              //如果当前可以不变(原始对应关系)  
              if(a > A[i-1] && b > B[i-1]) {  
                  if(dp1[i-1] > 0) {  
                      dp1[i] = dp1[i - 1];  
                  }  
                  if(dp2[i-1] > 0) {  
                      dp2[i] = dp2[i - 1] + 1;  
                  }  
              }  
              //如果需要交错关系  
              if(a > B[i-1] && b > A[i-1]) {  
                  if(dp1[i-1] > 0) {  
                      if(dp2[i] > 0) {  
                          dp2[i] = Math.min(dp1[i - 1] + 1, dp2[i]);  
                      } else {  
                          dp2[i] = dp1[i - 1] + 1;  
                      }  
    
                  }  
                  if(dp2[i-1] > 0) {  
                      if(dp1[i] > 0) {  
                          dp1[i] = Math.min(dp2[i - 1], dp1[i]);  
                      } else {  
                          dp1[i] = dp2[i - 1];  
                      }  
                  }  
              }  
              if(dp1[i] == 0) dp1[i] = -1;  
              if(dp2[i] == 0) dp2[i] = -1;  
          }  
    
          if(dp1[len - 1] == -1) {  
              return dp2[len-1];  
          } else if(dp2[len-1] == -1) {  
              return dp1[len-1];  
          } else {  
              return Math.min(dp1[len-1], dp2[len-1]);  
          }  
      }  
  ```  
  
tags:    
  -  重点数学  
  -  数字逻辑  
  -  动态规划  
