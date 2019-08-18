### description    
  Given a nested list of integers represented as a string, implement a parser to deserialize it.  
    
  Each element is either an integer, or a list -- whose elements may also be integers or other lists.  
    
  Note: You may assume that the string is well-formed:  
    
  String is non-empty.  
  String does not contain white spaces.  
  String contains only digits 0-9, [, - ,, ].  
  Example 1:  
    
  Given s = "324",  
    
  You should return a NestedInteger object which contains a single integer 324.  
  Example 2:  
    
  Given s = "[123,[456,[789]]]",  
    
  Return a NestedInteger object containing a nested list with 2 elements:  
    
  1. An integer containing value 123.  
  2. A nested list containing two elements:  
      i.  An integer containing value 456.  
      ii. A nested list with one element:  
           a. An integer containing value 789.  
### solution    
```    
    
```    
    
### 个人解读    
  类似于编译原理的语义分析。  
    
  使用栈。  
  关键点：  
  1、只需要使用NestedInteger的两个方法：构造方法和add()  
  2、通过一个stack来表示各个对象之间的包含关系  
    
  逻辑详情：  
  1、遇到【，创建新的对象并把原来的放进stack里面。//注意一开始是null需要单独判断  
  2、遇到】，分为两种情况，一组数字中的最后一个，或者是连续的】】情况  
    如果是前者，多了一步把数字放进curr里面的操作。  
    完事后，都需要弹栈，做收尾  
  3、  
  4、其他情况就是数字了，直接跳过  
    
tags:    
  -  栈  
  -  字符串解析  
  -  重点题目  
