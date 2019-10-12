### description      
  Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.    
      
  Example:    
      
  Input:    
  [    
    1->4->5,    
    1->3->4,    
    2->6    
  ]    
  Output: 1->1->2->3->4->4->5->6    
### solution      
```      
  Runtime: 2 ms, faster than 99.21% of Java online submissions for Merge k Sorted Lists.    
  Memory Usage: 41.9 MB, less than 32.79% of Java online submissions for Merge k Sorted Lists.    
      
  /**    
   * Definition for singly-linked list.    
   * public class ListNode {    
   *     int val;    
   *     ListNode next;    
   *     ListNode(int x) { val = x; }    
   * }    
   */    
  class Solution {    
      public ListNode mergeKLists(ListNode[] lists) {    
          int len = lists.length;    
          if(len == 0) {    
              return null;    
          } else if(len == 1) {    
              return  lists[0];    
          } else {    
              ListNode[] next = new ListNode[(len + 1) / 2];    
              for(int i = 0; i < next.length; i++) {    
                  int j = 2 * i;    
                  if(len >= j + 2) {    
                      next[i] = mergeTwo(lists[j], lists[j+1]);    
                  }  else {    
                      next[i] = lists[j];    
                  }    
              }    
              return mergeKLists(next);    
          }    
      }    
      
      private ListNode mergeTwo(ListNode p, ListNode q) {    
          ListNode dual = new ListNode(-1);    
          ListNode cur = dual;    
          while(p != null || q != null) {    
              if(p == null) {    
                  cur.next = q;    
                  cur = cur.next;    
                  q = q.next;    
              } else if(q == null) {    
                  cur.next = p;    
                  cur = cur.next;    
                  p = p.next;    
              } else {    
                  if(p.val < q.val) {    
                      cur.next = p;    
                      cur = cur.next;    
                      p = p.next;    
                  } else {    
                      cur.next = q;    
                      cur = cur.next;    
                      q = q.next;    
                  }    
              }    
          }    
          return dual.next;    
      }    
  }    
```      
      
### 个人解读      
  流水账也能做，关键是为了效率，然后第一思路就是归并法。    
    
  总结：有些Hard就是这样，甚至比easy还简单。  
      
tags:      
  -  链表    
  -  归并排序    
