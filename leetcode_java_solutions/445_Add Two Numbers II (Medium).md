### description  
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.  
  
You may assume the two numbers do not contain any leading zero, except the number 0 itself.  
  
Follow up:  
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.  
  
Example:  
  
Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)  
Output: 7 -> 8 -> 0 -> 7  
  
  
### solution  
```  
  class Solution {
      public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
          Stack<Integer> stack1 = buildStack(l1);
          Stack<Integer> stack2 = buildStack(l2);
          
          ListNode head = new ListNode(-1);
          int carry = 0;
          while(!stack1.isEmpty() || !stack2.isEmpty() || carry ==1) {
              int i1 = stack1.isEmpty() ? 0 : stack1.pop();
              int i2 = stack2.isEmpty() ? 0 : stack2.pop();
              int sum = i1 + i2 + carry;
              ListNode node = new ListNode(sum % 10);
              node.next = head.next;
              head.next = node;
              carry = sum / 10;
          }
          return head.next;
      }
  
      public Stack<Integer> buildStack(ListNode node) {
          Stack<Integer> stack = new Stack<>();
          while(node != null) {
              stack.push(node.val);
              node = node.next;
          }
          return stack;
      }
  }
```  
  
### 个人解读  
这道题的疑问：需要先获取到链表的结尾，然而这个需要先把链表遍历一遍，总是在纠结，会不会有方法不用单独遍历一遍就能把问题解决的。  
  
如果遍历完了，可以选择用Stack来存储数据。  
  
  
tags:  
  -   链表  
  -  双指针  
  -  预处理  
