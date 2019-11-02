### description    
  There is a box protected by a password. The password is a sequence of n digits where each digit can be one of the first k digits 0, 1, ..., k-1.  
    
  While entering a password, the last n digits entered will automatically be matched against the correct password.  
    
  For example, assuming the correct password is "345", if you type "012345", the box will open because the correct password matches the suffix of the entered password.  
    
  Return any password of minimum length that is guaranteed to open the box at some point of entering it.  
    
     
    
  Example 1:  
    
  Input: n = 1, k = 2  
  Output: "01"  
  Note: "10" will be accepted too.  
  Example 2:  
    
  Input: n = 2, k = 2  
  Output: "00110"  
  Note: "01100", "10011", "11001" will be accepted too.  
     
    
  Note:  
    
  n will be in the range [1, 4].  
  k will be in the range [1, 10].  
  k^n will be at most 4096.  
### solution    
```    
  
// 方法一：Hierholzer 算法  
  Runtime: 8 ms, faster than 47.71% of Java online submissions for Cracking the Safe.  
  Memory Usage: 38 MB, less than 70.00% of Java online submissions for Cracking the Safe.  
    
  class Solution {  
      Set<String> seen;  
      StringBuilder ans;  
    
      public String crackSafe(int n, int k) {  
          if (n == 1 && k == 1) return "0";  
          seen = new HashSet();  
          ans = new StringBuilder();  
    
          StringBuilder sb = new StringBuilder();  
          for (int i = 0; i < n-1; ++i)  
              sb.append("0");  
          String start = sb.toString();  
          //start是n-1个0  
    
          dfs(start, k);  
          ans.append(start);  
          return new String(ans);  
      }  
    
      public void dfs(String node, int k) {  
          for (int x = 0; x < k; ++x) {  
              String nei = node + x;  
              if (!seen.contains(nei)) {  
                  seen.add(nei);  
  //                  System.out.println("nei: " + nei + "   dfs: " + nei.substring(1));  
                  dfs(nei.substring(1), k);  
                  ans.append(x);  
  //                System.out.println(ans.toString());  
              }  
          }  
      }  
  }   
```    
    
### 个人解读    
  Input: n = 2, k = 2  
  Output: "00110"  
  Note: "01100", "10011", "11001" will be accepted too.  
    
  看着这个"00110"想起来了格雷码  
    
  尝试n=3,k=2  
  // 0001110011010  
  0011101000  
  0001011100  
    
  1、首先理想的最短字符长度： k^n + k - 1  
  2、反转后仍然是目标结果  
    
  完了。毫无头绪的一个题目  
    
  算了，直接看官方答案吧  
    
  总结：  
  1、先dfs，然后再输出字符，这样做会另输出结果是在一个dfs圈里面。  
  2、关键点：从起点u走了一圈必然能走到u，对于其中内部未走完的节点v，也能走完一圈走到v，这是让dfs圈能够接续的关键点。  
    
  ```  
  nei: 00   dfs: 0  
  nei: 01   dfs: 1  
  nei: 10   dfs: 0  
  nei: 02   dfs: 2  
  nei: 20   dfs: 0  
  0     //020100 是第一个dfs圈  
  nei: 21   dfs: 1  
  nei: 11   dfs: 1  
  nei: 12   dfs: 2  
  nei: 22   dfs: 2  
  02  
  022  
  0221  
  02211  
  022112  
  0221120  
  02211201  
  022112010  
  0  2211 20100  
    
  //"0 20100" 第一个闭合的dfs圈  
  //"22112" 第二个闭合的dfs圈  ，然后插入上面的0 2 中间  
    
  ```  
tags:    
  -  重点数学  
  -  数字逻辑  
  -  欧拉图(欧拉回路)  
  -  图论  
