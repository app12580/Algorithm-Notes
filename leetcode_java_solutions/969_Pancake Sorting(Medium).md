### description    
  Given an array A, we can perform a pancake flip: We choose some positive integer k <= A.length, then reverse the order of the first k elements of A.  We want to perform zero or more pancake flips (doing them one after another in succession) to sort the array A.  
    
  Return the k-values corresponding to a sequence of pancake flips that sort A.  Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.  
    
     
    
  Example 1:  
    
  Input: [3,2,4,1]  
  Output: [4,2,4,3]  
  Explanation:   
  We perform 4 pancake flips, with k values 4, 2, 4, and 3.  
  Starting state: A = [3, 2, 4, 1]  
  After 1st flip (k=4): A = [1, 4, 2, 3]  
  After 2nd flip (k=2): A = [4, 1, 2, 3]  
  After 3rd flip (k=4): A = [3, 2, 1, 4]  
  After 4th flip (k=3): A = [1, 2, 3, 4], which is sorted.   
  Example 2:  
    
  Input: [1,2,3]  
  Output: []  
  Explanation: The input is already sorted, so there is no need to flip anything.  
  Note that other answers, such as [3, 3], would also be accepted.  
     
    
  Note:  
    
  1 <= A.length <= 100  
  A[i] is a permutation of [1, 2, ..., A.length]  
### solution    
```    
Runtime: 1 ms, faster than 100.00% of Java online submissions for Pancake Sorting.  
Memory Usage: 36.5 MB, less than 94.74% of Java online submissions for Pancake Sorting.  
  
  class Solution {  
     public List<Integer> pancakeSort(int[] A) {  
          int len = A.length;  
          List<Integer> res = new ArrayList<>();  
          for(int x = len, i; x >= 2; x--) {  
              for(i = 0; A[i] != x; i++);  
              res.add(i + 1);  
              reverse(A, i);  
              res.add(x);  
              reverse(A, x - 1);  
          }  
          return res;  
    
      }  
    
      public void reverse(int[] A, int k) {  
          for (int i = 0, j = k; i < j; i++, j--) {  
              int tmp = A[i];  
              A[i] = A[j];  
              A[j] = tmp;  
          }  
      }  
  }  
```    
    
### 个人解读    
  对于数组翻转的进阶题目  
  这题目乍一看很绝望的，感觉根本就没办法处理。。。排序很麻烦，然后如何定位移动几下也是个麻烦事。  
    
  误区：  
  最大问题是题要求只要有解就行，不要求最优解。所以每次只移动一个数字即可。  
    
  思路：  
  1、为了后循环的不影响前面的反转，所以需要数字从大到小执行。  
  需要注意坐标细节+-1什么的  
    
  总结： 不能贪心  
    
tags:    
  -  数学  
