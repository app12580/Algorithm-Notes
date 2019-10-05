### description    
  A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.  
    
  Write a data structure CBTInserter that is initialized with a complete binary tree and supports the following operations:  
    
  CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;  
  CBTInserter.insert(int v) will insert a TreeNode into the tree with value node.val = v so that the tree remains complete, and returns the value of the parent of the inserted TreeNode;  
  CBTInserter.get_root() will return the head node of the tree.  
     
    
  Example 1:  
    
  Input: inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]  
  Output: [null,1,[1,2]]  
  Example 2:  
    
  Input: inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]  
  Output: [null,3,4,[1,2,3,4,5,6,7,8]]  
     
    
  Note:  
    
  The initial given tree is complete and contains between 1 and 1000 nodes.  
  CBTInserter.insert is called at most 10000 times per test case.  
  Every value of a given or inserted node is between 0 and 5000.  
### solution    
```    
//方法一： 流水账  
Runtime: 77 ms, faster than 5.22% of Java online submissions for Complete Binary Tree Inserter.  
Memory Usage: 43.6 MB, less than 80.00% of Java online submissions for Complete Binary Tree Inserter.  
    
class CBTInserter {  
  
    Queue<TreeNode> queue = new LinkedList<>();  
    private TreeNode root;  
    public CBTInserter(TreeNode root) {  
        this.root = root;  
        queue.offer(root);  
        boolean flag = true;  
        while(flag && !queue.isEmpty()) {  
            int size = queue.size();  
            for(int i = 0; i < size; i++) {  
                TreeNode poll = queue.peek();  
                if(poll.left == null) {  
                    flag = false;  
                    break;  
                }  
                queue.offer(poll.left);  
                if(poll.right == null) {  
                    flag = false;  
                    break;  
                }  
                queue.poll();  
                queue.offer(poll.right);  
            }  
        }  
    }  
  
    public int insert(int v) {  
        TreeNode peek = queue.peek();  
        TreeNode cur = new TreeNode(v);  
        if(peek.left == null) {  
            peek.left = cur;  
        } else {  
            peek.right = cur;  
            queue.poll();  
        }  
        queue.offer(cur);  
        return peek.val;  
    }  
  
    public TreeNode get_root() {  
        return root;  
    }  
}  
  
//方法二： 优化， 利用树的坐标法  
  
class CBTInserter {  
    List<TreeNode> tree;  
    public CBTInserter(TreeNode root) {  
        tree = new ArrayList<>();  
        tree.add(root);  
        for (int i = 0; i < tree.size(); ++i) {  
            if (tree.get(i).left != null) tree.add(tree.get(i).left);  
            if (tree.get(i).right != null) tree.add(tree.get(i).right);  
        }  
    }  
  
    public int insert(int v) {  
        int N = tree.size();  
        TreeNode node = new TreeNode(v);  
        tree.add(node);  
        if (N % 2 == 1)  
            tree.get((N - 1) / 2).left = node;  
        else  
            tree.get((N - 1) / 2).right = node;  
        return tree.get((N - 1) / 2).val;  
    }  
  
    public TreeNode get_root() {  
        return tree.get(0);  
    }  
}  
  
```    
    
### 个人解读    
  流水账，使用queue进行遍历。  
    
  方法二的优化：利用的树的坐标法，可以快速定位父节点。  
  同时使用一个List存放所有节点，可以避免来回删减的开销。  
    
tags:    
  -  完全二叉树  
  -  树的坐标  
