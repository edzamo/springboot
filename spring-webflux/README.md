# 🚀 ARCHITECTURE, SPRING BOOT & SPRING WEBFLUX MASTER GUIDE: CHEAT SHEET & TECHNICAL INTERVIEW PREPARATION

Este documento es una guía maestra de referencia absoluta diseñada para ingenieros de software avanzados y arquitectos de soluciones sobre el ecosistema **Spring Framework, Spring Boot y Spring WebFlux (Programación Reactiva)**. Consolida fundamentos estructurales, diseño perimetral de seguridad, persistencia, automatización y una inmersión profunda en la arquitectura reactiva no bloqueante orientada a sistemas de alta concurrencia y baja latencia.

---

## 1. FUNDAMENTOS DE SPRING, IoC Y INYECCIÓN DE DEPENDENCIAS (DI)

### El Ecosistema Base: ¿Qué es Spring Framework?
Spring Framework es un ecosistema modular de código abierto diseñado para simplificar el desarrollo de aplicaciones empresariales en Java mediante el uso de infraestructura liviana, patrones de diseño robustos y, fundamentalmente, la gestión del ciclo de vida de los componentes de software.

* **¿Qué es una Dependencia?:** En el diseño de software, una dependencia representa una característica o acoplamiento lógico de un objeto específico que requiere de otro servicio o componente externo para poder ejecutar de forma correcta su respectiva lógica operativa o de negocio.
* **Inversión de Control (IoC - Inversion of Control):** Es un principio de diseño arquitectónico en el cual se transfiere el flujo de control tradicional del programa (la instanciación y gestión manual de los objetos) a un contenedor o framework externo (el *Spring IoC Container* o *ApplicationContext*).

#### Ventajas Arquitectónicas de IoC:
1.  **Desacoplamiento Absoluto:** Separa la ejecución de una tarea de su implementación física concreta.
2.  **Intercambiabilidad de Componentes:** Facilita la transición dinámica entre diferentes implementaciones basadas en contratos o interfaces.
3.  **Modularidad Avanzada:** Aísla las responsabilidades de cada módulo del sistema de manera limpia.
4.  **Testabilidad Eficiente:** Permite aislar componentes unitarios y sustituir dependencias reales por simulacros (*Mocks* o *Stubs*).

#### Mecanismos de Implementación de IoC:
El principio de IoC puede materializarse mediante múltiples patrones de diseño:
* *Strategy Design Pattern*
* *Factory Design Pattern*
* *Service Locator Pattern*
* **Dependency Injection (DI - Inyección de Dependencias):** El patrón predominante en Spring. Consiste en delegar la configuración de las dependencias de un objeto a un agente externo (el contenedor), el cual "inyecta" físicamente las instancias requeridas en tiempo de ejecución.

### Gestión de Beans y Component Scan
Un **Spring Bean** es cualquier objeto cuya instanciación, ensamblado, ciclo de vida y destrucción son administrados de forma exclusiva por el contenedor de Spring IoC.

* **`@ComponentScan`:** Es la directiva que instruye a Spring a escanear paquetes específicos en búsqueda de clases anotadas con estereotipos de componentes (`@Component`, `@Service`, `@Repository`, `@Controller`). Si no se le proveen argumentos explícitos (`basePackages`), el framework por defecto escaneará de manera recursiva el paquete raíz donde se encuentra declarada la anotación y todos sus subpaquetes asociados.
* **`@Autowired`:** Indica al contenedor de Spring que resuelva e inyecte automáticamente una dependencia de tipo coincidente dentro de un Bean. Aunque se puede aplicar en atributos (*Field Injection*) o en métodos mutadores (*Setter Injection*), la mejor práctica de la industria es la **Inyección por Constructor**, ya que promueve la inmutabilidad de los campos (`final`) y facilita las pruebas unitarias sin requerir el levantamiento de contextos complejos del framework.

---

## 2. EL ECOSISTEMA DE SPRING BOOT Y AUTOCONFIGURACIÓN PROFUNDA

### ¿Qué es Spring Boot?
Spring Boot es un proyecto estratégico construido sobre Spring Framework que elimina la complejidad operativa de la configuración inicial. Permite crear aplicaciones independientes (*stand-alone*) de grado de producción listas para ejecutar de forma inmediata mediante el paradigma de **Convención sobre Configuración**.



#### Características Principales:
* **Aplicaciones Stand-Alone:** Autocontenidas, capaces de ejecutarse directamente mediante comandos nativos (`java -jar`).
* **Servidores Embebidos:** Integra de forma nativa contenedores de servlets como **Tomcat** (por defecto), **Jetty** o **Undertow** directamente dentro del artefacto ejecutable, eliminando la necesidad histórica de desplegar archivos `WAR` en servidores de aplicaciones externos.
* **Starter Dependencies:** Provee dependencias agrupadas (*POMs* de Maven o Gradle) que simplifican drásticamente la configuración de la construcción para diferentes pilas tecnológicas.
* **Autoconfiguración Inteligente:** Configura automáticamente las bibliotecas de Spring y de terceros basándose en las dependencias detectadas en el *Classpath* de ejecución del proyecto.
* **Funcionalidades Listas para Producción (NFRs):** Provee métricas operativas avanzadas, telemetría de salud remota (*Health Checks*) y manejo unificado de configuraciones externas mediante **Spring Boot Actuator**.
* **Cero Generación de Código y XML:** No requiere la escritura manual de configuraciones complejas en formatos XML ni genera código intrusivo en el espacio de trabajo.

### La Mecánica de la Autoconfiguración y Starters
Históricamente, construir una aplicación Spring requería definir manualmente componentes críticos como el `DispatcherServlet`, *DataSources*, convertidores JSON, escaneo de componentes, etc. Spring Boot automatiza este flujo mediante el análisis del entorno en tiempo de ejecución (*Runtime*).

