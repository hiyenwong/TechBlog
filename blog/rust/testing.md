# 测试
## 执行状态
- 设置所需的数据或状态 
- 运行想要测试的代码 
- 判断( assert )返回的结果是否符合预期

```rust spring_utils_test.rs
#[cfg(test)]
mod test {
    use mercury_rust::string_utils;
    #[test]
    fn it_test() {
        let sp = String::from(",");
        let re = String::from("hello, world");
        let ret = string_utils::string_utils::split_ext(sp,&re);
        let c = vec!["hello", " world"];
        assert_eq!(ret,c);
    }
}
```
可以看出，测试函数需要使用 test 属性进行标注。关于属性( attribute )，使用 test 属性，我们也可以获取 Rust 提供的测试特性。
经过 test 标记的函数就可以被测试执行器发现，并进行运行。当然，在测试模块 tests 中，还可以定义非测试函数，
这些函数可以用于设置环境或执行一些通用操作：例如为部分测试函数提供某个通用的功能，这种功能就可以抽象为一个非测试函数。

### 自定义失败的信息
```rust
fn greeting_contains_name() {
    let result = greeting("Sunface");
    let target = "孙飞";
    assert!(
        result.contains(target),
        "你的问候中并没有包含目标姓名 {} ，你的问候是 `{}`",
        target,
        result
    );
}
```
### 测试panic
们通过 panic 来触发报错，但是如果一个函数本来就会 panic ，而我们想要检查这种结果呢？

也就是说，我们需要一个办法来测试一个函数是否会 panic，对此， Rust 提供了 should_panic 属性注解，和 test 注解一样，对目标测试函数进行标注即可：

```rust

pub struct Guess {
value: i32,
}

impl Guess {
pub fn new(value: i32) -> Guess {
if value < 1 || value > 100 {
panic!("Guess value must be between 1 and 100, got {}.", value);
}

        Guess { value }
    }
}

#[cfg(test)]
mod tests {
use super::*;

    #[test]
    #[should_panic]
    fn greater_than_100() {
        Guess::new(200);
    }
}
```

### 并行执行顺序 `cargo test -- --test-threads=1`
### 展示打印 `cargo test -- --show-output`
### 只测试一部分 `cargo test [method_name]`

### cargo 包管理以及依赖
#### [dev-dependencies]

其中一个例子就是 pretty_assertions，它可以用来扩展标准库中的 assert_eq! 和 assert_ne!，例如提供彩色字体的结果对比。

### 生成测试二进制文件

在有些时候，我们可能希望将测试与别人分享，这种情况下生成一个类似 cargo build 的可执行二进制文件是很好的选择。

事实上，在 cargo test 运行的时候，系统会自动为我们生成一个可运行测试的二进制可执行文件:
```shell
$ cargo test
Finished test [unoptimized + debuginfo] target(s) in 0.00s
Running unittests (target/debug/deps/study_cargo-0d693f72a0f49166)
```


这里的 target/debug/deps/study_cargo-0d693f72a0f49166 就是可执行文件的路径和名称，我们直接运行该文件来执行编译好的测试:
```shell
$ target/debug/deps/study_cargo-0d693f72a0f49166

running 3 tests
test tests::add_two_and_two ... ok
test tests::add_three_and_two ... ok
test tests::one_hundred ... ok

test result: ok. 3 passed; 0 failed; 0 ignored; 0 measured; 0 filtered out; finished in 0.00s
```
如果你只想生成编译生成文件，不想看 cargo test 的输出结果，还可以使用 cargo test --no-run.