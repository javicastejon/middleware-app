# ----------------------------------------
# Fase de construcción (genera el .jar)
# ----------------------------------------
    FROM maven:3.8.6-openjdk-17 AS builder

    # 1. Configuramos el directorio de trabajo como /app (dentro del contenedor)
    WORKDIR /app
    
    # 2. Copiamos SOLO el pom.xml primero (para cachear dependencias)
    COPY final/pom.xml .
    
    # 3. Descargamos dependencias (cache eficiente)
    RUN mvn dependency:go-offline -B
    
    # 4. Copiamos el código fuente completo
    COPY final/src ./src
    
    # 5. Compilamos y empaquetamos la aplicación (omitimos tests)
    RUN mvn clean package -DskipTests
    
    # ----------------------------------------
    # Fase de ejecución (imagen liviana)
    # ----------------------------------------
    FROM openjdk:17-jdk-slim
    
    # 1. Directorio donde vivirá la aplicación
    WORKDIR /app
    
    # 2. Copiamos el .jar desde la fase de construcción
    # Nota: La ruta debe coincidir con donde Maven lo genera
    COPY --from=builder /app/target/*.jar app.jar
    
    # 3. Puerto que expone la aplicación (debe coincidir con tu application.properties)
    EXPOSE 8080
    
    # 4. Comando de ejecución (no usar shell form para mejor manejo de señales)
    ENTRYPOINT ["java", "-jar", "app.jar"]