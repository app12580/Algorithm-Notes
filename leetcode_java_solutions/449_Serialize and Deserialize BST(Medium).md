### description    
  Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.  
    
  Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.  
    
  The encoded string should be as compact as possible.  
    
  Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.  
### solution    
```    
  public class Codec {  
    public String serialize(TreeNode root) {  
          if (root == null) return "";  
          StringBuilder sb = new StringBuilder();  
          helper(root, sb);  
          return sb.substring(0, sb.length() - 1);  //去掉最后的一个逗号  
      }  
    
      private void helper(TreeNode root, StringBuilder sb) {  
          if (root == null) return;  
          //拼接当前节点  
          sb.append(root.val).append(",");  
          helper(root.left, sb);  
          helper(root.right, sb);  
      }  
    
      // Decodes your encoded data to tree.  
      public TreeNode deserialize(String data) {  
          if (data == null || data.length() == 0) return null;  
          String[] arr = data.split(",");  
          return builder(arr, 0, arr.length - 1);  
      }  
    
      private TreeNode builder(String[] arr, int lo, int hi) {  
          if (lo > hi) return null;  
          TreeNode root = new TreeNode(Integer.valueOf(arr[lo]));  
          //找到第一个比首元素大的元素位置  
          int index = hi + 1;  
          for (int i = lo + 1; i <= hi; i++) {  
              if (Integer.valueOf(arr[i]) > root.val) {  
                  index = i;  
                  break;  
              }  
          }  
          //递归构建子树  
          root.left = builder(arr, lo + 1, index - 1);  
          root.right = builder(arr, index, hi);  
          return root;  
      }  
  }  
```    
    
### 个人解读    
  因为是BST的序列化，所以比起平常的树的序列化，还要多一个关于关于数值比较的东西。  
  本题目算是为数不多的很实用的题目了。  
    
  首先对于树的遍历有很多种：前序、中序、后序、层次。  
  选择前序遍历的好处是，可以把字符串分成三段：中、左、右。  
    
    
tags:    
  -  重点模拟  
  -  重点题目  
