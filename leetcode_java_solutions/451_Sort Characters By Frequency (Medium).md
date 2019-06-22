### description    
  Given a string, sort it in decreasing order based on the frequency of characters.  
    
  Example 1:  
    
  Input:  
  "tree"  
    
  Output:  
  "eert"  
    
  Explanation:  
  'e' appears twice while 'r' and 't' both appear once.  
  So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.  
  Example 2:  
    
  Input:  
  "cccaaa"  
    
  Output:  
  "cccaaa"  
    
  Explanation:  
  Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.  
  Note that "cacaca" is incorrect, as the same characters must be together.  
  Example 3:  
    
  Input:  
  "Aabb"  
    
  Output:  
  "bbAa"  
    
  Explanation:  
  "bbaA" is also a valid answer, but "Aabb" is incorrect.  
  Note that 'A' and 'a' are treated as two different characters.  
    
  按照字符出现次数对字符串排序  
### solution    
```    
  
Runtime: 14 ms, faster than 89.34% of Java online submissions for Sort Characters By Frequency.  
Memory Usage: 38.4 MB, less than 92.08% of Java online submissions for Sort Characters By Frequency.  
    
  class Solution {  
       public String frequencySort(String s) {  
          Map<Character, Integer> map = new HashMap<>();  
          for(char c: s.toCharArray()) {  
              map.put(c, map.getOrDefault(c, 0) + 1);  
          }  
          List<Character>[] bucket = new ArrayList[s.length() + 1];  
          for(Character key: map.keySet()) {  
              Integer frequency = map.get(key);  
              if(bucket[frequency] == null) {  
                  bucket[frequency] = new ArrayList<>();  
              }  
              bucket[frequency].add(key);  
          }  
          StringBuilder builder = new StringBuilder();  
          for(int i = s.length(); i > 0; i--) {  
              if(bucket[i] == null) {  
                  continue;  
              }  
              List<Character> characters = bucket[i];  
              for(char c: characters) {  
                  for(int j = 0; j < i; j++) {  
                      builder.append(c);  
                  }  
              }  
          }  
          return builder.toString();  
            
      }  
  }  
    
    
```    
    
### 个人解读    
  预处理，先遍历全部，获取每个字符出现次数，同时存储在一个List数组中。  
  然后从大到小遍历数组，输出最终结果。  
  Kth问题，并没有什么特别神奇的解法。  
    
tags:    
  -  Kth    
