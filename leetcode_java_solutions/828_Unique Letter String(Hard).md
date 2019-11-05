### description    
  A character is unique in string S if it occurs exactly once in it.  
    
  For example, in string S = "LETTER", the only unique characters are "L" and "R".  
    
  Let's define UNIQ(S) as the number of unique characters in string S.  
    
  For example, UNIQ("LETTER") =  2.  
    
  Given a string S with only uppercases, calculate the sum of UNIQ(substring) over all non-empty substrings of S.  
    
  If there are two or more equal substrings at different positions in S, we consider them different.  
    
  Since the answer can be very large, return the answer modulo 10 ^ 9 + 7.  
    
     
    
  Example 1:  
    
  Input: "ABC"  
  Output: 10  
  Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".  
  Evey substring is composed with only unique letters.  
  Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10  
  Example 2:  
    
  Input: "ABA"  
  Output: 8  
  Explanation: The same as example 1, except uni("ABA") = 1.  
### solution    
```    
  // 方法一： 递推  
  Runtime: 18 ms, faster than 30.08% of Java online submissions for Unique Letter String.  
  Memory Usage: 37.8 MB, less than 33.33% of Java online submissions for Unique Letter String.  
    
  Map<Character, List<Integer>> index;  
      int[] peek;  
      int N;  
    
      public int uniqueLetterString(String S) {  
          index = new HashMap();  
          peek = new int[26];  
          N = S.length();  
    
          for (int i = 0; i < S.length(); ++i) {  
              char c = S.charAt(i);  
              index.computeIfAbsent(c, x-> new ArrayList<Integer>()).add(i);  
          }  
    
          long cur = 0, ans = 0;  
          for (char c: index.keySet()) {  
              index.get(c).add(N);    // 加一个是因为，最后一次，是用len - lastIndex  
              index.get(c).add(N);    // 加两个是防止index越界，运行到最后一次a的时候，旧长度为len - lastIndex, 还需要计算新的，为了令其为0，所以加了个N  
              cur += get(c);  // 刚开始，每个字符的长度  
          }  
    
          for (char c: S.toCharArray()) {  
              ans += cur;  
              long oldv = get(c);  
              peek[c - 'A']++;    // 当前字符索引指向下一次  
              cur += get(c) - oldv;  // 更新cur， 先减old 然后加上new  
          }  
          return (int) ans % 1_000_000_007;  
      }  
    
      public long get(char c) {  
          List<Integer> indexes = index.get(c);  
          int i = peek[c - 'A'];  
          return indexes.get(i+1) - indexes.get(i);  
      }  
        
        
   // 方法二： 代码优化，效率一样  
   Runtime: 17 ms, faster than 30.36% of Java online submissions for Unique Letter String.  
   Memory Usage: 38.2 MB, less than 33.33% of Java online submissions for Unique Letter String.  
     
   不再是一次一次加了，而是直接通过乘法原理，把每个list的数字乘起来。  
     
     
   class Solution {  
       public int uniqueLetterString(String S) {  
           Map<Character, List<Integer>> index = new HashMap();  
           for (int i = 0; i < S.length(); ++i) {  
               char c = S.charAt(i);  
               index.computeIfAbsent(c, x-> new ArrayList<Integer>()).add(i);  
           }  
     
           long ans = 0;  
           for (List<Integer> A: index.values()) {  
               for (int i = 0; i < A.size(); ++i) {  
                   long prev = i > 0 ? A.get(i-1) : -1;  
                   long next = i < A.size() - 1 ? A.get(i+1) : S.length();  
                   ans += (A.get(i) - prev) * (next - A.get(i));  
               }  
           }  
     
           return (int) ans % 1_000_000_007;  
       }  
   }  
         
```    
    
### 个人解读    
  通过26个List记录所有字符索引位置，然后假设'a'在x,y,z处出现过，那么x前面每一次'a'是unique字符的数量是y-x，经过了x以后，就变成z-y  
    
    
  方法二的优化：  
  考虑字母 "A"，并且有 S[10] = S[14] = S[20] = "A"，我们可以计算出仅包含 S[14] 的子串个数为 4 * 6 = 24，  
  S.length == 26;  
  list(字符'A'的)   
  仅包含S[10]: 11 * 4 // [0, 10] * [10 , 13]  
  仅包含S[14]: 4 * 6  // [11, 14] * [14, 19]  
  仅包含S[20]: 6 * 6  // [15, 20] * [20, 25]  
    
    
tags:    
  -  全部非空子序列  
  -  大数统计  
  -  字符串  
