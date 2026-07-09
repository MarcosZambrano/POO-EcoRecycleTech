# Git Cheatsheet — Referencia rápida
### Proyecto POO-EcoRecycleTech

---

## 1. Configuración inicial

**`git config --global user.name "Nombre"`**
Le dice a Git quién soy yo. Este nombre aparecerá como autor en cada commit que haga.

**`git config --global user.email "correo@mail.com"`**
Igual que el anterior, pero con el correo. Se usa una sola vez por computador, no por proyecto.

---

## 2. Inicio / Clonado

**`git init`**
Convierte una carpeta normal en un repositorio Git. Se usa cuando empiezo un proyecto desde cero, sin GitHub aún.

**`git clone url`**
Descarga una copia completa de un repositorio que ya existe en GitHub, incluyendo todo su historial.

---

## 3. Ciclo de vida del cambio

**`git status`**
Muestra qué archivos cambié, cuáles están listos para el commit y cuáles todavía no.

**`git add .`**
Prepara los cambios (staging). Con el punto agrego todo lo modificado; también puedo poner el nombre de un archivo específico.

**`git commit -m "mensaje"`**
Guarda una foto fija de los cambios preparados, con un mensaje que explica qué hice. Queda en mi historial local.

**`git log`**
Muestra el historial de commits: quién, cuándo y qué mensaje dejó. Útil para revisar el avance del proyecto.

---

## 4. Sincronización con GitHub

**`git push -u origin rama`**
Sube mis commits locales al repositorio remoto. El `-u` solo se usa la primera vez, para dejar la rama vinculada.

**`git push`**
Igual al anterior, pero corto: se usa después de la primera vez que ya quedó vinculada la rama.

**`git pull`**
Trae los cambios que hay en GitHub y no tengo en mi máquina, y los mezcla con lo mío automáticamente.

---

## 5. Gestión de ramas

**`git branch`**
Lista todas las ramas que existen en mi repositorio local y marca en cuál estoy parado.

**`git checkout -b develop`**
Crea una rama nueva llamada `develop` y me cambio a ella en el mismo paso.

**`git switch nombre`**
Versión moderna de `checkout`, pero solo para cambiar de rama (no crea archivos, solo me muevo entre ramas).

**`git merge develop`**
Junta los cambios de la rama `develop` dentro de la rama en la que estoy parado (ej. `main`), uniendo el trabajo.

---

## Flujo típico que voy a usar

1. `git status`
2. `git add .`
3. `git commit -m "mensaje"`
4. `git push`

Trabajo siempre sobre **develop**, nunca directo sobre **main**, y uso **merge** solo cuando la funcionalidad ya está probada.

---
*UTAMED — Programación Orientada a Objetos — Recuperación de la Evaluación Continua — Cheatsheet de Git*