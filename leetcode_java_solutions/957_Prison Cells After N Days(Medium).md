### description    
  There are 8 prison cells in a row, and each cell is either occupied or vacant.  
    
  Each day, whether the cell is occupied or vacant changes according to the following rules:  
    
  If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.  
  Otherwise, it becomes vacant.  
  (Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)  
    
  We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.  
    
  Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)  
    
     
    
  Example 1:  
    
  Input: cells = [0,1,0,1,1,0,0,1], N = 7  
  Output: [0,0,1,1,0,0,0,0]  
  Explanation:   
  The following table summarizes the state of the prison on each day:  
  Day 0: [0, 1, 0, 1, 1, 0, 0, 1]  
  Day 1: [0, 1, 1, 0, 0, 0, 0, 0]  
  Day 2: [0, 0, 0, 0, 1, 1, 1, 0]  
  Day 3: [0, 1, 1, 0, 0, 1, 0, 0]  
  Day 4: [0, 0, 0, 0, 0, 1, 0, 0]  
  Day 5: [0, 1, 1, 1, 0, 1, 0, 0]  
  Day 6: [0, 0, 1, 0, 1, 1, 0, 0]  
  Day 7: [0, 0, 1, 1, 0, 0, 0, 0]  
    
  Example 2:  
    
  Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000  
  Output: [0,0,1,1,1,1,1,0]  
     
    
  Note:  
    
  cells.length == 8  
  cells[i] is in {0, 1}  
  1 <= N <= 10^9  
### solution    
```    
  
Runtime: 2 ms, faster than 72.24% of Java online submissions for Prison Cells After N Days.  
Memory Usage: 36.4 MB, less than 97.62% of Java online submissions for Prison Cells After N Days.  
  
  
  class Solution {  
    public int[] prisonAfterNDays(int[] cells, int N) {  
          if(N == 0) return cells;  
          List<Integer> cycle = new ArrayList<>();  
          cycle.add(feature(cells));  
          int cur =  feature(cells);  
          int start = 0;  
          while(true) {  
              if(N == 1) {  
                  return next(decode(cur));  
              }  
              int next = feature(next(decode(cur)));  
              if(cycle.contains(next)) {  
                  start = cycle.indexOf(next);  
                  break;  
              } else {  
                  cycle.add(next);  
                  cur = next;  
                  N--;  
              }  
          }  
  //        0 123 456 ; N = 6; size = 3; start = 1  
          int size = cycle.size() - start;  
          int r = cycle.get((N - 1) % size + start) ;  
          return decode(r);  
      }  
    
      private int[] decode(int f) {  
          int[] res=  new int[8];  
          for(int i = 7; i >= 0; i--) {  
              res[i] = f % 2;  
              f /= 2;  
          }  
          return res;  
      }  
    
      private int feature(int[] cells) {  
          int res = 0;  
          for(int i = 0; i < 8; i++) {  
              res = res * 2 + cells[i];  
          }  
          return res;  
      }  
    
      private int[] next(int[] cells) {  
          int[] res = new int[8];  
          for(int i = 1; i < 7; i++) {  
              if(cells[i - 1] == cells[i + 1]) {  
                  res[i] = 1;  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  反思：不要想着过分依赖数学方法，暴力法遍历也不是不可以。  
    
  思路：最多256种情况，足够暴力法发现循环周期了。  
  使用List存储循环情况。  
    
  写代码时候，被最终return卡了好久  
  1、进入循环点不一定是0点  
  2、需要注意测试用例是否正确  
  3、最好具体例子测试一下  
    
tags:    
  -  暴力法  
