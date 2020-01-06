# newword
flink 学习
## 官网文档学习
### 词频统计
1. dataset
2. datastream
# 官方demo学习
## 安装项目
```
curl https://flink.apache.org/q/quickstart.sh | bash -s 1.9.0
```
## 官方地址
```
https://github.com/apache/flink/tree/master/flink-examples
```
## 官方示例
### batch
#### wordcount
1. ParameterTool
2. Preconditions
3. env.getConfig().setGlobalJobParameters(param);

## 打包
```
mvn clean package -DskipTests
```
## 运行
```
flink run -p 3 -c classname quickstart-0.1.jar
```