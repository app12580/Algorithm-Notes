### description  
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.  
  
Example:  
  
Input: 1->2->4, 1->3->4  
Output: 1->1->2->3->4->4  
  
### solution  
```  
class Solution {  
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {  
        ListNode res = new ListNode(-1);  
        ListNode cur = res;  
        while(l1 != null && l2 != null) {  
            if(l1.val < l2.val) {  
                cur.next = l1;  
                l1 = l1.next;  
            } else {  
                cur.next = l2;  
                l2 = l2.next;  
            }  
            cur = cur.next;  
        }  
        while(l1 != null) {  
            cur.next = l1;  
            cur = cur.next;  
            l1 = l1.next;  
        }  
        while(l2 != null) {  
            cur.next = l2;  
            cur = cur.next;  
            l2 = l2.next;  
        }  
        return res.next;  
    }  
}  
```  
+ 其他方法：递归  
```  
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {  
    if (l1 == null) return l2;  
    if (l2 == null) return l1;  
    if (l1.val < l2.val) {  
        l1.next = mergeTwoLists(l1.next, l2);  
        return l1;  
    } else {  
        l2.next = mergeTwoLists(l1, l2.next);  
        return l2;  
    }  
}  
```  
  
### 个人解读  
双指针归并，套模板题目  
  
tags:  
  - 链表  
  - 双指针  
