### description      
  For some fixed N, an array A is beautiful if it is a permutation of the integers 1, 2, ..., N, such that:    
      
  For every i < j, there is no k with i < k < j such that A[k] * 2 = A[i] + A[j].    
      
  Given N, return any beautiful array A.  (It is guaranteed that one exists.)    
      
       
      
  Example 1:    
      
  Input: 4    
  Output: [2,1,4,3]    
  Example 2:    
      
  Input: 5    
  Output: [3,1,2,5,4]    
       
      
  Note:    
      
  1 <= N <= 1000    
### solution      
```      
Runtime: 38 ms, faster than 23.43% of Java online submissions for Beautiful Array.  
Memory Usage: 35.8 MB, less than 20.00% of Java online submissions for Beautiful Array.  
  
    class Solution {  
         public int[] beautifulArray(int N) {  
            ArrayList<Integer> res = new ArrayList<>();  
            res.add(1);  
            while (res.size() < N) {  
                ArrayList<Integer> tmp = new ArrayList<>();  
                for (int i : res) if (i * 2 - 1 <= N) tmp.add(i * 2 - 1);  
                for (int i : res) if (i * 2 <= N) tmp.add(i * 2);  
                res = tmp;  
            }  
            return res.stream().mapToInt(i -> i).toArray();  
        }  
    }  
```      
      
### 个人解读      
  这个题目好难啊。。。    
  参考：https://leetcode.com/problems/beautiful-array/discuss/186679/Odd-%2B-Even-Pattern-O(N)    
      
  总结一下作者的思路：    
  1、如果一个奇数美丽数列+一个偶数美丽数列，还是一个数列    
  2、对美丽数列可以进行如下操作，使其仍然是美丽数列：删，乘，加减    
      
  根据以上两点，实现了不同N之间的状态转移方程。    
      
tags:      
  -  重点数学    
  -  数字逻辑    
