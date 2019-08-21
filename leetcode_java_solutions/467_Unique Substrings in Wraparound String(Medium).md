### description    
  Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".  
    
  Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.  
    
  Note: p consists of only lowercase English letters and the size of p might be over 10000.  
    
  Example 1:  
  Input: "a"  
  Output: 1  
    
  Explanation: Only the substring "a" of string "a" is in the string s.  
  Example 2:  
  Input: "cac"  
  Output: 2  
  Explanation: There are two substrings "a", "c" of string "cac" in the string s.  
  Example 3:  
  Input: "zab"  
  Output: 6  
  Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.  
### solution    
```    
     public int findSubstringInWraproundString(String p) {  
          int[] count = new int[26];  
          int maxLengthCur = 0;  
          for (int i = 0; i < p.length(); i++) {  
              if (i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || (p.charAt(i - 1) - p.charAt(i) == 25))) {  
                  maxLengthCur++;  
              }  
              else {  
                  maxLengthCur = 1;  
              }  
    
              int index = p.charAt(i) - 'a';  
              count[index] = Math.max(count[index], maxLengthCur);  
          }  
    
          int sum = 0;  
          for (int i = 0; i < 26; i++) {  
              sum += count[i];  
          }  
          return sum;  
      }  
```    
    
### 个人解读    
  通过set来避免重复，然后要尽量减少遍历的次数。  
  On遍历，然后遇到连续的字符串时候，从最后一个往前每一个字符串都加进set里面。  
    
  然而遇到 26个字母一直循环的这种用例，直接超时了。。。  
    
  ```  
    
  // 超时算法  
  public int findSubstringInWraproundString(String p) {  
          if(p.length() == 0) return 0;  
          Set<String> set = new HashSet<>();  
          set.add(p.substring(0,1));  
          int startIndex = 0;  
          for(int i = 1; i < p.length(); i++) {  
              char cur = p.charAt(i);  
              set.add(String.valueOf(cur));  
              if(cur == p.charAt(i-1) + 1 || (cur == 'a' && p.charAt(i - 1) == 'z')) {  
                  for(int j = startIndex; j <= i; j++) {  
                      set.add(p.substring(j, i + 1));  
                  }  
              } else {  
                  startIndex = i;  
              }  
          }  
          return set.size();  
      }  
  ```  
    
  超时了以后，就想会不会用一个int[26]来处理了。。。  
  需要把结果按照末尾字符来区分数组。  
    
    
tags:    
  -  数学  
  -  字符串解析  
  -  字母数组  
