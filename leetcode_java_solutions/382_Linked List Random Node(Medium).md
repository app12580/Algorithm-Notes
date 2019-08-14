### description    
  Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.  
    
  Follow up:  
  What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?  
    
  Example:  
    
  // Init a singly linked list [1,2,3].  
  ListNode head = new ListNode(1);  
  head.next = new ListNode(2);  
  head.next.next = new ListNode(3);  
  Solution solution = new Solution(head);  
    
  // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.  
  solution.getRandom();  
### solution    
```    
  class Solution {  
   private Random random;  
      private ListNode head;  
    
      public Solution(ListNode head) {  
          this.head = head;  
          random = new Random();  
      }  
    
      public int getRandom() {  
          int val = head.val;  
          ListNode n = head;  
          int s = 1;  
          while(n.next != null) {  
              n = n.next;  
              if(random.nextInt(s + 1) == s) {  
                  val = n.val;  
              }  
              s++;  
          }  
          return val;  
      }  
  }  
```    
    
### 个人解读    
  首先链表只有单向的，为了省空间，所以不会自己造双向链表的。而且为了获取随机，必须要遍历一遍全部的。  
    
  误区： 不需要任何效率上的优化。。。  
    
  新知识：蓄水池抽样。  
    
    
tags:    
  -  蓄水池抽样  
  -  随机数  
  -  链表  
