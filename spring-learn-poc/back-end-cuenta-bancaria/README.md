# Docker Compose cuenta-bancaria
***
Aplicación Back End para la administración de servicios REST.

## Prerrequisitos para ejecutar el proyecto
***
- Instalar docker

## Proceso de construcción de aplicación
***
1. Clonar el repositorio público con el siguiente comando: git clone --branch "main" https://github.com/edzamo13/bcoPichincha
2. Ejecutar el comando: mvn clean install

## Ejecución con Docker Compose

## Start
***
```bash
docker-compose up
```
Para ejecutar en background:
```bash
docker-compose up -d
```

## Stop
```bash
docker-compose down
```

## End Points
***
**Datos Generales:**

	servidor = localhost
	
	puerto = 8081
	
	endPointCliente = api/v1/clientes
	
	endPointCuenta = api/v1/cuentas
	
	endPointMovimiento = api/v1/movimientos
	
	endPointReporte = api/v1/reportes

```bash
1. Clientes
	http://{{servidor}}:{{puerto}}/{{endPointCliente}}
```
```bash
2. Cuentas
	http://{{servidor}}:{{puerto}}/{{endPointCuenta}}
```
```bash
3. Movimientos
	http://{{servidor}}:{{puerto}}/{{endPointMovimiento}}
```
```bash
4. Reportes
	http://{{servidor}}:{{puerto}}/{{endPointReporte}}?fechaInicio=2022-06-25&fechaFin=2022-06-26&idCliente=1
```
## Recursos para pruebas.
```bash
1. Script de base de datos: BaseDatos.sql
2. EndPoints: cuenta-bancaria.postman_collection.json
3. Variables de ambiente: testingEnv.postman_environment.json
```

