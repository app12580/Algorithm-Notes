### description    
  Implement the following operations of a stack using queues.  
    
  push(x) -- Push element x onto stack.  
  pop() -- Removes the element on top of the stack.  
  top() -- Get the top element.  
  empty() -- Return whether the stack is empty.  
  Example:  
    
  MyStack stack = new MyStack();  
    
  stack.push(1);  
  stack.push(2);    
  stack.top();   // returns 2  
  stack.pop();   // returns 2  
  stack.empty(); // returns false  
  Notes:  
    
  You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.  
  Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.  
  You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).  
### solution    
```    
  class MyStack {  
    
     private Queue<Integer> queue;  
    
      public MyStack() {  
          queue = new LinkedList<>();  
      }  
    
      public void push(int x) {  
          queue.offer(x);  
          int cnt = queue.size();  
          while (cnt-- > 1) {  
              queue.offer(queue.poll());  
          }  
      }  
    
      public int pop() {  
          return queue.poll();  
      }  
    
      public int top() {  
          return queue.peek();  
      }  
    
      public boolean empty() {  
          return queue.isEmpty();  
      }  
  }  
```    
    
### 个人解读    
  使用队列模拟栈，与栈不同的是，在stack里面，push和pop的都是同一个位置点，而queue里面，offer和poll的是不同的位置点，这样就可以在queue的内部调整。  
  调整有两种，一种是在push的时候调整，另一种是在pop的时候调整。  
  由于需要模拟top和pop两个方法，所以还是push的时候在调整吧。  
  push时候调整，就相当于每次插入的时候，自动插在队列的开头位置。  
    
    
tags:    
  -  模拟  
  -  队列  
