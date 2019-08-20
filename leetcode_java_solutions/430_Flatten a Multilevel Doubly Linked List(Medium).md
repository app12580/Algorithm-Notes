### description    
  You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.  
    
  Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.  
    
     
    
  Example:  
    
  Input:  
   1---2---3---4---5---6--NULL  
           |  
           7---8---9---10--NULL  
               |  
               11--12--NULL  
    
  Output:  
  1-2-3-7-8-11-12-9-10-4-5-6-NULL  
     
    
  Explanation for the above example:  
    
  Given the following multilevel doubly linked list:  
    
    
     
    
  We should return the following flattened doubly linked list:  
### solution    
```    
  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Flatten a Multilevel Doubly Linked List.  
Memory Usage: 37 MB, less than 100.00% of Java online submissions for Flatten a Multilevel Doubly Linked List.  
  
  class Solution {  
       public Node flatten(Node head) {  
          if(head == null) return null;  
          Node res = head;  
          Stack<Node> stack = new Stack<>();  
          Node cur = head;  
          while(true) {  
              //先移动cur  
              while(cur.child == null && cur.next != null) {  
                  cur = cur.next;  
              }  
              //当前节点需要处理了  
              //需要先处理有child的情况，然后才是next==null的情况。  
              if(cur.child != null){  
                  if(cur.next != null) {  
                      stack.push(cur.next);  
                  }  
                  Node child = cur.child;  
                  cur.next = child;  
                  child.prev = cur;  
                  cur.child = null;  
                  cur = cur.next;  
              } else if(cur.next == null) {  
                  if(!stack.isEmpty()) {  
                      Node pop = stack.pop();  
                      cur.next = pop;  
                      pop.prev = cur;  
                      cur = pop;  
                  } else {  
                      break;  
                  }  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  思路： 可以使用栈或者递归，然而因为链表是双向的，所以还是栈比较好。  
    
  不需要每个节点都入栈处理，而是遇到有子Node的时候才需要入栈。  
    
  好像中途遇到bug了，run code没通过，找了半天没发现问题所在，过了一会直接submit通过了。。。  
    
  栈的操作可以很多，不用每次必须push和pop，可以和while结合，也可以和true结合。  
    
tags:    
  -  栈  
  -  链表  
