### description    
  Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.   
    
  Person A will NOT friend request person B (B != A) if any of the following conditions are true:  
    
  age[B] <= 0.5 * age[A] + 7  
  age[B] > age[A]  
  age[B] > 100 && age[A] < 100  
  Otherwise, A will friend request B.  
    
  Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.  
    
  How many total friend requests are made?  
    
  Example 1:  
    
  Input: [16,16]  
  Output: 2  
  Explanation: 2 people friend request each other.  
  Example 2:  
    
  Input: [16,17,18]  
  Output: 2  
  Explanation: Friend requests are made 17 -> 16, 18 -> 17.  
  Example 3:  
    
  Input: [20,30,100,110,120]  
  Output:   
  Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.  
     
    
  Notes:  
    
  1 <= ages.length <= 20000.  
  1 <= ages[i] <= 120.  
### solution    
```    
  
Runtime: 2 ms, faster than 97.76% of Java online submissions for Friends Of Appropriate Ages.  
Memory Usage: 41.8 MB, less than 20.00% of Java online submissions for Friends Of Appropriate Ages.  
  
  
  class Solution {  
       public int numFriendRequests(int[] ages) {  
          int[] counts = new int[121];  
          int[] sums = new int[121];  
          for(int i = 0; i < ages.length; i++) {  
              int a = ages[i];  
              counts[a]++;  
          }  
          for(int i = 1; i < counts.length; i++) {  
              sums[i] = sums[i - 1] + counts[i];  
          }  
          int res = 0;  
          for(int i = 1; i < counts.length; i++) {     //这一行15是为了什么？  
              if(counts[i] == 0) continue;  
              int min = i / 2 + 7;  
              if(min > i - 1) continue;  //或者可以直接令i从15开始循环  
              int count = counts[i];    //当前年龄的人数  
              int need = sums[i] - sums[min] - 1; //满足条件的人[min, i - 1]  
              res += count * need;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  重点是这三个条件  
    
  ```  
    Person A will NOT friend request person B (B != A) if any of the following conditions are true:  
      
    age[B] <= 0.5 * age[A] + 7  
    age[B] > age[A]  
    age[B] > 100 && age[A] < 100  
  ```  
  年纪小的不能给年纪大的发消息，剩下的直接套用  
  注意第三条是第二条的子集。  
    
  两万个age的数组，感觉只要有排序就是大工程了。给了个提示，直接统计每个年龄的数量会不会有效。  
  考虑使用TreeMap，因为年龄是有限数组，改用int[]  
    
  总结：  
  翻译之后就是[0.5 * age[A] + 7, A]这个区间是可以的  
    
  超时算法  
  ```  
  class Solution {  
      public int numFriendRequests(int[] ages) {  
          Arrays.sort(ages);  
          int res = 0;  
          for(int i = 0; i < ages.length; i++) {  
              int a = ages[i];  
              for(int j = 0; j < ages.length; j++) {                  
                  int b = ages[j];  
                  if(b > a) break;  
                  if(i == j || b <= 0.5 * a + 7 || (b > 100 && a < 100)) {  
                      continue;  
                  }  
                  res++;  
              }  
          }  
          return res;  
      }  
  }  
  ```  
    
tags:    
  -  for循环  
  -  数字逻辑  
  -  重点数学  
