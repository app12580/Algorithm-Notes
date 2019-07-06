### description    
  You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.  
    
  You may assume the two numbers do not contain any leading zero, except the number 0 itself.  
    
  Example:  
    
  Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)  
  Output: 7 -> 0 -> 8  
  Explanation: 342 + 465 = 807.  
### solution    
```    
  /**  
   * Definition for singly-linked list.  
   * public class ListNode {  
   *     int val;  
   *     ListNode next;  
   *     ListNode(int x) { val = x; }  
   * }  
   */  
  class Solution {  
      public ListNode addTwoNumbers(ListNode l1, ListNode l2) {  
           ListNode dual = new ListNode(-1);  
          ListNode cur = dual;  
          int carry = 0;  
          int sum = 0;  
          while(l1 !=null || l2 != null) {  
              sum = carry;  
              sum += l1 == null ? 0 : l1.val;  
              sum += l2 == null ? 0 : l2.val;  
              ListNode next = new ListNode(sum % 10);  
              carry = sum / 10;  
              cur.next = next;  
              cur = next;  
              if(l1 != null) l1 = l1.next;  
              if(l2 != null) l2 = l2.next;  
          }  
          if(carry == 1) {  
              cur.next = new ListNode(1);  
          }  
            
          return dual.next;  
      }  
  }  
```    
    
### 个人解读    
  双指针即可。  
    
tags:    
  -  链表  
  -  双指针  
