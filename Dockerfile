# 1. Etapa de Construcción (Maven)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2. Etapa de Ejecución (Payara Micro)
FROM payara/micro:6.2023.10-jdk17

# Copiamos el WAR generado
COPY --from=build /app/target/s02_s2-1.0-SNAPSHOT.war /opt/payara/deployments/ROOT.war

# --- CORRECCIÓN AQUÍ ---
# Usamos ENTRYPOINT con "sh -c" para que Java pueda leer la variable $PORT correctamente
ENTRYPOINT ["sh", "-c", "java -jar /opt/payara/payara-micro.jar --deploy /opt/payara/deployments/ROOT.war --port $PORT"]
