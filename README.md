# student

# Run
This app runs on port `8080` and maps in-memory json database to local `/tmp` directory. It can run as one of the following methods:

## Gradle task
`./gradlew run`

## java command line
`java -jar ./build/libs/student<version>.jar` (Produced after `./gradlew clean build`)

## Docker
First build docker image using script `docker-build.sh` then start a daemon container using script `docker-start.sh`

# Example Urls:

## Ping or healthcheck
`http://localhost:8080/student/ping`

## Student Search
`http://localhost:8080/student/_search?first=John` OR

`http://localhost:8080/student/_search?last=smith` OR

`http://localhost:8080/student/_search?first=John&last=Smith`

## Student Detail
`http://localhost:8080/student/John/Smith`

# Api Documentation
`http://localhost:8080/swagger.json`
