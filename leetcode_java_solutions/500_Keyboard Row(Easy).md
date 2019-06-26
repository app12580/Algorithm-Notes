### description    
  Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.  
    
  Example:  
    
  Input: ["Hello", "Alaska", "Dad", "Peace"]  
  Output: ["Alaska", "Dad"]  
     
    
  Note:  
    
  You may use one character in the keyboard more than once.  
  You may assume the input string will only contain letters of alphabet.  
### solution    
```    
  class Solution {  
      public String[] findWords(String[] words) {  
          List<String> list = new ArrayList<>();  
          Map map = initData();  
            
          for(String word: words) {  
              char[] chars = word.toLowerCase().toCharArray();  
              int curRow = (int) map.get(chars[0]);  
              boolean flag = true;  
              for(int i = 1; i < chars.length; i++) {  
                  if((int)map.get(chars[i]) != curRow) {  
                      flag = false;  
                      break;  
                  }  
              }  
              if(flag) {  
                  list.add(word);  
              }  
          }  
            
          String[] res = new String[list.size()];  
          int index = 0;  
          for(String s: list) {  
              res[index++] = s;  
          }  
          return res;  
      }  
    
    
      private Map initData() {  
          Map map = new HashMap<>();  
          map.put('q', 1);  
          map.put('w', 1);  
          map.put('e', 1);  
          map.put('r', 1);  
          map.put('t', 1);  
          map.put('y', 1);  
          map.put('u', 1);  
          map.put('i', 1);  
          map.put('o', 1);  
          map.put('p', 1);  
          map.put('a', 2);  
          map.put('s', 2);  
          map.put('d', 2);  
          map.put('f', 2);  
          map.put('g', 2);  
          map.put('h', 2);  
          map.put('j', 2);  
          map.put('k', 2);  
          map.put('l', 2);  
          map.put('z', 3);  
          map.put('x', 3);  
          map.put('c', 3);  
          map.put('v', 3);  
          map.put('b', 3);  
          map.put('n', 3);  
          map.put('m', 3);  
          return map;  
      }  
  }  
```    
    
### 个人解读    
  没啥好说的，就是初始化条件太啰嗦了  
    
tags:    
  -  数学  
