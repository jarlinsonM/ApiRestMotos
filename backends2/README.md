Esta API REST proporciona endpoints para gestionar información sobre motos, incluyendo detalles sobre marcas, modelos y paclas.
A continuación se detallan los pasos para ejecutar el proyecto localmente:

Clone el repositorio desde GitHub a su máquina local

El proyecto utiliza una base de datos para almacenar la información. Asegúrese de tener una base de datos disponible y configure las credenciales de conexión en el archivo `application.properties`.

Uso
La API expone los siguientes endpoints:

GET /motos: Obtiene todas las motos almacenadas.
GET /motos/:id: Obtiene una moto específica por su ID.
POST /motos: Crea una nueva moto.
PUT /motos/:id: Actualiza los detalles de una moto existente.
DELETE /motos/:id: Elimina una moto existente.
