### description    
  Given an integer array with even length, where different numbers in this array represent different kinds of candies. Each number means one candy of the corresponding kind. You need to distribute these candies equally in number to brother and sister. Return the maximum number of kinds of candies the sister could gain.  
  Example 1:  
  Input: candies = [1,1,2,2,3,3]  
  Output: 3  
  Explanation:  
  There are three different kinds of candies (1, 2 and 3), and two candies for each kind.  
  Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too.   
  The sister has three different kinds of candies.   
  Example 2:  
  Input: candies = [1,1,2,3]  
  Output: 2  
  Explanation: For example, the sister has candies [2,3] and the brother has candies [1,1].   
  The sister has two different kinds of candies, the brother has only one kind of candies.   
  Note:  
    
  The length of the given array is in range [2, 10,000], and will be even.  
  The number in given array is in range [-100,000, 100,000].  
### solution    
```    
  class Solution {  
      public int distributeCandies(int[] candies) {  
          int len = candies.length;  
          Set<Integer> set = new HashSet<>();  
          int index = 0;  
          while(set.size() < len / 2 && index < len) {  
              set.add(candies[index++]);  
          }  
          return set.size();  
      }  
  }  
```    
    
### 个人解读    
  这题说了一大堆，但好像就是要获取所有种类就完事了吧。  
  讲真，这题目莫名其妙的。  
    
tags:    
  -  数学  
