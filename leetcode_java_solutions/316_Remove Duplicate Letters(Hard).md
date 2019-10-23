### description    
  Given a string which contains only lowercase letters, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.  
    
  Example 1:  
    
  Input: "bcabc"  
  Output: "abc"  
  Example 2:  
    
  Input: "cbacdcbc"  
  Output: "acdb"  
### solution    
```    
// 方法一： 贪婪算法+ 递归  
Runtime: 15 ms, faster than 18.21% of Java online submissions for Remove Duplicate Letters.  
Memory Usage: 38.1 MB, less than 16.67% of Java online submissions for Remove Duplicate Letters.  
  public class Solution {  
      public String removeDuplicateLetters(String s) {  
          int[] cnt = new int[26];  
          int pos = 0; // the position for the smallest s[i]  
          for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;  
          for (int i = 0; i < s.length(); i++) {  
              if (s.charAt(i) < s.charAt(pos)) pos = i;  
              if (--cnt[s.charAt(i) - 'a'] == 0) break;  
          }  
          return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));  
      }  
  }  
```    
    
### 个人解读    
    
  思路一：  
  使用标记法，有限数组  
  三个标记数组：出现次数count，是否已经加上了flag,最早出现索引位置。  
  宣告破产。"cbacdcbc"  "acdb" 里面c的处理     
    
  思路二：  
  遍历字符，从小到大，让每个字符尽可能的小  
  标记量preMaxIndex  
  if(count == 1) {  
    那么这个字符只有这一个位置  
  } else {  
     如果存在preMaxIndex以后的，就让其为preMaxIndex以后的第一个，否则，成为上一个preMaIndex的后面一个。  
     考虑使用栈实现  
  }  
  错误点一：  
  "cbacdcbc"  结果是acdb 而不是adbc，c过早的决定在了b的后面  
    
  思路三：  
  所以说肯定还是要按照原来字符串的顺序进行决策的  
  思路一和思路二都遇到了同样的问题，在遇到c的时候，因为觉得后面还有个b所以就未处理，所以就不予处置，没想到因此让d抢占了好的位置。  
  难道只能遍历或者DP了么？  
    
  参考答案总结：  
  1、贪婪思想：从左往右，找出最小字符的最左索引，两种情况：如果遇到其他字符count只有1个了，直接让这个最小的处理；如果顺利运行到了最后，则返回最小值索引，剩下的重新交给递归处理。  
    
tags:    
  -  贪婪算法  
  -  字符串  
  -  递归  
