### description    
  You are given an array A of strings.  
    
  Two strings S and T are special-equivalent if after any number of moves, S == T.  
    
  A move consists of choosing two indices i and j with i % 2 == j % 2, and swapping S[i] with S[j].  
    
  Now, a group of special-equivalent strings from A is a non-empty subset S of A such that any string not in S is not special-equivalent with any string in S.  
    
  Return the number of groups of special-equivalent strings from A.  
    
     
    
  Example 1:  
    
  Input: ["a","b","c","a","c","c"]  
  Output: 3  
  Explanation: 3 groups ["a","a"], ["b"], ["c","c","c"]  
  Example 2:  
    
  Input: ["aa","bb","ab","ba"]  
  Output: 4  
  Explanation: 4 groups ["aa"], ["bb"], ["ab"], ["ba"]  
  Example 3:  
    
  Input: ["abc","acb","bac","bca","cab","cba"]  
  Output: 3  
  Explanation: 3 groups ["abc","cba"], ["acb","bca"], ["bac","cab"]  
  Example 4:  
    
  Input: ["abcd","cdab","adcb","cbad"]  
  Output: 1  
  Explanation: 1 group ["abcd","cdab","adcb","cbad"]  
     
    
  Note:  
    
  1 <= A.length <= 1000  
  1 <= A[i].length <= 20  
  All A[i] have the same length.  
  All A[i] consist of only lowercase letters.    
      
  题目等价于：  
  用一个标准来描述字符串S： 奇数位有哪些字母和偶数位有哪些字母。  
  返回输入的string[]的不同标准数量。  
      
### solution    
```    
  class Solution {  
      public int numSpecialEquivGroups(String[] A) {  
          Set<String> set= new HashSet<>();  
          for (String s: A){  
              int[] odd= new int[26];  
              int[] even= new int[26];  
              for (int i=0; i<s.length(); i++){  
                  if (i%2==1) odd[s.charAt(i)-'a']++;  
                  else even[s.charAt(i)-'a']++;  
              }  
              String sig= Arrays.toString(odd)+Arrays.toString(even);  
              set.add(sig);  
          }  
          return set.size();  
      }  
  }  
```    
    
### 个人解读    
  中文版本的官方翻译根本不能看。。。  
  题目看不懂，读了好几遍。  
    
  数组外的字符串与内部均不同效。  
  需要注意 with i % 2 == j % 2这个条件。  
    
  对每个字符串进行分析，把奇数位和偶数位的情况分析出来，然后想办法存起来。  
    
tags:    
  -  题目描述  
  -  数学  
