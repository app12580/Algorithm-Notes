### description    
  Given a string containing only digits, restore it by returning all possible valid IP address combinations.  
    
  Example:  
    
  Input: "25525511135"  
  Output: ["255.255.11.135", "255.255.111.35"]  
    
  划分可行的IP拼接。  
### solution    
```    
  
Runtime: 3 ms, faster than 25.17% of Java online submissions for Restore IP Addresses.  
Memory Usage: 36.7 MB, less than 73.25% of Java online submissions for Restore IP Addresses.  
  
  
  class Solution {  
       public List<String> restoreIpAddresses(String s) {  
          List<String> res = new ArrayList();  
          if(s == null || s.length() == 0) {  
              return res;  
          }  
    
          dfs("", res, 0, s);  
           return res;  
      }  
    
      private void dfs(String prefix, List<String> res, int count, String rest) {  
          if(count == 4 || rest.length() == 0) {  
              if(count == 4 && rest.length() == 0) {  
                  res.add(prefix);  
              }  
              return;  
          }  
    
          for(int i = 0; i < 3 && i < rest.length(); i++) {  
                
              String substring = rest.substring(0, i + 1);  
              Integer cur = Integer.valueOf(substring);  
              if(!substring.equals(cur+"")) return;  
              if(cur >= 0 && cur <= 255) {  
                  String t = prefix + cur;  
                  if(count != 3) {  
                      t += ".";  
                  }  
                  dfs(t, res, count+1, rest.substring(i + 1));  
              }  
          }  
      }  
    
  }  
    
    
  // 方法二 优化  
  // 使用StringBuilder替换String  
  // 边界值0得处理(如果i!=0 && charAt(0)=='0')  
  // += "part." 优化成 += ".part" 因为开始要比末尾好判断if条件怎么写  
    
  public List<String> restoreIpAddresses(String s) {  
      List<String> addresses = new ArrayList<>();  
      StringBuilder tempAddress = new StringBuilder();  
      doRestore(0, tempAddress, addresses, s);  
      return addresses;  
  }  
    
  private void doRestore(int k, StringBuilder tempAddress, List<String> addresses, String s) {  
      if (k == 4 || s.length() == 0) {  
          if (k == 4 && s.length() == 0) {  
              addresses.add(tempAddress.toString());  
          }  
          return;  
      }  
      for (int i = 0; i < s.length() && i <= 2; i++) {  
          if (i != 0 && s.charAt(0) == '0') {  
              break;  
          }  
          String part = s.substring(0, i + 1);  
          if (Integer.valueOf(part) <= 255) {  
              if (tempAddress.length() != 0) {  
                  part = "." + part;  
              }  
              tempAddress.append(part);  
              doRestore(k + 1, tempAddress, addresses, s.substring(i + 1));  
              tempAddress.delete(tempAddress.length() - part.length(), tempAddress.length());  
          }  
      }  
  }  
```    
    
### 个人解读    
  回溯问题，找到所有可行的排列。  
    
    
    
tags:    
  -  回溯  
  -  DFS  
