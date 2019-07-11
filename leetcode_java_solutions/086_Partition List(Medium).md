### description    
  Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.  
    
  You should preserve the original relative order of the nodes in each of the two partitions.  
    
  Example:  
    
  Input: head = 1->4->3->2->5->2, x = 3  
  Output: 1->2->2->4->3->5  
### solution    
```    
  Runtime: 0 ms, faster than 100.00% of Java online submissions for Partition List.  
  Memory Usage: 35.7 MB, less than 100.00% of Java online submissions for Partition List.  
    
    
  class Solution {  
      public ListNode partition(ListNode head, int x) {  
          ListNode before = new ListNode(-1);  
          ListNode after = new ListNode(-1);  
          ListNode cur1 = before;  
          ListNode cur2 = after;  
          while(head != null) {  
              if(head.val < x) {  
                  cur1.next = head;  
                  cur1 = head;  
              } else {  
                  cur2.next = head;  
                  cur2 = head;  
              }  
              // head = head.next;      这里刚开始没有断开链接，导致链表混乱  
              ListNode next = head.next;  
              head.next = null;  
              head = next;  
          }  
          cur1.next = after.next;  
          return before.next;  
      }  
  }  
    
```    
    
### 个人解读    
  链表的处理方式与数组不同，遇到问题需要多思考一下，如果是数组是什么情况。  
  使用双节点表示前半段和后半段。  
    
tags:    
  -  链表  
  -  链表法：重新构造  
