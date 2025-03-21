package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etWeight: EditText
    private lateinit var etHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView
    private lateinit var tvCategory: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etWeight = findViewById(R.id.etWeight)
        etHeight = findViewById(R.id.etHeight)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)
        tvCategory = findViewById(R.id.tvCategory)

        btnCalculate.setOnClickListener {
            val sWeight = etWeight.text.toString()
            val sHeight = etHeight.text.toString()

            if (sWeight.isNotEmpty() && sHeight.isNotEmpty()) {
                try {
                    val weight = sWeight.toFloat()
                    val height = sHeight.toFloat() / 100  // Convertir cm a metros
                    val imc = weight / (height * height)

                    // Clasificar el IMC usando "when"
                    val category = when {
                        imc < 18.5 -> "Desnutrición"
                        imc < 25 -> "Peso Ideal"
                        imc < 30 -> "Sobrepeso"
                        imc < 35 -> "Obesidad I"
                        imc < 40 -> "Obesidad II"
                        imc < 50 -> "Obesidad III (Mórbida)"
                        else -> "Obesidad IV (Extrema)"
                    }

                    tvResult.text = "Tu IMC es: %.2f".format(imc)
                    tvCategory.text = "Categoría: $category"
                } catch (e: NumberFormatException) {
                    tvResult.text = "Introduce valores válidos."
                    tvCategory.text = ""
                }
            } else {
                tvResult.text = "Completa ambos campos."
                tvCategory.text = ""
            }
        }
    }
}
