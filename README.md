# Spring Learning Lab

![Spring](https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg)

A personal, hands-on library of Spring projects — organized as a **study hub for technical interviews** and a map to every deep-dive project in this repo. Read top to bottom for a fast refresher, or jump straight to a topic and follow the links into working code.

> **How this README works:** each concept below is explained in 2-4 lines (interview-ready) and links to the folder(s) where you actually built it. Section 2 is the "I have an interview in an hour" speed-read; the rest is depth + code.

---

## Table of Contents

1. [The Spring Ecosystem](#1-the-spring-ecosystem)
2. [Interview Quick-Reference (Q&A)](#2-interview-quick-reference-qa)
3. [IoC, Dependency Injection & the ApplicationContext](#3-ioc-dependency-injection--the-applicationcontext)
4. [Core Annotations Cheat Sheet](#4-core-annotations-cheat-sheet)
5. [Spring Boot: Auto-configuration & Starters](#5-spring-boot-auto-configuration--starters)
6. [Web Layer: MVC & REST](#6-web-layer-mvc--rest)
7. [Data Layer: Spring Data & JPA](#7-data-layer-spring-data--jpa)
8. [Security: Auth, JWT & OAuth2](#8-security-auth-jwt--oauth2)
9. [Reactive Stack: WebFlux, Reactor & R2DBC](#9-reactive-stack-webflux-reactor--r2dbc)
10. [Spring Cloud & Distributed Systems](#10-spring-cloud--distributed-systems)
11. [AI Integrations](#11-ai-integrations)
12. [Repo Map](#12-repo-map)
13. [Suggested Study Path](#13-suggested-study-path)
14. [Gaps & Next Steps to Study](#14-gaps--next-steps-to-study)

---

## 1. The Spring Ecosystem

**Spring Framework** is a dependency-injection / IoC container plus a family of modules (Web MVC, Data Access, Security, Messaging, AOP...) that solve cross-cutting concerns so application code stays focused on business logic.

**Spring Boot** sits on top of the Framework. It removes manual configuration via **auto-configuration**, **starters** (curated dependency bundles), an embedded server (Tomcat/Netty), and opinionated defaults — "convention over configuration."

Key artifacts you'll be asked about in interviews:

| Module | Purpose | Where in this repo |
|---|---|---|
| **Spring Core / Beans** | IoC container, DI | [`spring-basic/`](spring-basic/) |
| **Spring Boot** | Auto-config, starters, embedded server | [`spring-boot-mvc/`](spring-boot-mvc/) |
| **Spring MVC** | Servlet-based web layer (`@Controller`, REST) | [`spring-boot-mvc/`](spring-boot-mvc/), [`spring-documentation/`](spring-documentation/) |
| **Spring Data** | Repository abstraction over JPA/Mongo/R2DBC | [`spring-webflux/springDataR2Dbc/`](spring-webflux/springDataR2Dbc/), [`spring-learn-poc/`](spring-learn-poc/) |
| **Spring Security** | AuthN/AuthZ, JWT, OAuth2 | [`spring-security/`](spring-security/) |
| **Spring WebFlux** | Reactive, non-blocking web stack | [`spring-webflux/`](spring-webflux/) |
| **Spring Cloud** | Microservice patterns (config, gateway, functions) | [`spring-cloud/`](spring-cloud/) |
| **Spring + AI** | LLM/MCP integrations | [`spring-ia/`](spring-ia/) |

---

## 2. Interview Quick-Reference (Q&A)

The stuff that actually gets asked, phrased the way it gets asked. Skim this section right before an interview.

**Core / IoC**
- **`@Component` vs `@Bean`?** `@Component` is a class-level stereotype picked up by component scanning — use it for classes you own. `@Bean` is a method inside a `@Configuration` class — use it for 3rd-party classes you can't annotate, or when construction needs logic.
- **`@Controller`/`@Service`/`@Repository` vs plain `@Component`?** Functionally all register a bean; they differ in *semantics* and side effects — `@Repository` adds automatic translation of persistence exceptions into Spring's `DataAccessException` hierarchy.
- **Constructor vs field injection?** Constructor injection is preferred: it makes dependencies explicit and immutable (`final` fields), fails fast at startup if something's missing, and avoids `@Autowired`-on-field's testability and circular-dependency problems.
- **Singleton scope — is it thread-safe?** Not automatically. Singleton means *one shared instance*, not thread safety. Stateless beans (no mutable instance fields) are safe by construction; stateful ones need synchronization or a different scope.
- **Bean lifecycle order?** Constructor → dependency injection → `@PostConstruct` → bean ready for use → (on shutdown) `@PreDestroy`.
- **Circular dependency between two beans — what happens?** Constructor injection fails fast with `BeanCurrentlyInCreationException`. Field/setter injection can resolve it via a second pass, but a circular dependency is usually a sign the design needs a refactor (extract a third bean, use an interface, or `@Lazy`).

**Transactions**
- **`@Transactional` default rollback behavior?** Rolls back on unchecked exceptions (`RuntimeException` and subclasses) and `Error`; it does **not** roll back on checked exceptions unless you add `rollbackFor = Exception.class`. This is a classic interview trap.
- **Why doesn't `@Transactional` work when called from another method in the same class?** Spring's declarative transactions are implemented via a proxy (JDK dynamic proxy or CGLIB). A **self-invocation** (`this.otherMethod()`) bypasses the proxy entirely, so the annotation is silently ignored — this trips up almost everyone at least once.
- **Propagation — `REQUIRED` vs `REQUIRES_NEW`?** `REQUIRED` (default) joins the existing transaction if one is active, or starts one if not. `REQUIRES_NEW` always suspends any existing transaction and starts a fresh one — useful when a sub-operation (e.g., an audit log) must commit even if the outer transaction rolls back.
- **Isolation levels, from loosest to strictest?** `READ_UNCOMMITTED` → `READ_COMMITTED` (most DBs' default) → `REPEATABLE_READ` → `SERIALIZABLE`. Higher isolation = fewer anomalies (dirty read → non-repeatable read → phantom read) but more locking/contention.

**Web / REST**
- **`@RequestParam` vs `@PathVariable`?** `@PathVariable` binds a URI template segment (`/users/{id}`); `@RequestParam` binds a query string or form param (`/users?id=5`).
- **How does Spring resolve a validation error into a 400?** `@Valid`/`@Validated` on a `@RequestBody` triggers Bean Validation; a `MethodArgumentNotValidException` is thrown and, if you have a `@ControllerAdvice` with `@ExceptionHandler(MethodArgumentNotValidException.class)`, mapped to a structured 400 response — otherwise Spring Boot's default error handler returns a generic one.
- **CORS preflight — what triggers it?** The browser sends an `OPTIONS` request before the real one when the request is "non-simple" (custom headers, methods other than GET/POST/HEAD, or a non-simple `Content-Type` like `application/json`). The server must respond with the right `Access-Control-Allow-*` headers or the browser blocks the real request client-side.
- **Idempotency of HTTP verbs?** GET/PUT/DELETE are idempotent (same effect no matter how many times you call them); POST and PATCH are not, by convention.

**Data**
- **What's the N+1 query problem?** Fetching a list of N entities, then lazily fetching a related collection/entity for *each one individually* — 1 query becomes N+1. Fixed with `JOIN FETCH`, `@EntityGraph`, or batch fetching.
- **`FetchType.LAZY` vs `EAGER`?** `LAZY` defers loading a relationship until it's accessed (default for `@OneToMany`/`@ManyToMany`); `EAGER` loads it immediately with the parent (default for `@ManyToOne`/`@OneToOne`). Prefer `LAZY` and fetch explicitly to avoid the N+1 trap and `LazyInitializationException` outside a session.
- **`save()` on a JPA repository — insert or update?** Depends on the entity's `@Id`: `null`/unset → `INSERT`; already set (and Hibernate believes it's "detached") → `UPDATE` (technically a merge).

**Security**
- **Authentication vs Authorization?** Authentication = *who are you* (login, credentials). Authorization = *what are you allowed to do* (roles/permissions on an already-authenticated principal).
- **Why is JWT called "stateless"?** The server doesn't store session state — all claims (identity, roles, expiry) live signed inside the token itself, verified on each request. Trade-off: you can't easily force-invalidate a single token before it expires without extra infrastructure (a blocklist, short expiry + refresh tokens).
- **OAuth2 vs OpenID Connect (OIDC)?** OAuth2 is an *authorization* protocol (grants a client scoped access to a resource on the user's behalf). OIDC is a thin identity layer on top of OAuth2 that adds *authentication* (the `id_token`, who the user is).
- **Where does `PasswordEncoder` fit?** Passwords are hashed (BCrypt by default in Spring Security) before storage; `AuthenticationProvider` re-hashes the login attempt and compares hashes — plaintext is never stored or compared.

**Reactive**
- **`Mono` vs `Flux`?** `Mono<T>` emits 0 or 1 element; `Flux<T>` emits 0..N. Both are lazy — nothing runs until something subscribes.
- **Does WebFlux make requests faster?** No — it improves *throughput under I/O-bound concurrency* by not blocking a thread per in-flight request (small thread pool + event loop vs. thread-per-request in MVC). Latency for a single request isn't inherently better, and CPU-bound work gains nothing from it.
- **Why must you never call `.block()` inside a reactive chain?** It defeats the entire non-blocking model by parking an event-loop thread, which can starve the whole application under load. `.block()` is only acceptable at the very edge (e.g., a `main` method or a test).

---

## 3. IoC, Dependency Injection & the ApplicationContext

- **Inversion of Control (IoC):** the framework — not your code — creates and wires objects. You describe *what* you need; Spring decides *how* to construct and hand it to you.
- **Dependency Injection (DI):** the mechanism IoC uses. Constructor injection is preferred (immutability, testability, no circular-dependency surprises) over field injection (`@Autowired` on a field).
- **`ApplicationContext`:** the IoC container itself. It reads bean definitions (annotations, Java config, or XML), builds the dependency graph, and manages the **bean lifecycle**: instantiate → populate properties → `@PostConstruct` → ready → `@PreDestroy`.
- **Bean scopes:** `singleton` (default, one instance per context), `prototype` (new instance per request), plus web-aware scopes (`request`, `session`).

📂 See it built from scratch: [`spring-basic/spring-core-5/`](spring-basic/spring-core-5/), [`spring-basic/curso-introduccion-platzi/`](spring-basic/curso-introduccion-platzi/)

---

## 4. Core Annotations Cheat Sheet

| Annotation | What it does |
|---|---|
| `@Component` / `@Service` / `@Repository` | Registers a class as a bean; the three are semantically-distinct aliases (`@Repository` also translates persistence exceptions) |
| `@Controller` / `@RestController` | Web layer bean; `@RestController` = `@Controller` + `@ResponseBody` on every method |
| `@Autowired` | Requests DI (prefer via constructor) |
| `@Configuration` + `@Bean` | Java-based bean definition, for beans you don't own (3rd-party classes) |
| `@SpringBootApplication` | Shorthand for `@Configuration` + `@EnableAutoConfiguration` + `@ComponentScan` |
| `@Value` / `@ConfigurationProperties` | Binds external config (`application.yml`) into beans |
| `@Profile` | Activates a bean only for a given environment (`dev`, `prod`, `test`) |
| `@Conditional...` | Registers a bean only if a condition holds (this is how auto-configuration works internally) |
| `@Transactional` | Wraps a method in a DB transaction (commit/rollback) — see [§2](#2-interview-quick-reference-qa) for the gotchas |

---

## 5. Spring Boot: Auto-configuration & Starters

- **Starters** (`spring-boot-starter-web`, `-data-jpa`, `-security`...) are dependency bundles that pull in everything a feature needs at compatible versions.
- **Auto-configuration** inspects the classpath and active beans at startup and registers sensible defaults (e.g., a `DataSource` if a JDBC driver is on the classpath) — implemented through `@Conditional*` annotations on `@Configuration` classes.
- **`application.yml`/`.properties`** externalizes configuration per environment; `@ConfigurationProperties` gives it type-safety.
- Actuator, DevTools, and the embedded server (Tomcat by default, Netty for WebFlux) round out "batteries included."

📂 [`spring-boot-mvc/`](spring-boot-mvc/) · [`spring-documentation/`](spring-documentation/) (numbered, step-by-step course: DTOs, error handling, CORS, Swagger — see [§13](#13-suggested-study-path) for how to read the numbering)

---

## 6. Web Layer: MVC & REST

- `@RequestMapping` / `@GetMapping` / `@PostMapping`... map HTTP verbs+paths to controller methods.
- `@RequestBody` / `@ResponseBody` (de)serialize JSON via Jackson.
- **Global error handling:** `@ControllerAdvice` + `@ExceptionHandler` centralizes error responses instead of try/catch in every controller; `ResponseStatusException` for quick, ad-hoc errors.
- **CORS:** configured per-method (`@CrossOrigin`) or globally (`WebMvcConfigurer#addCorsMappings`).
- **DTOs**: never expose JPA entities directly on the wire — map to/from request/response DTOs.
- **API docs:** springdoc-openapi (Swagger UI) auto-generates docs from controller annotations.

📂 [`spring-documentation/`](spring-documentation/) has a dedicated, numbered lesson for each of these (DTOs → error handling → `@ExceptionHandler` → `@ControllerAdvice` → CORS → file upload → Swagger).

---

## 7. Data Layer: Spring Data & JPA

- `JpaRepository<T, ID>` gives CRUD + paging/sorting for free; derived query methods (`findByEmail(...)`) are generated from the method name.
- `@Entity` / `@Id` / `@OneToMany` etc. map Java objects to relational tables via JPA/Hibernate.
- `@Transactional` defines the commit/rollback boundary; understand propagation (`REQUIRED`, `REQUIRES_NEW`) and isolation levels — see [§2](#2-interview-quick-reference-qa).
- Same repository abstraction extends to MongoDB (`MongoRepository`) and reactive stores (`R2dbcRepository`) — see [§9](#9-reactive-stack-webflux-reactor--r2dbc).

📂 [`spring-learn-poc/back-end-cuenta-bancaria/`](spring-learn-poc/back-end-cuenta-bancaria/), [`spring-learn-poc/patient-data/`](spring-learn-poc/patient-data/), [`spring-security/pizzeria-data-jpa-main/`](spring-security/pizzeria-data-jpa-main/)

---

## 8. Security: Auth, JWT & OAuth2

- **`SecurityFilterChain`** replaces the old `WebSecurityConfigurerAdapter` — a bean-based, composable way to define which endpoints require auth.
- **Basic Auth** → simplest scheme, credentials on every request; good baseline before JWT.
- **JWT flow:** login endpoint issues a signed token → `OncePerRequestFilter` validates it on each request → `UserDetailsService` + `AuthenticationProvider` load the principal → `SecurityContextHolder` carries the authenticated user through the request.
- **OAuth2:** delegated authorization — Authorization Server issues tokens, Resource Server validates them; see [§2](#2-interview-quick-reference-qa) for OAuth2 vs OIDC.
- Password hashing via `PasswordEncoder` (BCrypt), never plain text.

📂 [`spring-security/`](spring-security/) is a full numbered course, from a bare project (`00_ProyectoBase`) through Basic Auth → JWT (`16`-`22`) → OAuth2 (`28`-`33`). Each topic has a `...Base` (starting point) and `...Completo` (solution) pair.

---

## 9. Reactive Stack: WebFlux, Reactor & R2DBC

- **Reactive programming** trades thread-per-request for an event-loop model: non-blocking I/O, backpressure, and composable async pipelines.
- **`Mono<T>`** (0 or 1 element) and **`Flux<T>`** (0..N elements) are Project Reactor's core types — the reactive equivalent of `Optional`/`Stream`, but lazy and push-based.
- **WebFlux** is the reactive counterpart to Spring MVC, running on Netty instead of a servlet container; controllers return `Mono`/`Flux` instead of blocking values.
- **R2DBC** brings the same non-blocking model to relational databases (vs. blocking JDBC); reactive Mongo driver does the same for MongoDB.
- Interview trap: reactive isn't automatically "faster" — see [§2](#2-interview-quick-reference-qa).

📂 [`spring-webflux/reactor/`](spring-webflux/reactor/) (Mono/Flux fundamentals) → [`spring-webflux/springWebFlux/`](spring-webflux/springWebFlux/) → [`spring-webflux/postgres-reactive/`](spring-webflux/postgres-reactive/), [`spring-webflux/springDataR2Dbc/`](spring-webflux/springDataR2Dbc/), [`spring-webflux/mongo-reactive/`](spring-webflux/mongo-reactive/), [`spring-webflux/reactivemongodb/`](spring-webflux/reactivemongodb/) → [`spring-webflux/rxJava/`](spring-webflux/rxJava/) (alternative reactive lib, good for comparison) → [`spring-webflux/kotlin-reactive/`](spring-webflux/kotlin-reactive/), [`spring-webflux/pluralsight/`](spring-webflux/pluralsight/), [`spring-webflux/udemy/`](spring-webflux/udemy/) (extra course material)

---

## 10. Spring Cloud & Distributed Systems

- **Spring Cloud** provides microservice building blocks on top of Boot: externalized config (Config Server), service discovery, API gateway, circuit breakers, and **Spring Cloud Function** (portable, cloud-agnostic functions deployable as HTTP endpoints, message handlers, or serverless).
- Interview framing: know *why* a monolith splits into services (independent deploys/scaling, team boundaries) and the cost it adds (network calls replace method calls, need for resilience patterns — retries, circuit breakers, timeouts).

📂 [`spring-cloud/spring-cloud-function/`](spring-cloud/spring-cloud-function/)

---

## 11. AI Integrations

- Spring's newer surface for wiring LLMs into applications: reactive controllers calling model APIs, and **MCP (Model Context Protocol)** servers exposing app capabilities to AI agents.

📂 [`spring-ia/demo/`](spring-ia/demo/) (WebFlux + image/song generation controllers), [`spring-ia/mcp-sprind-ia/`](spring-ia/mcp-sprind-ia/) (MCP server)

---

## 12. Repo Map

| Folder | What it is |
|---|---|
| [`spring-basic/`](spring-basic/) | Core Spring fundamentals — beans, component scanning, `@Autowired` — no Boot yet |
| [`spring-boot-mvc/`](spring-boot-mvc/) | Boot + MVC basics: controllers, repositories, CRUD, Thymeleaf |
| [`spring-documentation/`](spring-documentation/) | Guided, numbered course on REST API details: DTOs, error handling, CORS, file upload, Swagger |
| [`spring-security/`](spring-security/) | Guided, numbered course on Security: Basic Auth → JWT → OAuth2, plus two standalone apps (`PocEcommerce`, `pizzeria-data-jpa-main`) |
| [`spring-webflux/`](spring-webflux/) | Reactive stack: Reactor, WebFlux, R2DBC, reactive Mongo, RxJava, Kotlin — mixed sources (own labs + Pluralsight/Udemy courses) |
| [`spring-cloud/`](spring-cloud/) | Spring Cloud Function |
| [`spring-learn-poc/`](spring-learn-poc/) | Standalone proof-of-concepts: OAuth2 auth server, banking backend, billing/security, Kafka demo, patient data, conference demo |
| [`spring-ia/`](spring-ia/) | Spring + AI/LLM integrations, MCP server |

---

## 13. Suggested Study Path

**Fast interview refresher (read only):** section 2 first, then 1 and 3-8 top to bottom.

**Hands-on, in order:**
1. `spring-basic/` — IoC/DI without Boot, so the "magic" in step 2 makes sense.
2. `spring-boot-mvc/` — see auto-configuration replace the manual wiring from step 1.
3. `spring-documentation/` — work through the numbered lessons in order (each `NN_TopicBase` → try it yourself → compare against `NN_TopicCompleto`).
4. `spring-security/` — same Base/Completo pattern, numbered end-to-end: Basic Auth → JWT → OAuth2.
5. `spring-webflux/` — once blocking MVC + Security feel solid, contrast with the reactive model.
6. `spring-cloud/`, `spring-learn-poc/`, `spring-ia/` — pick based on what a target job/interview emphasizes (microservices vs. general backend vs. AI).

---

## 14. Gaps & Next Steps to Study

A scan of this repo (dependencies + folder contents) against the full Spring surface — these come up in interviews and aren't represented here yet:

| Topic | Why it matters | Status here |
|---|---|---|
| **Testing** (`@SpringBootTest`, `@WebMvcTest`/`@DataJpaTest`, MockMvc) | Almost every interview asks how you test a controller/service/repository in isolation | Test files exist across projects, but no dedicated "how to test a Spring app" lesson |
| **Testcontainers** | Standard now for integration tests against a real DB/Kafka in Docker instead of mocks or H2 | Not present anywhere in the repo |
| **AOP** (`@Aspect`, `@Around`, pointcuts) | Explains *how* `@Transactional`/`@Cacheable`/Security actually work under the hood (proxies) — ties directly into the self-invocation gotcha in §2 | Not present |
| **Caching** (`@Cacheable`, `@CacheEvict`, Redis) | Common "how would you speed this up" follow-up question | Not present |
| **Observability** (Actuator, Micrometer, health checks, metrics/tracing) | Production-readiness questions ("how do you know your service is healthy") | Only touched incidentally in 2 unrelated projects |
| **Messaging depth** (Kafka producer/consumer patterns, RabbitMQ, `@KafkaListener`, error handling/retries) | You have `demoKafka` as a starting point — worth expanding into consumer groups, offsets, dead-letter topics | Basic demo only |
| **Spring Cloud Gateway / Config Server / Service Discovery (Eureka)** | The rest of the microservices story beyond Cloud Function | Not present |
| **Resilience patterns** (Resilience4j: circuit breaker, retry, rate limiter) | Standard follow-up to any microservices discussion | Not present |
| **Spring Batch** | Comes up for ETL/data-processing–heavy roles | Not present |
| **Validation deep-dive** (custom `@Constraint`, group validation) | Beyond basic `@Valid`, interviewers sometimes probe custom validators | Only basic usage inside `spring-documentation/` |
| **Testing reactive code** (`StepVerifier`) | The reactive-stack equivalent of MockMvc; comes up if you claim WebFlux experience | Not present despite having `spring-webflux/` |
| **Virtual Threads (Java 21+) with Spring Boot 3.2+** | Increasingly asked as a modern alternative/complement to reactive for I/O-bound concurrency | Not present |
| **Containerization/deployment** (Dockerfile, layered jars, GraalVM native image) | "How do you ship this" is a common closing question | Only `spring-learn-poc/mcp-sprind-ia`-adjacent project had a Dockerfile before recent deletions — worth a canonical example |

**Suggested next module to add**, in priority order for interview ROI: **(1) Testing** (it's the single most commonly-asked practical topic and touches every existing project), **(2) AOP** (cheap to add, and directly explains a mechanism you already use via `@Transactional`), **(3) Resilience4j + Gateway** (rounds out the Cloud story), **(4) Caching**, **(5) Observability/Actuator**.

> Add a new folder under the matching top-level directory as each of these gets tackled (e.g. `spring-testing/`, `spring-boot-mvc/aop-demo/`) — the structure is designed to grow this way without needing a reorganization.
