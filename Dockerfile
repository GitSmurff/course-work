FROM openjdk:11-jre-slim

WORKDIR /app
COPY ./*.jar api-nikita-course-work.jar

CMD java -server $JAVA_OPTIONS \
    -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly \
    -XX:CMSInitiatingOccupancyFraction=70 \
    -XX:+ScavengeBeforeFullGC -XX:+CMSScavengeBeforeRemark \
    -jar /app/api-nikita-course-work.jar
