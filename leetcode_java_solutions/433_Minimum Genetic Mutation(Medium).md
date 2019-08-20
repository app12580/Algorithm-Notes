### description    
  A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".  
    
  Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.  
    
  For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.  
    
  Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.  
    
  Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.  
    
  Note:  
    
  Starting point is assumed to be valid, so it might not be included in the bank.  
  If multiple mutations are needed, all mutations during in the sequence must be valid.  
  You may assume start and end string is not the same.  
     
    
  Example 1:  
    
  start: "AACCGGTT"  
  end:   "AACCGGTA"  
  bank: ["AACCGGTA"]  
    
  return: 1  
     
    
  Example 2:  
    
  start: "AACCGGTT"  
  end:   "AAACGGTA"  
  bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]  
    
  return: 2  
     
    
  Example 3:  
    
  start: "AAAAACCC"  
  end:   "AACCCCCC"  
  bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]  
    
  return: 3  
### solution    
```    
  
// 方法一： BFS  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Minimum Genetic Mutation.  
Memory Usage: 33.9 MB, less than 100.00% of Java online submissions for Minimum Genetic Mutation.  
  
  class Solution {  
      public int minMutation(String start, String end, String[] bank) {  
          int res = 0;  
          Queue<String> queue = new LinkedList<>();  
          List<String> list = new ArrayList<>(Arrays.asList(bank));  
          queue.offer(start);  
          while(!queue.isEmpty()) {  
              res++;  
              int size = queue.size();  
              for(int i = 0; i < size; i++) {  
                  String cur = queue.poll();  
                  Iterator<String> iterator = list.iterator();  
                  while(iterator.hasNext()) {  
                      String next = iterator.next();  
                      if(oneDistance(next, cur)) {  
                          if(next.equals(end)) {  
                              return res;  
                          }  
                          queue.offer(next);  
                          iterator.remove();  
                      }  
                  }  
              }  
          }  
          return -1;  
      }  
        
      public boolean oneDistance(String s1, String s2) {  
          if(s1.length() != s2.length()) return false;  
          boolean hasOne = false;  
          for(int i = 0; i < s1.length(); i++) {  
              if(s1.charAt(i) != s2.charAt(i)) {  
                  if(hasOne) {  
                      return false;  
                  } else {  
                      hasOne = true;  
                  }  
              }  
          }  
          return hasOne;  
      }  
  }  
```    
    
### 个人解读    
  感觉这是一个遍历问题，因为求的是最小路径，所以感觉BFS要比DFS更好一些。  
  因为起点可能不在合法序列中，所以是要从start开始，然后BFS遍历所有的合法数组。  
    
  效率还可以嗷~  
      
tags:    
  -  BFS  
  -  字符串  
