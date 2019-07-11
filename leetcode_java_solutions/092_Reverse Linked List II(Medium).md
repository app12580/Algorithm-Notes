### description    
  Reverse a linked list from position m to n. Do it in one-pass.  
    
  Note: 1 ≤ m ≤ n ≤ length of list.  
    
  Example:  
    
  Input: 1->2->3->4->5->NULL, m = 2, n = 4  
  Output: 1->4->3->2->5->NULL  
    
### solution    
```    
  class Solution {  
      public ListNode reverseBetween(ListNode head, int m, int n) {  
          if(head == null) return null;  
          ListNode dummy = new ListNode(0);  
          dummy.next = head;  
          ListNode pre = dummy;  // 所有的都需要基于dummy，而不是head  
          for(int i = 0; i<m-1; i++) pre = pre.next;  //1  
    
          ListNode start = pre.next;  
          ListNode then = start.next;  
          // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3  
          // dummy-> 1 -> 2 -> 3 -> 4 -> 5  
          // 头插法： start不用动，pre也不用动  
          for(int i=0; i<n-m; i++)  
          {  
              //这个start是不用变化的，只是用来找到then的，它是反转的起始节点  
              start.next = then.next;  // 另先找到下一个then的位置  
              then.next = pre.next;  // 这行和下一行是插入then  
              pre.next = then;  
              then = start.next;      //转移  
          }  
    
          // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4  
          // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)  
          return dummy.next;  
    
      }  
  }  
```    
    
### 个人解读    
  链表的重点题目，对于数据结构的使用很有帮助。  
  mn不会超过范围很关键。  
    
  需要几个节点：首节点、反转前节点、反转后节点。  
  很多细节问题：如果m=1怎么办。  
    
  使用头插法。优点是原本结构不变。  
    
tags:    
  -  链表反转  
  -  头插法  
