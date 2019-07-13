### description    
  All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.  
    
  Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.  
    
  Example:  
    
  Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"  
    
  Output: ["AAAAACCCCC", "CCCCCAAAAA"]  
### solution    
```    
Runtime: 19 ms, faster than 60.58% of Java online submissions for Repeated DNA Sequences.  
Memory Usage: 46.1 MB, less than 68.87% of Java online submissions for Repeated DNA Sequences.  
  
  class Solution {  
      public List<String> findRepeatedDnaSequences(String s) {  
          Set<String> res = new HashSet<>();  
          Set<String> set = new HashSet<>();  
          // 0...9..10..11  
          for(int i = 0; i < s.length() - 9; i++) {  
              String cur = new String(s.substring(i, i + 10));  
              if(set.contains(cur)) {  
                  res.add(cur);  
              } else {  
                  set.add(cur);  
              }  
          }  
          List<String> list = new ArrayList<>(res.size());  
          for(String ss: res) {  
              list.add(ss);  
          }  
          return list;  
      }  
  }  
```    
    
### 个人解读    
  使用hash来存储，就是不知道这种效率会怎么样。  
  效率还行吧。  
    
tags:    
  -  流水账  
  -  Hash表  
  -  字符串  
