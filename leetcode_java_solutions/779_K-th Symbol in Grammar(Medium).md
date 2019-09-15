### description    
  On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.  
    
  Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).  
    
  Examples:  
  Input: N = 1, K = 1  
  Output: 0  
    
  Input: N = 2, K = 1  
  Output: 0  
    
  Input: N = 2, K = 2  
  Output: 1  
    
  Input: N = 4, K = 5  
  Output: 1  
    
  Explanation:  
  row 1: 0  
  row 2: 01  
  row 3: 0110  
  row 4: 01101001  
  Note:  
    
  N will be an integer in the range [1, 30].  
  K will be an integer in the range [1, 2^(N-1)].  
### solution    
```    
  class Solution {  
     public int kthGrammar(int N, int K) {  
          return helper(K);  
      }  
    
      private int helper(int k) {  
          if(k == 1) return 0;  
          if(k == 2) return 1;  
          int pre = 1;  
          while(pre * 2 < k) {  
              pre *= 2;  
          }  
          return 1- helper(k - pre);  
      }  
    
  }  
```    
    
### 个人解读    
  感觉本题目还是有规律可循，至少前面的几位不会变。  
    
  找到规律了，每次新的字符串，等于先前的字符串0变11变0，然后拼接上去。  
    
  因此N是没啥意义的。而K是可以和前面的比较的。  
  5： 先去找到4，然后5等于5-4的反位  
    
  测试方法  
  ```  
   private String trans(String input) {  
          StringBuilder builder = new StringBuilder();  
          for(int i = 0; i < input.length(); i++) {  
              if(input.charAt(i) == '0') {  
                  builder.append("01");  
              } else {  
                  builder.append("10");  
              }  
          }  
          return builder.toString();  
      }  
  ```  
    
tags:    
  -  重点数学  
  -  数字  
