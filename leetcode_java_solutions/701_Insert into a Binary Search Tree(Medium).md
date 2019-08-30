### description  
  Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
  
  Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.
  
  For example, 
  
  Given the tree:
          4
         / \
        2   7
       / \
      1   3
  And the value to insert: 5
  You can return this binary search tree:
  
           4
         /   \
        2     7
       / \   /
      1   3 5
  This tree is also valid:
  
           5
         /   \
        2     7
       / \   
      1   3
           \
            4
### solution  
```  
  Runtime: 0 ms, faster than 100.00% of Java online submissions for Insert into a Binary Search Tree.
  Memory Usage: 39 MB, less than 100.00% of Java online submissions for Insert into a Binary Search Tree.
   
class Solution {
   public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) {
            return new TreeNode(val);
        }
        TreeNode node = findNode(root, val);
        if(val > node.val) {
            TreeNode r = node.right;
            node.right = new TreeNode(val);
            node.right.right = r;
        } else {
            TreeNode r = node.left;
            node.left = new TreeNode(val);
            node.left.left = r;
        }
        return root;
    }

    private TreeNode findNode(TreeNode root, int val) {
        TreeNode node = root;
        while(true) {
            if(node.val < val) {
                if(node.right != null) {
                    node = node.right;
                } else {
                    break;
                }
            } else {
                if(node.left != null) {
                    node = node.left;
                } else {
                    break;
                }
            }
        }
        return node;
    }
}

```  
  
### 个人解读  
  感觉可以找到位置后强行插入，并不需要修改树的结构。
  
  注意找到父节点的位置，以null为终止条件。
  等价说，最后一定会有叶子节点作为添加的地方。这是非常关键的一个信息
  
  
  
tags:  
  -  变化树
  -  BST
