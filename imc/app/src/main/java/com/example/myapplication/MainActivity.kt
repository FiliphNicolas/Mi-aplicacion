package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etWeight: EditText
    private lateinit var etHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView
    private lateinit var tvCategory: TextView
    private var defaultCategoryTextColor: Int = 0 // Variable para almacenar el color de texto por defecto de la categoría

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etWeight = findViewById(R.id.etWeight)
        etHeight = findViewById(R.id.etHeight)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)
        tvCategory = findViewById(R.id.tvCategory)

        // Obtener el color de texto por defecto de la categoría al inicio
        defaultCategoryTextColor = tvCategory.currentTextColor

        btnCalculate.setOnClickListener {
            val sWeight = etWeight.text.toString()
            val sHeight = etHeight.text.toString()

            // Restablecer el color de texto de la categoría por defecto al hacer clic en el botón
            tvCategory.setTextColor(defaultCategoryTextColor)

            if (sWeight.isNotEmpty() && sHeight.isNotEmpty()) {
                try {
                    val weight = sWeight.toFloat()
                    val height = sHeight.toFloat() / 100  // Convertir cm a metros
                    val imc = weight / (height * height) // Cálculo del IMC

                    // Clasificar el IMC usando "when"
                    val categoryResult: Pair<Int, String> = when {
                        imc < 18.5 -> Pair(ContextCompat.getColor(this, R.color.desnutricion), "Desnutrición")
                        imc < 25 -> Pair(ContextCompat.getColor(this, R.color.peso_ideal), "Peso Ideal")
                        imc < 30 -> Pair(ContextCompat.getColor(this, R.color.sobrepeso), "Sobrepeso")
                        imc < 35 -> Pair(ContextCompat.getColor(this, R.color.obesidad_i), "Obesidad I")
                        imc < 40 -> Pair(ContextCompat.getColor(this, R.color.obesidad_ii), "Obesidad II")
                        imc < 50 -> Pair(ContextCompat.getColor(this, R.color.obesidad_iii), "Obesidad III (Mórbida)")
                        else -> Pair(ContextCompat.getColor(this, R.color.obesidad_iv), "Obesidad IV (Extrema)") // Manejar IMC fuera de rango
                    }
                    // Mostrar el resultado
                    tvResult.text = String.format("Tu IMC es: %.2f", imc)
                    tvCategory.text = "Categoría: ${categoryResult.second}"

                    // Aplicar el color al texto de la categoría
                    tvCategory.setTextColor(categoryResult.first)

                } catch (e: NumberFormatException) {
                    // Manejar errores de formato
                    tvResult.text = "Introduce valores válidos."
                    tvCategory.text = ""
                    // No es necesario restablecer el color aquí, ya que no se aplicó un color específico
                }
            } else {
                // Mostrar mensaje de error si los campos están vacíos
                tvResult.text = "Completa ambos campos."
                tvCategory.text = ""
                // No es necesario restablecer el color aquí
            }
        }
    }
}