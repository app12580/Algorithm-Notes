### description    
  The i-th person has weight people[i], and each boat can carry a maximum weight of limit.  
    
  Each boat carries at most 2 people at the same time, provided the sum of the weight of those people is at most limit.  
    
  Return the minimum number of boats to carry every given person.  (It is guaranteed each person can be carried by a boat.)  
    
     
    
  Example 1:  
    
  Input: people = [1,2], limit = 3  
  Output: 1  
  Explanation: 1 boat (1, 2)  
  Example 2:  
    
  Input: people = [3,2,2,1], limit = 3  
  Output: 3  
  Explanation: 3 boats (1, 2), (2) and (3)  
  Example 3:  
    
  Input: people = [3,5,3,4], limit = 5  
  Output: 4  
  Explanation: 4 boats (3), (3), (4), (5)  
  Note:  
    
  1 <= people.length <= 50000  
  1 <= people[i] <= limit <= 30000  
### solution    
```    
Runtime: 21 ms, faster than 36.74% of Java online submissions for Boats to Save People.  
Memory Usage: 52.2 MB, less than 80.00% of Java online submissions for Boats to Save People.  
  
  class Solution {  
      public int numRescueBoats(int[] people, int limit) {  
          int res = 0;  
          Arrays.sort(people);  
          int i = 0; int j = people.length - 1;  
          while(i <= j) {  
              if(i == j) {  
                  res++;  
                  break;  
              }  
              if(people[i] + people[j] <= limit) {  
                  res++;  
                  i++;  
                  j--;  
              } else {  
                  res++;  
                  j--;  
              }  
          }  
          return res;  
      }  
    
  }  
```    
    
### 个人解读    
  第一反应是DFS，但是由于是双人船，思考有没有特殊的数学方法。  
    
  思路一：先排序，然后双指针。然后问题来了。双人的贪婪匹配，是让两个大的，还是一大一小。  
  还是一大一小更好。  
    
tags:    
  -  数学  
  -  双指针  
