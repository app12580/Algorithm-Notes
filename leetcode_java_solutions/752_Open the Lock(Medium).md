### description    
  You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.  
    
  The lock initially starts at '0000', a string representing the state of the 4 wheels.  
    
  You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.  
    
  Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.  
    
  Example 1:  
  Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"  
  Output: 6  
  Explanation:  
  A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".  
  Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,  
  because the wheels of the lock become stuck after the display becomes the dead end "0102".  
  Example 2:  
  Input: deadends = ["8888"], target = "0009"  
  Output: 1  
  Explanation:  
  We can turn the last wheel in reverse to move from "0000" -> "0009".  
  Example 3:  
  Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"  
  Output: -1  
  Explanation:  
  We can't reach the target without getting stuck.  
  Example 4:  
  Input: deadends = ["0000"], target = "8888"  
  Output: -1  
  Note:  
  The length of deadends will be in the range [1, 500].  
  target will not be in the list deadends.  
  Every string in deadends and the string target will be a string of 4 digits from the 10,000 possibilities '0000' to '9999'.  
### solution    
```    
  class Solution {  
     public int openLock(String[] deadends, String target) {  
          Set<String> set = new HashSet<>();  
          for(String d: deadends) {  
              set.add(d);  
          }  
          if(set.contains("0000")) return -1;  
    
          Queue<String> queue = new LinkedList<>();  
          String cur = "0000";  
          queue.offer(cur);  
          int res = 0;  
          while(!queue.isEmpty()) {  
              int size = queue.size();  
              for(int i = 0; i < size; i++) {  
                  String poll = queue.poll();  
                  if(target.equals(poll)) return res;  
                  List<String> nextAll = getNextAll(poll);  
                  for(String next: nextAll) {  
                      if(set.contains(next)) {  
                          continue;  
                      }  
                      queue.offer(next);  
                      set.add(next);  
                  }  
              }  
              res++;  
          }  
          return -1;  
    
      }  
    
      private List<String> getNextAll(String poll) {  
          char[] chars = poll.toCharArray();  
          List<String> res = new ArrayList<>();  
          for(int i = 0; i < 4; i++) {  
              char[] copy = chars.clone();  
              int pre = chars[i] - '0';  
              copy[i] = (char)('0' + (pre + 1) % 10);  
              res.add(new String(copy));  
              copy[i] = (char)('0' + (pre + 9) % 10);  
              res.add(new String(copy));  
          }  
          return res;  
      }  
    
  }  
```    
    
### 个人解读    
  首先肯定是个遍历问题。  
    
  要么从起点开始遍历，要么从终点开始遍历。  
  这个问题，一个搞不好，复杂度会爆炸。。。  
    
  本题目很类似与BFS，但是四个位数双方向，直接BFS数据量会爆炸的，而且还会发生避开，相同位置需要不同的方式绕道。  
  又突然联想到了迷宫这种东西。  
    
  试一试，直接构建一个超大数组看看。  
    
  反思：总想着通过数学方法可以精确遍历方向，然而最终还是要靠完全BFS去遍历额。。。  
    
    
tags:    
  -  BFS   
  -  字符串解析   
