### description    
  You are given the number of rows n_rows and number of columns n_cols of a 2D binary matrix where all values are initially 0. Write a function flip which chooses a 0 value uniformly at random, changes it to 1, and then returns the position [row.id, col.id] of that value. Also, write a function reset which sets all values back to 0. Try to minimize the number of calls to system's Math.random() and optimize the time and space complexity.  
    
  Note:  
    
  1 <= n_rows, n_cols <= 10000  
  0 <= row.id < n_rows and 0 <= col.id < n_cols  
  flip will not be called when the matrix has no 0 values left.  
  the total number of calls to flip and reset will not exceed 1000.  
  Example 1:  
    
  Input:   
  ["Solution","flip","flip","flip","flip"]  
  [[2,3],[],[],[],[]]  
  Output: [null,[0,1],[1,2],[1,0],[1,1]]  
  Example 2:  
    
  Input:   
  ["Solution","flip","flip","reset","flip"]  
  [[1,2],[],[],[],[]]  
  Output: [null,[0,0],[0,1],null,[0,0]]  
  Explanation of Input Syntax:  
    
  The input is two lists: the subroutines called and their arguments. Solution's constructor has two arguments, n_rows and n_cols. flip and reset have no arguments. Arguments are always wrapped with a list, even if there aren't any.  
### solution    
```    
Runtime: 66 ms, faster than 94.10% of Java online submissions for Random Flip Matrix.  
Memory Usage: 40.1 MB, less than 66.67% of Java online submissions for Random Flip Matrix.  
  
  class Solution {  
    
      Map<Integer, Integer> map;  
      int rows, cols, total;  
      Random rand;  
        
      public Solution(int n_rows, int n_cols) {  
          map = new HashMap<>();  
          rand = new Random();  
          rows = n_rows;   
          cols = n_cols;   
          total = rows * cols;  
      }  
        
      public int[] flip() {  
            // 下面这三行是核心中的核心  
          int r = rand.nextInt(total--);  
          int x = map.getOrDefault(r, r);  
          map.put(r, map.getOrDefault(total, total));  
          return new int[]{x / cols, x % cols};  
      }  
        
      public void reset() {  
          map.clear();  
          total = rows * cols;  
      }  
  }  
```    
    
### 个人解读    
  既然要求尽量少的使用随机数，那就根据总和去作为随机数的上限。  
    
  超时算法  
  ```  
  class Solution {  
    
  private int[][] matrix;  
      private int sum;  
      private Random random;  
    
      public Solution(int n_rows, int n_cols) {  
          matrix = new int[n_rows][n_cols];  
          sum = n_cols * n_rows;  
          random = new Random();  
      }  
    
      public int[] flip() {  
          int r = random.nextInt(sum--);  
          for(int i = 0; i < matrix.length; i++) {  
              for(int j = 0; j < matrix[0].length; j++) {  
                  if(matrix[i][j] == 0) {  
                      if(r > 0) {  
                          r--;  
                      } else {  
                          matrix[i][j] = 1;  
                          return new int[]{i, j};  
                      }  
                  }  
              }  
          }  
          return new int[]{-1,-1};  
      }  
    
      public void reset() {  
          matrix = new int[matrix.length][matrix[0].length];  
          sum = matrix.length * matrix[0].length;  
      }  
  }  
  ```  
    
  优化的话，在一开始就创建好所有的随机数列。  
    
  优化原则：  
  1、并不需要真实存在一个矩阵  
  2、尽可能的少用random  
    
  反思：集合末端调换。印象里是第二次遇到了。  
  这个方法非常好用。属于标记法的高级应用把，可以动态的调整对应关系。  
    
// 下面这三行是核心中的核心  
```  
int r = rand.nextInt(total--);  
int x = map.getOrDefault(r, r);  
map.put(r, map.getOrDefault(total, total));  
```  
    
tags:    
  -  随机数  
  -  集合末端调换  
