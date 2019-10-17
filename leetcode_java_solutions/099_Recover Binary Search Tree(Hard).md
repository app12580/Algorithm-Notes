### description    
  Two elements of a binary search tree (BST) are swapped by mistake.  
    
  Recover the tree without changing its structure.  
    
  Example 1:  
    
  Input: [1,3,null,null,2]  
    
     1  
    /  
   3  
    \  
     2  
    
  Output: [3,1,null,null,2]  
    
     3  
    /  
   1  
    \  
     2  
  Example 2:  
    
  Input: [3,1,4,null,null,2]  
    
    3  
   / \  
  1   4  
     /  
    2  
    
  Output: [2,1,4,null,null,3]  
    
    2  
   / \  
  1   4  
     /  
    3  
  Follow up:  
    
  A solution using O(n) space is pretty straight forward.  
  Could you devise a constant space solution?  
### solution    
```    
Runtime: 2 ms, faster than 100.00% of Java online submissions for Recover Binary Search Tree.  
Memory Usage: 42.8 MB, less than 69.23% of Java online submissions for Recover Binary Search Tree.  
  
  class Solution {  
      public void recoverTree(TreeNode root) {  
          TreeNode pre = null;  
          TreeNode first = null, second = null;  
          // Morris Traversal  
          TreeNode temp = null;  
  		while(root!=null){  
  			if(root.left!=null){  
  				// connect threading for root  
  				temp = root.left;  
  				while(temp.right!=null && temp.right != root)  
  					temp = temp.right;  
  				// the threading already exists  
  				if(temp.right!=null){  
  				    if(pre!=null && pre.val > root.val){  
  				        if(first==null){first = pre;second = root;}  
  				        else{second = root;}  
  				    }  
  				    pre = root;  
  				      
  					temp.right = null;  
  					root = root.right;  
  				}else{  
  					// construct the threading  
  					temp.right = root;  
  					root = root.left;  
  				}  
  			}else{  
  				if(pre!=null && pre.val > root.val){  
  				    if(first==null){first = pre;second = root;}  
  				    else{second = root;}  
  				}  
  				pre = root;  
  				root = root.right;  
  			}  
  		}  
  		// swap two node values;  
  		if(first!= null && second != null){  
  		    int t = first.val;  
  		    first.val = second.val;  
  		    second.val = t;  
  		}  
      }  
  }  
```    
    
### 个人解读    
    
  首先几个基本思路需要想到：  
  1、看到BST，就要想到中序遍历  
  2、树的坐标  
    
  思路一：  
  1、使用两个List，一个存储值，一个存储坐标  
  2、先通过中序遍历找到出问题的点、然后交换  
  3、所谓交换节点，不过是两个val互换  
  然而本题目主要考察的是O(1)space。  
    
  思路二：  
  莫里斯遍历  
    
    
  //方法一： 低效率  
  ```  
  class Solution {  
   TreeNode firstElement = null;  
      TreeNode secondElement = null;  
      // The reason for this initialization is to avoid null pointer exception in the first comparison when prevElement has not been initialized  
      TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);  
    
      public void recoverTree(TreeNode root) {  
    
          traverse(root);  
    
          int temp = firstElement.val;  
          firstElement.val = secondElement.val;  
          secondElement.val = temp;  
      }  
    
      private void traverse(TreeNode root) {  
          if (root == null)  
              return;  
          traverse(root.left);  
    
          if (firstElement == null && prevElement.val >= root.val) {  
              firstElement = prevElement;  
          }  
    
          //找出比first小的最右边那个节点  
          if (firstElement != null && prevElement.val >= root.val) {  
              secondElement = root;  
          }  
          prevElement = root;  
          traverse(root.right);  
      }  
  }  
  ```  
    
tags:    
  -  树  
  -  遍历  
  -  莫里斯遍历  
