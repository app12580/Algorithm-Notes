### description    
  Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.  
    
  Example 1:  
    
  Input: 1->2->3->3->4->4->5  
  Output: 1->2->5  
  Example 2:  
    
  Input: 1->1->1->2->3  
  Output: 2->3  
### solution    
```    
  
Runtime: 1 ms, faster than 81.54% of Java online submissions for Remove Duplicates from Sorted List II.  
Memory Usage: 36.1 MB, less than 100.00% of Java online submissions for Remove Duplicates from Sorted List II.  
  
  class Solution {  
      public ListNode deleteDuplicates(ListNode head) {  
           ListNode dual = new ListNode(-1);  
          ListNode cur = dual;  
          dual.next = head;  
          boolean sameFlag = false;  
          while(cur.next != null) {  
              //想办法令cur为第一个不重复的元素。  
              ListNode next = cur.next;  
              int v = next.val;  
              if(next.next != null && next.next.val == v) {  
                  //然后想办法，让cur.next = 下一个值  
                  while(next.next != null && next.next.val == v) {  
                      next = next.next;  
                  }  
                  next = next.next;  
                  cur.next = next;  
              } else {  
                  cur.next = next;  
                  cur = next;  
              }  
          }  
    
          return dual.next;  
      }  
  }  
```    
    
### 个人解读    
  使用标记法，简单的标记，使用一个set。  
  需要使用一个boolean，或者在某处使用while同时去除所有的重复节点。  
  需要注意链表的处理方式  
    
  思考：标记法和while的关系：具体的应该是连续元素标记可以使用while来解决。  
    
  因为while条件是这个，所以需要想好细节和每一步的处理。遇到相同的节点，只需要清理next，而不需要修改cur。  
  ```  
  cur.next != null  
  ```  
    
    
tags:    
  -  链表  
  -  标记法(while法)  
