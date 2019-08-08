### description    
  One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.  
    
       _9_  
      /   \  
     3     2  
    / \   / \  
   4   1  #  6  
  / \ / \   / \  
  # # # #   # #  
  For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.  
    
  Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.  
    
  Each comma separated value in the string must be either an integer or a character '#' representing null pointer.  
    
  You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".  
    
  Example 1:  
    
  Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"  
  Output: true  
  Example 2:  
    
  Input: "1,#"  
  Output: false  
  Example 3:  
    
  Input: "9,#,#,1"  
  Output: false  
### solution    
```    
  class Solution {  
       public boolean isValidSerialization(String preorder) {  
           if(preorder == null || preorder.length() == 0) {  
              return false;  
          }  
          Stack<String> stack = new Stack<>();  
          String[] split = preorder.split(",");  
          for(int i = 0; i < split.length; i++) {  
              String cur = split[i];  
              if("#".equals(cur)) {  
                  while(!stack.isEmpty() && stack.peek().equals("#")) {  
                      //保证是第二个"#"  
                      stack.pop();  
                      if(stack.isEmpty()) {  
                          //如果两个"#"的上面为空了  
                          return false;  
                      }  
                      stack.pop();  
                  }  
              }  
              stack.push(cur);  
          }  
          return stack.size() == 1 && stack.peek().equals("#");  
      }  
  }  
```    
    
### 个人解读    
  走流水账，需要控制好几个变量用来描述进度  
  start:当前周期的起始索引位置  
  size:当前周期有多少个不为null的点  
  need:当前周期需要遍历多少下。  
    
  没发现是前序遍历，前面说的都是层次遍历的思路。  
  然后思考了一番，看了看前序遍历与树的关系对照，感觉可以用栈来解决。  
    
  使用栈的核心思路是：每次遇到了两个"#"的时候，把这两个"#"的父节点给弹出来，并且替换成"#"，重复此操作，直到不是两个"#"的时候。  
    
  //方法二： 统计全局的出入度，并且忽略了前序遍历的特性。相当于直接一点一点的去加上子节点  
  ```  
  https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/discuss/78551/7-lines-Easy-Java-Solution  
      Some used stack. Some used the depth of a stack. Here I use a different perspective. In a binary tree, if we consider null as leaves, then  
        
      all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root  
      all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).  
      Suppose we try to build this tree. During building, we record the difference between out degree and in degree diff = outdegree - indegree. When the next node comes, we then decrease diff by 1, because the node provides an in degree. If the node is not null, we increase diff by 2, because it provides two out degrees. If a serialization is correct, diff should never be negative and diff will be zero when finished.  
        
      public boolean isValidSerialization(String preorder) {  
          String[] nodes = preorder.split(",");  
          int diff = 1;  
          for (String node: nodes) {  
              if (--diff < 0) return false;  
              if (!node.equals("#")) diff += 2;  
          }  
          return diff == 0;  
      }  
    
  ```  
    
tags:    
  -  流水账(X)  
  -  替换法  
  -  栈  
  -  重点数学  
