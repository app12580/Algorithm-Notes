### description    
  Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.  
    
  Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.  
    
  The order of output does not matter.  
    
  Example 1:  
    
  Input:  
  s: "cbaebabacd" p: "abc"  
    
  Output:  
  [0, 6]  
    
  Explanation:  
  The substring with start index = 0 is "cba", which is an anagram of "abc".  
  The substring with start index = 6 is "bac", which is an anagram of "abc".  
  Example 2:  
    
  Input:  
  s: "abab" p: "ab"  
    
  Output:  
  [0, 1, 2]  
    
  Explanation:  
  The substring with start index = 0 is "ab", which is an anagram of "ab".  
  The substring with start index = 1 is "ba", which is an anagram of "ab".  
  The substring with start index = 2 is "ab", which is an anagram of "ab".  
    
  给出两个字符串s和p，求所有s中满足要求的索引i，使得s.subString(i, i + p.length()) 与 p为相同 字母乱序   
    
  所有字母全是小写  
### solution    
```    
  // 方法一  : 没有利用遍历中间检结果，效率很差  
   public List<Integer> findAnagrams(String s, String p) {  
          List<Integer> res = new ArrayList<>();  
          int[] target = new int[26];  
          for(char ch: p.toCharArray()) {  
              target[ch - 'a']++;  
          }  
    
          for(int i = 0; i <= s.length() - p.length(); i++) {  
              int[] cur = new int[26];  
              boolean flag = true;  
              for(int j = i; j < i + p.length(); j++) {  
                  char ch = s.charAt(j);  
                  if(target[ch - 'a'] == cur[ch - 'a']){  
                      flag = false;  
                      break;  
                  }  
                  cur[ch - 'a']++;  
              }  
              if(flag) {  
                  res.add(i);  
              }  
          }  
          return res;  
    
      }  
        
      //方法二：优化  
      class Solution {  
           public List<Integer> findAnagrams(String s, String p) {  
              List<Integer> res = new ArrayList<>();  
              int[] target = new int[26];  
              for(char ch: p.toCharArray()) {  
                  target[ch - 'a']++;  
              }  
        
              int i = 0;  
              int j = 0;  
              while(j < s.length()) {  
                  char ch = s.charAt(j);  
                  if(target[ch - 'a'] > 0) {  
                      target[ch - 'a']--;  
                      j++;  
                      if(j - i == p.length()) {  
                          res.add(i);  
                      }  
                  } else {  
                      //l向前一步走  
                      target[s.charAt(i) - 'a']++;  
                      i++;  
                  }  
              }  
              return res;  
          }  
      }     
```    
    
### 个人解读    
  因为所有都是小写 ，考虑数组作为中间存储变量。  
  如果不做处理遍历，那么O(n^2)。问题是如何存储中间结果。  
  想法： 双指针+dfs。  
  一个数组：字符最后出现的索引，方便不符合要求后跳转指针。  
  可是跳转了以后，出现字符次数怎么复用啊。  
    
  方法一：暴力法，效率很低。  
  方法二：优化。  
  优化思路：   
  左指针一次往前走一步就好，没必要一下跳很多个。  
  在同一个数组里面判断结果，不需要两个数组之间比较。  
  双指针遍历，主要在于右指针，左指针基本不动，所以while里面最好是右指针。  
    
tags:    
  -  双指针  
  -  优化  
