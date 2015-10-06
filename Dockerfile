FROM java:8

WORKDIR /

ADD build/libs/color-0.0.1-SNAPSHOT.jar /color.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/color.jar"]
