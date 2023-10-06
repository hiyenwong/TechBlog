# Func Advance in Java

## 高阶函数 和 Callback Function

目标,我们入参是一个方法,将本体运行中的值或者返回传入作为参数中入参函数的参数传入.

### 一个简单的例子

场景：购物车计算器

假设我们有一个购物车，其中有多个商品，每个商品都有价格和数量。我们需要编写一个购物车计算器，根据购物车中的商品列表和一个计算规则，计算出购物车的总价。

首先，我们需要定义一个商品类：

```java
class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}
```

接下来，我们定义一个高阶函数来计算购物车的总价。我们使用了Java的内置函数式接口java.util.function.Function，它接收一个泛型类型参数，并且具有一个apply方法，该方法接收一个参数并返回一个结果。

```java
import java.util.List;
import java.util.function.Function;

public class ShoppingCartCalculator {

    public static double calculateTotalPrice(List<Product> products, Function<Product, Double> priceCalculator) {
        double total = 0;
        for (Product product : products) {
            total += priceCalculator.apply(product);
        }
        return total;
    }

    public static void main(String[] args) {
        // 创建购物车商品列表
        List<Product> products = List.of(
                new Product("商品1", 10.0, 2),
                new Product("商品2", 5.0, 3),
                new Product("商品3", 8.0, 1)
        );

        // 计算购物车总价
        double totalPrice = calculateTotalPrice(products, Product::getTotalPrice);
        System.out.println("购物车总价：" + totalPrice);
    }
}
```

在上述示例中，calculateTotalPrice方法是一个高阶函数，它接收一个List<Product>和一个Function<Product, Double>
作为参数。Function<Product, Double>指定了计算单个商品价格的规则。在main方法中，我们使用了Lambda表达式Product::
getTotalPrice来作为参数传递给calculateTotalPrice方法，这样就可以计算出购物车的总价。

注意：Java中的函数式编程相较于其他编程语言可能略显繁琐，但它可以实现类似高阶函数的功能，使代码更具可读性和灵活性。

### 其他例子

[参见案例](../../../src/java/functiondemo/FunctionDemo.java)
- 接口只有一个方法,切可以做 Lambda表达式
- 当一个接口Interface作为一个返回对象时候,如果里面的方法体只有一个时,则可以用lambda

```java
interface BiOut {
    String getX(BiIn x);
}
```
```java
public BiOut functionDemo() {
        return resp ->
                resp.getZ().toString();
    }
```
