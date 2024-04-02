# conda 使用

## 常用命令


查看当前conda的版本：
```shell
conda --version
```

要创建一个conda环境，可以使用以下命令：
```shell 
conda create --name myenv  python=x.xx
```
展示所有环境：
```shell
conda env list
```

激活一个环境：
```shell
conda activate myenv
```

退出一个环境：
```shell
conda deactivate
```

删除一个环境：
```shell
conda remove --name myenv --all
```

更新conda：
```shell
conda update conda
```

更新一个环境：
```shell
conda update --name myenv --all
```

<!-- 其他高级conda用法 -->
## 其他高级conda用法

### 1. 自定义conda环境

```shell
conda create --name myenv --file requirements.txt
```

### 2. 自定义conda环境的python版本

```shell
conda create --name myenv python=3.6
```

### 3. 自定义conda环境的python版本和pip版本

```shell
conda create --name myenv python=3.6 pip=10.0.1
```

### 4. conda channel 用法

conda channel 是conda的扩展，可以用来安装conda包，conda channel 类似于python的pip，可以用来安装python包。

conda channel 主要分为两种：

- conda-forge：conda-forge是conda官方维护的conda channel，主要用于conda包的发布。
- bioconda：bioconda是由conda-forge维护的conda channel，主要用于生物信息学相关的包的发布。

conda channel 的使用方法：

```shell
conda config --add channels conda-forge
conda config --add channels bioconda
```

### 5. conda 更改镜像

conda 默认使用清华的镜像，如果需要更换镜像，可以使用以下命令：

```shell
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/main/
```

### 6. mamba
https://github.com/mamba-org/mamba

[EOF]
