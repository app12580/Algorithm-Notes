### description    
  You are given a license key represented as a string S which consists only alphanumeric character and dashes. The string is separated into N+1 groups by N dashes.  
    
  Given a number K, we would want to reformat the strings such that each group contains exactly K characters, except for the first group which could be shorter than K, but still must contain at least one character. Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.  
    
  Given a non-empty string S and a number K, format the string according to the rules described above.  
    
  Example 1:  
  Input: S = "5F3Z-2e-9-w", K = 4  
    
  Output: "5F3Z-2E9W"  
    
  Explanation: The string S has been split into two parts, each part has 4 characters.  
  Note that the two extra dashes are not needed and can be removed.  
  Example 2:  
  Input: S = "2-5g-3-J", K = 2  
    
  Output: "2-5G-3J"  
    
  Explanation: The string S has been split into three parts, each part has 2 characters except the first part as it could be shorter as mentioned above.  
  Note:  
  The length of string S will not exceed 12,000, and K is a positive integer.  
  String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).  
  String S is non-empty.  
    
  输入两个参数String S和int K，根据K，将S格式化，返回格式化后的字符串  
  格式化要求：  
    把S用"-"将字符串的所有字母和数字分割  
    第一部分可以小于等于K，剩下部分必须长度等于K  
    所有小写转化成大写  
    
### solution    
```    
  class Solution {  
      public String licenseKeyFormatting(String S, int K) {  
         StringBuilder builder = new StringBuilder();  
         int cur = 0;  
         for(int i = S.length() - 1; i >= 0; i--) {  
             char ch = S.charAt(i);  
             if(ch == '-') {  
                 continue;  
             }  
             ch = Character.toUpperCase(ch);  
             if(builder.length() != 0 && cur % K == 0) {  
                 builder.append('-');  
                 cur = 0;  
             }  
             builder.append(ch);  
             cur++;  
         }  
          return builder.reverse().toString();    
      }  
  }  
```    
    
### 个人解读    
  因为没有效率要求，先通过数学办法，获取总字符长度，然后计算第一部分的长度，然后循环即可。  
  因为tail是否为0需要分别判断，所以还是倒叙处理容易一些，最后数组反转一下。  
    
tags:    
  -  字符串  
  -  反转  
