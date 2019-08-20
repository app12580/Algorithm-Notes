### description    
  Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.  
    
  In one operation, you can choose any character of the string and change it to any other uppercase English character.  
    
  Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.  
    
  Note:  
  Both the string's length and k will not exceed 104.  
    
  Example 1:  
    
  Input:  
  s = "ABAB", k = 2  
    
  Output:  
  4  
    
  Explanation:  
  Replace the two 'A's with two 'B's or vice versa.  
     
    
  Example 2:  
    
  Input:  
  s = "AABABBA", k = 1  
    
  Output:  
  4  
    
  Explanation:  
  Replace the one 'A' in the middle with 'B' and form "AABBBBA".  
  The substring "BBBB" has the longest repeating letters, which is 4.  
    
    
### solution    
```    
  public int characterReplacement(String s, int k) {  
          int[] freq = new int[26];  
          int mostFreqLetter = 0;  
          int left = 0;  
          int max = 0;  
    
          for(int right = 0; right < s.length(); right++){  
              freq[s.charAt(right) - 'A']++;  
              mostFreqLetter = Math.max(mostFreqLetter, freq[s.charAt(right) - 'A']);  
    
              int lettersToChange = (right - left + 1) - mostFreqLetter;  
              if(lettersToChange > k){  
                  freq[s.charAt(left) - 'A']--;  
                  left++;  
                  mostFreqLetter--;       // 这一行可能会减错了  
                  //如果新的字符是最高频率，则会回收伏笔；而如果不是最高字母，会让情形更加恶化，删就删了。  
                  //但还是不建议这么做。。。  
              }  
    
              max = Math.max(max, right - left + 1);  
          }  
    
          return max;  
      }  
  
```    
    
### 个人解读    
   longest sub-string containing all repeating letters  
   官方翻译：找到包含重复字母的最长子串的长度。  
   翻译成人话：只含有单个字符(可以重复)的字符串。  
     
   思路：感觉还是要用到dfs的。还有一种思路就是dp。  
   dp的话不太好弄，因为需要同时多个指标：哪些位置变了？当前长度是多少？从多少到多少是重复字符。  
   所以还是DFS+回溯靠谱一些。  
     
   前面的思路有一个误区，就是先改动字母，然后再确定位置。然而可以先确定位置，然后再计算，而且可以通过统计结果，并不需要实际的换字母行为。  
      
    
tags:    
  -  滑动窗口  
  -  数学  