#### Ejemplo Práctico: `spring-boot-starter-web`
Al incluir este Starter en la configuración de dependencias, el mecanismo de autoconfiguración detecta su presencia en el Classpath y aprovisiona automáticamente de fondo:
1.  **`DispatcherServlet`** mediante `DispatcherServletAutoConfiguration`.
2.  **Servidor Web Embebido Tomcat** mediante `EmbeddedWebServerFactoryCustomizerAutoConfiguration`.
3.  **Páginas de Error por Defecto** mediante `ErrorMvcAutoConfiguration`.
4.  **Serialización/Deserialización Bean <-> JSON** mediante la integración automatizada de Jackson en `JacksonHttpMessageConvertersConfiguration`.

*Ejemplo de entorno adaptativo:* Si la base de datos en memoria `HSQLDB` o `H2` es detectada en el classpath y no se ha definido manualmente ningún bean de conexión a bases de datos (*DataSource*), Spring Boot autoconfigurará automáticamente una conexión a una base de datos en memoria para agilizar el desarrollo de la aplicación.

### La Anotación Maestra: `@SpringBootApplication`
Esta anotación se ubica en la clase principal que arranca el contexto de la aplicación. Funciona como una macroanotación que unifica tres capacidades estructurales críticas:

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration     // 1. Define la clase como origen de configuraciones de Spring Boot.
@EnableAutoConfiguration    // 2. Activa el escaneo y aplicación de las autoconfiguraciones del classpath.
@ComponentScan              // 3. Dispara el escaneo automático de componentes en el paquete actual y derivados.
public @interface SpringBootApplication { ... }
```

---

## 3. ARQUITECTURA WEB CLÁSICA: SPRING MVC VS. EL PATRÓN FRONT CONTROLLER

### Flujo de Ejecución de una Petición HTTP
En el ecosistema clásico síncrono (Spring MVC), todas las solicitudes entrantes se procesan bajo la arquitectura de un servidor multitendencia basado en hilos (*Thread-per-request*), utilizando el patrón de diseño **Front Controller**.



#### Ciclo de Vida de una Petición:
1.  **Recepción Centralizada:** El cliente emite una petición HTTP que es interceptada por el **`DispatcherServlet`** (el Front Controller del sistema).
2.  **Mapeo de Rutas:** El `DispatcherServlet` consulta al componente `HandlerMapping` para identificar qué controlador específico y qué método concreto (`@RequestMapping`) está asociado a la URL entrante.
3.  **Procesamiento de Negocio:** Se delega la ejecución al Controlador interceptor. Los parámetros de la petición se procesan y se ejecutan las operaciones correspondientes.
4.  **Transformación JSON/REST:** Si el controlador está anotado con `@RestController` (o `@ResponseBody`), el objeto retornado (ej: un POJO o un Bean) se intercepta inmediatamente por el componente de autoconfiguración de Jackson (`JacksonHttpMessageConvertersConfiguration`), el cual serializa el objeto de Java transformándolo en una cadena legible en formato JSON para escribirlo directamente en el cuerpo de la respuesta HTTP.

### El Rol de `CommandLineRunner`
Es una interfaz funcional provista por Spring Boot que expone un método abstracto `run(String... args)`. Cualquier Spring Bean que implemente este contrato será invocado de forma automática por el framework **inmediatamente después de que el ApplicationContext se haya cargado por completo** y antes de dar por finalizado el arranque del sistema. Es ideal para tareas de inicialización de datos, precarga de cachés locales o ejecuciones de scripts rápidos en el arranque de la aplicación.

---

## 4. PERSISTENCIA Y ACCESO A DATOS CON JPA Y SPRING DATA JPA

### Conceptos Clave de Mapeo Objeto-Relacional (ORM)
* **JPA (Java Persistence API / Jakarta Persistence):** Representa la especificación estándar oficial de la plataforma Java para gestionar el mapeo objeto-relacional y la persistencia de datos entre objetos puros de Java (Entidades) y tablas de bases de datos relacionales tradicionales.
* **Hibernate:** Es una implementación concreta, robusta y comercial de la especificación técnica definida por JPA.
* **Spring Data JPA:** **No es una implementación de JPA**, sino un proyecto de abstracción de alto nivel del ecosistema Spring diseñado para simplificar drásticamente la implementación de repositorios basados en JPA. Reduce el código repetitivo (*Boilerplate code*) proveyendo interfaces genéricas integradas que manejan operaciones CRUD automáticas y la generación dinámica de consultas basadas en nombres de métodos (*Query Methods*).

### La Jerarquía Arquitectónica de Spring Data
El ecosistema de Spring Data se segmenta internamente en subproyectos especializados según el motor de persistencia subyacente:
* *Persistencia Relacional (SQL):* `Spring Data JPA` y `Spring Data JDBC`.
* *Persistencia No Relacional (NoSQL):* `Spring Data MongoDB`, `Spring Data Cassandra`, `Spring Data Redis`, entre otros.

---

## 5. CATÁLOGO AVANZADO Y EXHAUSTIVO DE ANOTACIONES DE SPRING ECOSYSTEM

A continuación se presenta el desglose exhaustivo de las anotaciones que rigen el comportamiento, alcance, persistencia y aspectos transversales dentro de las aplicaciones empresariales modernas basadas en Spring.

### 5.1 Anotaciones de Configuración y Núcleo (Core Beans)
* **`@Configuration`:** Indica que la clase contiene definiciones de métodos anotados con `@Bean` destinados a la creación de instancias de objetos de terceros o configuraciones personalizadas que serán registradas en el contenedor de Spring.
* **`@Bean`:** Se aplica a nivel de método dentro de clases `@Configuration`. Registra el objeto retornado por el método como un bean administrado por el contenedor de Spring.
* **`@Component`:** Estereotipo genérico que marca a una clase Java tradicional para que sea detectada por el mecanismo de escaneo automático de componentes y registrada en el contenedor de Spring.
* **`@Service`:** Especialización semántica de `@Component` destinada a marcar clases que albergan la lógica de negocio central del sistema.
* **`@Repository`:** Especialización semántica de `@Component` destinada a encapsular el acceso a datos. Además, habilita la traducción automática de excepciones nativas de la base de datos (como `SQLException`) a la jerarquía de excepciones de Spring (`DataAccessException`).
* **`@Qualifier`:** Rompe la ambigüedad en escenarios de inyección donde coexisten múltiples beans que implementan la misma interfaz común, indicando explícitamente el nombre del bean exacto que se desea inyectar.
* **`@Lazy`:** Configura un bean para que su inicialización sea perezosa, es decir, el objeto no se instanciará durante el arranque inicial de la aplicación, sino bajo demanda estricta, en el momento exacto en que sea requerido por primera vez.
* **`@PostConstruct`:** Se aplica sobre un método dentro de un bean para que sea ejecutado automáticamente por el contenedor inmediatamente después de que el bean haya sido instanciado y todas sus dependencias hayan sido inyectadas de forma exitosa.
* **`@PreDestroy`:** Se aplica sobre un método para que sea invocado de forma automática por el contenedor inmediatamente antes de que el bean sea destruido o el contexto de la aplicación se cierre por completo.

### 5.2 Ciclos de Vida y Ciclo de Vida de los Beans (Scopes)
La anotación **`@Scope`** define la estrategia de ciclo de vida e instanciación de un bean administrado por Spring. Los alcances disponibles son:

| Alcance (Scope) | Descripción Mecánica e Impacto en Memoria | Entorno Válido |
| :--- | :--- | :--- |
| **`singleton`** *(Por Defecto)* | Mantiene una **única instancia compartida globalmente del bean por cada contenedor de Spring IoC**. Todas las peticiones concurrentes del sistema compartirán la misma referencia en el Heap. *¡No debe almacenar estado mutable (debe ser Thread-Safe)!* | Universal |
| **`prototype`** | Genera **una nueva instancia física del bean cada vez que es solicitado** o inyectado por el sistema. El ciclo de vida de destrucción no queda completamente en manos de Spring. | Universal |
| **`request`** | Crea e inicializa una instancia del bean **única y exclusiva para el ciclo de vida de una sola petición HTTP**. Al finalizar el procesamiento de la solicitud, el bean se destruye de la memoria de forma automática. | Aplicaciones Web |
| **`session`** | Vincula el ciclo de vida del bean a la **existencia de una Sesión HTTP de un usuario específico**. La misma instancia se reutiliza a lo largo de múltiples peticiones del mismo usuario. | Aplicaciones Web |
| **`globalSession`** | Vincula el alcance del bean al ciclo de vida de una sesión HTTP compartida globalmente a nivel de un contexto de Portlets empresariales. | Entornos Portlet |

### 5.3 Anotaciones de Propiedades y Perfiles (Externalized Configuration)
* **`@Value`:** Permite inyectar valores específicos escalares directly en campos del bean desde archivos de propiedades externas (`application.properties` / `application.yml`), variables de entorno del sistema o expresiones basadas en SpEL (*Spring Expression Language*).
* **`@ConfigurationProperties`:** Permite mapear y cargar de forma estructurada un bloque jerárquico de propiedades definidas en archivos de configuración directamente sobre los atributos correspondientes de un POJO, garantizando una validación y tipado fuerte de las configuraciones externas.
* **`@PropertySource`:** Se declara a nivel de clase de configuración para especificar la ubicación exacta de archivos de propiedades personalizados de los cuales se desea cargar variables de configuración en tiempo de ejecución.
* **`@Profile`:** Condiciona la activación o disponibilidad de clases `@Configuration` o componentes `@Component` basándose en el perfil de entorno activo configurado en la ejecución de la aplicación (ej: `spring.profiles.active=dev`, `qa`, `prod`).

### 5.4 Anotaciones para APIs REST y Controladores Síncronos
* **`@RestController`:** Macroanotación que combina `@Controller` y `@ResponseBody`. Elimina la necesidad de anotar cada método con `@ResponseBody`, garantizando que todos los datos retornados por los métodos se serialicen directamente en formato JSON/XML en el cuerpo de la respuesta HTTP.
* **`@RequestMapping`:** Mapea solicitudes HTTP a rutas o endpoints específicos. Puede aplicarse a nivel de clase para definir una ruta base o a nivel de método para acciones concretas.
* **`@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`:** Especializaciones semánticas directas de `@RequestMapping` vinculadas respectivamente a los verbos estándar del protocolo HTTP para la lectura, creación, actualización y eliminación de recursos en una arquitectura arquitectónica REST.
* **`@RequestBody`:** Intercepta el cuerpo de la petición HTTP entrante y activa el mecanismo de deserialización automática del contenido (típicamente en formato JSON) hacia un objeto o modelo de datos de Java.
* **`@PathVariable`:** Extrae dinámicamente variables incrustadas directamente dentro de la URI del endpoint hacia parámetros tipados del método del controlador.
* **`@RequestParam`:** Extrae variables de consulta (*Query Parameters*) o parámetros enviados a través de formularios HTTP desde la URI hacia parámetros del método.
* **`@RequestHeader`:** Permite recuperar de forma directa los valores asociados a cabeceras HTTP específicas de la petición entrante para su uso en la lógica del endpoint.
* **`@CrossOrigin`:** Habilita de forma selectiva políticas de Intercambio de Recursos de Origen Cruzado (**CORS - Cross-Origin Resource Sharing**) en controladores o métodos, definiendo explícitamente qué dominios o patrones externos tienen autorización legal de invocar el API.
* **`@ControllerAdvice`:** Centraliza componentes globales interceptores para el manejo unificado de excepciones transversales a toda la aplicación. Alberga métodos anotados con `@ExceptionHandler`.
* **`@ExceptionHandler`:** Define un método especializado para capturar y procesar de forma automática excepciones específicas (tanto nativas como personalizadas) lanzadas por cualquier controlador del sistema, retornando una estructura de respuesta limpia y controlada al cliente.
* **`@Valid`:** Activa de forma proactiva la ejecución de reglas de validación de datos estándar sobre objetos deserializados en parámetros como `@RequestBody` o `@RequestParam`, basándose en las restricciones anotadas en los campos del modelo (ej: `@NotNull`, `@Size`).

### 5.5 Anotaciones de Persistencia y Validación en Modelos Relacionales (JPA)
* **`@Entity`:** Especifica que la clase Java representa una entidad persistente cuyo estado será mapeado directamente a una tabla correspondiente de la base de datos relacional.
* **`@Table`:** Permite personalizar detalles explícitos de la tabla en la base de datos (como el nombre de la tabla) asociándola a la entidad `@Entity`.
* **`@Id`:** Declara el atributo de la entidad que actuará de forma exclusiva como la Llave Primaria (*Primary Key*) del registro en la tabla de la base de datos.
* **`@GeneratedValue`:** Especifica la estrategia de generación automática para los valores de las llaves primarias (ej: `GenerationType.IDENTITY`, `SEQUENCE`).
* **`@Column`:** Permite personalizar las propiedades físicas de una columna de la base de datos (nombre de la columna, nulabilidad, longitud máxima) asociada al atributo de la entidad.
* **`@Query`:** Declara consultas personalizadas escritas de forma directa sobre el método de una interfaz de repositorio utilizando lenguaje de consultas orientado a objetos JPQL o consultas SQL nativas (`nativeQuery = true`).
* **`@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`:** Definen la naturaleza de las relaciones cardinales estructurales que gobiernan los enlaces relacionales entre las diferentes entidades de la base de datos.
* **`@NotNull, @Max, @Min, @Positive, @Negative`:** Restricciones de validación del estándar Bean Validation que restringen los valores válidos admitidos por los campos de un objeto durante los ciclos de persistencia o deserialización.

### 5.6 Aspectos Transversales y Enterprise (AOP, Cache, Scheduling, Transactions)
* **`@Aspect`:** Marca una clase como un Aspecto dentro de la Programación Orientada a Aspectos (**AOP**), permitiendo encapsular lógica transversal que interfecta la ejecución del código de negocio principal (ej: logs de auditoría, métricas operativas).
* **`@Pointcut`:** Define expresiones condicionales de filtrado que especifican con precisión matemática en qué puntos de ejecución del sistema se inyectará la lógica del aspecto.
* **`@Before`, `@After`, `@Around`:** Especifican el momento exacto en que se ejecutará la lógica del consejo (*Advice*) con respecto a la ejecución del método de negocio real interceptado por el aspecto.
* **`@EnableCaching`:** Activa la infraestructura global de caché administrada por Spring a nivel de la clase principal de la aplicación.
* **`@Cacheable`:** Almacena automáticamente en la memoria de caché el resultado retornado por la ejecución de un método, asociándolo a una clave única. En ejecuciones consecutivas con los mismos parámetros, se retorna el valor directo de la caché saltándose la ejecución física del método.
* **`@CachePut`:** Fuerza la ejecución del cuerpo del método y actualiza de forma proactiva el contenido almacenado en la caché con el nuevo valor calculado.
* **`@CacheEvict`:** Remueve de forma selectiva o total las entradas almacenadas en la caché para forzar una nueva lectura física desde el origen de datos.
* **`@EnableScheduling`:** Habilita la infraestructura del programador de tareas automáticas en segundo plano dentro de la aplicación.
* **`@Scheduled`:** Configura un método para que sea ejecutado de forma automatizada y periódica basándose en intervalos fijos (`fixedRate`, `fixedDelay`) o mediante expresiones de tiempo complejas basadas en expresiones **CRON**.
* **`@Transactional`:** Envuelve la ejecución del método o clase dentro del contexto de una Transacción de base de datos controlada. Garantiza el cumplimiento de las propiedades **ACID**, ejecutando automáticamente un `commit` si el método finaliza con éxito o un `rollback` completo si ocurre una excepción de tipo `RuntimeException` no controlada.

---

## 6. SEGURIDAD PERIMETRAL, OAUTH 2.0 Y JWT EN ARQUITECTURAS CORPORATIVAS

### Estructura de Módulos de Spring Security
Spring Security organiza sus capacidades de control de acceso perimetral a través de submódulos altamente especializados:
1.  **Core:** Alberga las interfaces y abstracciones fundamentales del sistema de seguridad (como `Authentication`, `GrantedAuthority`, `SecurityContextHolder`).
2.  **Web:** Contiene la infraestructura de filtros HTTP interceptores que articulan la seguridad web perimetral y gestionan la cadena de filtros de seguridad (*Security Filter Chain*).
3.  **LDAP:** Provee mecanismos de integración y autenticación empresarial contra servidores de directorio basados en el protocolo LDAP.



### Actores en el Ecosistema de Autorización OAuth 2.0
El estándar OAuth 2.0 desacopla el proceso de autenticación de la autorización mediante cinco actores con responsabilidades bien delimitadas:

```
┌──────────────────────────────────────────────────────────────────────────┐
│                      FLUJO DE ACTORES EN OAUTH 2.0                       │
├─────────────────┬───────────────────┬──────────────────┬─────────────────┤
│ Resource Owner  │ User-Agent        │ Client Application│ Authorization   │
│ El usuario final│ El navegador web  │ La aplicación que│ Server          │
│ dueño de los    │ o dispositivo móvil│ solicita acceso  │ Valida identi-  │
│ datos.          │ del usuario.      │ en nombre del    │ dad y emite     │
│                 │                   │ Resource Owner.  │ tokens de acceso│
└─────────────────┴───────────────────┴──────────────────┴─────────────────┘
                                   │
                                   ▼
                        ┌────────────────────┐
                        │  Resource Server   │
                        │ El API / Microser- │
                        │ vicio que protege y│
                        │ sirve los datos de │
                        │ forma segura.      │
                        └────────────────────┘
