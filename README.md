# Proyecto-Ajedrez
Para ejecutar la aplicacion debes pasar los siguientes parámetros:

$ java searchChess método tamaño probabilidad agente semilla

- Método: puede ser breadth-first (anchura), depth-first (profundidad), uniform-cost (coste
uniforme), depth-limited (profundidad limitada), iterative-deepening (profundidad limitada
iterativa), best-first (primero mejor) y a-star (A-estrella).

- Tamaño: es un número natural positivo que indica el tamaño del tablero.

- Probabilidad: es un número real en [0,1] que indica la probabilidad de las piezas de aparecer o no
en el tablero. Si es 1 estarán todas las piezas y si es 0.5 estarán aproximadamente la mitad.

- Agente: es un número natural positivo en 0,1,2,3,4,5 indicando que tipo de pieza es nuestro agente:
peón (0), torre (1), alfil (2), caballo (3), reina (4) o rey (5). Siempre serán blancas.

- Semilla es un número natural positivo que se usará como semilla para generar la configuración
inicial.

Por ejemplo, la llamada:
$ java searchChess uniform-cost 8 0.5 1 2020
