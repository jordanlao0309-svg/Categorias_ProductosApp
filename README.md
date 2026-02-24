# App de Gestión de Categorías y Productos

Esta es una aplicación Android moderna para la gestión de un catálogo de productos organizado por categorías, desarrollada como parte de la tarea del curso de Desarrollo de Aplicaciones Móviles.

## 🚀 Características

- **Gestión de Categorías:** CRUD completo (Crear, Leer, Actualizar, Eliminar) para categorías.
- **Gestión de Productos:** CRUD completo para productos asociados a cada categoría.
- **Relación entre Entidades:** Implementación de una relación One-to-Many entre Categorías y Productos con eliminación en cascada.
- **Confirmación de Acciones:** Diálogos de confirmación antes de eliminar datos sensibles.
- **Interfaz Moderna:** Diseño basado en Material Design 3 con soporte para componentes dinámicos.

## 🛠️ Tecnologías Utilizadas

- **Jetpack Compose:** Para la construcción de la interfaz de usuario de forma declarativa (Sin XML).
- **Room Persistence Library:** Para el manejo de la base de datos local y persistencia de datos.
- **Navigation Compose:** Gestión de la navegación entre pantallas mediante rutas.
- **Arquitectura MVVM:** Separación clara de responsabilidades entre el Modelo, la Vista y el ViewModel.
- **Coroutines & Flow:** Para el manejo de operaciones asíncronas y flujo de datos en tiempo real.
- **Kotlin 2.0:** Uso de las últimas características del lenguaje y el nuevo plugin de compilación de Compose.

## 📂 Estructura del Proyecto

- `data/`: Contiene las entidades, DAOs y la configuración de la base de datos Room.
- `ui/`: Contiene los ViewModels y las pantallas (Screens) desarrolladas en Compose.
- `ui/theme/`: Definición de colores, tipografía y tema de la aplicación.
- `MainActivity.kt`: Punto de entrada y configuración del NavHost.

## 📝 Requisitos de la Tarea Cumplidos

- [x] Dos entidades con Room relacionadas.
- [x] CRUD completo para ambas entidades.
- [x] Navegación entre pantallas.
- [x] Arquitectura MVVM.
- [x] Uso exclusivo de Jetpack Compose (Sin XML / No RecyclerView).

---
**Entregado por:** [Tu Nombre]  
**Fecha de entrega:** 11:59 PM (Hoy)