```



### Anatomía de un JSON Web Token (JWT)
Un token JWT es una estructura inmutable, compacta y autocontenida que se codifica en formato Base64URL y se divide de forma estricta en tres segmentos delimitados por puntos (`.`): **`[Header].[Payload].[Signature]`**.

#### 1. Header (Cabecera):
Contiene los metadatos de naturaleza criptográfica que describen la estructura y cómo procesar el token. Especifica típicamente el tipo de token (`JWT`) y el algoritmo de firma digital de hash utilizado (ej: `HS256`, `RS256`).
*Ejemplo:* `{"alg": "HS256", "typ": "JWT"}`

#### 2. Payload (Cuerpo / Reclamaciones):
Alberga los datos de usuario o información de negocio estructurada que se transmite mediante el token. Estos datos individuales se conocen como **Claims** (Reclamaciones) y se clasifican en tres subcategorías:
* *Registered Claims:* Propiedades estándar recomendadas globalmente por la especificación (ej: `iss` - emisor, `sub` - sujeto/usuario, `aud` - audiencia, `exp` - tiempo de expiración exacta).
* *Public Claims:* Reclamaciones personalizadas creadas de forma pública para ser consumidas de manera genérica por las aplicaciones del ecosistema.
* *Private / Custom Claims:* Información altamente especializada acordada exclusivamente de forma privada entre las partes emisora y consumidora del token (ej: roles, permisos internos, IDs de base de datos).

#### 3. Signature (Firma Digital):
Es el mecanismo criptográfico que garantiza de forma matemática la **integridad absoluta** y la **no alteración del token** a lo largo de su tránsito por la red. Se calcula tomando la representación codificada en Base64URL del `Header` combinada de forma contigua con el `Payload`, y aplicando el algoritmo criptográfico especificado contra una clave secreta simétrica o una clave privada asimétrica conocida exclusivamente por el *Authorization Server*.

### Seguridad a Nivel de Métodos
Spring Security permite inyectar políticas de control de acceso granulares directamente sobre métodos específicos de servicios mediante anotaciones interceptoras pre-ejecución y post-ejecución:
* **`@Secured`:** Enfoque clásico que restringe la ejecución de un método basándose estrictamente en una lista fija de roles textuales autorizados (ej: `@Secured("ROLE_ADMIN")`).
* **`@PreAuthorize`:** Alternativa moderna sumamente versátil basada en expresiones SpEL que evalúa condiciones complejas antes de permitir la entrada física al método (ej: `@PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")`).
* **`@PermitAll`:** Directiva explícita de seguridad que anula cualquier restricción de control perimetral sobre el método, declarándolo de libre acceso para cualquier usuario.

