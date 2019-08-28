### description    
  Print a binary tree in an m*n 2D string array following these rules:  
    
  The row number m should be equal to the height of the given binary tree.  
  The column number n should always be an odd number.  
  The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.  
  Each unused space should contain an empty string "".  
  Print the subtrees following the same rules.  
  Example 1:  
  Input:  
       1  
      /  
     2  
  Output:  
  [["", "1", ""],  
   ["2", "", ""]]  
  Example 2:  
  Input:  
       1  
      / \  
     2   3  
      \  
       4  
  Output:  
  [["", "", "", "1", "", "", ""],  
   ["", "2", "", "", "", "3", ""],  
   ["", "", "4", "", "", "", ""]]  
  Example 3:  
  Input:  
        1  
       / \  
      2   5  
     /   
    3   
   /   
  4   
  Output:  
    
  [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]  
   ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]  
   ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]  
   ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]  
  Note: The height of binary tree is in the range of [1, 10].  
### solution    
```    
Runtime: 1 ms, faster than 100.00% of Java online submissions for Print Binary Tree.  
Memory Usage: 36.3 MB, less than 100.00% of Java online submissions for Print Binary Tree.  
  
  class Solution {  
    public List<List<String>> printTree(TreeNode root) {  
      List<List<String>> res = new LinkedList<>();  
      int height = root == null ? 1 : getHeight(root);  
      int rows = height, columns = (int) (Math.pow(2, height) - 1);  
      List<String> row = new ArrayList<>();  
      for(int i = 0; i < columns; i++)  row.add("");  
      for(int i = 0; i < rows; i++)  res.add(new ArrayList<>(row));  
      populateRes(root, res, 0, rows, 0, columns - 1);  
      return res;  
  }  
    
  public void populateRes(TreeNode root, List<List<String>> res, int row, int totalRows, int i, int j) {  
      if (row == totalRows || root == null) return;  
      res.get(row).set((i+j)/2, Integer.toString(root.val));  
      populateRes(root.left, res, row+1, totalRows, i, (i+j)/2 - 1);  
      populateRes(root.right, res, row+1, totalRows, (i+j)/2+1, j);  
  }  
    
  public int getHeight(TreeNode root) {  
       if (root == null) return 0;  
       return 1 + Math.max(getHeight(root.left), getHeight(root.right));  
  }  
  }  
```    
    
### 个人解读    
  因为父节点必须要知道子节点的情况，而子节点也需要知道父节点的位置。  
  所以思路是两轮遍历，第一次遍历获取宽度，第二次遍历获取打印内容。  
    
  但是问题是，获取的宽度一般是通过递归获得的。  
    
  反思以后发现，宽度是有规律的，固定只和树的总层数有关。  
    
  总结：  
  1、需要两轮遍历，第一次遍历获取整个树的高度  
  2、需要注意，获取树的高度只需要递归即可，不需要层次遍历  
  3、list.set()方法  
    
tags:    
  -  树  
  -  递归  
  -  索引定位  
