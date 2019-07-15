### description    
  Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.  
    
  According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."  
    
  Example:  
    
  Input: citations = [3,0,6,1,5]  
  Output: 3   
  Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had   
               received 3, 0, 6, 1, 5 citations respectively.   
               Since the researcher has 3 papers with at least 3 citations each and the remaining   
               two with no more than 3 citations each, her h-index is 3.  
  Note: If there are several possible values for h, the maximum one is taken as the h-index.  
### solution    
```    
  class Solution {  
      public int hIndex(int[] citations) {  
          if(citations.length == 0) return 0;  
          // if(citations.length == 1) return citations[0] >= 1 ? 1 : 0;  
           Arrays.sort(citations);  
          int l = 0;  
          int h = citations.length - 1;  
          int len = citations.length;  
          while(l < h) {  
              int m = l + (h - l) / 2;  
              if(citations[m] < len - m) {  
                  l = m + 1;  
              } else {  
                  h = m;  
              }  
          }  
          return citations[l] >= len - l ? len - l : 0;  
      }  
  }  
    
  //方法二： 优化  
  class Solution {  
      public int hIndex(int[] citations) {  
          if (citations == null || citations.length == 0) {  
              return 0;  
          }  
          int n = citations.length;  
            
          int[] count = new int[n+1];  
          for (int cite : citations) {  
              if (cite >= n) {  
                  count[n]++;  
              }  
              else {  
                  count[cite]++;  
              }  
          }  
          int cnt = 0;  
          for (int i = n; i >= 0; i--) {  
              cnt += count[i];  
              if (cnt >= i) {  
                  return i;  
              }  
          }  
          return 0;  
      }  
  }  
```    
    
### 个人解读    
  关于至多至少，一直都是很多题目里面很扯淡的一个提问因素。  
  本题很明确：说的很清楚。  
  ```  
  说明: 如果 h 有多种可能的值，h 指数是其中最大的那个。  
  ```  
    
  首先肯定需要先排序一下。  
  解释h指数：  
  ```  
   h 指数是指他（她）的 （N 篇论文中）至多有 h 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）  
     
   对于一个h，则该数组中的最多前h大的数字要大于等于h，并且剩下的要小于h  
  ```  
    
  问题来了。。。。我TM H指数为一万有毛病吗？？？  
  百度了一下：  
  ```  
  "A scientist has index h if (????)h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."  
  ```  
  原来的英文根本就没有至多前h的限制，瞎xx翻译，连百度百科都不知道从哪里抄的。  
    
  [0,1,2,3,5,6] 因为要看右边的排序顺位，所以从大到小排序效果会更好。  
    
  [6,5,4,3,2,1,0]  
  [6,5,3,3,2,0,0]  
  i+1 要 <=nums[i]  
    
  二分法的应用二：满足条件的最右值  
  //如果正向排序，多了一个len-i的比较，更麻烦一些  
  [0,0,2,3,3,5,6]   len = 7  
  nums[i] >= len - i 的最左值  
    
    
  方法二的做法：  
  先统计0~len每一个引用了cnt的次数  //count[5] = 5 表示有5个被引用了5次  
  然后从大到小遍历，返回第一个符合要求的数字。  
    
tags:    
  -  重点数学  
  -  二分法  
