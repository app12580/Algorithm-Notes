### description  
  Given a binary tree
  
  struct Node {
    int val;
    Node *left;
    Node *right;
    Node *next;
  }
  Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
  
  Initially, all next pointers are set to NULL.
  
   
  
  Example:
  
  
  
  Input: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":null,"next":null,"right":{"$id":"6","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
  
  Output: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":null,"right":null,"val":7},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"6","left":null,"next":null,"right":{"$ref":"5"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"6"},"val":1}
  
  Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.
   
  
  Note:
  
  You may only use constant extra space.
  Recursive approach is fine, implicit stack space does not count as extra space for this problem.
### solution  
```  
  // 方法一： 标准层次遍历
  
  Runtime: 2 ms, faster than 42.04% of Java online submissions for Populating Next Right Pointers in Each Node II.
  Memory Usage: 51.2 MB, less than 78.30% of Java online submissions for Populating Next Right Pointers in Each Node II.
  
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
  
  //方法二 优化后
  Runtime: 1 ms, faster than 76.96% of Java online submissions for Populating Next Right Pointers in Each Node II.
  Memory Usage: 51.1 MB, less than 82.82% of Java online submissions for Populating Next Right Pointers in Each Node II.
  
  
  class Solution {
     public Node connect(Node root) {  
           if(root == null) return null;
          Node res = root;
          Node cur = root;
          Node nextCur = null;
          while(cur != null) {
              //cur 为每一层的第一个上层节点
              Node t = null;
              nextCur = null;
              while(cur != null) {
                  if(cur.left != null) {
                      if(nextCur == null) {
                          nextCur = cur.left;
                          t = cur.left;
                      } else {
                          nextCur.next = cur.left;
                          nextCur = nextCur.next;
                      }
                  }
                  if(cur.right != null) {
                      if(nextCur == null) {
                          nextCur = cur.right;
                          t = cur.right;
                      } else {
                          nextCur.next = cur.right;
                          nextCur = nextCur.next;
                      }
                  }
                  cur = cur.next;
              }
              cur = t;
          }
          return res;
        }  
  }
```  
  
### 个人解读  
  与[116](116_Populating%20Next%20Right%20Pointers%20in%20Each%20Node(Medium).md)类似，主要是孩子节点不一定双齐全。
  可以用标准层次处理，但是效率低。
  复用中间结果思路。
  
  难点在于cur的处理，和while的终止条件。
  
tags:  
  -  
