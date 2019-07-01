### description    
  Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.  
    
  Example 1:  
    
  Input: S = "loveleetcode", C = 'e'  
  Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]  
     
    
  Note:  
    
  S string length is in [1, 10000].  
  C is a single character, and guaranteed to be in string S.  
  All letters in S and C are lowercase.  
### solution    
```    
  
// 方法一  
Runtime: 3 ms, faster than 29.17% of Java online submissions for Shortest Distance to a Character.  
Memory Usage: 36.1 MB, less than 99.76% of Java online submissions for Shortest Distance to a Character.  
  
  class Solution {  
      public int[] shortestToChar(String S, char C) {  
           int[] res = new int[S.length()];  
          Stack<Integer> stack = new Stack<>();  
          int lastTarget = -1;  
          int curCount = 1;     //注意这个是1   
          for(int i = 0; i < S.length(); i++ ) {  
              char ch = S.charAt(i);  
              if(ch != C) {  
                  stack.push(i);  
              } else {  
                  //如果当前是目标字符，此时需要出栈  
                  res[i] = 0;  
                  if(lastTarget == -1) {  
                     //第一次遇到target  
                     while(!stack.isEmpty()) {  
                         int cur = stack.pop();  
                         res[cur] = curCount++;  
                     }  
                 } else {  
                      while(!stack.isEmpty()) {  
                          int cur = stack.pop();  
                          res[cur] = Math.min(curCount++, cur - lastTarget);  
                      }  
                 }  
                  lastTarget = i;  
                  curCount = 1;  
    
              }  
          }  
          while(!stack.isEmpty()) {  
              int cur = stack.pop();  
              res[cur] = cur - lastTarget;      //注意这行  
          }  
          return res;  
      }  
  }  
    
  //方法二：  
    
    
  class Solution {  
      public int[] shortestToChar(String S, char C) {  
          int n = S.length();  
          int[] res = new int[n];  
          int pos = -n;  
          for (int i = 0; i < n; ++i) {  
              if (S.charAt(i) == C) pos = i;  
              res[i] = i - pos;  
          }  
            
          //运行到这里的是哦户pos为正向最后一个target  
          for (int i = n - 1; i >= 0; --i) {  
              if (S.charAt(i) == C)  pos = i;  
              res[i] = Math.min(res[i], Math.abs(i - pos)); // 这个绝对值 主要是在最开始的那一段才会出现  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  感觉这种题目都是需要用到栈的啊。遇到C的时候就要出栈，然后再中间的字母要比较和两端的C的距离。  
  所以需要一个int存储上一次E的位置，每次弹栈的时候也需要一个int标记出了几次。  
    
  方法二的优化： 两次for循环反而要比一次for循环快。新创建了一个概念：正反向遍历。主要在于“方向性”这个点。  
    
tags:    
  -   正反向遍历  
  -   栈  
