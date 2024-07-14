<h1 align="center">
  LiterAlura
</h1>

![4-catalogos-opac-AbsysNet-con-muchas-ideas-para-aplicar-en-vuestras-bibliotecas](https://github.com/user-attachments/assets/d0db1795-affd-477d-84d8-bc14f6f05dc6)

## Introducción 

Este proyecto consiste en un catalogo de libros implementado en consola con Java, el uso de la API Gutendex, junto con permanencia de datos usando PostgreSQL.

En esta se pueden buscar libros cuyos datos se obtendrán de la API y posteriormente el usuario consultar la lista de los libros o autores almacenados en la base de datos, así como realizar diferentes filtraciones de estos.

## Como se usa

Una vez se ejecuta el programa en la clase [Principal](src/main/java/com/jdvelez/LiterAlura_AluraChallenge/main/Principal.java), nn la consola se observa un menú que ofrece múltiples opciones, en caso de que sea la primera vez que hace uso de este proyecto es recomendable que se seleccione la primera opción (Buscar libro por titulo), esto porque al no haber ningún libro ni autor almacenado las otras funciones no retornaran ningún dato, cabe resaltar que para elegir una opción se debe ingresar el numero correspondiente que se encuentra a la izquierda.

![image](https://github.com/user-attachments/assets/b0962a9c-e081-4650-a402-8b75d80f6ecb)

Una vez se realice la elección de buscar un libro se debe ingresar el nombre del libro como se solicita, se muestran los datos obtenidos del libro, en caso de que el libro ya este almacenado en la base de datos se indicará.

![image](https://github.com/user-attachments/assets/4c6ca9ab-2522-4122-9a88-ce8677d8101b)

Después de buscar y almacenar varios libros se puede solicitar una lista completa de estos con la segunda opción.

![image](https://github.com/user-attachments/assets/39b23db5-5f5c-45cd-a8ac-485fa18f2a48)

Con la opción 3 se mostrará la lista de autores almacenados y los libros con los que están relacionados.

![image](https://github.com/user-attachments/assets/65207a61-d7c6-46b4-9a34-e16130b2c393)

La opción 4 permite obtener los autores que están/estaban vivos en un determinado año.

![image](https://github.com/user-attachments/assets/388efc40-c651-40fb-9120-3ad585beb0b7)

Para la opción 5 se solicita que se ingrese un lenguaje, si esta almacenado en la base de datos se mostraran los libros con dicho lenguaje.

![image](https://github.com/user-attachments/assets/1bad131c-f78d-40bb-abd4-0fc49cdae7f8)

## Referencias

- [Gutendex API](https://gutendex.com/)
- [HttpRequest Java](https://docs.oracle.com/en/java/javase/22/docs/api/java.net.http/java/net/http/HttpRequest.html)
- [Postman API tests](https://www.postman.com/)
- [Gson](https://central.sonatype.com/artifact/com.google.code.gson/gson/2.11.0)

