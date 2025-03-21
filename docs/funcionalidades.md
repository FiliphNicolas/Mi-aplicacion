# Funcionalidades de la Aplicación

## 1. Calculadora de IMC

### Entrada de Datos
- El usuario ingresa:
  - Peso en kilogramos.
  - Altura en centímetros.

### Cálculo del IMC
- La altura ingresada en cm se convierte a metros (`altura / 100`).
- Se usa la fórmula: `IMC = peso / (altura * altura)`.

### Clasificación del IMC
- El resultado se compara con la tabla de categorías:
  - Desnutrición
  - Peso Ideal
  - Sobrepeso
  - Obesidad I
  - Obesidad II
  - Obesidad III
  - Obesidad IV

### Manejo de Errores
- Se validan entradas vacías.
- Se evita que el usuario ingrese valores no numéricos.
- Se muestra un mensaje de error en caso de datos inválidos.

### Interfaz de Usuario
- **EditText**: Para entrada de datos.
- **Button**: Para calcular.
- **TextView**: Para mostrar el resultado y la categoría.

---

## 2. Calculadora Básica

### Interfaz de Usuario (UI)
- **TextView**: Muestra la entrada del usuario y el resultado.
- **Botones**: Organizados en un `GridLayout` para simular una calculadora física.
  - Números (0-9).
  - Operadores (+, -, *, /).
  - Paréntesis ((), ).
  - Punto decimal (.).
  - Botón de igual (`=`) para calcular el resultado.
  - Botón de borrar (`DEL`) para eliminar el último carácter.
  - Botón de limpiar (`C`) para reiniciar la calculadora.

### Lógica de la Calculadora

#### Entrada del Usuario
- Cuando el usuario presiona un botón, el texto correspondiente se agrega al `TextView`.
- Se manejan casos especiales:
  - Evitar múltiples puntos decimales en un número.
  - Agregar operadores después de números.

#### Cálculo del Resultado
- Cuando el usuario presiona el botón `=`, la expresión ingresada en el `TextView` se evalúa.
- Se utiliza una función `eval` personalizada para analizar y calcular la expresión matemática.

#### Borrado de Caracteres
- El botón `DEL` elimina el último carácter del `TextView`.
- Si no quedan caracteres, el `TextView` muestra `"0"`.

#### Limpieza
- El botón `C` reinicia la calculadora, estableciendo el `TextView` en `"0"`.

#### Manejo de Errores
- Si la expresión ingresada no es válida (por ejemplo, división por cero o paréntesis desbalanceados), se muestra `"Error"` en el `TextView`.
