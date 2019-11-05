### description    
  An N x N board contains only 0s and 1s. In each move, you can swap any 2 rows with each other, or any 2 columns with each other.  
    
  What is the minimum number of moves to transform the board into a "chessboard" - a board where no 0s and no 1s are 4-directionally adjacent? If the task is impossible, return -1.  
    
  Examples:  
  Input: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]  
  Output: 2  
  Explanation:  
  One potential sequence of moves is shown below, from left to right:  
    
  0110     1010     1010  
  0110 --> 1010 --> 0101  
  1001     0101     1010  
  1001     0101     0101  
    
  The first move swaps the first and second column.  
  The second move swaps the second and third row.  
    
    
  Input: board = [[0, 1], [1, 0]]  
  Output: 0  
  Explanation:  
  Also note that the board with 0 in the top left corner,  
  01  
  10  
    
  is also a valid chessboard.  
    
  Input: board = [[1, 0], [1, 0]]  
  Output: -1  
  Explanation:  
  No matter what sequence of moves you make, you cannot end with a valid chessboard.  
  Note:  
    
  board will have the same number of rows and columns, a number in the range [2, 30].  
  board[i][j] will be only 0s or 1s.  
### solution    
```    
  
// 方法一： 很巧妙的数学方法，到处都是技巧  
Runtime: 1 ms, faster than 100.00% of Java online submissions for Transform to Chessboard.  
Memory Usage: 43.5 MB, less than 92.86% of Java online submissions for Transform to Chessboard.  
  class Solution {  
       public int movesToChessboard(int[][] b) {  
           int N = b.length, rowSum = 0, colSum = 0, rowSwap = 0, colSwap = 0;  
           for (int i = 1; i < N; ++i) for (int j = 0; j < N; ++j)  
               if ((b[0][0] ^ b[i][0] ^ b[0][j] ^ b[i][j]) == 1) return -1;  
           // 每一行的时候，与i==0进行比较。判断第i行与0行比较是不是一致还是刚好相反。  
           // 四个^ 等于1，说明两个结果，一个是0，另一个是1，意味着 31分组  
           for (int i = 0; i < N; ++i) {  
               rowSum += b[0][i];  // 计算第一位是1的数量  
               colSum += b[i][0];  
               if (b[i][0] == i % 2) rowSwap ++;  // 如果第i行i为奇数且第一个为1  或者 偶数行，并且0开头 ， rowSwap++。  
               //奇数1偶数0 是匹配的，这种的行，要么不换，要么一起换  
               //这里的rowSwap只是某一种，后面会各种转换的  
               if (b[0][i] == i % 2) colSwap ++ ;  
           }  
           if (rowSum != N / 2 && rowSum != (N + 1) / 2) return -1;    // 如果10的数量不一致  
           if (colSum != N / 2 && colSum != (N + 1) / 2) return -1;  
           if (N % 2 == 1) {  
               if (colSwap % 2 == 1) colSwap = N - colSwap;    // 如果总量是奇数  
               if (rowSwap % 2 == 1) rowSwap = N - rowSwap;  
           } else {  
               colSwap = Math.min(N - colSwap, colSwap);  
               rowSwap = Math.min(N - rowSwap, rowSwap);  
           }  
           return colSwap / 2 + rowSwap / 2; //可能一个为偶数，一个为奇数  
       }  
  }  
```    
    
### 个人解读    
    
  几点基本的对策：  
  1、先列再横向 或者 先横再列  
  2、1111这种，无论怎么调换都分不开  
  3、前提：每一行每一列都要有一半是0，一半是1  
  4、当把列弄好了以后，剩下的就很好解决了。类似于奇数偶数排序。用双指针操作。  
  5、猜测：只要满足条件3，就一定能出结果  
  猜测失败  
  1010  0110  
  0110  1010  
  1001  0101  
  0101  1001  
  6、每次调换都是一个奇数和一个偶数调换  
    
  问题的难点：  
  1、以下两点必须找到其中一个好的解决办法：要么一个好的数学指标，直接知道结果； 要么一个好的遍历方案、  
    
  最数学的解法  
  https://leetcode.com/problems/transform-to-chessboard/discuss/114847/C%2B%2BJavaPython-Solution-with-Explanation  
  直觉：  
    
  有助于解决此问题的两个条件：  
    
  在一个有效的棋盘中，只有两种行，一种与另一种相反。  
  例如，如果板中有一行01010011，则任何其他行都必须是01010011或10101100。  
  同样适用于列  
  一个推论是，板内的任何一个有左上角、右上角、左下角、右下角的矩形必须是4 0或2 1 2 0或4 0。  
    
  另一个重要的特性是每一行和每一列都有一半的行和列。假设董事会为n*n：  
  如果N=2*K，则每行每列都有K个一和K个零。  
  如果N=2*K+1，则每行每列都有K个一和K+1个零或K+1个一和K个零。  
    
    
tags:    
  -  数字逻辑  
  -  矩阵  
  -  重点数学  
