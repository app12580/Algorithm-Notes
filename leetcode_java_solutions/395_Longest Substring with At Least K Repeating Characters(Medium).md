### description    
  Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.  
    
  Example 1:  
    
  Input:  
  s = "aaabb", k = 3  
    
  Output:  
  3  
    
  The longest substring is "aaa", as 'a' is repeated 3 times.  
  Example 2:  
    
  Input:  
  s = "ababbc", k = 2  
    
  Output:  
  5  
    
  The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.  
### solution    
```    
  
Runtime: 2 ms, faster than 63.25% of Java online submissions for Longest Substring with At Least K Repeating Characters.  
Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Longest Substring with At Least K Repeating Characters.  
  
  class Solution {  
     public int longestSubstring(String s, int k) {  
          int[] arr = new int[26];  
          for(char ch: s.toCharArray()) {  
              arr[ch - 'a']++;  
          }  
          boolean allBig = true;  
          boolean allSmall = true;  
          Set<Integer> small = new HashSet<>();  
          for(int i = 0; i < 26; i++) {  
              int cur = arr[i];  
              if(cur == 0) continue;  
              if(cur >= k) {  
                  allSmall = false;  
              } else {  
                  allBig = false;  
                  small.add(i);  
              }  
          }  
          if(allBig) return s.length();  
          if(allSmall) return 0;  
          int res = 0;  
          int start = 0;  
          for(int i = 0; i < s.length(); i++) {  
              char c = s.charAt(i);  
              if(small.contains(c - 'a')) {  
                  res = Math.max(res, check(s, start, i - 1, k));  
                  start = i + 1;  
              }  
          }  
         res = Math.max(res, check(s, start, s.length() -1, k));  
    
          return res;  
      }  
    
      private int check(String s, int start, int end, int k) {  
          if(start > end) return 0;  
          boolean ok = true;  
          Map<Character, Integer> map = new HashMap<>();  
          for(int i = start; i <= end; i++) {  
              char c = s.charAt(i);  
              map.put(c, map.getOrDefault(c, 0) + 1);  
          }  
          for(int val: map.values()) {  
              if(val < k) return longestSubstring(s.substring(start, end + 1), k);  
          }  
          return end - start + 1;  
      }  
  }  
```    
    
### 个人解读    
  思路一：先遍历一遍把符合要求的字母和不符合要求的字母分开。然后第二次遍历，遇到不符合的字母，就把当前curLen拿去判断。  
  核心：题目要求的是最长，而不能一直长的原因就是会被不符合要求的字符污染掉。  
  反思：这思路不行啊，因为可能会不被check成功，但是前面的子字符串是可能存在结果的。这样子的话辅助函数就没啥意义了额。  
  解决办法： 这个时候再去递归主函数即可，效率意外的还可以。  
    
  反思：curLen问题的两种模板，在for循环时候，根据不同时候除法，这个时候注意出循环后还需要再处理一次  
    
tags:    
  -  curLen问题  
  -  递归  
