# 创建自己的Systemctl服务

## 路径

`/usr/lib/systemd/system`



## 代码
```shell
[Unit]
Description=OpenSSH server daemon
Documentation=man:sshd(8) man:sshd_config(5)
After=network.target sshd-keygen.service
Wants=sshd-keygen.service

[Service]
EnvironmentFile=/etc/sysconfig/sshd
ExecStart=/usr/sbin/sshd -D $OPTIONS
ExecReload=/bin/kill -HUP $MAINPID
Type=simple
KillMode=process
Restart=on-failure
RestartSec=42s

[Install]
WantedBy=multi-user.target
```

## 参数说明
- After|Befor:表示如果network.target需要启动，那么service应该在它们之后启动 After和Before字段只涉及启动顺序，不涉及依赖关系。
- EnvironmentFile：指定当前服务的环境参数文件。该文件内部的key=value键值对，可以用$key的形式，在当前配置文件中获取。
- ExecReload字段：重启服务时执行的命令
- ExecStop字段：停止服务时执行的命令
- ExecStartPre字段：启动服务之前执行的命令
- ExecStartPost字段：启动服务之后执行的命令
- ExecStopPost字段：停止服务之后执行的命令
- type:
    - simple（默认值）：ExecStart字段启动的进程为主进程
    - forking：ExecStart字段将以fork()方式启动，此时父进程将会退出，子进程将成为主进程
    - oneshot：类似于simple，但只执行一次，Systemd 会等它执行完，才启动其他服务
    - dbus：类似于simple，但会等待 D-Bus 信号后启动
    - notify：类似于simple，启动结束后会发出通知信号，然后 Systemd 再启动其他服务
    - idle：类似于simple，但是要等到其他任务都执行完，才会启动该服务。一种使用场合是为让该服务的输出，不与其他服务的输出相混合