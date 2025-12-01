# 1. Usamos una imagen con Maven y Java 17 para construir
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
# Construimos el proyecto ignorando los tests para ir rápido
RUN mvn clean package -DskipTests

# 2. Usamos una imagen con Payara Micro 6 para ejecutar
FROM payara/micro:6.2023.10-jdk17
# Copiamos el archivo WAR que se creó en el paso anterior
# OJO: Asegúrate que el nombre s02_s2-1.0-SNAPSHOT.war coincida con tu pom.xml
COPY --from=build /app/target/s02_s2-1.0-SNAPSHOT.war /opt/payara/deployments/ROOT.war

# 3. Le decimos a Payara que arranque en el puerto que Railway nos asigne
CMD ["--deploy", "/opt/payara/deployments/ROOT.war", "--port", "$PORT"]