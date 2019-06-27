### description    
  Given an n-ary tree, return the preorder traversal of its nodes' values.  
    
  For example, given a 3-ary tree:  
    
  Return its preorder traversal as: [1,3,5,6,2,4].  
    
  Note:  
    
  Recursive solution is trivial, could you do it iteratively?  
    
    
### solution    
```    
  Runtime: 1 ms, faster than 100.00% of Java online submissions for N-ary Tree Preorder Traversal.  
  Memory Usage: 49.7 MB, less than 13.71% of Java online submissions for N-ary Tree Preorder Traversal.  
  class Solution {  
       private List<Integer> res = new ArrayList<>();  
      public List<Integer> preorder(Node root) {  
          if(root == null) {  
              return res;  
          }  
          res.add(root.val);  
          for(Node child: root.children) {  
              preorder(child);  
          }  
          return res;  
      }  
  }  
    
  //优化内存  
  Runtime: 1 ms, faster than 100.00% of Java online submissions for N-ary Tree Preorder Traversal.  
  Memory Usage: 47.4 MB, less than 36.52% of Java online submissions for N-ary Tree Preorder Traversal.  
    
    
  class Solution {  
      private List<Integer> res = new ArrayList<>();  
      public List<Integer> preorder(Node root) {  
          preHelper(root);  
          return res;  
      }  
        
      private void preHelper(Node root) {  
          if(root == null) {  
              return;  
          }  
          res.add(root.val);  
          for(Node child: root.children) {  
              preHelper(child);  
          }  
      }  
  }  
```    
    
### 个人解读    
  多重树的前序遍历：中左右。  
  能够使用递归可以说很easy了。  
  为了省事，使用全局变量。  
    
  因为多次无效return list，为了优化内存，引入一个void的辅助函数。  
    
tags:    
  -  树  
  -  递归  
