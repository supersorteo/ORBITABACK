FROM eclipse-temurin:23-jdk

# Crear directorio para uploads
#RUN mkdir -p /app/uploads
#RUN chmod -R 777 /app/uploads

# Copiar el JAR compilado localmente
COPY target/orbita-0.0.1-SNAPSHOT.jar java-app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Configurar el comando de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "java-app.jar", "--spring.profiles.active=prod"]