# Prueba Tecnica CHIB

Este proyecto se ha realizado con el stack MERN (MongoDB, Express, React, NodeJS) y con el lenguaje kotlin en la app android nativa.

## Requisitos

para iniciar de manera local el proyecto se requiere tener instalado **Mongodb** y **NodeJS** en su version mas reciente:

> 
>

la aplicacion android solo fue diseñada para funcionar en moviles con sistema operativo **Android 10**

## Despues de clonar el repositorio

lo primero que necesitamos es agregar las dependencia de los proyectos de backend y frontend, esto lo hacemos con el siguiente comando en una terminal dentro de la raiz del repositorio:

```
  cd backend && npm i && cd ../frontend && npm i
```

# Iniciar el servidor (backend)

despues de instalar las dependencias de debe ejecutar el siguiente comando para iniciar el servidor, considerando que la consola se encuentra en la raiz del repositorio:

```
cd backend
node ./server/index.js
```

si todo funciona corractamente vera la respuesta:

```
server running on port 3001
database online
```

si esto no funciona por favor asegurese de tener instalado y funcionando **MongoDB**

# Iniciar el cliente web (frontend)

para iniciar el cliente web se debe regresar a la raiz del repositorio y ejecutar el siguiente comando:

```
cd frontend
yarn start
```

si todo funciona corractamente vera la respuesta:

```
Compiled successfully!

You can now view frontend in the browser.

  Local:            http://localhost:3000
  On Your Network:  http://192.168.0.26:3000

Note that the development build is not optimized.
To create a production build, use yarn build.
```

# Endpoints del Backend

**Crear un nuevo usuario**

`localhost:3001/server/users`

> Metodo POST

```
Body Request

{
    "nombre": "Eric Lopez",
    "correo": "eric@zh.com",
    "clave": "12345",
    "telefono" : "661888929",
    "fechaNacimiento" : "18/11/1998",
    "profesion" : "Mecanico",
    "descripcionProfesion" : "Reparacion de vehiculos a domicilio y en mis instalaciones, afinaciones, cambio de frenos, revision electrica, escaneo de computadora",
    "precioPorHora" : "180",
    "avatar": "https://icon-icons.com/icons2/1879/PNG/128/iconfinder-3-avatar-2754579_120516.png"
}
```

```
Response

{
    "usuario": {
        "_id": "5eff51d024244d089faa20e3",
        "nombre": "Eric Lopez",
        "correo": "eric@zh.com",
        "telefono": "661888929",
        "fechaNacimiento": "18/11/1998",
        "profesion": "Mecanico",
        "descripcionProfesion": "Reparacion de vehiculos a domicilio y en mis instalaciones, afinaciones, cambio de frenos, revision electrica, escaneo de computadora",
        "precioPorHora": "180",
        "avatar": "https://icon-icons.com/icons2/1879/PNG/128/iconfinder-3-avatar-2754579_120516.png",
        "__v": 0
    }
}
```

**Obtener lista de usuarios**

`localhost:3001/server/users`

> Metodo GET

```
Response

{
    "usuarios": [
        {
            "_id": "5efe37975467d349b2e0855e",
            "nombre": "Juan Jose Mendez",
            "correo": "j@br.com",
            "telefono": "5512345678",
            "fechaNacimiento": "07/01/1980",
            "profesion": "Piloto",
            "descripcionProfesion": "realizo vuelos de publicidad en avioneta",
            "precioPorHora": "250",
            "avatar": "https://icon-icons.com/icons2/1879/PNG/128/iconfinder-3-avatar-2754579_120516.png",
            "__v": 0
        },
        {
            "_id": "5efe44da5467d349b2e0855f",
            "nombre": "Manuel Velazco",
            "correo": "manuel@correo.com",
            "telefono": "557263636",
            "fechaNacimiento": "07/01/1978",
            "profesion": "Carpintero",
            "descripcionProfesion": "realizo diversos trabajos con madera y reparacion de muebles",
            "precioPorHora": "135",
            "avatar": "https://icon-icons.com/icons2/1879/PNG/128/iconfinder-3-avatar-2754579_120516.png",
            "__v": 0
        },
    ]
}
```

**login de usuario**

`localhost:3001/server/login`

> Metodo POST

```
Body Request

{
    "correo": "j@br.com",
    "clave": "12345"
}
```

```
Response

{
    "ok": true,
    "usuariobd": {
        "_id": "5efe37975467d349b2e0855e",
        "nombre": "Juan Jose Mendez",
        "correo": "j@br.com",
        "telefono": "5512345678",
        "fechaNacimiento": "07/01/1980",
        "profesion": "Piloto",
        "descripcionProfesion": "realizo vuelos de publicidad en avioneta",
        "precioPorHora": "250",
        "avatar": "https://icon-icons.com/icons2/1879/PNG/128/iconfinder-3-avatar-2754579_120516.png",
        "__v": 0
    },
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c3VhcmlvYmQiOnsiX2lkIjoiNWVmZTM3OTc1NDY3ZDM0OWIyZTA4NTVlIiwibm9tYnJlIjoiSnVhbiBKb3NlIE1lbmRleiIsImNvcnJlbyI6ImpAYnIuY29tIiwidGVsZWZvbm8iOiI1NTEyMzQ1Njc4IiwiZmVjaGFOYWNpbWllbnRvIjoiMDcvMDEvMTk4MCIsInByb2Zlc2lvbiI6IlBpbG90byIsImRlc2NyaXBjaW9uUHJvZmVzaW9uIjoicmVhbGl6byB2dWVsb3MgZGUgcHVibGljaWRhZCBlbiBhdmlvbmV0YSIsInByZWNpb1BvckhvcmEiOiIyNTAiLCJhdmF0YXIiOiJodHRwczovL2ljb24taWNvbnMuY29tL2ljb25zMi8xODc5L1BORy8xMjgvaWNvbmZpbmRlci0zLWF2YXRhci0yNzU0NTc5XzEyMDUxNi5wbmciLCJfX3YiOjB9LCJpYXQiOjE1OTM3OTExMDksImV4cCI6MTU5Mzg3NzUwOX0.ubFZmBuR8bTIKAllESKxc21ltSybfmzSLlsrES2PIYI"
}
```

# Rutas del FrontEnd

`http://localhost:3000/register`

`http://localhost:3000/login`

`http://localhost:3000/` -> la raiz solo se ve cuando inicias sesion, de lo contrario sera redirigido al endpoint de login

# Antes de compilar la aplicacion android

> Se requiere cambiar la `baseUrl` dentro del metodo `getApiService()` del archivo `ApiManager` en el proecto principal.

```
val baseUrl = "http://<server-ip>:3001/server/"
```

## Capturas de pantalla de la aplicacion android

 <table style="border: none;">
  <tr>
    <td><img src="https://github.com/ernest0vm/chib_technical_test/blob/master/screenshots/Screenshot_1593796210.png" width="320"></td>
    <td><img src="https://github.com/ernest0vm/chib_technical_test/blob/master/screenshots/Screenshot_1593796236.png" width="320"></td>
    <td><img src="https://github.com/ernest0vm/chib_technical_test/blob/master/screenshots/Screenshot_1593796258.png" width="320"></td>
  </tr>
 </table>