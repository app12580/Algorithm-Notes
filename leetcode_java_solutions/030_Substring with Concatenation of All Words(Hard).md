### description    
  You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.  
    
  Example 1:  
    
  Input:  
    s = "barfoothefoobarman",  
    words = ["foo","bar"]  
  Output: [0,9]  
  Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.  
  The output order does not matter, returning [9,0] is fine too.  
  Example 2:  
    
  Input:  
    s = "wordgoodgoodgoodbestword",  
    words = ["word","good","best","word"]  
  Output: []  
### solution    
```    
Runtime: 9 ms, faster than 86.46% of Java online submissions for Substring with Concatenation of All Words.  
Memory Usage: 38.3 MB, less than 97.62% of Java online submissions for Substring with Concatenation of All Words.  
  
  class Solution {  
        
      public List<Integer> findSubstring(String s, String[] words) {  
          List<Integer> res = new ArrayList<>();  
          if(s.length()==0 ||words.length == 0)return res;  
          int gap = words[0].length();  
          int len = words.length;  
    
          Map<String, Integer> targetMap = new HashMap<>();  
          Map<String, Integer> curMap = null;  
          for (String w : words) {  
              targetMap.put(w, targetMap.getOrDefault(w, 0) + 1);  
          }  
          int total = gap * len;  
          int curNum = 0;  
          for (int i = 0; i < gap; i++) {  
              curMap = new HashMap<>();  
              curNum = 0;  
              for (int j = i; j + gap <= s.length(); j += gap) {  
                  String cur = s.substring(j, j + gap);  
                  if(!targetMap.containsKey(cur)) {  
                      //不包含  
                      curMap = new HashMap<>();  
                      curNum = 0;  
                      if (targetMap.containsKey(cur)) {  
                          //跳动到当前  
                          j -= gap;  
                      }  
                  } else if(!curMap.containsKey(cur) || curMap.get(cur) < targetMap.get(cur)) {  
                      //可以继续循环  
                      curNum++;  
                      curMap.put(cur, curMap.getOrDefault(cur, 0) + 1);  
                      if (curNum == len) {  
                          int left = j - total + gap;  
                          res.add(left);  
                          //滑动一下  
                          String first = s.substring(left, left + gap);  
                          curMap.put(first, curMap.get(first) - 1);  
                          curNum--;  
                      }  
                  } else {  
                      //超过次数  
                      int left = j - (curNum * gap);  
                      int first = s.indexOf(cur, left);  
                      for(int k = left; k <= first; k += gap) {  
                          String del = s.substring(k, k + gap);  
                          curMap.put(del, curMap.get(del) - 1);  
                          curNum--;  
                      }  
                      //加上当前，然后继续循环  
                      curMap.put(cur, curMap.get(cur) + 1);  
                      curNum++;  
                  }  
              }  
          }  
    
          return res;  
      }  
    
  }  
```    
    
### 个人解读    
  神似流水账的题目，但是需要注意words中的单词可以是重复的，所以需要一个Map来统计个数肯定没有错。  
    
  接下来的核心问题是，如何复用结果?可不可以类似于curLen问题，只不过由原本的单个字符比较，变成了一个个单词的比较。  
    
  疑问：  
  1、要不要为了连续性，遍历时候k等分的去遍历：147,258,369这样的去遍历？  
    
  思路一：  
  1、k等分遍历  
  2、双方通过Map统计，并且使用一个int来和length比较，作为结果标准  
  3、滑动窗口遍历，这样也省的去用一个map来记录原来数组内的情况  
  4、思路应该没有问题，剩下的就是写代码了，还有考察一下对于滑动窗口的掌握程度。  
  5、并不是滑动窗口，而是跳跃窗口，遇到不行的，直接往后面跳。但是遇到行的，需要滑动一下  
    
  错误！！！！  
  不能直接跳，导致前面的没有利用上。  
  应该分类：如果不在target中，则可以直接跳；如果在target中，因为出现次数过多而不行的话，则需要跳刀第一次出现之后。但是这样就太麻烦了。。。  
    
   看了一下discuss，感觉高票数的解法也不怎么样啊。。。有的还没有复用中间结果，直接无脑直接遍历的。  
     
   历经磨难，总算做出来了，效率还可以嗷  
    
tags:    
  -  字符串  
  -  滑动+跳动窗口  
