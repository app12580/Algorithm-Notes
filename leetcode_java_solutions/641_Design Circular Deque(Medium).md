### description    
  Design your implementation of the circular double-ended queue (deque).  
    
  Your implementation should support following operations:  
    
  MyCircularDeque(k): Constructor, set the size of the deque to be k.  
  insertFront(): Adds an item at the front of Deque. Return true if the operation is successful.  
  insertLast(): Adds an item at the rear of Deque. Return true if the operation is successful.  
  deleteFront(): Deletes an item from the front of Deque. Return true if the operation is successful.  
  deleteLast(): Deletes an item from the rear of Deque. Return true if the operation is successful.  
  getFront(): Gets the front item from the Deque. If the deque is empty, return -1.  
  getRear(): Gets the last item from Deque. If the deque is empty, return -1.  
  isEmpty(): Checks whether Deque is empty or not.   
  isFull(): Checks whether Deque is full or not.  
     
    
  Example:  
    
  MyCircularDeque circularDeque = new MycircularDeque(3); // set the size to be 3  
  circularDeque.insertLast(1);			// return true  
  circularDeque.insertLast(2);			// return true  
  circularDeque.insertFront(3);			// return true  
  circularDeque.insertFront(4);			// return false, the queue is full  
  circularDeque.getRear();  			// return 2  
  circularDeque.isFull();				// return true  
  circularDeque.deleteLast();			// return true  
  circularDeque.insertFront(4);			// return true  
  circularDeque.getFront();			// return 4  
     
    
  Note:  
    
  All values will be in the range of [0, 1000].  
  The number of operations will be in the range of [1, 1000].  
  Please do not use the built-in Deque library.  
### solution    
```    
  class MyCircularDeque {  
    int[] a;  
      int front, rear, cap;  
    
      /** Initialize your data structure here. Set the size of the deque to be k. */  
      public MyCircularDeque(int k) {  
          a = new int[k + 1];  
          front = 0;  
          rear = 0;  
          cap = k + 1;  
      }  
        
      /** Adds an item at the front of Deque. Return true if the operation is successful. */  
      public boolean insertFront(int value) {  
          if(isFull()) return false;  
          a[front] = value;  
          front = (front + 1) % cap;  
          return true;  
      }  
        
      /** Adds an item at the rear of Deque. Return true if the operation is successful. */  
      public boolean insertLast(int value) {  
          if(isFull()) return false;  
          rear = (rear - 1 + cap) % cap;  
          a[rear] = value;  
          return true;  
      }  
        
      /** Deletes an item from the front of Deque. Return true if the operation is successful. */  
      public boolean deleteFront() {  
          if(isEmpty()) return false;  
          front = (front - 1 + cap) % cap;  
          return true;  
      }  
        
      /** Deletes an item from the rear of Deque. Return true if the operation is successful. */  
      public boolean deleteLast() {  
          if(isEmpty()) return false;  
          rear = (rear + 1) % cap;  
          return true;  
      }  
        
      /** Get the front item from the deque. */  
      public int getFront() {  
          return isEmpty() ? -1 : a[(front - 1 + cap) % cap];  
      }  
        
      /** Get the last item from the deque. */  
      public int getRear() {  
          return isEmpty() ? -1 : a[rear];  
      }  
        
      /** Checks whether the circular deque is empty or not. */  
      public boolean isEmpty() {  
          return front == rear;  
      }  
        
      /** Checks whether the circular deque is full or not. */  
      public boolean isFull() {  
           return (front + 1) % cap == rear;  
      }  
  }  
    
     
```    
    
### 个人解读    
  通过数组应该是效率最高的了。  
    
tags:    
  -  模拟  
