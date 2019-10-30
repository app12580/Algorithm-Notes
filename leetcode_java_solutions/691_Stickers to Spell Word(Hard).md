### description    
  We are given N different types of stickers. Each sticker has a lowercase English word on it.  
    
  You would like to spell out the given target string by cutting individual letters from your collection of stickers and rearranging them.  
    
  You can use each sticker more than once if you want, and you have infinite quantities of each sticker.  
    
  What is the minimum number of stickers that you need to spell out the target? If the task is impossible, return -1.  
    
  Example 1:  
    
  Input:  
    
  ["with", "example", "science"], "thehat"  
  Output:  
    
  3  
  Explanation:  
    
  We can use 2 "with" stickers, and 1 "example" sticker.  
  After cutting and rearrange the letters of those stickers, we can form the target "thehat".  
  Also, this is the minimum number of stickers necessary to form the target string.  
  Example 2:  
    
  Input:  
    
  ["notice", "possible"], "basicbasic"  
  Output:  
    
  -1  
  Explanation:  
    
  We can't form the target "basicbasic" from cutting letters from the given stickers.  
  Note:  
    
  stickers has length in the range [1, 50].  
  stickers consists of lowercase English words (without apostrophes).  
  target has length in the range [1, 15], and consists of lowercase English letters.  
  In all test cases, all words were chosen randomly from the 1000 most common US English words, and the target was chosen as a concatenation of two random words.  
  The time limit may be more challenging than usual. It is expected that a 50 sticker test case can be solved within 35ms on average.  
    
### solution    
```    
Runtime: 23 ms, faster than 78.69% of Java online submissions for Stickers to Spell Word.  
Memory Usage: 41.4 MB, less than 16.67% of Java online submissions for Stickers to Spell Word.  
  
  class Solution {  
      public int minStickers(String[] stickers, String target) {  
          int m = stickers.length;  
          int[][] mp = new int[m][26];  
          Map<String, Integer> dp = new HashMap<>();  
          for (int i = 0; i < m; i++)   
              for (char c:stickers[i].toCharArray()) mp[i][c-'a']++;  
          dp.put("", 0);  
          return helper(dp, mp, target);  
      }  
      private int helper(Map<String, Integer> dp, int[][] mp, String target) {  
          if (dp.containsKey(target)) return dp.get(target);  
          int ans = Integer.MAX_VALUE, n = mp.length;  
          int[] tar = new int[26];  
          for (char c:target.toCharArray()) tar[c-'a']++;  
          // try every sticker  
          for (int i = 0; i < n; i++) {  
              // optimization  
              if (mp[i][target.charAt(0)-'a'] == 0) continue;  
              StringBuilder sb = new StringBuilder();  
              // apply a sticker on every character a-z  
              for (int j = 0; j < 26; j++) {  
                  if (tar[j] > 0 )   
                      for (int k = 0; k < Math.max(0, tar[j]-mp[i][j]); k++)  
                          sb.append((char)('a'+j));  
              }  
              String s = sb.toString();  
              int tmp = helper(dp, mp, s);  
              if (tmp != -1) ans = Math.min(ans, 1+tmp);  
          }  
          dp.put(target, ans == Integer.MAX_VALUE? -1:ans);  
          return dp.get(target);  
      }  
  }  
```    
    
### 个人解读    
  很少见的在题目里直接强调对时间效率要求很高。。。  
    
  思路一：  
  先把target处理一下每个字符出现多少次。用一个boolean[26]来处理-1的情况，之后就是DFS了。。。  
  DFS优化就是DP了。问题是如何建模？  
    
  DP建模思路：  
  1、根据target的数量来    (大的字符串等于小的字符串拼接， 拼接后可能会溢出，需要减少数字)  
  2、根据已有的sticker来   (前两个贴纸，前三个贴纸这样子， 问题是每个贴纸都有26个字符，很难控制)  
  如果根据sticker的比较，类似于背包问题的那种优化。那咋办嘛？  
    
  难道要根据target字符串拼接，同时记录下来溢出字符个数？然后同时还要记录下来有哪些贴纸，然后根据这两个信息来看，新加入的贴纸是否能忽略？    
    
  反思：  
  根据字符串除了拼接，还有递进和零件式。  
  拜这题目所赐，总结了DP的常用模式：递进式、拼接式、零件式  
    
  动态规划现在有个技巧，一维不够就想办法二维  
    
    
    
tags:    
  -  DP  
  -  字符串解析  
