# Apache Airflow Setup Guide

## 安装 Apache Airflow
目前适用于apache airflow 3.1.3版本, 后续4.x版本请参考官方文档进行安装

```shell
# install airflow
pip install "apache-airflow[celery]==3.1.3" --constraint "https://raw.githubusercontent.com/apache/airflow/constraints-3.1.3/constraints-3.10.txt"

pip install apache-airflow-providers-fab

airflow db init #airflow 3.1.3 好像没有这个命令

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

## 创建Airflow 启动服务

### 环境变量
可以通过设置环境变量来配置 Airflow，例如：
```shell
# -----------------------------------------------------------------
# Airflow Environment Configuration
# -----------------------------------------------------------------

# Airflow 根目录
AIRFLOW_HOME=/data/airflow

# Airflow Database
AIRFLOW__CORE__SQL_ALCHEMY_CONN=postgresql+psycopg2://oaadmin:Agyness09&^ESB2AEEE@10.110.91.177:5432/airflow_db

# Conda 环境的 bin 目录 (⚠️请修改为你的实际路径)
# 你可以通过在 conda 环境下运行 `echo $CONDA_PREFIX/bin` 获取
AIRFLOW_CONDA_BIN=/home/irsoperator/miniconda3/envs/airflow/bin

# 确保 Path 包含 Conda 环境
PATH=/home/irsoperator/miniconda3/envs/airflow/bin:/usr/local/bin:/usr/bin:/bin
```

### Systemd 服务文件

### airflow.target
```shell
[Unit]
Description=Airflow 3.x Composite Service (API + Scheduler + Triggerer)
# 强依赖：启动 target 时必须启动这些
Requires=airflow-api-server.service airflow-scheduler.service airflow-triggerer.service
# 弱依赖：停止 target 时也会尝试停止这些
Wants=airflow-api-server.service airflow-scheduler.service airflow-triggerer.service

[Install]
WantedBy=multi-user.target
```

### airflow-api-server.service
```shell
[Unit]
Description=Airflow API Server Daemon
After=network.target
# 属于 airflow.target 组
PartOf=airflow.target

[Service]
EnvironmentFile=/etc/sysconfig/airflow
# ⚠️ 修改为你的 Linux 用户名
User=irsoperator
Group=irsoperator
Type=simple
WorkingDirectory=/data/airflow

# 使用 bash exec 调用 Conda 环境内的 airflow
ExecStart=/bin/bash -c 'exec ${AIRFLOW_CONDA_BIN}/airflow api-server'

Restart=always
RestartSec=5s
PrivateTmp=true

[Install]
WantedBy=airflow.target
```

### airflow-scheduler.service
```shell
[Unit]
Description=Airflow Scheduler Daemon
After=network.target airflow-api-server.service
# 属于 airflow.target 组
PartOf=airflow.target

[Service]
EnvironmentFile=/etc/sysconfig/airflow
# ⚠️ 修改为你的 Linux 用户名
User=irsoperator
Group=irsoperator
Type=simple
WorkingDirectory=/data/airflow

# 使用 bash exec 调用 Conda 环境内的 airflow
ExecStart=/bin/bash -c 'exec ${AIRFLOW_CONDA_BIN}/airflow scheduler'

Restart=always
RestartSec=5s
PrivateTmp=true

[Install]
WantedBy=airflow.target
```

### airflow-triggerer.service
```shell
[Unit]
Description=Airflow Triggerer Daemon
After=network.target postgresql.service airflow-api-server.service
# 属于 airflow.target 组
PartOf=airflow.target

[Service]
EnvironmentFile=/etc/sysconfig/airflow
# ⚠️ 修改为你的 Linux 用户名
User=irsoperator
Group=irsoperator
Type=simple
WorkingDirectory=/data/airflow

# 使用 bash exec 调用 Conda 环境内的 airflow
ExecStart=/bin/bash -c 'exec ${AIRFLOW_CONDA_BIN}/airflow triggerer'

Restart=always
RestartSec=5s
PrivateTmp=true

[Install]
WantedBy=airflow.target
```

### 启动服务
创建好以上 systemd 服务文件后，执行以下命令启动 Airflow 服务：
```shell
# 重新加载 systemd 配置
sudo systemctl daemon-reload
# 启动 Airflow 服务
sudo systemctl start airflow.target
# 设置 Airflow 服务开机自启
sudo systemctl enable airflow.target
```