### description    
  Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.  
    
  Example 1:  
    
  Input: ["abcw","baz","foo","bar","xtfn","abcdef"]  
  Output: 16   
  Explanation: The two words can be "abcw", "xtfn".  
  Example 2:  
    
  Input: ["a","ab","abc","d","cd","bcd","abcd"]  
  Output: 4   
  Explanation: The two words can be "ab", "cd".  
  Example 3:  
    
  Input: ["a","aa","aaa","aaaa"]  
  Output: 0   
  Explanation: No such pair of words.  
    
  找到字符串长度乘积的最大值，要求这两个字符串不包含相同字母。  
### solution    
```    
  class Solution {  
      public int maxProduct(String[] words) {  
           int max = 0;  
          int[] val = new int[words.length];  
          for(int i = 0; i < words.length; i++) {  
              String s = words[i];  
              for(int j = 0; j < s.length(); j++) {  
                  char ch = s.charAt(j);  
                  val[i] |= 1<<(ch - 'a');  
              }  
          }  
    
          for(int i = 0; i < val.length; i++) {  
              for(int j = i+1; j < val.length; j++) {  
                  if((val[i]&val[j]) == 0) {  
                      max = Math.max(max, words[i].length() * words[j].length());  
                  }  
              }  
          }  
          return max;  
      }  
  }  
```    
    
### 个人解读    
  把一个int当成一个32位的数组，好处是，可以方便的使用位运算来当做数组之间的运算。而且更巧妙的是，int有32位，比英文字母26位要多。    
    
    
tags:    
  -  数组  
  -  位运算  
  -  骚操作  
