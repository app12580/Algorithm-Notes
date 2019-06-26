### description    
  Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.  
    
  You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.  
    
  Example 1:  
  Input:  
  ["Shogun", "Tapioca Express", "Burger King", "KFC"]  
  ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]  
  Output: ["Shogun"]  
  Explanation: The only restaurant they both like is "Shogun".  
  Example 2:  
  Input:  
  ["Shogun", "Tapioca Express", "Burger King", "KFC"]  
  ["KFC", "Shogun", "Burger King"]  
  Output: ["Shogun"]  
  Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).  
  Note:  
  The length of both lists will be in the range of [1, 1000].  
  The length of strings in both lists will be in the range of [1, 30].  
  The index is starting from 0 to the list length minus 1.  
  No duplicates in both lists.  
### solution    
```    
  class Solution {  
      public String[] findRestaurant(String[] list1, String[] list2) {  
          Map<String, Integer> map = new HashMap<>();  
          for(int i = 0; i < list1.length; i++) {  
              map.put(list1[i], i);  
          }  
          List<String> list = new ArrayList<>();  
          int min = Integer.MAX_VALUE;  
          for(int i = 0; i < list2.length; i++) {  
              String s = list2[i];  
              if(map.containsKey(s)) {  
                  int sum = map.get(s) + i;  
                  if(sum < min) {  
                      min = sum;  
                      list.clear();  
                      list.add(s);  
                  } else if(sum == min) {  
                      list.add(s);  
                  }  
              }  
          }  
          String[] res = new String[list.size()];  
          int i = 0;  
          for(String s: list) {  
              res[i++] = s;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  刚开始想着双指针，但还是很棘手。  
  然后意识到遍历一遍拿到结果就够了，没必要实时判断双指针数据。  
    
tags:    
  -  Hash表  
