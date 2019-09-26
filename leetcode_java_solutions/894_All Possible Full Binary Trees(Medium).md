### description    
  A full binary tree is a binary tree where each node has exactly 0 or 2 children.  
    
  Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.  
    
  Each node of each tree in the answer must have node.val = 0.  
    
  You may return the final list of trees in any order.  
    
     
    
  Example 1:  
    
  Input: 7  
  Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]  
  Explanation:  
    
     
    
  Note:  
    
  1 <= N <= 20  
### solution    
```    
Runtime: 6 ms, faster than 23.66% of Java online submissions for All Possible Full Binary Trees.  
Memory Usage: 46.5 MB, less than 69.23% of Java online submissions for All Possible Full Binary Trees.  
  
  class Solution {  
       
   public List<TreeNode> allPossibleFBT(int N) {  
          List<TreeNode> res = new ArrayList<>();  
          if(N == 1) {  
              res.add(new TreeNode(0));  
              return res;  
          }  
          int rest = N - 1;  
          for(int i = 1; i < rest; i += 2) {  
              List<TreeNode> left = allPossibleFBT(i);  
              List<TreeNode> right = allPossibleFBT(rest - i);  
              for(TreeNode l : left) {  
                  for(TreeNode r: right) {  
                      TreeNode n = new TreeNode(0);  
                      n.left = l;  
                      n.right = r;  
                      res.add(n);  
                  }  
              }  
          }  
    
          return res;  
      }  
  }  
```    
    
### 个人解读    
  主要难点，树的遍历回溯以后还需要克隆操作么。。。  
  两个思路：  
  1、通过数学特征，控制好遍历的方向  
  2、在一个树上面，遍历+回溯+节点克隆。  
    
  本题的隐藏规律：  
  1、N必须是奇数  
  2、关键点在于非叶子节点的排布  
    
  总结：  
  本题目的核心点在于，不用让各个节点独立，可以让那些节点全部都是共享的。这种思维很关键。。。  
    
tags:    
  -  树  
  -  交织树  
