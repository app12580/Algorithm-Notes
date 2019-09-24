### description    
  We have a string S of lowercase letters, and an integer array shifts.  
    
  Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a').   
    
  For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.  
    
  Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.  
    
  Return the final string after all such shifts to S are applied.  
    
  Example 1:  
    
  Input: S = "abc", shifts = [3,5,9]  
  Output: "rpl"  
  Explanation:   
  We start with "abc".  
  After shifting the first 1 letters of S by 3, we have "dbc".  
  After shifting the first 2 letters of S by 5, we have "igc".  
  After shifting the first 3 letters of S by 9, we have "rpl", the answer.  
  Note:  
    
  1 <= S.length = shifts.length <= 20000  
  0 <= shifts[i] <= 10 ^ 9  
### solution    
```    
//方法一： 效率低  
  class Solution {  
      public String shiftingLetters(String S, int[] shifts) {  
          long[] counts = new long[shifts.length];  
          for(int i = 0; i < shifts.length; i++) {  
              for(int j = 0; j <= i; j++) {  
                  counts[j] += shifts[i];  
              }  
          }  
          char[] chars = S.toCharArray();  
          for(int i = 0; i < chars.length; i++) {  
              chars[i] = helper(chars[i], counts[i]);  
          }  
          return new String(chars);  
      }  
    
      private char helper(char aChar, long count) {  
          return (char)((count + aChar - 'a') % 26 + 'a');  
      }  
  }  
    
  // 方法二 优化sum计算  
  class Solution {  
      public String shiftingLetters(String S, int[] shifts) {  
          long[] counts = new long[shifts.length];  
          long sum = 0;  
          for(int i = shifts.length - 1; i >= 0; i--) {  
              sum += shifts[i];  
              counts[i] = sum;  
          }  
          char[] chars = S.toCharArray();  
          for(int i = 0; i < chars.length; i++) {  
              chars[i] = helper(chars[i], counts[i]);  
          }  
          return new String(chars);  
      }  
    
      private char helper(char aChar, long count) {  
          return (char)((count + aChar - 'a') % 26 + 'a');  
      }  
  }  
```    
    
### 个人解读    
  流水账，先计算每个点要偏移多少，然后再用辅助函数。  
    
  TLE  
  ```  
  class Solution {  
      public String shiftingLetters(String S, int[] shifts) {  
          int[] counts = new int[shifts.length];  
          for(int i = 0; i < shifts.length; i++) {  
              for(int j = 0; j <= i; j++) {  
                   counts[j] = shifts[i] % 26 + counts[j] % 26;  
              }  
          }  
          char[] chars = S.toCharArray();  
          for(int i = 0; i < chars.length; i++) {  
              chars[i] = helper(chars[i], counts[i]);  
          }  
          return new String(chars);  
      }  
    
      private char helper(char aChar, int count) {  
          int c = (aChar - 'a');  
          return (char)((c + count) % 26 + 'a');  
      }  
  }  
  ```  
    
  不知道为什么会超时，感觉每一步都是必要的，无论是前面的计算求和，还是后面的计算偏移量，都是必要的计算步骤。  
    
  {3,5,9}  
  3  
  5 5  
  9 9 9   
  还是说%26这一步出了问题？  
  果然，改成long以后就快了很多。  
    
  反思：  
  求和计算重复了。只需要知道：  
  9, 9+5, 9+5+3即可，并不需要每个节点都算一次。  
    
tags:    
  -  int超限  
  -  数学  
