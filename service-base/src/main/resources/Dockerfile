FROM openjdk:8-jdk-alpine

RUN apk add --update --no-cache ttf-dejavu fontconfig && rm -rf /var/cache/apk/*


MAINTAINER "Jzj"

WORKDIR app

ADD vue-system.jar /app/vue-system.jar

ENV TIME_ZONE=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo $TIME_ZONE >  /etc/timezone

EXPOSE 8089

CMD java -jar  -Xms512M -Xmx512M -Duser.timezone=GMT+8 /app/vue-system.jar
