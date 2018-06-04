# marx-ingestion

POC de ingesta de archivos planos sin separadores,

El proceso que se prueba se llama DOIMMS y es basicamente un archivo mensual que reciben las aseguradoras (en este caso
profuturo GNP), el cual como sus siglas lo indican es una dispersion ordinaria de todas las aportaciones mensuales que 
realizan los clientes de GNP a las diferentes subcuentas de retiro, o algo asi.

El flujo es mediante dos archivos de entrada, un csv el cual le llamaremos template (schema) que es basicamente una 
tablita que te dice que campos son, que posiciones tienen, su descripcion, su expresion regular para validacion y 
otras reglas que no me acuerdo.

El segundo archivo es el principal, es basicamente un txt con extension dat el cual tiene la siguiente estructura:

linea 01: Encabezado es la linea que contiene los datos del proceso, como el tipo de dispersion, fechas de control,
 identificadores de applicaciones de origen de los datos y bla bla bla, (en su momento llegaron a existir aprox 20 
 procesos distintos de dispersiones, IMSS, ISSSTE, PROCESAR, BANCOMER, BANAMEX, etc), esta linea es unica y SIEMPRE ES
 LA PRIMERA LINEA DEL ARCHIVO.
 
Linea 02: Detalle individial, esta linea es un registro comun de el archivo, contiene los datos de 1 usuario o cliente y
contiene todos los datos personales, cuentas, cantidades, deudas, limites de pago, fechas, subcuentas de retiro, entre
muchos otros datos mas. ESTE TIPO DE LINEA SE REPITE N VECES.

Linea 08: Cuentas por pagar, dependiendo el tipo de proceso y de cuenta, se puede repetir varias veces, esta linea 
contiene las sumatorias de cierto tipo de subcuenta de TODAS LAS LINEAS 02, ejemplo, si esta linea concentra la sumatoria
de la Subcuenta Tipo de vivienda en garantia, entonces el total de esta linea debera de coincidir con la suma de todas
las lineas 02 para ese campo en especifico.

Linea 09: Sumario, esta linea es la ULTIMA LINEA DEL ARCHIVO, es unica, y debe de coincidir en fechas con el Encabezado,
el total de lineas 02 y 08 deben coincidir con los contadores de esta linea ademas de los totatles de todas las cuentas 
por pagar (sumatoria de todas las lineas 08).

PASOS PARA EJECUTAR POC

Dentro del objeto Launcher añadir ruta del template o schema dentro del metodo main:

    Template.cargaTemplate("~/Development/data/schema/DOIMSSTemplate.csv")

 
De igual manera dentro de main añadir ruta del archivo a ingestar en la sig linea:

    in.cargaArchivoProceso("~/Development/data/in/doimss.dat")

Denle Run a Launcher y crucen los dedos.

Si todo salio bien les debera de generar dos csv con los resultados de validaciones de estructura y contenido,
si solo estan las cabeceras es que el archivo no presenta errores.