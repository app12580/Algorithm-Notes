### description    
  Given a non-empty array of unique positive integers A, consider the following graph:  
    
  There are A.length nodes, labelled A[0] to A[A.length - 1];  
  There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.  
  Return the size of the largest connected component in the graph.  
    
     
    
  Example 1:  
    
  Input: [4,6,15,35]  
  Output: 4  
    
  Example 2:  
    
  Input: [20,50,9,63]  
  Output: 2  
    
  Example 3:  
    
  Input: [2,3,6,7,4,12,21,39]  
  Output: 8  
    
  Note:  
    
  1 <= A.length <= 20000  
  1 <= A[i] <= 100000  
### solution    
```    
// 方法一： 并查集  
  
Runtime: 147 ms, faster than 71.60% of Java online submissions for Largest Component Size by Common Factor.  
Memory Usage: 51.8 MB, less than 71.43% of Java online submissions for Largest Component Size by Common Factor.  
  
  class Solution {  
      public int largestComponentSize(int[] A) {  
          int N = A.length;  
    
          // factored[i] = a list of unique prime factors of A[i]  
          ArrayList<Integer>[] factored = new ArrayList[N];  
          for (int i = 0; i < N; ++i) {  
              factored[i] = new ArrayList<Integer>();  
              int d = 2, x = A[i];  
              while (d * d <= x) {  
                  if (x % d == 0) {  
                      while (x % d == 0)  
                          x /= d;  
                      factored[i].add(d);  
                  }  
    
                  d++;  
              }  
    
              if (x > 1 || factored[i].isEmpty())  
                  factored[i].add(x);  
          }  
    
          // primesL : a list of all primes that occur in factored  
          Set<Integer> primes = new HashSet();  
          for (List<Integer> facs: factored)  
              for (int x: facs)  
                  primes.add(x);  
    
          int[] primesL = new int[primes.size()];  
          int t = 0;  
          for (int x: primes)  
              primesL[t++] = x;   // 里面存储每个质因数的列表  
    
          // primeToIndex.get(v) == i  iff  primes[i] = v  
          Map<Integer, Integer> primeToIndex = new HashMap(); //每个质因数的索引映射  
          for (int i = 0; i < primesL.length; ++i)  
              primeToIndex.put(primesL[i], i);  
    
          DSU dsu = new DSU(primesL.length);  
          for (List<Integer> facs: factored)  
              for (int x: facs)  
                  dsu.union(primeToIndex.get(facs.get(0)), primeToIndex.get(x));  //对每个数字内部的所有质因数进行合并  
    
          int[] count = new int[primesL.length];  
          for (List<Integer> facs: factored){  
              // 每个数字只需要在第一个因数那里进行数字+1即可  
              int tt = dsu.find(primeToIndex.get(facs.get(0)));  
              count[tt]++;  
          }  
    
          int ans = 0;  
          for (int x: count)  
              if (x > ans)  
                  ans = x;  
          return ans;  
      }  
    
      class DSU {  
          int[] parent;  
          public DSU(int N) {  
              parent = new int[N];  
              for (int i = 0; i < N; ++i)  
                  parent[i] = i;  
          }  
          public int find(int x) {  
              if (parent[x] != x) parent[x] = find(parent[x]);  
              return parent[x];  
          }  
          public void union(int x, int y) {  
              parent[find(x)] = find(y);  
          }  
      }  
    
  }  
     
```    
    
### 个人解读    
  并查集就完事了。  
    
  思路一： 并查集  
  结果TLE了。。。  
    
  思路二： 不是针对源数组中的数字，而是针每个数字的因数。  
  1、先获取每个数字的因数列表，  
  2、然后只union质因数，而不进行size计算  
  3、遍历源数组，只对第一个质因数所在的并查集进行  
    
  主要关键在于size计算，union里面不要计算大小。  
    
tags:    
  -  并查集  
