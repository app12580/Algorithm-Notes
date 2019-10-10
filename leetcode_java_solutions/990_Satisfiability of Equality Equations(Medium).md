### description    
  Given an array equations of strings that represent relationships between variables, each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.  
    
  Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.  
    
     
    
  Example 1:  
    
  Input: ["a==b","b!=a"]  
  Output: false  
  Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.  
  Example 2:  
    
  Input: ["b==a","a==b"]  
  Output: true  
  Explanation: We could assign a = 1 and b = 1 to satisfy both equations.  
  Example 3:  
    
  Input: ["a==b","b==c","a==c"]  
  Output: true  
  Example 4:  
    
  Input: ["a==b","b!=c","c==a"]  
  Output: false  
  Example 5:  
    
  Input: ["c==c","b==d","x!=z"]  
  Output: true  
     
    
  Note:  
    
  1 <= equations.length <= 500  
  equations[i].length == 4  
  equations[i][0] and equations[i][3] are lowercase letters  
  equations[i][1] is either '=' or '!'  
  equations[i][2] is '='  
### solution    
```    
Runtime: 1 ms, faster than 100.00% of Java online submissions for Satisfiability of Equality Equations.  
Memory Usage: 37.6 MB, less than 40.00% of Java online submissions for Satisfiability of Equality Equations.  
  
  class Solution {  
     
      public boolean equationsPossible(String[] equations) {  
          int[] sameF = new int[26];  
          for(int i = 0; i < 26; i++) {  
              sameF[i] = i;  
          }  
    
          List<int[]> list = new ArrayList<>();  
          for (String s : equations) {  
              int c1 = s.charAt(0) - 'a';  
              int c2 = s.charAt(3) - 'a';  
              boolean same = s.charAt(1) == '=';  
              if (c1 == c2) {  
                  if (same) {  
                      continue;  
                  } else {  
                      return false;  
                  }  
              }  
              if (same) {  
                  union(sameF, c1, c2);  
              } else {  
                  list.add(new int[]{c1, c2});  
              }  
          }  
          for(int[] p: list) {  
              int c1 = p[0];  
              int c2 = p[1];  
              if(find(sameF, c1) == find(sameF, c2)) {  
                  return false;  
              }  
          }  
          return true;  
      }  
    
      private int find(int[] f, int x) {  
          if (f[x] != x) {  
              f[x] = find(f, f[x]);  
          }  
          return f[x];  
      }  
    
      private void union(int[] f, int x, int y) {  
          x = find(f, x);  
          y = find(f, y);  
          if (x != y) {  
              f[x] = y;  
          }  
      }  
    
        
  }  
```    
    
### 个人解读    
  主要考察人如何使用数据结构去描述。  
    
  思路一：不想整什么乱七八糟的了，每个字符配两个set就完事了。然而遇到问题了，A==B,B==C，需要将AC建立联系，所以还是老实用并查集好了。  
  并查集也遇到问题。。。因为不同的字符的之间也是需要循环进行建立联系的。但是可以用两套并查集进行判断。  
    
  思路二：  
  先使用并查集统计所有==，然后再去判断所有不等于的  
    
tags:    
  -  并查集  
