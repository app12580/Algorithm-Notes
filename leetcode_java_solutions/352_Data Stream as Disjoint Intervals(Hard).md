### description  
  Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.
  
  For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
  
  [1, 1]
  [1, 1], [3, 3]
  [1, 1], [3, 3], [7, 7]
  [1, 3], [7, 7]
  [1, 3], [6, 7]
   
  
  Follow up:
  
  What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
### solution  
```  
// 方法一： TreeMap流水账
  Runtime: 119 ms, faster than 58.15% of Java online submissions for Data Stream as Disjoint Intervals.
  Memory Usage: 47.4 MB, less than 88.89% of Java online submissions for Data Stream as Disjoint Intervals.
  
  class SummaryRanges {
  
     TreeMap<Integer, int[]> tree;
  
      public SummaryRanges() {
          tree = new TreeMap<>();
      }
  
      public void addNum(int val) {
          if(tree.containsKey(val)) return;
          Integer l = tree.lowerKey(val);
          Integer h = tree.higherKey(val);
          if(l != null && h != null && tree.get(l)[1] + 1 == val && h == val + 1) {
              tree.get(l)[1] = tree.get(h)[1];
              tree.remove(h);
          } else if(l != null && tree.get(l)[1] + 1 >= val) {
              tree.get(l)[1] = Math.max(tree.get(l)[1], val);
          } else if(h != null && h == val + 1) {
              tree.put(val, new int[]{val, tree.get(h)[1]});
              tree.remove(h);
          } else {
              tree.put(val, new int[]{val,val});
          }
      }
  
      public int[][] getIntervals() {
          int size = tree.size();
          int[][] res = new int[size][2];
          int index = 0;
          for(int[] v: tree.values()) {
              res[index][0] = v[0];
              res[index][1] = v[1];
              index++;
          }
          return res;
      }
  }
   
```  
  
### 个人解读  
  核心点：
  用什么数据结构去存储？
  TreeMap
  
tags:  
  -  数据结构
  -  TreeMap
