### description    
  There are some trees, where each tree is represented by (x,y) coordinate in a two-dimensional garden. Your job is to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed. Your task is to help find the coordinates of trees which are exactly located on the fence perimeter.  
    
     
    
  Example 1:  
    
  Input: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]  
  Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]  
  Explanation:  
    
  Example 2:  
    
  Input: [[1,2],[2,2],[4,2]]  
  Output: [[1,2],[2,2],[4,2]]  
  Explanation:  
    
  Even you only have trees in a line, you need to use rope to enclose them.   
     
    
  Note:  
    
  All trees should be enclosed together. You cannot cut the rope to enclose trees that will separate them in more than one group.  
  All input integers will range from 0 to 100.  
  The garden has at least one tree.  
  All coordinates are distinct.  
  Input points have NO order. No order required for output.  
  input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.  
### solution    
```    
Runtime: 27 ms, faster than 39.75% of Java online submissions for Erect the Fence.
Memory Usage: 47.5 MB, less than 100.00% of Java online submissions for Erect the Fence.

    class Solution {
       class Point{
            int x;
            int y;
            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    
        public int orientation(Point p, Point q, Point r) {
            return (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        }
        public int distance(Point p, Point q) {
            return (p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y);
        }
        private static Point bottomLeft(Point[] points) {
            Point bottomLeft = points[0];
            for (Point p: points)
                if (p.y < bottomLeft.y)
                    bottomLeft = p;
            return bottomLeft;
        }
        public int[][] outerTrees(int[][] p) {
            Point[] points = new Point[p.length];
            for(int i = 0; i < p.length; i++) {
                points[i] = new Point(p[i][0], p[i][1]);
            }
            if (points.length <= 1)
                return p;
            Point bm = bottomLeft(points);
            Arrays.sort(points, new Comparator < Point > () {
                public int compare(Point p, Point q) {
                    double diff = orientation(bm, p, q) - orientation(bm, q, p);
                    if (diff == 0)
                        return distance(bm, p) - distance(bm, q);
                    else
                        return diff > 0 ? 1 : -1;
                }
            });
            int i = points.length - 1;
            while (i >= 0 && orientation(bm, points[points.length - 1], points[i]) == 0)
                i--;
            for (int l = i + 1, h = points.length - 1; l < h; l++, h--) {
                Point temp = points[l];
                points[l] = points[h];
                points[h] = temp;
            }
            Stack < Point > stack = new Stack < > ();
            stack.push(points[0]);
            stack.push(points[1]);
            for (int j = 2; j < points.length; j++) {
                Point top = stack.pop();
                while (orientation(stack.peek(), top, points[j]) > 0)
                    top = stack.pop();
                stack.push(top);
                stack.push(points[j]);
            }
             int[][] res = new int[stack.size()][2];
            int size = stack.size();
            for(int k = 0;  k< size; k++) {
                Point pop = stack.pop();
                res[k][0] = pop.x;
                res[k][1] = pop.y;
            }
            return res;
        }
    }
```    
    
### 个人解读    
  很绝望，不知道如何描述网格中的定位问题。。  
    
  凸包问题  
  lzzscl，中文博客互相抄，嘴上说着什么三点连线的方向，不明所以的直线L，这帮人脑子有坑么。。。  
  文字描述混乱，文不对题，代码关键点没在文章说出来，配图也瞎JB配，中文博客怎么垃圾这么多。。  
    
  唯一来说相对靠谱的博客。。。  
  https://blog.csdn.net/u013377068/article/details/80095620  
    
  关键点：  
  1、不是市面上那些瞎抄的博客什么直线L的左边右边  
  2、与P0关系不大，只在第一次才有用  
  3、关键点：每条凸包的边都是向左拐的。 需要比较的是和栈里面的前一条边，而不会什么和P0的连线。  
    
     
  最靠谱的教程还是要看leetcode的题解里面  
  https://leetcode-cn.com/problems/erect-the-fence/solution/an-zhuang-zha-lan-by-leetcode/   
    
  有一个很关键的知识点，在坐标系里面，用叉乘来描述两个向量的相对位置关系。  
    
    
tags:    
  -  网格区域  
  -  图形学  
  -  凸包问题  
  -  了解  
