### description    
  You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.  
    
  Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows.   
    
  Please note that both secret number and friend's guess may contain duplicate digits.  
    
  Example 1:  
    
  Input: secret = "1807", guess = "7810"  
    
  Output: "1A3B"  
    
  Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.  
  Example 2:  
    
  Input: secret = "1123", guess = "0111"  
    
  Output: "1A1B"  
    
  Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.  
  Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.  
### solution    
```    
Runtime: 1 ms, faster than 100.00% of Java online submissions for Bulls and Cows.  
Memory Usage: 38.1 MB, less than 53.39% of Java online submissions for Bulls and Cows.  
  
  class Solution {  
      public String getHint(String secret, String guess) {  
          int aCnt = 0;  
          int bCnt = 0;  
          int[] secretF = new int[10];  
          int[] guessF = new int[10];  
          for(int i = 0; i < secret.length(); i++) {  
              if(secret.charAt(i) == guess.charAt(i)) {  
                  aCnt++;  
              } else {  
                  secretF[secret.charAt(i) - '0']++;  
                  guessF[guess.charAt(i) - '0']++;  
              }  
          }  
          for(int i = 0; i < 10; i++) {  
              bCnt += Math.min(secretF[i], guessF[i]);  
          }  
          return aCnt + "A" + bCnt + "B";  
      }  
  }  
```    
    
### 个人解读    
  字符串遍历，每次遍历时候做两件事情：统计A的个数；统计两个字符串的特征。  
  特征个数 - A 就等于B的个数。  
  1234  
  5331  
    
  或者为了省力，符合A的时候，就不要算进特征里面了。    
    
tags:    
  -  字符串  
