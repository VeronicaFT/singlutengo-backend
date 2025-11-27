# SinGluten&GO – Backend 🍽️🌍

Backend de la aplicación **SinGluten&GO**, una plataforma pensada para ayudar a personas con enfermedad celíaca o sensibilidad al gluten a encontrar establecimientos que ofrezcan opciones sin gluten de forma segura.

Este proyecto expone una **API REST** que gestiona usuarios, establecimientos y valoraciones y sirve como base para el frontend de la aplicación.

Proyecto desarrollado como parte de mi **Proyecto Final de Ciclo de DAW**.

---

## 🧩 Descripción del proyecto

El backend se encarga de:

- Gestionar la información de **establecimientos** (por ejemplo, restaurantes, cafeterías, heladerías… con opciones sin gluten).
- Almacenar y consultar **valoraciones** que los usuarios realizan sobre esos establecimientos.
- Gestionar **usuarios** registrados en la plataforma.
- Proveer un endpoint de **autenticación** que genera un token JWT para el inicio de sesión.
- Exponer una serie de **endpoints REST** que consumirá el frontend de SinGluten&GO.

Este repositorio corresponde únicamente a la **parte servidor (API)** del proyecto.

---

## 🛠️ Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3** (Spring Web, Spring Data JPA, Spring Security)
- **MySQL** como base de datos
- **Hibernate / JPA**
- **Jakarta Validation** para validación de datos
- **JWT (JSON Web Tokens)** para autenticación.
- **Maven** como gestor de dependencias

---

## 📦 Requisitos previos

Antes de ejecutar el proyecto necesitas tener instalado:

- **JDK 17**
- **Maven**
- **MySQL** (u otra base de datos compatible, ajustando la configuración)
- **Git** (recomendado para clonar el repositorio)

---

## ⚙️ Configuración de la base de datos

La configuración de conexión se define en el fichero:

- `src/main/resources/application.properties`

Ejemplo de configuración:

    spring.datasource.url=jdbc:mysql://localhost:3306/singlutengo?useSSL=false&serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

Antes de arrancar el proyecto:

1. Crea la base de datos `singlutengo` (o el nombre que prefieras) en tu servidor MySQL.
2. Ajusta `username` y `password` a tu entorno local.
3. Si cambias el nombre de la base de datos o el puerto, actualiza también la URL.

---

## 🚀 Puesta en marcha del proyecto

1. Clonar el repositorio:

    git clone https://github.com/VeronicaFT/singlutengo-backend.git  
    cd singlutengo-backend

2. Configurar la base de datos:

    - Crea la base de datos en MySQL.
    - Revisa y ajusta `src/main/resources/application.properties`.

3. Compilar el proyecto:

    mvn clean install

4. Ejecutar la aplicación:

    mvn spring-boot:run

5. Acceder a la API:

    Por defecto, el backend estará disponible en:

    http://localhost:8080

(Si cambias el puerto en la configuración, actualiza esta URL.)

---

## 📚 Endpoints principales

La API está organizada por recursos: **autenticación**, **usuarios**, **establecimientos** y **valoraciones**.

### 🔐 Autenticación

- **POST /auth/login**  
  Recibe las credenciales del usuario (por ejemplo, email y contraseña) en el cuerpo de la petición y devuelve:
  - Un **token JWT** válido para autenticar las peticiones.
  - Información básica del usuario autenticado (nombre, tipo de usuario, id).

Si las credenciales no son correctas, devuelve un mensaje de error.

---

### 👤 Usuarios

Base de la ruta: `/api/usuarios`

- **GET /api/usuarios**  
  Devuelve el listado de todos los usuarios registrados.

- **GET /api/usuarios/{id}**  
  Devuelve los datos de un usuario concreto por su ID.

- **POST /api/usuarios**  
  Crea o actualiza un usuario a partir de los datos enviados en el cuerpo de la petición.

- **DELETE /api/usuarios/{id}**  
  Elimina un usuario por su ID.

---

### 🏪 Establecimientos

Base de la ruta: `/api/establecimientos`

- **GET /api/establecimientos**  
  Devuelve el listado de todos los establecimientos registrados.

- **GET /api/establecimientos/{id}**  
  Devuelve el detalle de un establecimiento concreto por su ID.

- **POST /api/establecimientos**  
  Crea o actualiza un establecimiento a partir de los datos enviados en el cuerpo de la petición.

- **DELETE /api/establecimientos/{id}**  
  Elimina un establecimiento por su ID.

*(El frontend usa estos endpoints para mostrar listados de sitios sin gluten, detalles de cada sitio, etc.)*

---

### ⭐ Valoraciones

Base de la ruta: `/api/valoraciones`

- **GET /api/valoraciones**  
  Devuelve el listado de todas las valoraciones.

- **GET /api/valoraciones/{id}**  
  Devuelve el detalle de una valoración concreta por ID.

- **POST /api/valoraciones**  
  Crea una nueva valoración para un establecimiento.

- **DELETE /api/valoraciones/{id}**  
  Elimina una valoración por su ID.

- **GET /api/valoraciones/establecimiento/{id}**  
  Devuelve todas las valoraciones asociadas a un establecimiento concreto (por su ID).

*(Este endpoint se usa, por ejemplo, en la pantalla de detalle de un establecimiento para cargar las opiniones de otros usuarios.)*

---

## 🧱 Estructura del proyecto

El proyecto sigue una estructura clásica por capas en Spring Boot:

- `com.singlutengo.entity`  
  Contiene las entidades principales del dominio:
  - `Usuario` → representa a un usuario registrado (datos personales, tipo de usuario, fecha de registro, etc.).
  - `Establecimiento` → representa un local que ofrece opciones sin gluten.
  - `Valoracion` → representa una valoración (puntuación, comentario y fecha) sobre un establecimiento.

- `com.singlutengo.repository`  
  Interfaces de acceso a datos (Spring Data JPA) para cada entidad.

- `com.singlutengo.service`  
  Lógica de negocio que coordina repositorios y controladores.  
  Aquí se encapsulan las operaciones que realizan los controladores.

- `com.singlutengo.controller`  
  Controladores REST que exponen los endpoints descritos anteriormente:
  - `AuthController`
  - `UsuarioController`
  - `EstablecimientoController`
  - `ValoracionController`

- `com.singlutengo.security`  
  Utilidades relacionadas con JWT (por ejemplo, generación y validación de tokens).

- `com.singlutengo.dto`  
  Clases de transferencia de datos como `LoginRequest` para el proceso de autenticación.

Otros archivos importantes:

- `src/main/resources/application.properties` → configuración de la base de datos y propiedades de JPA.  
- `pom.xml` → dependencias del proyecto y configuración de Maven.  

---

## 🔍 Ejemplo de uso

Una vez arrancado el backend:

- El **frontend de SinGluten&GO** puede:
  - Pedir un listado de establecimientos sin gluten en una ciudad.
  - Mostrar el detalle de un establecimiento concreto.
  - Permitir que el usuario deje una valoración.
  - Mostrar valoraciones de otros usuarios.
  - Iniciar sesión y trabajar con un token JWT.

También puedes probar la API con herramientas como:

- Postman  
- Insomnia  
- Navegador (para algunas peticiones GET sencillas)  

---

## 👩‍💻 Autora

**Verónica Flores Torralba**  
Desarrolladora Web Junior

LinkedIn: https://www.linkedin.com/in/veronicaflorestorralva/  
GitHub: https://github.com/VeronicaFT
