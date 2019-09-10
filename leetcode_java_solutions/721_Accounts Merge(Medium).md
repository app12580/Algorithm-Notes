### description    
  Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.  
    
  Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.  
    
  After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.  
    
  Example 1:  
  Input:   
  accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]  
  Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]  
  Explanation:   
  The first and third John's are the same person as they have the common email "johnsmith@mail.com".  
  The second John and Mary are different people as none of their email addresses are used by other accounts.  
  We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],   
  ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.  
  Note:  
    
  The length of accounts will be in the range [1, 1000].  
  The length of accounts[i] will be in the range [1, 10].  
  The length of accounts[i][j] will be in the range [1, 30].  
### solution    
```    
  class Solution {  
      public List<List<String>> accountsMerge(List<List<String>> accounts) {  
          int[] parents = new int[accounts.size()];  
          for (int i = 0; i < accounts.size(); i++) {  
              parents[i] = i;  
          }  
          Map<String, Integer> owners = new HashMap<>();  //第一个email的拥有者  
          for (int i = 0; i < accounts.size(); i++) {     //最终所有parent都是最后一个出现的  
              for (int j = 1; j < accounts.get(i).size(); j++) {  
                  String email = accounts.get(i).get(j);  
                  if (owners.containsKey(email)) {  
                      int person = owners.get(email);  
                      int p1 = findParent(parents, i);  //p1是当前id  
                      int p2 = findParent(parents, person);  //p2是前面出现过的  
                      if (p1 != p2) {  
                          parents[p2] = p1;  
                      }  
                  } else {  
                      owners.put(email, i);  
                  }  
              }  
          }  
    
          Map<Integer, TreeSet<String>> users = new HashMap<>(); //key为parent  
          for (int i = 0; i < accounts.size(); i++) {  
              int parent = findParent(parents, i);  
              List<String> emails = accounts.get(i);  
              users.putIfAbsent(parent, new TreeSet<String>());  
              users.get(parent).addAll(emails.subList(1, emails.size()));  
          }  
    
          List<List<String>> res = new ArrayList<List<String>>(); //遍历结果， 就当默认名称不会出问题，所以从那个开始都一样  
          for (Integer idx : users.keySet()) {  
              String name = accounts.get(idx).get(0);  
              ArrayList<String> emails = new ArrayList<>(users.get(idx));  
              emails.add(0, name);  
              res.add(emails);  
          }  
          return res;  
      }  
    
        
      //这个方法并没有完全替换掉旧数据  
      private int findParent(int[] parents, int idx) {  
          while (idx != parents[idx]) {  
              parents[idx] = parents[parents[idx]];  
              idx = parents[idx];  
          }  
          return idx;  
      }  
  }  
```    
    
### 个人解读    
  打算先用Map来根据名称存储，先遍历一遍，全放进Map里面，然后再重新遍历map，返回结果。  
    
  输入里面input自身就带重复的。。。。  
    
  然后会遇到一个问题，如果AB不重复，然后就把AB扔进去了，之后C与AB都有重复，那么原来的AB也需要处理了。  
    
  未说明的题目描述：  
  Expected  
  Line 27: AssertionError: People must have same name across accounts  
    
  本题思路以email为标记，然后类似于并查集的操作去做合并。  
    
tags:    
  -  实际应用  
  -  并查集  
