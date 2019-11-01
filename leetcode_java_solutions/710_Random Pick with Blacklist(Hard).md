### description    
  Given a blacklist B containing unique integers from [0, N), write a function to return a uniform random integer from [0, N) which is NOT in B.  
    
  Optimize it such that it minimizes the call to system’s Math.random().  
    
  Note:  
    
  1 <= N <= 1000000000  
  0 <= B.length < min(100000, N)  
  [0, N) does NOT include N. See interval notation.  
  Example 1:  
    
  Input:   
  ["Solution","pick","pick","pick"]  
  [[1,[]],[],[],[]]  
  Output: [null,0,0,0]  
  Example 2:  
    
  Input:   
  ["Solution","pick","pick","pick"]  
  [[2,[]],[],[],[]]  
  Output: [null,1,1,1]  
  Example 3:  
    
  Input:   
  ["Solution","pick","pick","pick"]  
  [[3,[1]],[],[],[]]  
  Output: [null,0,0,2]  
  Example 4:  
    
  Input:   
  ["Solution","pick","pick","pick"]  
  [[4,[2]],[],[],[]]  
  Output: [null,1,3,1]  
  Explanation of Input Syntax:  
    
  The input is two lists: the subroutines called and their arguments. Solution's constructor has two arguments, N and the blacklist B. pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.  
### solution    
```    
  
// 方法一： 自己写的  
Runtime: 98 ms, faster than 55.07% of Java online submissions for Random Pick with Blacklist.  
Memory Usage: 68.9 MB, less than 33.33% of Java online submissions for Random Pick with Blacklist.  
  
  class Solution {  
    
      private Map<Integer, Integer> map;  
      private int max;  
      private Random random;  
      public Solution(int N, int[] blacklist) {  
          max = N - blacklist.length;  
          map = new HashMap<>();  
          random = new Random();  
    
          Set<Integer> blackSet = new HashSet<>();  
          for(int b: blacklist) blackSet.add(b);  
          int start = max;  
          for(int i = 0; i < blacklist.length; i++) {  
              if(blacklist[i] >= max) continue;  
              while(blackSet.contains(start)) {  
                  start++;  
              }  
              map.put(blacklist[i], start++);  
          }  
      }  
    
      public int pick() {  
          int r = random.nextInt(max);  
          if(map.containsKey(r)) {  
              return map.get(r);  
          } else {  
              return r;  
          }  
      }  
  }  
     
```    
    
### 个人解读    
  两个问题：  
  1、如何优雅的回避黑名单  
  2、如何较少的使用Random方法  
    
  1、可以使用替换，  
  举例子：[4,[2]]    
  正常来说，length = 4  
  但是黑名单长度为1，所以长度修正成4-1=3，0,1,2，然后用一个Map存储，key是2，value是3  
    
  注意细节，需要另替换后的不在黑名单里面  
    
  2、不清楚，但应该不影响效率，先测试一下吧  
     
  总结：  
  和discuss里面高票数解答一毛一样，然而也没看见什么少用系统的random方法啊？？  
  算了，不关键。   
     
tags:    
  -  随机数  
