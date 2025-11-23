# Apache Airflow Setup Guide

## 安装 Apache Airflow
目前适用于apache airflow 3.1.3版本, 后续4.x版本请参考官方文档进行安装

```shell
# install airflow
pip install "apache-airflow[celery]==3.1.3" --constraint "https://raw.githubusercontent.com/apache/airflow/constraints-3.1.3/constraints-3.10.txt"

pip install apache-airflow-providers-fab

airflow db init

airflow db migrate
```

这里默认安装的数据库是使用sqlite,如果要安装mysql, postgres数据库,则需要如下配置

```shell
# 安装 PostgreSQL Provider（可选，但推荐，提供 Postgres Hook/Operator）
pip install apache-airflow-providers-postgres

# 安装 psycopg2 驱动程序
pip install psycopg2-binary
```
然后修改 airflow.cfg 配置文件中的 sql_alchemy_conn 参数，例如：

```cfg
sql_alchemy_conn = postgresql+psycopg2://username:password@hostname:port/dbname
```
替换其中的 username、password、hostname、port 和 dbname 为你的 PostgreSQL 数据库的实际信息。

完成后，重新初始化数据库：

```shell
airflow db init
```

如果使用 MySQL 数据库，则需要如下配置：

```shell
# 安装 MySQL Provider（可选，但推荐，提供 MySQL Hook/Operator）
pip install apache-airflow-providers-mysql
# 安装 mysql-connector-python 驱动程序
pip install mysql-connector-python
```
然后修改 airflow.cfg 配置文件中的 sql_alchemy_conn 参数，例如：

```cfg
sql_alchemy_conn = mysql+mysqlconnector://username:password@hostname:port/dbname
```
替换其中的 username、password、hostname、port 和 dbname 为你的 MySQL 数据库的实际信息。
完成后，重新初始化数据库：

```shell
airflow db init
```

## 权限配置
一开始默认使用的是SimpleAuthManager, 需要切换成FAB权限管理.
安装相关的包
```shell
pip install apache-airflow-providers-fab
```

修改 airflow.cfg 配置文件中的以下参数：
```cfg
[webserver]
authenticate = True
auth_backend = airflow.www.fab_security.manager.FABSecurityManager
```

Docker 环境可以通过环境变量进行配置：
```yaml
AIRFLOW__WEBSERVER__AUTHENTICATE: 'True'
AIRFLOW__WEBSERVER__AUTH_BACKEND: 'airflow.www.fab_security.manager
.FABSecurityManager'
```
完成后，重启 Airflow Web 服务器：
```shell
airflow webserver --port 8080
```
## 创建管理员用户
使用以下命令创建管理员用户：
```shell
airflow users create \
    --username admin \
    --firstname Admin \
    --lastname User \
    --role Admin \
    --email admin@example.com \
    --password your_password
```
将 `your_password` 替换为你想要设置的管理员密码。
完成后，你可以使用刚才创建的管理员账户登录 Airflow Web 界面，进行进一步的配置和管理。