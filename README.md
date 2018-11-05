# Ejecutar MagnetoRestService

#### Pre-Requisitos:<br />
    Java8, Maven, Postman (o alguna herramienta parecida), Mongobd (de querer consumir local)

#### Pasos para la ejecución:
  1 - Descargar el proyecto MagnetoRestServices.

  2 - Abrir un CMD.

  3 - Ingresar la siguiente línea "cd <PATH_DE_DESCARGA>\MagnetoRestServices" para ubicarse en la carpeta del proyecto.

  4 - Ingresar la siguiente línea "mvn spring-boot:run" para ejecutar la aplicación.

  5 - Esperar a que el servicio levante.

  6 - Abrir Postman e ingrese las siguientes urls:<br />
      a) http://localhost:8080/stats (GET)<br />
      b) http://localhost:8080/mutant (POST)

  7.a - Para ejecutar a) con solo ingresar la url en Postman indicando el tipo de llamado GET.

  7.b - Para ejecutar b) deberá ingresar la url seleccionar el tipo de llamado POST, ingresar un body(war) y seleccionar "application/json" e ingresar una matriz con el siguiente formato:<br />
      {<br />
      "dna":["AGCTAA","CTAGCC","TCGAGT","GATCTG","GGGTTT","AAACCC"]<br />
      }<br />
##### NOTA: "dna" siempre tiene que estar, solo se permite una matriz de 6x6 y solo pueden ser las letras A, T, C y G.

  8 - Dar Send en el Postman, posibles respuestas:<br />
      a) Respuesta método stats: {"count_mutant_dna": <Cantidad de Mutantes>, "count_human_dna": <Cantidad de Humanos>, "ratio": <Ratio Mutantes-Humanos>}<br />
      b) Respuesta método mutant: HTTP 200-OK (Si es Mutante) y HTTP 403-FORBIDDEN (Si es Humano)

# Ejecucion de Prueba

#### Pasos para la ejecución:<br />
  1 - Debe estar corriendo el proyecto.
  2 - Ubicarse en cd <PATH_DE_DESCARGA>\MagnetoRestServices"  y ejecutar el siguiente comando "mvn clean test".

# Google App Engine

#### Pasos para la ejecución:<br />
  1 - Abrir Postman e ingrese las siguientes urls:<br />
      a) https://examenmeli1.appspot.com/stats(GET)<br />
      b) https://examenmeli1.appspot.com/mutant(POST)

  2 - Seguir en los pasos 7.a o 7.b, según sea el caso.

# DATABASE MONGODB

#### Pasos para conectar una DB local<br />
La aplicación actualmente usa MongoDB Atlas . Si se desea cambiar por una DB Mongodb local seguir estos pasos:<br />
  1 - Abrir el archivo "<PATH_DE_DESCARGA>\MagnetoRestService\src\main\resources\application.properties".<br />

  2 - Comentar toda la línea correspondiente a el placeholder "spring.data.mongodb.uri".<br />

  3 - Agregar las siguientes líneas:<br />
        spring.data.mongodb.host=localhost<br />
        spring.data.mongodb.port=27017

  4 - Volver a ejecutar la aplicación.

##### NOTA 1: Si se usa local no debería tener Autenticación, de ser así hay de agregar más configuraciones.
##### NOTA 2: No es necesario importar alguna DB, la aplicación creara la DB y las collections cuando lo necesite.
##### NOTA 3: EL nombre de la DB es "magneto" y la collection es "persona".
