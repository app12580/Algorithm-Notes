### description    
  In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.  
    
  Example:  
    
  Input: start = "RXXLRXRXL", end = "XRLXXRRLX"  
  Output: True  
  Explanation:  
  We can transform start to end following these steps:  
  RXXLRXRXL ->  
  XRXLRXRXL ->  
  XRLXRXRXL ->  
  XRLXXRRXL ->  
  XRLXXRRLX  
  Note:  
    
  1 <= len(start) = len(end) <= 10000.  
  Both start and end will only consist of characters in {'L', 'R', 'X'}.  
### solution    
```    
  class Solution {  
       public boolean canTransform(String start, String end) {  
          if (start.length() != end.length()) {  
              return false;  
          }  
          Queue<String> queue = new LinkedList<>();  
          for(int i = 0; i < start.length(); i++) {  
              char cur = start.charAt(i);  
              if(cur == 'X') continue;  
              if(cur == 'L') {  
                  queue.offer("L" + ":" + i);  
              } else {  
                  queue.offer("R" + ":" + i);  
              }  
          }  
          for(int i = 0; i < end.length(); i++) {  
              char cur = end.charAt(i);  
              if(cur == 'X') continue;  
              if(queue.isEmpty()) return false;     //这一行需要判断，queue过多过少都不行  
              String poll = queue.poll();  
              String[] split = poll.split(":");  
              char name = split[0].charAt(0);  
              int index = Integer.valueOf(split[1]);  
              if(cur != name) {  
                  return false;  
              }  
              if(cur == 'L' && i > index || cur == 'R' && i < index) {  
                 return false;  
              }   
          }  
          return queue.isEmpty();       //这一行需要注意，刚开始直接return true 了  
      }  
  }  
```    
    
### 个人解读    
  只能交换LX，RX，不能交换LR。  
    
  除了整体DFS外，想想有没有其他办法。  
    
  沿着示例走了一下，本来打算看着从左到右怎么选取变量去遍历，然后突然发现一个问题，会不会因为无法改变LR，所以无法改变整体的LR相对位置。突然想起来了那个"(*)"问题。  
    
  然后除了整体的顺序，还需要看看什么情况下不会被卡住。  
    
  XXXXLR  
  LRXXXX  
    
  感觉因为X可以理解成任意移动，所以足够了。  
  考虑如何实行： 双指针  
    
  问题："XL" with "LX", or replacing one occurrence of "RX" with "XR"  
  只有这种操作：简称L左移或者R右移。  
    
  通过index遍历，然后区分XLR的出现时机。  
    
tags:    
  -  选择合适数据结构  
  -  字符串  
  -  数学  
