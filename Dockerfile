FROM gradle:7.2-jdk17 as build-step
ENV GRADLE_OPTS -Dorg.gradle.daemon=false
WORKDIR /app
COPY ./ .
RUN gradle build -x test
CMD ["sh", "-c", "while true; do sleep 86400; done"]
