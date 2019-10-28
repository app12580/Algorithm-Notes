### description    
  In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring", and use the dial to spell a specific keyword in order to open the door.  
    
  Given a string ring, which represents the code engraved on the outer ring and another string key, which represents the keyword needs to be spelled. You need to find the minimum number of steps in order to spell all the characters in the keyword.  
    
  Initially, the first character of the ring is aligned at 12:00 direction. You need to spell all the characters in the string key one by one by rotating the ring clockwise or anticlockwise to make each character of the string key aligned at 12:00 direction and then by pressing the center button.  
    
  At the stage of rotating the ring to spell the key character key[i]:  
    
  You can rotate the ring clockwise or anticlockwise one place, which counts as 1 step. The final purpose of the rotation is to align one of the string ring's characters at the 12:00 direction, where this character must equal to the character key[i].  
  If the character key[i] has been aligned at the 12:00 direction, you need to press the center button to spell, which also counts as 1 step. After the pressing, you could begin to spell the next character in the key (next stage), otherwise, you've finished all the spelling.  
  Example:  
    
    
     
  Input: ring = "godding", key = "gd"  
  Output: 4  
  Explanation:  
  For the first key character 'g', since it is already in place, we just need 1 step to spell this character.   
  For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".  
  Also, we need 1 more step for spelling.  
  So the final output is 4.  
  Note:  
    
  Length of both ring and key will be in range 1 to 100.  
  There are only lowercase letters in both strings and might be some duplcate characters in both strings.  
  It's guaranteed that string key could always be spelled by rotating the string ring.  
### solution    
```    
  
// 方法一： DP O(m * n* n)  
Runtime: 30 ms, faster than 42.38% of Java online submissions for Freedom Trail.  
Memory Usage: 35.7 MB, less than 100.00% of Java online submissions for Freedom Trail.  
public class Solution {  
   public int findRotateSteps(String ring, String key) {  
        int m = key.length();  
        int n = ring.length();  
        int[][] dp = new int[m + 1][n];   
        //dp[i][j]表示要想完成从ring的索引j开始，并且前面key.subString(0,i)的所有已经完成了，还需要走多少步(此时匹配key.charAt(i))。  
        //dp[m][j]这一组，全是0  
  
        for (int i = m - 1; i >= 0; i--) { //i表示目标从后往前的字符索引  
            for (int j = 0; j < n; j++) {  
                dp[i][j] = Integer.MAX_VALUE;  
                for (int k = 0; k < n; k++) {  
                    //对每一个可能的k进行循环，dp[i][j] 要走上step步，然后到达dp[i+1][k]  
                    if (ring.charAt(k) == key.charAt(i)) {  
                        int diff = Math.abs(j - k);  
                        int step = Math.min(diff, n - diff);  
                        dp[i][j] = Math.min(dp[i][j], step + dp[i + 1][k]);  
                    }  
                }  
            }  
        }  
  
        return dp[0][0] + m;  
    }  
}  
  
// 方法二： 优化: 预处理出来每ring中每个字符到达目标字符所需要的步数  
看不懂。。。不管了。。。  
https://leetcode.com/problems/freedom-trail/discuss/98902/Concise-Java-DP-Solution  
  
Runtime: 11 ms, faster than 76.68% of Java online submissions for Freedom Trail.  
Memory Usage: 36.7 MB, less than 100.00% of Java online submissions for Freedom Trail.  
    
  public class Solution {  
       public int findRotateSteps(String ring, String key) {  
              int n = ring.length(), m = key.length();  
              int[][] dp = new int[m + 1][n];  
              int[][] clock = preproc(ring, 1), anti = preproc(ring, -1);  
              for (int i = m - 1; i >= 0; --i) {  
                  int idx = key.charAt(i) - 'a';  
                  for (int j = 0; j < n; ++j) { // fill dp[i][j]  
                      int p = clock[j][idx];  
                      int q = anti[j][idx];  
                      dp[i][j] = Math.min(dp[i + 1][p] + (j + n - p) % n, dp[i + 1][q] + (q + n - j) % n);  
                  }  
              }  
              return dp[0][0] + m;  
          }  
          int[][] preproc(String r, int inc) {  
              int n = r.length();  
              int[][] ans = new int[n][26];  
              int[] map = new int[26];  
              for (int i = 0, j = 0; j < n * 2 - 1; ++j) {  
                  map[r.charAt(i) - 'a'] = i;  
                  System.arraycopy(map, 0, ans[i], 0, 26);  
                  i = (i + inc + n) % n;  
              }  
              return ans;  
          }        
  }  
    
```    
    
### 个人解读    
  最大的问题就是遇到重复字符怎么办？全部DFS+回溯么，这样担心会导致复杂度过高啊。  
    
  如果要优化成DP的话，很难想。。。  
  难道说Hard题目主要是在考如何建立DP模型么。。。  
    
tags:    
  -  DFS优化DP  
  -  重点题目  
  -  逆向思维  
