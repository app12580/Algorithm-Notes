### description
Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3

### solution
```
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dual = head;
        while(head != null) {
            if(head.next != null && head.next.val == head.val) {
                head.next = head.next.next;
            } else {
                head = head.next;   //首次写的时候，没有这个ifelse，
            }
        }
        return dual;
    }
}
```

### 个人解读
在while循环里面，不能无脑的head=head.next，因为删除的时候，如果直接跳过了，就少了一次判断
while里面还有一种操作：
while(条件) {
  if(需要擦欧洲哦){
    //需要操作
  } else {
    游标下移
  }
}

tags:
  - 链表
