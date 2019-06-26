### description  
  Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
  
  Example 1:
  Input: "Let's take LeetCode contest"
  Output: "s'teL ekat edoCteeL tsetnoc"
  Note: In the string, each word is separated by single space and there will not be any extra space in the string.
  
  反转每个单词
### solution  
```  
  class Solution {
      public String reverseWords(String s) {
          char[] chars = s.toCharArray();
  
          int i = 0;
          int j = 0;
          while (j < s.length()) {
              //先让i归位
              while (i < s.length() && chars[i] == ' ') {
                  i++;
              }
              if (i == s.length() - 1) break;
              //此时i为第一个字母
              j = i + 1;
              while (j < s.length() && chars[j] != ' ') {
                  j++;
              }
              swap(chars, i, j - 1);
              i = j;
  
          }
  
          return new String(chars);
      }
      
      private void swap(char[] chars, int i, int j) {
          while(i < j) {
              char t = chars[i];
              chars[i] =chars[j];
              chars[j] = t;
              i++;
              j--;
          }
      }
  }
```  
  
### 个人解读  
  双指针问题
  采用策略：内部通过while调整指针的位置
  
  
tags:  
  -  字符串
  -  双指针
