### description    
  We are given two arrays A and B of words.  Each word is a string of lowercase letters.  
    
  Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.  For example, "wrr" is a subset of "warrior", but is not a subset of "world".  
    
  Now say a word a from A is universal if for every b in B, b is a subset of a.   
    
  Return a list of all universal words in A.  You can return the words in any order.  
    
     
    
  Example 1:  
    
  Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]  
  Output: ["facebook","google","leetcode"]  
  Example 2:  
    
  Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]  
  Output: ["apple","google","leetcode"]  
  Example 3:  
    
  Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]  
  Output: ["facebook","google"]  
  Example 4:  
    
  Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]  
  Output: ["google","leetcode"]  
  Example 5:  
    
  Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]  
  Output: ["facebook","leetcode"]  
     
    
  Note:  
    
  1 <= A.length, B.length <= 10000  
  1 <= A[i].length, B[i].length <= 10  
  A[i] and B[i] consist only of lowercase letters.  
  All words in A[i] are unique: there isn't i != j with A[i] == A[j].  
### solution    
```    
Runtime: 53 ms, faster than 14.67% of Java online submissions for Word Subsets.  
Memory Usage: 47.9 MB, less than 100.00% of Java online submissions for Word Subsets.  
  
  class Solution {  
     public List<String> wordSubsets(String[] A, String[] B) {  
          int[] need = new int[26];  
          int[] curB = new int[26];  
          for(String b: B) {  
              Arrays.fill(curB, 0);       
              //上面一行不如int[] curB = new int[26];  
              for(char ch: b.toCharArray()) {  
                  curB[ch - 'a']++;  
              }  
              for(int i = 0; i < 26; i++) {  
                  need[i] = Math.max(need[i], curB[i]);  
              }  
          }  
          List<String> res = new ArrayList<>();  
          for(String a: A) {  
              if(check(a, need)) {  
                  res.add(a);  
              }  
          }  
          return res;  
      }  
    
      private boolean check(String a, int[] need) {  
          int[] arr = new int[26];  
          for(char ch: a.toCharArray()  ) {  
              arr[ch - 'a']++;  
          }  
          for(int i = 0; i < 26; i++) {  
              if(need[i] > arr[i]) return false;  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  第一反应流水账，需要A中比较B的每一个，然后就意识到，只需要获取b的每一个字符的最大值即可。  
  写完发现效率有点低，应该是哪里有可以优化的地方。。  
    
tags:    
  -  字符串解析  
