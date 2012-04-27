# svrKpax

svrKpax son unos servicios creados para la interacción de diferentes aplicaciones.

Estos servicios interactúan mediante unos plugins de elgg y las aplicaciones que sean necearías.

# Requisitos

Para un correcto funcionamiento se necesita:

mysql 5
jboss 4.2.3
php 5
java 1.6
maven 3.0.3 >

# Instalación

La instalación es muy sencilla, primero se descarga el proyecto

    git checkout o descarga por otras vías 
    
Una vez descargado se configura el pom.xml y se configura donde se despliega la aplicación. Cuando todo este configurado se ejecuta el siguiente comando

	mvn install

Lo que realiza es descargar todas las librerías necesarias para que funcione la aplicación, una vez descargadas  si es preciso visualizar en eclipse correctamente se ejecuta

	mvn eclipse:eclipse

Y por ultimo para crear una nuevo paquete desplegable nuevo de la aplicación

	mvn -Denv=local clean package

-Denv=local es el entorno donde se ejecuta, se pueden crear todos los que se quiera, todo esto se configura en pom.xml

# MYSQL

En la carpeta doc/sql esta el script de creación de base de datos

Una vez creada hay que configurar los credenciales, en la misma carpeta hay un archivo que se llama srvKpax-ds.xml, debe configurarse con los datos del servidor propio.

Este archivo se guarda donde se despliega la aplicacion, por ejemplo:

	/home/server/jboss/server/default/deploy/*

# INCIDENCIAS

Si tenéis alguna incidencia no dudéis enviar un correo a vuestro responsable

# LICENSE

Universitat Oberta de catalunya