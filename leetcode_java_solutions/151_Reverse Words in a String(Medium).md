### description    
  Given an input string, reverse the string word by word.  
    
     
    
  Example 1:  
    
  Input: "the sky is blue"  
  Output: "blue is sky the"  
  Example 2:  
    
  Input: "  hello world!  "  
  Output: "world! hello"  
  Explanation: Your reversed string should not contain leading or trailing spaces.  
  Example 3:  
    
  Input: "a good   example"  
  Output: "example good a"  
  Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.  
     
    
  Note:  
    
  A word is defined as a sequence of non-space characters.  
  Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.  
  You need to reduce multiple spaces between two words to a single space in the reversed string.  
     
    
  Follow up:  
    
  For C programmers, try to solve it in-place in O(1) extra space.  
### solution    
```    
  public class Solution {  
    
      public String reverseWords(String s) {  
          if (s == null) return null;  
    
          char[] a = s.toCharArray();  
          int n = a.length;  
    
          // step 1. reverse the whole string  
          reverse(a, 0, n - 1);  
          // step 2. reverse each word  
          reverseWords(a, n);  
          // step 3. clean up spaces  
          return cleanSpaces(a, n);  
      }  
        
      //while里面另一种写法  
      void reverseWords2(char[] a, int n) {  
          int i = 0, j = 0;  
    
          while (i < n) {  
              i=j;  
              while (i < n && a[i] == ' ') i++; // skip spaces  
              j=i;  
              while (j < n && a[j] != ' ') j++; // skip non spaces  
              reverse(a, i, j - 1);                      // reverse the word  
          }  
      }  
      void reverseWords(char[] a, int n) {  
          int i = 0, j = 0;  
    
          while (i < n) {  
              while (i < j || i < n && a[i] == ' ') i++; // skip spaces  
              while (j < i || j < n && a[j] != ' ') j++; // skip non spaces  
              reverse(a, i, j - 1);                      // reverse the word  
          }  
      }  
    
      // trim leading, trailing and multiple spaces  
      String cleanSpaces(char[] a, int n) {  
          int i = 0, j = 0;  
    
          while (j < n) {  
              while (j < n && a[j] == ' ') j++;             // skip spaces  
              while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces  
              while (j < n && a[j] == ' ') j++;             // skip spaces  
              if (j < n) a[i++] = ' ';                      // keep only one space  
          }  
            
            
    
          return new String(a).substring(0, i);  
      }  
    
      // reverse a[] from a[i] to a[j]  
      private void reverse(char[] a, int i, int j) {  
          while (i < j) {  
              char t = a[i];  
              a[i++] = a[j];  
              a[j--] = t;  
          }  
      }  
    
  }  
```    
    
### 个人解读    
  这种题的难点主要在于一些细节的处理上： 开头和结尾的空格，多格空格  
  将每个单词翻转，然后将整个字符串翻转，去除多余空格。  
    
  算法根据实现结构可以分为两种：整体实现(单函数)和功能拆分(多个函数)，很多时候，虽然只是一个简单的while语句，但是单独拿出去作为一个方法还是很有必要的。可读性能上升很多个台阶。  
  比如本题的reverseWords方法，在while里面有个细节，就是每次while要么只动了i，要么只动了j，没有ij一块动。  
  while(i < j) i++ 近似于另i=j;  
  最后去除多余空格的操作类似于数组把0全往后移的算法，如果是我自己做的话，可能会创建一个变量，用来存储是否已经有零了，并且在遍历的时候会一直的维护。  
  算法翻译：  
  while (j < n) {  
              while (j < n && a[j] == ' ') j++;             // 把j指向第一个不是空格的位置A  
              while (j < n && a[j] != ' ') a[i++] = a[j++]; //A点进入循环，同时把j指向之后的第一个空格位置B  
              while (j < n && a[j] == ' ') j++;             // 把j指向第一个不是空格的位置A(用来判断之后是否还有单词)  
              if (j < n) a[i++] = ' ';                      // 如果之后还有其他单词，手动加上一个空格  
          }  
  这是一种做法：思路是新的字符串有两种结构，单词和空格，他每次只把单词处理了，而空格是在另外的地方处理。  
  另外还有一种做法是在a[i++]=a[j++]这一步的时候就把空格加进去。大概这一步判断的时候判断会更麻烦一些？？也许这时就需要引入一个中间变量了。          
tags:    
  -   字符串  
  -   数组反转  
    
