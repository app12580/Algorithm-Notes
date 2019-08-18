### description    
  Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.  
    
  Note:  
  Input contains only lowercase English letters.  
  Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.  
  Input length is less than 50,000.  
  Example 1:  
  Input: "owoztneoer"  
    
  Output: "012"  
  Example 2:  
  Input: "fviefuro"  
    
  Output: "45"  
### solution    
```    
  
// 方法一： 利用数字特殊性  
Runtime: 8 ms, faster than 66.84% of Java online submissions for Reconstruct Original Digits from English.  
Memory Usage: 37.2 MB, less than 100.00% of Java online submissions for Reconstruct Original Digits from English.  
  
  
  class Solution {  
        String[] numstr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};  
    
    
      public String originalDigits(String s) {  
          Map<Character, Integer> map = new HashMap<>();  
          int[] chars = new int[26];  
          for (char ch : s.toCharArray()) {  
              chars[ch - 'a']++;  
          }  
          int[] resInt = new int[10];  
          helper(chars, resInt, 'z', 0);  
          helper(chars, resInt, 'x', 6);  
          helper(chars, resInt, 's', 7);  
          helper(chars, resInt, 'w', 2);  
          helper(chars, resInt, 'v', 5);  
          helper(chars, resInt, 'f', 4);  
          helper(chars, resInt, 'r', 3);  
          helper(chars, resInt, 'o', 1);  
          helper(chars, resInt, 'n', 9);  
          helper(chars, resInt, 'g', 8);  
    
          StringBuilder builder = new StringBuilder();  
          for (int i = 0; i < 10; i++) {  
              int count = resInt[i];  
              for (int j = 0; j < count; j++) {  
                  builder.append(i);  
              }  
          }  
          return builder.toString();  
      }  
    
      private void helper(int[] chars, int[] resInt, char z, int i) {  
          int count = chars[z - 'a'];  
          if(count > 0) {  
              if(z == 'n') count /= 2;  
    
              resInt[i] += count;  
              String str = numstr[i];  
              for(char ch: str.toCharArray()) {  
                  chars[ch - 'a'] -= count;  
              }  
          }  
      }  
  }  
```    
    
### 个人解读    
  感觉这种题目有点无聊啊。。。。  
  题目描述里面没有说明，数字是否可以重复额。。。  
    
  两种思路吧：  
  1、通过DFS，把数字解析出来  
  2、利用每个数字的特殊性，找规律，哪些字母是特别的。  
    
  "zero","one","two","three","four","five","six","seven","eight","nine"  
    
  -- zero 里的z是独有的：  
    "one","two","three","four","five","six","seven","eight","nine"  
  -- six 里的x是独有的  
    "one","two","three","four","five","seven","eight","nine"  
  -- seven 里的s是独有的  
    "one","two","three","four","five","eight","nine"  
  -- two 里的w是独有的  
    "one","three","four","five","eight","nine"  
  -- five 里的v是独有的  
    "one","three","four","eight","nine"    
  -- four 里的f是独有的  
    "one","three","eight","nine"    
  -- three 里的r是独有的  
    "one","eight","nine"      
  -- one 里的o是独有的  
    "eight","nine"          
  -- nine 里的n是独有的  
  --然后只剩下"eight"          
    
  真的很无聊，这种顺序写下来。  
   
tags:    
  -  字符串解析  
  -  DFS  
   
