#  Fancy Sequence 
请你实现三个 API append，addAll 和 multAll 来实现奇妙序列。

请实现 Fancy 类 ：

    Fancy() 初始化一个空序列对象。
    void append(val) 将整数 val 添加在序列末尾。
    void addAll(inc) 将所有序列中的现有数值都增加 inc 。
    void multAll(m) 将序列中的所有现有数值都乘以整数 m 。
    int getIndex(idx) 得到下标为 idx 处的数值（下标从 0 开始），并将结果对 109 + 7 取余。如果下标大于等于序列的长度，请返回 -1 。

## Solution(Rust): 
```rust

fn add_mod(a: i32, b: i32) -> i32 {
    ((a as i64 + b as i64) % 1000000007) as i32
}

fn mul_mod(a: i32, b: i32) -> i32 {
    (a as i64 * b as i64 % 1000000007) as i32
}
// f(x) = a0+a1*x
// g(x) = b0+b1*x
// g(f(x)) = b0+b1*(a0+a1*x) = (a0*b1+b0)+(a1*b1)*x
fn merge(a: (i32, i32, usize), b: (i32, i32, usize)) -> (i32, i32, usize) {
    (add_mod(mul_mod(a.0, b.1), b.0), mul_mod(a.1, b.1), b.2)
}
struct Fancy {
    val: Vec<(i32, usize)>, //(val, first_op_id)
    ops: Vec<(i32, i32, usize)> //(inc, mul, next_op_id)
}
impl Fancy {
    fn new() -> Self {
        Self{val:Vec::new(), ops:Vec::new()}
    }
    fn append(&mut self, val: i32) {
        self.val.push((val, self.ops.len()));
    }
    fn add_all(&mut self, inc: i32) {
        self.ops.push((inc, 1, self.ops.len() + 1));
    }
    fn mult_all(&mut self, m: i32) {
        self.ops.push((0, m, self.ops.len() + 1));
    }
    fn reduce(&mut self, opid: usize) {
        let next = self.ops[opid].2;
        if next < self.ops.len() {
            self.reduce(next);
            self.ops[opid] = merge(self.ops[opid], self.ops[next]);
        }
    }
    fn get_index(&mut self, idx: i32) -> i32 {
        if idx as usize >= self.val.len() {
            return -1;
        }
        let mut ret = self.val[idx as usize].0;
        let mut opid = self.val[idx as usize].1;
        if opid < self.ops.len() {
            self.reduce(opid);
            ret = add_mod(mul_mod(ret, self.ops[opid].1), self.ops[opid].0);
        }
        ret
    }
}
```