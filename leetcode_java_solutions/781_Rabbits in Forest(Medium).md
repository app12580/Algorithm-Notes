### description    
  In a forest, each rabbit has some color. Some subset of rabbits (possibly all of them) tell you how many other rabbits have the same color as them. Those answers are placed in an array.  
    
  Return the minimum number of rabbits that could be in the forest.  
    
  Examples:  
  Input: answers = [1, 1, 2]  
  Output: 5  
  Explanation:  
  The two rabbits that answered "1" could both be the same color, say red.  
  The rabbit than answered "2" can't be red or the answers would be inconsistent.  
  Say the rabbit that answered "2" was blue.  
  Then there should be 2 other blue rabbits in the forest that didn't answer into the array.  
  The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn't.  
    
  Input: answers = [10, 10, 10]  
  Output: 11  
    
  Input: answers = []  
  Output: 0  
  Note:  
    
  answers will have length at most 1000.  
  Each answers[i] will be an integer in the range [0, 999].  
### solution    
```    
  class Solution {  
      public int numRabbits(int[] answers) {  
          Map<Integer, Integer> map = new HashMap<>();  
          for(int num: answers) {  
              map.put(num, map.getOrDefault(num , 0) + 1);  
          }  
          int res = 0;  
          for(Map.Entry<Integer, Integer> entry: map.entrySet()) {  
              int key = entry.getKey();  
              int value = entry.getValue();  
              res += (key + 1) * ((value + key) / (key + 1));       //类似于分页中的页数计算  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  数学问题。  
    
  首先大原则，颜色越少越好，那么就可以假设，回答一样的兔子要尽可能是同样的颜色。  
    
  answer ： 1， 最多有2只，多了就要其他兔子。  
    
  这么一分析就是一个纯数学问题了。  
    
tags:    
  -  数学  
