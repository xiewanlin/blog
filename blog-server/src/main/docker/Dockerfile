FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /data
ADD sample-server.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=" -server -Xms256m -Xmx1024m -XX:MaxMetaspaceSize=256m -XX:NewRatio=1 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:ParallelGCThreads=2 -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=utf-8 -Dsun.jnu.encoding=UTF8 -Duser.timezone=GMT+08 -Djava.util.Arrays.useLegacyMergeSort=true"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]