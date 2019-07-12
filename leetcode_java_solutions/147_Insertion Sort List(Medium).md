### description    
  Sort a linked list using insertion sort.  
    
    
  A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.  
  With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list  
     
    
  Algorithm of Insertion Sort:  
    
  Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.  
  At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.  
  It repeats until no input elements remain.  
    
  Example 1:  
    
  Input: 4->2->1->3  
  Output: 1->2->3->4  
  Example 2:  
    
  Input: -1->5->3->4->0  
  Output: -1->0->3->4->5  
### solution    
```    
Runtime: 32 ms, faster than 24.66% of Java online submissions for Insertion Sort List.  
Memory Usage: 36 MB, less than 100.00% of Java online submissions for Insertion Sort List.  
  
  class Solution {  
      public ListNode insertionSortList(ListNode head) {  
         if(head == null) return null;  
          ListNode dummy = new ListNode(1);  
          ListNode cur = head;  
          ListNode next = null;  
          while(cur != null) {  
              int v = cur.val;  
              next = cur.next;  
              //找出cur的插入位置  
              ListNode insert = dummy;  
              while(insert.next != null && insert.next.val < v && insert.next != cur) {  
                  insert = insert.next;  
              }  
              ListNode ll = insert.next;  
              insert.next = cur;  
              cur.next = ll;  
    
              cur = next;  
          }  
          return dummy.next;  
      }  
  }  
    
  //参考答案  
  Runtime: 3 ms, faster than 95.67% of Java online submissions for Insertion Sort List.  
  Memory Usage: 37.7 MB, less than 93.72% of Java online submissions for Insertion Sort List.  
    
  // 整体思路是类似的，搞不懂为啥效率差了这么多  
  // 没用dummy；在原来的链上修改。  
    
  class Solution {  
      public ListNode insertionSortList(ListNode head) {  
          if(head==null || head.next==null)return head;  
          ListNode end=head,itr=null;  
          while(end.next!=null){  
              itr=end.next;  
              if(itr.val>=end.val){  
                  end = itr;  
              }else{  
                  end.next = end.next.next;  
                  ListNode temp = head;  
                  if(temp.val>=itr.val){  
                      itr.next = head;  
                      head=itr;  
                  }else{  
                      while(temp.next.val<itr.val){  
                          temp = temp.next;  
                      }  
                      itr.next = temp.next;  
                      temp.next = itr;  
                  }  
              }  
          }  
          return head;  
            
      }  
  }  
```    
    
### 个人解读    
  先不管对错，为什么下面的代码会导致内存不够了？？  
  ```  
  public ListNode insertionSortList(ListNode head) {  
          if(head == null) return null;  
          ListNode dummy = new ListNode(1);  
          dummy.next = head;  
          ListNode cur = head;  
          ListNode next = null;  
          while(cur != null) {  
              int v = cur.val;  
              next = cur.next;  
              //找出cur的插入位置  
              ListNode insert = dummy;  
              while(insert.next != null && insert.next.val < v && insert.next != cur) {  
                  insert = insert.next;  
              }  
              if(insert != null) {  
                  //执行插入  
                  //为什么下面这两行执行会导致内存爆炸？？ 因为insert==cur了？？  
                  cur.next = insert.next;  
                  insert.next = cur;  
              }  
              cur = next;  
          }  
          return dummy.next;  
      }  
  
  ```  
    
tags:    
  -  链表  
  -  排序  
  -  内存泄露  
