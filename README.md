
**ACTIVIDAD DE APRENDIZAJE DE LA 2ª EVALUACIÓN DE PSP**

Realización de una aplicación que consume una API externa (con programación reactiva)
y que presenta información al usuario mediante una interfaz gráfica hecha con JavaFX 
utilizando el programa SceneBuilder.

Para este proyecto he elegido una **API** relacionada con un videojuego 
llamado **Dead By Daylight** en el cual hay dos bandos (Supervivientes y Asesinos) y ambos bandos
tienen habilidades propias (llamadas Perks).

La **BASE_URL** es esta: https://dbd.tricky.lol/ 

Aqui se acceden a los endpoints de la API: https://dbd.tricky.lol/api

De los cuales he elegido listar por medio de dos botones, uno para cada uno:
* **Characters**: https://dbd.tricky.lol/api/characters
* **Perks**: https://dbd.tricky.lol/api/perks

Ademas de listar, muestra mas información acerca del genero del  personaje, altura... 
y en el caso de las habilidades a que rol pertenece y a quien pertenece.

He añadido una barra de progreso mientras cargan ambos filtrados.
Como filtros, dentro del proyecto he filtrado:

* **Characters by Gender** (male or female)
* **Perks by Role** (survivor or killer)

Estos filtrados han sido realizados externos a la API

Todo este código esta documentado en **GITHUB**:
https://github.com/Joserra2304/AA_PSP_REACTIVEAPI.git