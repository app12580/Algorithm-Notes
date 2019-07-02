### description    
  Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.  
    
    
    
  For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).  
    
  Two binary trees are considered leaf-similar if their leaf value sequence is the same.  
    
  Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.  
    
     
    
  Note:  
    
  Both of the given trees will have between 1 and 100 nodes.  
    
  比较两个树所有的叶子节点从左到右是否值一致。  
### solution    
```    
  class Solution {  
      public boolean leafSimilar(TreeNode root1, TreeNode root2) {  
          List<Integer> list1 = helper(root1);  
          List<Integer> list2 = helper(root2);  
          if(list1.size() != list2.size()) {  
              return false;  
          }  
          for(int i = 0; i < list1.size(); i++) {  
              if(!list1.get(i).equals(list2.get(i))) {  
                  return false;  
              }  
          }  
          return true;  
      }  
    
      private List<Integer> helper(TreeNode root) {  
          List<Integer> res=  new ArrayList<>();  
          if(root == null) {  
              return res;  
          }  
          Stack<TreeNode> stack = new Stack();  
          stack.push(root);  
          while(!stack.isEmpty()) {  
              TreeNode pop = stack.pop();  
              if(pop.left == null && pop.right == null) {  
                  res.add(pop.val);  
                  continue;  
              }   
              if(pop.left != null) {  
                  stack.push(pop.left);  
              }  
              if(pop.right != null) {  
                  stack.push(pop.right);  
              }  
          }  
          return res;  
    
      }  
  }  
```    
    
### 个人解读    
  因为树的中值不影响结果，所以什么顺序遍历都可以。  
    
  各用一个List存储结果。  
    
  参考了一下[144](144_Binary%20Tree%20Preorder%20Traversal%20(Medium).md)非递归遍历树的用法。  
    
tags:    
  -  非递归  
  -  树  
