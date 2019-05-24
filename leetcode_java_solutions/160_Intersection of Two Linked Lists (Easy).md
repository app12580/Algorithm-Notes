### description  
Write a program to find the node at which the intersection of two singly linked lists begins.  
  
For example, the following two linked lists:  
  
  
begin to intersect at node c1.  
  
   
  
Example 1:  
  
  
Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3  
Output: Reference of the node with value = 8  
Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.  
   
Example 3:  
  
  
Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2  
Output: null  
Input Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.  
Explanation: The two lists do not intersect, so return null.  
   
  
Notes:  
  
If the two linked lists have no intersection at all, return null.  
The linked lists must retain their original structure after the function returns.  
You may assume there are no cycles anywhere in the entire linked structure.  
Your code should preferably run in O(n) time and use only O(1) memory.  
Example 2:  
  
  
Input: intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1  
Output: Reference of the node with value = 2  
Input Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [0,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.  
  
Example 3:  
  
  
Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2  
Output: null  
Input Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.  
Explanation: The two lists do not intersect, so return null.  
   
  
Notes:  
  
If the two linked lists have no intersection at all, return null.  
The linked lists must retain their original structure after the function returns.  
You may assume there are no cycles anywhere in the entire linked structure.  
Your code should preferably run in O(n) time and use only O(1) memory.  
### solution  
```  
public class Solution {  
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {  
         ListNode l1 = headA;  
        ListNode l2 = headB;  
        while(l1 != l2) {  
            if(l1 == null) {  
                l1 = headB;  
            } else {  
                l1 = l1.next;  
            }  
            if(l2 == null) {  
                l2 = headA;  
            } else {  
                l2 = l2.next;  
            }  
        }  
        return l1;  
    }  
}  
有个很巧妙的地方  
+ 1.也不知道谁规定的，while条件不要去判断node.next==null，而要直接判断node == null  
+ 2.除了交点外，双指针还有null这个交点  
```  
  
### 个人解读  
首先是链表数据结构，肯定要用到双指针  
问题：双指针遍历的时候，如何能知道是不是有交点  
双指针遍历的时候，有两类指标，一类是遍历速度，正常的是step=1，在判断成环时候一快一慢，另外一类指标就是指针的遍历范围，常规来说两个链表各自的长度。  
考虑速度不科学，变成考虑范围。  
a + c 和 b + c  
范围的变化有两种，一种做差另一种是做和  
做差是i走到头以后，j才开始遍历(i - j = lenghtA)  
做和是搞成a+b+c，i遍历完LenA再遍历lenB  
  
如果只是判断是否存在交点，那么就是另一个问题，即 编程之美 3.6 的问题。有两种解法：  
把第一个链表的结尾连接到第二个链表的开头，看第二个链表是否存在环；  
或者直接比较两个链表的最后一个节点是否相同。  
  
  
tags:  
  - 链表  
  - 双指针  
  - 双指针变范围