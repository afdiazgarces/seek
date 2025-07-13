# Dockerfile

# 1. Usa una imagen base con JDK 17
FROM eclipse-temurin:17-jdk-alpine

# 2. Crea un directorio en el contenedor para la app
WORKDIR /app

# 3. Copia el JAR generado por Maven al contenedor
COPY target/seek-0.0.1-SNAPSHOT.jar app.jar

# 4. Expón el puerto (por defecto Spring Boot usa el 8080)
EXPOSE 8080

# 5. Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
