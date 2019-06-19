### description  
  Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.
  
  Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
  
  Examples:
  Input:
  letters = ["c", "f", "j"]
  target = "a"
  Output: "c"
  
  Input:
  letters = ["c", "f", "j"]
  target = "c"
  Output: "f"
  
  Input:
  letters = ["c", "f", "j"]
  target = "d"
  Output: "f"
  
  Input:
  letters = ["c", "f", "j"]
  target = "g"
  Output: "j"
  
  Input:
  letters = ["c", "f", "j"]
  target = "j"
  Output: "c"
  
  Input:
  letters = ["c", "f", "j"]
  target = "k"
  Output: "c"
  Note:
  letters has a length in range [2, 10000].
  letters consists of lowercase letters, and contains at least 2 unique letters.
  target is a lowercase letter.
  
  大于给定元素的最小元素
  
### solution  
```  

// 方法一： 
Runtime: 1 ms, faster than 29.26% of Java online submissions for Find Smallest Letter Greater Than Target.
Memory Usage: 38.6 MB, less than 99.85% of Java online submissions for Find Smallest Letter Greater Than 

  class Solution {
      public char nextGreatestLetter(char[] letters, char target) {
          if(letters[letters.length - 1] <= target) return letters[0];
          int l = 0;
          int h = letters.length - 1;
          while(l < h) {
              int m = l + (h - l) / 2;
              int ch = letters[m];
              if(ch > target) {
                  h = m;
              } else {
                  l = l + 1;
              }
          }
          return letters[l];
      }
  }
  
  //方法二：
  
  Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Smallest Letter Greater Than Target.
  Memory Usage: 39.2 MB, less than 84.74% of Java online submissions for Find Smallest Letter Greater Than Target.
  
  
  class Solution {
     public char nextGreatestLetter(char[] letters, char target) {
      int n = letters.length;
      int l = 0, h = n - 1;
      while (l <= h) {
          int m = l + (h - l) / 2;
          if (letters[m] <= target) {
              l = m + 1;
          } else {
              h = m - 1;
          }
      }
      return l < n ? letters[l] : letters[0];
  }
  }
```  
  
### 个人解读  
  注意末尾元素都比目标<=的时候，返回首字母。  
  额。。。没注意，原题目的数组元素不重复的。。。
  
  
  
tags:  
  -  
