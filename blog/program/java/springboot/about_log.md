# spring boot 关于日志

### about color
## Sample: 
- [logback-spring.xml](https://gist.github.com/hiyenwong/027c33768d61b2e9a7566d15a5a31f6c#file-logback-spring-xml)

## 说明
### 详情 ： https://logback.qos.ch/manual/layouts.html#coloring

### 注意事项：

`%black(控制台-) %red(%d{yyyy-MM-dd HH:mm:ss}) %green([%thread]) %highlight(%-5level) %boldMagenta(%logger{10}) - %cyan(%msg%n)`

- 第一点，颜色%black %red等等 ，需要用括号将你要显示本颜色的子模块括起来

- 第二点，%red颜色等，前面要与上一个模块 空格隔开

- 同样可以定义一个变量然后直接引用在`标签中`

![image](https://github.com/hiyenwong/TechBlog/blob/master/blog/images/springboot-logback.png)