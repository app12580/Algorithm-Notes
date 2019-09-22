### description    
  We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job.   
    
  Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with difficulty at most worker[i].   
    
  Every worker can be assigned at most one job, but one job can be completed multiple times.  
    
  For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, his profit is $0.  
    
  What is the most profit we can make?  
    
  Example 1:  
    
  Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]  
  Output: 100   
  Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] seperately.  
  Notes:  
    
  1 <= difficulty.length = profit.length <= 10000  
  1 <= worker.length <= 10000  
  difficulty[i], profit[i], worker[i]  are in range [1, 10^5]  
### solution    
```    
Runtime: 58 ms, faster than 37.19% of Java online submissions for Most Profit Assigning Work.  
Memory Usage: 40 MB, less than 77.78% of Java online submissions for Most Profit Assigning Work.  
  
  
  import javafx.util.Pair;  
    
  class Solution {  
       public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {  
          List<Pair<Integer, Integer>> jobs = new ArrayList<>();  
          int N = profit.length, res = 0, i = 0, best = 0;  
          for (int j = 0; j < N; ++j)  
              jobs.add(new Pair<Integer, Integer>(difficulty[j], profit[j]));  
          Collections.sort(jobs, Comparator.comparing(Pair::getKey));  
          Arrays.sort(worker);  
          for (int ability : worker) {  
              while (i < N && ability >= jobs.get(i).getKey())  
                  best = Math.max(jobs.get(i++).getValue(), best);  
              res += best;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  如果以worker的能力为基准，需要从符合难度中的收入最高的。  
    
  首先需要预处理，难度高但是收入低的给去掉。  
  然后为了速率考虑是否使用二分法。  
    
  方法总结，控制best为从小到大阶段性的最优解，这样就可以不用每次都要从头遍历，也不必要使用二分法去找了。  
    
  另外本题目中还使用了两个小技巧：  
  1、Pair的使用  
  2、java新特性  
  3、预处理与过程中筛选  
    
tags:    
  -  数学  
  -  阶段变量  
