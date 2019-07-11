### description    
  There are N gas stations along a circular route, where the amount of gas at station i is gas[i].  
    
  You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.  
    
  Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.  
    
  Note:  
    
  If there exists a solution, it is guaranteed to be unique.  
  Both input arrays are non-empty and have the same length.  
  Each element in the input arrays is a non-negative integer.  
  Example 1:  
    
  Input:   
  gas  = [1,2,3,4,5]  
  cost = [3,4,5,1,2]  
    
  Output: 3  
    
  Explanation:  
  Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4  
  Travel to station 4. Your tank = 4 - 1 + 5 = 8  
  Travel to station 0. Your tank = 8 - 2 + 1 = 7  
  Travel to station 1. Your tank = 7 - 3 + 2 = 6  
  Travel to station 2. Your tank = 6 - 4 + 3 = 5  
  Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.  
  Therefore, return 3 as the starting index.  
  Example 2:  
    
  Input:   
  gas  = [2,3,4]  
  cost = [3,4,3]  
    
  Output: -1  
    
  Explanation:  
  You can't start at station 0 or 1, as there is not enough gas to travel to the next station.  
  Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4  
  Travel to station 0. Your tank = 4 - 3 + 2 = 3  
  Travel to station 1. Your tank = 3 - 3 + 3 = 3  
  You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.  
  Therefore, you can't travel around the circuit once no matter where you start.  
### solution    
```    
  
// 方法一 通过各种标记  
  
Runtime: 1 ms, faster than 31.17% of Java online submissions for Gas Station.  
Memory Usage: 37 MB, less than 99.75% of Java online submissions for Gas Station.  
  
关键点在于curIndex的处理  
  class Solution {  
      public int canCompleteCircuit(int[] gas, int[] cost) {  
          int sum = 0;  
          for(int i = 0; i < gas.length; i++) {  
              gas[i] -= cost[i];  
              sum += gas[i];  
          }  
          if(sum < 0) return -1;  
    
          int max = 0;  
          int maxIndex = 0;  
          int curIndex = -1;    //为了return时候的+1  
          int curSum = 0;  
          for(int i = 0; i < gas.length * 2; i++) {  
              // if(i >= curIndex + gas.length) break;     //加上了这一行并没有提高效率。  
  
              int cur = gas[i % gas.length];  
              curSum += cur;  
              if(curSum <= 0) {  
                  curSum = 0;  
                  curIndex = i; //放在为负数的时候  
              } else {  
                  if(curSum > max) {  
                      maxIndex = curIndex + 1;  
                      max = curSum;     //如果套圈了也并不影响  
                  }  
              }  
          }    
          return maxIndex;  
      }  
  }  
    
  // 方法二  
    class Solution {  
        public int canCompleteCircuit(int[] gas, int[] cost) {  
            int g = 0;  
            int c = 0;  
            int t = 0;  
            int s = 0;  
            for(int i=0;i<gas.length;i++){  
                g+=gas[i];  
                c+=cost[i];  
                t+=(gas[i]-cost[i]);  
                if(t<0){    //核心在于这几行，返回curSum>=0的第一个索引。  
                    s = i+1;  
                    t = 0;  
                }  
            }  
            if(c<=g) return s;  
            return -1;  
              
        }  
    }  
```    
    
### 个人解读    
  先统计出每个索引的消耗，然后去获取sum最多的区间起点。  
  证明略。  
    
  想办法优化，从套圈这个角度入手。优化了，没用。  
    
  方法二的优化：  
  1、将步骤全放在一个循环里面了。  
  2、关键在于，没有比较，没有循环数组。  
  需要从数学上证明啊：最后一段为正的部分是OK的  
  对于单次数组长度循环中，如果sum>0，那么对于最后一段累计和为正的区间，其一定是包含最终节点。  
  如果不包含，则正+负，根据总sum>0，则前面依旧是正，所以变成了正+正+负，此时所求的index不准确，发生矛盾。  
  所以最后一段正区间即为所求结果。  
    
tags:    
  -  重点数学  
  -  数组  
