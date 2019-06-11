### description  
  Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
  
  For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
  
  Example:
  
  Given the sorted linked list: [-10,-3,0,5,9],
  
  One possible answer is: [0,-3,9,-10,null,5], which re  
sents the following height balanced BST:
  
        0
       / \
     -3   9
     /   /
   -10  5
  
  根据有序链表构造平衡的二叉查找树
### solution  
```  
  class Solution {
    public TreeNode sortedListToBST(ListNode head) {
          if(head == null) {
              return null;
          }
         if (head.next == null) return new TreeNode(head.val);
          ListNode   
 = helper(head);
          ListNode next =   
.next;
            
.next = null;
          TreeNode node = new TreeNode(next.val);
          node.left = sortedListToBST(head);
          node.right = sortedListToBST(next.next);
          return node;
      }
  
      public ListNode helper(ListNode head) {
          if(head == null) {
              return null;
          }
          if(head.next == null) {
              return head;
          }
          ListNode slow = head;
          ListNode fast = head.next;
          ListNode   
 = head;
          while(fast !=null && fast.next != null) {
                
 = slow;
              slow = slow.next;
              fast = fast.next.next;
          }
          return   
;
      }
  }
```  
  
### 个人解读  
  链表的问题在于，无法轻松的从任意顺序访问集结点。有两种方法：先遍历一遍，然后存储下来；使用fast和slow遍历，能获取到中间的节点。
  思路来了，先获取到中间节点，然后断开连接，执行递归。
  为了排除排除slow.next==null的影响，在获取slow的时候，返回前一个的  
节点。
  不要吝啬curNode.next == null 这种的极限值
  
tags:  
  -  树
  -  链表
  -  双指针
