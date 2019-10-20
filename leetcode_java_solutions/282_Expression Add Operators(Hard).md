### description    
  Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.  
    
  Example 1:  
    
  Input: num = "123", target = 6  
  Output: ["1+2+3", "1*2*3"]   
  Example 2:  
    
  Input: num = "232", target = 8  
  Output: ["2*3+2", "2+3*2"]  
  Example 3:  
    
  Input: num = "105", target = 5  
  Output: ["1*0+5","10-5"]  
  Example 4:  
    
  Input: num = "00", target = 0  
  Output: ["0+0", "0-0", "0*0"]  
  Example 5:  
    
  Input: num = "3456237490", target = 9191  
  Output: []  
### solution    
```    
  // 方法一 DFS+回溯  
  Runtime: 141 ms, faster than 16.15% of Java online submissions for Expression Add Operators.  
  Memory Usage: 61.7 MB, less than 18.92% of Java online submissions for Expression Add Operators.  
  class Solution {  
      public List<String> addOperators(String num, int target) {  
          List<String> rst = new ArrayList<String>();  
          if(num == null || num.length() == 0) return rst;  
          helper(rst, "", num, target, 0, 0, 0);  
          return rst;  
      }  
      public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed){  
          if(pos == num.length()){  
              if(target == eval)  
                  rst.add(path);  
              return;  
          }  
          for(int i = pos; i < num.length(); i++){  
              if(i != pos && num.charAt(pos) == '0') break;    
              long cur = Long.parseLong(num.substring(pos, i + 1));  
              if(pos == 0){  
                  helper(rst, path + cur, num, target, i + 1, cur, cur);  
              }  
              else{  
                  helper(rst, path + "+" + cur, num, target, i + 1, eval + cur , cur);  
    
                  helper(rst, path + "-" + cur, num, target, i + 1, eval -cur, -cur);  
    
                  helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur );  
              }  
          }  
      }  
  }  
    
  // 方法二： DFS + 回溯思路不变 主要是将字符串加减变成了char[]  
  Runtime: 9 ms, faster than 99.45% of Java online submissions for Expression Add Operators.  
  Memory Usage: 38.9 MB, less than 97.30% of Java online submissions for Expression Add Operators.  
    
  class Solution {  
      public List<String> addOperators(String num, int target) {  
          List<String> ret = new LinkedList<>();  
          if (num.length() == 0) return ret;  
          char[] path = new char[num.length() * 2 - 1];  
          char[] digits = num.toCharArray();  
          long n = 0;  
          for (int i = 0; i < digits.length; i++) {  
              n = n * 10 + digits[i] - '0';  
              path[i] = digits[i];  
              dfs(ret, path, i + 1, 0, n, digits, i + 1, target);  
              if (n == 0) break;  
          }  
          return ret;  
      }  
        
        //一共8个变量  
        //ret, target, digits 是全局变量 , path(遍历中间结果)  
        // pos 是指坐标, len 是当前dfs的长度  
        // cur: 类似于preVal  left  
     void dfs(List<String> ret, char[] path, int len, long left, long cur, char[] digits, int pos, int target) {  
      if (pos == digits.length) {  
          if (left + cur == target) ret.add(new String(path, 0, len));  //遇到最后数字的终止条件  
          return;  
      }  
      long n = 0;  
      int j = len + 1;  
      for (int i = pos; i < digits.length; i++) {  
          n = n * 10 + digits[i] - '0';  
          path[j++] = digits[i];  
          path[len] = '+';  
          dfs(ret, path, j, left + cur, n, digits, i + 1, target);  
          path[len] = '-';  
          dfs(ret, path, j, left + cur, -n, digits, i + 1, target);  
          path[len] = '*';  
          dfs(ret, path, j, left, cur * n, digits, i + 1, target);  
          if (digits[pos] == '0') break;   
          }  
      }  
  }  
```    
    
### 个人解读    
  又是计算器的逻辑，感觉自己有点苦手啊。。。  
  如果完全遍历的话，需要4^n-1次方  
    
    
tags:    
  -  计算器  
  -  DFS  
  -  回溯  
