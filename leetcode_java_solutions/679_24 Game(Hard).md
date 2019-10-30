### description    
  You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.  
    
  Example 1:  
  Input: [4, 1, 8, 7]  
  Output: True  
  Explanation: (8-4) * (7-1) = 24  
  Example 2:  
  Input: [1, 2, 1, 2]  
  Output: False  
  Note:  
  The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.  
  Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.  
  You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.  
### solution    
```    
  
//方法一： DFS + 回溯  
Runtime: 12 ms, faster than 37.20% of Java online submissions for 24 Game.  
Memory Usage: 38 MB, less than 93.75% of Java online submissions for 24 Game.  
  
  class Solution {  
      double eps = 0.001;  
    
      public boolean judgePoint24(int[] nums) {  
          List<Double> list = new ArrayList<>();  
          for (int n : nums) {  
              list.add((double) n);  
          }  
          return dfs(list);  
      }  
    
      private boolean dfs(List<Double> list) {  
          if (list.size() == 1) {  
              if (Math.abs(list.get(0) - 24) < eps) {  
                  return true;  
              } else {  
                  return false;  
              }  
          }  
    
          for (int i = 0; i < list.size(); i++) {  
              for(int j = 0; j < i; j++) {  
                  double h = list.get(i);  
                  double l = list.get(j);  
                  List<Double> next = new ArrayList<>();  
                  next.addAll(Arrays.asList(h + l, h - l, h * l, l - h));  
                  if(Math.abs(h) > eps) next.add(l / h);  
                  if(Math.abs(l) > eps) next.add(h / l);  
                  list.remove(h);  
                  list.remove(l);  
                  for(double n: next) {  
                      list.add(n);  
                      if(dfs(list)) return true;  
                      list.remove(list.size() - 1);  
                  }  
                  list.add(j, l);  
                  list.add(i, h);  
              }  
    
          }  
          return false;  
    
      }  
     
  }  
```    
    
### 个人解读    
    
  DFS遍历，boolean返回值。  
    
  1、关于如何处理分数： 另差距小于0.001即可，就认为两个数字相等、  
  2、如何实现动态数组数量的变化？  
    使用List，每次先remove，然后回溯  
    
  思路一：  
  逆向，每次与24比较。查看剩下的数字需要凑出来多少。  
  错误； 并不是计算过程中每个数直接与24作用的，可能是(a+b)*(c -d) = 24  
    
  思路二：  
  每次让两个数字合并，最终化成一个数字  
  需要注意回溯和增加数字时候的顺序和标记索引。  
  删除时候先大后小，回溯时候先小后大  
    
tags:    
  -  DFS  
  -  回溯  