---

## 7. EL PARADIGMA REACTIVO: FILOSOFÍA Y EL MANIFIESTO DE SISTEMAS REACTIVOS

### ¿Por qué nace la Programación Reactiva?
La programación tradicional (Spring MVC) opera bajo un modelo de concurrencia síncrono bloqueante donde el servidor (Tomcat) mantiene un grupo (*Pool*) de hilos fijo (ej: 200 o 400 hilos). Cada solicitud entrante toma control exclusivo de un hilo dedicado hasta dar por concluida la transacción HTTP. 

#### El Problema Crítico del Bloqueo:
Si el microservicio requiere realizar llamadas de red externas, consumir un API remoto o ejecutar una consulta pesada en una base de datos relacional, el hilo asignado entra en un **estado de espera bloqueante (Idle Waiting State)**. Durante este intervalo, el hilo no realiza trabajo computacional útil, pero consume recursos fijos del sistema (aproximadamente **1 MB de memoria RAM nativa asignada al Stack por cada hilo creado**). En escenarios de alta concurrencia o microservicios anidados, este modelo satura rápidamente el pool de hilos, disparando cuellos de botella severos por el costo de cambios de contexto (*Context Switching*) y causando el agotamiento total de la memoria RAM del servidor.

#### La Solución Reactiva:
La programación reactiva propone un cambio drástico de paradigma: un modelo de procesamiento de flujos de datos asincrónico, no bloqueante y orientado a eventos. En lugar de bloquear hilos esperando respuestas, los procesos registran funciones de respuesta (*Callbacks*) que se ejecutan inmediatamente cuando los datos están disponibles, permitiendo que un grupo extremadamente reducido de hilos permanezca continuamente procesando tareas sin entrar jamás en estado de inactividad bloqueante.

