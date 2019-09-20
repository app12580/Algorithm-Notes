### description    
  Given an array of unique integers, each integer is strictly greater than 1.  
    
  We make a binary tree using these integers and each number may be used for any number of times.  
    
  Each non-leaf node's value should be equal to the product of the values of it's children.  
    
  How many binary trees can we make?  Return the answer modulo 10 ** 9 + 7.  
    
  Example 1:  
    
  Input: A = [2, 4]  
  Output: 3  
  Explanation: We can make these trees: [2], [4], [4, 2, 2]  
  Example 2:  
    
  Input: A = [2, 4, 5, 10]  
  Output: 7  
  Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].  
     
    
  Note:  
    
  1 <= A.length <= 1000.  
  2 <= A[i] <= 10 ^ 9.  
### solution    
```    
  
//方法一：   
Runtime: 206 ms, faster than 5.28% of Java online submissions for Binary Trees With Factors.  
Memory Usage: 50.3 MB, less than 6.67% of Java online submissions for Binary Trees With Factors.  
  
  class Solution {  
      long big = (long)1e9+7;  
      public int numFactoredBinaryTrees(int[] A) {  
          Map<Integer, List<int[]>> map = new HashMap<>();  
          Set<Integer> set = new HashSet<>();  
          for(int a: A) {  
              set.add(a);  
          }  
          for(int i = A.length - 1; i >= 0; i--) {  
              int mult = A[i];  
              List<int[]> curList = new ArrayList<>();  
              map.put(mult, curList);  
              for(int a: set) {  
                  if(mult % a == 0 && set.contains(mult / a)) {  
                      curList.add(new int[]{a, mult/a});  
                  }  
              }  
          }  
          Map<Integer, Integer> midVal = new HashMap<>();  
          long res = 0;  
          for(int a: A) {  
              res = (helper(a, map, midVal) + res) % big;  
          }  
          return (int) res;  
      }  
    
      private int helper(int a, Map<Integer, List<int[]>> map, Map<Integer, Integer> midVal) {  
          if(midVal.containsKey(a)) {  
              return midVal.get(a);  
          }  
          int res = 1;  
          List<int[]> list = map.get(a);  
          for(int[] fact: list) {  
              long left = helper(fact[0], map, midVal) % big;  
              long right = helper(fact[1], map, midVal) % big;  
              long t = left * right % big;  
              res = (int) ((res + t) % big);  
          }  
          midVal.put(a, res);  
          return res;  
      }  
  }  
    
    
  //方法二： 代码优化，但效率差不多  
  优化点是先排序，然后就可以定向遍历，就可以通过数学法单方向的去获取结果。  
  效果就是一个Map就能搞定，而不需要中间的辅助函数  
  Runtime: 56 ms, faster than 22.44% of Java online submissions for Binary Trees With Factors.  
  Memory Usage: 38.5 MB, less than 100.00% of Java online submissions for Binary Trees With Factors.  
    
  class Solution {  
       public int numFactoredBinaryTrees(int[] A) {  
          long res = 0L, mod = (long)1e9 + 7;  
          Arrays.sort(A);  
          HashMap<Integer, Long> dp = new HashMap<>();  
          for (int i = 0; i < A.length; ++i) {  
              dp.put(A[i], 1L);  
              for (int j = 0; j < i; ++j)  
                  if (A[i] % A[j] == 0)  
                      dp.put(A[i], (dp.get(A[i]) + dp.get(A[j]) * dp.getOrDefault(A[i] / A[j], 0L)) % mod);  
              res = (res + dp.get(A[i])) % mod;  
          }  
          return (int) res;  
      }  
  }  
```    
    
### 个人解读    
  需要预处理，获取每个数字有的配对数量。  
  然后使用递归，参数是根节点的val。  
    
  大数据出错，估计是int超限的问题。  
  把big的int改成long就通过了。  
    
tags:    
  -  动态规划  
  -  数学  
  -  数字  
