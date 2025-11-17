
Julissa De frías <julissaivette15@gmail.com>
4:13 PM (8 minutes ago)
to me

# Conversor de Monedas

Este es un sencillo conversor de monedas escrito en Java que utiliza la API de [ExchangeRate-API](https://www.exchangerate-api.com/) para obtener las tasas de cambio actualizadas.

## Características

*   Permite al usuario ingresar un monto a convertir desde una moneda base (USD por defecto).
*   Permite al usuario especificar las monedas a las que desea convertir el monto.
*   Muestra las tasas de cambio y los resultados de la conversión.
*   Maneja errores y valida la entrada del usuario.
*   Permite repetir la conversión varias veces.

## Requisitos

*   Java 8 o superior
*   Librería Gson (para parsear JSON)
*   Cuenta en [ExchangeRate-API](https://www.exchangerate-api.com/) para obtener una API key

## Cómo usar

1.  Clona este repositorio:

    ```bash
    git clone [URL_DE_TU_REPOSITORIO]
    ```

2.  Abre el proyecto en tu IDE (IntelliJ IDEA, Eclipse, etc.).

3.  Agrega la librería Gson a tu proyecto.

4.  Reemplaza `"TU_API_KEY"` con tu API key real en el archivo `ConversorMonedas.java`.

5.  Compila y ejecuta el código.

6.  Sigue las instrucciones en la consola para ingresar el monto a convertir y las monedas deseadas.

## Ejemplo de uso

Bienvenido al Conversor de Monedas!
Ingrese el monto a convertir desde USD: 100
Ingrese las monedas deseadas (separadas por comas, ej: EUR,GBP,JPY): EUR,GBP
Resultados de la conversión:
100.00 USD = 92.00 EUR (Tasa: 0.9200)
100.00 USD = 80.00 GBP (Tasa: 0.8000)
¿Desea realizar otra conversión? (s/n): n
¡Gracias por usar el Conversor de Monedas!


## Créditos

* Este proyecto utiliza la API de [ExchangeRate-API](https://www.exchangerate-api.com/).
* La librería Gson es utilizada para parsear JSON.
