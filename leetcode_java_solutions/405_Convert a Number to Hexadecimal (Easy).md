### description    
  Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.  
    
  Note:  
    
  All letters in hexadecimal (a-f) must be in lowercase.  
  The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.  
  The given number is guaranteed to fit within the range of a 32-bit signed integer.  
  You must not use any method provided by the library which converts/formats the number to hex directly.  
  Example 1:  
    
  Input:  
  26  
    
  Output:  
  "1a"  
  Example 2:  
    
  Input:  
  -1  
    
  Output:  
  "ffffffff"
### solution  
```  
  public String toHex(int num) {
      char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
      if (num == 0) return "0";
      StringBuilder sb = new StringBuilder();
      while (num != 0) {
          sb.append(map[num & 0b1111]); //看看后四位有哪些东西
          num >>>= 4; // 因为考虑的是补码形式，因此符号位就不能有特殊的意义，需要使用无符号右移，左边填 0
      }
      return sb.reverse().toString();
  }
```  
  
### 个人解读  
  其他的进制转换往往是-1转换后还是-1，而本题直接是ffffffff  
  所以本题不是一般的进制转换，而是负数找到它的补码，正数正常转换。  
  正数和负数，尽管规则不同，但是仍然能不通过if判断，直接用同一个方法计算，关键在于正数的最高为是0，把末几位非0的处理完以后，直接就能通过!=0这个条件终止了  
  
tags:  
  -  位运算  
