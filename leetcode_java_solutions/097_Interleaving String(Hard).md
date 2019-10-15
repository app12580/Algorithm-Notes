### description    
  Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.  
    
  Example 1:  
    
  Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"  
  Output: true  
  Example 2:  
    
  Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"  
  Output: false  
### solution    
```    
Runtime: 4 ms, faster than 43.20% of Java online submissions for Interleaving String.  
Memory Usage: 34.6 MB, less than 100.00% of Java online submissions for Interleaving String.  
  
  class Solution {  
       public boolean isInterleave(String s1, String s2, String s3) {  
          if (s3.length() != s1.length() + s2.length()) return false;  
          int m = s1.length();  
          int n = s2.length();  
          boolean[][] dp = new boolean[m +1][n + 1]; //if true，表示前i,j个数可以组成s3的前i+j个数字,具有唯一过程性  
          dp[0][0] = true;  
          for(int i = 0; i < m; i++) {  
              if(s1.charAt(i) == s3.charAt(i)) {  
                  dp[i+1][0] = true;  
              } else {  
                  break;  
              }  
          }  
          for(int i = 0; i < n; i++) {  
              if(s2.charAt(i) == s3.charAt(i)) {  
                  dp[0][i+1] = true;  
              } else {  
                  break;  
              }  
          }  
          for(int i = 0; i < m; i++) {  
              for(int j = 0; j < n; j++) {  
                  char target = s3.charAt(i + j + 1);  
                  char c1 = s1.charAt(i);  
                  char c2 = s2.charAt(j);  
                  if(c1 == target && dp[i][j+1] || c2 == target && dp[i+1][j]) {  
                      dp[i+1][j+1] = true;  
                  }  
              }  
          }  
          return dp[m][n];  
      }  
  }  
```    
    
### 个人解读    
  类似前面的AC自助机[044](044_Wildcard%20Matching(Hard).md)。  
  问题在于如何建立一个回溯机制，本题目好像不是044那种，因为*的存在，导致贪婪算法，让*匹配最少只需要存储最后一次分支即可。  
  思路一：  
  用两个指针不停地去凑，遇到不行的就回溯。  
  然而TLE了。。。。  
    
  思路二：  
  核心点： 把s3刨去s1就变成了s2，因此不需要回溯了。  
  然而并不是这样子的。。。  
  ```  
    aa  
    abc  
    abcaa  : 刨去aa以后会变成bca     
  ```  
    
  思路三：  
  多维DP  
  为什么一开始没有想到啊。。。之前做法属于遍历，TLE之后应该第一时间想到DP的啊。。
  结果瞄了一眼discuss里的DP两个字母，瞬间会了这道题。。。    
    
  总结：  
  1、类似于递归的方向性，从原始出发有无限种可能，但是从终点出发，还会发生世界线的收束。  
    
  ```  
  TLE  
  class Solution {  
      public boolean isInterleave(String s1, String s2, String s3) {  
              if (s3.length() != s1.length() + s2.length()) return false;  
              int f1 = 0, f2 = 0, f3 = 0;  
              Stack<int[]> stack = new Stack<>();  
              while(f3 < s3.length()) {  
                  if(f2 == s2.length() || f1 == s1.length()) {  
                      String rest = f1 == s1.length() ? s2.substring(f2) : s1.substring(f1);  
                      if(s3.substring(f3).equals(rest)) {  
                          return true;  
                      } else {  
                          if(stack.isEmpty()) return false;  
                          int[] pop = stack.pop();  
                          f1 = pop[0];  
                          f2 = pop[1] + 1;  
                          f3 = f1 + f2; //error，注意这里的写法，开始时候错误的+1了  
                          continue;  
                      }  
                  }  
                  char target = s3.charAt(f3);  
                  char ch1 = s1.charAt(f1);  
                  char ch2 = s2.charAt(f2);  
                  if(ch1 == target && ch2 == target) {  
                      stack.push(new int[]{f1, f2});  
                      f1++;  
                      f3++;  
                  } else if(ch1 == target) {  
                      f1++;  
                      f3++;  
                  } else if(ch2 == target) {  
                      f2++;  
                      f3++;  
                  } else {  
                      if(stack.isEmpty()) return false;  
                      int[] pop = stack.pop();  
                      f1 = pop[0];  
                      f2 = pop[1] + 1;  
                      f3 = f1 + f2;  
                  }  
              }  
              return true;  
          }  
  }  
  ```  
    
    
tags:    
  -  多维DP  
  -  字符串  
  -  唯一过程性  
