### description    
  Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.  
    
  Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.  
    
  So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.  
    
  Note:  
    
  Numbers of houses and heaters you are given are non-negative and will not exceed 25000.  
  Positions of houses and heaters you are given are non-negative and will not exceed 10^9.  
  As long as a house is in the heaters' warm radius range, it can be warmed.  
  All the heaters follow your radius standard and the warm radius will the same.  
     
    
  Example 1:  
    
  Input: [1,2,3],[2]  
  Output: 1  
  Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.  
     
    
  Example 2:  
    
  Input: [1,2,3,4],[1,4]  
  Output: 1  
  Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.  
    
  中文描述：  
  https://leetcode-cn.com/problems/heaters/  
    
### solution    
```    
  class Solution {  
      public int findRadius(int[] houses, int[] heaters) {  
          Arrays.sort(houses);  
          Arrays.sort(heaters);  
          int index = 0;  
          int max = 0;  
          int first = heaters[0];  
          int last = heaters[heaters.length - 1];  
          for(int i = 0; i < houses.length; i++) {  
              int house = houses[i];  
              if(house <= first) {  
                  if(i == 0) {  
                      max = Math.max(max, first - house);  
                  }  
              } else if(house >= last) {  
                  if(i == houses.length - 1) {  
                      max = Math.max(max, house - last );  
                  }  
              } else {  
                  //越过了第一个  
                  while(heaters[index] < house && heaters[index + 1] < house) {  
                      index++;  
                  }  
                  max = Math.max(max, Math.min(house - heaters[index], heaters[index + 1] - house));  
              }  
          }  
          return max;  
      }  
  }  
```    
    
### 个人解读    
  数学问题，需要找规律  
  找到相邻两个heater的最大距离，边缘需要特殊处理。  
    
  理清一下提议，heater可能在数组的外面。  
  ```  
    Last executed input  [1,5]  [10]  
                      
  ```  
    
  重新理解题目后，每次从house里面数字和heater里面数字进行比较，获取最大差值。  
  这题还蛮复杂的，最后一步一步拆解。  
    
    
tags:    
  -  数学  
  -  重点  
