### description      
  Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].    
      
  Return any permutation of A that maximizes its advantage with respect to B.    
      
       
      
  Example 1:    
      
  Input: A = [2,7,11,15], B = [1,10,4,11]    
  Output: [2,11,7,15]    
  Example 2:    
      
  Input: A = [12,24,8,32], B = [13,25,32,11]    
  Output: [24,32,8,12]    
       
      
  Note:    
      
  1 <= A.length = B.length <= 10000    
  0 <= A[i] <= 10^9    
  0 <= B[i] <= 10^9    
### solution      
```      
    
// 方法一 用double解决相同数字情况    
Runtime: 55 ms, faster than 62.35% of Java online submissions for Advantage Shuffle.    
Memory Usage: 42.7 MB, less than 83.33% of Java online submissions for Advantage Shuffle.    
    
  class Solution {    
       public int[] advantageCount(int[] A, int[] B) {    
          TreeMap<Double, Integer> bMap = new TreeMap<>();    
          double deep = 0.00001;    
          for(int i = 0; i < B.length; i++) {    
              double cur = B[i];    
              cur += deep;    
              deep += 0.00001;    
              bMap.put(cur, i);    
          }    
          int[] res = new int[A.length];    
          Arrays.fill(res, -1);    
          Set<Integer> unPut = new HashSet<>();    
      
          for(int i = 0; i < A.length; i++) {    
              double cur = A[i];    
              Double floor = bMap.floorKey(cur);    
              if(floor == null) {    
                  unPut.add(i);    
              } else {    
                  res[bMap.get(floor)] = A[i];    
                  bMap.remove(floor);    
              }    
          }    
          int index = 0;    
          for(int un: unPut) {    
              while(res[index] != -1) {    
                  index++;    
              }    
              res[index] = A[un];    
          }    
          return res;    
      
      }    
      
  }    
      
  // 方法二 用一个int[] 来映射B的索引关系，但是理解起来很绕圈    
  但这种使用int[]属于正统解法，并且在之后使用双指针遍历结果时候，明显比方法一的二分法要更有效率一些，不用每次都去找。  
  class Solution {    
      public int[] advantageCount(int[] A, int[] B) {    
          Arrays.sort(A);    
              
          Integer[] indexB = new Integer[B.length];    
          for (int i = 0; i < B.length; i++) {    
              indexB[i] = i;    
          }    
          Arrays.sort(indexB, new Comparator<Integer>() {    
              @Override    
              public int compare(Integer o1, Integer o2) {    
                  return B[o1] - B[o2];    
              }    
          });    
              
          int[] result = new int[A.length];    
              
          LinkedList<Integer> remaining = new LinkedList<>();    
          int j = 0; // index for indexB    
          for (int i = 0; i < A.length; i++) {    
              if (A[i] > B[indexB[j]]) {    
                  result[indexB[j]] = A[i];    
                  j++;    
              } else {    
                  remaining.offer(A[i]);    
              }    
          }    
              
          while (j < B.length) {    
              result[indexB[j]] = remaining.poll();    
              j++;    
          }    
              
          return result;    
      }    
  }    
```      
      
### 个人解读      
  类似于田忌赛马，让每张牌尽可能的堪堪比对方的牌高。    
  问题核心在于如何定制贪婪算法。    
  有两种：一种是遍历A，另一种是遍历B。    
      
  如果遍历A,然后放在比A最近且小于A的位置。这样子应该是贪婪算法了。    
  数据结构选择，可以用TreeMap存储B的位置信息。但是如果重复了怎么办。。。    
  有个捷径，可以用double的小说点来近似处理。    
      
      
tags:      
  -   数组     
  -   TreeMap     
