### description    
  Given a singly linked list L: L0→L1→…→Ln-1→Ln,  
  reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…  
    
  You may not modify the values in the list's nodes, only nodes itself may be changed.  
    
  Example 1:  
    
  Given 1->2->3->4, reorder it to 1->4->2->3.  
  Example 2:  
    
  Given 1->2->3->4->5, reorder it to 1->5->2->4->3.  
### solution    
```    
  class Solution {  
       public void reorderList(ListNode head) {  
          if(head == null) return;  
          ListNode f = head;  
          ListNode s = head;  
          ListNode sign = null;  
          while(f != null && f.next != null) {  
              sign = s;  
              s = s.next;  
              f = f.next.next;  
          }  
          if(f != null) {       // 这几行是因为奇数的时候需要再处理一次。  
              sign = s;  
              s = s.next;  
          }  
          sign.next = null;  
          ListNode last = reverse(s);  
          while(head != null || last != null) {  
              ListNode next1 = head.next;       //每一轮处理两个。  
              head.next = last;  
              head = next1;  
              if(last != null) {  
                  ListNode next2 = last.next;  
                  last.next = head;  
                  last = next2;  
              }  
          }  
      }  
    
      private ListNode reverse(ListNode node) {  
          if(node == null) {  
              return null;  
          }  
          ListNode pre = null;  
          while(node != null) {  
              ListNode next = node.next;  
              node.next = pre;  
              pre = node;  
              node = next;  
          }  
    
          return pre;  
      }  
  }  
```    
    
### 个人解读    
  流水账：  
  获取中间节点->分段->后半段反转->merge链表  
  需要注意奇偶的时候情形。  
    
tags:    
  -  链表  
  -  反转  
  -  双指针  
