### description    
  You have an array of logs.  Each log is a space delimited string of words.  
    
  For each log, the first word in each log is an alphanumeric identifier.  Then, either:  
    
  Each word after the identifier will consist only of lowercase letters, or;  
  Each word after the identifier will consist only of digits.  
  We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.  
    
  Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.  
    
  Return the final order of the logs.  
    
     
    
  Example 1:  
    
  Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]  
  Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]  
     
    
  Note:  
    
  0 <= logs.length <= 100  
  3 <= logs[i].length <= 100  
  logs[i] is guaranteed to have an identifier, and a word after the identifier.  
### solution    
```    
  class Solution {  
      public String[] reorderLogFiles(String[] logs) {  
          Comparator<String> myComp = new Comparator<String>() {  
              @Override  
              public int compare(String s1, String s2) {  
                  int s1si = s1.indexOf(' ');  
                  int s2si = s2.indexOf(' ');  
                  char s1fc = s1.charAt(s1si+1);  
                  char s2fc = s2.charAt(s2si+1);  
                    
                  if (s1fc <= '9') {  
                    //如果当前是数字  
                      if (s2fc <= '9') return 0;    //原封不动  
                      else return 1;        //另外一个是字母，则调换位置  
                  }   
                  //如果当前是字母  
                  if (s2fc <= '9') return -1;  
                    
                  int preCompute = s1.substring(s1si+1).compareTo(s2.substring(s2si+1));    // 按照内容排序  
                  if (preCompute == 0) return s1.substring(0,s1si).compareTo(s2.substring(0,s2si));  
                  return preCompute;   
              }  
          };  
            
          Arrays.sort(logs, myComp);  
          return logs;  
      }  
  }  
```    
    
### 个人解读    
  创建一个字符串排序的规则，然后排序。  
    
tags:    
  -  字符串排序  
