### description    
  Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.  
    
  Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.  
    
  Example:   
    
  You may serialize the following tree:  
    
      1  
     / \  
    2   3  
       / \  
      4   5  
    
  as "[1,2,3,null,null,4,5]"  
  Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.  
    
  Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.  
### solution    
```    
// 方法一： 与leetcode的一致  
  
效率意外的还可以  
   public class Codec {  
    
          public String serialize(TreeNode root) {  
              if(root == null) {  
                  return "null";  
              }  
              Queue<TreeNode> queue = new LinkedList<>();  
              queue.offer(root);  
              StringBuilder builder = new StringBuilder();  
              while(!queue.isEmpty()) {  
                  int size = queue.size();  
                  for(int i = 0; i < size; i++) {  
                      TreeNode poll = queue.poll();  
                      if(poll == null) {  
                          builder.append(",null");  
                          continue;  
                      }  
                      if(builder.length() != 0) builder.append("," );  
                      builder.append(poll.val);  
                      queue.offer(poll.left);  
                      queue.offer(poll.right);  
                  }  
              }  
              int count = 0;  
              while(builder.charAt(builder.length() - 1 - count*5) == 'l') {  
                  count++;  
              }  
              return builder.substring(0, builder.length() - 5*count);  
          }  
    
          public TreeNode deserialize(String data) {  
              String[] words = data.split(",");  
              Integer[] arr = new Integer[words.length];  
              for(int i = 0; i < words.length; i++) {  
                  String w = words[i];  
                  if(!"null".equals(w)) {  
                      arr[i] = Integer.valueOf(w);  
                  }  
              }  
              return  transArrToNode(arr);  
          }  
    
          public TreeNode transArrToNode(Integer[] arr) {  
              if(arr[0] == null) return null;  
              Queue<TreeNode> queue = new LinkedList<>();  
              TreeNode root = new TreeNode(arr[0]);  
              queue.offer(root);  
              for (int i = 1; i < arr.length; i++) {  
                  TreeNode poll = queue.poll();  
                  if (arr[i] == null) {  
                      poll.left = null;  
                  } else {  
                      poll.left = new TreeNode(arr[i]);  
                      queue.offer(poll.left);  
                  }  
                  if (i == arr.length - 1) continue;  
                  if (arr[i + 1] == null) {  
                      poll.right = null;  
                  } else {  
                      poll.right = new TreeNode(arr[i + 1]);  
                      queue.offer(poll.right);  
                  }  
                  i++;  
              }  
              return root;  
          }  
    
      }  
```    
    
### 个人解读    
  树的序列化和反序列化，就采用之前为了方便测试写的Leetcode那个吧。  
    
tags:    
  -  树  
  -  序列化  
