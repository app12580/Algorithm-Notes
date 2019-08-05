### description    
  Given a positive integer n and you can do operations as follow:  
    
  If n is even, replace n with n/2.  
  If n is odd, you can replace n with either n + 1 or n - 1.  
  What is the minimum number of replacements needed for n to become 1?  
    
  Example 1:  
    
  Input:  
  8  
    
  Output:  
  3  
    
  Explanation:  
  8 -> 4 -> 2 -> 1  
  Example 2:  
    
  Input:  
  7  
    
  Output:  
  4  
    
  Explanation:  
  7 -> 8 -> 4 -> 2 -> 1  
  or  
  7 -> 6 -> 3 -> 2 -> 1  
### solution    
```    
Runtime: 2 ms, faster than 36.24% of Java online submissions for Integer Replacement.  
Memory Usage: 33 MB, less than 5.77% of Java online submissions for Integer Replacement.  
  
  class Solution {  
      public int integerReplacement(int n) {  
          int res = 0;  
          while(n % 2 == 0) {  
              res++;  
              n /= 2;  
          }  
          if(n == 1) return res;      
          if(n == Integer.MAX_VALUE) return res +integerReplacement(n - 1); //这里为什么不用+1  //可以直接return32  
    
          return res + 1 + Math.min(integerReplacement(n+1), integerReplacement(n - 1));  
      }  
  }  
    
    
    // 方法二： 根据数学，指定行进路线。
  class Solution {  
  public int integerReplacement(int n) {  
      int c = 0;  
      while (n != 1) {  
          if ((n & 1) == 0) {  
              n >>>= 1;  
          } else if (n == 3 || ((n >>> 1) & 1) == 0) {  
                // 如果是3或者01的情况  
              --n;  
          } else {  
                // 如果是11的情况  
              ++n;  //可能出现一致的情况  
          }  
          ++c;  
      }  
      return c;  
  }  
  }  
```    
    
### 个人解读    
  递归是最好的减少思路支线的套路。  
  本题竟然也会出现int超限问题。。。  
    
  遇到了问题：为什么Max_Value的结果是32，而MAX_Value - 1的结果也是32？？  
  官方测试用例有问题，看到其他人也提到这个问题了。  
    
  反思：其实是虽然int不让max+1，但是实际上可以加1的。  
    
  那么除了用long有没有办法解决：  
    
  使用方法二的优化  
  本题本质其实是将数字不断右移，然后根据末尾的情况来处理，核心就是可以直接确定什么时候+1什么时候-1。  
    
  如果末位是0，可以直接右移，无需多言。  
  如果末位是1，需要讨论倒数第二位：  
  11的情况：  
  -1： _11 _10  _1 _0(?0)   
  +1： _11 ?00  ?0 ?      (?)  
  01的情况：  
  -1：_01 _00 _0 _  
  +1：_01 _10 _1  
  这么想有点乱  
    
  换个思路，其实本质就是想办法把二进制中的1变成0，所以01做--就可以消去1个1，而11做++操作就可以消去两个1。  
  需要注意++时候不要引入新的数字：比如3，7.  
  11 100 10 1  
  11 10  1  
    
  111 1000 100 10 1  
  111 110  11  10 1  
    
  1111 10000 1000 100 10 1  
  1111 1110  111  110 11 10 1   
    
  11111 100000 10000 1000  100  10  1  
  11111 11110  1111  10000 1000 100 10 1  
    
  根据递归就可以知道，只有3的时候是特殊的。  
    
tags:    
  -  位运算  
  -  重点数学  
