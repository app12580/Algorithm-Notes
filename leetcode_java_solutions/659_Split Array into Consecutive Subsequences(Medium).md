### description      
  Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more subsequences such that each subsequence consists of consecutive integers and has length at least 3.    
      
       
      
  Example 1:    
      
  Input: [1,2,3,3,4,5]    
  Output: True    
  Explanation:    
  You can split them into two consecutive subsequences :     
  1, 2, 3    
  3, 4, 5    
      
  Example 2:    
      
  Input: [1,2,3,3,4,4,5,5]    
  Output: True    
  Explanation:    
  You can split them into two consecutive subsequences :     
  1, 2, 3, 4, 5    
  3, 4, 5    
      
  Example 3:    
      
  Input: [1,2,3,4,4,5]    
  Output: False    
### solution      
```      
  class Solution {    
      public boolean isPossible(int[] nums) {    
      Map<Integer, Integer> freq = new HashMap<>(), appendfreq = new HashMap<>();    
      for (int i : nums) freq.put(i, freq.getOrDefault(i,0) + 1);    
      for (int i : nums) {    
          if (freq.get(i) == 0) continue;    
          else if (appendfreq.getOrDefault(i,0) > 0) {    
              appendfreq.put(i, appendfreq.get(i) - 1);     //这个map表示前面开头的，key表示，可以把key值放进前面的链里面    
              appendfreq.put(i+1, appendfreq.getOrDefault(i+1,0) + 1);    
          }       
          else if (freq.getOrDefault(i+1,0) > 0 && freq.getOrDefault(i+2,0) > 0) {    
              freq.put(i+1, freq.get(i+1) - 1);     //这个map表示未安排的数量    
              freq.put(i+2, freq.get(i+2) - 1);    
              appendfreq.put(i+3, appendfreq.getOrDefault(i+3,0) + 1);    
          }    
          else return false;    
          freq.put(i, freq.get(i) - 1);    
      }    
      return true;    
  }    
  }    
```      
      
### 个人解读      
  刚开始想着，这种问题无非就是DFS+回溯了，但是这样执行很麻烦，设计动态的数组个数。    
      
  后来发现，可以统计相同数字的个数，然后进行排列，从中看看有没有什么数学规律    
      
  123345 可以 翻译成 11211    
  然而在处理字符串的时候，还是绕不开dfs。    
      
  总结：    
  1、贪婪算法，能放前面的就放前面    
  2、通过两个map表示当前进度，一个表示开头，一个表示结尾    
  3、遍历数组，然后"if (freq.get(i) == 0) continue; "这一行也很精髓  
      
      
tags:      
  -  DFS+回溯    
  -  重点数学    
  -  贪婪算法    
