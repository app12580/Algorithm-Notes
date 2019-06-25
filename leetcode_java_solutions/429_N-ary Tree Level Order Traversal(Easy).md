### description    
  Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).  
    
  For example, given a 3-ary tree:  
    
  We should return its level order traversal:  
    
  [  
       [1],  
       [3,2,4],  
       [5,6]  
  ]  
     
    
  Note:  
    
  The depth of the tree is at most 1000.  
  The total number of nodes is at most 5000.  
### solution    
```    
  class Solution {  
      public List<List<Integer>> levelOrder(Node root) {  
       List<List<Integer>> res = new ArrayList<>();  
          if(root == null) {  
              return res;  
          }  
          Queue<Node> queue = new LinkedList<>();  
          queue.offer(root);  
          while(!queue.isEmpty()) {  
              int size = queue.size();  
              List<Integer> cur = new ArrayList<>();  
              for(int i = 0; i < size; i++) {  
                  Node poll = queue.poll();  
                  cur.add(poll.val);  
                  if(poll.children != null && poll.children.size() != 0) {  
                      for(Node child: poll.children) {  
                          queue.offer(child);  
                      }  
                  }  
              }  
              res.add(cur);  
          }  
          return res;     
      }  
  }  
```    
    
### 个人解读    
  层次遍历，套模板即可  
    
tags:    
  -  层次遍历  
  -  BFS  
