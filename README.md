# API Ventas - Innovatech Chile (EV2-DEVOPS)

Este repositorio contiene el microservicio de **Gestión de Ventas** desarrollado en Spring Boot para la empresa Innovatech Chile. El proyecto es parte integral de la Evaluación Parcial N°2 de la asignatura Introducción a Herramientas DevOps.

## 🚀 Descripción del Proyecto
La API de Ventas es el componente encargado de procesar las transacciones comerciales y el registro de ventas de la plataforma. Opera bajo una arquitectura de microservicios, utilizando contenedores Docker para asegurar un despliegue consistente y automatizado en la nube de AWS.

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Java 17.
* **Framework:** Spring Boot 3.4.4.
* **Gestor de Dependencias:** Maven.
* **Base de Datos:** MySQL 8.0.
* **Contenedorización:** Docker (Multi-stage builds).
* **Orquestación:** Docker Compose.
* **CI/CD:** GitHub Actions.
* **Infraestructura:** AWS (EC2, ECR, SSM).

## 📦 Contenedorización e Infraestructura

### Dockerfile (Multi-stage & Seguridad)
Siguiendo los estándares de la pauta de evaluación, el `Dockerfile` aplica optimizaciones avanzadas:
1.  **Multi-stage Build:** Utiliza una imagen de Maven para la construcción del artefacto y una imagen JRE Alpine mínima para la ejecución, garantizando una imagen final ligera y eficiente.
2.  **Usuario No-Root:** La aplicación se ejecuta bajo el usuario `springuser`, limitando los privilegios dentro del contenedor para aumentar la seguridad.
3.  **Gestión de Capas:** Se optimiza la descarga de dependencias para aprovechar la caché de Docker, reduciendo los tiempos de construcción en el pipeline.

### Docker Compose
El microservicio se integra en el stack definido en el `docker-compose.yml`, destacando:
* **Persistencia de Datos:** El servicio de base de datos MySQL utiliza un *Named Volume* (`db-data`) para garantizar que la información de las ventas persista tras reinicios o actualizaciones.
* **Comunicación Interna:** Utiliza la red `back-tier` para comunicarse de forma segura con la base de datos mediante el nombre de servicio `mysql-db`.
* **Configuración Segura:** Las credenciales y URLs de conexión se gestionan mediante variables de entorno inyectadas desde el archivo `.env`.

## ⚙️ Configuración y Ejecución Local

### Prerrequisitos
* Docker y Docker Compose instalados.
* Archivo `.env` con las variables correspondientes (`DB_USER`, `DB_PASSWORD`, `ECR_REPO_URL_VENTAS`).

### Comandos de Ejecución
```bash
# Levantar el microservicio de ventas
docker compose up -d api-ventas

# Seguimiento de logs en tiempo real
docker logs -f api-ventas

# Detener el servicio
docker compose stop api-ventas
```
## 🔄 Pipeline CI/CD (GitHub Actions)
El flujo de despliegue automatizado se activa con cada `push` a la rama `deploy`:

1. **Build & Push:** La imagen se construye y se sube automáticamente a Amazon ECR.
2. **Deploy Automatizado:** Mediante **AWS Systems Manager (SSM)**, el pipeline envía las instrucciones a la instancia EC2 para descargar la imagen actualizada y reiniciar el contenedor de ventas de forma quirúrgica, manteniendo la disponibilidad de los demás servicios.

## 🛡️ Seguridad en AWS
* **Aislamiento de Red:** Al igual que el resto del backend, este servicio reside en una subred privada.
* **Control de Acceso:** Los Security Groups están configurados para permitir el tráfico entrante únicamente desde el Frontend a través del puerto configurado (**8082**).