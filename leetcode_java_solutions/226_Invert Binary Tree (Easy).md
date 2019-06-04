### description    
  Invert a binary tree.  
    
  Example:  
    
  Input:  
    
       4  
     /   \  
    2     7  
   / \   / \  
  1   3 6   9  
  Output:  
    
       4  
     /   \  
    7     2  
   / \   / \  
  9   6 3   1  
  Trivia:  
  This problem was inspired by this original tweet by Max Howell:  
    
  Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.  
    
  翻转二叉树  
### solution    
```    
  public TreeNode invertTree(TreeNode root) {  
      if (root == null) return null;  
      TreeNode left = root.left;  // 后面的操作会改变 left 指针，因此先保存下来  
      root.left = invertTree(root.right);  
      root.right = invertTree(left);  
      return root;  
  }  
```    
    
### 个人解读    
  首先需要判断是否需要中间函数：root.left = reverse(root.right);所以不需要中间函数。  
  // 第一次写的时候，发生错误，同时写了left=f(right)和right=f(left)，没有设置临时变量保存下来。  
    
tags:    
  -  树  
  -  递归  
