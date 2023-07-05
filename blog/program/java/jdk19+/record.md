# Record 类型
## 特点
- 拥有自己的getter方法
- 自动生成构造函数
- 自动创建hashcode
- 自动创建toString
- 自动创建equals
- 定义class时使用final，无法派生子类；

```java
/**
 * @author Hi Yen Wong {@code @date} 2023/7/4 22:59
 */
public record UserType(String typeName, String value) implements Serializable {
  private static final long serialVersionUID = 1L;
}
```