### description    
  Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.  
    
  k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.  
    
  Example:  
    
  Given this linked list: 1->2->3->4->5  
    
  For k = 2, you should return: 2->1->4->3->5  
    
  For k = 3, you should return: 3->2->1->4->5  
    
  Note:  
    
  Only constant extra memory is allowed.  
  You may not alter the values in the list's nodes, only nodes itself may be changed.  
### solution    
```    
Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Nodes in k-Group.  
Memory Usage: 37.2 MB, less than 94.83% of Java online submissions for Reverse Nodes in k-Group.  
  
     
class Solution {  
     public ListNode reverseKGroup(ListNode head, int k) {  
        if(k == 1 || head == null) return head;  
        ListNode dual = new ListNode(-1);  
        ListNode pre = dual;  
        ListNode curHead = head;  
        boolean flag = false;  
        while(true) {  
            for(int i = 0; i < k - 1; i++) {  
                if(head == null || head.next == null) {     // error： 重点是这一行  
                    flag = true;  
                    break;  
                }  
                head = head.next;  
            }  
            if(flag) {  
                pre.next = curHead;  
                break;  
            }  
            //经过for循环后，head为当前批次中的最后一个,pre接电，curHead会变成下一轮的接点  
            ListNode next = head.next;  
            head.next = null;  
            // 1 -> 2 -> 3 -> 4  
            pre.next = reverse(curHead);  
            head = next;  
            pre = curHead;  
            curHead = head;  
  
        }  
        return dual.next;  
    }  
  
    public ListNode reverse(ListNode head) {  
        ListNode pre = null;  
        while(head != null) {  
            ListNode next = head.next;  
            head.next = pre;  
            pre = head;  
            head = next;  
        }  
        return pre;  
    }  
}  
```    
    
### 个人解读    
  主要考察 的是对代码的控制力，最大的思路就是要问题拆解，别面向过程全堆在一起了。  
    
  思路：和反转链表方法结合。  
    
  总结：思路没有错，主要是对于细节的把握，每个变量的具体含义，经过循环后要变成什么样子。  
    
  一点点感慨： 套路是一点点积累的，至少这题目的虚拟节点已经成习惯了。  
    
tags:    
  -  链表  
  -  细节  
  -  反转  
