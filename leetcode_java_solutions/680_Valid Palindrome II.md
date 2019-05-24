### description  
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.  
  
Example 1:  
Input: "aba"  
Output: True  
Example 2:  
Input: "abca"  
Output: True  
Explanation: You could delete the character 'c'.  
Note:  
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.  
### solution  
```  
class Solution {  
     public boolean validPalindrome(String s) {  
        int i = 0;  
        int j = s.length() - 1;  
        while (i < j) {  
            if(s.charAt(i) != s.charAt(j)) {  
                return helper(s, i + 1, j) || helper(s, i, j - 1);  
            }  
            i++;  
            j--;  
        }  
        return true;  
    }  
  
    public boolean helper(String s, int i, int j) {  
        while(i < j) {  
            if(s.charAt(i) != s.charAt(j)) {  
                return false;  
            }  
            i++;  
            j--;  
        }  
        return true;  
    }  
}  
```  
  
### 个人解读  
很多算法关键在于中间方法的设计，以及能够满足要求的推论(DP更加常见)  
本题问题：判断一个字符串，能否删除0个或1个字符，成为一个回文字符串  
实现方式：先判断如果不删除字符时候，双指针会运行到哪里，然后遇到字符不对称的时候:  
现在程序已经将字符串分为了三个部分，前中后，其中前后为对称的，而中间的两端为不同字符，此时如果要删除一个字符的话，要么删除中间部分的左端或者右端  
复用思想：尽可能用上字符串是回文这一方法  
问题转化：  
    字符串是回文||字符串左右对称，中间包含了一段s,s的[start, end-1]或者[start+1,end-1]是回文  
  
延伸：  
    如果可以删除两个字符串呢？  
    + 方法一： 双指针遍历，然后遇到字符不同的地方时候，判断中间的字符串[start, end-1]或者[start+1,end-1]如果删除一个字符串能否成为回文字符串  
      
  
tags:  
  - 数组  
  - 双指针  
  - 字符串  
  - 工具方法设计    
