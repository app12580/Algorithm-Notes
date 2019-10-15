### description    
  Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).  
    
  Example:  
    
  Input: S = "ADOBECODEBANC", T = "ABC"  
  Output: "BANC"  
  Note:  
    
  If there is no such window in S that covers all characters in T, return the empty string "".  
  If there is such window, you are guaranteed that there will always be only one unique minimum window in S.  
### solution    
```    
Runtime: 22 ms, faster than 38.84% of Java online submissions for Minimum Window Substring.  
Memory Usage: 38.1 MB, less than 91.49% of Java online submissions for Minimum Window Substring.  
  
  class Solution {  
    
      public String minWindow(String s, String t) {  
          if(s == null || s.length() < t.length() || s.length() == 0){  
              return "";  
          }  
          HashMap<Character,Integer> map = new HashMap<Character,Integer>();  
          for(char c : t.toCharArray()) {  
              map.put(c, map.getOrDefault(c, 0) + 1);  
          }  
          int left = 0;  
          int minLeft = 0;    //目标结果的最左索引  
          int minLen = s.length()+1;  //  
          int count = 0;  // 符合字符的进度  
          for(int right = 0; right < s.length(); right++){  
              if(map.containsKey(s.charAt(right))){  
                  map.put(s.charAt(right),map.get(s.charAt(right))-1);  
                  if(map.get(s.charAt(right)) >= 0){  
                      count ++;  
                  }  
                  //开始回溯  
                  // 先回溯左边多余的节点，使整体最短。  
                  while(count == t.length()){  
                      if(right-left+1 < minLen){  
                          minLeft = left;  
                          minLen = right-left+1;  
                      }  
                      if(map.containsKey(s.charAt(left))){  
                          map.put(s.charAt(left),map.get(s.charAt(left))+1);  
                          if(map.get(s.charAt(left)) > 0){  
                              count --;  
                          }  
                      }  
                      left ++ ;  
                  }  
              }  
          }  
          if(minLen>s.length())  
          {  
              return "";  
          }  
    
          return s.substring(minLeft,minLeft+minLen);  
      }  
        
        
  }  
```    
    
### 个人解读    
  标准的滑动窗口问题。  
  先从左到右获取初始长度的窗口，然后再开始一点点右移。  
    
  反思：  
  1、实际写代码的时候发现，并没有必要分开处理  
  2、只使用一个int用来描述进度并不够，需要配合map里的count使用  
  3、一个好的认识，把每个字符想成一个木桩，作为回溯时候的目标。  
    
  放弃了，问题在于一开始就没有想好如何去滑动这个问题。修修补补，等想清楚时候已经晚了。  
  而且第一次遍历的时候，可以把所有的目标内的东西存进去。  
    
  参考答案一的总结：  
  1、将原来的每个字符与目标中的个数比较，转换成个数与0的比较  
  2、使用一个int作为count来描述进度的进行程度  
  3、使用s.length()作为初始  
  4、滑动逻辑：  
    我的错误：先去找到上一次重复的字符，然后要么抛弃它，要么从它开始(本次字符只出现了一次)  
    正确逻辑：滑动，直到破坏完整计数。  
    
    
  ```  
     public String minWindow(String s, String t) {  
          String res = null;  
          Map<Character, Integer> target = new HashMap<>();  
          Map<Character, Integer> curMap = new HashMap<>();  
          int total = 0;  
          for (char ch : t.toCharArray()) {  
              target.put(ch, target.getOrDefault(ch, 0) + 1);  
              total++;  
          }  
          int curNum = 0;  
          int left = 0;  
          while(left < s.length() && !target.containsKey(s.charAt(left))) {  
              left++;  
          }  
          int right = left;  
          while (right < s.length()) {  
                  char cur = s.charAt(right);  
              if (target.containsKey(cur)) {  
                  //先判断curNum是否可以添加  
                  if (!curMap.containsKey(cur) || curMap.get(cur) < target.get(cur)) {  
                      curNum++;  
                      //回溯前必须先把当前count算上，不然会乱套。  
                      curMap.put(cur, curMap.getOrDefault(cur, 0) + 1);  
                      if (curNum == total) {  
                          //进行结果更新  
                          String curRes = s.substring(left, right + 1);  
                          if(res == null) {  
                              res = curRes;  
                          } else if(res.length() > curRes.length()) {  
                              res = curRes;  
                          }  
                          //第二次回溯，回溯到上一个cur的位置  
                          //先用一个while找到上次cur出现的位置，同时进行curMap清算  
                          while(left < right && s.charAt(left) != cur) {  
                              char c = s.charAt(left);  
                              if(curMap.containsKey(c)) {  
                                  if(curMap.get(c) <= target.get(c)) curNum--;  
                                  curMap.put(c, curMap.get(c) - 1);  
                              }  
                              left++;  
                          }  
                          if(target.get(cur) > 1) {  
                              //如果大于1次，就舍弃前面第一个，否则保留  
                              curMap.put(cur, curMap.get(cur) - 1);  
                              curNum--;  
                              left++;  
                              while(left < s.length() && !target.containsKey(s.charAt(left))) {  
                                  left++;  
                              }  
                          } else {  
    
                          }  
    
                      }  
                  } else {  
                      curMap.put(cur, curMap.getOrDefault(cur, 0) + 1);  
                  }  
              }  
    
              right++;  
          }  
          return res == null ? "" : res;  
      }  
```  
    
    
tags:    
  -    
