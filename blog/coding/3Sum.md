# 3Sum

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],
```
A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```

# code 
## version 1:
```java
class Solution {

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> ret = new ArrayList<>();
 
		if (nums == null || nums.length < 3)
			return ret;
		int len = nums.length;
		Arrays.sort(nums);
		// 注意，对于 num[i]，寻找另外两个数时，只要从 i+1 开始找就可以了。
		// 因为数组时排好序的，所以当一个数被放到结果集中的时候，其后面和它相等的直接被跳过。
		for (int i = 0; i < len; i++) {
			// 可省，目的减少无意义的循环
			if (nums[i] > 0)
				break;
	
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
		
			int begin = i + 1;
			int end = len - 1;
			while (begin < end) {
				int sum = nums[i] + nums[begin] + nums[end];
				if (sum == 0) {
					List<Integer> list = new ArrayList<>();
					list.add(nums[i]);
					list.add(nums[begin]);
					list.add(nums[end]);
					ret.add(list);
					begin++;
					end--;
					while (begin < end && nums[begin] == nums[begin - 1])
						begin++;
					while (begin < end && nums[end] == nums[end + 1])
						end--;
				} else if (sum > 0)
					end--;
				else
					begin++;
			}
		}
		return ret;
	}
}
```
## version 2:
```java
class Solution {
 public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> ret = new ArrayList<List<Integer>>();
            Arrays.sort(nums); //asc order
            
            for (int i=0; i < nums.length-2; i++)
            {
                if(nums[i] > 0 || (i > 0 && nums[i-1]==nums[i]))
                    continue;
                int sum = 0 - nums[i];
                
                int lo=i+1;
                int hi = nums.length-1; 
                while (lo<hi)
                {
                    if (nums[lo] + nums[hi] > sum)
                    {
                        hi--;
                    }
                    else if (nums[lo] + nums[hi] < sum)
                        lo++;
                    else
                    {
                        List<Integer> entry = new ArrayList<Integer>();
                        entry.add(nums[i]);
                        entry.add(nums[lo++]);
                        entry.add(nums[hi--]);
                        ret.add(entry);
                        while (lo < nums.length && nums[lo] == nums[lo-1])
                            lo++;
                        while (hi >= 0 && nums[hi] == nums[hi+1])
                            hi--;
                    }
                }
                
            }
            return ret;
        }
}
```
## version 3:
```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.length;) {
            if (nums[i] > 0) {
                break;
            }
            int target = -nums[i], lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                if (sum > target) {
                    hi = hi - 1;
                } else if (sum < target) {
                    lo = lo + 1;
                } else {
                    result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    do {
                        ++ lo;
                    } while (lo < hi && nums[lo] == nums[lo - 1]);
                    do {
                        -- hi;
                    } while (lo < hi && nums[hi] == nums[hi + 1]);
                }
                            
            }
            while (i < nums.length && -target == nums[i]) ++i;
        }
        return result;
    }
}
```