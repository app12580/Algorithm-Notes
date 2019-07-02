### curLen 
参考(830)  
对于curLen有两种方式：  
用for循环遍历，然后当cur!=pre的时候触发；  
外层用while，然后里面每次走到cur的尽头。  
模板:  
```
class Solution {
       public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i < S.length()) {
            i = j;
            while(j < S.length() && S.charAt(i) == S.charAt(j)) j++;
            if(j - i >= 3) {
                res.add(new ArrayList<>(Arrays.asList(i, j - 1)));
            }
        }
        return res;
    }
}
```
或者
```
   public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0, j = 0; i < S.length(); i = j) {
            while (j < S.length() && S.charAt(j) == S.charAt(i)) ++j;
            if (j - i >= 3)
                res.add(Arrays.asList(i, j - 1));
        }
        return res;
    }
```

  