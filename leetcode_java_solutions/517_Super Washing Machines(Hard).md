### description    
  You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.  
    
  For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time .  
    
  Given an integer array representing the number of dresses in each washing machine from left to right on the line, you should find the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.  
    
  Example1  
    
  Input: [1,0,5]  
    
  Output: 3  
    
  Explanation:   
  1st move:    1     0 <-- 5    =>    1     1     4  
  2nd move:    1 <-- 1 <-- 4    =>    2     1     3      
  3rd move:    2     1 <-- 3    =>    2     2     2     
  Example2  
    
  Input: [0,3,0]  
    
  Output: 2  
    
  Explanation:   
  1st move:    0 <-- 3     0    =>    1     2     0      
  2nd move:    1     2 --> 0    =>    1     1     1       
  Example3  
    
  Input: [0,2,0]  
    
  Output: -1  
    
  Explanation:   
  It's impossible to make all the three washing machines have the same number of dresses.   
  Note:  
  The range of n is [1, 10000].  
  The range of dresses number in a super washing machine is [0, 1e5].  
### solution    
```    
  Runtime: 1 ms, faster than 100.00% of Java online submissions for Super Washing Machines.  
  Memory Usage: 37.4 MB, less than 75.00% of Java online submissions for Super Washing Machines.  
    
  public class Solution {  
      public int findMinMoves(int[] machines) {  
          int total = 0;   
          for(int i: machines) total+=i;  
          if(total%machines.length!=0) return -1;  
          int avg = total/machines.length, cnt = 0, max = 0;  
          for(int load: machines){  
              cnt += load-avg; //load-avg is "gain/lose"  
              max = Math.max(Math.max(max, Math.abs(cnt)), load-avg);  
          }  
          return max;  
      }  
  }  
    
```    
    
### 个人解读    
  感觉题目类似于之前某个二叉树移动金币的题目。总之不能遍历，一定要通过数学法计算。  
    
  思路一：  
  从左到右遍历，依次记录截止到目前，左边的移动数目和欠缺or富裕的数量。  
  忽略了一个重要问题，本题目是可以同步传送的。  
    
  数学法： 两个指标中取最大值：当前节点比平均值多多少(意思要移出去)；  
  翻译过来就是要么别人进来，要么自己出去。  
    
tags:    
  -  数学  
