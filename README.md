# student

# Run
This app can run as one of following ways.

## Gradle task
`./gradlew run`

## java command line
`java -jar ./build/libs/student<version>.jar

## Docker
First build docker image using script `docker-build.sh` then start a demon contain using script `docker-start.sh`

# Example Urls:

## Ping or healthcheck
`http://localhost:8080/student/ping`

## Student Search
`http://localhost:8080/student/search?first=John` OR
`http://localhost:8080/student/search?last=smith` OR
`http://localhost:8080/student/search?first=John&last=Smith` OR

## Student Detail
`http://localhost:8080/student/John/Smith`

