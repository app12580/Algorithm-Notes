### description    
  Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.  
    
  Example 1:  
    
  Input: "(()"  
  Output: 2  
  Explanation: The longest valid parentheses substring is "()"  
  Example 2:  
    
  Input: ")()())"  
  Output: 4  
  Explanation: The longest valid parentheses substring is "()()"  
### solution    
```    
Runtime: 4 ms, faster than 55.45% of Java online submissions for Longest Valid Parentheses.  
Memory Usage: 36.8 MB, less than 100.00% of Java online submissions for Longest Valid Parentheses.  
  
  class Solution {  
       public int longestValidParentheses(String s) {  
          Stack<Integer> stack = new Stack<>();  
          int res = 0;  
          int count = 0;  
          for (char ch : s.toCharArray()) {  
              if (ch == '(') {  
                  if(count == 0) {  
                      stack.push(1);  
                  } else {  
                      stack.push(count + 1);  
                      count = 0;  
                  }  
              } else {  
                  if (stack.isEmpty()) {  
                      count = 0;  
                      continue;  
                  }  
                  count += stack.pop();  
                  res = Math.max(res, count);  
              }  
          }  
          return res * 2;  
      }  
    
  }  
    
  //方法二： 通过坐标来描述当前进度  
  类似于消消乐的感觉，直接把中间的部分给消去了。  
    
  https://leetcode.com/problems/longest-valid-parentheses/discuss/14126/My-O(n)-solution-using-a-stack  
  class Solution {  
  public:  
      int longestValidParentheses(string s) {  
          stack<int> stk;  
          stk.push(-1);  
          int ans = 0;  
          for (int i = 0; i < s.size(); i++) {  
              if (s[i] == '(') {  
                  stk.push(i);  
              } else {  
                  stk.pop();  
                  if (stk.empty()) {  
                      stk.push(i);  
                  } else {  
                      ans = max(ans, i - stk.top());  
                  }  
              }  
          }  
          return ans;  
      }  
  };  
```    
    
### 个人解读    
  思考了以下几种可能做法：  
  1、多维DP：很难构造转移方程  
  2、滑动窗口：问题在于什么时候滑动，而一旦滑动了，中间结果怎么复用？  
  3、栈：感觉是最靠谱的做法，在遍历过程中，中间可能有若干种分段。  
    
  总结:  
  1、使用双栈解法，因为()()的统计可以用一个int解决，所以简化成了单栈，用来存放如果收容当前的'('会计数多少  
  2、stack里面存放的是"合格的+一个("，此时如果遇到)，那么这个时候的括号对数就是stack里面的值  
  3、count是指在遇到)时候，已经累计了多少对，因为还没有遇到(，所以还没有在栈里面。  
  4、总之呢，不管代码怎么写的，这题目用stack是没跑了  
    
    
tags:    
  -  栈  
