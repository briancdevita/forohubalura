# Forohub Alura

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-lightgrey?style=for-the-badge)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![Maven](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

Un proyecto REST API desarrollado como parte del desafío del programa ONE de Alura Latam, Forohub es una plataforma tipo foro donde los usuarios pueden crear, leer, actualizar y eliminar tópicos de discusión.

## 🚀 Funcionalidades

* **Autenticación y Autorización**: Registro de usuarios y autenticación mediante JWT (JSON Web Tokens) con Spring Security.
* **Gestión de Tópicos (CRUD)**:
    * Crear nuevos tópicos.
    * Listar todos los tópicos existentes.
    * Listar todos los tópicos existentes con **paginación y ordenamiento**.
    * Obtener detalles de un tópico específico.
    * Actualizar tópicos existentes.
    * Eliminar tópicos.
* **Validación de Datos**: Validación de entrada de datos para asegurar la integridad y consistencia.
* **Persistencia de Datos**: Uso de JPA y Hibernate para la interacción con una base de datos MySQL.

## 🛠️ Tecnologías Utilizadas

* **Backend**:
    * Java 17
    * Spring Boot 3.x
    * Spring Data JPA
    * Spring Security
    * Maven (Gestor de dependencias)
* **Base de Datos**:
    * MySQL
* **Autenticación**:
    * JWT (JSON Web Tokens)

## ⚙️ Configuración del Entorno de Desarrollo

Para levantar este proyecto localmente, sigue los siguientes pasos:

### 1. Requisitos Previos

Asegúrate de tener instalado lo siguiente:

* **JDK 17** o superior.
* **Maven** 3.x o superior.
* **MySQL Server** 8.x o superior.
* Un IDE como IntelliJ IDEA, Eclipse o VS Code con soporte para Spring Boot.

### 2. Clonar el Repositorio


git clone [https://github.com/briancdevita/forohubalura.git](https://github.com/briancdevita/forohubalura.git)
cd forohubalura


3. Configuración de la Base de Datos
Crea una base de datos MySQL para el proyecto (ej. forohub_db).

SQL

CREATE DATABASE forohub_db;
Actualiza el archivo src/main/resources/application.properties (o application.yml) con tus credenciales de base de datos:

Properties

# src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/forohub_db
spring.datasource.username=tu_usuario_mysql
spring.datasource.password=tu_contraseña_mysql
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate DDL-Auto (solo para desarrollo, usar 'validate' o migraciones en producción)
spring.jpa.hibernate.ddl-auto=update # O 'create-drop' si quieres recrear el esquema en cada inicio
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Configuración de JWT para Spring Security
# Genera una clave secreta fuerte y guárdala aquí
api.security.token.secret=TU_SUPER_SECRETO_JWT_AQUI_DEBE_SER_LARGO_Y_COMPLEJO
¡Importante! Genera una clave secreta JWT robusta y no la expongas en un repositorio público para producción. Para desarrollo, una cadena larga es suficiente.

4. Construir el Proyecto
Navega a la raíz del proyecto en tu terminal y ejecuta:

Bash

mvn clean install
Esto descargará las dependencias y construirá el proyecto.


5. Ejecutar la Aplicación
Puedes ejecutar la aplicación desde tu IDE o usando Maven:

Bash

mvn spring-boot:run
La aplicación se iniciará en http://localhost:8000 (o el puerto configurado).

💡 Endpoints de la API
La API de Forohub expone los siguientes endpoints:

Autenticación
POST /login

Cuerpo de la solicitud:

JSON

{
    "username": "nombre_de_usuario",
    "password": "tu_contraseña"
}
Respuesta (200 OK):

JSON

{
    "token": "eyJhbGciOiJIUzI1NiIsIn..."
}
Tópicos (requiere autenticación JWT en el header Authorization: Bearer <token>)
POST /topicos

Crea un nuevo tópico.

Cuerpo de la solicitud:

JSON

{
    "titulo": "Título del nuevo tópico",
    "mensaje": "Este es el mensaje del tópico.",
    "curso": "Nombre del Curso"
    // El ID del autor se tomará del token JWT
}
Respuesta (201 Created): Detalles del tópico creado.

GET /topicos

Lista todos los tópicos existentes.

Respuesta (200 OK): Una lista de objetos DatosDetalleTopico.

GET /topicos/{id}

Obtiene los detalles de un tópico específico por su ID.

Respuesta (200 OK): Detalles del tópico.

Respuesta (404 Not Found): Si el tópico no existe.

PUT /topicos/{id}

Actualiza un tópico existente por su ID.

Cuerpo de la solicitud:

JSON

{
    "titulo": "Nuevo título (opcional)",
    "mensaje": "Nuevo mensaje (opcional)",
    "status": "ABIERTO" // o "CERRADO"
}
Respuesta (200 OK): Detalles del tópico actualizado.

Respuesta (404 Not Found): Si el tópico no existe.

Respuesta (403 Forbidden): Si el usuario autenticado no es el autor del tópico.

DELETE /topicos/{id}

Elimina un tópico por su ID.

Respuesta (204 No Content): Tópico eliminado exitosamente.

Respuesta (404 Not Found): Si el tópico no existe.

Respuesta (403 Forbidden): Si el usuario autenticado no es el autor del tópico.


📄 Documentación de la API (Swagger UI)
Una vez que la aplicación esté corriendo, puedes acceder a la documentación interactiva de la API a través de Swagger UI:

URL: http://localhost:8000/swagger-ui.html

Aquí podrás ver todos los endpoints, sus parámetros, modelos de respuesta, y probar las solicitudes directamente desde el navegador




🤝 Contribuciones
Las contribuciones son bienvenidas. Si encuentras un error o tienes una mejora, no dudes en abrir un issue o enviar un pull request.

📄 Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

briancdevita


```bash


