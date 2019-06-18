### description    
  Given a collection of numbers that might contain duplicates, return all possible unique permutations.  
    
  Example:  
    
  Input: [1,1,2]  
  Output:  
  [  
    [1,1,2],  
    [1,2,1],  
    [2,1,1]  
  ]  
    
  带重复元素的排序问题。  
### solution    
```    
  class Solution {  
     public List<List<Integer>> permuteUnique(int[] nums) {  
          List<List<Integer>> res = new ArrayList<>();  
          if(nums == null || nums.length == 0) {  
              return res;  
          }  
          Arrays.sort(nums);  
          boolean[] mark = new boolean[nums.length];  
          dfs(new ArrayList<Integer>(), res, nums, mark);  
          return res;  
      }  
    
      private void dfs(ArrayList<Integer> curList, List<List<Integer>> res, int[] nums, boolean[] mark) {  
          if(curList.size() == nums.length) {  
              res.add(copyList(curList));  
              return;  
          }  
          for(int i = 0; i < mark.length; i++) {  
              if(i > 0 && nums[i] == nums[i-1] && !mark[i-1]) {  
                  continue;  
              }  
              if(!mark[i]) {  
                  mark[i] = true;  
                  curList.add(nums[i]);  
                  dfs(curList, res, nums, mark);  
                  mark[i] = false;  
                  curList.remove(curList.size() - 1);  
              }  
          }  
      }  
    
      private List<Integer> copyList(ArrayList<Integer> src){  
          List<Integer> copy = new ArrayList();  
          copy.addAll(src);  
          return copy;  
      }  
  }  
```    
    
### 个人解读    
  防止重复的做法： 湮灭法用不上，标记法要标记多个，数学法也许靠谱一点。  
  [1,1,2]  
    
  1 1 2  
  1 2 1  
  ...跳过了第二个1  
  2 1 1 //第一个是2，然后跳过了第二个1  
    
  结论：  
  数组元素可能含有相同的元素，进行排列时就有可能出现重复的排列，要求重复的排列只返回一个。  
  在实现上，和 Permutations 不同的是要先排序，然后在添加一个元素时，判断这个元素是否等于前一个元素，如果等于，并且前一个元素还未访问，那么就跳过这个元素。  
    
    
    
tags:    
  -  DFS    
  -  回溯    
  -  排列    
