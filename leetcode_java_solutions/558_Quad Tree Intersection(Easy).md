### description    
  A quadtree is a tree data in which each internal node has exactly four children: topLeft, topRight, bottomLeft and bottomRight. Quad trees are often used to partition a two-dimensional space by recursively subdividing it into four quadrants or regions.  
    
  We want to store True/False information in our quad tree. The quad tree is used to represent a N * N boolean grid. For each node, it will be subdivided into four children nodes until the values in the region it represents are all the same. Each node has another two boolean attributes : isLeaf and val. isLeaf is true if and only if the node is a leaf node. The val attribute for a leaf node contains the value of the region it represents.  
    
  For example, below are two quad trees A and B:  
    
  A:  
  +-------+-------+   T: true  
  |       |       |   F: false  
  |   T   |   T   |  
  |       |       |  
  +-------+-------+  
  |       |       |  
  |   F   |   F   |  
  |       |       |  
  +-------+-------+  
  topLeft: T  
  topRight: T  
  bottomLeft: F  
  bottomRight: F  
    
  B:                 
  +-------+---+---+  
  |       | F | F |  
  |   T   +---+---+  
  |       | T | T |  
  +-------+---+---+  
  |       |       |  
  |   T   |   F   |  
  |       |       |  
  +-------+-------+  
  topLeft: T  
  topRight:  
       topLeft: F  
       topRight: F  
       bottomLeft: T  
       bottomRight: T  
  bottomLeft: T  
  bottomRight: F  
     
    
  Your task is to implement a function that will take two quadtrees and return a quadtree that represents the logical OR (or union) of the two trees.  
    
  A:                 B:                 C (A or B):  
  +-------+-------+  +-------+---+---+  +-------+-------+  
  |       |       |  |       | F | F |  |       |       |  
  |   T   |   T   |  |   T   +---+---+  |   T   |   T   |  
  |       |       |  |       | T | T |  |       |       |  
  +-------+-------+  +-------+---+---+  +-------+-------+  
  |       |       |  |       |       |  |       |       |  
  |   F   |   F   |  |   T   |   F   |  |   T   |   F   |  
  |       |       |  |       |       |  |       |       |  
  +-------+-------+  +-------+-------+  +-------+-------+  
  Note:  
    
  Both A and B represent grids of size N * N.  
  N is guaranteed to be a power of 2.  
  If you want to know more about the quad tree, you can refer to its wiki.  
  The logic OR operation is defined as this: "A or B" is true if A is true, or if B is true, or if both A and B are true.  
    
  四叉树是一种树数据，其中每个结点恰好有四个子结点：topLeft、topRight、bottomLeft 和 bottomRight。四叉树通常被用来划分一个二维空间，递归地将其细分为四个象限或区域。  
    
  我们希望在四叉树中存储 True/False 信息。四叉树用来表示 N * N 的布尔网格。对于每个结点, 它将被等分成四个孩子结点直到这个区域内的值都是相同的。每个节点都有另外两个布尔属性：isLeaf 和 val。当这个节点是一个叶子结点时 isLeaf 为真。val 变量储存叶子结点所代表的区域的值。  
    
### solution    
```    
  
// 答案  
  class Solution {  
      public Node intersect(Node quadTree1, Node quadTree2) {  
            
          if (quadTree1.isLeaf)  
             return quadTree1.val? quadTree1 : quadTree2;  
             
          if (quadTree2.isLeaf)  
              return quadTree2.val? quadTree2 : quadTree1;  
           
          quadTree1.topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);  
          quadTree1.topRight = intersect(quadTree1.topRight, quadTree2.topRight);  
          quadTree1.bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);  
          quadTree1.bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);  
          
          
        // 下面这个if，题里面有告诉吗？？？  
          if (quadTree1.topLeft.isLeaf && quadTree1.topRight.isLeaf   
              && quadTree1.bottomLeft.isLeaf && quadTree1.bottomRight.isLeaf  
              && quadTree1.topLeft.val == quadTree1.topRight.val   
              && quadTree1.topLeft.val == quadTree1.bottomLeft.val   
              && quadTree1.topLeft.val == quadTree1.bottomRight.val){  
               
             quadTree1.val = quadTree1.topLeft.val;     //有疑问，如果quadTree1.val==true，岂不是给刷成了false？？？  
             quadTree1.isLeaf = true;  
             quadTree1.topLeft = null;  
             quadTree1.topRight = null;  
             quadTree1.bottomLeft = null;  
             quadTree1.bottomRight = null;  
          }  
            
          quadTree1.val = quadTree1.val || quadTree2.val;   
          quadTree1.isLeaf = quadTree1.isLeaf || quadTree2.isLeaf;  
          return quadTree1;  
    
      }  
  }  
    
    
    
  // 我自己摸石头写的答案  
  public Node intersect(Node quadTree1, Node quadTree2) {  
          if(quadTree1 == null && quadTree2 == null) {  
              return null;  
          }  
          if(quadTree1 == null) {  
              return quadTree2;  
          }  
          if(quadTree2 == null) {  
              return quadTree1;  
          }  
          if(quadTree1.isLeaf && quadTree2.isLeaf) {  
              Node node = new Node();  
              node.val = quadTree1.val || quadTree2.val;  
              node.isLeaf = true;  
              return node;  
          }  
          if(quadTree1.isLeaf) {  
              if(quadTree1.val) {  
                  return quadTree1;  
              } else {  
                  return quadTree2;  
              }  
          }  
          if(quadTree2.isLeaf) {  
              if(quadTree2.val) {  
                  return quadTree2;  
              } else {  
                  return quadTree1;  
              }  
          }  
    
          Node node = new Node();  
          node.topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);  
          node.topRight = intersect(quadTree1.topRight, quadTree2.topRight);  
          node.bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);  
          node.bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);  
          node.isLeaf = false;  
          node.val = quadTree1.val || quadTree2.val;  
          return node;  
      }  
```    
    
### 个人解读    
  需要好好理解结构： 有一个关键点题干没说清除，当一个Node的isLeaf为false时候，它的val是没有具体含义的吗？？？  
  根据测试用例，猜测val为所有子Node的val的或运算。  
    
  这傻逼题目啥都不说清楚，什么时候四个节点能变成一个节点都TM不说清楚  
  就这！！！  
  A:                 B:                 C (A or B):  
  +-------+-------+  +-------+---+---+  +-------+-------+  
  |       |       |  |       | F | F |  |       |       |  
  |   T   |   T   |  |   T   +---+---+  |   T   |   T   |  
  |       |       |  |       | T | T |  |       |       |  
  +-------+-------+  +-------+---+---+  +-------+-------+  
  |       |       |  |       |       |  |       |       |  
  |   F   |   F   |  |   T   |   F   |  |   T   |   F   |  
  |       |       |  |       |       |  |       |       |  
  +-------+-------+  +-------+-------+  +-------+-------+  
    
  凭什么C不是下面 这个东西啊？？  
   +-------+-------+  
   |               |  
   |               |  
   |               |  
   +      T        +  
   |               |  
   |               |  
   |               |  
   +-------+-------+  
   服了。。。  
   
   行吧，题目描述还要另外查百度，我的锅。  
   
   
    
tags:    
  -  题目描述  
  -  模拟  
  -  递归  
  -  傻逼题目，不看了  
