### description    
  Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.  
    
  Two trees are duplicate if they have the same structure with same node values.  
    
  Example 1:  
    
          1  
         / \  
        2   3  
       /   / \  
      4   2   4  
         /  
        4  
  The following are two duplicate subtrees:  
    
        2  
       /  
      4  
  and  
    
      4  
  Therefore, you need to return above trees' root in the form of a list.  
### solution    
```    
    
class Solution {  
   public List<TreeNode> findDuplicateSubtrees(TreeNode root) {  
    List<TreeNode> res = new LinkedList<>();  
    postorder(root, new HashMap<>(), res);  
    return res;  
}  
  
public String postorder(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {  
    if (cur == null) return "#";    
    String serial = cur.val + "," + postorder(cur.left, map, res) + "," + postorder(cur.right, map, res);  
    if (map.getOrDefault(serial, 0) == 1) res.add(cur);  
    map.put(serial, map.getOrDefault(serial, 0) + 1);  
    return serial;  
}  
}  
```    
    
### 个人解读    
    
  思路一：从上往下遍历，每次用一个字符串保存上面节点的信息，这样运行到头以后，就能知道所有子树的特征，然后通过一个Map<特征, 节点>进行存储  
  不太靠谱啊，因为遍历到子节点时候是单向的，并不知道其他支线的情况。  
    
  但是通过节点序列化的结果来判断还是可行的。  
    
tags:    
  -  树  
  -  DFS  
  -  树的特征值  
