# 最长回文子串
给你一个字符串 s，找到 s 中最长的回文子串。如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。

# 解题
```rust
impl Solution {
    pub fn longest_palindrome(s: String) -> String {
        let chars: Vec<char> = s.chars().collect();
            let n = chars.len();
            let mut start = 0;
            let mut max_len = 1;
            let mut table: Vec<Vec<bool>> = vec![vec![false; n]; n];
            
            // All individual characters are palindromes
            for i in 0..n {
                table[i][i] = true;
            }
            
            // Check for palindromes of length 2
            for i in 0..n-1 {
                if chars[i] == chars[i+1] {
                    table[i][i+1] = true;
                    start = i;
                    max_len = 2;
                }
            }
            
            // Check for palindromes of length 3 or more
            for k in 3..=n {
                for i in 0..n-k+1 {
                    let j = i + k - 1;
                    if chars[i] == chars[j] && table[i+1][j-1] {
                        table[i][j] = true;
                        if k > max_len {
                            start = i;
                            max_len = k;
                        }
                    }
                }
            }
            
            // Return the longest palindromic substring
            let result: String = chars[start..start+max_len].iter().collect();
            result
    }
}
```
## 官方例子

```rust
impl Solution {
    pub fn longest_palindrome(s: String) -> String {
        // Manacher's algorithm
        // 插入了特殊标记#后, 回文个数必然是奇数的. 以某一位置对称的的半轴长必然是原始回文的长度;
        let mut ps = Vec::with_capacity((s.len() << 1) + 3);
        ps.push('^');
        s.chars().for_each(|x| {ps.push('#'); ps.push(x);});
        ps.push('#');
        ps.push('$');
        
        // cnt记录以i位置对称的长度(不包括自身), c记录上一次最长对称的中心位置, r记录遍历过的最远位置
        let (mut cnt, mut c, mut r) = (Vec::new(), 0usize, 0);
        cnt.resize(ps.len(), 0);
        for i in 1..(ps.len()-1) {
            // 关于c与i对称的位置(c - (i-c))
            let m = (c << 1).wrapping_sub(i);
            // 跳过已经比较过对称的元素, 因为m关于c和i对称, 如果r大于i, 那么m实在以c为中心的对称的轴上的,
            // 那么m对称的轴和以i为对称的轴必然有重叠, 重叠便是min(r-i,cnt[m])
            // r'----xx-m-xx----c--xx-i-xx----r
            cnt[i] = if r > i {std::cmp::min(r - i, cnt[m])} else {0};
            
            // 以T[i]为中心, 向左右两边查找对称
            while ps[i + 1 + cnt[i]] == ps[i - 1 - cnt[i]] {
                cnt[i] += 1;
            }
            
            if i + cnt[i] > r {
                c = i;
                r = i + cnt[i];
            }
        }
        
        match cnt.iter().enumerate().max_by(|&x, &y| {
            x.1.cmp(y.1)
        }) {
            Some((center_idx, &max_len)) => {
                s.chars().skip((center_idx - 1 - max_len) >> 1).take(max_len).collect()
            },
            None => String::new(),
        }
 
    }
}



```
