### description    
  We are stacking blocks to form a pyramid. Each block has a color which is a one letter string.  
    
  We are allowed to place any color block C on top of two adjacent blocks of colors A and B, if and only if ABC is an allowed triple.  
    
  We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed. Each allowed triple is represented as a string of length 3.  
    
  Return true if we can build the pyramid all the way to the top, otherwise false.  
    
  Example 1:  
    
  Input: bottom = "BCD", allowed = ["BCG", "CDE", "GEA", "FFF"]  
  Output: true  
  Explanation:  
  We can stack the pyramid like this:  
      A  
     / \  
    G   E  
   / \ / \  
  B   C   D  
    
  We are allowed to place G on top of B and C because BCG is an allowed triple.  Similarly, we can place E on top of C and D, then A on top of G and E.  
     
    
  Example 2:  
    
  Input: bottom = "AABA", allowed = ["AAA", "AAB", "ABA", "ABB", "BAC"]  
  Output: false  
  Explanation:  
  We can't stack the pyramid to the top.  
  Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.  
     
    
  Note:  
    
  bottom will be a string with length in range [2, 8].  
  allowed will have length in range [0, 200].  
  Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.  
### solution    
```    
  class Solution {  
        public boolean pyramidTransition(String bottom, List<String> allowed) {  
          Map<String, List<String>> map = new HashMap<>();  
          for (String a : allowed) {  
              String name = a.substring(0, 2);  
              if (!map.containsKey(name)) {  
                  map.put(name, new ArrayList<String>());  
              }  
              List<String> list = map.get(name);  
              list.add(a.substring(2));  
          }  
          return helper(bottom, map);  
      }  
    
      private boolean helper(String bottom, Map<String, List<String>> map) {  
          if (bottom.length() == 1) {  
              return true;  
          }  
          List<String> nextAll = new ArrayList<>();  
          dfs(bottom, map, new StringBuilder(), nextAll);  
          if(nextAll.size() == 0) return false;  
          for(String next: nextAll) {  
              if(helper(next, map)) {  
                  return true;  
              }  
          }  
          return false;  
      }  
    
      private void dfs(String bottom, Map<String, List<String>> map, StringBuilder prefix, List<String> res) {  
          if (prefix.length() == bottom.length() - 1) {  
              res.add(prefix.toString());  
              return;  
          }  
          int index = prefix.length();  
          String name = bottom.substring(index, index + 2);  
          if(map.containsKey(name)) {  
              List<String> next = map.get(name);  
              for(String n: next) {  
                  prefix.append(n);  
                  dfs(bottom, map, prefix, res);  
                  prefix.delete(prefix.length() - 1, prefix.length());  
              }  
          }  
      }  
    
  }  
```    
    
### 个人解读    
  又是一个遍历题目，感觉这题目可以使用DFS。然后可以递归的。  
    
  思路一：先把所有可能的结果全找出来，然后不停的遍历，7变6，6变5，每次把所有的可能性都列出来。  
  问题： 指数增长，数据量非常大。假设某一位有6种选择，那么就变成了5^6。  
    
  思路二：逆向考虑，根据输入参数，想办法从小到大增长。或者从左到右，一点点进行。  
  从左到右一点点进行很类似与使用Stack  
    
  反思，最后还是用了思路一，失败原因是自己一开始递归的方法写错了，只需要进行下一位填充，然而错写成for循环填充。  
    
tags:    
  -  DFS  
  -  回溯  
  -  字符串  
