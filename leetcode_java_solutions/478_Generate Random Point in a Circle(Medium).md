### description    
  Given the radius and x-y positions of the center of a circle, write a function randPoint which generates a uniform random point in the circle.  
    
  Note:  
    
  input and output values are in floating-point.  
  radius and x-y position of the center of the circle is passed into the class constructor.  
  a point on the circumference of the circle is considered to be in the circle.  
  randPoint returns a size 2 array containing x-position and y-position of the random point, in that order.  
  Example 1:  
    
  Input:   
  ["Solution","randPoint","randPoint","randPoint"]  
  [[1,0,0],[],[],[]]  
  Output: [null,[-0.72939,-0.65505],[-0.78502,-0.28626],[-0.83119,-0.19803]]  
  Example 2:  
    
  Input:   
  ["Solution","randPoint","randPoint","randPoint"]  
  [[10,5,-7.5],[],[],[]]  
  Output: [null,[11.52438,-8.33273],[2.46992,-16.21705],[11.13430,-12.42337]]  
  Explanation of Input Syntax:  
    
  The input is two lists: the subroutines called and their arguments. Solution's constructor has three arguments, the radius, x-position of the center, and y-position of the center of the circle. randPoint has no arguments. Arguments are always wrapped with a list, even if there aren't any.  
### solution    
```    
  class Solution {  
       private double x;  
      private double y;  
      private double radius;  
      private Random random;  
    
      public Solution(double radius, double x_center, double y_center) {  
          this.x = x_center;  
          this.y = y_center;  
          this.radius = radius;  
          this.random = new Random();  
      }  
    
      public double[] randPoint() {  
          double angle = random.nextDouble() * 2 * Math.PI;  
          double sin = Math.sin(angle);  
          double cos = Math.cos(angle);  
          double r = Math.sqrt(random.nextDouble()) * radius;  
          return new double[]{x + r * cos, y + r * sin};  
      }  
  }  
```    
    
### 个人解读    
  又TM的是随机数，然而这次变成了随机的浮点数。  
    
  分析本题，圆心坐标并不关键。在圆心内部的话，需要两个数的平方和小于半径的平方和。  
    
  思路一：先找出来一个数字A，然后在R*R-A中再找一个随机数。问题是这样能否保证"完全随机"。  
  感觉是阔以的。  
  然而却报错。。。  
  反思以后知道问题在哪了，本题目要求的应该是根据面积划分概率。然而按照思路一的算法，不同的x获取的概率是一样子的，这点不合理，因为有的x上的y会更多。  
    
  ```  
  错误算法  
  class Solution {  
        
      private double x;  
      private double y;  
      private double sum;  
      private Random random;  
        
      public Solution(double radius, double x_center, double y_center) {  
          this.x = x_center;  
          this.y = y_center;  
          this.sum = radius *radius;  
          this.random = new Random();  
      }  
    
      public double[] randPoint() {  
          double xx = random.nextDouble() * sum;  
          double yy = random.nextDouble() * (sum - xx);  
          double x1 = Math.sqrt(xx);  
          double y1 = Math.sqrt(yy);  
          return new double[]{x + x1, y + y1};  
      }  
        
  }  
  ```  
    
  思路二：  
  通过角坐标去获取点。  
  不知道为什么还是不通过。。。  
  ```  
  class Solution {  
       private double x;  
      private double y;  
      private double radius;  
      private Random random;  
    
      public Solution(double radius, double x_center, double y_center) {  
          this.x = x_center;  
          this.y = y_center;  
          this.radius = radius;  
          this.random = new Random();  
      }  
    
      public double[] randPoint() {  
          double angle = random.nextDouble() * 2 * Math.PI;  
          double sin = Math.sin(angle);  
          double cos = Math.cos(angle);  
          double r = random.nextDouble() * radius;  
          //只需要替换成下面这一行即可  
          //简单解释：外层的随机数是面积，经过sqrt后变成了半径  
          //  double r = Math.sqrt(random.nextDouble()) * radius;    
  
          return new double[]{x + r * cos, y + r * sin};  
      }  
  }  
  ```  
  反思了一下，问题还是一样子的。。。不同的半径的几率不应该是相同的，越远的应该比重越大。  
    
    
tags:    
  -  随机数  
  -  多维随机数  
