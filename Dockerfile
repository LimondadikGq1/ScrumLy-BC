

FROM gradle:9.1.0-jdk21 as build
WORKDIR /app
COPY --chown=gradle:gradle . /app
RUN gradle clean build -x test --no-daemon


FROM  eclipse-temurin:21-jre-jammy AS builder

WORKDIR /appd
COPY --from=build /app/build/libs/jira-0.0.1.jar /app/jira.jar
RUN java -Djarmode=layertools -jar jira.jar extract

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=builder app/dependencies/ ./
COPY --from=builder app/spring-boot-loader/ ./
COPY --from=builder app/snapshot-dependencies/ ./
COPY --from=builder app/application/ ./

EXPOSE 8080

ENTRYPOINT ["java","org.springframework.boot.loader.launch.JarLauncher"]



