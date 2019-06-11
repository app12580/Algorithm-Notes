### description    
  Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.  
    
  According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”  
    
  Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]  
    
    
     
    
  Example 1:  
    
  Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8  
  Output: 6  
  Explanation: The LCA of nodes 2 and 8 is 6.  
  Example 2:  
    
  Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4  
  Output: 2  
  Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.  
     
    
  Note:  
    
  All of the nodes' values will be unique.  
  p and q are different and both values will exist in the BST.  
    
  二叉查找树的最近公共祖先  
### solution    
```    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {  
           if(root == null) {  
              return null;  
          }  
          if(root.val > p.val && root.val > q.val) {  
              return lowestCommonAncestor(root.left, p, q);  
          }  
          if(root.val < p.val && root.val < q.val) {  
              return lowestCommonAncestor(root.right, p, q);  
          }  
          return root;  
      }  
```    
    
### 个人解读    
  主要疑虑在于两边的遍历不同步，总不能用一个map存放所有遍历数据吧。  
  然而是二叉树的话，难道可以控制方向？  
  思路有误：不应该从p，q出发，从root出发就只有一个方向，没有第一时间这么想在于不知道root应该满足什么条件。  
  需要比较root.val和p.val，q.val，只要在这两者之间，就是祖先了。  
    
tags:    
  -  BST祖先  
  -  树  
  -  递归  
