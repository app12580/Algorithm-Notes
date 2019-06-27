### description    
  You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.  
    
  The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.  
    
  Example 1:  
  Input: Binary tree: [1,2,3,4]  
         1  
       /   \  
      2     3  
     /      
    4       
    
  Output: "1(2(4))(3)"  
    
  Explanation: Originallay it needs to be "1(2(4)())(3()())",   
  but you need to omit all the unnecessary empty parenthesis pairs.   
  And it will be "1(2(4))(3)".  
  Example 2:  
  Input: Binary tree: [1,2,3,null,4]  
         1  
       /   \  
      2     3  
       \    
        4   
    
  Output: "1(2()(4))(3)"  
    
  Explanation: Almost the same as the first example,   
  except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.  
    
  英文太长了，看不太懂，感觉直接看用例清晰一点。  
  对于root: val在最开头，然后如果不是叶子节点，则后面跟两个括号，括号里面再重复此操作。  
### solution    
```    
  class Solution {  
      private StringBuilder builder = new StringBuilder();  
    
      public String tree2str(TreeNode t) {  
          preOrder(t);  
          return builder.toString();  
      }  
    
      private void preOrder(TreeNode t) {  
          if(t == null) {  
              return;  
          }  
          int v = t.val;  
    
          builder.append(t.val);  
          if(t.left == null && t.right == null) {  
              return;  
          }  
          if(t.right == null) {  
              builder.append("(");  
              preOrder(t.left);  
              builder.append(")");  
              return;  
          }  
          builder.append("(");  
          preOrder(t.left);  
          builder.append(")");  
          builder.append("(");  
          preOrder(t.right);  
          builder.append(")");  
          return;  
      }  
    
  }  
```    
    
### 个人解读    
  改造前序遍历。  
    
tags:    
  -  树  
  -  前序遍历  
  -  递归  
