### description    
  Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.  
    
  In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix  
    
  Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.  
    
     
    
  Example 1:  
    
  Input: "/home/"  
  Output: "/home"  
  Explanation: Note that there is no trailing slash after the last directory name.  
  Example 2:  
    
  Input: "/../"  
  Output: "/"  
  Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.  
  Example 3:  
    
  Input: "/home//foo/"  
  Output: "/home/foo"  
  Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.  
  Example 4:  
    
  Input: "/a/./b/../../c/"  
  Output: "/c"  
  Example 5:  
    
  Input: "/a/../../b/../c//.//"  
  Output: "/c"  
  Example 6:  
    
  Input: "/a//b////c/d//././/.."  
  Output: "/a/b/c"  
    
  简化Unix路径  
### solution    
```    
Runtime: 4 ms, faster than 88.97% of Java online submissions for Simplify Path.  
Memory Usage: 36.9 MB, less than 99.59% of Java online submissions for Simplify Path.  
  
  
  class Solution {  
      public String simplifyPath(String path) {  
           String[] split = path.split("/");  
          Stack<String> stack = new Stack<>();  
          StringBuilder builder = new StringBuilder();  
          for(String s: split) {  
              if(".".equals(s) || "".equals(s)) {  
                  continue;  
              } if("..".equals(s)) {  
                  if(!stack.empty()) {  
                      stack.pop();  
                  }  
              } else {  
                  stack.push(s);  
              }  
          }  
            
            
         for(int i = 0; i < stack.size(); i++) {        //注意这里栈的获取方式  
              builder.append("/").append(stack.get(i));  
          }  
          return builder.length() == 0 ? "/" : builder.toString();  
      }  
  }  
    
```    
    
### 个人解读    
  使用split函数，然后因为会有删减，所以使用Stack，最后再转化成String。  
    
  反思：一开始没有注意到stack的方向反了。  
  联想Stack，Vector，辅助记忆正向获取栈元素的方法。  
    
tags:    
  -  栈  
  -  反向弹栈  
