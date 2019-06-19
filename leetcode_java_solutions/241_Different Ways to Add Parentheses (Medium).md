### description    
  Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.  
    
  Example 1:  
    
  Input: "2-1-1"  
  Output: [0, 2]  
  Explanation:   
  ((2-1)-1) = 0   
  (2-(1-1)) = 2  
  Example 2:  
    
  Input: "2*3-4*5"  
  Output: [-34, -14, -10, -10, 10]  
  Explanation:   
  (2*(3-(4*5))) = -34   
  ((2*3)-(4*5)) = -14   
  ((2*(3-4))*5) = -10   
  (2*((3-4)*5)) = -10   
  (((2*3)-4)*5) = 10  
    
  给表达式加括号，重复值得也要算作两次。  
### solution    
```    
  class Solution {  
       public List<Integer> diffWaysToCompute(String input) {  
          List<Integer> res = new ArrayList<>();  
          if(input == null || input.length() == 0) {  
              return res;  
          }  
          char[] chars = input.toCharArray();  
          for(int i = 0; i < chars.length; i++) {  
              char ch = chars[i];  
              if(ch != '-' && ch != '+' && ch != '*') {  
                  continue;  
              } else {  
                  List<Integer> left = diffWaysToCompute(input.substring(0, i));  
                  List<Integer> right = diffWaysToCompute(input.substring(i + 1));  
                  for(int l: left) {  
                      for(int r: right) {  
                          if(ch == '+') {  
                              res.add(l + r);  
                          } else if(ch == '-') {  
                              res.add(l-r);  
                          } else {  
                              res.add(l*r);  
                          }  
                      }  
                  }  
              }  
                
          }  
          if(res.isEmpty()) {  
              res.add(Integer.valueOf(input));  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  初见时有种并查集的既视感，然而发现字符串用并查集并不好处理。  
  然后使用递归，不需要张总监函数。  
    
tags:    
  -  递归  
