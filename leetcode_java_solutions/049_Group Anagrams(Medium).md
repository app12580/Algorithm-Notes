### description    
  Given an array of strings, group anagrams together.  
    
  Example:  
    
  Input: ["eat", "tea", "tan", "ate", "nat", "bat"],  
  Output:  
  [  
    ["ate","eat","tea"],  
    ["nat","tan"],  
    ["bat"]  
  ]  
  Note:  
    
  All inputs will be in lowercase.  
  The order of your output does not matter.  
    
### solution    
```    
  
// 方法一： 内部类  
这个速度。。。。惊呆了。。。  
Runtime: 431 ms, faster than 5.02% of Java online submissions for Group Anagrams.  
Memory Usage: 43.6 MB, less than 57.94% of Java online submissions for Group Anagrams.  
  
  class Solution {  
        public List<List<String>> groupAnagrams(String[] strs) {  
          List<List<String>> res = new ArrayList<>();  
          List<Des> flags = new ArrayList<>();  
          for(String s: strs) {  
              int[] arr = new int[26];  
              for(char ch: s.toCharArray()) {  
                  arr[ch - 'a']++;  
              }  
              Des cur = new Des(arr);  
              int index = flags.indexOf(cur);  
              if(index >= 0) {  
                  res.get(index).add(s);  
              } else {  
                  res.add(new ArrayList<String>(){{this.add(s);}});  
                  flags.add(cur);  
              }  
                
          }  
          return res;  
      }  
    
    
      class Des {  
          int[] cnts = new int[26];  
          private int hash = 1;  
    
          public Des(int[] cnts) {  
              this.cnts = cnts;  
              for(int c: cnts) {  
                  hash *= c;  
              }  
          }  
    
          @Override  
          public int hashCode() {  
              return hash % 26;  
          }  
    
          @Override  
          public boolean equals(Object obj) {  
              Des other = (Des)obj;  
              for(int i = 0; i < 26; i++) {  
                  if(this.cnts[i] != other.cnts[i]) {  
                      return false;  
                  }  
              }  
              return true;  
          }  
      }  
  }  
    
  // 用String标记  
  class Solution {  
      public List<List<String>> groupAnagrams(String[] strs) {  
           Map<String, List<String>> map = new HashMap<>();  
          for(String s: strs) {  
              char[] arr = s.toCharArray();  
              Arrays.sort(arr);  
              String key = new String(arr);  
              List<String> list = map.getOrDefault(key, new ArrayList<String>());  
              list.add(s);  
              map.put(key, list);  
          }  
          return new ArrayList<>(map.values());  
      }  
  }  
```    
    
### 个人解读    
  如果能够用一个变量描述26个字符出现的情况，并且能够通过hash和equal保证是一个东西。  
  因为java语言的问题，数组无法实现此功能。先自己尝试写个类测试一下效率。看了下时间，大概因为创建类在算法中很不可取，因为开销太高。  
    
  看了下答案，为啥自建类不如一个String啊？？？？明明自建类少了排序的开销，也压缩了数据量。搞不懂。。。。  
    
  还看到了一种用26个质数去描述的，但是感觉有问题，int超限。  
  核心问题在于如何用一个东西去描述字符串特征。  
    
tags:    
  -  算法思想尝试：java语言的局限性  
  -  java核心效率问题不明白  
