### description    
  Given words first and second, consider occurrences in some text of the form "first second third", where second comes immediately after first, and third comes immediately after second.  
    
  For each such occurrence, add "third" to the answer, and return the answer.  
    
     
    
  Example 1:  
    
  Input: text = "alice is a good girl she is a good student", first = "a", second = "good"  
  Output: ["girl","student"]  
  Example 2:  
    
  Input: text = "we will we will rock you", first = "we", second = "will"  
  Output: ["we","rock"]  
     
    
  Note:  
    
  1 <= text.length <= 1000  
  text consists of space separated words, where each word consists of lowercase English letters.  
  1 <= first.length, second.length <= 10  
  first and second consist of lowercase English letters.  
### solution    
```    
  class Solution {  
      public String[] findOcurrences(String text, String first, String second) {  
           List<String> list = new ArrayList<>();  
    
          String[] split = text.split(" ");  
    
          for(int i = 0; i < split.length - 2 ; i++) {  
              if(split[i].equals(first) && split[i + 1].equals(second)) {  
                  list.add(split[i + 2]);  
              }  
          }  
    
          String[] res = new String[list.size()];  
          int index = 0;  
          for(String s: list) {  
              res[index++] = s;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  遍历，然后用好标记法，注意细节。  
    
  因为first 和 second相等的情况，导致标记法很难搞。而且就算不想等，分类讨论也很难搞。  
    
  每次获取arr[i]和arr[i+1]，与first和second比较。  
    
tags:    
  -  字符串  
  -  标记法  
