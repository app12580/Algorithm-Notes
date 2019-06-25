### description    
  A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).  
    
  Each LED represents a zero or one, with the least significant bit on the right.  
    
    
  For example, the above binary watch reads "3:25".  
    
  Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.  
    
  Example:  
    
  Input: n = 1  
  Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]  
  Note:  
  The order of output does not matter.  
  The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".  
  The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".  
### solution    
```    
  // 双过程List的dfs  
  class Solution {  
        
      private int[] minArr = {1,2,4,8,16,32};  
      private int[] hourArr = {1,2,4,8};  
        
     public List<String> readBinaryWatch(int num) {  
          List<String> res = new ArrayList<>();  
          if(num < 0 || num > 10){  
              return res;  
          }  
          if(num == 0) {  
              res.add("0:00");  
              return res;  
          }  
          List<Integer> minList = new ArrayList<>();  
          List<Integer> hourList = new ArrayList<>();  
          dfs(minList, hourList, res, 0, 0, num);  
         Collections.sort(res);  
    
          return res;  
      }  
    
       private void dfs(List<Integer> minList, List<Integer> hourList, List<String> res, int min, int hour, int num) {  
          if(minList.size() + hourList.size() == num) {  
              int hourSum = 0;  
              int minSum = 0;  
              for(int m: minList) {  
                  minSum += minArr[m];  
              }  
              for(int h: hourList) {  
                  hourSum += hourArr[h];  
              }  
              if(hourSum > 11 || minSum > 59) return;  
              StringBuilder builder = new StringBuilder();  
              builder.append(hourSum).append(":");  
              if(minSum < 10) {  
                  builder.append("0");  
              }  
              builder.append(minSum);  
              res.add(builder.toString());  
              return;  
          }  
          //if(min > 5 || hour > 3) return;  
    
         if(hour == 0) {  
              for(int i = min; i <= 5; i++) {  
                  minList.add(i);  
                  dfs(minList, hourList, res, i +1, hour, num);  
                  minList.remove(minList.size() - 1);  
              }  
          }  
          for(int i = hour; i <= 3; i++) {  
              hourList.add(i);  
              dfs(minList, hourList, res, min, i +1, num);  
              hourList.remove(hourList.size() - 1);  
          }  
      }  
  }  
    
  // 预处理过  
  class Solution {  
      String[][] hour = {{"0"},  
              {"1", "2", "4", "8"},  
              {"3", "5", "6", "9", "10"},  
              {"7", "11"}};  
      String[][] minute = {{"00"},  
              {"01", "02", "04", "08", "16", "32"},  
              {"03", "05", "06", "09", "10", "12", "17", "18", "20", "24", "33", "34", "36", "40", "48"},  
              {"07", "11", "13", "14", "19", "21", "22", "25", "26", "28", "35", "37", "38", "41", "42", "44", "49", "50", "52", "56"},  
              {"15", "23", "27", "29", "30", "39", "43", "45", "46", "51", "53", "54", "57", "58"},  
              {"31", "47", "55", "59"}};  
    
      public List<String> readBinaryWatch(int num) {  
          List<String> ret = new ArrayList();  
          for (int i = 0; i <= 3 && i <= num; i++) {  
              if (num - i <= 5) {  
                  for (String str1 : hour[i]) {  
                      for (String str2 : minute[num - i]) {  
                          ret.add(str1 + ":" + str2);  
                      }  
                  }  
              }  
          }  
          return ret;  
      }  
  }  
```    
    
### 个人解读    
  标准的dfs，但是需要双个list，注意为了防止重复，需要if(hour == 0) 判断。  
    
tags:    
  -  dfs  
  -  双中间Listdfs  
