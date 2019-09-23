### description    
  There are N dominoes in a line, and we place each domino vertically upright.  
    
  In the beginning, we simultaneously push some of the dominoes either to the left or to the right.  
    
    
    
  After each second, each domino that is falling to the left pushes the adjacent domino on the left.  
    
  Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.  
    
  When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.  
    
  For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.  
    
  Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.  
    
  Return a string representing the final state.   
    
  Example 1:  
    
  Input: ".L.R...LR..L.."  
  Output: "LL.RR.LLRRLL.."  
  Example 2:  
    
  Input: "RR.L"  
  Output: "RR.L"  
  Explanation: The first domino expends no additional force on the second domino.  
  Note:  
    
  0 <= N <= 10^5  
  String dominoes contains only 'L', 'R' and '.'  
### solution    
```    
  // 方法一：  
  Runtime: 53 ms, faster than 10.27% of Java online submissions for Push Dominoes.  
  Memory Usage: 38.1 MB, less than 100.00% of Java online submissions for Push Dominoes.  
    
  class Solution {  
      public String pushDominoes(String dominoes) {  
          //先预处理  
          if(dominoes.length() == 1) return dominoes;  
          char[] chars = dominoes.toCharArray();  
          for(int i = 0; i < chars.length; i++) {  
              if(chars[i] != '.') continue;  
              if(i == 0) {  
                  if(chars[i + 1] == 'L') {  
                      chars[i] = 'l';  
                  }  
              } else if(i == chars.length - 1) {  
                  if(chars[chars.length - 2] == 'R') {  
                      chars[i] = 'r';  
                  }  
              } else {  
                  if(chars[i - 1] == 'R' && chars[i + 1] == 'L') {  
    
                  } else if(chars[i - 1] == 'R') {  
                      chars[i] = 'r';  
                  } else if(chars[i + 1] == 'L') {  
                      chars[i] = 'l';  
                  }  
              }  
          }  
          for(int i = 0; i < chars.length; i++) {  
              switch (chars[i]) {  
                  case 'l':  
                      chars[i] = 'L';  
                      break;  
                  case 'r':  
                      chars[i] = 'R';  
                      break;  
              }  
          }  
          //如果未处理过，直接返回dominoes  
          String res = new String(chars);  
          if(dominoes.equals(res)) return res;  
          //最后返回递归结果  
          return pushDominoes(res);  
      }  
    
  }  
    
  // 方法二： 优化  
  通过LR分段进行处理，每次可以判断一大片字符  
    
  Runtime: 5 ms, faster than 99.33% of Java online submissions for Push Dominoes.  
  Memory Usage: 38.8 MB, less than 100.00% of Java online submissions for Push Dominoes.  
    
  class Solution {  
      public String pushDominoes(String dominoes) {  
          int slow = -1;  
          int fast = 0;  
          //这里的slow和fast是指两个LR字母的一段。  
          int len = dominoes.length();  
          char[] chars = dominoes.toCharArray();  
          while (fast < len) {  
              while (fast < len && chars[fast] == '.') fast++;  
              //fast 为第一个不是.的索引  
              if (slow == -1) {  
                  //如果是第一段，左边是起始点，右边是第一个非.的索引  
                  if (fast == len) break;  
                  if (chars[fast] == 'L') {  
                      for (int i = 0; i < fast; i++) {  
                          chars[i] = 'L';  
                      }  
                  }  
              }  
              else if (fast == len) {  
                  if (chars[slow] == 'R') {  
                      for (int i = slow; i < fast; i++) {  
                          chars[i] = 'R';  
                      }  
                  }  
              }  
              else {  
  //                一共四种情况  
  //                LL LR RL RR  
                  if (chars[slow] == 'R' && chars[fast] == 'L') {  
                      int head = slow + 1;  
                      int tail = fast - 1;  
                      while (head < tail) {  
                          chars[head++] = 'R';  
                          chars[tail--] = 'L';  
                      }  
                  }  
                  else if (chars[slow] == chars[fast]) {  
                      for (int i = slow; i < fast; i++) {  
                          chars[i] = chars[slow];  
                      }  
                  }  
              }  
              slow = fast;  
              fast++;  
          }  
          return new String(chars);  
      }  
    
  }  
```    
    
### 个人解读    
  感觉这个题目就是要各种if判断，然后一次一次的递归。  
    
  方法一：  
  1、先挨个位置遍历，通过各种if判断处理结果  
  2、由于处理结果会影响到下一次遍历，所以采用临时标记  
  
tags:    
  -  数学  
  -  字符串  
  -  分治法  
