### description    
  You are given two strings s and t of the same length. You want to change s to t. Changing the i-th character of s to i-th character of t costs |s[i] - t[i]| that is, the absolute difference between the ASCII values of the characters.  
    
  You are also given an integer maxCost.  
    
  Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of twith a cost less than or equal to maxCost.  
    
  If there is no substring from s that can be changed to its corresponding substring from t, return 0.  
    
     
    
  Example 1:  
    
  Input: s = "abcd", t = "bcdf", maxCost = 3  
  Output: 3  
  Explanation: "abc" of s can change to "bcd". That costs 3, so the maximum length is 3.  
  Example 2:  
    
  Input: s = "abcd", t = "cdef", maxCost = 3  
  Output: 1  
  Explanation: Each character in s costs 2 to change to charactor in t, so the maximum length is 1.  
  Example 3:  
    
  Input: s = "abcd", t = "acde", maxCost = 0  
  Output: 1  
  Explanation: You can't make any change, so the maximum length is 1.  
     
    
  Constraints:  
    
  1 <= s.length, t.length <= 10^5  
  0 <= maxCost <= 10^6  
  s and t only contain lower case English letters.  
### solution    
```    
  class Solution {  
     public int equalSubstring(String s, String t, int maxCost) {  
          int[] diffArr = new int[s.length()];  
          for(int i = 0; i < s.length(); i++) {  
              int diff = Math.abs(s.charAt(i) - t.charAt(i));  
              diffArr[i] = diff;  
          }  
          int left = 0;  
          int res = 0;  
          int curPro = diffArr[0];  
          if(curPro <= maxCost) res = 1;  
          for(int right = 1; right < diffArr.length; right++) {  
              curPro += diffArr[right];  
              while(curPro > maxCost && left <= right) {  
                  curPro -= diffArr[left++];  
              }  
              res = Math.max(res, right - left + 1);  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  与[713](713_Subarray%20Product%20Less%20Than%20K(Medium).md)一样的问题。  
  使用滑动窗口解决，关节在于0点索引处需要单独处理。  
    
tags:    
  -  滑动窗口  
  -  字符串  
