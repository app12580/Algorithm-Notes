### description    
  A message containing letters from A-Z is being encoded to numbers using the following mapping way:  
    
  'A' -> 1  
  'B' -> 2  
  ...  
  'Z' -> 26  
  Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.  
    
  Given the encoded message containing digits and the character '*', return the total number of ways to decode it.  
    
  Also, since the answer may be very large, you should return the output mod 109 + 7.  
    
  Example 1:  
  Input: "*"  
  Output: 9  
  Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".  
  Example 2:  
  Input: "1*"  
  Output: 9 + 9 = 18  
  Note:  
  The length of the input string will fit in range [1, 105].  
  The input string will only contain the character '*' and digits '0' - '9'.  
    
### solution    
```    
//方法一： 两位两位的讨论，最后一位数还是两位分开讨论，不要先看数字，然后各自讨论一位数和两位数    
Runtime: 15 ms, faster than 88.21% of Java online submissions for Decode Ways II.  
Memory Usage: 37.8 MB, less than 100.00% of Java online submissions for Decode Ways II.  
  
  class Solution {  
     public int numDecodings(String s) {  
          long[] dp = new long[s.length()+1];  
          dp[0] = 1;  
          if(s.charAt(0) == '0'){  
              return 0;  
          }  
          dp[1] = (s.charAt(0) == '*') ? 9 : 1;  
    
          for(int i = 2; i <= s.length(); i++){  
              char first = s.charAt(i-2);  
              char second = s.charAt(i-1);  
    
              // For dp[i-1]  
              if(second == '*'){  
                  dp[i] += 9*dp[i-1];  
              }else if(second > '0'){  
                  dp[i] += dp[i-1];  
              }  
                
              // For dp[i-2]  
              if(first == '*'){  
                  if(second == '*'){  
                      dp[i] += 15*dp[i-2];  
                  }else if(second <= '6'){  
                      dp[i] += 2*dp[i-2];  
                  }else{  
                      dp[i] += dp[i-2];  
                  }  
              }else if(first == '1' || first == '2'){  
                  if(second == '*'){  
                      if(first == '1'){  
                         dp[i] += 9*dp[i-2];   
                      }else{ // first == '2'  
                         dp[i] += 6*dp[i-2];   
                      }  
                  }else if( ((first-'0')*10 + (second-'0')) <= 26 ){  
                      dp[i] += dp[i-2];      
                  }  
              }  
    
              dp[i] %= 1000000007;  
          }  
          return (int)dp[s.length()];  
      }  
  }  
```    
    
### 个人解读    
  DP无误  
  dp[i] 表示[0, i]的结果数目  
  
  先假设输入的一定是合法的    
  分类讨论：  
  1、i == 0：  
   *20  
   dp[i] = dp[i - 2]  
     
   如果前面是*  
   dp[i] = dp[i - 2] * 2  
    
  2、i == 1~9：  
  [7~9]: 如果前面是2：dp[i] = dp[i - 1] 如果前面是1：dp[i] = dp[i - 1] + dp[i - 2] 如果前面是* dp[i] = dp[i - 1] + dp[i - 2]   
         其他： dp[i] = dp[i-1]  
           
  [1~6]: 如果前面的大于2或者等于0 ： dp[i] = dp[i - 1] ; 如果是1或者2： dp[i] = dp[i - 1] + dp[i-2]  
         如果是*: dp[i] = dp[i-1] + dp[i-2] * 2  
    
  3、i == *  
  如果前面的大于2或者等于0 ：dp[i] = dp[i - 1] * 9  
  如果前面是1： dp[i] = dp[i-1] * 9 + dp[i-2] * 10  
  ....这里遇到问题了。。。  
  dp[i] 还需要知道最后一位如果是*,那对应的是12还是567。  
    
  所以不能一位一位比较，需要两位两位的比较。而且需要注意："*"不能代表0  
    
tags:    
  -  字符串解析  
  -  大数统计  
  -  分类讨论  
  -  动态规划  
