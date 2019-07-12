### description  
  Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
  
  To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
  
  Note: Do not modify the linked list.
  
   
  
  Example 1:
  
  Input: head = [3,2,0,-4], pos = 1
  Output: tail connects to node index 1
  Explanation: There is a cycle in the linked list, where tail connects to the second node.
  
  
  Example 2:
  
  Input: head = [1,2], pos = 0
  Output: tail connects to node index 0
  Explanation: There is a cycle in the linked list, where tail connects to the first node.
  
  
  Example 3:
  
  Input: head = [1], pos = -1
  Output: no cycle
  Explanation: There is no cycle in the linked list.
  
  
   
  
  Follow-up:
  Can you solve it without using extra space?
  
  找出链表的起始位置索引
### solution  
```  
  public class Solution {
      public ListNode detectCycle(ListNode head) {
           if(head == null || head.next == null) return null;
          ListNode fast = head.next.next;
          ListNode slow = head.next;
          while(fast != null && fast.next != null && fast != slow) {
              fast = fast.next.next;
              slow = slow.next;
          }
          if(fast == null || fast.next == null) {
              return null;
          }
          fast = head;
          while(true) {
               if(fast == slow) {       //注意这里先判断，排除index=0的情况。
                  return slow;
              }
              fast = fast.next;
              slow = slow.next;
             
          }
      }
  }
```  
  
### 个人解读  
  参见[287](287_Find%20the%20Duplicate%20Number(Medium).md)
  印象里有一个方法，双指针满足特定条件时，恰好落在起点。
  
  假设环结构： A + N * L， 相遇时，环的入口为A点，相交的点为B点。
  OA=a
  AB=b
  
  成环的相遇点为大于A的第一个L的倍数点C
  证明：不妨另c=a+b=N*L。 //NL 为大于a的最小L的倍数
  首先fast先走到C处，此时slow走到C/2处。
  接下来slow再走到C处，fast再走C，为原地蹦跶。 
   
  之后一个从B走，一个从O走。一倍速走。
  slow先从0走到了A点，走了a。
  而B点再走A步：b+a=NL，恰好是在起点。
  
  特殊解法，需要重点记忆。
  
tags:  
  -  链表
  -  双指针
  -  链表成环
  -  重点数学
