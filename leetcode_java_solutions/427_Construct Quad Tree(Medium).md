### description    
  We want to use quad trees to store an N x N boolean grid. Each cell in the grid can only be true or false. The root node represents the whole grid. For each node, it will be subdivided into four children nodes until the values in the region it represents are all the same.  
    
  Each node has another two boolean attributes : isLeaf and val. isLeaf is true if and only if the node is a leaf node. The val attribute for a leaf node contains the value of the region it represents.  
    
  Your task is to use a quad tree to represent a given grid. The following example may help you understand the problem better:  
    
  Given the 8 x 8 grid below, we want to construct the corresponding quad tree:  
    
    
    
  It can be divided according to the definition above:  
    
    
    
     
    
  The corresponding quad tree should be as following, where each node is represented as a (isLeaf, val) pair.  
    
  For the non-leaf nodes, val can be arbitrary, so it is represented as *.  
    
    
    
  Note:  
    
  N is less than 1000 and guaranteened to be a power of 2.  
  If you want to know more about the quad tree, you can refer to its wiki.   
    
### solution    
```    
  /*  
  // Definition for a QuadTree node.  
  class Node {  
      public boolean val;  
      public boolean isLeaf;  
      public Node topLeft;  
      public Node topRight;  
      public Node bottomLeft;  
      public Node bottomRight;  
    
      public Node() {}  
    
      public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {  
          val = _val;  
          isLeaf = _isLeaf;  
          topLeft = _topLeft;  
          topRight = _topRight;  
          bottomLeft = _bottomLeft;  
          bottomRight = _bottomRight;  
      }  
  };  
  */  
  class Solution {  
       public Node construct(int[][] grid) {  
          Node root = helper(grid, 0, 0, grid.length - 1, grid.length - 1);  
          return root;  
      }  
    
        
      private Node helper(int[][] grid, int x1, int y1, int x2, int y2) {  
          if(x1 == x2 && y1 == y2) {  
              Node node = new Node();  
              node.val = grid[x1][y1] == 1;  
              node.isLeaf = true;  
              return node;  
          }  
          int len = x2 - x1 + 1;  
          int diff = len / 2;  
          Node topLeft = helper(grid, x1, y1, x1 + diff - 1, y1 + diff - 1);  
          Node topRight = helper(grid, x1, y1 + diff, x1 + diff - 1, y2);  
          Node bottomLeft = helper(grid, x1 + diff, y1, x2, y1 + diff - 1);  
          Node bottomRight = helper(grid, x1 + diff, y1 + diff, x2, y2);  
          if(topLeft.isLeaf && bottomLeft.isLeaf && topRight.isLeaf && bottomRight.isLeaf) {  
              if(topLeft.val && topRight.val && bottomLeft.val && bottomRight.val) {  
                  Node node = new Node();  
                  node.val = true;  
                  node.isLeaf = true;  
                  return node;  
              } else if(!topLeft.val && !topRight.val && !bottomLeft.val && !bottomRight.val) {  
                  Node node = new Node();  
                  node.val = false;  
                  node.isLeaf = true;  
                  return node;  
              }  
          }  
          Node node = new Node();  
          node.bottomLeft = bottomLeft;  
          node.bottomRight = bottomRight;  
          node.topLeft = topLeft;  
          node.topRight = topRight;  
          return node;  
      }  
  }  
```    
    
### 个人解读    
  首先这个题目可以用到递归，然后出于效率的原因，不创建新数组，而是通过坐标位置来作为递归元素。  
    
  还可以嗷~  
    
tags:    
  -  模拟  
