# Two Sum

Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
```
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
```

## code:
```java
class Solution {
    public     int[] twoSum(int[] nums, int target) {
        int[] rs = new int[]{-1, -1};

        if (nums == null || nums.length <= 1)
            return rs;

        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                rs[0] = hashMap.get(nums[i]);
                rs[1] = i;
                return rs;
            } else {
                hashMap.put(target - nums[i], i);
            }
        }

        return rs;
    }
}
```