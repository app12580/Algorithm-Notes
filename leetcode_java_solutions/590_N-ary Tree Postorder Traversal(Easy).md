### description    
  Given an n-ary tree, return the postorder traversal of its nodes' values.  
    
  For example, given a 3-ary tree:  
  
    
  Return its postorder traversal as: [5,6,3,2,4,1].  
    
     
  Note:  
    
  Recursive solution is trivial, could you do it iteratively?  
### solution    
```    
  class Solution {  
      private List<Integer> res = new ArrayList<>();  
      public List<Integer> postorder(Node root) {  
          preHelper(root);  
          return res;  
      }  
    
      private void preHelper(Node root) {  
          if(root == null) {  
              return;  
          }  
    
          for(Node child: root.children) {  
              preHelper(child);  
          }  
          res.add(root.val);  
      }  
  }  
```    
    
### 个人解读      
  多重树的前序遍历：中左右。    
  能够使用递归可以说很easy了。    
  为了省事，使用全局变量。    
      
  因为多次无效return list，为了优化内存，引入一个void的辅助函数。    
  和[589](589_N-ary%20Tree%20Preorder%20Traversal(Easy).md)基本一致  
      
tags:      
  -  树    
  -  递归  