# switch 使用

```java
static String formatterPatternSwitch(Object obj) {
    return switch (obj) {
        case Integer i -> String.format("int %d", i);
        case Long l    -> String.format("long %d", l);
        case Double d  -> String.format("double %f", d);
        case String s  -> String.format("String %s", s);
        default        -> obj.toString();
    };
}
```

```java
   switch (s) {
        case "Foo", "Bar" -> System.out.println("Great");
        default           -> System.out.println("Ok");
    }
```

```java
static String formatterPatternSwitch(Object obj) {
    return switch (obj) {
        case Integer i -> String.format("int %d", i);
        case Long l    -> String.format("long %d", l);
        case Double d  -> String.format("double %f", d);
        case String s  -> String.format("String %s", s);
        default        -> obj.toString();
    };
}
```

主要的增强功能是引入了一个新标签，其中是一个模式。的本质是不变的：将选择器表达式的值与开关标签进行比较，选择其中一个标签，并执行或计算与该标签关联的代码。现在的区别在于，对于具有模式的标签，所选标签由模式匹配的结果确定，而不是由相等性测试确定。例如，在下面的代码中，的值与模式匹配，并计算与标签关联的表达式：

```java
Object obj = 123L;
String formatted = switch (obj) {
    case Integer i -> String.format("int %d", i);
    case Long l    -> String.format("long %d", l);
    case Double d  -> String.format("double %f", d);
    case String s  -> String.format("String %s", s);
    default        -> obj.toString();
};
```

