# 私有化 Cargo
## Git支持
一般在组织内总有一些内部封装的模块代码, Cargo则类似golang一样提供一种git方式的[支持](https://doc.rust-lang.org/cargo/reference/specifying-dependencies.html#specifying-dependencies-from-git-repositories).

```toml Cargo.toml
[dependencies]
regex = { git = "https://github.com/rust-lang/regex" } 
```

### git对文件下载类型支持:
- branch (Exp:```regex = { git = "https://github.com/rust-lang/regex", branch = "next" } ```)
- tag (Exp: ```regex = { git = "https://github.com/rust-lang/regex", tag = "0.3.1" } ```)
- rev (Exp: ```regex = { git = "https://github.com/rust-lang/regex", tag = "0.3.1" } ```)
- version,在本地的时候用本地的,发布的时候用版本0.3.1 (Exp: ```regex = { git = "https://github.com/rust-lang/regex", version = "0.3.1" } ```)

#### Bitflags
`Bitflags` 是一个 Rust crate（也可以理解为一个 Rust 的库或模块），用于创建可以方便地进行按位操作的标志位。
在 Rust 中，一个 `bitflag` 是由多个二进制位组成的数值，每个二进制位代表一个标志位。使用这些标志位，可以表示一个开关状态、一组选项或者其他一些可以被二进制位表达的值。
通过使用 `bitflags` crate，可以轻松地定义和操作这些标志位。该 crate 提供了一组宏，可以定义一个 `bitflag` 枚举类型，并为该类型添加各种操作符和方法，以便进行按位操作。
例如，以下代码演示了如何定义一个 `bitflag` 枚举类型，并使用其中的标志位进行按位操作:
```rust 
use bitflags::bitflags;

bitflags! {
    struct MyFlags: u32 {
        const FLAG_A = 0b00000001;
        const FLAG_B = 0b00000010;
        const FLAG_C = 0b00000100;
        const FLAG_D = 0b00001000;
    }
}

fn main() {
    let flags = MyFlags::FLAG_A | MyFlags::FLAG_B;
    assert!(flags.contains(MyFlags::FLAG_A));
    assert!(flags.contains(MyFlags::FLAG_B));
    assert!(!flags.contains(MyFlags::FLAG_C));
    assert!(!flags.contains(MyFlags::FLAG_D));
}

```
在上面的示例中，我们定义了一个 MyFlags 枚举类型，其包含四个标志位：`FLAG_A`、`FLAG_B`、`FLAG_C` 和 `FLAG_D`。每个标志位都表示为二进制数，使用 `bitflags`! 宏进行定义。在 main() 函数中，我们创建了一个名为 flags 的变量，将其中包含的标志位设置为 `FLAG_A` 和 `FLAG_B`。我们然后使用 contains() 方法检查变量中是否包含指定的标志位，并使用断言（assert）来确保测试通过。
#### SmallVec
`SmallVec` 是一个 Rust crate（也可以理解为一个 Rust 的库或模块），提供了一种简单而高效的数据结构，可以在大多数情况下代替 Vec。

在 Rust 中，Vec 是一种动态数组，其大小可以根据需要自动增长或缩小。由于 Vec 的实现方式，当元素数量较小时，它可能会分配过多的内存。而 `SmallVec` 是一种优化过的容器，它使用一个固定大小的缓冲区，可以存储一定数量的元素。当元素数量超过缓冲区的大小时，它会切换到堆分配内存。

使用 `SmallVec` 与使用 Vec 的 API 相同，可以使用 push()、pop()、get() 等方法来操作容器。与 Vec 不同的是，使用 `SmallVec` 时可以避免在元素数量较小的情况下进行堆分配，从而提高性能和减少内存占用。

例如，以下代码演示了如何使用 `SmallVec` 创建一个存储字符串的容器：
```rust
use smallvec::SmallVec;

fn main() {
    let mut vec = SmallVec::<[String; 3]>::new();
    vec.push("hello".to_string());
    vec.push("world".to_string());
    vec.push("rust".to_string());
    assert_eq!(vec.get(1), Some(&"world".to_string()));
}

```
在上面的示例中，我们使用 `SmallVec` 创建了一个存储字符串的容器。使用 `SmallVec::<[String; 3]>::new()` 可以创建一个最大容量为 3 的 `SmallVec`，即缓冲区可以存储三个元素。我们使用 push() 方法向容器中添加了三个字符串，然后使用 get() 方法获取了容器中的第二个元素，即 "world" 字符串。由于 `SmallVec` 的实现方式，当元素数量不超过 3 时，容器不需要进行堆分配，因此可以获得较好的性能和较小的内存占用。如果添加更多的元素，`SmallVec` 会自动切换到堆分配内存。

## 自定义
### [Nexus支持: Nexus Repository Cargo Format](https://github.com/sonatype-nexus-community/nexus-repository-cargo)
### [Cargo.io](https://github.com/rust-lang/crates.io)



## 命名规范
基本的 Rust 命名规范在 [RFC 430] 中有描述。

通常，对于 **type-level** 的构造 Rust 倾向于使用**驼峰命名法**，而对于 **value-level** 的构造使用**蛇形命名法**。详情如下：

| 条目 | 惯例 |
| ---- | ---------- |
| 包 Crates | [unclear](https://github.com/rust-lang/api-guidelines/issues/29) |
| 模块 Modules | `snake_case` |
| 类型 Types | `UpperCamelCase` |
| 特征 Traits | `UpperCamelCase` |
| 枚举 Enumerations | `UpperCamelCase` |
| 结构体 Structs | `UpperCamelCase` |
| 函数 Functions | `snake_case` |
| 方法 Methods | `snake_case` |
| 通用构造器 General constructors | `new` or `with_more_details` |
| 转换构造器 Conversion constructors | `from_some_other_type` |
| 宏 Macros | `snake_case!` |
| 局部变量 Local variables | `snake_case` |
| 静态类型 Statics | `SCREAMING_SNAKE_CASE` |
| 常量 Constants | `SCREAMING_SNAKE_CASE` |
| 类型参数 Type parameters | `UpperCamelCase`，通常使用一个大写字母: `T` |
| 生命周期 Lifetimes | 通常使用小写字母: `'a`，`'de`，`'src` |
| Features | [unclear](https://github.com/rust-lang/api-guidelines/issues/101) but see [C-FEATURE] |