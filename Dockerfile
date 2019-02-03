FROM anapsix/alpine-java:8_jdk
WORKDIR /app
VOLUME /tmp
ADD . /app
RUN chmod +x start.sh && ./gradlew clean build
EXPOSE 8080
ENTRYPOINT [ "./start.sh" ]
