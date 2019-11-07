### description    
  In a given integer array A, we must move every element of A to either list B or list C. (B and C initially start empty.)  
    
  Return true if and only if after such a move, it is possible that the average value of B is equal to the average value of C, and B and C are both non-empty.  
    
  Example :  
  Input:   
  [1,2,3,4,5,6,7,8]  
  Output: true  
  Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have the average of 4.5.  
  Note:  
    
  The length of A will be in the range [1, 30].  
  A[i] will be in the range of [0, 10000].  
### solution    
```    
  
// 官方解答  
Runtime: 22 ms, faster than 57.57% of Java online submissions for Split Array With Same Average.  
Memory Usage: 37.5 MB, less than 33.33% of Java online submissions for Split Array With Same Average.  
  
  import java.awt.Point;  
    
  class Solution {  
      public boolean splitArraySameAverage(int[] A) {  
          int N = A.length;  
          int S = 0;  
          for (int x: A) S += x;  
          if (N == 1) return false;  
    
          int g = gcd(S, N);  
          Point mu = new Point(-(S/g), N/g);  
          // A[i] -> fracAdd(A[i], mu)  
          List<Point> A2 = new ArrayList();  
          for (int x: A)  
              A2.add(fracAdd(new Point(x, 1), mu));  
    
          Set<Point> left = new HashSet();  
          left.add(A2.get(0));  
          for (int i = 1; i < N/2; ++i) {  
              Set<Point> left2 = new HashSet();  
              Point z = A2.get(i);  
              left2.add(z);  
              for (Point p: left) {  
                  left2.add(p);  
                  left2.add(fracAdd(p, z));  
              }  
              left = left2;  
          }  
    
          if (left.contains(new Point(0, 1))) return true;  
    
          Set<Point> right = new HashSet();  
          right.add(A2.get(N-1));  
          for (int i = N/2; i < N-1; ++i) {  
              Set<Point> right2 = new HashSet();  
              Point z = A2.get(i);  
              right2.add(z);  
              for (Point p: right) {  
                  right2.add(p);  
                  right2.add(fracAdd(p, z));  
              }  
              right = right2;  
          }  
    
          if (right.contains(new Point(0, 1))) return true;  
    
          Point sleft = new Point(0, 1);  
          for (int i = 0; i < N/2; ++i)  
              sleft = fracAdd(sleft, A2.get(i));  
    
          Point sright = new Point(0, 1);  
          for (int i = N/2; i < N; ++i)  
              sright = fracAdd(sright, A2.get(i));  
    
          for (Point ha: left) {  
              Point ha2 = new Point(-ha.x, ha.y);  
              if (right.contains(ha2) && (!ha.equals(sleft) || !ha2.equals(sright)))  
                  return true;  
          }  
          return false;  
      }  
    
      public Point fracAdd(Point A, Point B) {  
          int numer = A.x * B.y + B.x * A.y;  
          int denom = A.y * B.y;  
          int g = gcd(numer, denom);  
          numer /= g;  
          denom /= g;  
    
          if (denom < 0) {  
              numer *= -1;  
              denom *= -1;  
          }  
    
          return new Point(numer, denom);  
      }  
    
      public int gcd(int a, int b) {  
         if (b==0) return a;  
         return gcd(b, a%b);  
      }  
  }   
```    
    
### 个人解读    
  能否把数组二等分。  
  前面明明有直接K等分的题目，而且印象还是Medium。  
  这个二等分有啥玄机么？简便算法？  
    
  通用思路： DFS+回溯，而且印象先从大到小排序。  
    
  题目没读明白，要求两数组平均数相等，然而两数组长度并不一定相等。  
    
  看了discuss，真的是一个人一个思路，而且很难看懂他们在干啥。。。  
    
  关于：sum * i % n == 0 这个表达式的含义？  
  sum / n = sumI / i  
  因为sumI为正数， 所以 sum / n * i也为整数。  
    
  知道这点以后，就转化成了，求k个数字，使得和为avg * i。  
  接下来可以考虑用递归或者DP  
   
    
  Lee215的做法：  
  ```  
  Java (TLE for new test case):  
    
      public boolean splitArraySameAverage(int[] A) {  
          int n = A.length, s = Arrays.stream(A).sum();  
          for (int i = 1; i <= n / 2; ++i) if (s * i % n == 0 && find(A, s * i / n, i, 0)) return true;  
          return false;  
      }  
    
        // 含义： 找出k个int，使得这k个数字和是target， i是从几号索引开始  
      public boolean find(int[] A, int target, int k, int i) {  
          if (k == 0) return target == 0;  
          if (k + i > A.length) return false;  
          return find(A, target - A[i], k - 1, i + 1) || find(A, target, k, i + 1);  
      }  
  ```  
    
  本题目很考究各种数学转换，将问题转换成其他模型。  
    
  官方答案：  
  1、问题转化： 找出一个子区间，使得该子区间(非连续)的平均值与全部源数组的平均值相等、  
    将平均值转化成0，即把全部数字减去整体的平均数A   
      
    那个fracAdd看不懂，，，算了，不管了。。。  
    
tags:    
  -  重点数学  
  -  TODO  