### Los 4 Pilares del Manifiesto Reactivo
Los sistemas construidos bajo arquitecturas reactivas se adhieren estrictamente a los principios definidos en el **Reactive Manifesto**:

```
                  ┌──────────────────────┐
                  │      RESPONSIVO      │
                  │ (Foco en el usuario) │
                  └──────────┬───────────┘
                             │
               ┌─────────────┴─────────────┐
               ▼                           ▼
       ┌───────────────┐           ┌──────────────┐
       │  RESILIENTE   │           │   ELÁSTICO   │
       │(Frente a fallos)          │(Frente a carga)
       └───────────────┘           └──────────────┘
               ▲                           ▲
               └─────────────┬─────────────┘
                             │
                  ┌──────────┴───────────┐
                  │ ORIENTADO A MENSAJES │
                  │    (Bajo acoplo)     │
                  └──────────────────────┘
```

1.  **Responsivos (Responsive):** El sistema responde a tiempo de manera consistente y predecible, garantizando latencias mínimas y un comportamiento interactivo efectivo. Es la piedra angular de la usabilidad de las aplicaciones modernas.
2.  **Resilientes (Resilient):** El sistema permanece responsivo incluso frente a fallos inminentes o catastróficos de sus componentes internos. Se logra aislando los fallos en fronteras seguras, delegando la recuperación de forma transparente y evitando colapsos en cadena.
3.  **Elásticos (Elastic):** El sistema escala horizontalmente incrementando o reduciendo los recursos computacionales asignados de forma dinámica y automática para mantener la responsividad bajo variaciones extremas en el volumen de la carga de trabajo.
4.  **Orientados a Mensajes (Message-Driven):** Los componentes interactúan entre sí exclusivamente a través del intercambio asincrónico de mensajes. Esto establece fronteras físicas claras, garantiza bajo acoplamiento, aislamiento total y facilita la aplicación del principio de **Backpressure** (Contrapresión), permitiendo a los consumidores notificar a los productores cuánta carga de datos son capaces de procesar sin ser abrumados.

