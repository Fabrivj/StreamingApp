# StreamingApp
Programación Patrones

Fabricio Vargas Jimenez
Andrey Martinez Obando
Andrey Bonilla Matamoros 
Jonathan Méndez Herrera
Luis Vega Araya
# Vimeo

- Crear una cuenta de vimeo (https://vimeo.com/log_in)
- Crear una app https://developer.vimeo.com/apps
- Generar personal token
# Características del Sistema
- **Patrón State:** Implementación de un sistema de estados de sesión que permite cambiar entre los siguientes estados:
- **EstadoNoAutenticado:** Usuario no autenticado, permite iniciar sesión.
- **EstadoAutenticado:** Usuario autenticado, permite acceder a los servicios y a la API de Vimeo.
- **EstadoSesionExpirada:** La sesión ha expirado, solicitando una nueva autenticación.
- **Autenticación Segura con Tokens:** Al iniciar sesión, se genera un token de acceso que se utiliza para interactuar con la API de Vimeo.
- **Interacción con Vimeo:** Permite realizar solicitudes autenticadas a Vimeo mediante el token generado.
# Estructura del proyecto
- **EstadoAutenticacion:** Interfaz que define los métodos iniciarSesion(), accederServicio(), y cerrarSesion().
- **ContextoAutenticacion:** Clase que gestiona el estado actual y permite los cambios entre estados según el flujo de autenticación.
- **Estados de Autenticación:**
-  **EstadoNoAutenticado:** Permite al usuario iniciar sesión y cambiar al estado EstadoAutenticado.
-  **EstadoAutenticado**: Permite acceso a la API de Vimeo y verifica la validez del token.
-  **EstadoSesionExpirada:** Solicita una nueva autenticación al detectar que el token ha expirado.
-  **Patron Decorador:** Se implementaron varios decoradores en diferentes sitios para que con la combinación de decoradores, cada vez que llames a decoratedService.consultar(), se ejecutarán todos los comportamientos adicionales en el orden en que los has decorado: Logging: Registra la consulta, Caché: Intenta obtener los resultados de la caché si ya se hizo una consulta con el mismo término.
-  **Patron Facade:** Se implemento en la clase StreamingServiceManager: Actúa como el Facade, proporcionando métodos simplificados para interactuar con los servicios de streaming.
- VimeoAPI: Clase para gestionar las solicitudes a la API de Vimeo, utilizando el token generado tras la autenticación.
- Clases para ejecutar el  patron de Proxy se implementan dentro de la carpeta org.search. La funcion de este patron es  controlar el acceso a los servicios de streaming, limitando el acceso según permisos y suscripciones.

# Uso del proyecto
- **Iniciar Sesión:** Usa el método iniciarSesion en ContextoAutenticacion con usuario y contraseña para autenticarte y generar un token.
- **Acceder a Vimeo:** Tras autenticarse, utiliza el método accederServicio para enviar solicitudes a Vimeo, verificando el token de acceso. Si el token ha expirado, el sistema cambia automáticamente al estado EstadoSesionExpirada.
- **Cerrar Sesión:** Usa el método cerrarSesion para invalidar el token y volver al estado EstadoNoAutenticado.
   
