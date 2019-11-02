### description    
  A virus is spreading rapidly, and your task is to quarantine the infected area by installing walls.  
    
  The world is modeled as a 2-D array of cells, where 0 represents uninfected cells, and 1 represents cells contaminated with the virus. A wall (and only one wall) can be installed between any two 4-directionally adjacent cells, on the shared boundary.  
    
  Every night, the virus spreads to all neighboring cells in all four directions unless blocked by a wall. Resources are limited. Each day, you can install walls around only one region -- the affected area (continuous block of infected cells) that threatens the most uninfected cells the following night. There will never be a tie.  
    
  Can you save the day? If so, what is the number of walls required? If not, and the world becomes fully infected, return the number of walls used.  
    
  Example 1:  
  Input: grid =   
  [[0,1,0,0,0,0,0,1],  
   [0,1,0,0,0,0,0,1],  
   [0,0,0,0,0,0,0,1],  
   [0,0,0,0,0,0,0,0]]  
  Output: 10  
  Explanation:  
  There are 2 contaminated regions.  
  On the first day, add 5 walls to quarantine the viral region on the left. The board after the virus spreads is:  
    
  [[0,1,0,0,0,0,1,1],  
   [0,1,0,0,0,0,1,1],  
   [0,0,0,0,0,0,1,1],  
   [0,0,0,0,0,0,0,1]]  
    
  On the second day, add 5 walls to quarantine the viral region on the right. The virus is fully contained.  
  Example 2:  
  Input: grid =   
  [[1,1,1],  
   [1,0,1],  
   [1,1,1]]  
  Output: 4  
  Explanation: Even though there is only one cell saved, there are 4 walls built.  
  Notice that walls are only built on the shared boundary of two different cells.  
  Example 3:  
  Input: grid =   
  [[1,1,1,0,0,0,0,0,0],  
   [1,0,1,0,1,1,1,1,1],  
   [1,1,1,0,0,0,0,0,0]]  
  Output: 13  
  Explanation: The region on the left only builds two new walls.  
  Note:  
  The number of rows and columns of grid will each be in the range [1, 50].  
  Each grid[i][j] will be either 0 or 1.  
  Throughout the described process, there is always a contiguous viral region that will infect strictly more uncontaminated squares in the next round.  
