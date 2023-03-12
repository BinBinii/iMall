#基础镜像通过java11来的
FROM openjdk:11
#将当前文件中所有*.jar  拷贝到项目的app.jar中（这个app.jar是自己生成的）
COPY *.jar /app.jar
#映射地址
CMD ["--server.prot=9002"]
#暴露端口
EXPOSE 9002
#执行命令java  -jar
ENTRYPOINT ["java","-jar","/app.jar"]
