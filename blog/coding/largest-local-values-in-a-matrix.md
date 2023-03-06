# 题目:
给你一个大小为 n x n 的整数矩阵 grid 。

生成一个大小为 (n - 2) x (n - 2) 的整数矩阵  maxLocal ，并满足：

    maxLocal[i][j] 等于 grid 中以 i + 1 行和 j + 1 列为中心的 3 x 3 矩阵中的 最大值 。

换句话说，我们希望找出 grid 中每个 3 x 3 矩阵中的最大值。

返回生成的矩阵。
# 解答:
## 方法一 rust version
```rust
impl Solution {
    pub fn largest_local(grid: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
      (0..grid.len() - 2)
        .map(|i|
            (0..grid.len() - 2)
            .map(|j|
            *grid[i][j..j + 3]
                .iter()
                .max()
                .unwrap()
                .max(grid[i + 1][j..j + 3]
                    .iter()
                    .max()
                    .unwrap()
                )
                .max(grid[i + 2][j..j + 3]
                    .iter()
                    .max()
                    .unwrap()
                )
            ).collect::<Vec<i32>>()
        ).collect::<Vec<Vec<i32>>>()
  }
}
```
## 方法2 rust version
```rust
impl Solution {
    pub fn largest_local(grid: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
      (0..grid.len() - 2)
    let n = grid.len();
    let mut max_local = vec![vec![0; n-2]; n-2];
    for i in 1..n-1 {
        for j in 1..n-1 {
            let mut max_val = i32::MIN;
            for x in i-1..i+2 {
                for y in j-1..j+2 {
                    max_val = max_val.max(grid[x][y]);
                }
            }
            max_local[i-1][j-1] = max_val;
        }
    }
    max_local
  }
}
```
