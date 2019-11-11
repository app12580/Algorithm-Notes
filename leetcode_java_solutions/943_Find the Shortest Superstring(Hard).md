### description    
  Given an array A of strings, find any smallest string that contains each string in A as a substring.  
    
  We may assume that no string in A is substring of another string in A.  
    
     
  Example 1:  
    
  Input: ["alex","loves","leetcode"]  
  Output: "alexlovesleetcode"  
  Explanation: All permutations of "alex","loves","leetcode" would also be accepted.  
  Example 2:  
    
  Input: ["catg","ctaagt","gcta","ttca","atgcatc"]  
  Output: "gctaagttcatgcatc"  
     
    
  Note:  
    
  1 <= A.length <= 12  
  1 <= A[i].length <= 20  
### solution    
```    
  / 方法一： 动态规划  
  Runtime: 29 ms, faster than 43.18% of Java online submissions for Find the Shortest Superstring.  
  Memory Usage: 38.3 MB, less than 100.00% of Java online submissions for Find the Shortest Superstring.  
    
  class Solution {  
        public String shortestSuperstring(String[] A) {  
          int n = A.length;  
          int[][] graph = new int[n][n];  
          // build the graph  
          for (int i = 0; i < n; i++) {  
              for (int j = 0; j < n; j++) {  
                  graph[i][j] = calc(A[i], A[j]);  
                  graph[j][i] = calc(A[j], A[i]);  
              }  
          }  
          int[][] dp = new int[1 << n][n];    //dp[i][j] i表示需要访问的点集合，并且j是最后一个访问时候的最小长度  
          int[][] path = new int[1 << n][n];  //[i][j] i表示访问状态， j之前的最后一个节点  
  //        路径[44(0,2,3,5)][2]表示“我们旅行2之前的最后一个节点。换句话说，当移动{0,3,5}时的最后一个节点  
          int last = -1, min = Integer.MAX_VALUE;  
    
          // start TSP DP  
          for (int i = 1; i < (1 << n); i++) { // for all the combinations of the nodes  
              Arrays.fill(dp[i], Integer.MAX_VALUE);// the length =MAX_VALUE  
              for (int j = 0; j < n; j++) { //for each node  
                  if ((i & (1 << j)) > 0) {      // if the node is in the set. Assume i = 10010(18), j = 100(4), then set={1,4}, the node is 2. The node is not in this set  
                      int prev = i - (1 << j);      // the set without j. Assume i = 10010, j = 10 then pre = 10000  
                      if (prev == 0) {            // if j is the only one  
                          dp[i][j] = A[j].length();  // the whole word  
                      } else {  
                          for (int k = 0; k < n; k++) {    //try all the possible nodes before j  
                              if (dp[prev][k] < Integer.MAX_VALUE && dp[prev][k] + graph[k][j] < dp[i][j]) { // if k is valid and the length could be reduced  
                                  dp[i][j] = dp[prev][k] + graph[k][j];   //update the result  
                                  path[i][j] = k; // update the node before j  
                              }  
                          }  
                      }  
                  }  
                  if (i == (1 << n) - 1 && dp[i][j] < min) {  // if i == 11...1111 means the node set contains all the nodes, and the length is smaller  
                      min = dp[i][j];  //update the result  
                      last = j;   //update the last node  
                  }  
              }  
          }  
    
          // build the path  
          StringBuilder sb = new StringBuilder();  
          int cur = (1 << n) - 1;  
          Stack<Integer> stack = new Stack<>();  
          while (cur > 0) {  
              stack.push(last);  
              int temp = cur;  
              cur -= (1 << last);  
              last = path[temp][last];  
          }  
    
          // build the result  
          int i = stack.pop();  
          sb.append(A[i]);  
          while (!stack.isEmpty()) {  
              int j = stack.pop();  
              sb.append(A[j].substring(A[j].length() - graph[i][j]));  
              i = j;  
          }  
          return sb.toString();  
      }  
    
      // a + b ，a后面需要加上b的个数  
      private int calc(String a, String b) {  
          for (int i = 1; i < a.length(); i++) {  
              if (b.startsWith(a.substring(i))) {  
                  return b.length() - a.length() + i;  
              }  
          }  
          return b.length();  
      }  
    
  }  
    
```    
    
### 个人解读    
  稍微想了想，有两个思路：一个是将所有数据装填进一个Trie里面；另一个是类似[753](753_Cracking%20the%20Safe(Hard).md)，将不同的字符串之间做连接，然后转化成图论问题(最小路径和问题)。  
    
  在不知道TSP问题的前提下，能意识到和图论有关，唉，感觉已经足够了把。  
    
  讨论区也是一股哀嚎，都觉得难。  
  
  重点题目，主要是DP模型的建立。  
    
tags:    
  -  重点题目  
  -  TSP问题  
  -  动态规划  
  -  多点同步  
