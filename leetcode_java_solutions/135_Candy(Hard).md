### description    
  There are N children standing in a line. Each child is assigned a rating value.  
    
  You are giving candies to these children subjected to the following requirements:  
    
  Each child must have at least one candy.  
  Children with a higher rating get more candies than their neighbors.  
  What is the minimum candies you must give?  
    
  Example 1:  
    
  Input: [1,0,2]  
  Output: 5  
  Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.  
  Example 2:  
    
  Input: [1,2,2]  
  Output: 4  
  Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.  
               The third child gets 1 candy because it satisfies the above two conditions.  
### solution    
```    
  // 方法一： 流水账 + 回溯  
  class Solution {  
       public int candy(int[] ratings) {  
          int len = ratings.length;  
          if(len == 0) return 0;  
          int[] count = new int[len];  
          count[0] = 1;  
          int res = 0;  
          for(int i = 1; i < len; i++) {  
              if(ratings[i] > ratings[i - 1]) {  
                  count[i] = count[i - 1] + 1;  
              } else if(ratings[i] < ratings[i - 1]) {  
                  count[i] = 1;  
                  //让前面的递加1  
                  if(count[i - 1] > count[i]) continue; //error 少了这行  
                  int j = i - 1;  
                  while(j >= 0 && ratings[j] > ratings[j + 1] && count[j] <= count[j + 1]) {    //error：少了最后count的比较  
                      count[j]++;  
                      j--;  
                  }  
              } else {  
                  count[i] = 1;  
              }  
          }  
          for(int c: count) {  
              res += c;  
          }  
          return res;  
      }  
  }  
    
  // 方法二： 一段段的遍历  
  Runtime: 2 ms, faster than 100.00% of Java online submissions for Candy.  
  Memory Usage: 40.1 MB, less than 100.00% of Java online submissions for Candy.  
    
  class Solution {  
       public int candy(int[] ratings) {  
          int len = ratings.length;  
          if(len <= 1) return len;  
          int res = 0;  
          int preHigh = 0;  
          int i = 1;  
          while(i < len) {  
              int left = i - 1;  
              if(ratings[i] == ratings[i - 1]) {  
                  if(left == 0) {  
                      res += 2;  
                  } else {  
                      res ++;  
                  }  
                  preHigh = 0;  
              } else if(ratings[i] > ratings[i - 1]) {  
                  preHigh = 1;  
                  while(i < len - 1 && ratings[i + 1] > ratings[i]) {  
                      preHigh++;  
                      i++;  
                  }  
                  if(left == 0) {  
                      res += (preHigh + 2) * (preHigh + 1) / 2;  
                  } else {  
                      res += (preHigh + 2) * (preHigh + 1) / 2 - 1;  
                  }  
              } else {  
                  int curDown = 1;  
                  while(i < len - 1 && ratings[i + 1] < ratings[i]) {  
                      curDown++;  
                      i++;  
                  }  
                  if(left == 0) {  
                      res += (curDown + 2) * (curDown + 1) / 2;  
                  } else {  
                      res += (curDown + 1) * (curDown) / 2;  
                  }  
                  if(left > 0 && curDown > preHigh) {  
                      res += (curDown - preHigh);  
                  }  
                  preHigh = 0;  
              }  
              i++;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  刚开始想着用贪婪算法，然后发现像12345，这种的，需要向左不停地回溯去补充+1.  
  然后这种不停回溯的过程就让我联想到了单调栈。然后又发现，回溯的时候还要去找当前给了多少糖果，所以干脆就用一个数组算了，然后在数组里面回溯。成流水账问题。  
    
  方法一： 流水账，效率很低  
    
  可能的优化思路：  
  1、不是一个个遍历，而是直接while一段段的处理  
  2、类似于数山峰，交错数组的方式，有什么隐藏数学规律在里面。  
    
  方法二：  
  虽然是不停debug出来的产物，但看见这效率，还可以嗷~  
  1、一段段遍历  
  2、对于下降时，需要和preHigh比较  
  3、只有left==0时候加上左边起始点，否则不加  
  4、注意每次的preHigh要归零。  
    
    
tags:    
  -  重点数学  
  -  数字逻辑  
