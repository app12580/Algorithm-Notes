### description      
  Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.    
      
  Example 1:    
  Input: 2736    
  Output: 7236    
  Explanation: Swap the number 2 and the number 7.    
  Example 2:    
  Input: 9973    
  Output: 9973    
  Explanation: No swap.    
  Note:    
  The given number is in the range [0, 108]    
### solution      
```      
  
Runtime: 1 ms, faster than 33.35% of Java online submissions for Maximum Swap.  
Memory Usage: 33.2 MB, less than 25.00% of Java online submissions for Maximum Swap.  
  
  
  class Solution {    
        public int maximumSwap(int num) {    
          Map<Integer, Integer> map = new HashMap<>();    
          char[] chars = (num + "").toCharArray();    
          for(int i = chars.length - 1; i >= 0; i--) {    
              int cur = chars[i] - '0';    
              if(!map.containsKey(cur)) {    
                  map.put(cur, i);    
              }    
          }    
              
          for(int i = 0; i < chars.length - 1; i++) {    
              int cur = chars[i] - '0';    
              for(int j = 9; j > cur; j--) {    
                  if(!map.containsKey(j)) {    
                      continue;    
                  }    
                  if(map.get(j) > i) {    
                      swap(chars, i, map.get(j));    
                      return Integer.valueOf(new String(chars));    
                  }    
              }    
          }    
          return num;    
       }    
      
      private void swap(char[] chars, int i, int j) {    
          char t = chars[i];    
          chars[i] = chars[j];    
          chars[j] = t;    
      }    
      
  }    
```      
      
### 个人解读      
  把第一个位置换成后面起第一个最大的。    
  如果直接做的话，会需要各种讨论，如果首位最大了怎么办？如果首位是最大值，那么需要看第二位，而第二位又会出现同样的情况。    
      
  感觉需要一个Map，记录每个数字最后出现的索引了。记录完map之后，再从左开始遍历。    
      
tags:      
  -  数字排列    
