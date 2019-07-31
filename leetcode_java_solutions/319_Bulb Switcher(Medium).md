### description    
  There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the i-th round, you toggle every i bulb. For the n-th round, you only toggle the last bulb. Find how many bulbs are on after n rounds.  
    
  Example:  
    
  Input: 3  
  Output: 1   
  Explanation:   
  At first, the three bulbs are [off, off, off].  
  After first round, the three bulbs are [on, on, on].  
  After second round, the three bulbs are [on, off, on].  
  After third round, the three bulbs are [on, off, off].   
    
  So you should return 1, because there is only one bulb is on.  
    
    
### solution    
```    
  // 方法一： 超时了  
  、 public int bulbSwitch(int n) {  
           int[] arr = new int[n];  
           for(int i = 2; i <= n; i++) {  
               int t = i;  
               while(t <= n) {  
                   arr[t - 1]++;  
                   t += i;  
               }  
           }  
           //偶数是on  
           int res = 0;  
           for(int num: arr) {  
               if(num % 2 == 0) {  
                   res ++;  
               }  
           }  
           return res;  
     
       }  
         
         
  //方法二： 返回平方数  
  class Solution {  
      public int bulbSwitch(int n) {  
                  return (int) Math.sqrt(n);  
    
      }  
  }  
```    
    
### 个人解读    
  不知道除了从1到n遍历，O(n^2)的解法外，还有什么好的办法吗？  
    
  通过代码读取每次合格的灯泡的位置，发现惊人事实：  
  ```  
  input: 103  
  output: 1  4  9  16  25  36  49  64  81  100  
  ```  
  发现只有平方数会被点亮。  
    
  解释：  
  因为对于任意一个数A，它可以在每一个因数里面被点击一次。  
  因此，对于任意不相等的两个因数b*c，此时的操作相当于无效操作，会被点灭。只有相同的两个因数d，d*d==A，因为只会点击d的一次，所以最后会是电量状态。  
    
  关于平方可以参考问题[069](069_Sqrt(x)%20(Easy).md)  
    
tags:    
  -  重点数学  
