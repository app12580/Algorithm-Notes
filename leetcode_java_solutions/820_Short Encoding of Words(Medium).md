### description    
  Given a list of words, we may encode it by writing a reference string S and a list of indexes A.  
    
  For example, if the list of words is ["time", "me", "bell"], we can write it as S = "time#bell#" and indexes = [0, 2, 5].  
    
  Then for each index, we will recover the word by reading from the reference string from that index until we reach a "#" character.  
    
  What is the length of the shortest reference string S possible that encodes the given words?  
    
  Example:  
    
  Input: words = ["time", "me", "bell"]  
  Output: 10  
  Explanation: S = "time#bell#" and indexes = [0, 2, 5].  
     
    
  Note:  
    
  1 <= words.length <= 2000.  
  1 <= words[i].length <= 7.  
  Each word has only lowercase letters.  
### solution    
```    
  class Solution {  
      public int minimumLengthEncoding(String[] words) {  
          Set<String> s = new HashSet<>(Arrays.asList(words));  
          for (String w : words)  
              for (int i = 1; i < w.length(); ++i)  
                  s.remove(w.substring(i));  
          int res = 0;  
          for (String w : s) res += w.length() + 1;  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  因为会出现字符串包含的情况，所以每次都需要去判断是否之前出现过。初次打算是通过长短来排序。  
    
  大数据失败算法，不知道为啥不行。  
  另外反思一下，这种大量重复的计算量，要优先考虑hash的。  
    
  反思：  
  由于本题目遇到#终止，所以删除的substring只有到length-1的。  
  ```  
  class Solution {  
      public int minimumLengthEncoding(String[] words) {  
          Arrays.sort(words, new Comparator<String>() {  
              @Override  
              public int compare(String o1, String o2) {  
                  return o2.length() - o1.length();  
              }  
          });  
          StringBuilder builder = new StringBuilder();  
          int res = 0;  
          for(int i = 0; i < words.length; i++){  
              String cur = words[i];  
              boolean flag = true;  
              for(int j = 0; j < i; j++) {  
                  String pre = words[j];  
                  if(pre.indexOf(cur) >= 0) {  
                      flag = false;  
                      break;  
                  }  
              }  
              if(flag) {  
                  res += cur.length() + 1;  
              }  
          }  
          return res;   
      }  
  }  
  ```  
    
tags:    
  -  字符串  
