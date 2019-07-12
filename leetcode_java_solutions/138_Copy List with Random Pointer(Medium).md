### description    
  A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.  
    
  Return a deep copy of the list.  
    
     
  Example 1:  
    
    
    
  Input:  
  {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}  
    
  Explanation:  
  Node 1's value is 1, both of its next and random pointer points to Node 2.  
  Node 2's value is 2, its next pointer points to null and its random pointer points to itself.  
     
    
  Note:  
    
  You must return the copy of the given head as a reference to the cloned list.  
### solution    
```    
  class Solution {  
      private Map<Integer, Node> map = new HashMap<>();  
      public Node copyRandomList(Node head) {  
          if(head == null) return null;  
          int v = head.val;  
          if(map.containsKey(v)) {  
              return map.get(v);  
          }  
            
          Node copy = new Node();  
          copy.val = v;  
          map.put(v, copy);  
          copy.next = copyRandomList(head.next);  
          copy.random = copyRandomList(head.random);  
            
          return copy;  
      }  
  }  
```    
    
### 个人解读    
  类似于[133](133_Clone%20Graph(Medium).md)，依旧有个问题：val可以作为唯一标识，题目并没有说。  
    
  结果：可以说是一模一样的做法。  
    
    
tags:    
  -  模拟  
  -  DFS  
