package com.example.calculadorabasica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvInput: TextView
    private var lastNumeric = false
    private var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onButtonClick(view: View) {
        if (view is Button) {
            when (view.id) {
                R.id.btnClear -> {
                    tvInput.text = "0"
                    lastNumeric = false
                    lastDot = false
                }
                R.id.btnDelete -> {
                    deleteLastCharacter()
                }
                R.id.btnEquals -> {
                    calculateResult()
                }
                R.id.btnDot -> {
                    if (lastNumeric && !lastDot) {
                        tvInput.append(".")
                        lastNumeric = false
                        lastDot = true
                    }
                }
                R.id.btnParenthesisOpen, R.id.btnParenthesisClose -> {
                    tvInput.append(view.text)
                    lastNumeric = false
                }
                else -> {
                    if (tvInput.text.toString() == "0") {
                        tvInput.text = view.text
                    } else {
                        tvInput.append(view.text)
                    }
                    lastNumeric = true
                }
            }
        }
    }

    private fun calculateResult() {
        if (lastNumeric) {
            var input = tvInput.text.toString()
            try {
                val result = eval(input)
                tvInput.text = result.toString()
            } catch (e: Exception) {
                tvInput.text = "Error"
            }
            lastDot = true
        }
    }

    private fun eval(expression: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0

            fun nextChar() {
                ch = if (++pos < expression.length) expression[pos].toInt() else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.toInt()) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < expression.length) throw RuntimeException("Unexpected: " + ch.toChar())
                return x
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    when {
                        eat('+'.toInt()) -> x += parseTerm()
                        eat('-'.toInt()) -> x -= parseTerm()
                        else -> return x
                    }
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    when {
                        eat('*'.toInt()) -> x *= parseFactor()
                        eat('/'.toInt()) -> x /= parseFactor()
                        else -> return x
                    }
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.toInt())) return parseFactor()
                if (eat('-'.toInt())) return -parseFactor()
                var x: Double
                val startPos = pos
                if (eat('('.toInt())) {
                    x = parseExpression()
                    eat(')'.toInt())
                } else if (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) {
                    while (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) nextChar()
                    x = expression.substring(startPos, pos).toDouble()
                } else {
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }
                return x

            }
            private fun deleteLastCharacter() {
                val currentText = tvInput.text.toString()
                if (currentText.isNotEmpty() && currentText != "0") {
                    val newText = currentText.substring(0, currentText.length - 1)
                    tvInput.text = if (newText.isEmpty()) "0" else newText
                }
                // Actualizar estados
                lastNumeric = tvInput.text.toString().lastOrNull()?.isDigit() == true
                lastDot = tvInput.text.toString().endsWith(".")
            }
        }.parse()
    }
    private fun deleteLastCharacter() {
        val currentText = tvInput.text.toString()
        if (currentText.isNotEmpty() && currentText != "0") {
            val newText = currentText.substring(0, currentText.length - 1)
            tvInput.text = if (newText.isEmpty()) "0" else newText
        }
        // Actualizar estados
        lastNumeric = tvInput.text.toString().lastOrNull()?.isDigit() == true
        lastDot = tvInput.text.toString().endsWith(".")
    }
}