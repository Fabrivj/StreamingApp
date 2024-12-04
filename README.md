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
# Autenticación Segura con Tokens:
Al iniciar sesión, el sistema genera un token de acceso.
Este token se utiliza para interactuar con la API de Vimeo.
La validez del token se verifica constantemente para mantener la seguridad.
# Interacción con Vimeo y Watchmode:
El sistema permite realizar solicitudes autenticadas a Vimeo/Watchmode utilizando el token generado.
Las solicitudes se manejan a través de un diseño orientado a patrones para mantener un código limpio y modular.
# Estructura del Proyecto
Clases y Patrones Implementados:
# 1. Patrón State:
EstadoAutenticacion:
Interfaz que define los métodos iniciarSesion(), accederServicio() y cerrarSesion().
ContextoAutenticacion:
Clase que gestiona el estado actual de autenticación y permite cambios entre estados según el flujo de trabajo.
Estados Específicos:
EstadoNoAutenticado: Permite al usuario iniciar sesión y transicionar al estado autenticado.
EstadoAutenticado:
Proporciona acceso a la API de Vimeo.
Verifica la validez del token.
EstadoSesionExpirada:
Detecta un token inválido o caducado.
Solicita una nueva autenticación.
# 2. Patrón Decorador:
Este patrón se implementa para agregar comportamientos adicionales de manera dinámica a las funcionalidades de búsqueda de servicios.
Ejemplo de decoradores implementados:
Logging: Registra cada consulta realizada.
Caché: Almacena resultados de consultas anteriores para reducir el tiempo de respuesta en búsquedas repetidas.
Cada vez que se llama a decoratedService.consultar(), se ejecutan los comportamientos adicionales en el orden en que se aplicaron los decoradores.
# 3. Patrón Facade:
La clase StreamingServiceManager actúa como un Facade, proporcionando métodos simplificados para interactuar con los servicios de streaming.
Esto oculta la complejidad interna y ofrece una interfaz clara y directa para los usuarios.
# 4. Patrón Strategy:
SearchStrategy: Interfaz que define los métodos search(String query) y recommend(String userPreferences).
Implementaciones específicas:
VimeoSearchStrategy: Estrategia para realizar búsquedas y recomendaciones en Vimeo.
API2SearchStrategy: Alternativa para otras plataformas de streaming.
# SearchContext:
Permite cambiar dinámicamente entre estrategias según la plataforma seleccionada.
Mantiene una referencia a la estrategia actual y delega las solicitudes a esta.
Ejemplo: El usuario selecciona una estrategia en tiempo de ejecución, como Vimeo o una API alternativa, y el sistema adapta su lógica de búsqueda automáticamente.
# 5. Patrón Proxy:
Las clases relacionadas con este patrón están en el paquete org.search.
Funcionalidad del Proxy:
Controlar el acceso a los servicios de streaming según los permisos del usuario.
Validar roles, como usuarios "premium" o "básicos", para permitir o denegar el acceso a contenido específico.
Proporcionar un punto de control para la gestión de suscripciones y permisos.
Implementación:
StreamingPlatformService: Interfaz común para los servicios de streaming.
VideoStreamingService: Implementa la lógica real de acceso al contenido.
Proxy dinámico:
Antes de delegar la solicitud al servicio real, el proxy verifica si el usuario tiene los permisos necesarios.
Si no tiene acceso, muestra un mensaje apropiado y evita realizar operaciones no permitidas.
# Patron Factory
En este proyecto, se implementa el patrón Factory para crear instancias de usuarios con diferentes roles (Administrador, Básico, Premium). La clase UsuarioFactory se encarga de crear el tipo de usuario adecuado según el parámetro de tipo recibido, sin necesidad de instanciar directamente las clases específicas. Esto permite una mayor flexibilidad y facilita la adición de nuevos tipos de usuario en el futuro.
   
