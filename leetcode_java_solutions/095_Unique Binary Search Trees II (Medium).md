### description    
  Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.  
    
  Example:  
    
  Input: 3  
  Output:  
  [  
    [1,null,3,2],  
    [3,2,null,1],  
    [3,1,null,null,2],  
    [2,1,3],  
    [1,null,2,null,3]  
  ]  
  Explanation:  
  The above output corresponds to the 5 unique BST's shown below:  
    
     1         3     3      2      1  
      \       /     /      / \      \  
       3     2     1      1   3      2  
      /     /       \                 \  
     2     1         2                 3  
       
  给定一个数字 n，要求生成所有值为 1...n 的二叉搜索树。  
       
### solution    
```    
  public List<TreeNode> generateTrees(int n) {  
          List<TreeNode> res = new ArrayList<>();  
          if(n <= 0) {  
              return res;  
          }  
          return dfs(1, n);  
      }  
    
      private List<TreeNode> dfs(int start, int end) {  
          ArrayList<TreeNode> res = new ArrayList<>();  
          if(start == end) {  
              res.add(new TreeNode(start));  
              return res;  
          }  
          if(start > end) {  
              return res;  
          }  
          for(int i = start; i <= end; i++) {  
              List<TreeNode> left = dfs(start, i - 1);  
              List<TreeNode> right = dfs(i + 1, end);  
    
              if(left.size() == 0 && right.size() != 0) {  
                  for(TreeNode r: right) {  
                      TreeNode cur = new TreeNode(i);  
                      cur.right = r;  
                      res.add(cur);  
                  }  
              } else if(left.size() != 0 && right.size() == 0) {  
                  for(TreeNode l: left) {  
                      TreeNode cur = new TreeNode(i);  
                      cur.left = l;  
                      res.add(cur);  
                  }  
              } else {  
                  for(TreeNode l: left) {  
                      for(TreeNode r: right) {  
                          TreeNode cur = new TreeNode(i);  
                          cur.right = r;  
                          cur.left = l;  
                          res.add(cur);  
                      }  
                  }  
              }  
    
          }  
          return res;  
      }  
        
        
      //add(null)优化后  
      class Solution {  
           public List<TreeNode> generateTrees(int n) {  
              List<TreeNode> res = new ArrayList<>();  
              if(n <= 0) {  
                  return res;  
              }  
              return dfs(1, n);  
          }  
        
          private List<TreeNode> dfs(int start, int end) {  
              ArrayList<TreeNode> res = new ArrayList<>();  
              if(start == end) {  
                  res.add(new TreeNode(start));  
                  return res;  
              }  
              if(start > end) {  
                  res.add(null);  
                  return res;  
              }  
              for(int i = start; i <= end; i++) {  
                  List<TreeNode> left = dfs(start, i - 1);  
                  List<TreeNode> right = dfs(i + 1, end);  
        
                  for(TreeNode l: left) {  
                      for(TreeNode r: right) {  
                          TreeNode cur = new TreeNode(i);  
                          cur.right = r;  
                          cur.left = l;  
                          res.add(cur);  
                      }  
                  }  
              }  
              return res;  
          }  
        
      }  
        
      // 优化  
      主要是add(null)的优化  
      public List<TreeNode> generateTrees(int n) {  
          if (n < 1) {  
              return new LinkedList<TreeNode>();  
          }  
          return generateSubtrees(1, n);  
      }  
        
      private List<TreeNode> generateSubtrees(int s, int e) {  
          List<TreeNode> res = new LinkedList<TreeNode>();  
          if (s > e) {  
              res.add(null);  
              return res;  
          }  
          for (int i = s; i <= e; ++i) {  
              List<TreeNode> leftSubtrees = generateSubtrees(s, i - 1);  
              List<TreeNode> rightSubtrees = generateSubtrees(i + 1, e);  
              for (TreeNode left : leftSubtrees) {  
                  for (TreeNode right : rightSubtrees) {  
                      TreeNode root = new TreeNode(i);  
                      root.left = left;  
                      root.right = right;  
                      res.add(root);  
                  }  
              }  
          }  
          return res;  
      }  
```    
    
### 个人解读    
  递归。需要中间函数，有start数字和end数字。  
  因为需要存储结果集，所以使用dfs。  
    
  需要注意两点：  
  dfs的返回值是List，这样每次dfs一遍后，返回的是一个集合，需要for循环装配  
  List的add(null) 操作，方便遍历  
    
tags:    
  -  DFS  
