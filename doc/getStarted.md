# 使用说明

## 0. 退出程序

输入 `exit`，退出本程序

## 1. 用户相关命令

### (1) 新建用户

```
user new username
```

### (2) 列表用户

```shell
user list
```

### (3) 查看当前用户

```shell
user current
```

### (4) 切换用户

```shell
user switch username
```

### (5) 登录用户

```shell
user login
```

### (6) 登出用户

```shell
user logout
```


## 2. 代办事项相关命令

### (1) 代办列表

输入 `list` 命令，列出代办事项列表，默认列出的是未完成的代办任务。本命令支持以下参数：

- `--all`: 列出全部代办事项，包括已完成和未完成
- `--checked`: 列出已完成的代办任务
- `--todo`: 列出未完成的代办任务

### (2) 新增任务

输入 `new`命令，创建新代办任务，如：

```shell
new 读半小时书
```

### (3) 完成任务

输入 `check`命令，完成任务，如：

```shell
check 任务id
```

### (4) 更新任务

输入 `update`命令，更新代办任务，如：

```shell
update 任务id 读一小时书
```

### (5) 删除任务

输入 `delete`命令，删除代办任务，如：

```shell
delete 任务id
```

## 3. 用户待办事项导入导出

### （1）导出数据

```shell
export /tmp/data
```

其中，`/tmp/data`为任意有效的文件地址，绝对路径、相对路径均可

### （2）导入并覆盖原数据

```shell
import --overwrite /tmp/data
```

### （3）导入数据，与当前已有数据合并

```shell
import /tmp/data
```
