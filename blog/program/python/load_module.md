# 在Python中加载模块

## 环境变量
通过使用环境变量进行设置, 这个也是最方便的. 只需要通过`export PYTHONPATH=/path/to/module`即可.`命令进行设置即可.

## 通过代码实现模块的加载
通过代码加载模块的路径
```python
import os
import sys
# 当前文件绝对路径
current_path = os.path.abspath(__file__) 
# 当前路径
parent_path = os.path.dirname(current_path)
# 添加路径
sys.path.insert(0, os.path.join(parent_path, '..'))  # 上上级目录
sys.path.insert(0, parent_path)
```

## 在Virsual Studio Code 设置模块路径
在Virsual Studio Code 设置模块路径, 需要在`setting.json`中添加如下内容:
```json
"python.analysis.extraPaths": [],
```
这两个路径就会被添加到 Python 语言服务的搜索路径中。

### 在Jupyter notebook 设置模块路径
```json
  "jupyter.notebookFileRoot": "${fileDirname}"
  ```
  "jupyter.notebookFileRoot": "${fileDirname}" 的意思是将 Jupyter notebook 的根目录设置为当前打开文件的目录。这在你希望在特定的目录上下文中运行 Jupyter notebook 时非常有用。

  [EOF]