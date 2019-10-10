### description    
  We are given the root node of a maximum tree: a tree where every node has a value greater than any other value in its subtree.  
    
  Just as in the previous problem, the given tree was constructed from an list A (root = Construct(A)) recursively with the following Construct(A) routine:  
    
  If A is empty, return null.  
  Otherwise, let A[i] be the largest element of A.  Create a root node with value A[i].  
  The left child of root will be Construct([A[0], A[1], ..., A[i-1]])  
  The right child of root will be Construct([A[i+1], A[i+2], ..., A[A.length - 1]])  
  Return root.  
  Note that we were not given A directly, only a root node root = Construct(A).  
    
  Suppose B is a copy of A with the value val appended to it.  It is guaranteed that B has unique values.  
    
  Return Construct(B).  
    
     
    
  Example 1:  
    
    
    
  Input: root = [4,1,3,null,null,2], val = 5  
  Output: [5,4,null,1,3,null,null,2]  
  Explanation: A = [1,4,2,3], B = [1,4,2,3,5]  
  Example 2:  
    
    
  Input: root = [5,2,4,null,1], val = 3  
  Output: [5,2,4,null,1,null,3]  
  Explanation: A = [2,1,5,4], B = [2,1,5,4,3]  
  Example 3:  
    
    
  Input: root = [5,2,3,null,1], val = 4  
  Output: [5,2,4,null,1,3]  
  Explanation: A = [2,1,5,3], B = [2,1,5,3,4]  
     
    
  Note:  
    
  1 <= B.length <= 100  
### solution    
```    
  Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Binary Tree II.  
  Memory Usage: 35.5 MB, less than 100.00% of Java online submissions for Maximum Binary Tree II.  
    
    
class Solution {  
   public TreeNode insertIntoMaxTree(TreeNode root, int val) {  
        if(root == null) {  
            return new TreeNode(val);  
        }  
        if(root.val < val) {  
            TreeNode res = new TreeNode(val);  
            res.left = root;  
            return res;  
        }  
        TreeNode res = root;  
        TreeNode pre = null;  
        while(root != null){  
            if(root.val > val) {  
                pre = root;  
                root = root.right;  
            } else {  
                break;  
            }  
        }  
        TreeNode right = pre.right;  
        TreeNode n = new TreeNode(val);  
        if(right == null || right.val < val) {  
            n.left = right;  
        } else {  
            n.right = right;  
        }  
        pre.right = n;  
        return res;  
    }  
}  
```    
    
### 个人解读    
  首先读明白题目：本题目输入的input是根据一个int[]构造而成，而输出的是在原有int[]后面加上一个int，然后按照那个同样规则后构建的结果。  
    
  思路一：  
  需要判断新给的数字与前面所有数字的大小关系。  
  1、如果大于max，就很easy了  
  2、如果是其中某一个位置，  
  3、发现原有构造函数就是前序遍历。   
  4、刚开始以为要先把原来int[]弄出来，但定睛一看，发现直接从root节点去找大概就行了。类似于BST的找法。  
    如果cur>val,cur=cur.next否则，  
    
  总结：  
  1、直接通过当前节点的大小关系去找  
  2、注意各种情况，比如null  
  3、通过找规律即可  
    
  差点就去翻答案了。。。。  
    
tags:    
  -  树  
  -  前序遍历深度了解  