### solution    
```    
// 参考答案二：  
Runtime: 6 ms, faster than 89.47% of Java online submissions for Contain Virus.  
Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Contain Virus.  
class Solution {  
    public int containVirus(int[][] grid) {  
           int result=0;  
           while (true){  
               int walls=process(grid);  
               if (walls==0){  
                   break;  
               }  
               result+=walls;  
           }  
           return result;  
       }  
     
       //对整个场景进行业务逻辑梳理  
       // stateArray 0表示未访问， 1表示访问过了  
       private int process(int[][] grid) {  
           int rows = grid.length;  
           int cols = grid[0].length;  
           // cnt 是最大面积，res是对应的墙  
           int cnt = 0, res = 0, color = -1, row = -1, col = -1;  
           //用一个数组来装当前这个点的状态  
           int[][] stateArray=new int[rows][cols];  
           for (int i = 0; i < rows; i++) {  
               for (int j = 0; j < cols; j++) {  
                   if(grid[i][j]==1&&stateArray[i][j]==0){  
                       Wall wall = new Wall();  
                       wall.walls=0;  
                       int maxArea = getMaxAreaDfs(grid, stateArray, i, j, color, wall);  
                       if(maxArea>cnt){  
                           cnt=maxArea;  
                           res=wall.walls;  
                           row=i;  
                           col=j;  
                       }  
                       color--;  
                   }  
               }  
           }  
           //修墙，将目标区域设置为未活动  
           buildWall(grid,row,col);  
           //另一块传播病毒  
           //spread(grid,stateArray,row,col);  
           stateArray=new int[rows][cols];  
           for (int i = 0; i < rows; i++) {  
               for (int j = 0; j < cols; j++) {  
                   if (grid[i][j] == 1 && stateArray[i][j] == 0){  
                       spread(grid, stateArray, i, j);  
                   }  
               }  
           }  
           return res;  
     
       }  
     
       // if stateArray == 1 则跳过当前  
       private void spread(int[][] grid, int[][] stateArray, int row, int col) {  
           int rows = grid.length;  
           int cols = grid[0].length;  
           if (row < 0 || row >= rows || col < 0 || col >= cols || stateArray[row][col] == 1) {  
               return;  
           }  
           if (grid[row][col] == 0){  
               grid[row][col] = 1;  
               stateArray[row][col] = 1;  
           }else if (grid[row][col] == 1){  
               stateArray[row][col] = 1;  
               int[] dir = { -1, 0, 1, 0, -1 };  
               for (int i = 0; i < 4; i++){  
                   spread(grid, stateArray, row + dir[i], col + dir[i + 1]);  
               }  
           }  
       }  
     
       private void buildWall(int[][] grid, int row, int col) {  
           int rows = grid.length;  
           int cols = grid[0].length;  
           if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] != 1){  
               return;  
           }  
           //设置为不活动  
           grid[row][col] = -1;  
           int[] dir = { -1, 0, 1, 0, -1 };  
           //DFS  
           for (int i = 0; i < 4; i++){  
               buildWall(grid, row + dir[i], col + dir[i + 1]);  
           }  
       }  
     
       // 返回的是影响的数量  
       private int getMaxAreaDfs(int[][] grid, int[][] stateArray, int row, int col, int color, Wall wall) {  
           int rows = grid.length;  
           int cols = grid[0].length;  
           int res = 0;  
           //异常排除  
           if (row < 0 || row >= rows || col < 0 || col >= cols){  
               return 0;  
           }  
           //0不是病毒  
           if (grid[row][col] == 0) {  
               wall.walls++;  
               //第二次进入  
               if (stateArray[row][col] == color){  
                   return 0;  
               }  
               //第一次进入  
               stateArray[row][col] = color;  
               return 1;  
           }  
           // 不是grid[i][j]==1 or 0  
           if (stateArray[row][col] == 1 || grid[row][col] != 1){  
               return 0;  
           }  
           //grid[i][j]==1  
           stateArray[row][col] = 1;  
           //前后左右,再来一次，直到出现不再是1的结果。DFS的思想。  
           int[] dir = { -1, 0, 1, 0, -1 };  
           for (int i = 0; i < 4; i++){  
               res += getMaxAreaDfs(grid,stateArray, row + dir[i], col + dir[i + 1], color, wall);  
           }  
           return res;  
       }  
     
       class Wall{  
           public int walls;  
     
       }  
  
}   
  
// 参考答案一：  
// 算了，学就完事了。。。  
Runtime: 7 ms, faster than 74.74% of Java online submissions for Contain Virus.  
Memory Usage: 40.3 MB, less than 33.33% of Java online submissions for Contain Virus.  
  
  class Solution {  
     public int containVirus(int[][] grid) {  
          int[] cost = new int[]{0};  
          while(check(grid, cost));  
          return cost[0];  
      }  
    
      private boolean check(int[][] grid, int[] cost) {  
  // update every day information and return false if no improvement can be made  
          int count = 1;  
          int max = -1;  
          boolean flag = false;  
          List<int[]> info = new ArrayList<>();  
          for (int i = 0; i < grid.length; i++) {  
              for (int j = 0; j < grid[0].length; j++) {  
                  if (grid[i][j] == 1) {  
                      count++;  
                      int[][] walls = new int[grid.length][grid[0].length];  
                      int[] res = new int[2];  
                      grid[i][j] = count;  
                      dfs(i, j, grid, count, walls, res);  
                      if (res[0] != 0) flag = true;  
                      if (max == -1 || res[0] > info.get(max)[0]) {  
                          max = count - 2;  
                      }  
                      info.add(res);  
                  }  
              }  
          }  
          if (count == 1) {  
              return false;  
          }  
          cost[0] += info.get(max)[1];  
          update(grid, max + 2);  
    
          return flag;  
      }  
    
    
      private void dfs(int row, int col, int[][] grid, int count, int[][] walls, int[] res) {  
  //dfs and record number of walls need to block this area and how many 0s are under infection  
          int[] shiftX = new int[]{1, 0, -1, 0};  
          int[] shiftY = new int[]{0, 1, 0, -1};  
    
          for(int i = 0; i < 4; i++) {  
              int newRow = row + shiftX[i];  
              int newCol = col + shiftY[i];  
              if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length) {  
                  if (grid[newRow][newCol] == 1) {  
                      grid[newRow][newCol] = count;  
                      dfs(newRow, newCol, grid, count, walls, res);  
                  } else if (grid[newRow][newCol] == 0) {  
                      if (walls[newRow][newCol] == 0) res[0]++;  
                      if ((walls[newRow][newCol] & 1 << i) == 0) {  
                          res[1]++;  
                          walls[newRow][newCol] |= 1 << i;  
                      }  
                  }  
              }  
          }  
      }  
    
    
      private void update(int[][] grid, int quarantine) {  
  //set the new infected area and set blocked area to be -1  
          int[] shiftX = new int[]{1, 0, -1, 0};  
          int[] shiftY = new int[]{0, 1, 0, -1};  
    
          for (int i = 0; i < grid.length; i++) {  
              for (int j = 0; j < grid[0].length; j++) {  
                  if (grid[i][j] > 1 && grid[i][j] != quarantine) {  
                      for (int k = 0; k < 4; k++) {  
                          int newRow = i + shiftX[k];  
                          int newCol = j + shiftY[k];  
                          if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length && grid[newRow][newCol] == 0) {  
                              grid[newRow][newCol] = 1;  
                          }  
                      }  
                      grid[i][j] = 1;  
                  } else if (grid[i][j] == quarantine) {  
                      grid[i][j] = -1;  
                  }  
              }  
          }  
      }  
  }  
    
  // 方法二： 自己写的  
  Runtime: 123 ms, faster than 5.26% of Java online submissions for Contain Virus.  
  Memory Usage: 45.6 MB, less than 33.33% of Java online submissions for Contain Virus.  
    
  class Solution {  
       private int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1, 0}};  
        public int containVirus(int[][] grid) {  
            int res = 0;  
            int p = 0;  
            while ((p = process(grid)) != 0) {  
                res += p;  
            }  
            return res;  
        }  
      
        // 如果grid有扩散，返回当前的建墙数量。  
        // -1表示已经计算过了，不用继续管  
        private int process(int[][] grid) {  
            int m = grid.length;  
            int n = grid[0].length;  
            int maxSpread = 0;  
            int maxBuild = 0;  
            int stopX = -1, stopY = -1;  
            for (int i = 0; i < m; i++) {  
                for (int j = 0; j < n; j++) {  
                    int cur = grid[i][j];  
                    if(cur != 1) {  
                        continue;  
                    }  
                    //如果等于1  
                    int[] arr = dfs(grid, i, j);  
                    change(grid, -3, 0);  
                    int curSpread = arr[0];  
                    int curBuild = arr[1];  
                    if(curSpread > maxSpread) {  
                        maxBuild = curBuild;  
                        maxSpread = curSpread;  //这一行没有匹配上。。。差点怀疑人生了。。。至少能通过才对啊  
                        stopX = i;  
                        stopY = j;  
                    }  
                }  
            }  
      
            change(grid, -2, 1);  
      
            if(maxSpread == 0) {  
                return 0;  
            }  
            stop(grid, stopX, stopY);  
            spread(grid);  
            for(int i = 0; i < m; i++) {  
                System.out.println();  
                for(int j = 0; j < n ; j++) {  
                    System.out.print(grid[i][j] + "\t");  
                }  
            }  
            System.out.println("\n----------"+maxBuild+"--------------");  
            return maxBuild;  
        }  
      
        private void change(int[][] grid, int pre, int after) {  
            for(int i = 0; i < grid.length; i++) {  
                for(int j = 0; j < grid[0].length; j++) {  
                    if(grid[i][j] == pre) grid[i][j] = after;  
                }  
            }  
        }  
        //int[0] 是扩散数量， int[1]是建造墙的数量  
        //-1表示已经隔离，  
        //注意这个方法不会改变grid标记，需要后续改回来  
        private int[] dfs(int[][] grid, int x, int y) {  
            int m = grid.length;  
            int n = grid[0].length;  
            if(x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 0 || grid[x][y] == -1) {  
                return new int[]{0,0};  
            }  
            if(grid[x][y] == 1) {  
                grid[x][y] = -2;  
            }  
            int spread = 0;  
            int build = 0;  
            for(int[] d: directions) {  
                int nextX = x + d[0];  
                int nextY = y + d[1];  
                if(nextX < 0 || nextY < 0 || nextX >= m || nextY >= n) {  
                    continue;  
                }  
                int nextVal = grid[nextX][nextY];  
                if(nextVal == 0) {  
                    grid[nextX][nextY] = -3;  
                    build++;  
                    spread++;  
                } else if(nextVal == 1) {  
                    int[] dfs = dfs(grid, nextX, nextY);  
                    spread += dfs[0];  
                    build += dfs[1];  
                } else if(nextVal == -3) {  
                    build++;  
                }  
            }  
      
      
      
            return new int[]{spread, build};  
        }  
      
        private void spread(int[][] grid) {  
            int m = grid.length;  
            int n = grid[0].length;  
            for(int i = 0; i < m; i++) {  
                for(int j = 0; j < n; j++) {  
                    if(grid[i][j] == 1){  
                        for(int[] d: directions) {  
                            int nextX = i + d[0];  
                            int nextY = j + d[1];  
                            if(nextX < 0 || nextY < 0 || nextX >= m || nextY >= n) {  
                                continue;  
                            }  
                            if(grid[nextX][nextY] == 0) {  
                                grid[nextX][nextY] = -2;  
                            }  
                        }  
                        grid[i][j] = -2;  
                    }  
                }  
            }  
      
            change(grid, -2, 1);  
        }  
      
        private void stop(int[][] grid, int x, int y) {  
            int m = grid.length;  
            int n = grid[0].length;  
            if(grid[x][y] == 1) {  
                grid[x][y] = -1;  
                for(int[] d: directions) {  
                    int nextX = x + d[0];  
                    int nextY = y + d[1];  
                    if(nextX < 0 || nextY < 0 || nextX >= m || nextY >= n) {  
                        continue;  
                    }  
                    stop(grid, nextX, nextY);  
                }  
            }  
        }  
  }  
    
    
    
```    
    
### 个人解读    
  先说一下个人感觉：  
  1、根据题目设定元素，需要使用并查集。。。  
  2、根据算法要求，感觉像是个流水账题目。。。。  
    
  思路一：  湮灭法  
  不知道为啥没通过，Debug太麻烦了，以后有空再说吧  
  经过校验，是在max比较时候，匹配了错误的变量，属于低级错误。  
    
  参考答案：  
  1、使用了位运算应该是用作某种标记  
  2、通过最大值用来标记，而我习惯于用-1，-2  
  3、如果湮灭法还要不停的换值，不如直接使用标记法了  
  4、太迷信湮灭发了。。。。   
    
tags:    
  -  矩阵  
  -  DFS  
