### description    
  Given a binary tree, return the vertical order traversal of its nodes values.  
    
  For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).  
    
  Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).  
    
  If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.  
    
  Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.  
    
     
    
  Example 1:  
    
    
    
  Input: [3,9,20,null,null,15,7]  
  Output: [[9],[3,15],[20],[7]]  
  Explanation:   
  Without loss of generality, we can assume the root node is at position (0, 0):  
  Then, the node with value 9 occurs at position (-1, -1);  
  The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);  
  The node with value 20 occurs at position (1, -1);  
  The node with value 7 occurs at position (2, -2).  
  Example 2:  
    
    
    
  Input: [1,2,3,4,5,6,7]  
  Output: [[4],[2],[1,5,6],[3],[7]]  
  Explanation:   
  The node with value 5 and the node with value 6 have the same position according to the given scheme.  
  However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.  
     
    
  Note:  
    
  The tree will have between 1 and 1000 nodes.  
  Each node's value will be between 0 and 1000.  
### solution    
```    
 Runtime: 4 ms, faster than 47.76% of Java online submissions for Vertical Order Traversal of a Binary Tree.  
 Memory Usage: 36.5 MB, less than 100.00% of Java online submissions for Vertical Order Traversal of a Binary Tree.  
   
  class Solution {  
   public List<List<Integer>> verticalTraversal(TreeNode root) {  
          TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> treeMap = new TreeMap<>();  
          List<List<Integer>> res = new ArrayList<>();  
          if (root == null) return res;  
          Queue<TreeNode> nodeQueue = new LinkedList<>();  
          Queue<Integer> xQueue = new LinkedList<>();  
          nodeQueue.offer(root);  
          xQueue.offer(0);  
          int y = 0;  
          while (!nodeQueue.isEmpty()) {  
              int size = nodeQueue.size();  
              y++;  
    
              for (int i = 0; i < size; i++) {  
                  TreeNode poll = nodeQueue.poll();  
                  int x = xQueue.poll();  
                  if (!treeMap.containsKey(x)) {  
                      treeMap.put(x, new TreeMap<>());  
                  }  
                  if (!treeMap.get(x).containsKey(y)) {  
                      treeMap.get(x).put(y, new PriorityQueue<>());  
                  }  
                  treeMap.get(x).get(y).add(poll.val);  
                  if(poll.left != null) {  
                      nodeQueue.offer(poll.left);  
                      xQueue.offer(x - 1);  
                  }  
                  if(poll.right != null) {  
                      nodeQueue.offer(poll.right);  
                      xQueue.offer(x + 1);  
                  }  
              }  
          }  
          for (TreeMap<Integer, PriorityQueue<Integer>> ys : treeMap.values()) {  
              res.add(new ArrayList<>());  
              for (PriorityQueue<Integer> nodes : ys.values()) {  
                  while (!nodes.isEmpty()) {  
                      res.get(res.size() - 1).add(nodes.poll());  
                  }  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  思路一：BFS，然后每次遍历一个节点的时候，同时记录一下当前的X坐标。  
  失败：对于相同XY的时候，需要去比较大小这一点没有做。  
  解决办法：在嵌套一层TreeMap  
    
  整体来说，主要考察数据结构的使用。整个题目特别啰嗦。  
    
tags:    
  -  数据结构  
