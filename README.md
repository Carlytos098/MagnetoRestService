# Ejecutar MagnetoRestService

Pre-Requisitos:
    Java8, Maven, Postman (O alguna herramienta parecida), Mongobd (De quere consumir local)

Pasos para la ejecución:
  1 - Descargar el proyecto MagnetoRestServices.

  2 - Abrir un CMD.

  3 - Ingresar la siguiente línea "cd <PATH_DE_DESCARGA>\MagnetoRestServices" para ubicarse en la carpeta del proyecto.

  4 - Ingresar la siguiente línea "mvn spring-boot:run" para ejecutar la aplicación.

  5 - Esperar a que el servicio levante.

  6 - Abrir Postman e ingrese las siguientes urls:
      a) http://localhost:8080/stats (GET)
      b) http://localhost:8080/mutant (POST)

  7.a - Para ejecutar a) con solo ingresar la url en Postman indicando el tipo de llamado GET.

  7.b - Para ejecutar b) deberá ingresar la url seleccionar el tipo de llamado POST, ingresar un body(war) y seleccionar "application/json" e ingresar una matriz con el siguiente formato:
      {
      "dna":["AGCTAA","CTAGCC","TCGAGT","GATCTG","GGGTTT","AAACCC"]
      }
      NOTA: "dna" siempre tiene que estar, solo se permite una matriz de 6x6 y solo pueden ser las letras A, T, C y G.

  8 - Dar Send en el Postman, posibles respuestas:
      a) Respuesta método stats: {"count_mutant_dna": <Cantidad de Mutantes>, "count_human_dna": <Cantidad de Humanos>, "ratio": <Ratio Mutantes-Humanos>}
      b) Respuesta método mutant: HTTP 200-OK (Si es Mutante) y HTTP 403-FORBIDDEN (Si es Humano)

# Google App Engine

Pasos para la ejecución:
  1 - Abrir Postman e ingrese las siguientes urls:
      a) https://examenmeli1.appspot.com/stats(GET)
      b) https://examenmeli1.appspot.com/mutant(POST)

  2 - Seguir en los pasos 7.a o 7.b, según sea el caso.

# DATABASE MONGODB
La aplicación actualmente usa MongoDB Atlas . Si se desea cambiar por una DB Mongodb local seguir estos pasos:
  1 - Abrir el archivo "<PATH_DE_DESCARGA>\MagnetoRestService\src\main\resources\application.properties".
  2 - Comentar toda la línea correspondiente a el placeholder "spring.data.mongodb.uri".
  3 - Agregar las siguientes líneas:
        spring.data.mongodb.host=localhost
        spring.data.mongodb.port=27017
  4 - Volver a ejecutar la aplicación.

NOTA 1: Si se usa local no debería tener Autenticación, de ser así hay de agregar más configuraciones.
NOTA 2: No es necesario importar alguna DB, la aplicación creara la DB y las collections cuando lo necesite.
NOTA 3: EL nombre de la DB es "magneto" y la collection es "persona".
