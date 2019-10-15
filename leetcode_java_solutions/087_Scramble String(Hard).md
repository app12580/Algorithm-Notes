### description    
  Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.  
    
  Below is one possible representation of s1 = "great":  
    
      great  
     /    \  
    gr    eat  
   / \    /  \  
  g   r  e   at  
             / \  
            a   t  
  To scramble the string, we may choose any non-leaf node and swap its two children.  
    
  For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".  
    
      rgeat  
     /    \  
    rg    eat  
   / \    /  \  
  r   g  e   at  
             / \  
            a   t  
  We say that "rgeat" is a scrambled string of "great".  
    
  Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".  
    
      rgtae  
     /    \  
    rg    tae  
   / \    /  \  
  r   g  ta  e  
         / \  
        t   a  
  We say that "rgtae" is a scrambled string of "great".  
    
  Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.  
    
  Example 1:  
    
  Input: s1 = "great", s2 = "rgeat"  
  Output: true  
  Example 2:  
    
  Input: s1 = "abcde", s2 = "caebd"  
  Output: false  
### solution    
```    
Runtime: 2 ms, faster than 96.66% of Java online submissions for Scramble String.  
Memory Usage: 41.1 MB, less than 31.25% of Java online submissions for Scramble String.  
  
  class Solution {  
      public boolean isScramble(String s1, String s2) {  
          if (s1.equals(s2)) return true;   
            
          int[] letters = new int[26];  
          for (int i=0; i<s1.length(); i++) {  
              letters[s1.charAt(i)-'a']++;  
              letters[s2.charAt(i)-'a']--;  
          }  
          for (int i=0; i<26; i++) if (letters[i]!=0) return false;  
        
          for (int i=1; i<s1.length(); i++) {  
              if (isScramble(s1.substring(0,i), s2.substring(0,i))   
               && isScramble(s1.substring(i), s2.substring(i))) return true;  
              if (isScramble(s1.substring(0,i), s2.substring(s2.length()-i))   
               && isScramble(s1.substring(i), s2.substring(0,s2.length()-i))) return true;  
          }  
          return false;  
      }  
  }  
```    
    
### 个人解读    
  本题目首先需要花一段时间了解描述：  
  翻译人话：  
  把一个字符串以某一种形式展开成二叉树，对这个二叉树进行零次(测试用例告诉的)或多次调换非叶子节点的两个子树，使之变成另外一个字符串s2，则称s2为s1的一个扰乱。  
    
  思路一：  
  就是需要判断string能否分成两部分，使得左右两边的两部分含有的所有字符内容是一致的。如果能一直递归为true，则返回true。   
  因为eat可以变成tae，所以这种思路可能并不对。。。  
    
  反思：意志不够坚定，应该先试一试的。  
    
  另外发现，  
  1、subString的效率并不低，不一定非要用char[]进行到底。  
  2、而且也没有用map记录中间结果。  
    
tags:    
  -  递归  
  -  字符串  
  -  树  
