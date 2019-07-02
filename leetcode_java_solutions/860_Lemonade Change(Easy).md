### description    
  At a lemonade stand, each lemonade costs $5.   
    
  Customers are standing in a queue to buy from you, and order one at a time (in the order specified by bills).  
    
  Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.  You must provide the correct change to each customer, so that the net transaction is that the customer pays $5.  
    
  Note that you don't have any change in hand at first.  
    
  Return true if and only if you can provide every customer with correct change.  
    
     
    
  Example 1:  
    
  Input: [5,5,5,10,20]  
  Output: true  
  Explanation:   
  From the first 3 customers, we collect three $5 bills in order.  
  From the fourth customer, we collect a $10 bill and give back a $5.  
  From the fifth customer, we give a $10 bill and a $5 bill.  
  Since all customers got correct change, we output true.  
  Example 2:  
    
  Input: [5,5,10]  
  Output: true  
  Example 3:  
    
  Input: [10,10]  
  Output: false  
  Example 4:  
    
  Input: [5,5,10,10,20]  
  Output: false  
  Explanation:   
  From the first two customers in order, we collect two $5 bills.  
  For the next two customers in order, we collect a $10 bill and give back a $5 bill.  
  For the last customer, we can't give change of $15 back because we only have two $10 bills.  
  Since not every customer received correct change, the answer is false.  
     
    
  Note:  
    
  0 <= bills.length <= 10000  
  bills[i] will be either 5, 10, or 20.  
    
  柠檬水找零  
### solution    
```    
  class Solution {  
     public boolean lemonadeChange(int[] bills) {  
          int[] change = new int[2];  // 5 10 个数  20的不用管  
          for(int num: bills) {  
              if(!check(num, change)) {  
                  return false;  
              }  
          }  
          return true;  
      }  
    
      private boolean check(int num, int[] change) {  
          if(num == 5) {  
              change[0]++;  
              return true;  
          } else if(num == 10) {  
              if(change[0] > 0) {  
                  change[0]--;  
                  change[1]++;  
                  return true;  
              }  
              return false;  
          } else {  
              if(change[1] > 0 && change[0] > 0) {  
                  change[0]--;  
                  change[1]--;  
                  return true;  
              } else if(change[0] > 2) {  
                  change[0] -= 3;  
                  return true;  
              } else {  
                  return false;  
              }  
          }  
      }  
  }  
```    
    
### 个人解读    
  需要找一个结构存储当前手里零钱，可以用数组；然后需要一个方法，判断能否成功找零。  
  局部的小贪心算法：找零时候尽量返回10块钱的。  
    
tags:    
  -  数学  
  -  贪心算法  
