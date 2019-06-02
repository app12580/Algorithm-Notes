### description    
  Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.  
    
  Example 1:  
    
  Input: 2  
  Output: [0,1,1]  
  Example 2:  
    
  Input: 5  
  Output: [0,1,1,2,1,2]  
  Follow up:  
    
  It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?  
  Space complexity should be O(n).  
  Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.  
    
  求[0,n]的所有数字中，二进制中1的位数。  
### solution    
```    
   class Solution {  
       public int[] countBits(int num) {  
               int[] res = new int[num +1];  
           for(int i = 1; i <= num; i++) {  
               res[i] = res[i & (i - 1)] + 1;  
           }  
           return res;  
       }  
   }  
```    
    
### 个人解读    
  如果只是统计一个数字的1的个数，那么很简单，while(n>0)n/=2就完事了，本题的描述明显就是需要答案复用。  
  然后想到创建一个数组，里面存储着结果，需要初始化数据为-1；(因为循环的运算方向，会导致不需要判断是否处理过了，所以这个-1没有必要)  
  问题接下就是怎么复用。  
  1、需要先算小的，然后再算大的，大的需要复用小的结果。(因为大的不方便直接算出来)  
  2、想到一个思路，1010101 然后去掉末位的1，然后再+1得到结果  
  3、看看能不能实现：  
  ```  
      去除 n 的位级表示中最低的那一位    n&(n-1)    
      得到 n 的位级表示中最低的那一位    n&(-n)     
      去除 n 的位级表示中最高的那一位    n-n&(\~n+1)   
  ```  
      
    
tags:    
  -   位运算    
  -  n&(n-1)  
