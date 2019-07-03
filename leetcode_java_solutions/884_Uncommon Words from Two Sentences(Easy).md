### description    
  We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)  
    
  A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.  
    
  Return a list of all uncommon words.   
    
  You may return the list in any order.  
    
     
    
  Example 1:  
    
  Input: A = "this apple is sweet", B = "this apple is sour"  
  Output: ["sweet","sour"]  
  Example 2:  
    
  Input: A = "apple apple", B = "banana"  
  Output: ["banana"]  
     
    
  Note:  
    
  0 <= A.length <= 200  
  0 <= B.length <= 200  
  A and B both contain only spaces and lowercase letters.  
    
  返回两个字符串的差集  
### solution    
```    
  class Solution {  
      public String[] uncommonFromSentences(String A, String B) {  
          List<String> list = new ArrayList<>();  
          Map<String, Integer> map = new HashMap<>();  
          for(String a: A.split(" ")) {  
              map.put(a, map.getOrDefault(a, 0) + 1);  
          }  
          for(String a: B.split(" ")) {  
              map.put(a, map.getOrDefault(a, 0) + 1);  
          }  
          for(Map.Entry<String, Integer> entry: map.entrySet()) {  
              if(entry.getValue() == 1) {  
                  list.add(entry.getKey());  
              }  
          }  
            
          return list.toArray(new String[list.size()]);  
      }  
  }  
```    
    
### 个人解读    
  如果一个单词在其中一个句子中只出现一次，在另一个句子中却没有出现，那么这个单词就是不常见的。  
  等价于： 一个单词在两个字符串中只出现过一次。  
    
    
tags:    
  -  数学  
  -  hash表  
