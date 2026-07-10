# 📋 Hoja de Ruta — POO-EcoRecycleTech

## ✅ Parte 1: Git (20%) — Completada

- [x] Repositorio `POO-EcoRecycleTech` creado en GitHub
- [x] `.gitignore` con plantilla Java + VS Code/IntelliJ
- [x] Rama `develop` creada
- [x] Estructura `src/main/java/{modelo,vista,controlador}`
- [x] `README.md` con tus datos
- [x] Commit inicial + push
- [x] Cheatsheet de Git (PDF + MD)

---

## 🔨 Parte 2: Desarrollo del Sistema (80%)

### FASE A — Modelo de Dominio (`modelo/`)

| # | Archivo                                  | Estado |
|---|------------------------------------------|--------|
| 1 | `IResiduo.java`                          | LISTO  |
| 2 | `Residuo.java` (abstracta)               | LISTO  |
| 3 | `ResiduoPlastico.java`                   | LISTO  |
| 4 | `ResiduoVidrio.java`                     | LISTO  |
| 5 | `ResiduoPapel.java`                      | LISTO  |
| 6 | `Contenedor.java` (base)                 | LISTO  |
| 7 | `ContenedorPlastico.java`                | LISTO  |
| 8 | `ContenedorVidrio.java`                  | LISTO  |
| 9 | `ContenedorPapel.java`                   | LISTO  |
| 10 | `ResiduoMetal.java` (extensión SOLID)    | LISTO  |
| 11 | `ContenedorMetal.java` (extensión SOLID) | LISTO  |

**Commit sugerido:** `feat: modelo de dominio residuos y contenedores`

---

### FASE B — Patrón Factory

| # | Archivo | Descripción |
|---|---|---|
| 12 | `ResiduoFactory.java` | Genera residuos sin exponer clases concretas al resto del sistema | LISTO

**Commit sugerido:** `feat: patron factory para creacion de residuos`

---

### FASE C — Lógica de la Planta (`modelo/`)

| # | Archivo | Descripción |
|---|---|---|
| 13 | `Planta.java` | Orquesta la cinta (`List<Residuo>`), contenedores, y reglas de negocio (procesar, vaciar, % llenado) | LISTO

**Commit sugerido:** `feat: logica central de la planta`

---

### FASE D — Persistencia (`modelo/`)

| # | Archivo | Descripción |
|---|---|---|
| 14 | `LogManager.java` | Escribe cada depósito exitoso en `recycle.log` |
| 15 | `PersistenciaEstado.java` | Guarda/lee `estado_planta.json` al cerrar/abrir la app |

**Commit sugerido:** `feat: persistencia de logs y estado de la planta`

---

### FASE E — Vista (`vista/`, Swing)

| # | Archivo | Descripción |
|---|---|---|
| 16 | `VentanaPrincipal.java` | Ventana principal: estado de cinta, barras de llenado, alertas |
| 17 | Componentes interactivos | Botones "Simular Entrada de Residuo", "Procesar residuo", "Vaciar contenedor" |

**Commit sugerido:** `feat: interfaz grafica con swing`

---

### FASE F — Controlador (`controlador/`)

| # | Archivo | Descripción |
|---|---|---|
| 18 | `ControladorPlanta.java` | Conecta clics de la Vista con el Modelo, ordena refresco de pantalla |

**Commit sugerido:** `feat: controlador MVC`

---

### FASE G — Integración final

| # | Archivo | Descripción |
|---|---|---|
| 19 | `Main.java` | Punto de entrada: carga persistencia, arranca la Vista |
| 20 | Hook de cierre | `WindowListener` que guarda estado al cerrar la ventana |

**Commit sugerido:** `feat: integracion final y punto de entrada`

---

### FASE H — Documentación y Entregables

| # | Tarea | Descripción |
|---|---|---|
| 21 | Javadoc | Comentarios `/** */` en todas las clases → generar carpeta `docs/` |
| 22 | Memoria Técnica | PDF/MD máx. 3 páginas: decisiones de diseño + patrones + capturas GUI |
| 23 | Archivos de salida reales | Adjuntar `recycle.log` y `estado_planta.json` generados de verdad al usar tu app |
| 24 | ZIP de respaldo | Comprimir el código fuente (fuera del repo, para la entrega) |

**Commit sugerido:** `docs: javadoc y memoria tecnica`

---

## 🗺️ Vista resumida del árbol final de archivos

```
POO-EcoRecycleTech/
├── .gitignore
├── README.md
├── docs/                          ← Javadoc generado
├── src/main/java/
│   ├── modelo/
│   │   ├── IResiduo.java
│   │   ├── Residuo.java
│   │   ├── ResiduoPlastico.java
│   │   ├── ResiduoVidrio.java
│   │   ├── ResiduoPapel.java
│   │   ├── ResiduoOrganico.java
│   │   ├── Contenedor.java
│   │   ├── ContenedorPlastico.java
│   │   ├── ContenedorVidrio.java
│   │   ├── ContenedorPapel.java
│   │   ├── ContenedorOrganico.java
│   │   ├── ResiduoFactory.java
│   │   ├── Planta.java
│   │   ├── LogManager.java
│   │   └── PersistenciaEstado.java
│   ├── vista/
│   │   └── VentanaPrincipal.java
│   ├── controlador/
│   │   └── ControladorPlanta.java
│   └── Main.java
├── recycle.log                    ← generado en ejecución
├── estado_planta.json             ← generado en ejecución
└── Memoria_Tecnica.pdf
```

---

## 📍 Estás aquí ahora mismo

Ya tienes el código de `IResiduo.java` y `Residuo.java` listo, solo falta pegarlo en IntelliJ una vez tengas el JDK configurado. El siguiente paso natural es **Fase A, Paso 3**: crear las tres clases concretas (`ResiduoPlastico`, `ResiduoVidrio`, `ResiduoPapel`).