### description    
  Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.  
    
  According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”  
    
  Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]  
    
    
     
    
  Example 1:  
    
  Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1  
  Output: 3  
  Explanation: The LCA of nodes 5 and 1 is 3.  
  Example 2:  
    
  Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4  
  Output: 5  
  Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.  
     
    
  Note:  
    
  All of the nodes' values will be unique.  
  p and q are different and both values will exist in the binary tree.  
    
  二叉树的最近公共祖先  
### solution    
```    
  // 返回祖先节点或者root节点  
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {  
      if (root == null || root == p || root == q) return root;      // 极值条件一： 如果root为null，或者说p和q有一个直接跑到root了，就直接返回root  
      TreeNode left = lowestCommonAncestor(root.left, p, q);  
      TreeNode right = lowestCommonAncestor(root.right, p, q);  
        
      if(left == null) return right;  
      if(right == null) return left;  
      return root;  
      //return left == null ? right : right == null ? left : root;   //极值条件二： 如果某一边为null，则返回另外一个； 如果两个都不为null，则返回root  
      //因为极值条件二是基于left和right来的，并不是准确意义上的极值，所以还是要看极值一  
      //然后就绪要仔细研究极值一： 向下递归，遇到p或者q就return，如果没遇到，则返回null。  
      //所以极值二的条件：当left==null，说明了p和q都在右边，return right即可。  
      //如果都不为null，说明一边一个  
  }  
    
    
```    
    
### 个人解读    
  难点在于，如何根据root，去判断是否是公共祖先。  
  详情见solution里面的代码分析  
  本解法特殊之处在于，给主函数增加了一个额外的含义： 如果找不到公共祖先，则返回root。  
  给主函数增加了这么一个额外含义后，，就变得轻松多了。  
    
tags:    
  -  修改递归主函数含义  
  -  树   
    
