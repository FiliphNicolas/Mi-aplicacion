# Funcionalidades

La siguientes es una lista de las funcionalidades disponibles para el usuario de la aplicación:

1. Imc callculator
✔ Entrada de datos:

El usuario ingresa peso en kilogramos y altura en centímetros.
✔ Cálculo del IMC:

La altura ingresada en cm se convierte a metros (altura / 100).
✔ Clasificación del IMC:

Se usa la fórmula:
IMC = peso / (altura * altura)

El resultado se compara con la tabla de categorías (Desnutrición, Peso Ideal, Sobrepeso, Obesidad I, II, III, IV).
✔ Manejo de errores:

Se validan entradas vacías.
Se evita que el usuario ingrese valores no numéricos.
Se muestra un mensaje de error en caso de datos inválidos.
✔ Interfaz de usuario:

Usa EditText para entrada de datos.
Button para calcular.
TextView para mostrar el resultado y la categoría.
