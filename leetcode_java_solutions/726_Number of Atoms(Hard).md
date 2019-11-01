### description    
  Given a chemical formula (given as a string), return the count of each atom.  
    
  An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.  
    
  1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.  
    
  Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.  
    
  A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.  
    
  Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.  
### solution    
```    
方法一： 自己写的 stack+map  
Runtime: 5 ms, faster than 38.11% of Java online submissions for Number of Atoms.  
Memory Usage: 35.6 MB, less than 100.00% of Java online submissions for Number of Atoms.  
  
  class Solution {  
      public String countOfAtoms(String formula) {  
          Stack<Map<String, Integer>> stack = new Stack<>();  
          Map<String, Integer> curMap = new HashMap<>();  
          for (int i = 0; i < formula.length(); i++) {  
              char ch = formula.charAt(i);  
              if (ch == '(') {  
                  //入栈，开启新的计数  
                  Map<String, Integer> copy = new HashMap<>(curMap);  
                  stack.push(copy);  
                  curMap = new HashMap<>();  
              } else if (ch == ')') {  
                  //进行弹栈，同时合并两次的结果  
                  //首先获取括号外面数字是多少  
                  int count = 0;  
                  while(i < formula.length() - 1&& Character.isDigit(formula.charAt(i+1))) {  
                      i++;  
                      count = count * 10 + (formula.charAt(i) - '0');  
                  }  
    
                  //跳出循环后，i是最后一个数字的索引  
                  //cur = cur * count + stack.pop;  
                  Map<String, Integer> pop = stack.pop();  
                  if(count != 0) {  
                      for(Map.Entry<String, Integer> entry: curMap.entrySet()) {  
                          String key = entry.getKey();  
                          curMap.put(key, entry.getValue() * count);  
                      }  
                  }  
                  for(Map.Entry<String, Integer> entry: pop.entrySet()) {  
                      String key = entry.getKey();  
                      curMap.put(key, curMap.getOrDefault(key, 0) + entry.getValue());  
                  }  
    
              } else if(Character.isDigit(ch)) {  
                  //找出前面一个字符  
                  char pre = formula.charAt(i - 1);  
                  String preWord = String.valueOf(pre);  
                  if(Character.isLowerCase(pre)) {  
                      preWord = formula.charAt(i - 2) + preWord;  
                  }  
                  int count = ch - '0';  
                  while(i < formula.length() - 1 && Character.isDigit(formula.charAt(i+1))) {  
                      i++;  
                      count = count * 10 + (formula.charAt(i) - '0');  
                  }  
                  curMap.put(preWord, curMap.get(preWord) + count - 1);  
    
              } else {  
                  //字符的情况  
                  String curWord = String.valueOf(ch);  
                  while(i < formula.length() - 1 && Character.isLowerCase(formula.charAt(i+1))) {  
                      i++;  
                      curWord += formula.charAt(i);  
                  }  
                  curMap.put(curWord, curMap.getOrDefault(curWord, 0) + 1);  
              }  
          }  
          StringBuilder builder = new StringBuilder();  
          List<String> list = new ArrayList<String>(curMap.keySet());  
          Collections.sort(list);  
          for(String s: list) {  
              builder.append(s);  
              Integer num = curMap.get(s);  
              if(num != 1) builder.append(num);  
          }  
          return builder.toString();  
      }  
  }  
```    
    
### 个人解读    
  字符串解析  
  类似于计算器的那种，使用栈  
    
  两个错误：  
  1、错误的认为全部是大写字母，忽略了Mg这种  
  2、忽略了不经过括号，直接出来数字这种  
    
  用什么数据结构去存储呢？感觉HashMap了，但是消耗有点高  
    
  思路没有错，放心的写吧  
    
    
tags:    
  -  字符串解析(括号)  
  -  栈  
