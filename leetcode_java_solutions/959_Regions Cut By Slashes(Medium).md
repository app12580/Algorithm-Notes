### description    
  In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.  These characters divide the square into contiguous regions.  
    
  (Note that backslash characters are escaped, so a \ is represented as "\\".)  
    
  Return the number of regions.  
    
     
    
  Example 1:  
    
  Input:  
  [  
    " /",  
    "/ "  
  ]  
  Output: 2  
  Explanation: The 2x2 grid is as follows:  
    
  Example 2:  
    
  Input:  
  [  
    " /",  
    "  "  
  ]  
  Output: 1  
  Explanation: The 2x2 grid is as follows:  
    
  Example 3:  
    
  Input:  
  [  
    "\\/",  
    "/\\"  
  ]  
  Output: 4  
  Explanation: (Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.)  
  The 2x2 grid is as follows:  
    
  Example 4:  
    
  Input:  
  [  
    "/\\",  
    "\\/"  
  ]  
  Output: 5  
  Explanation: (Recall that because \ characters are escaped, "/\\" refers to /\, and "\\/" refers to \/.)  
  The 2x2 grid is as follows:  
    
  Example 5:  
    
  Input:  
  [  
    "//",  
    "/ "  
  ]  
  Output: 3  
  Explanation: The 2x2 grid is as follows:  
    
     
    
  Note:  
    
  1 <= grid.length == grid[0].length <= 30  
  grid[i][j] is either '/', '\', or ' '.  
### solution    
```    
Runtime: 5 ms, faster than 79.18% of Java online submissions for Regions Cut By Slashes.
Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Regions Cut By Slashes.


    class Solution {
       
    //    top is 0, right is 1, bottom is 2 left is 3.
        int count, n;
        int[] f;
        public int regionsBySlashes(String[] grid) {
            n = grid.length;
            f = new int[n * n * 4];
            count = n * n * 4;
            for (int i = 0; i < n * n * 4; ++i)
                f[i] = i;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (i > 0) union(g(i - 1, j, 2), g(i, j, 0));
                    if (j > 0) union(g(i , j - 1, 1), g(i , j, 3));
                    if (grid[i].charAt(j) != '/') {
                        union(g(i , j, 0), g(i , j,  1));
                        union(g(i , j, 2), g(i , j,  3));
                    }
                    if (grid[i].charAt(j) != '\\') {
                        union(g(i , j, 0), g(i , j,  3));
                        union(g(i , j, 2), g(i , j,  1));
                    }
                }
            }
            return count;
        }
    
        public int find(int x) {
            if (x != f[x]) {
                f[x] = find(f[x]);
            }
            return f[x];
        }
        public void union(int x, int y) {
            x = find(x); y = find(y);
            if (x != y) {
                f[x] = y;
                count--;
            }
        }
        public int g(int i, int j, int k) {
            return (i * n + j) * 4 + k;
        }
    
    }
```    
    
### 个人解读    
  本题目的核心在于，如何将图形化的东西转化成数字信息。  
  解决：把一个方格分成4个部分，然后可以用成岛的问题类似解决。  
    
  可以应用并查集的解决方案。  
    
tags:    
  -  模拟  
  -  并查集  
