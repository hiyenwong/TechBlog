# 寻找两个正序数组的中位数
## 描述
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).

给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。

算法的时间复杂度应该为 O(log (m+n)) 。


Example 1:

```
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
```

Example 2:

```
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
```
Constraints:
```
nums1.length == m
    nums2.length == n
    0 <= m <= 1000
    0 <= n <= 1000
    1 <= m + n <= 2000
    -106 <= nums1[i], nums2[i] <= 106
```


## Solution(Rust): 
```rust

impl Solution {
    fn find_median_sorted_arrays(nums1: Vec<i32>, nums2: Vec<i32>) -> f64 {
        let m = nums1.len();
        let n = nums2.len();
        if m > n {
            return Solution::find_median_sorted_arrays(nums2, nums1);
        }
        let mut left = 0;
        let mut right = m;
        while left <= right {
            let i = (left + right) / 2;
            let j = (m + n + 1) / 2 - i;
            if i < m && nums2[j-1] > nums1[i] {
                left = i + 1;
            } else if i > 0 && nums1[i-1] > nums2[j] {
                right = i - 1;
            } else {
                let max_left = if i == 0 {
                    nums2[j-1]
                } else if j == 0 {
                    nums1[i-1]
                } else {
                    nums1[i-1].max(nums2[j-1])
                };
                if (m + n) % 2 == 1 {
                    return max_left as f64;
                }
                let min_right = if i == m {
                    nums2[j]
                } else if j == n {
                    nums1[i]
                } else {
                    nums1[i].min(nums2[j])
                };
                return (max_left + min_right) as f64 / 2.0;
            }
        }
        panic!("should never reach here");
    }
}
```
