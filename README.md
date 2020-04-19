# Behavior Test with Cucumber & Spring Boot

Este proyecto contiene algunos ejemplos de tests funcionales realizados con [Cucumber](https://cucumber.io/).

## Indice

* [Introducción](#Introducción)
* [Entorno](#Entorno)
* [Run](#Run)

### Por que BDD?
BDD (Behavior Driven Development) es una metodología para desarrollar software a través de la interacción continua entre desarrolladores, QA y BA dentro del equipo ágil. 
BDD tiene estos componentes principales:

- Feature file: contiene información de los escenarios, pasos y ejemplos (datos de prueba). Es un archivo de texto plano con la extensión ".feature".

- Escenario: cada función puede tener múltiples escenarios de prueba positivos y negativos, por ejemplo, iniciar sesión con la contraseña incorrecta, iniciar sesión con las credenciales de inicio de sesión correctas, etc.

- Definiciones de pasos: cada escenario contiene una lista de pasos.

### Given-When-Then
BDD se basa en estos tres pilares principales:

1. Given: Precondición.

2. When: ejecución de prueba.

3. Then: aceptación y afirmaciones.

Mas info [acá](https://github.com/cucumber/cucumber/wiki/Given-When-Then)

##### Given
El propósito de "Given" es poner el sistema en un estado conocido antes de que el usuario (o sistema externo) comience a interactuar con el sistema (en los pasos "When"). Si estuviera creando casos de uso, Givens serían sus condiciones previas.

##### When
El propósito de "When" es describir la acción clave que realiza el usuario, como interactuar con una página web. Realmente llama a la lógica de negocios o a la API.

##### Then
El propósito de los pasos de "Then" es observar resultados de las afirmaciones (assert). Estas observaciones deben estar relacionadas con el valor/beneficio de la función del negocio. Las observaciones también deben estar en algún tipo de salida (valor calculado, informe, interfaz de usuario, mensaje)

Si utiliza varios "Given", "Whens" or "Thens", pueden escribirse así:

````
Scenario: Multiple Givens
    Given one thing
    Given another thing
    Given yet another thing
    When I open my eyes
    Then I see something
    Then I don't see something else
````

O puede escribirlo de esta manera para que pueda leerse mas fluídamente

````

Scenario: Multiple Givens
    Given one thing
      And another thing
      And yet another thing
    When I open my eyes
    Then I see something
      But I don't see something else
````
Algunas ventajas de su utilización:
- Simplicidad: manejo de una sintaxis no técnica. Los archivos features están escritos en inglés simple, que se puede vincular con historias ágiles.
- La comunicación entre los negocios y el desarrollo está extremadamente enfocada como resultado de un idioma común de tipo inglés.
- El código es más fácil de mantener, flexible y ampliable.
- El código se auto documenta con los ejemplos.
- Los datos de prueba solo se pueden cambiar en el archivo feature, no en el código.
- Las historias son más fáciles de "preparar": desglose, tarea y plan.
- Hay más visibilidad sobre el progreso y el estado del equipo mediante informes. Los informes de pepino pueden compartirse con la administración de nivel superior, integrarse con Jenkins y configurarse con notificaciones por correo electrónico. También se puede integrar con herramientas automatizadas de compilación e implementación como los complementos de correo electrónico de Jenkins.

#### Unit Testing vs. TDD vs. BDD
Las pruebas unitarias son para probar módulos individuales, mientras que TDD se basa en escribir casos de prueba primero y luego escribir código para hacer que pase. BDD se basa en las pruebas de comportamiento basadas en escenarios reales (que no se pueden probar en TDD). Los test de las API REST son un buen ejemplo.

[UT vs TDD vs BDD](https://codeutopia.net/blog/2015/03/01/unit-testing-tdd-and-bdd/) 

#### Why BDD with Cucumber? Pros/Cons:  

[Brief comparison of BDD frameworks](https://dzone.com/articles/brief-comparison-bdd)

Cucumber es un framework muy poderoso para las pruebas BDD. Tiene muchas características útiles, como por ejemplo las pruebas por (tablas de datos, que pueden ser parte de los casos de prueba) o parámetros, que se pueden pasar directamente de los archivos features. Se pueden enviar múltiples conjuntos de pruebas a los casos de prueba de BDD. Los archivos features y el código relacionado son legibles y mantenibles. Además, Cucumber admite muchos lenguajes y plataformas diferentes como Ruby, Java o .NET.

#### Creamos y corremos Tests

##### Escribiendo un **Feature File**
En la carpeta src/main/test/resources agregamos nuestro archivo ".feature" (puede ser dentro de otra carpeta)

````
Feature: the message can be retrieved
  Scenario: client makes call to POST /emilio
    When the client calls /emilio
    Then the client receives status code of 200
    And the client receives server version hello
  Scenario: client makes call to GET /hello
    Given the client calls /hello
    When the client receives status code of 200
    Then the client receives server version hello
````

##### Configuramos Junit para trabajar con Cucumber
Para que JUnit tenga en cuenta Cucumber y lea los archivos de características cuando se ejecuta, la clase Cucumber debe declararse como Runner. También debemos decirle a JUnit el lugar donde buscar archivos de características y definiciones de pasos.

````java
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:Feature")
public class CucumberTest {
     
}
````
Como se puede ver, la anotación  @CucumberOptions localiza el archivo ".feature" creado anteriormente.

##### Escribimos las definiciones de los pasos
Cuando Cucumber analiza los pasos (steps) de los features, buscará métodos que tengan anotaciones con las palabras claves Gherkin para localizar las definiciones de pasos coincidentes.
La primera vez, estos metodos no existirán, para esto podemos hacer uso del codigo que muestra la consola cuando corremos la clase "CucumberTest"

````
@When("^the client calls /emilio$")
public void the_client_calls_emilio() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
````
Con esta información vamos a empezar a escribir nuestro código. 
Recuerden que nosotros debemos ejecutar llamados POST/GET... según lo especifica el comportamiento del test y esperar una respuesta (asserts). Una vez que finalicemos de escribir nuestro código, debemos correr el test que valide que lo que llega (body/headers) y lo que se respode del llamado (status, body) sea lo esperado.


###### Fuente

* [dzone](https://dzone.com/articles/microservices-test-automation-bdd-with-cucumber-jv)
* [baeldung](https://www.baeldung.com/cucumber-rest-api-testing)
* [microservices-bdd-interface-oriented](https://www.infoq.com/articles/microservices-bdd-interface-oriented/)



