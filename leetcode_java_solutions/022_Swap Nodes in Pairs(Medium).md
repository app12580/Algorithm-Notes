### description
Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes, only nodes itself may be changed.

 

Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.
### solution
```
class Solution {
    public ListNode swapPairs(ListNode head) {
         //伪造了一个初始节点用于计算
        ListNode node = new ListNode(-1);
        node.next = head;
        //在进循环前创建了两个变量，一个是dual节点，另一个是
        ListNode pre = node;
        while (pre.next != null && pre.next.next != null) {
//            pre l1 l2 next
//            pre l2 l1 next
//            pre 指向l1
            ListNode l1 = pre.next, l2 = pre.next.next;
            ListNode next = l2.next;
            l1.next = next;
            l2.next = l1;
            pre.next = l2;

            pre = l1;
        }
        return node.next;
    }
}
```

### 个人解读
链表与数组不同的地方在于，链表的交换成本很高，而且head都能弄没是最气的
 感觉链表的题还是要画图啊

一次循环移动两次，while条件不太一样(pre.next != null && pre.next.next != null)
还要创建一个虚拟节点node


tags:
  - 
