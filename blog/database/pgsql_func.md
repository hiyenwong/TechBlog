# PostgreSQL 常用函数
日常使用的函数

## 保留小数
- TRUNC: 不四舍五入
```sql
select TRUNC(3.2352,2); 
--- 3.23
```
- ROUND: 四舍五入保留小数
```sql
select ROUND(3.2352,2); 
--- 3.24
```

## 计算周数
第一周
ISO 8601定义包含当年第一个星期四的那一周是第一个星期。 基于这个定义，下列的属性有相互的等价性：

第一周至少有4天在1月里面。
该年的“第一天”是最靠近该年1月1日的星期一。
第一个星期最早是12月29日至1月4日，最晚是1月4日至1月10日。
如果1月1日和星期六与星期日不是工作日，1月4日就会是第一个工作日。
如果1月1日是星期一、二、三、或四，它就是第一周，如果1月1日是星期五，它就是去年度的第53周；如果是星期六，它是去年第52周的一部分（如果上一年是格里历的闰年，它就是第53周的一部分）；如果是星期日，它是去年第52周的部分。

最后一周
ISO周日历的最后一星期是第52周或53周，是下一年的第一周之前。这一周的特质如下：

格里历的最后一个星期四会在这一周内。
最后一周有至少有4天在12月里面。
它的中间日，星期四，一定在年尾。
最接近格里历年结束的是12月31日星期日。
12月28日一定在年度内。因为最后一周的日期最晚是12月28日至1月3日，最早是12月22日至12月28日。
如果12月31日是星期一、二、或三，它是下年度的第一周；如果是星期四，它会是结束结束的第53周；如果是星期五，它是年度的第52周（或是在闰年的第53周）；如果是星期六或星期日，它是结束年度的第52周。

### Functions 

#### isodow
The day of the week as Monday (1) to Sunday (7)

```sql
SELECT EXTRACT(ISODOW FROM TIMESTAMP '2001-02-18 20:38:40');
Result: 7
```
This is identical to dow except for Sunday. This matches the ISO 8601 day of the week numbering.

#### isoyear
The ISO 8601 week-numbering year that the date falls in (not applicable to intervals)

```sql
SELECT EXTRACT(ISOYEAR FROM DATE '2006-01-01');
Result: 2005
SELECT EXTRACT(ISOYEAR FROM DATE '2006-01-02');
Result: 2006
```
Each ISO 8601 week-numbering year begins with the Monday of the week containing the 4th of January, so in early January or late December the ISO year may be different from the Gregorian year. See the week field for more information.

This field is not available in PostgreSQL releases prior to 8.3.