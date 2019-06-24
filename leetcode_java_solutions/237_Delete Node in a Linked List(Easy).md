### description      
  Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.    
      
  Given linked list -- head = [4,5,1,9], which looks like following:    
      
       
      
  Example 1:    
      
  Input: head = [4,5,1,9], node = 5    
  Output: [4,1,9]    
  Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.    
  Example 2:    
      
  Input: head = [4,5,1,9], node = 1    
  Output: [4,5,9]    
  Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.    
       
      
  Note:    
      
  The linked list will have at least two elements.    
  All of the nodes' values will be unique.    
  The given node will not be the tail and it will always be a valid node of the linked list.    
  Do not return anything from your function.    
      
      
### solution      
```      
  class Solution {    
      public void deleteNode(ListNode node) {    
          ListNode next = node.next;    
          node.val = next.val;    
          node.next = next.next;    
      }    
  }    
```      
      
### 个人解读      
  坑爹题目，类似于脑筋急转弯。本题需要理解一点：题目不是要求"删除一个节点"，而是"返回一个与删除节点后一模一样的链表"。    
  内存地址什么的并不一样，删除节点和为返回数值一样的能是一回事吗？所以说题目坑底。    
      
  把当前节点伪造成下一个节点，然后删除下一个节点。    
      
      
tags:      
  -  链表    
  -  坑爹题目     
