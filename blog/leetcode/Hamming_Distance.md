# Hamming Distance

The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:
```
Input: x = 1, y = 4
```
Output: 2
```
Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
```
The above arrows point to positions where the corresponding bits are different.

```java 
class Solution {
    public int hammingDistance(int x, int y) {
        // String x1=Integer.toBinaryString(x);
        // String y1=Integer.toBinaryString(y);
        int tem=x^y;
        int count=0;
        String str=Integer.toBinaryString(tem);
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='1'){
                count++;
            }
        }
        // System.out.println(count);
         
        return count;
    }
}
```