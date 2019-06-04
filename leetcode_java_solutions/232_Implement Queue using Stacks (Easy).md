### description    
  Implement the following operations of a queue using stacks.  
    
  push(x) -- Push element x to the back of queue.  
  pop() -- Removes the element from in front of queue.  
  peek() -- Get the front element.  
  empty() -- Return whether the queue is empty.  
  Example:  
    
  MyQueue queue = new MyQueue();  
    
  queue.push(1);  
  queue.push(2);    
  queue.peek();  // returns 1  
  queue.pop();   // returns 1  
  queue.empty(); // returns false  
  Notes:  
    
  You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.  
  Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.  
  You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).  
### solution    
```    
  class MyQueue {  
    
      private Stack<Integer> in = new Stack<>();  
      private Stack<Integer> out = new Stack<>();  
    
      public void push(int x) {  
          in.push(x);  
      }  
    
      public int pop() {  
          in2out();  
          return out.pop();  
      }  
    
      public int peek() {  
          in2out();  
          return out.peek();  
      }  
    
      private void in2out() {  
          if (out.isEmpty()) {  
              while (!in.isEmpty()) {  
                  out.push(in.pop());  
              }  
          }  
      }  
    
      public boolean empty() {  
          return in.isEmpty() && out.isEmpty();  
      }  
  }  
```    
    
### 个人解读    
  模拟一个栈，主要是模拟出来相应类的方法和属性。  
  使用栈模拟队列，因为栈只能先进后出。所以想到内部有两个栈，入队列时候直接用stack.push就行，等出队列的时候只能全部stack.pop()装进另外一个stack里面。  
    
tags:    
  -  模拟    
