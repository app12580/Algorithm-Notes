### description      
In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.     
    
There is at least one empty seat, and at least one person sitting.    
    
Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.     
    
Return that maximum distance to closest person.    
    
Example 1:    
    
Input: [1,0,0,0,1,0,1]    
Output: 2    
Explanation:     
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.    
If Alex sits in any other open seat, the closest person has distance 1.    
Thus, the maximum distance to the closest person is 2.    
Example 2:    
    
Input: [1,0,0,0]    
Output: 3    
Explanation:     
If Alex sits in the last seat, the closest person is 3 seats away.    
This is the maximum distance possible, so the answer is 3.    
Note:    
    
1 <= seats.length <= 20000    
seats contains only 0s or 1s, at least one 0, and at least one 1.    
      
### solution      
```      
Runtime: 1 ms, faster than 100.00% of Java online submissions for Maximize Distance to Closest Person.  
Memory Usage: 39.3 MB, less than 97.53% of Java online submissions for Maximize Distance to Closest Person.  
  
  
    class Solution {  
        public int maxDistToClosest(int[] seats) {  
             int res = 0;  
            for(int i = 0, j = 0; i < seats.length; i = j) {  
                while(i < seats.length && seats[i] == 1) {  
                    i++;  
                }  
                // i 为第一个0  
                j = i;  
                while(j < seats.length && seats[j] == 0) {  
                    j++;  
                }  
                if(i == 0) {  
                    res = j;  
                } else if(j == seats.length) {  
                    res = Math.max(j - i, res);  
                } else {  
                    res = Math.max((j - i + 1) / 2, res);  
                }  
            }  
      
            return res;  
        }  
    }  
```      
      
### 个人解读      
  curLen问题，套模板即可。需要注意i和j的分开处理，  
    
  找规律  
  ```  
//[1,0,0,0,1,0,1]  
//    1: 1  
//    2: 1  
//    3: 2  
//    4: 2  
//    5: 3  
  
  res = Math.max((j - i + 1) / 2, res);  
  ```  
      
tags:      
  -  curLen问题  
