# Docker Desktop的替代方案

## 方案1: [colima](https://github.com/abiosoft/colima)

colima 的方法相对比较简单
```bash
brew install colima
colima start
docker run hello-world
docker run -d -p 8080:80 nginx
```

## 方案2: Racher-Desktop 
Racher-Desktop 是一个基于Kubernetes的容器编排工具，可以快速部署容器集群，并且可以管理容器集群中的容器。

它在mac系统里是使用lima来启动虚拟机和docker服务,而容器化管理则使用Moby.

安装基本没有问题,非常简单,主要遇到下面几个麻烦.

### 设置代理
由于这个是在虚拟机下,而代理服务是在宿主机上,所以需要提供一个宿主机的访问name
#### 执行 `rdctrl shell` 进入虚拟机
#### 编辑 `/etc/config.d/docker`,`/etc/config/containerd` 

```bash
HTTP_PROXY="http://host.docker.internal:7890"
HTTPS_PROXY="http://host.docker.internal:7890"
NO_PROXY="localhost,127.0.0.1,.svc,.cluster.local"
export HTTP_PROXY
export HTTPS_PROXY
export NO_PROXY
```
这样在虚拟机里,就可以通过代理访问网络,从而可以docker pull 想要的镜像. 由于我的大部分image 是我自己制作的,所以在国内的一些mirror里找不到.

### 在容器里使用代理访问

无论是docker-desktop,还是colima, lima这类都会访问`~/.docker/config.json`,在这里,你可以给容器里添加代理,这样就可以在打包镜像的时候,通过代理访问网络.

```json
  "proxies": {
    "default": {
      "httpProxy": "http://host.docker.internal:7890",
      "httpsProxy": "http://host.docker.internal:7890",
      "noProxy": "localhost,127.0.0.1,.example.com"
    }
  },
```
## 顺便说一下GIT问题

由于一些代理,不支持22 端口出去,所以导致访问github的时候, push, pull 失败,所以需要配置一下`~/.ssh/config`这个文件,需要做两件事情.

* 修改端口走 443 也就是https 端口
* 添加代理

具体相关配置如下:

```
Host github.com
    HostName ssh.github.com
    User git
    Port 443
    PreferredAuthentications publickey
    IdentityFile ~/.ssh/github_id_rsa
    ProxyCommand nc -X connect -x 127.0.0.1:7890 %h %p
```
