### description    
  Sort a linked list in O(n log n) time using constant space complexity.  
    
  Example 1:  
    
  Input: 4->2->1->3  
  Output: 1->2->3->4  
  Example 2:  
    
  Input: -1->5->3->4->0  
  Output: -1->0->3->4->5  
### solution    
```    
  class Solution {  
      public static ListNode sortList(ListNode head) {  
          return head == null ? head : mergeSort(head);  
      }  
    
      private static ListNode mergeSort(ListNode head) {  
          if (head.next == null) {  
              return head;  
          }  
          //慢结点  
          ListNode s = head;  
          //快结点  
          ListNode f = head;  
          //sign为慢节点的前面一个节点  
          ListNode sign = null;  
          while (f != null && f.next != null) {  
              sign = s;  
              s = s.next;  
              f = f.next.next;  
          }  
          //标记结点下一个结点为空  
          sign.next = null;  
          ListNode left = mergeSort(head);  
          ListNode right = mergeSort(s);  
          return merge(left, right);  
      }  
    
      //合并两个链表  
      public static ListNode merge(ListNode l, ListNode r) {  
          ListNode dummyHead = new ListNode(0);  
          ListNode cur = dummyHead;  
          while (l != null && r != null) {  
              if (l.val <= r.val) {  
                  cur.next = l;  
                  cur = cur.next;  
                  l = l.next;  
              } else {  
                  cur.next = r;  
                  cur = cur.next;  
                  r = r.next;  
              }  
          }  
          if (l != null) {  
              cur.next = l;  
          }  
          if (r != null) {  
              cur.next = r;  
          }  
          return dummyHead.next;  
      }  
    
  }  
```    
    
### 个人解读    
  链表的基础操作，排序：联想到数组的各种排序操作。归并是最好移植的。  
    
tags:    
  -  链表  
  -  模板代码  
  -  归并排序  
