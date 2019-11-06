### description    
  Let f(x) be the number of zeroes at the end of x!. (Recall that x! = 1 * 2 * 3 * ... * x, and by convention, 0! = 1.)  
    
  For example, f(3) = 0 because 3! = 6 has no zeroes at the end, while f(11) = 2 because 11! = 39916800 has 2 zeroes at the end. Given K, find how many non-negative integers x have the property that f(x) = K.  
    
  Example 1:  
  Input: K = 0  
  Output: 5  
  Explanation: 0!, 1!, 2!, 3!, and 4! end with K = 0 zeroes.  
    
  Example 2:  
  Input: K = 5  
  Output: 0  
  Explanation: There is no x such that x! ends in K = 5 zeroes.  
  Note:  
    
  K will be an integer in the range [0, 10^9].  
### solution    
```    
    
```    
    
### 个人解读    
  
超级重点的数学题目：  
https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/discuss/?currentPage=1&orderBy=most_votes&query=  
  
  阶乘后续0的规律： 要看5， 25是+2,30 + 1;  
    
  猜测：  
  先找到符合条件的最低数字，然后再+5，就会多了一个0，所以大部分是5，少部分是0  
    
  难点大概是K个0可以很大很大吧。。。  
    
  1,2,3,4,6,  
  7,8,9,10,12  
  13,14,15,16,18  
  19,20,21,22,24  
  25,26,27,28,31  
  找这里面的规律：  
    
  感觉要用二分法什么的了。。  
    
  如果input是25， 然后去找到25会额外产生多少？ 25 + 6 = 31。  
  所以思路一：先找到5^k的最大值，然后去找额外的，之后再与input做差值继续这个过程。  
    
    
  方法一： 推导法：  
 ```  
  class Solution {  
       public int preimageSizeFZF(int K) {  
           int step=1;  
           while (step < K)  step = step * 5 + 1;  
     
           while (K > 0) {  
               step = (step - 1) / 5;  
               if (K / step == 5) return 0;  
               K %= step;  
           }  
           return 5;  
       }  
   }  
 ```  
  设ai为某进制下的数字: 1, 2, 3, 4, 10, 11, 12, 13....  
  在其进制下， 第一位c1 = 1， 第二位c2 = 1 * 5 + 1 = 6, 第三位c3 = 6 * 5 + 1  
  只要满足input的K可以写成这种数列，就返回5   
  与[378](378_Kth%20Smallest%20Element%20in%20a%20Sorted%20Matrix%20(Medium).md)题目类似
  ```
  373. Find K Pairs with Smallest Sums
  378. Kth Smallest Element in a Sorted Matrix
  668. Kth Smallest Number in Multiplication Table
  719. Find K-th Smallest Pair Distance
  786. K-th Smallest Prime Fraction
  ```
  
    
tags:    
  -  重点数学(特别重点)  
  -  数字逻辑  
