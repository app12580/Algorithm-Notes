### description    
  Design a data structure that supports all following operations in average O(1) time.  
    
  insert(val): Inserts an item val to the set if not already present.  
  remove(val): Removes an item val from the set if present.  
  getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.  
  Example:  
    
  // Init an empty set.  
  RandomizedSet randomSet = new RandomizedSet();  
    
  // Inserts 1 to the set. Returns true as 1 was inserted successfully.  
  randomSet.insert(1);  
    
  // Returns false as 2 does not exist in the set.  
  randomSet.remove(2);  
    
  // Inserts 2 to the set, returns true. Set now contains [1,2].  
  randomSet.insert(2);  
    
  // getRandom should return either 1 or 2 randomly.  
  randomSet.getRandom();  
    
  // Removes 1 from the set, returns true. Set now contains [2].  
  randomSet.remove(1);  
    
  // 2 was already in the set, so return false.  
  randomSet.insert(2);  
    
  // Since 2 is the only number in the set, getRandom always return 2.  
  randomSet.getRandom();  
### solution    
```    
  class RandomizedSet {  
    
     private List<Integer> list;  
      private Map<Integer, Integer> map;  
      private Random random;  
    
      public RandomizedSet() {  
          random = new Random();  
          map = new HashMap<>();  
          list = new ArrayList<>();  
      }  
    
      public boolean insert(int val) {  
          if(map.containsKey(val)) return false;  
          map.put(val, list.size());  
          list.add(val);  
          return true;  
      }  
    
      public boolean remove(int val) {  
          if(!map.containsKey(val)) return false;  
          int index = map.get(val);  
          if(val != list.size() - 1) {  
              Integer last = list.get(list.size() - 1);  
              map.put(last, index);  
              list.set(index, last);  
              index = list.size() - 1;  
          }  
          list.remove(index);  
          map.remove(val);  
          return true;  
      }  
    
      public int getRandom() {  
          int i = random.nextInt(list.size());  
          return list.get(i);  
      }  
  }  
    
  /**  
   * Your RandomizedSet object will be instantiated and called as such:  
   * RandomizedSet obj = new RandomizedSet();  
   * boolean param_1 = obj.insert(val);  
   * boolean param_2 = obj.remove(val);  
   * int param_3 = obj.getRandom();  
   */  
```    
    
### 个人解读    
  几点原则：  
  因为时间要求O(1)  
  1、获取时必须通过数组  
  2、判断是否有必须通过Hash表。  
    
  然而在删除时候遇到了问题，就算有排序，也是NlgN的时间。  
    
  解决办法：  
  删除时候保证大部分的标记不变，先swap，然后再删除，这样子可以不用遍历删除了。  
    
    
tags:    
  -  随机数  
  -  模拟  
