### description    
  Given a linked list, rotate the list to the right by k places, where k is non-negative.  
    
  Example 1:  
    
  Input: 1->2->3->4->5->NULL, k = 2  
  Output: 4->5->1->2->3->NULL  
  Explanation:  
  rotate 1 steps to the right: 5->1->2->3->4->NULL  
  rotate 2 steps to the right: 4->5->1->2->3->NULL  
  Example 2:  
    
  Input: 0->1->2->NULL, k = 4  
  Output: 2->0->1->NULL  
  Explanation:  
  rotate 1 steps to the right: 2->0->1->NULL  
  rotate 2 steps to the right: 1->2->0->NULL  
  rotate 3 steps to the right: 0->1->2->NULL  
  rotate 4 steps to the right: 2->0->1->NULL  
    
### solution    
```    
Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate List.  
Memory Usage: 35.7 MB, less than 100.00% of Java online submissions for Rotate List.  
  
  class Solution {  
      public ListNode rotateRight(ListNode head, int k) {  
          //    Input: 1->2->3->4->5->6NULL, k = 2 需要找出4的位置  
          //    Output:5->6->1->2->3->4NULL  
          if(head == null || k == 0) return head;  
          ListNode fast = head;  
          int len = 0;  
          for(int i = 0; i < k; i++) {  
              len++;  
              if(fast.next == null) {  
                  fast = head;  
                  for(int j = 0; j < k % len; j++) {  
                      fast = fast.next;  
                  }  
                  break;  
              } else {  
                  fast = fast.next;  
              }  
          }  
           
          //fast相当于3  
          ListNode slow = head;  
          while(fast.next != null) {  
              fast = fast.next;  
              slow = slow.next;  
          }  
          //此时slow就是要找的4  
          if(slow.next == null) {  
              return head;  
          }  
          ListNode next = slow.next;  
          slow.next = null;  
          fast.next = head;  
          return next;  
    
      }  
  }  
```    
    
### 个人解读    
  需要掌握几个节点，然后进行拼接处理：断点前节点；末尾节点；首节点。  
    
  注意两个点：  
  1、如果k特别大，需要优化。  
  2、如果遍历时候，超过size了，需要处理。  
    
tags:    
  -  链表  
  -  双指针  
