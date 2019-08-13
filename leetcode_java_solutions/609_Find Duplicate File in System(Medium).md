### description    
  Given a list of directory info including directory path, and all the files with contents in this directory, you need to find out all the groups of duplicate files in the file system in terms of their paths.  
    
  A group of duplicate files consists of at least two files that have exactly the same content.  
    
  A single directory info string in the input list has the following format:  
    
  "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"  
    
  It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content, respectively) in directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.  
    
  The output is a list of group of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:  
    
  "directory_path/file_name.txt"  
    
  Example 1:  
    
  Input:  
  ["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]  
  Output:    
  [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]  
     
    
  Note:  
    
  No order is required for the final output.  
  You may assume the directory name, file name and file content only has letters and digits, and the length of file content is in the range of [1,50].  
  The number of files given is in the range of [1,20000].  
  You may assume no files or directories share the same name in the same directory.  
  You may assume each given directory info represents a unique directory. Directory path and file info are separated by a single blank space.  
     
    
  Follow-up beyond contest:  
  Imagine you are given a real file system, how will you search files? DFS or BFS?  
  If the file content is very large (GB level), how will you modify your solution?  
  If you can only read the file by 1kb each time, how will you modify your solution?  
  What is the time complexity of your modified solution? What is the most time-consuming part and memory consuming part of it? How to optimize?  
  How to make sure the duplicated files you find are not false positive?  
### solution    
```    
Runtime: 43 ms, faster than 40.87% of Java online submissions for Find Duplicate File in System.  
Memory Usage: 52.3 MB, less than 93.75% of Java online submissions for Find Duplicate File in System.  
  
class Solution {  
        public List<List<String>> findDuplicate(String[] paths) {  
        List<List<String>> res = new ArrayList<>();  
        if(paths.length == 0) return res;  
        Map<String, Set<String>> map = new HashMap<>(); //key是文件内容 value是文件路径  
        for(String path: paths) {  
            String[] strs = path.split("\\s+");  
            String p = strs[0];  
            for(int i = 1; i < strs.length; i++) {  
                String cc = strs[i];  
                int idx = cc.indexOf("(");  
                String content = cc.substring(idx);  
                String filename = p + "/" + cc.substring(0, idx);  
                Set<String> set = map.getOrDefault(content, new HashSet<>());  
                set.add(filename);  
                map.put(content, set);  
            }  
        }  
        for(Set<String> set: map.values()) {  
            if(set.size() > 1) {  
                res.add(new ArrayList<>(set));  
            }  
        }  
        return res;  
        }  
}  
```    
    
### 个人解读    
  根据内容来划分文件。  
  使用Hash表来存储，value为一个Set，key为文件内容，根据set的size()判断，然后把set内容返回  
  流水账问题。  
    
tags:    
  -   Hash表  
  -   流水账  
