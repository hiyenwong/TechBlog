# 使字符串平衡的最少删除次数
给你一个字符串 s ，它仅包含字符 'a' 和 'b'​​​​ 。

你可以删除 s 中任意数目的字符，使得 s 平衡 。当不存在下标对 (i,j) 满足 i < j ，且 s[i] = 'b' 的同时 s[j]= 'a' ，此时认为 s 是 平衡 的。

请你返回使 s 平衡 的 最少 删除次数。

示例 1：

输入：s = "aababbab"
输出：2
解释：你可以选择以下任意一种方案：
下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。

## Solution(Rust): 

```rust
impl Solution {
fn minimum_deletions(s: String) -> i32 {
    let n = s.len();
    let mut count_a = vec![0; n+1];
    let mut count_b = vec![0; n+1];
    let s_chars: Vec<char> = s.chars().collect();
    for i in 1..=n {
        if s_chars[i-1] == 'a' {
            count_a[i] = count_a[i-1] + 1;
            count_b[i] = count_b[i-1];
        } else {
            count_a[i] = count_a[i-1];
            count_b[i] = count_b[i-1] + 1;
        }
    }
    let mut ans = n;
    for i in 0..=n {
        let deletions = count_b[i] + count_a[n] - count_a[i];
        ans = ans.min(deletions);
    }
    ans as i32
    }
}
```