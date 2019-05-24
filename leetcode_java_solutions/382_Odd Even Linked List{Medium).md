### description    
  Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.  
    
  You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.  
    
  Example 1:  
    
  Input: 1->2->3->4->5->NULL  
  Output: 1->3->5->2->4->NULL  
  Example 2:  
    
  Input: 2->1->3->5->6->4->7->NULL  
  Output: 2->3->6->7->1->5->4->NULL  
  Note:  
    
  The relative order inside both the even and odd groups should remain as it was in the input.  
  The first node is considered odd, the second node even and so on ...  
### solution    
```    
  class Solution {  
      public ListNode oddEvenList(ListNode head) {  
          if(head == null) {  
              return head;  
          }  
          ListNode odd = head;  
          ListNode even = head.next;  
          ListNode evenHead = even;  
          while (even != null && even.next != null) {  
              odd.next = odd.next.next;  
              odd = odd.next;  
              even.next = even.next.next;  
              even = even.next;  
          }  
          odd.next = evenHead;  
          return head;  
      }  
  }  
```    
    
### 个人解读    
类似于[22](022_Swap%20Nodes%20in%20Pairs(Medium).md)，都是遍历单链表，每次操作多个节点  
如果换成是数组的话，就会双指针轻松实现  
然而链表的话，还是需要双指针，每次都需要移动两次  
需要一个中间变量存储第一个even的位置  
    
tags:    
  -  链表  
  -  双指针  
  -  一次两步  
