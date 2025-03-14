# Usar una imagen base de OpenJDK 17
FROM openjdk:17-jdk-slim

# Install libfreetype6 and other necessary libraries
RUN apt-get update && apt-get install -y \
    libfreetype6 \
    fontconfig \
    fonts-noto \
    fonts-dejavu \
    && rm -rf /var/lib/apt/lists/*

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado al contenedor
COPY target/gestorfutbol-0.0.1.jar app.jar

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar la aplicación con la propiedad java.awt.headless
ENTRYPOINT ["java", "-Djava.awt.headless=true", "-jar", "app.jar"]