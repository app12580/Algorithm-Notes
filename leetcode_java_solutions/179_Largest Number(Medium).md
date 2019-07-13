### description    
  Given a list of non negative integers, arrange them such that they form the largest number.  
    
  Example 1:  
    
  Input: [10,2]  
  Output: "210"  
  Example 2:  
    
  Input: [3,30,34,5,9]  
  Output: "9534330"  
  Note: The result may be very large, so you need to return a string instead of an integer.  
### solution    
```    
  
//方法一： 惊呆了，以为这个算法很垃圾的呢。。。  
Runtime: 3 ms, faster than 99.66% of Java online submissions for Largest Number.  
Memory Usage: 36.2 MB, less than 88.12% of Java online submissions for Largest Number.  
  
  
  class Solution {  
      public String largestNumber(int[] nums) {  
              String[] strArr = new String[nums.length];  
          for(int i = 0; i < nums.length; i++) {  
              strArr[i] = String.valueOf(nums[i]);  
          }  
          Arrays.sort(strArr, new Comparator<String>(){  
              @Override  
              public int compare(String s1, String s2) {  
                  if(s1.length() == s2.length()) {  
                      return s2.compareTo(s1);  
                  }  
                  for(int i = 0; i < Math.min(s1.length(), s2.length()); i++) {  
                      if(s1.charAt(i) != s2.charAt(i)) {  
                          return s2.charAt(i) - s1.charAt(i);  
                      }  
                  }  
                  return (s2+s1).compareTo(s1+s2);    //直接看前几位的结果  
              }  
                
          });  
          StringBuilder builder = new StringBuilder();  
          for(String s: strArr) {  
              if("0".equals(builder.toString()) && "0".equals(s)) continue;  
              builder.append(s);  
          }  
          return builder.toString();  
      }  
  }  
    
    
  // 方法二： 直接简写  
  Runtime: 4 ms, faster than 94.90% of Java online submissions for Largest Number.  
  Memory Usage: 36.3 MB, less than 88.02% of Java online submissions for Largest Number.  
    
  class Solution {  
      public String largestNumber(int[] nums) {  
              String[] strArr = new String[nums.length];  
          for(int i = 0; i < nums.length; i++) {  
              strArr[i] = String.valueOf(nums[i]);  
          }  
          Arrays.sort(strArr, new Comparator<String>(){  
              @Override  
              public int compare(String s1, String s2) {  
                  
                  return (s2+s1).compareTo(s1+s2);    //直接看前几位的结果  
              }  
                
          });  
          StringBuilder builder = new StringBuilder();  
          for(String s: strArr) {  
              if("0".equals(builder.toString()) && "0".equals(s)) continue;  
              builder.append(s);  
          }  
          return builder.toString();  
      }  
  }  
```    
    
### 个人解读    
  其实就是想办法排个序，然后从左到右挨个拼在一块即可。  
    
  对于两个String，如果前面的部分不相同，则大的排在前面。  
  如果前面的相同，则比较什么呢？  
  57 575 5757573 5757 5753 5752 578  
  578 57857： 57857 578 < 578 57857  
  比较s1 和 s2.subString(s1.length, Math.min(2))  
    
  真是蠢，搞那么多没用的，直接s1+s2和s2+s1比较就完事了  
    
    
    
tags:    
  -  贪心算法  
  -  整体法  
  -  字符串  
