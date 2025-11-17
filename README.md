

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
