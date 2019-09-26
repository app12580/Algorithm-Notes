### description    
  Return any binary tree that matches the given preorder and postorder traversals.  
    
  Values in the traversals pre and post are distinct positive integers.  
    
     
    
  Example 1:  
    
  Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]  
  Output: [1,2,3,4,5,6,7]  
     
    
  Note:  
    
  1 <= pre.length == post.length <= 30  
  pre[] and post[] are both permutations of 1, 2, ..., pre.length.  
  It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.  
### solution    
```    
 Runtime: 1 ms, faster than 100.00% of Java online submissions for Construct Binary Tree from Preorder and Postorder Traversal.  
 Memory Usage: 40.9 MB, less than 9.09% of Java online submissions for Construct Binary Tree from Preorder and Postorder Traversal.  
   
  class Solution {  
       public TreeNode constructFromPrePost(int[] pre, int[] post) {  
          int N = pre.length;  
          return dfs(pre, post, 0, N - 1, 0, N - 1);  
      }  
    
      private TreeNode dfs(int[] preArr, int[] postArr, int pre1, int pre2, int post1, int post2) {  
          if(pre1 > pre2) return null;  
    
          TreeNode node = new TreeNode(preArr[pre1]);  
          if(pre1 == pre2) {  
              return node;  
          }  
    
          int leftVal = preArr[pre1 + 1];  
          int indexOfLeftValInPost = post1;  
          while(postArr[indexOfLeftValInPost] != leftVal) {  
              indexOfLeftValInPost++;  
          }  
          int leftLength = indexOfLeftValInPost - post1 + 1;  
          node.left = dfs(preArr, postArr, pre1 + 1, pre1 + leftLength, post1, indexOfLeftValInPost);  
          node.right = dfs(preArr, postArr, pre1 + leftLength + 1, pre2, indexOfLeftValInPost + 1, post2 - 1);  
          return node;  
      }  
  }  
```    
    
### 个人解读    
  本题目是前序和后续  
  前面的[105](105_Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal(Medium).md)  
  是前序和中序。  
    
  重点题目。  
    
  距离分析  
  Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]  
  Output: [1,2,3,4,5,6,7]  
  前序：中左右     后续：左右中  
  所以可知道：1是中，[2,4,5] 和 [4,5,2]  
  看pre可以知道2是左边中节点，然后需要去在post里面找2.  
    
  有进步啊，至少有点头绪了，主要是数组分成了三个区间，左中右。  
    
  时间100%应该就可以惹。  
    
tags:    
  -  树  
  -  重点题目  
