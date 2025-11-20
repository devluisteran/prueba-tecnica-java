# Prueba Técnica Java

Este proyecto corresponde a una **prueba técnica** realizada en Java con Spring Boot (versión 17) y base de datos embebida H2 Database.  
Además está preparado para ejecutarse en contenedor Docker.

---

## 🧰 Tecnologías usadas

- Java 17  
- Spring Boot  
- H2 Database (modo embebido)  
- Maven (con wrapper `mvnw`)  
- Dockerfile para contenerización  
---

## 🚀 Cómo correr localmente (sin Docker)

1. Clona el repositorio:  
   ```bash
   git clone https://github.com/devluisteran/prueba-tecnica-java.git
   cd prueba-tecnica-java
   
2. Compila el proyecto con Maven:
  ./mvnw clean package -DskipTests

3. Ejecuta el JAR generado:
  java -jar target/*.jar
  
4. Abre en tu navegador o Postman:
  http://localhost:8080

🐳 Cómo correr con Docker

1. Construye la imagen Docker:
  docker build -t spring-h2-app .


2. Ejecuta el contenedor:
  docker run -p 8080:8080 spring-h2-app


3. Ahora la aplicación está disponible en:
  http://localhost:8080
  
📁 Estructura del proyecto
📁 prueba-tecnica-java
│
├── .mvn/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/prueba_tecnica/
│   │   │       ├── PruebaTecnicaApplication.java
│   │   │       │
│   │   │       ├── controller/
│   │   │       │   ├── DirectorioRestService.java
│   │   │       │   └── FacturaRestService.java
│   │   │       │
│   │   │       ├── entity/
│   │   │       │   ├── Factura.java
│   │   │       │   └── Persona.java
│   │   │       │
│   │   │       ├── repository/
│   │   │       │   ├── DirectorioRepository.java
│   │   │       │   └── VentasRepository.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── FacturaService.java
│   │   │       │   └── PersonaService.java
│   │   │       │
│   │   │       └── exception/
│   │   │           ├── BadRequestException.java
│   │   │           ├── ErrorResponse.java
│   │   │           ├── GlobalExceptionHandler.java
│   │   │           └── ResourceNotFoundException.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── logback.xml
│   │
│   └── test/
│       └── java/
│           └── com/prueba_tecnica/
│
├── target/
│
├── Dockerfile
├── mvnw
├── mvnw.cmd
└── pom.xml


📦 Endpoints (ejemplo)
## EndPoints Persona
POST api/personas/store → Crea una persona
GET api/personas/nombre/{nombre} → Retorna lista de personas que coinciden con nombre
GET api/personas/idenficacion/{identificacion} → Retorna la persona encontrada con dicha identificación
DELETE api/personas/delete/{identificacion} → Elimina todos los datos relacionados a persona y a la persona.

## EndPoints Factura
POST api/facturas/store → Crea una factura
GET api/facturas/persona/{personaId} → Retorna lista de facturas que coinciden con el id de persona


