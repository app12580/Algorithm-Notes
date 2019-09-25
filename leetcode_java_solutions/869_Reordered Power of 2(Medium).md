### description    
  Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.  
    
  Return true if and only if we can do this in a way such that the resulting number is a power of 2.  
    
     
    
  Example 1:  
    
  Input: 1  
  Output: true  
  Example 2:  
    
  Input: 10  
  Output: false  
  Example 3:  
    
  Input: 16  
  Output: true  
  Example 4:  
    
  Input: 24  
  Output: false  
  Example 5:  
    
  Input: 46  
  Output: true  
     
    
  Note:  
    
  1 <= N <= 10^9  
### solution    
```    
  class Solution {  
      public boolean reorderedPowerOf2(int N) {  
          Map<Integer, List<int[]>> map = new HashMap<>();  
          int sum = 1;  
          fill(map, "1");  
          while(sum * 2 < Integer.MAX_VALUE && sum * 2 > sum) {  
              sum *= 2;  
              fill(map, sum+"");  
          }  
            
          int size = (N+"").length();  
          if(!map.containsKey(size)) {  
              return false;  
          }  
          int[] now = new int[10];  
          for(char ch: (N+"").toCharArray()) {  
              now[ch - '0']++;  
          }  
          for(int[] count: map.get(size)) {  
              if(check(count, now)) {  
                  return true;  
              }  
          }  
          return false;  
      }  
    
      private boolean check(int[] count, int[] now) {  
          for(int i = 0; i < 10; i++) {  
              if(count[i] != now[i]) {  
                  return false;  
              }  
          }  
          return true;  
      }  
    
      private void fill(Map<Integer, List<int[]>> map, String s) {  
          int size = s.length();  
          if(!map.containsKey(size)) {  
              map.put(size, new ArrayList<>());  
          }  
          int[] counts = new int[10];  
          for(char ch: s.toCharArray()) {  
              counts[ch - '0']++;  
          }  
          map.get(size).add(counts);  
            
      }  
  }  
```    
    
### 个人解读    
  很无聊的一个题目啊。。。。  
  流水账  
    
tags:    
  -  流水账  
