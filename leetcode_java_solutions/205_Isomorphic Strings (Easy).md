### description  
  Given two strings s and t, determine if they are isomorphic.  
  
  Two strings are isomorphic if the characters in s can be replaced to get t.  
  
  All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same   character but a character may map to itself.  
  
  Example 1:  
  
  Input: s = "egg", t = "add"  
  Output: true  
  Example 2:  
  
  Input: s = "foo", t = "bar"  
  Output: false  
  Example 3:  
  
  Input: s = "paper", t = "title"  
  Output: true  
  Note:  
  You may assume both s and t have the same length.  
### solution  
```  
  class Solution {
      public boolean isIsomorphic(String s, String t) {
          int[] indexS = new int[256];
          int[] indexT = new int[256];
          for(int i = 0; i < s.length(); i++) {
              char ss = s.charAt(i);
              char tt = t.charAt(i);
              if(indexS[ss] != indexT[tt]) {
                  return false;
              }
              indexS[ss] = i+1; //这里的i+1是为了避开初始化的0
              indexT[tt] = i+1;
          }
          return true;
      }
  }
```  
  
### 个人解读  
  想到的最大的问题，就是两个数组是错位的。第一反应是再弄一个数组结构去做这个关联。  
  从头开始遍历的时候，每单词循环有四个数据：s的i，s的ch，t的i，t的ch。发现关键点在那个i上面。因此猜测那个中间变量就是存着index的信息。  
  注意一个细节（刚开始犯错了）：需要避开初始化数组的0，所以需要i避开0，可以i+1，或者i+2什么的。  
tags:   
  -  字符串   
  -  全范围存储结果  
