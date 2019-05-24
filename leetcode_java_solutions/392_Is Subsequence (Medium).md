### description  
Given a string s and a string t, check if s is subsequence of t.  
  
You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).  
  
A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).  
  
Example 1:  
s = "abc", t = "ahbgdc"  
  
Return true.  
  
Example 2:  
s = "axc", t = "ahbgdc"  
  
Return false.  
### solution  
```  
class Solution {  
    public boolean isSubsequence(String s, String t) {  
         //判断s是否是t的子字符串  
        int i =0, j =0;  
        while(i < s.length() && j < t.length()) {  
            if(s.charAt(i) == t.charAt(j)) {  
                i++;  
            }  
            j++;  
        }  
        return i == s.length();  
    }  
}  
```  
  
### 个人解读  
[524](524_Longest%20Word%20in%20Dictionary%20through%20Deleting.md)的子问题，双指针问题，没什么好说的，按照模板写就好  
  
  
tags:  
  - 数组  
  - 双指针  
