### description    
  Design a data structure that supports all following operations in average O(1) time.  
    
  Note: Duplicate elements are allowed.  
  insert(val): Inserts an item val to the collection.  
  remove(val): Removes an item val from the collection if present.  
  getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.  
  Example:  
    
  // Init an empty collection.  
  RandomizedCollection collection = new RandomizedCollection();  
    
  // Inserts 1 to the collection. Returns true as the collection did not contain 1.  
  collection.insert(1);  
    
  // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].  
  collection.insert(1);  
    
  // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].  
  collection.insert(2);  
    
  // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.  
  collection.getRandom();  
    
  // Removes 1 from the collection, returns true. Collection now contains [1,2].  
  collection.remove(1);  
    
  // getRandom should return 1 and 2 both equally likely.  
  collection.getRandom();  
### solution    
```    
Runtime: 56 ms, faster than 76.52% of Java online submissions for Insert Delete GetRandom O(1) - Duplicates allowed.  
Memory Usage: 45.6 MB, less than 41.67% of Java online submissions for Insert Delete GetRandom O(1) - Duplicates allowed.  
  
  
   class RandomizedCollection {  
    
          private List<Integer> list;  
          private Map<Integer, List<Integer>> map;  
          private Random random;  
    
          public RandomizedCollection() {  
              random = new Random();  
              map = new HashMap<>();  
              list = new ArrayList<>();  
          }  
    
          public boolean insert(int val) {  
              boolean flag = true;  
              if (!map.containsKey(val)) {  
                  map.put(val, new ArrayList<>());  
              }  
              if(map.get(val).size() != 0) {  
                  flag = false;  
              }  
              map.get(val).add(list.size());  
              list.add(val);  
              return flag;  
          }  
    
          public boolean remove(int val) {  
              if (!map.containsKey(val) || map.get(val).size() == 0) return false;  
              List<Integer> curList = map.get(val);  
              int index = curList.get(curList.size() - 1);  
              if (index != list.size() - 1) {  
                  Integer last = list.get(list.size() - 1);  
                  List<Integer> lastList = map.get(last);  
                  for(int j = lastList.size() - 1; j >= 0; j--) {  
                      if(lastList.get(j) == list.size() - 1) {  
                          lastList.remove(lastList.get(j));  
                      }  
                  }  
                  lastList.add(index);  
                  list.set(index, last);  
                  index = list.size() - 1;  
              }  
              list.remove(index);  
              curList.remove(curList.size() - 1);  
              return true;  
          }  
    
          public int getRandom() {  
              int i = random.nextInt(list.size());  
              return list.get(i);  
          }  
      }  
  
```    
    
### 个人解读    
  与380类似，线性getRandom需要ArrayList，线性remove(搜索)需要HashMap  
  利用了swap。将每次remove的数字放调换最后，这样可以避免list的大量开销。  
    
  本题目多了一个可以允许重复数字的条件。第一反应就是将原来的Map<Integer,Integer>变成Map<Integer, List<Integer>>  
    
tags:    
  -  随机数  
  -  模拟  
