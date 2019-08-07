### description    
  Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.  
    
  get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.  
  put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.  
    
  The cache is initialized with a positive capacity.  
    
  Follow up:  
  Could you do both operations in O(1) time complexity?  
    
  Example:  
    
  LRUCache cache = new LRUCache( 2 /* capacity */ );  
    
  cache.put(1, 1);  
  cache.put(2, 2);  
  cache.get(1);       // returns 1  
  cache.put(3, 3);    // evicts key 2  
  cache.get(2);       // returns -1 (not found)  
  cache.put(4, 4);    // evicts key 1  
  cache.get(1);       // returns -1 (not found)  
  cache.get(3);       // returns 3  
  cache.get(4);       // returns 4  
### solution    
```    
  
  
Runtime: 60 ms, faster than 75.97% of Java online submissions for LRU Cache.  
Memory Usage: 57.1 MB, less than 64.71% of Java online submissions for LRU Cache.  
  
  class LRUCache {  
        
      final Node head = new Node(0, 0);  
      final Node tail = new Node(0, 0);  
      final Map<Integer, Node> map;  
      final int capacity;  
        
      public LRUCache(int capacity) {  
          this.capacity = capacity;  
          map = new HashMap(capacity);  
          head.next = tail;  
          tail.prev = head;  
      }  
        
      public int get(int key) {  
        int res = -1;  
        if(map.containsKey(key)){  
          Node n = map.get(key);  
          remove(n);  
          insertToHead(n);  
          res = n.value;  
        }  
        return res;     
      }  
        
      public void put(int key, int value) {  
        if(map.containsKey(key)){  
          Node n = map.get(key);  
          remove(n);  
          n.value = value;  
          insertToHead(n);  
        } else {  
            
          if(map.size() == capacity){  
             map.remove(tail.prev.key);   
             remove(tail.prev);  
          }   
            
          Node n = new Node(key, value);  
          insertToHead(n);  
          map.put(key, n);  
        }    
      }  
        
      private void remove(Node n){  
        n.prev.next = n.next;  
        n.next.prev = n.prev;  
      }  
        
      private void insertToHead(Node n){  
        Node headNext = head.next;  
        head.next = n;  
        headNext.prev = n;  
        n.prev = head;  
        n.next = headNext;  
      }  
        
      class Node{  
        Node prev, next;  
        int key, value;  
        Node(int k, int v){  
          key = k;  
          value = v;  
        }  
      }  
  }  
```    
    
### 个人解读    
  LRU缓存策略，常用链表来处理。  
    
  使用Queue只能用offer和poll这两个方法，所以还是自己维护一套关于节点的数据结构才是解决办法。Java很多时候这点就是特别难受。。。    
    
  ```  
    失败的尝试，不知道LRU到底想要啥  
    class LRUCache {  
      
        private Map<Integer, Integer> map = new HashMap<>();  
        private Queue<Integer> queue = new LinkedList<>();  
        private int limit;  
      
        public LRUCache(int capacity) {  
            this.limit = capacity;  
        }  
      
        public int get(int key) {  
            if(!map.containsKey(key)) return -1;  
            rePosition(key);  
            return map.get(key);  
        }  
      
        private void rePosition(int key) {  
            Iterator<Integer> iterator = queue.iterator();  
            Integer pre = null;  
            while(iterator.hasNext()) {  
                Integer next = iterator.next();  
                if(next.equals(key)) {  
                    pre = key;  
                    iterator.remove();  
                    break;  
                }  
            }  
            queue.offer(pre);  
        }  
      
        public String put(int key, int value) {  
            if(queue.size() < limit) {  
                queue.offer(key);  
            } else {  
                if(queue.contains(key)) {  
                    rePosition(key);  
                } else {  
                    //不存在，删除第一个  
                    Integer poll = queue.poll();  
                    map.remove(poll);  
                    queue.offer(key);`  //问题出在这里，没有放在最前面  
                }  
            }  
            map.put(key, value);  
            return "";  
        }  
    }  
  ```  
    
    
tags:    
  -  LRU  
  -  概念描述  
