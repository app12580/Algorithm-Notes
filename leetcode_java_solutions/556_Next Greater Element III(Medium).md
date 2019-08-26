### description    
  Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.  
    
  Example 1:  
    
  Input: 12  
  Output: 21  
     
    
  Example 2:  
    
  Input: 21  
  Output: -1  
    
  给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的位数完全相同，并且其值大于n。如果不存在这样的32位整数，则返回-1。  
### solution    
```    
//方法一： 低效率  
Runtime: 1 ms, faster than 26.64% of Java online submissions for Next Greater Element III.  
Memory Usage: 33.3 MB, less than 10.00% of Java online submissions for Next Greater Element III.  
  
  class Solution {  
      
      public int nextGreaterElement(int n) {  
          String s = n + "";  
          if(s.length() <= 1) {  
              return -1;  
          }  
          int index = s.length() - 2;  
          while(index >= 0) {  
              if(s.charAt(index) < s.charAt(index + 1)) {  
                  break;  
              }  
              index--;  
          }  
          if(index == -1) {  
              return -1;  
          }  
          char[] chars = s.toCharArray();  
           int swapIndex = -1;  
           for (int i = index + 1; i < chars.length; i++) {  
              int cur = chars[i] - '0';  
              if (chars[i] <= chars[index]) continue;  
              if (swapIndex == -1) {  
                  swapIndex = i;  
              } else if (cur < chars[swapIndex] - '0') {  
                  swapIndex = i;  
              }  
          }  
          swap(chars, swapIndex, index);  
          Arrays.sort(chars, index + 1, chars.length);  
            long aLong = Long.valueOf(new String(chars));  
          if(aLong > Integer.MAX_VALUE) return -1;  
          return (int) aLong;  
    
      }  
    
      private void swap(char[] chars, int i, int j) {  
          char t = chars[i];  
          chars[i] = chars[j];  
          chars[j] = t;  
      }  
  }  
```    
    
### 个人解读    
  完全读不懂题目在说啥。  
    
  经过解读，题目描述的关键在于："digits"这个词，这个词不是比特的那个位，而是个十百千万的那个位。  
    
  步骤  
  1、从后往前，先找到第一个下降的数字索引i  
  2、如果i不存在，返回-1  
  3、如果i存在，另i的位置变成i以后，大于nums[i]的最小值  
  4、将剩下的数字进行从小到的顺序排列  
    
    
  能优化的地方：  
  //根据354321这种数字线型  
  1、获取swapIndex可以从后往前遍历，第一个比index大的就是所求结果。  
  2、调换完之后，不用sort，而是直接reverse即可。  
    
tags:    
  -  读不懂题目描述  
  -  数学  
  -  数字排列  