---

## 8. ARQUITECTURA Y MECÁNICA INTERNA DE SPRING WEBFLUX VS. SPRING MVC

### Matriz Comparativa de Pilares Arquitectónicos

| Dimensión Arquitectónica | Spring MVC (Clásico) | Spring WebFlux (Reactivo) |
| :--- | :--- | :--- |
| **Modelo de Programación** | Síncrono, Imperativo, Bloqueante | Asíncrono, Funcional Declarativo, No Bloqueante |
| **Servidor Web Subyacente** | Tomcat, Jetty (Servlet Container Clásico) | **Netty** (Por Defecto), Undertow (Event-Driven) |
| **Mecanismo de Hilos** | *Thread-per-request* (Pool amplio de hilos) | *Event Loop* (Pool mínimo, típicamente igual al número de núcleos de CPU) |
| **Especificación Base** | Servlet API tradicional | **Reactive Streams Specification** (Java 9+) |
| **Tipos de Retorno** | POJOs puros, `List<T>`, `ResponseEntity<T>` | **`Mono<T>`** y **`Flux<T>`** (Project Reactor) |
| **Impacto del Bloqueo** | Tolerable (dentro de los límites del pool de hilos) | **Catastrófico**. Bloquear un EventLoop congela el procesamiento global del sistema. |



### Los Editores de Flujo Reactivos: `Mono<T>` vs. `Flux<T>`
Project Reactor provee las dos implementaciones primarias de la interfaz `Publisher` de la especificación de Reactive Streams:



* **`reactor.core.publisher.Mono<T>`:** Representa un flujo de datos reactivo optimizado que emitirá **cero (0) o un (1) elemento** como máximo, finalizando de forma exitosa o emitiendo una señal de error. Se mapea conceptualmente al procesamiento asincrónico de recursos singulares (ej: buscar un registro por ID, procesar una única transacción).
* **`reactor.core.publisher.Flux<T>`:** Representa una secuencia asincrónica que puede emitir desde **cero (0) hasta un número infinito ($\infty$) de elementos** a lo largo del tiempo. Es el modelo ideal para la transmisión continua de streams de datos, lectura de bases de datos no bloqueantes o procesamiento de eventos continuos en tiempo real.

---

## 9. DESARROLLO PRÁCTICO EN WEBFLUX: CONTROLADORES, MANEJO EXHAUSTIVO DE EXCEPCIONES Y ENDPOINTS FUNCIONALES

### 9.1 Capa de Servicio Reactiva
La regla de oro en Spring WebFlux es que **toda la cadena lógica de ejecución debe operar de forma asincrónica y no bloqueante**. La lógica matemática o de negocio se encapsula dentro del pipeline del flujo.

```java
package com.ezamora.webfluxdemo.service;

import com.ezamora.webfluxdemo.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Duration;

@Service
public class ReactiveMathService {

    // Emisión asincrónica de un único elemento matemático (Cuadrado de un número)
    public Mono<Response> findSquare(int input) {
        return Mono.fromSupplier(() -> input * input)
                   .map(Response::new);
    }

    // Emisión reactiva de un flujo secuencial continuo (Tabla de multiplicar)
    // Simula una latencia reactiva de 1 segundo por cada elemento sin bloquear el hilo
    public Flux<Response> multiplicationTable(int input) {
        return Flux.range(1, 10)
                   .delayElements(Duration.ofSeconds(1)) // Latencia no bloqueante reactiva
                   .doOnNext(i -> System.out.println("Reactive-math-service processing step: " + i))
                   .map(index -> new Response(index * input));
    }
    
    // Operación reactiva que procesa un DTO empaquetado en un Mono
    public Mono<Response> multiply(Mono<MultiplyRequestDto> dtoMono) {
        return dtoMono.map(dto -> dto.getFirstInput() * dto.getSecondInput())
                      .map(Response::new);
    }
}
```

### 9.2 Controlador Reactivo Tradicional (`@RestController`)
A continuación se ilustra cómo exponer endpoints reactivos y capturar cabeceras HTTP de forma asincrónica:

```java
package com.ezamora.webfluxdemo.controller;

import com.ezamora.webfluxdemo.dto.MultiplyRequestDto;
import com.ezamora.webfluxdemo.dto.Response;
import com.ezamora.webfluxdemo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Map;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {

    @Autowired
    private ReactiveMathService mathService;

    @GetMapping("/square/{input}")
    public Mono<Response> findSquare(@PathVariable int input) {
        return this.mathService.findSquare(input);
    }

    // Retorna una secuencia asincrónica continua (Flux)
    // El cliente recibirá los datos de forma progresiva a medida que se emitan en el stream
    @GetMapping("/table/{input}")
    public Flux<Response> multiplicationTable(@PathVariable int input) {
        return this.mathService.multiplicationTable(input);
    }

    // Lectura asincrónica de Cabeceras HTTP y cuerpo de petición reactiva
    @PostMapping("/multiply")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequestDto> dtoRequest, 
                                   @RequestHeader Map<String, String> headers) {
        System.out.println("Intercepted Reactive HTTP Headers: " + headers);
        return this.mathService.multiply(dtoRequest);
    }
}
```

