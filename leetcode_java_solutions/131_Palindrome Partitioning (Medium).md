### description    
  Given a string s, partition s such that every substring of the partition is a palindrome.  
    
  Return all possible palindrome partitioning of s.  
    
  Example:  
    
  Input: "aab"  
  Output:  
  [  
    ["aa","b"],  
    ["a","a","b"]  
  ]  
    
  分割字符串使得每个部分都是回文数  
### solution    
```    
Runtime: 2 ms, faster than 96.63% of Java online submissions for Palindrome Partitioning.
Memory Usage: 38.8 MB, less than 97.73% of Java online submissions for Palindrome Partitioning.

  class Solution {  
       public List<List<String>> partition(String s) {  
          List<List<String>> res = new ArrayList<>();  
          if(s == null || s.length() == 0) {  
              return res;  
          }  
          dfs(new ArrayList<String>(), res, s, 0);  
           return res;  
      }  
    
      private void dfs(ArrayList<String> curList, List<List<String>> res, String s, int index) {  
          if(index == s.length()) {  
              res.add(new ArrayList<>(curList));  
              return;  
          }  
            
          for(int i = index; i < s.length(); i++) {  
              if(isPalindrome(s, index, i)) {  
                  curList.add(s.substring(index, i + 1));  
                  dfs(curList, res, s, i + 1);  
                  curList.remove(curList.size() - 1);  
              }  
          }  
    
      }  
    
      //左闭右闭区间  
      private boolean isPalindrome(String s, int start, int end) {  
          while(start < end) {  
              if(s.charAt(start++) != s.charAt(end--)) {  
                  return false;  
              }  
          }  
          return true;  
      }  
    
  }  
```    
    
### 个人解读    
  首先判断是需要单指针。  
  然后从左往右遍历，然后遇到是回文的子字符串就添加进curList，然后继续dfs剩下的String。  
  注意：字符串尽量不要拼接和删减。  
    
tags:    
  -  DFS  
  -  回溯  
