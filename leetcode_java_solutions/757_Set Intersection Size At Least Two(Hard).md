### description    
  An integer interval [a, b] (for integers a < b) is a set of all consecutive integers from a to b, including a and b.  
    
  Find the minimum size of a set S such that for every integer interval A in intervals, the intersection of S with A has size at least 2.  
    
  Example 1:  
  Input: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]  
  Output: 3  
  Explanation:  
  Consider the set S = {2, 3, 4}.  For each interval, there are at least 2 elements from S in the interval.  
  Also, there isn't a smaller size set that fulfills the above condition.  
  Thus, we output the size of this set, which is 3.  
  Example 2:  
  Input: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]  
  Output: 5  
  Explanation:  
  An example of a minimum sized set is {1, 2, 3, 4, 5}.  
  Note:  
    
  intervals will have length in range [1, 3000].  
  intervals[i] will have length 2, representing some integer interval.  
  intervals[i][j] will be an integer in [0, 10^8].  
    
  找出最小区间，使得与所有input的区间至少有两个交点  
### solution    
```    
// 方法一： 栈预处理 + 二值法  
通过最大和第二大两个数字指标作为集合标准  
Runtime: 17 ms, faster than 69.28% of Java online submissions for Set Intersection Size At Least Two.  
Memory Usage: 39.8 MB, less than 100.00% of Java online submissions for Set Intersection Size At Least Two.  
  
  class Solution {  
      public int intersectionSizeTwo(int[][] intervals) {  
          // Step1  
          Arrays.sort(intervals,(a,b)->((a[0]==b[0])?(-a[1]+b[1]):(a[0]-b[0])));  
          // 左端从小到大， 右边从大到小  
          Stack<int[]> st=new Stack<>();  
          for (int[] in:intervals)  
          {  
              // 如果栈顶的右端大于等于当前右端 <==> 栈顶的把当前区间包起来了  
              // 这样处理后，不存在两个区间，左边的值一样了  
              while (!st.isEmpty() && st.peek()[1]>=in[1]) st.pop();  
              st.push(in);  
          }  
          // Step2  
          int n=st.size();  
          int[][] a=new int[n][2];  
          // 下面的for循环相当于把stack展开成数组  
          for (int i=n-1;i>=0;i--)  
          {  
              a[i][0]=st.peek()[0];  
              a[i][1]=st.pop()[1];  
          }  
    
          //step3  
          int ans=2;  
          int p1=a[0][1]-1,p2=a[0][1];  //p1 表示第一个区间的第二大数字    p2表示第一个区间的最大数字  
          for (int i=1;i<n;i++)  
          {  
              boolean bo1=(p1>=a[i][0] && p1<=a[i][1]),bo2=(p2>=a[i][0] && p2<=a[i][1]);  
              //bo1: p1在当前区间里面 bo2: p2在当前区间里面  
              if (bo1 && bo2) continue;  
              if (bo2)  
              {  
                  //如果p1不在，但是p2在、 说明新的区间是[p2, 一个更大的数字]， 同时导致了p1 p2 分离  
                  // 为了让集合最小，此时只保留先前的p2，转变成p1，然后加入此时的最大值作为新的p2  
                  // [1,2]  [2,10]  
                  // 1,2 -> 2, 10  
                  p1=p2;  
                  p2=a[i][1];  
                  ans++;  
                  continue;  
              }  
              //如果p1p2都不在，或者p1在，p2不在  
              //针对p1在，p2不在的这种情况是不存在的：  
              //因为：如果p1 p2是连续的，那么除非[p1,p1]这种区间；  
              // 如果p1 p2 非连续，说明前面发生了跳跃，因为排序过和筛选过，新的区间的左端不可能经过p1。  
              // (因为p1p2分离的前提，就是因为新的区间是[p2, 一个更大的数字])  
              p1=a[i][1]-1;  
              p2=a[i][1];  
              ans+=2;  
          }  
          return ans;  
      }  
  }  
```    
    
### 个人解读    
  注意，要求集合可以不是连续的  
    
  参考答案：  
  https://leetcode.com/problems/set-intersection-size-at-least-two/discuss/113076/Java-O(nlogn)-Solution-Greedy  
    
  详情见代码  
  
  主要思路是：将区间排序后，最贪婪的做法就是让最大的两个数字作为交点  
    
tags:    
  -  区间  
  -  数学  