### 9.3 Gestión Profesional de Excepciones y Validación en WebFlux
En entornos reactivos, lanzar una excepción imperativa tradicional (`throw new Exception`) **rompe el pipeline funcional del flujo de datos**, degradando la resiliencia del sistema. Spring WebFlux provee tres estrategias arquitectónicas bien diferenciadas para realizar validaciones de datos y mitigar fallos dentro del flujo reactivo de forma limpia:

#### Modelos de DTO y Excepción Utilizados:
```java
// DTO de respuesta estructurada ante fallos de validación
package com.ezamora.webfluxdemo.dto;
import lombok.Data;

@Data
public class InputFailedValidationResponse {
    private int errorCode;
    private int input;
    private String message;
}

// Excepción de dominio reactivo basada en RuntimeException
package com.ezamora.webfluxdemo.exception;
import lombok.Getter;

public class InputValidationException extends RuntimeException {
    private static final String MSG = "The allowed logical range is strictly between 10 and 20";
    @Getter private final int errorCode = 100;
    @Getter private final int input;

    public InputValidationException(int input) {
        super(MSG);
        this.input = input;
    }
}
```

#### Manejador Global interceptor de Excepciones Reactivo (`@ControllerAdvice`):
```java
package com.ezamora.webfluxdemo.exceptionhandler;

import com.ezamora.webfluxdemo.dto.InputFailedValidationResponse;
import com.ezamora.webfluxdemo.exception.InputValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation. some;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InputValidationHandler {

    // Captura automática de fallos reactivos y mapeo a una respuesta REST controlada
    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationResponse> handleException(InputValidationException ex) {
        InputFailedValidationResponse response = new InputFailedValidationResponse();
        response.setInput(ex.getInput());
        response.setErrorCode(ex.getErrorCode());
        response.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
```

#### Controlador Demostrativo con las 3 Estrategias de Validación:
```java
package com.ezamora.webfluxdemo.controller;

import com.ezamora.webfluxdemo.dto.Response;
import com.ezamora.webfluxdemo.exception.InputValidationException;
import com.ezamora.webfluxdemo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-validation")
public class ReactiveMathValidationController {

    @Autowired
    private ReactiveMathService mathService;

    /**
     * ESTRATEGIA 1: Enfoque Imperativo / Híbrido (No Recomendado)
     * Lanza la excepción inmediatamente fuera del pipeline reactivo. 
     * Rompe la simetría del flujo y la cohesión declarativa del framework.
     */
    @GetMapping("/square/{input}/throws")
    public Mono<Response> findSquareThrows(@PathVariable int input) {
        if (input < 10 || input > 20) {
            throw new InputValidationException(input); // Interrupción imperativa abrupta
        }
        return this.mathService.findSquare(input);
    }

    /**
     * ESTRATEGIA 2: Manejo dentro del Flujo mediante `SynchronousSink` (Altamente Recomendado)
     * Utiliza el operador `.handle()` para mantener la validación 100% dentro del ecosistema del flujo reactivo.
     * Permite emitir de forma segura una señal de error reactiva (`sink.error()`) sin interrumpir los hilos de ejecución.
     */
    @GetMapping("/square/{input}/mono-error")
    public Mono<Response> monoError(@PathVariable int input) {
        return Mono.just(input)
                   .handle((integer, synchronousSink) -> {
                       if (integer >= 10 && integer <= 20) {
                           synchronousSink.next(integer); // Emisión segura del dato al flujo
                       } else {
                           synchronousSink.error(new InputValidationException(integer)); // Señalización de error reactiva
                       }
                   })
                   .cast(Integer.class)
                   .flatMap(validInput -> this.mathService.findSquare(validInput));
    }

    /**
     * ESTRATEGIA 3: Validación Declarativa Pura con Respuestas por Defecto (`defaultIfEmpty`)
     * Filtra los datos mediante criterios lógicos estrictos dentro del pipeline. 
     * Si el dato no cumple con las condiciones, el flujo queda vacío, lo que permite mapear un código de estado 
     * HTTP controlado de forma declarativa (ej: HTTP 400 Bad Request) usando operadores de contingencia.
     */
    @GetMapping("/square/{input}/assignment")
    public Mono<ResponseEntity<Response>> assignment(@PathVariable int input) {
        return Mono.just(input)
                   .filter(value -> value >= 10 && value <= 20) // Filtro condicional reactivo
                   .flatMap(validValue -> this.mathService.findSquare(validValue))
                   .map(ResponseEntity::ok) // Mapeo a HTTP 200 OK si el flujo contiene datos
                   .defaultIfEmpty(ResponseEntity.badRequest().build()); // Emisión de HTTP 400 si el filtro vacía el flujo
    }
}
```

---

## 10. SIMULACRO AVANZADO DE ENTREVISTA TÉCNICA (BANCO DE PREGUNTAS REACTIVAS Y SPRING BOOT)

### P1: ¿Cuál es la diferencia mecánica fundamental entre Spring Boot y Spring Framework en términos de arquitectura?
**Respuesta:** Spring Framework es la base del motor completo, encargado de proveer el contenedor de inversión de control (IoC), el motor de inyección de dependencias (DI) y los módulos esenciales de abstracción. Su configuración inicial es manual e histórica, requiriendo que el ingeniero defina minuciosamente la infraestructura por código o XML. 
Por el contrario, Spring Boot es un envoltorio (*wrapper*) de automatización arquitectónica construido sobre la base de Spring. Su objetivo es acelerar los tiempos de desarrollo proveyendo dependencias empaquetadas autogestionadas (*Starters*), servidores web embebidos (Tomcat/Netty) listos para usar y un motor de **Autoconfiguración** condicional que inicializa beans basándose en el análisis en tiempo de ejecución del contenido del classpath de la aplicación.

