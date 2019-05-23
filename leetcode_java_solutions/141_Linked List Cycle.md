### description
Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

 

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the second node.


Example 2:

Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the first node.

### solution
```
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null) {
             return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast != null && fast.next != null) {
            if(fast == slow) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
```

### 个人解读
链表是否成环，首先肯定需要遍历，然后常规想法是找一个数据结构去存储遍历到的结果(例如哈希表)，然后每遍历一次就判断hash表中是否已存在，如果存在，则返回true，否则继续遍历。如果循环结束，则返回false
然而这样有问题：1.需要保证hash和equals方法 2.空间消耗大

假设有其他简便算法，那么就不需要"hash表"去存储"全部遍历结果"，然而中间结果一定要有东西去存储，那么会是什么形式呢？
思路一：中间结构要变成O(1)复杂度的
思路二：
for(节点A 为起始， 一直到A==null) {  ...1
    判断是否成环	...2
    把遍历对象放进中间结果对象(修改中间结果数据)  ...3
}
循环有循环变量和循环结果存储，所以要改循环结果存储，循环变量也必然要改



tags:
  - 链表
  - 双指针
  - 计算模型