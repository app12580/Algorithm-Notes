### description    
  A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.  
    
  Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.  
    
  If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.  
    
  Note:  
    
  The number of stones is ≥ 2 and is < 1,100.  
  Each stone's position will be a non-negative integer < 231.  
  The first stone's position is always 0.  
  Example 1:  
    
  [0,1,3,5,6,8,12,17]  
    
  There are a total of 8 stones.  
  The first stone at the 0th unit, second stone at the 1st unit,  
  third stone at the 3rd unit, and so on...  
  The last stone at the 17th unit.  
    
  Return true. The frog can jump to the last stone by jumping   
  1 unit to the 2nd stone, then 2 units to the 3rd stone, then   
  2 units to the 4th stone, then 3 units to the 6th stone,   
  4 units to the 7th stone, and 5 units to the 8th stone.  
  Example 2:  
    
  [0,1,2,3,4,8,9,11]  
    
  Return false. There is no way to jump to the last stone as   
  the gap between the 5th and 6th stone is too large.  
### solution    
```    
//方法二： 优化： value变成当前格子能走多少步，这样就少了将到达的k转化成下一次的操作了  
Runtime: 42 ms, faster than 54.37% of Java online submissions for Frog Jump.  
Memory Usage: 42.4 MB, less than 33.33% of Java online submissions for Frog Jump.  
class Solution {  
      
     public boolean canCross(int[] stones) {  
        if (stones.length == 0) {  
        	return true;  
        }  
          
        HashMap<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>(stones.length);  
        map.put(0, new HashSet<Integer>());  
        map.get(0).add(1);  
        for (int i = 1; i < stones.length; i++) {  
        	map.put(stones[i], new HashSet<Integer>() );  
        }  
          
        for (int i = 0; i < stones.length - 1; i++) {  
        	int stone = stones[i];  
        	for (int step : map.get(stone)) {  
        		int reach = step + stone;  
        		if (reach == stones[stones.length - 1]) {  
        			return true;  
        		}  
        		HashSet<Integer> set = map.get(reach);  
        		if (set != null) {  
        		    set.add(step);  
        		    if (step - 1 > 0) set.add(step - 1);  
        		    set.add(step + 1);  
        		}  
        	}  
        }  
          
        return false;  
    }   
}  
  
// 方法一： map: key:位置  value 通过多少到达的  
Runtime: 80 ms, faster than 15.62% of Java online submissions for Frog Jump.  
Memory Usage: 49.9 MB, less than 9.52% of Java online submissions for Frog Jump.  
  
  class Solution {  
        
      public boolean canCross(int[] stones) {  
           if (stones.length < 2 || stones[1] != 1) {  
              return false;  
          }  
          if(stones.length == 2) return true;  
          int target = stones[stones.length - 1];  
          Map<Integer, Set<Integer>> map = new HashMap<>();  
          for(int s: stones) {  
              map.put(s, new HashSet<>());  
          }  
          map.get(1).add(1);  
          for(int i = 1; i < stones.length; i++) {  
              Set<Integer> next = new HashSet<>();  
              int cur = stones[i];  
              Set<Integer> curSet = map.get(cur);  
              for(int step: curSet) {  
                  if(step != 1) next.add(step - 1);  
                  next.add(step);  
                  next.add(step+1);  
              }  
              for(int step: next) {  
                  if(cur + step == target) return true;  
                  if(map.containsKey(cur + step)) {  
                      map.get(cur + step).add(step);  
                  }  
              }  
          }  
          return false;  
      }  
  }  
```    
    
### 个人解读    
  感觉是一个遍历题目，要么是DFS要么是BFS。  
    
  思路一：  
  尝试一下DFS吧。直接TLE了。  
    
  思路二：  
  类似于[055](055_Jump%20Game(Medium).md)，存储每个点能到达的位置  
    
tags:    
  -    
