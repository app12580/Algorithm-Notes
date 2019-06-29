### description    
  Design a HashSet without using any built-in hash table libraries.  
    
  To be specific, your design should include these functions:  
    
  add(value): Insert a value into the HashSet.   
  contains(value) : Return whether the value exists in the HashSet or not.  
  remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.  
    
  Example:  
    
  MyHashSet hashSet = new MyHashSet();  
  hashSet.add(1);           
  hashSet.add(2);           
  hashSet.contains(1);    // returns true  
  hashSet.contains(3);    // returns false (not found)  
  hashSet.add(2);            
  hashSet.contains(2);    // returns true  
  hashSet.remove(2);            
  hashSet.contains(2);    // returns false (already removed)  
    
  Note:  
    
  All values will be in the range of [0, 1000000].  
  The number of operations will be in the range of [1, 10000].  
  Please do not use the built-in HashSet library.  
    
### solution    
```    
    // 方法一  
        boolean hset[];  
        /** Initialize your data structure here. */  
        public MyHashSet() {  
            hset = new boolean[100001];  
        }  
          
        public void add(int key) {  
            hset[key] = true;  
        }  
          
        public void remove(int key) {  
            hset[key] = false;  
        }  
          
        /** Returns true if this set contains the specified element */  
        public boolean contains(int key) {  
            if(hset[key]==true)  
                return true;  
            else  
                return false;  
        }  
  
  
    // 方法二  
  class MyHashSet {  
    
      private int buckets = 1000;  
      private int itemsPerBucket = 1001;  
      private boolean[][] table;  
        
      /** Initialize your data structure here. */  
      public MyHashSet() {  
          table = new boolean[buckets][];  
      }  
    
      public int hash(int key) {  
          return key % buckets;  
      }  
    
      public int pos(int key) {  
          return key / buckets;  
      }  
        
      public void add(int key) {  
          int hashkey = hash(key);  
            
          if (table[hashkey] == null) {  
              table[hashkey] = new boolean[itemsPerBucket];  
          }  
          table[hashkey][pos(key)] = true;  
      }  
        
      public void remove(int key) {  
          int hashkey = hash(key);  
    
          if (table[hashkey] != null)  
              table[hashkey][pos(key)] = false;  
      }  
        
      /** Returns true if this set did not already contain the specified element */  
      public boolean contains(int key) {  
          int hashkey = hash(key);  
          return table[hashkey] != null && table[hashkey][pos(key)];  
      }  
  }  
```    
    
### 个人解读    
  模拟HashSet，直接参考答案吧。  
    
tags:    
  -  模拟  
  -  参考答案  
