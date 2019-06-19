### description    
  The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.  
    
    
    
  Given an integer n, return all distinct solutions to the n-queens puzzle.  
    
  Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.  
    
  Example:  
    
  Input: 4  
  Output: [  
   [".Q..",  // Solution 1  
    "...Q",  
    "Q...",  
    "..Q."],  
    
   ["..Q.",  // Solution 2  
    "Q...",  
    "...Q",  
    ".Q.."]  
  ]  
  Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.  
    
  N皇后问题  
### solution    
```    
  // 方法一 自己写的  
  Runtime: 5 ms, faster than 40.90% of Java online submissions for N-Queens.  
  Memory Usage: 36.6 MB, less than 99.96% of Java online submissions for N-Queens.  
    
  class Solution {  
      public List<List<String>> solveNQueens(int n) {  
          List<List<String>> res = new ArrayList<>();  
          if(n <= 0) {  
              return res;  
          }  
          List<Integer> list = new ArrayList<>();  
          List<List<Integer>> intRes = new ArrayList<>();  
          dfs(list, intRes, n);  
    
          for(List<Integer> l: intRes) {  
              List<String> strList = new ArrayList<>();  
              for(int i = 0; i < n; i++) {  
                  StringBuilder builder = new StringBuilder();  
                  Integer curIndex = l.get(i);  
                  for(int j = 0; j < n; j++) {  
                      if(curIndex == j) {  
                          builder.append("Q");  
                      } else {  
                          builder.append(".");  
                      }  
                  }  
                  strList.add(builder.toString());  
              }  
              res.add(strList);  
          }  
          return res;  
      }  
    
      private void dfs(List<Integer> list, List<List<Integer>> intRes, int n) {  
          if(list.size() == n) {  
              intRes.add(new ArrayList<>(list));  
              return;  
          }  
          for(int i = 0; i < n; i++) {  
              if(check(i, list)) {  
                  list.add(i);  
                  dfs(list, intRes, n);  
                  list.remove(list.size() - 1);  
              }  
          }  
      }  
    
      private boolean check(int add, List<Integer> curList) {  
          if(curList == null) {  
              return true;  
          }  
          int size = curList.size();  
          int j = size;  
          for(int i = 0; i < size; i++) {  
              int num = curList.get(i);  
              if(add == num || Math.abs(i - j) == Math.abs(add - num)) {  
                  return false;  
              }  
          }  
          return true;  
      }  
  }  
    
  // 方法二 CYC参考答案  
  Runtime: 1 ms, faster than 100.00% of Java online submissions for N-Queens.  
  Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for N-Queens.  
    
    
  class Solution {  
      private List<List<String>> solutions;  
  private char[][] nQueens;  
  private boolean[] colUsed;  
  private boolean[] diagonals45Used;  
  private boolean[] diagonals135Used;  
  private int n;  
    
  public List<List<String>> solveNQueens(int n) {  
      solutions = new ArrayList<>();  
      nQueens = new char[n][n];  
      for (int i = 0; i < n; i++) {  
          Arrays.fill(nQueens[i], '.');  
      }  
      colUsed = new boolean[n];  
      diagonals45Used = new boolean[2 * n - 1];  
      diagonals135Used = new boolean[2 * n - 1];  
      this.n = n;  
      backtracking(0);  
      return solutions;  
  }  
    
  private void backtracking(int row) {  
      if (row == n) {  
          List<String> list = new ArrayList<>();  
          for (char[] chars : nQueens) {  
              list.add(new String(chars));  
          }  
          solutions.add(list);  
          return;  
      }  
    
      for (int col = 0; col < n; col++) {  
          int diagonals45Idx = row + col;  
          int diagonals135Idx = n - 1 - (row - col);  
          if (colUsed[col] || diagonals45Used[diagonals45Idx] || diagonals135Used[diagonals135Idx]) {  
              continue;  
          }  
          nQueens[row][col] = 'Q';  
          colUsed[col] = diagonals45Used[diagonals45Idx] = diagonals135Used[diagonals135Idx] = true;  
          backtracking(row + 1);  
          colUsed[col] = diagonals45Used[diagonals45Idx] = diagonals135Used[diagonals135Idx] = false;  
          nQueens[row][col] = '.';  
      }  
  }  
  }  
    
    
```    
    
### 个人解读    
  比起看每一个格子是否能下皇后，还不如直接比较已经下好的棋子，要不然回溯都不好回溯。  
    
  两个优化点：   
  创建一个2n-1的boolean数组，存储斜对线的结果。以斜线为结果存储下来，就不用每次都去判断了。  
  使用char[][]  
    
    
tags:    
  -  DFS  
  -  n皇后  
  -  回溯  
  -  优化  