### P2: ¿Por qué es un error crítico inyectar lógica de bloqueo (como consultas JDBC estándar o llamadas de API síncronas) dentro de un microservicio desarrollado con Spring WebFlux?
**Respuesta:** Spring WebFlux opera internamente bajo la arquitectura no bloqueante provista por servidores dirigidos por eventos como **Netty**. A diferencia de los servidores tradicionales que asignan cientos de hilos por cada petición, Netty utiliza un grupo extremadamente reducido de hilos de alto rendimiento denominados **Event Loops** (normalmente limitados a la cantidad de núcleos físicos de la CPU del servidor) para orquestar de forma concurrente todas las solicitudes entrantes del ecosistema. 
Si se introduce una operación de bloqueo tradicional (ej: consultar una base de datos SQL clásica mediante JDBC convencional o invocar un servicio externo con una biblioteca síncrona) dentro del pipeline de ejecución, **el hilo del Event Loop quedará físicamente congelado y bloqueado** esperando la respuesta. Al estar limitado a unos pocos hilos, bloquear un Event Loop detiene por completo la capacidad del servidor para interceptar, procesar y enrutar todas las demás solicitudes entrantes del resto de los usuarios del sistema. Esto provoca la acumulación masiva de eventos en las colas internas, la degradación exponencial del rendimiento de la aplicación y la inminente caída de todo el ecosistema reactivo. Para resolver operaciones bloqueantes inevitables, se debe forzar el aislamiento de la tarea en hilos paralelos independientes utilizando utilidades específicas como el operador `.subscribeOn(Schedulers.boundedElastic())`.

### P3: Explique detalladamente el concepto de "Backpressure" (Contrapresión) dentro del contexto de la especificación de Reactive Streams en Java.
**Respuesta:** La contrapresión es el pilar de resiliencia fundamental que define a la programación reactiva y regula la comunicación asincrónica entre un emisor de información (`Publisher`) y un receptor de la misma (`Subscriber`). En arquitecturas distribuidas de alta velocidad, es común enfrentarse a escenarios donde el productor genera y emite flujos de datos a una velocidad computacional inmensamente superior a la velocidad real a la que el consumidor es capaz de procesar, serializar o persistir dicha información. En sistemas tradicionales sin contrapresión, esto causa el desbordamiento de las colas de memoria, picos severos de saturación de CPU y eventuales errores catastróficos por falta de memoria (*Out Of Memory Exceptions*).
Bajo la especificación de Reactive Streams, la comunicación deja de ser un modelo de empuje descontrolado (*Push Model*) y se transforma en un modelo de tracción controlada (*Pull Model*). El `Subscriber` interactúa dinámicamente con el emisor a través de una interfaz de suscripción (`Subscription`), indicándole explícitamente y bajo demanda el número exacto de elementos que es capaz de procesar en un momento determinado (ej: mediante la instrucción `request(n)`). El `Publisher` tiene la obligación legal de limitar su tasa de emisión ciñéndose estrictamente a la cuota solicitada por el consumidor, garantizando un flujo balanceado y protegiendo la estabilidad de los recursos del sistema en todo momento.

### P4: ¿Cuál es la diferencia operativa y de firma entre los métodos `Mono.just()` y `Mono.fromSupplier()` en Project Reactor? ¿Cómo afecta esto al rendimiento de la aplicación?
**Respuesta:** La diferencia radica en el momento exacto en que se realiza la evaluación y computación de la lógica contenida dentro de la expresión:
* **`Mono.just(T data)`:** Evalúa de forma inmediata y en tiempo de compilación/inicialización el argumento provisto (*Eager Evaluation*). Si pasamos como parámetro un método que realiza un cálculo costoso o una llamada de red (ej: `Mono.just(ejecutarLlamadaRed())`), dicho método se ejecutará de forma instantánea en el hilo principal del sistema, **incluso si nadie llega a suscribirse jamás al flujo del Mono**.
* **`Mono.fromSupplier(Supplier<? extends T> supplier)`:** Adopta un enfoque de evaluación perezosa (*Lazy Evaluation*). El objeto proveedor (`Supplier`) encapsula la lógica sin ejecutarla. El cuerpo de la función contenido dentro del proveedor **se ejecutará única y exclusivamente en el momento exacto en que un componente invoque formalmente una suscripción (`.subscribe()`) sobre el flujo reactivo**. Esto es una regla de diseño de vital importancia en programación reactiva, donde impera la máxima universal: *"Nothing happens until you subscribe"* (Nada ocurre hasta que te suscribes), protegiendo el pipeline de inicializaciones accidentales o ejecuciones bloqueantes prematuras en los hilos de arranque.

### P5: ¿Cómo gestiona e implementa Spring Security la seguridad en una arquitectura funcional reactiva basada en Spring WebFlux en comparación con Spring MVC clásico?
**Respuesta:** En la arquitectura clásica de Spring MVC, Spring Security se fundamenta estructuralmente en el uso de los hilos de Java a través de la infraestructura provista por variables locales de tipo **`ThreadLocal`**. El contexto de seguridad del usuario autenticado se almacena de forma exclusiva en el hilo dedicado a la petición mediante el componente `SecurityContextHolder`. Esto funciona perfectamente debido a que la misma solicitud HTTP es procesada de principio a fin por el mismo hilo físico.
Sin embargo, en el ecosistema asincrónico y no bloqueante de Spring WebFlux, la misma petición HTTP es fragmentada en múltiples eventos atómicos que pueden ser procesados de forma intercalada por diferentes hilos de un pool reducido de *Event Loops*. Debido a esta dispersión de hilos, el uso de variables locales `ThreadLocal` queda completamente inhabilitado. Para solucionar este reto arquitectónico, Spring Security para WebFlux se integra directamente con el mecanismo de **Contexto Nativo de Project Reactor**. El contexto reactivo viaja incrustado de forma nativa a lo largo de todo el pipeline del flujo de datos, propagándose de manera asincrónica hacia arriba a lo largo de la cadena de operadores. La seguridad perimetral web ya no se configura mediante servlets, sino implementando la interfaz **`SecurityWebFilterChain`**, la cual utiliza interceptores de intercambio reactivos de tipo `ServerWebExchange` para validar y aplicar políticas de control de acceso sin bloquear hilos.
