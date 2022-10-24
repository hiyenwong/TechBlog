# Ugly Number II

## Solution

Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

Example:
```
Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
```
Note:  
```
1 is typically treated as an ugly number.
n does not exceed 1690.
```

## code
### java:
#### version 1:
```java
class Solution {
 static final int maxN = 1690;
    public int nthUglyNumber(int n) {
        return scan(n);
        // return heap(n);
    }

    public int scan(int n){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int i2 = 0, i3 = 0, i5 = 0;
        while (list.size() < n){
            int x2 = list.get(i2) * 2;
            int x3 = list.get(i3) * 3;
            int x5 = list.get(i5) * 5;

            int next =  Math.min(x2, Math.min(x3, x5));
            list.add(next);

            if (next == x2) i2++;
            if (next == x3) i3++;
            if (next == x5) i5++;
        }
        return list.get(n-1);
    }

    public int heap(int n){
        Queue<Long> queue = new PriorityQueue<>(); 
        Set<Long> set = new HashSet<>();
        add(queue, set, 1L);
        while (!queue.isEmpty()){
            long top = queue.poll();
            if (--n == 0) return (int)top;
            add(queue, set, top * 2);
            add(queue, set, top * 3);
            add(queue, set, top * 5);
        }
        return 0;
    }

    private void add(Queue<Long> queue, Set<Long> set, long x){
        if (!set.contains(x)){
            set.add(x);
            queue.offer(x);
        }
    }

    public int dpTLE(int n){
        if (n == 1) return 1;
        List<Boolean> f = new ArrayList<>();
        f.add(false);
        f.add(true);
        for (int i = 2; n > 1 ; i++){
            f.add(false);
            if (i % 2 == 0) f.set(i, f.get(i) || f.get(i /2));
            if (i % 3 == 0) f.set(i, f.get(i) || f.get(i /3));
            if (i % 5 == 0) f.set(i, f.get(i) || f.get(i /5));
            if (f.get(i))  n--;
            if (n == 1) return i;
        }
        return 1;
    }
}
```
#### version 2:
```java
class Solution {
  
    public static Ugly ugly = new Ugly();
    public int nthUglyNumber(int n) {
     
      return ugly.nums[n-1];
      
    }
}

class Ugly {
  
  int[] nums;
  
  public Ugly(){
    nums = new int[1690];
    nums[0] = 1;
    int i2 = 0, i3 = 0, i5 = 0;
    for(int i=1;i<1690;i++){
      
      nums[i] = Math.min(Math.min(nums[i2]*2, nums[i3]*3), nums[i5]*5);
      
      if(nums[i]==nums[i2]*2) i2++;
      if(nums[i]==nums[i3]*3) i3++;
      if(nums[i]==nums[i5]*5) i5++;
    }
  }
  
}
```
### python:
```python
class Solution:
    # @param {integer} n
    # @return {integer}
    def nthUglyNumber(self, n):
        q = [1]
        i2 = i3 = i5 = 0
        while len(q) < n:
            m2, m3, m5 = q[i2] * 2, q[i3] * 3, q[i5] * 5
            m = min(m2, m3, m5)
            if m == m2:
                i2 += 1
            if m == m3:
                i3 += 1
            if m == m5:
                i5 += 1
            q += m,
        return q[-1]
```        

