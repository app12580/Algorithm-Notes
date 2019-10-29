### description    
  Given several boxes with different colors represented by different positive numbers.  
  You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.  
  Find the maximum points you can get.  
    
  Example 1:  
  Input:  
    
  [1, 3, 2, 2, 2, 3, 4, 3, 1]  
  Output:  
  23  
  Explanation:  
  [1, 3, 2, 2, 2, 3, 4, 3, 1]   
  ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)   
  ----> [1, 3, 3, 3, 1] (1*1=1 points)   
  ----> [1, 1] (3*3=9 points)   
  ----> [] (2*2=4 points)  
  Note: The number of boxes n would not exceed 100.  
### solution    
```    
// 方法一： 特殊三维DP模型  
Runtime: 26 ms, faster than 43.08% of Java online submissions for Remove Boxes.  
Memory Usage: 50.6 MB, less than 100.00% of Java online submissions for Remove Boxes.  
  
  class Solution {  
    
      public int removeBoxes(int[] boxes) {  
          int[][][] dp = new int[100][100][100];  
          return calculatePoints(boxes, dp, 0, boxes.length - 1, 0);  
      }  
    
      public int calculatePoints(int[] boxes, int[][][] dp, int l, int r, int k) {  
          if (l > r) return 0;  
          if (dp[l][r][k] != 0) return dp[l][r][k];  
          while (r > l && boxes[r] == boxes[r - 1]) {  
              r--;  
              k++;  
          }  
          dp[l][r][k] = calculatePoints(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);  
          for (int i = l; i < r; i++) {  
              if (boxes[i] == boxes[r]) {  
                  dp[l][r][k] = Math.max(dp[l][r][k], calculatePoints(boxes, dp, l, i, k + 1) + calculatePoints(boxes, dp, i + 1, r - 1, 0));  
              }  
          }  
          return dp[l][r][k];  
      }  
  }  
     
```    
    
### 个人解读    
  类似于祖玛的那个题目[488](488_Zuma%20Game(Hard).md)  
    
  然而类似于[312](312_Burst%20Balloons(Hard).md)，核心问题，根据区间外的状态无关DP  
  https://leetcode-cn.com/problems/remove-boxes/solution/yi-chu-he-zi-by-leetcode/  
    
  DFS+回溯  
  问题是用什么数据结构描述，有必要搞一个String么？还是用数组？  
  先用字符串测试一下吧:TLE了。。  
    
  ```  
  TLE   
    public int removeBoxes(int[] boxes) {  
          StringBuilder builder = new StringBuilder();  
          for(int b: boxes) builder.append((char)b);  
          return dfs(builder.toString());  
      }  
    
      private int dfs(String s) {  
          if(s.length() == 0) return 0;  
          char[] chars = s.toCharArray();  
          int res = 0;  
          for(int i = 0; i < chars.length; i++) {  
              int left = i;   // left 0, i: 1  
              while(i < s.length() - 1 && chars[i] == chars[i+1]) {  
                  i++;  
              }  
              String tail = (i + 1 == s.length()) ? "":s.substring(i + 1);  
              int next = (i - left + 1) * (i - left + 1) + dfs(s.substring(0, left) + tail);  
              res = Math.max(res, next);  
          }  
          return res;  
      }  
        
      // 数组写法  
        
public class Solution {  
    public int removeBoxes(int[] boxes) {  
        return remove(boxes);  
    }  
    public int remove(int[] boxes)  
    {  
        if(boxes.length==0)  
            return 0;  
        int res=0;  
        for(int i=0,j=i+1;i<boxes.length;i++)  
        {  
            while(j<boxes.length && boxes[i]==boxes[j])  
                j++;  
            int[] newboxes=new int[boxes.length-(j-i)];  
            for(int k=0,p=0;k<boxes.length;k++)  
            {  
                if(k==i)  
                    k=j;  
                if(k<boxes.length)  
                    newboxes[p++]=boxes[k];  
            }  
            res=Math.max(res,remove(newboxes)+(j-i)*(j-i));  
        }  
        return res;  
    }  
}  
   
  ```  
    
tags:    
  -  重点题目  
  -  DP特殊建模  
