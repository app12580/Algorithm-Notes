### description    
  To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same size).  
    
  Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not, we do nothing.  
    
  For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff", then because "cd" starts at position 2 in the original string S, we will replace it with "ffff".  
    
  Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee", as well as another replacement operation i = 2, x = "ec", y = "ffff", this second operation does nothing because in the original string S[2] = 'c', which doesn't match x[0] = 'e'.  
    
  All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.  
    
  Example 1:  
    
  Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]  
  Output: "eeebffff"  
  Explanation: "a" starts at index 0 in S, so it's replaced by "eee".  
  "cd" starts at index 2 in S, so it's replaced by "ffff".  
  Example 2:  
    
  Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]  
  Output: "eeecd"  
  Explanation: "ab" starts at index 0 in S, so it's replaced by "eee".   
  "ec" doesn't starts at index 2 in the original S, so we do nothing.  
  Notes:  
    
  0 <= indexes.length = sources.length = targets.length <= 100  
  0 < indexes[i] < S.length <= 1000  
  All characters in given inputs are lowercase letters.  
    
### solution    
```    
  class Solution {  
      public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {  
          Map<Integer, String> map = new HashMap<>();  
          Map<Integer, Integer> map2 = new HashMap<>();  
          for(int i = 0; i < indexes.length; i++) {  
              int index = indexes[i];  
              if(check(S, index, sources[i])) {  
                  map.put(index, targets[i]);  
                  map2.put(index, sources[i].length());  
              }  
          }  
          StringBuilder builder = new StringBuilder();  
          for(int i = 0; i < S.length(); i++) {  
              if(map.containsKey(i)) {  
                  builder.append(map.get(i));  
                  i+=map2.get(i) - 1;  
              } else {  
                  builder.append(S.charAt(i));  
              }  
          }  
          return builder.toString();  
      }  
    
      private boolean check(String s, int index, String source) {  
          if(index + source.length() > s.length()) return false;  
          for(int i = index; i < index + source.length(); i++) {  
              if(s.charAt(i) != source.charAt(i - index)) return false;  
          }  
          return true;  
      }  
    
  }  
```    
    
### 个人解读    
  主要思路是保留原来内容，新造一个StringBuilder，然后类似双指针的去遍历，然后获取新的字符串。  
  妈的，和前面那个新21点牌根本不是一个层次的题目。。。  
    
  没有注意到索引不是按顺序的情况，另外题目里面没有说，  
  如果source被替换了会发生什么事情。  //input排除了  
    
  总结：记录所有需要替换的操作，然后再统一执行，需要注意替换后索引i的偏移。  
    
tags:    
  -  字符串  
  -  预处理  
