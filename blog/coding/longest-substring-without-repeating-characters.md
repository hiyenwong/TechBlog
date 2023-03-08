# 无重复字符的最长子串

给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。

Given a string s, find the length of the longest substring without repeating characters.

```rust
impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let s = s.as_str();
        let mut max_len = 0;
        let mut start = 0;
        let mut char_indices = std::collections::HashMap::new();
        
        for (i, c) in s.char_indices() {
            if let Some(j) = char_indices.get(&c) {
                start = start.max(*j + 1);
            }
            max_len = max_len.max(i - start + 1);
            char_indices.insert(c, i);
        }
        
        max_len as i32
    }
}

```
