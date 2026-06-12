# Spring Security - Pizzeria Project

Este proyecto de pizzería sirve como un caso de estudio para comprender e implementar la seguridad en aplicaciones Spring Boot utilizando Spring Security.

## Seguridad por Defecto: ¡Activada con una Sola Dependencia!

Con solo agregar la dependencia de `spring-boot-starter-security` a tu proyecto, Spring Security se activa automáticamente y protege todos tus endpoints. No se necesita configuración adicional para empezar.

### Credenciales Generadas Automáticamente

Al iniciar tu aplicación, Spring Security crea:

-   **Usuario:** `user`
-   **Contraseña:** Una contraseña segura y aleatoria que se imprime en la consola de tu IDE o terminal. Debes buscarla en los logs de inicio.

### Autenticación Básica (Basic Auth)

Para acceder a cualquier endpoint protegido, debes usar **Autenticación Básica**. Simplemente, incluye el usuario `user` y la contraseña generada en la cabecera `Authorization` de tu solicitud HTTP.

## Resumen de Seguridad con Spring

### ¿Qué es Spring Security y cómo funciona su autenticación básica?

Spring Security es un framework poderoso y altamente personalizable que proporciona autenticación y autorización a las aplicaciones Java. Al agregar la dependencia de Spring Security, se activa una configuración de seguridad predeterminada que protege todos los endpoints.

Por defecto, esta configuración genera un usuario (`user`) y una contraseña aleatoria que se imprime en la consola al iniciar la aplicación. Esto permite un entorno de desarrollo seguro desde el principio.

### ¿Cómo se utiliza la autenticación básica?

Spring Security utiliza **Basic Authentication** de forma predeterminada. Este esquema de autenticación implica enviar las credenciales (usuario y contraseña) en cada solicitud HTTP.

1.  **Realización de una petición GET sin autorización:**
    Si intentas acceder a un recurso protegido sin las credenciales correctas, recibirás una respuesta `401 Unauthorized`.

2.  **Incorporación del encabezado `Authorization`:**
    Para autenticarte, debes incluir un encabezado `Authorization` en tu solicitud. El valor de este encabezado debe ser la palabra `Basic` seguida de un espacio y una cadena de texto codificada en Base64. Esta cadena se compone del nombre de usuario y la contraseña, separados por dos puntos (`:`).

    **Ejemplo:** `user:contraseña` -> `dXNlcjpjb250cmFzZcOxYQ==`

    Con el encabezado correcto, la API responderá con un `200 OK` y el recurso solicitado.

### Gestión de Credenciales y Debugging

-   **Usuario por defecto:** `user`
-   **Contraseña por defecto:** Se genera una nueva cada vez que se inicia la aplicación y se muestra en la consola.

Para facilitar la depuración y entender cómo Spring Security maneja las solicitudes, puedes habilitar el logging a nivel de `DEBUG` para el paquete de seguridad web de Spring. Agrega la siguiente línea en tu archivo `application.properties`:

```properties
logging.level.org.springframework.security.web=DEBUG
```

### ¿Cómo protege tu aplicación el Spring Security Filter Chain?

Cada solicitud a tu aplicación es interceptada por una cadena de filtros de seguridad (`Spring Security Filter Chain`). Estos filtros son responsables de:

-   **Autenticar** cada solicitud.
-   **Autorizar** el acceso a los recursos solicitados.
-   **Proteger** la aplicación contra ataques comunes como CSRF, XSS, etc.

Comprender el funcionamiento de esta cadena de filtros es fundamental para personalizar y fortalecer la seguridad de tus aplicaciones con Spring.

## Configuración Personalizada: Desactivando la Seguridad

A veces, durante el desarrollo o para APIs completamente públicas, es necesario desactivar la seguridad por completo. Esto se logra creando una clase de configuración que sobreescribe el comportamiento por defecto de Spring Security.

```java
package com.platzi.pizza.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
           .authorizeHttpRequests()
           .anyRequest()
           .permitAll();
              
        return http.build();
    }
}
```

### Explicación del Código

-   **`@Configuration`**: Anota la clase como una fuente de definiciones de beans para el contenedor de Spring.
-   **`@Bean`**: Indica que el método `filterChain` produce un bean que será gestionado por Spring. Este bean reemplaza la configuración de seguridad por defecto.
-   **`SecurityFilterChain filterChain(HttpSecurity http)`**: Este es el método principal donde se define la cadena de filtros de seguridad.
-   **`http.authorizeHttpRequests()`**: Inicia la configuración de las reglas de autorización para las solicitudes HTTP.
-   **`.anyRequest()`**: Selecciona todas las solicitudes entrantes.
-   **`.permitAll()`**: Permite el acceso a todas las solicitudes (`anyRequest`) sin necesidad de autenticación. En efecto, deshabilita la seguridad.
-   **`return http.build()`**: Construye y registra el `SecurityFilterChain` personalizado.
