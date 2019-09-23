### description  
  In a group of N people (labelled 0, 1, 2, ..., N-1), each person has different amounts of money, and different levels of quietness.
  
  For convenience, we'll call the person with label x, simply "person x".
  
  We'll say that richer[i] = [x, y] if person x definitely has more money than person y.  Note that richer may only be a subset of valid observations.
  
  Also, we'll say quiet[x] = q if person x has quietness q.
  
  Now, return answer, where answer[x] = y if y is the least quiet person (that is, the person y with the smallest value of quiet[y]), among all people who definitely have equal to or more money than person x.
  
   
  
  Example 1:
  
  Input: richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
  Output: [5,5,2,5,4,5,6,7]
  Explanation: 
  answer[0] = 5.
  Person 5 has more money than 3, which has more money than 1, which has more money than 0.
  The only person who is quieter (has lower quiet[x]) is person 7, but
  it isn't clear if they have more money than person 0.
  
  answer[7] = 7.
  Among all people that definitely have equal to or more money than person 7
  (which could be persons 3, 4, 5, 6, or 7), the person who is the quietest (has lower quiet[x])
  is person 7.
  
  The other answers can be filled out with similar reasoning.
  Note:
  
  1 <= quiet.length = N <= 500
  0 <= quiet[i] < N, all quiet[i] are different.
  0 <= richer.length <= N * (N-1) / 2
  0 <= richer[i][j] < N
  richer[i][0] != richer[i][1]
  richer[i]'s are all different.
  The observations in richer are all logically consistent.
### solution  
```  
Runtime: 11 ms, faster than 58.24% of Java online submissions for Loud and Rich.
Memory Usage: 61.4 MB, less than 50.00% of Java online submissions for Loud and Rich.

  class Solution {
     HashMap<Integer, List<Integer>> richer2 = new HashMap<>();
      int res[];
  
      public int[] loudAndRich(int[][] richer, int[] quiet) {
          int n = quiet.length;
          for (int i = 0; i < n; ++i) richer2.put(i, new ArrayList<Integer>());
          for (int[] v : richer) richer2.get(v[1]).add(v[0]);
          res = new int[n];
          Arrays.fill(res, -1);
          for (int i = 0; i < n; i++) dfs(i, quiet);
          return res;
      }
  
      int dfs(int i, int[] quiet) {
          if (res[i] >= 0) return res[i];
          res[i] = i;
          for (int j : richer2.get(i)) if (quiet[res[i]] > quiet[dfs(j, quiet)]) res[i] = res[j];
          return res[i];
      }
  }
```  
  
### 个人解读  
  后面的题目真是越来越啰嗦了。。。
  
  看懂了题目描述以后，第一个反应像是拓扑排序。
  类似于悲观处理法，先把当前值放在res里面作为暂用值，问题是能否直接因为res[i]>0就直接return回去。
  
  知道怎么解决这个疑问了，疑虑在于，在调用DFS的时候，还未处理完毕就被提交回去了。
  DFS调用有两个地方：
  1、主函数全部遍历
  2、递归函数遍历途中
  
  主函数中暂且不需要理会，在递归函数中，假设当前变量是i，之后再调用的却不可能是i，并且假设i调用了j，但是之后j不会调用i。
  
tags:  
  -  数学
  -  dfs
