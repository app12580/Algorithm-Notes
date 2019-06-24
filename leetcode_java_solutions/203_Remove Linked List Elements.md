### description    
  Remove all elements from a linked list of integers that have value val.  
    
  Example:  
    
  Input:  1->2->6->3->4->5->6, val = 6  
  Output: 1->2->3->4->5  
### solution    
```    
  class Solution {  
      public ListNode removeElements(ListNode head, int val) {  
           ListNode dual = new ListNode(-1);  
          dual.next = head;  
          ListNode pre  = dual;  
          while(head != null) {  
              if(head.val == val) {  
                  ListNode next = head.next;  
                  pre.next = next;  
                  head = next;  
              } else {  
                  pre = head;  
                  head = head.next;  
              }  
          }  
          return dual.next;  
      }  
  }  
```    
    
### 个人解读    
  链表  
  两个小技巧：使用dual节点；while里面 的条件。  
    
tags:    
  -  链表  
