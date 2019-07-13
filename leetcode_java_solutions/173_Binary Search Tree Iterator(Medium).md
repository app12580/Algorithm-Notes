### description    
  Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.  
    
  Calling next() will return the next smallest number in the BST.  
    
     
    
  Example:  
    
    
    
  BSTIterator iterator = new BSTIterator(root);  
  iterator.next();    // return 3  
  iterator.next();    // return 7  
  iterator.hasNext(); // return true  
  iterator.next();    // return 9  
  iterator.hasNext(); // return true  
  iterator.next();    // return 15  
  iterator.hasNext(); // return true  
  iterator.next();    // return 20  
  iterator.hasNext(); // return false  
     
    
  Note:  
    
  next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.  
  You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.  
### solution    
```    
方法一： 没有用到BST特性这个效率。。  
Runtime: 64 ms, faster than 9.95% of Java online submissions for Binary Search Tree Iterator.  
Memory Usage: 50.7 MB, less than 92.57% of Java online submissions for Binary Search Tree Iterator.  
  class BSTIterator {  
    
      private int index = -1;  
          private int[] arr = new int[0];  
    
          public BSTIterator(TreeNode root) {  
              if(root == null) return;  
              Queue<TreeNode> queue = new LinkedList<>();  
              queue.offer(root);  
              List<Integer> list = new ArrayList<>();  
              while(!queue.isEmpty()) {  
                  TreeNode poll = queue.poll();  
                  if(poll.left != null) {  
                      queue.offer(poll.left);  
                  }  
                  if(poll.right != null) {  
                      queue.offer(poll.right);  
                  }  
                  list.add(poll.val);  
              }  
              arr = new int[list.size()];  
              for(int i  = 0; i < list.size(); i++) {  
                  arr[i] = list.get(i);  
              }  
              Arrays.sort(arr);  
          }  
    
          /** @return the next smallest number */  
          public int next() {  
              return arr[++index];  
          }  
    
          /** @return whether we have a next smallest number */  
          public boolean hasNext() {  
              return index < arr.length - 1;  
          }  
  }  
    
    
  // 方法二：  
  Runtime: 58 ms, faster than 82.29% of Java online submissions for Binary Search Tree Iterator.  
  Memory Usage: 51 MB, less than 91.12% of Java online submissions for Binary Search Tree Iterator.  
    
    
class BSTIterator {  
  
     private int index = -1;  
        private int[] arr = new int[0];  
  
        public BSTIterator(TreeNode root) {  
            if(root == null) return;  
            List<Integer> list = new ArrayList<>();  
            helper(root, list);  
            arr = new int[list.size()];  
            for(int i  = 0; i < list.size(); i++) {  
                arr[i] = list.get(i);  
            }  
        }  
  
        private void helper(TreeNode node, List<Integer> list) {  
            if(node.left != null) {  
                helper(node.left, list);  
            }  
            list.add(node.val);  
            if(node.right != null) {  
                helper(node.right, list);  
            }  
              
        }  
  
        /** @return the next smallest number */  
        public int next() {  
            return arr[++index];  
        }  
  
        /** @return whether we have a next smallest number */  
        public boolean hasNext() {  
            return index < arr.length - 1;  
        }  
}  
  
```    
    
### 个人解读    
  通过层次遍历，遍历一遍，然后再转换成一个数组。  
  方法一忘记了BST的属性了。  
    
    
tags:    
  -  BST  
  -  中序遍历  
