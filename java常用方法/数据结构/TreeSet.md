### TreeSet  
  常用方法  
```  
   @Test  
    public void test3() {  
  
        TreeSet<Integer> set = new TreeSet<>();  
        set.add(1);  
        set.add(10);  
        set.add(3);  
        set.add(123);  
        set.add(-1);  
  
        System.out.println(set);  
        System.out.println(set.first());  
        System.out.println(set.ceiling(2));  
        System.out.println(set.floor(2));  
        System.out.println(set.ceiling(5));  
        System.out.println(set.floor(5));  
        System.out.println(set.pollFirst());  
        System.out.println(set);  
        System.out.println(set.tailSet());  
    }  
  
  
  
```  
  
