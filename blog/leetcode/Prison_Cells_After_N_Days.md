# Prison Cells After N Days

There are 8 prison cells in a row, and each cell is either occupied or vacant.

Each day, whether the cell is occupied or vacant changes according to the following rules:

If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
Otherwise, it becomes vacant.
(Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)

We describe the current state of the prison in the following way: `cells[i] == 1` if the i-th cell is occupied, else cells[i] == 0.

Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)

 

Example 1:
```
Input: cells = [0,1,0,1,1,0,0,1], N = 7
Output: [0,0,1,1,0,0,0,0]
Explanation: 
The following table summarizes the state of the prison on each day:
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
```

Example 2:
```
Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
Output: [0,0,1,1,1,1,1,0]
```

Note:
```
cells.length == 8
cells[i] is in {0, 1}
1 <= N <= 10^9
```


# code
## python:
``` python
class Solution(object):
    def prisonAfterNDays(self, oldcells, N):
        """
        :type cells: List[int]
        :type N: int
        :rtype: List[int]
        """
        cells = copy.deepcopy(oldcells)
        count = 0
        N %= 14
        if N == 0:
            N = 14
        while count < N:
            newCell = [0] * 8
            for i in range(1, 7):
                if cells[i - 1] == cells[i + 1]:
                    newCell[i] = 1
                else:
                    newCell[i] = 0
            cells = newCell
            count += 1
        return cells
```
## java
### version 1
``` java
class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        if(cells == null || cells.length == 0 || N <= 0){
            return cells;
        }

        int n = cells.length;
        for(int k = (N - 1) % (2 * (n - 1)) + 1; k > 0; k--){
            int [] temp = new int[n];
            for(int i = 1; i < n - 1; i++){
                if(cells[i - 1] == cells[i + 1]){
                    temp[i] = 1;
                }
            }

            cells = temp;
        }

        return cells;
    }
}
```
```java
class Solution {
     public int[] prisonAfterNDays(int[] cells, int N) {
        int prevLeft=0;
        N = N%14 == 0 ? 14 : N%14;
        while(N > 0){
            for(int i=0; i<cells.length-1; i++){
                if(i == 0){
                    prevLeft = cells[i]; 
                    cells[i] = 0;
                } else {
                    if(prevLeft == cells[i+1]){
                        prevLeft = cells[i]; 
                        cells[i] = 1;
                    } else {
                        prevLeft = cells[i]; 
                        cells[i] = 0;
                    }
                    if(i == cells.length-2){
                        cells[cells.length-1] = 0;
                    }
                }
            }
            N--;
        }
        return cells;
    }
}
```