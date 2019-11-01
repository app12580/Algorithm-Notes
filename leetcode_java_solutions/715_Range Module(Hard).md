### description    
  A Range Module is a module that tracks ranges of numbers. Your task is to design and implement the following interfaces in an efficient manner.  
    
  addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.  
  queryRange(int left, int right) Returns true if and only if every real number in the interval [left, right) is currently being tracked.  
  removeRange(int left, int right) Stops tracking every real number currently being tracked in the interval [left, right).  
  Example 1:  
  addRange(10, 20): null  
  removeRange(14, 16): null  
  queryRange(10, 14): true (Every number in [10, 14) is being tracked)  
  queryRange(13, 15): false (Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)  
  queryRange(16, 17): true (The number 16 in [16, 17) is still being tracked, despite the remove operation)  
  Note:  
    
  A half open interval [left, right) denotes all real numbers left <= x < right.  
  0 < left < right < 10^9 in all calls to addRange, queryRange, removeRange.  
  The total number of calls to addRange in a single test case is at most 1000.  
  The total number of calls to queryRange in a single test case is at most 5000.  
  The total number of calls to removeRange in a single test case is at most 1000.  
### solution    
```    
// 方法一： 通过TreeMap的subMap方法，属于逃课做法  
Runtime: 115 ms, faster than 30.43% of Java online submissions for Range Module.  
Memory Usage: 64.8 MB, less than 20.00% of Java online submissions for Range Module.  
  class RangeModule {  
      TreeMap<Integer, Integer> map;  
      public RangeModule() {  
          map = new TreeMap<>();  
      }  
        
     public void addRange(int left, int right) {  
          if (right <= left) return;  
          Integer start = map.floorKey(left);  
          Integer end = map.floorKey(right);  
          if (start == null && end == null) {  
              map.put(left, right);  
          } else if (start != null && map.get(start) >= left) {  
                
              map.put(start, Math.max(map.get(end), Math.max(map.get(start), right)));  
          } else {  
              map.put(left, Math.max(map.get(end), right));  
          }  
          // clean up intermediate intervals  
          Map<Integer, Integer> subMap = map.subMap(left, false, right, true);  
          Set<Integer> set = new HashSet(subMap.keySet());  
          map.keySet().removeAll(set);  
      }  
    
      public boolean queryRange(int left, int right) {  
          Integer start = map.floorKey(left);  
          if (start == null) return false;  
          return map.get(start) >= right;  
      }  
    
      public void removeRange(int left, int right) {  
          if (right <= left) return;  
          Integer start = map.floorKey(left);  
          Integer end = map.floorKey(right);  
          if (end != null && map.get(end) > right) {  
              // 如果有超出right的残余  
              map.put(right, map.get(end));  
          }  
          if (start != null && map.get(start) > left) {  
              //是否左边有残余  
              map.put(start, left);  
          }  
          // clean up intermediate intervals  
          Map<Integer, Integer> subMap = map.subMap(left, true, right, false);  
          Set<Integer> set = new HashSet(subMap.keySet());  
          map.keySet().removeAll(set);  
      }  
  }  
```    
    
### 个人解读    
  需要一个数据结构，同时具有数组的访问性(以便二分法)和添加删除，排序是顺带的。  
  TreeSet或者TreeMap.  
    
  本题目属于区间的合并和删除，应该算是可以进代码模板的题目了  
    
    
  答案一：  
  通过TreeMap的subMap进行多余区间的清洗，添加时候保证(left,right]删除掉，删除时候保证[left,right)删除掉。  
  本身这个方法就很少用，属于小技巧  
    
tags:    
  -  区间问题  
