# Sistema de Evaluación para Linux

Este proyecto forma parte del trabajo de egreso del profesorado de informática para la asignatura "Taller 2". Se ha desarrollado un sistema de evaluación que permite realizar preguntas de opción múltiple, verdadero o falso y completar espacios en blanco. El sistema utiliza Java y está diseñado con una arquitectura cliente-servidor. El desarrollo se llevó a cabo utilizando NetBeans en su versión 22.

## Integrantes del Proyecto

- **Ana Valiño**
- **Gabriel Vázquez**
- **Santiago Martinez**
- **Gonzalo Vázquez**
- **Juan Acuña**

## Descripción del Proyecto

El sistema de evaluación está orientado a la enseñanza de conceptos de Linux y proporciona una interfaz de consola diseñada desde cero. A través de este sistema, los usuarios pueden responder preguntas y recibir retroalimentación sobre su desempeño.

### Características

- **Preguntas de Opción Múltiple:** Permite seleccionar una o más opciones entre varias propuestas.
- **Preguntas de Verdadero o Falso:** El usuario debe determinar si una afirmación es verdadera o falsa.
- **Completar Espacios en Blanco:** El usuario completa una frase o código con las palabras o fragmentos correctos.

### Arquitectura

El sistema sigue una arquitectura cliente-servidor donde:

- **Cliente:** Es una consola interactiva desarrollada en Java, que se comunica con el servidor para enviar y recibir datos de las evaluaciones.
- **Servidor:** Gestiona las evaluaciones, almacena las preguntas y respuestas, y proporciona retroalimentación en función de las respuestas del usuario.

## Requisitos del Sistema

- **Java JDK 11** o superior.
- **NetBeans 22**.

## Instalación y Configuración

1. **Clonar el Repositorio:**

   ```bash
   git clone https://github.com/usuario/sistema-evaluacion-linux.git
   ```
