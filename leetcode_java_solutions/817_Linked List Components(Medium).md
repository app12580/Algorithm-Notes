### description    
  We are given head, the head node of a linked list containing unique integer values.  
    
  We are also given the list G, a subset of the values in the linked list.  
    
  Return the number of connected components in G, where two values are connected if they appear consecutively in the linked list.  
    
  Example 1:  
    
  Input:   
  head: 0->1->2->3  
  G = [0, 1, 3]  
  Output: 2  
  Explanation:   
  0 and 1 are connected, so [0, 1] and [3] are the two connected components.  
  Example 2:  
    
  Input:   
  head: 0->1->2->3->4  
  G = [0, 3, 1, 4]  
  Output: 2  
  Explanation:   
  0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.  
  Note:  
    
  If N is the length of the linked list given by head, 1 <= N <= 10000.  
  The value of each node in the linked list will be in the range [0, N - 1].  
  1 <= G.length <= 10000.  
  G is a subset of all values in the linked list.  
### solution    
```    
  Runtime: 7 ms, faster than 81.22% of Java online submissions for Linked List Components.  
  Memory Usage: 40.7 MB, less than 69.23% of Java online submissions for Linked List Components.  
    
  class Solution {  
       public int numComponents(ListNode head, int[] G) {  
         if(head == null) return 0;  
          Set<Integer> set = new HashSet<>();  
          for(int g: G) {  
              set.add(g);  
          }  
          int res = 0;  
          while(head != null) {  
              while(head != null && !set.contains(head.val)) {  
                  head = head.next;  
              }  
              //head是第一个值  
              if(head == null) {  
                  break;  
              }   
              res++;  
              //head变成末尾  
              while(head != null && set.contains(head.val)) {  
                  head = head.next;  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  题目描述里面有不明确的地方，如果遇到重复的数字怎么算？  
  ```  
  input   
  [0,1,1,3,3]  
  [0,1,3]  
    
  expected   
  1  
  ```  
  所以看来是可以重复利用的。  
    
  总结：实际写代码时候发现类似于数组的分治法。  
    
tags:    
  -  链表  
  -  分治法  
