### description    
  You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:  
    
  struct Node {  
    int val;  
    Node *left;  
    Node *right;  
    Node *next;  
  }  
  Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.  
    
  Initially, all next pointers are set to NULL.  
    
     
    
  Example:  
    
    
    
  Input: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}  
    
  Output: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}  
    
  Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.  
     
    
  Note:  
    
  You may only use constant extra space.  
  Recursive approach is fine, implicit stack space does not count as extra space for this problem.  
### solution    
```    
  
Runtime: 1 ms, faster than 31.31% of Java online submissions for Populating Next Right Pointers in Each Node.  
Memory Usage: 36.3 MB, less than 29.73% of Java online submissions for Populating Next Right Pointers in Each Node.  
  
  
  /*  
  // Definition for a Node.  
  class Node {  
      public int val;  
      public Node left;  
      public Node right;  
      public Node next;  
    
      public Node() {}  
    
      public Node(int _val,Node _left,Node _right,Node _next) {  
          val = _val;  
          left = _left;  
          right = _right;  
          next = _next;  
      }  
  };  
  */  
  class Solution {  
      public Node connect(Node root) {  
          if(root == null) return null;  
          Queue<Node> queue = new LinkedList<>();  
          Node res = root;  
          Node cur = null;  
          queue.offer(root);  
          while(!queue.isEmpty()) {  
              int size = queue.size();  
              for(int i = 0; i < size; i++) {  
                  if(cur != null) {  
                      cur.next = queue.peek();  
                  }  
                  cur = queue.poll();  
                  if(cur.left != null) {  
                      queue.offer(cur.left);  
                  }  
                  if(cur.right != null) {  
                      queue.offer(cur.right);  
                  }  
              }  
              cur = null;  
          }  
          return res;  
      }  
  }  
    
  //方法二 优化；  
  class Solution {  
      public Node connect(Node root) {  
          if(root == null) return null;  
          Node res = root;  
          Node cur = root;  
          while(cur != null && cur.left != null) {  
              Node t = cur;  
              while(cur != null) {  
                  cur.left.next = cur.right;  
                  if(cur.next != null) {  
                      cur.right.next = cur.next.left;  
                  }  
                  cur = cur.next;  
              }  
              cur = t.left;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  层次遍历的变种应用。  
    
  想办法优化。  
  核心思想，在于复用已经处理好的结果。所以每次都是在当前行遍历，去处理下一行的内容。  
    
tags:    
  -  树  
  -  层次遍历  
  -  中间处理结果复用  
