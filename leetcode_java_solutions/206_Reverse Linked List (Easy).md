### description  
Reverse a singly linked list.  
  
Example:  
  
Input: 1->2->3->4->5->NULL  
Output: 5->4->3->2->1->NULL  
Follow up:  
  
A linked list can be reversed either iteratively or recursively. Could you implement both?  
### solution  
```  
  
循环法: 口诀：   
外2内4  
while外有声明一个return一个，while里 声(明)1移(动)1(继)续2  
public ListNode reverseList(ListNode head) {  
        ListNode pre = null;  
        while(head != null) {       //坚持链表的while条件绝不动摇  
            ListNode next = head.next;  
            head.next = pre;  
            pre = head;     //然后要想到head需要存起来  
            head = next;    //head = next 是必须的  
        }  
        return pre; //return pre的原因是当head为null时候，pre就是最新节点了  
    }  
      
      
其他方法  
  
递归  
  
public ListNode reverseList(ListNode head) {  
    if (head == null || head.next == null) {  
        return head;  
    }  
    ListNode next = head.next;  
    ListNode newHead = reverseList(next);  
    next.next = head;  
    head.next = null;  
    return newHead;  
}  
  
head -> next -> ... -> newHead  
head   newHead -> ... -> ... next  
newHead -> ... -> ... next -> head  
  
  
头插法  
  
public ListNode reverseList(ListNode head) {  
    ListNode newHead = new ListNode(-1);  
    while (head != null) {  
        ListNode next = head.next;  
        head.next = newHead.next;  
        newHead.next = head;  
        head = next;  
    }  
    return newHead.next;  
}  
  
```  
  
### 个人解读  
经典算法，需要认真处理(背答案)，头插法、递归法  
每种方法都需要定义两个变量  
  
  
tags:  
  -   
