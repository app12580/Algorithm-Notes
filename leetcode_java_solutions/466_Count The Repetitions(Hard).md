### description    
  Define S = [s,n] as the string S which consists of n connected strings s. For example, ["abc", 3] ="abcabcabc".  
    
  On the other hand, we define that string s1 can be obtained from string s2 if we can remove some characters from s2 such that it becomes s1. For example, “abc” can be obtained from “abdbec” based on our definition, but it can not be obtained from “acbbe”.  
    
  You are given two non-empty strings s1 and s2 (each at most 100 characters long) and two integers 0 ≤ n1 ≤ 106 and 1 ≤ n2 ≤ 106. Now consider the strings S1 and S2, where S1=[s1,n1] and S2=[s2,n2]. Find the maximum integer M such that [S2,M] can be obtained from S1.  
    
  Example:  
    
  Input:  
  s1="acb", n1=4  
  s2="ab", n2=2  
    
  Return:  
  2  
### solution    
```    
  
  // 方法一： 暴力法代码的优化  
  仅仅是写法的改变，就从TLE变成了这个成绩。。。  
  Runtime: 225 ms, faster than 43.75% of Java online submissions for Count The Repetitions.  
  Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Count The Repetitions.  
    
  class Solution {  
       public int getMaxRepetitions(String s1, int n1, String s2, int n2) {  
          int gcd = gcd(n1, n2);  
          n1 /= gcd;  
          n2 /= gcd;  
          if(s1.equals(s2))return n1 / n2;  
          return find(s1, n1, s2, n2);       
      }  
    
       private int find(String s1, int n1, String s2, int n2) {  
          int m = s1.length();  
          int n = s2.length();  
          int count1 = 0, count2 = 0, index = 0;  
          while(count1 < n1) {    
              for(int i = 0; i < m; i++) {  
                  if(s1.charAt(i) == s2.charAt(index)) {  
                      if(index == n - 1) {  
                          index = 0;  
                          count2++;  
                      } else {  
                          index++;  
                      }  
                  }  
              }  
              count1++;  
          }  
          return count2 / n2;  
      }  
    
      private int gcd(int a, int b) {  
          return b == 0 ? a : gcd(b, a % b);  
      }  
  }   
```    
    
### 个人解读    
  最大的问题在于，如何将问题切割分治  
  ba:10 ab:2 --> 4  
  babababa babababa baba  
  abab  
  ba:5 ab:1 --> 3  
  babababa    
  ab  
    
  刚开始想着去找n1,n2的最大公约数，然而感觉这样可能会有遗漏。。。然而事实并没有。。。  
    
  优化：3*4, 将原来0,1,2,3,....11的遍历，而且还要各种%4的操作变成了：  
  1:0123 2:0123 3:0123 并且不用%计算，就实现了很大的时间优化  
  然而思路是一模一样的。  
    
  最大问题： 我那个gcd的步骤。。。。不知道是不是错误的。。。。  
    
  TLE方法  
  ```  
  class Solution {  
     public int getMaxRepetitions(String s1, int n1, String s2, int n2) {   
          int gcd = gcd(n1, n2);  
          n1 /= gcd;  
          n2 /= gcd;  
          return find(s1, n1, s2, n2);  
      }  
    
      private int find(String s1, int n1, String s2, int n2) {  
          int m = s1.length();  
          int n = s2.length();  
          int res = 0;  
          int i = 0, j = 0;  
          while(i < m * n1) {  
              char c1 = s1.charAt(i % m);  
              char c2 = s2.charAt(j % n);  
              if(c1 == c2) {  
                  if(j % (n * n2) == n * n2 - 1) res++;  //注意res++的判定条件  
                  j++;  
              }  
              i++;  
          }  
          return res;  
      }  
    
      private int gcd(int a, int b) {  
          return b == 0 ? a : gcd(b, a % b);  
      }  
  }  
  ```  
    
tags:    
  -  代码优化  
  -  存疑  
