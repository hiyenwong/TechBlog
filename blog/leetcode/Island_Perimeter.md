# Island Perimeter

You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

 

Example:

Input:
```
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]
```
Output: 16
```
Explanation: The perimeter is the 16 yellow stripes in the image below:

![image](https://github.com/hiyenwong/TechBlog/blob/master/blog/images/island.png)
```

# code
## java version1
```java
class Solution {
    public int islandPerimeter(int[][] grid) {
        
		if (grid == null || grid.length == 0)
			return 0;
		// 二维数组1的个数
		int numOf1 = 0;
		// 重复边的个数
		int repeat = 0;
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++)
				if (grid[i][j] == 1) {
					numOf1++;
					// 上下左右是否为1
					if (i - 1 >= 0 && grid[i - 1][j] == 1)
						repeat++;
					if (i + 1 < grid.length && grid[i + 1][j] == 1)
						repeat++;
					if (j - 1 >= 0 && grid[i][j - 1] == 1)
						repeat++;
					if (j + 1 < grid[0].length && grid[i][j + 1] == 1)
						repeat++;
 
				}
		return numOf1 * 4 - repeat;
    }
}
```

## java version2:
```java
class Solution {
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int per = 0;
        for(int i = 0;i < grid.length;i++) {
            for (int j = 0;j < grid[0].length;j++) {
                if (grid[i][j] == 1) {
                    per += 4;
                    if (i < grid.length - 1 && grid[i + 1][j] == 1)
                        per -= 2;
                    if (j < grid[0].length - 1 && grid[i][j + 1] == 1)
                        per -= 2;
                }
            }
        }
        return per;
    }
}
```