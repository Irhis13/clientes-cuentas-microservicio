# Microservicio de Gestión de Cuentas Bancarias 
Proyecto que proporciona una API REST para la gestión de clientes y cuentas bancarias.

## Funcionalidades

- **Gestión de clientes**: Listar, buscar y filtrar clientes según su edad o la suma total de sus cuentas bancarias, incluyendo la obtención de un cliente por DNI.
- **Gestión de cuentas bancarias**: Permite crear nuevas cuentas, actualizar el saldo total y crear automáticamente el cliente asociado si aún no existe en la base de datos.
- **Documentación integrada**: Incluye una interfaz Swagger UI y un esquema OpenAPI YAML para explorar y probar los endpoints directamente desde el navegador.
- **Base de datos en memoria**: Utiliza H2 para almacenar los datos temporalmente al ejecutar la aplicación.

---

## Tecnologías utilizadas

- **Lenguaje**: Java 21
- **Framework**: Spring Boot 3
- **Base de datos**: H2
- **ORM**: Hibernate y Spring Data JPA
- **Build Tool**: Maven
- **Testing**: JUnit 5, Mockito, AssertJ y MockMvc
- **Documentación**: Springdoc OpenAPI y Swagger UI

## Ejecución del proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/Irhis13/clientes-cuentas-microservicio.git
```
### 2. Configura la base de datos
- src/main/resources/application.properties.

### 3. Ejecuta el proyecto

```bash
mvn spring-boot:run
```

## Ejecución de tests

Para ejecutar los tests unitarios y de integración:

```
mvn test
```

# Endpoints disponibles
```plaintext

Módulo               | Endpoint                                       | Descripción
---------------------|------------------------------------------------|------------------------------------------------------------
Clientes             | GET  /clientes                                 | Obtiene la lista completa de clientes con sus cuentas
Clientes             | GET  /clientes/mayores-de-edad                 | Obtiene los clientes mayores de edad (≥ 18 años)
Clientes             | GET  /clientes/con-cuenta-superior-a/{cantidad}| Obtiene los clientes cuya suma de cuentas supera el valor indicado
Clientes             | GET  /clientes/{dni}                           | Obtiene un cliente específico por su DNI
Cuentas Bancarias    | POST /cuentas                                  | Crea una nueva cuenta bancaria (y el cliente si no existe)
Cuentas Bancarias    | PUT  /cuentas/{idCuenta}                       | Actualiza el saldo total de una cuenta existente

```
---
## Ejemplos de peticiones
### 1. Listar todos los clientes:
#### Endpoint: GET /clientes
#### Respuesta exitosa: 200 OK
```json
[
  {
    "dni": "11111111A",
    "nombre": "Juan",
    "apellido1": "Pérez",
    "apellido2": "López",
    "fechaNacimiento": "1959-09-12",
    "cuentas": [
      { "id": 1, "tipoCuenta": "PREMIUM", "total": 150000.0 },
      { "id": 2, "tipoCuenta": "NORMAL", "total": 20000.0 }
    ]
  },
  {
    "dni": "22222222B",
    "nombre": "Raúl",
    "apellido1": "Canales",
    "apellido2": "Rodríguez",
    "fechaNacimiento": "1985-03-01",
    "cuentas": [
      { "id": 3, "tipoCuenta": "NORMAL", "total": 50000.0 }
    ]
  }
]
```
---
### 2. Obtener clientes mayores de edad:
#### Endpoint: GET /clientes/mayores-de-edad
#### Respuesta exitosa: 200 OK
```json
[
  {
    "dni": "11111111A",
    "nombre": "Juan",
    "apellido1": "Pérez",
    "apellido2": "López",
    "fechaNacimiento": "1959-09-12"
  },
  {
    "dni": "22222222B",
    "nombre": "Raúl",
    "apellido1": "Canales",
    "apellido2": "Rodríguez",
    "fechaNacimiento": "1985-03-01"
  }
]
```
---
### 3. Obtener clientes con suma de cuentas superior a una cantidad:
#### Endpoint: GET /clientes/con-cuenta-superior-a/{cantidad}
#### Ejemplo: /clientes/con-cuenta-superior-a/50000
#### Respuesta exitosa: 200 OK
```json
[
  {
    "dni": "11111111A",
    "nombre": "Juan",
    "apellido1": "Pérez",
    "apellido2": "López",
    "fechaNacimiento": "1959-09-12"
  }
]
```
---
### 4. Crear una nueva cuenta:
#### Endpoint: POST /cuentas
#### Body de la petición:
```json
{
  "dniCliente": "11111111X",
  "tipoCuenta": "NORMAL",
  "total": 70000
}
```
#### Respuesta exitosa: 201 Created
```json
{
  "id": 8,
  "tipoCuenta": "NORMAL",
  "total": 70000.0
}
```
---
### 5. Obtener cliente por DNI:
#### Endpoint: GET /clientes/{dni}
#### Ejemplo: /clientes/11111111A
#### Respuesta exitosa: 200 OK
```json
{
  "dni": "11111111A",
  "nombre": "Juan",
  "apellido1": "Pérez",
  "apellido2": "López",
  "fechaNacimiento": "1959-09-12",
  "cuentas": [
    { "id": 1, "tipoCuenta": "PREMIUM", "total": 150000.0 },
    { "id": 2, "tipoCuenta": "NORMAL", "total": 20000.0 }
  ]
}
```
---
### 6. Actualizar saldo de una cuenta:
#### Endpoint: PUT /cuentas/{idCuenta}
#### Ejemplo: /cuentas/8
#### Body de la petición:
```json
{
  "total": 180000
}
```
#### Respuesta exitosa: 204 No Content

---




