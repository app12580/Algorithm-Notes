### description  
  Count the number of prime numbers less than a non-negative number, n.  
  
  Example:  
  
  Input: 10  
  Output: 4  
  Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.  
### solution  
```  
  class Solution {
      public int countPrimes(int n) {
         boolean[] notPrime = new boolean[n +1];  //初始为false，如果不是质数则为true，false则为质数
          int cnt = 0;
          for(int i = 2; i < n; i++) {
              if(notPrime[i]) {
                  continue;
              }
              int j = i +i; //优化：
              while(j <= n) {
                  notPrime[j] = true;
                  j += i;
              }
              
          //  for (long j = (long) (i) * i; j < n; j += i) {
          //        notPrimes[(int) j] = true;
          //    }
              cnt++;
          }
          return cnt; 
      }
  }
```  
  
### 个人解读  
  数某一个范围内的质数  
  《埃拉托斯特尼筛法》  
  在每次找到一个素数时，将能被素数整除的数排除掉。  
  记不住这个名字了，总之就是每次划去2的倍数，然后3的倍数，一次类推  
  
  
tags:  
  -  质数  
  -  数组  
  