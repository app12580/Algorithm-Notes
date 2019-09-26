### description    
  We are given a binary tree (with root node root), a target node, and an integer value K.  
    
  Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.  
    
     
    
  Example 1:  
    
  Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2  
    
  Output: [7,4,1]  
    
  Explanation:   
  The nodes that are a distance 2 from the target node (with value 5)  
  have values 7, 4, and 1.  
    
    
    
  Note that the inputs "root" and "target" are actually TreeNodes.  
  The descriptions of the inputs above are just serializations of these objects.  
     
    
  Note:  
    
  The given tree is non-empty.  
  Each node in the tree has unique values 0 <= node.val <= 500.  
  The target node is a node in the tree.  
  0 <= K <= 1000.  
### solution    
```    
  //方法一  
   Runtime: 1 ms, faster than 100.00% of Java online submissions for All Nodes Distance K in Binary Tree.  
   Memory Usage: 37 MB, less than 94.74% of Java online submissions for All Nodes Distance K in Binary Tree.  
     
  class Solution {  
      Map<TreeNode, Integer> map = new HashMap<>();  
    
      public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {  
          List<Integer> res = new LinkedList<>();  
          find(root, target);  
          dfs(root,  K, map.get(root), res);  
          return res;  
      }  
    
      // find target node first and store the distance in that path that we could use it later directly  
      // 能找到的会赋值，找不到的会用-1替代  
      private int find(TreeNode root, TreeNode target) {  
          if (root == null) return -1;  
          if (root == target) {  
              map.put(root, 0);  
              return 0;  
          }  
          int left = find(root.left, target);  
          if (left >= 0) {  
              map.put(root, left + 1);  
              return left + 1;  
          }  
          int right = find(root.right, target);  
          if (right >= 0) {  
              map.put(root, right + 1);  
              return right + 1;  
          }  
          return -1;  
      }  
    
      private void dfs(TreeNode root,  int K, int length, List<Integer> res) {  
          if (root == null) return;  
          if (map.containsKey(root)) length = map.get(root);  
          if (length == K) res.add(root.val);  
          dfs(root.left,  K, length + 1, res);  
          dfs(root.right,  K, length + 1, res);  
      }  
    
  }  
    
    
  // 错误方法  
   public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {  
          List<Integer> list = new ArrayList<>();  
          if (root == null) return list;  
          if (K == 0) {  
              list.add(target.val);  
              return list;  
          }  
          List<TreeNode> parents = new ArrayList<>();  
          dfs(parents, root, target);  
          int height = parents.size();  
          list.addAll(find(target, K, null));  
          if(root == target) {  
    
          } else if(K <= height - 1) {  
              //说明在parents列表上面去直接获取  
              list.add(parents.get(height - K - 1).val);  
          } else {  
              list.addAll(find(root, K - height + 1, parents));  
          }  
          return list;  
      }  
    
      private List<Integer> find(TreeNode node, int k, List<TreeNode> excludeList) {  
          TreeNode ex = null;  
          if(excludeList != null && k < excludeList.size()) {  
              ex = excludeList.get(k);  
          }  
          int height = 0;  
          Queue<TreeNode> queue = new LinkedList<>();  
          queue.offer(node);  
          List<Integer> res = new ArrayList<>();  
          while(height < k && !queue.isEmpty()) {  
              int size = queue.size();  
              for(int i = 0; i < size; i++) {  
                  TreeNode poll = queue.poll();  
                  if(poll.left != null) {  
                      queue.offer(poll.left);  
                  }  
                  if(poll.right != null) {  
                      queue.offer(poll.right);  
                  }  
              }  
              height++;  
          }  
          while(!queue.isEmpty()) {  
              TreeNode poll = queue.poll();  
              if(excludeList != null && excludeList.contains(poll)) continue;  
              res.add(poll.val);  
          }  
          return res;  
      }  
    
      private boolean dfs(List<TreeNode> parents, TreeNode root, TreeNode target) {  
          if (root == target) {  
              parents.add(root);  
              return true;  
          }  
          parents.add(root);  
          if (root.left != null && dfs(parents, root.left, target)) {  
              return true;  
          }  
          if (root.right != null && dfs(parents, root.right, target)) {  
              return true;  
          }  
          parents.remove(parents.size() - 1);  
          return false;  
      }  
  
```    
    
### 个人解读    
  这种题目第一次看到时候，会觉得很棘手，因为遍历到目标以后，还需要再次遍历，因为目标的相关参数是承上启下的，然后想到两遍遍历，问题就差不多了。  
    
  反思：  
  总是在想，递归到目标时候，会丢失掉以前的信息，然而除了通过常态标记法，还有一个办法，就是通过递归，递归到目标时，前面的值并没有完结，而是等到目标以后才会返回结果。  
    
tags:    
  -  树   
  -  递归   
  -  DFS   
