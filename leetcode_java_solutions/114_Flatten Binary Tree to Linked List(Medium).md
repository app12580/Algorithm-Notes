### description    
  Given a binary tree, flatten it to a linked list in-place.  
    
  For example, given the following tree:  
    
      1  
     / \  
    2   5  
   / \   \  
  3   4   6  
  The flattened tree should look like:  
    
  1  
   \  
    2  
     \  
      3  
       \  
        4  
         \  
          5  
           \  
            6  
### solution    
```    
  class Solution {  
     private TreeNode dummy;  
      public void flatten(TreeNode root) {  
          if(root == null) return;  
          dummy = root;  
          TreeNode allRight = root.right;  
          TreeNode allLeft = root.left;  
          root.left = null;  
          root.right = null;  
          helper(allLeft);  
          helper(allRight);  
      }  
    
      private void helper(TreeNode node) {  
          //右左中  
          //为了断开节点，中必须在最后。  
          if(node == null) {  
              return;  
          }  
          TreeNode left = node.left;  
          TreeNode right = node.right;  
    
          node.left = null;  
          node.right = null;  
          dummy.right = node;  
          dummy = node;  
    
          helper(left);  
          helper(right);  
      }  
  }  
```    
    
### 个人解读    
  没说什么顺序，盲猜一个前序遍历。  
  看了一下，发现主要是把树的左子树全部塞进根节点的右子树那里。  
  先把左右两个子树全部拿出来，然后一点一点塞进去。  
    
  别想着保留了，上来直接把根节点全砍掉。    
    
tags:    
  -  树  
  -  前序遍历  
