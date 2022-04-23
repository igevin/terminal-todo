# 迭代4 需求

# 概要

增加用户代办事项的导入导出功能

## 相关命令

（1）导出数据

```shell
export /tmp/data
```

其中，`/tmp/data`为任意有效的文件地址，绝对路径、相对路径均可

（2）导入并覆盖原数据

```shell
import --overwrite /tmp/data
```

（2）导入数据，与当前已有数据合并

```shell
import /tmp/data
```