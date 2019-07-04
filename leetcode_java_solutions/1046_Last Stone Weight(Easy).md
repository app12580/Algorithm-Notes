### description    
  We have a collection of rocks, each rock has a positive integer weight.  
    
  Each turn, we choose the two heaviest rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:  
    
  If x == y, both stones are totally destroyed;  
  If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.  
  At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)  
    
     
    
  Example 1:  
    
  Input: [2,7,4,1,8,1]  
  Output: 1  
  Explanation:   
  We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,  
  we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,  
  we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,  
  we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.  
     
    
  Note:  
    
  1 <= stones.length <= 30  
  1 <= stones[i] <= 1000  
    
    
### solution    
```    
  
Runtime: 1 ms, faster than 97.56% of Java online submissions for Last Stone Weight.  
Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Last Stone Weight.  
  
  class Solution {  
      public int lastStoneWeight(int[] stones) {  
           PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {  
              @Override  
              public int compare(Integer o1, Integer o2) {  
                  return o2 - o1;  
              }  
          });  
          for(int s: stones) {  
              queue.offer(s);  
          }  
          while(queue.size() >= 2) {  
              int max = queue.poll();  
              int second = queue.poll();  
              if(max != second) {  
                  queue.offer(max - second);  
              }  
          }  
          return queue.size() == 0 ? 0 : queue.poll();  
      }  
  }  
```    
    
### 个人解读    
  要么按照题先排个序，然后流水账下来，  
  要么就去找有没有什么规律。  
    
  本题的数字操作与[561](561_Array%20Partition%20I(Easy).md)一致。  
    
  第一个问题： 一定要先排序吗？  
  第一个问题想到一半，然后提出了第二个问题：碎石先后顺序有什么影响吗？  
    
  有影响，算了，还是流水账把。  
    
    
tags:    
  -  流水账  
