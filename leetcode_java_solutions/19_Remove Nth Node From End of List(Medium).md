### description
Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?

### solution
```
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        while(n-- > 0) {
            if(fast != null) {
                fast = fast.next;
            } else {
                return head;
            }
        }
        if(fast == null) {
            return head.next;
        }
        ListNode slow = head;
        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return head;

    }
}
```

### 个人解读
假设一个链表为[0,1,2,3,4,5,6,7,8,9]
要删除倒数第二个8

```
统计各个数值
start = 0
last = 9

n = 2
deleteIndex = 8

如果n=3
deleteIndex = 7
```
链表循环题目，有链表，有指针，所以猜测需要双指针
本题需要做差，做差是有间隔的

所以思路来了，先遍历n次，让fastIndex先移动n次，然后slowIndex启动
等fast.next == null (fast为最后一个元素时候)时候，删除slowIndex的下一个元素

这个时候注意两点:
+ 如果n>链表的长度
+ 删除时候，要在前一个节点删除
+ 各种边界条件


tags:
  - 链表
  - 双指针
  - 边界条件