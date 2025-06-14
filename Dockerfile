# ---- Build Stage ----
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app
COPY . .
# If you use Maven Wrapper, replace 'mvn' with './mvnw'
RUN ./mvnw clean package -DskipTests

# ---- Run Stage ----
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
