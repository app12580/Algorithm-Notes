### description    
  Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.  
    
  get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.  
  put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.  
    
  Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to zero when the item is removed.  
    
     
    
  Follow up:  
  Could you do both operations in O(1) time complexity?  
    
     
    
  Example:  
    
  LFUCache cache = new LFUCache( 2 /* capacity */ );  
    
  cache.put(1, 1);  
  cache.put(2, 2);  
  cache.get(1);       // returns 1  
  cache.put(3, 3);    // evicts key 2  
  cache.get(2);       // returns -1 (not found)  
  cache.get(3);       // returns 3.  
  cache.put(4, 4);    // evicts key 1.  
  cache.get(1);       // returns -1 (not found)  
  cache.get(3);       // returns 3  
  cache.get(4);       // returns 4  
### solution    
```    
Runtime: 78 ms, faster than 61.01% of Java online submissions for LFU Cache.  
Memory Usage: 59.3 MB, less than 60.00% of Java online submissions for LFU Cache.  
  
  class LFUCache {  
          HashMap<Integer, Integer> vals;  
          HashMap<Integer, Integer> counts;  
          HashMap<Integer, LinkedHashSet<Integer>> lists;  
          int cap;  
          int min = -1;  
          public LFUCache(int capacity) {  
              cap = capacity;  
              vals = new HashMap<>();  
              counts = new HashMap<>();  
              lists = new HashMap<>();  
              lists.put(1, new LinkedHashSet<>());  
          }  
    
          public int get(int key) {  
              if(!vals.containsKey(key))  
                  return -1;  
              int count = counts.get(key);  
              counts.put(key, count+1);  
              lists.get(count).remove(key);  
              if(count==min && lists.get(count).size()==0)  
                  min++;  
              if(!lists.containsKey(count+1))  
                  lists.put(count+1, new LinkedHashSet<>());  
              lists.get(count+1).add(key);  
              return vals.get(key);  
          }  
    
          public void put(int key, int value) {  
              if(cap<=0)  
                  return;  
              if(vals.containsKey(key)) {  
                  vals.put(key, value);  
                  get(key);  
                  return;  
              }  
              if(vals.size() >= cap) {  
                  int evit = lists.get(min).iterator().next();  
                  lists.get(min).remove(evit);  
                  vals.remove(evit);  
              }  
              vals.put(key, value);  
              counts.put(key, 1);  
              min = 1;  
              lists.get(1).add(key);  
          }  
      }  
```    
    
### 个人解读    
  这种题目属于数据结构的应用，可以参考[432](432_All%20O%60one%20Data%20Structure(Hard).md)  
    
  LinkedHashSet： 按照输入顺序的Hash表。  
  感觉420的那种写法更加好一些。  
    
  这种题目虽然是Hard，但主要还是考察数据结构的理解。  
  1、key-value一定要有，用HashMap  
  2、key-counts一定要有，用HashMap  
  3、存储每个使用次数有哪些key:HashMap<Integer(使用次数), LinkedHashSet<Integer>>  
  剩下的就是细节问题了  
    
tags:    
  -  模拟  
  -  缓存策略  
  -  LinkedHashSet  
