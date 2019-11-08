### description  
  There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
  
  Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:
  
  Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
  Every worker in the paid group must be paid at least their minimum wage expectation.
  Return the least amount of money needed to form a paid group satisfying the above conditions.
  
   
  
  Example 1:
  
  Input: quality = [10,20,5], wage = [70,50,30], K = 2
  Output: 105.00000
  Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
  Example 2:
  
  Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
  Output: 30.66667
  Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately. 
   
  
  Note:
  
  1 <= K <= N <= 10000, where N = quality.length = wage.length
  1 <= quality[i] <= 10000
  1 <= wage[i] <= 10000
  Answers within 10^-5 of the correct answer will be considered correct.
### solution  
```  
// 方法一： 数学模型：
Runtime: 27 ms, faster than 82.41% of Java online submissions for Minimum Cost to Hire K Workers.
Memory Usage: 39.8 MB, less than 44.44% of Java online submissions for Minimum Cost to Hire K Workers.

  class Solution {
      public double mincostToHireWorkers(int[] q, int[] w, int K) {
          double[][] workers = new double[q.length][2];
          for (int i = 0; i < q.length; ++i)
              workers[i] = new double[]{(double)(w[i]) / q[i], (double)q[i]};
          Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
          double res = Double.MAX_VALUE, qsum = 0;
          PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
          for (double[] worker: workers) {
              qsum += worker[1];
              pq.add(worker[1]);
              if (pq.size() > K) qsum -= pq.poll();
              if (pq.size() == K) res = Math.min(res, qsum * worker[0]);
          }
          return res;
      }
  }
```  
  
### 个人解读  
  wi:wj = qi:qj ==》 wi:qi = wj:qj
  所以可以根据wi:qi来作为一个指标，并且这个值越小越好，说明小工资干大事。但是光这一个指标并不能完全取代原题目描述啊。
  
  总结：
  1、rate=w/q的解读：同一雇佣的员工，w/q的值都一样所以可以理解成甲方指定的q的单价
  2、因为每个员工的薪水   rate * qi  要大于心理预期(w期待)； rate >= w期待 / qi
  所以w总 = r * (q1 + q2 + ... + qn)
  所以w总 = max(wi/qi) * q总
  
  可以理解成每一个worker都要求一个最低的wi/qi
  
  3、因此，按照前面所述，将wi/qi按照从小到大排序，每K个人中，wi/qi最大的那个具有话语权，他能决定最大的薪水rate是多少，此时为了总薪水最低，因此需要总q最小，所以用一个pq来进行排序。每次从前面的worker中，把q最大的给踢出去
  
  
  只能时候TQL。。。
  
tags:  
  -  重点数学 
  -  贪婪算法 
