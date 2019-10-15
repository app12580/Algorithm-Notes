### description    
  Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.  
    
  You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.  
    
  Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.  
    
  For the last line of text, it should be left justified and no extra space is inserted between words.  
    
  Note:  
    
  A word is defined as a character sequence consisting of non-space characters only.  
  Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.  
  The input array words contains at least one word.  
  Example 1:  
    
  Input:  
  words = ["This", "is", "an", "example", "of", "text", "justification."]  
  maxWidth = 16  
  Output:  
  [  
     "This    is    an",  
     "example  of text",  
     "justification.  "  
  ]  
  Example 2:  
    
  Input:  
  words = ["What","must","be","acknowledgment","shall","be"]  
  maxWidth = 16  
  Output:  
  [  
    "What   must   be",  
    "acknowledgment  ",  
    "shall be        "  
  ]  
  Explanation: Note that the last line is "shall be    " instead of "shall     be",  
               because the last line must be left-justified instead of fully-justified.  
               Note that the second line is also left-justified becase it contains only one word.  
  Example 3:  
    
  Input:  
  words = ["Science","is","what","we","understand","well","enough","to","explain",  
           "to","a","computer.","Art","is","everything","else","we","do"]  
  maxWidth = 20  
  Output:  
  [  
    "Science  is  what we",  
    "understand      well",  
    "enough to explain to",  
    "a  computer.  Art is",  
    "everything  else  we",  
    "do                  "  
  ]  
### solution    
```    
  class Solution {  
       public List<String> fullJustify(String[] words, int maxWidth) {  
          List<String> res = new ArrayList<>();  
          int index = 0;  
          int curLen;  
          List<String> curList = new ArrayList<>();  
          while(index < words.length) {  
              curLen = 0;  
              curList.clear();  
              while(index < words.length) {  
                  int cur = words[index].length();  
                  int next = curLen == 0 ? cur : curLen + cur + 1;  
                  if(next <= maxWidth) {  
                      curList.add(words[index]);  
                      curLen = next;  
                      index++;  
                  } else {  
                      break;  
                  }  
              }  
              if(index == words.length) {  
                  res.add(remain(curList, maxWidth));  
              } else {  
                  res.add(full(curList, maxWidth));  
              }  
          }  
          return res;  
      }  
    
      private String full(List<String> words, int maxWidth) {  
          int len = words.size();  
          if(len == 1) return remain(words, maxWidth);  
    
          int[] blanks = new int[len - 1];  
          int rest = maxWidth;  
          for(String w: words) {  
              rest -= w.length();  
          }  
          int low = rest / (len - 1);  
          int count = rest - low * (len - 1);  
          int index = 0;  
          for(int i = 0; i < count; i++) {  
              blanks[index++] = low + 1;  
          }  
          for(int i = 0; i < len - 1 - count; i++) {  
              blanks[index++] = low;  
          }  
          StringBuilder builder = new StringBuilder();  
          for(int i = 0; i < words.size(); i++) {  
              if(builder.length() == 0) {  
                  builder.append(words.get(i));  
              } else {  
                  for(int j = 0; j < blanks[i - 1]; j++) {  
                      builder.append(" ");  
                  }  
                  builder.append(words.get(i));  
              }  
          }  
          return builder.toString();  
      }  
    
      private String remain(List<String> words, int maxWidth) {  
          StringBuilder builder = new StringBuilder();  
          for(String w:words) {  
              if(builder.length() == 0) {  
                  builder.append(w);  
              } else {  
                  builder.append(" " + w);  
              }  
          }  
          for(int i = builder.length(); i < maxWidth; i++) {  
              builder.append(" ");  
          }  
          return builder.toString();  
      }  
  }  
```    
    
### 个人解读    
  思路：  
  流水账，先抓取单词，然后根据是否还剩下单词来进行两边对齐还是原封不动。  
    
  注意几点细节：  
  1、对于中间某个单个单词过于长的，需要判断if(len == 1),然后return full()，否则出现 / zero  
  2、最后一行需要凑够maxWidth字符  
    
  总结：  
  Hard题目也有这种流水账题目啊  
    
tags:    
  -  流水账  
