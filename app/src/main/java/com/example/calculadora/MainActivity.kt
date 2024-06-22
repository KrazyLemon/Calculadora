package com.example.calculadora

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.currentCoroutineContext


//Main Class
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var num1  by remember {
                mutableStateOf("0")
            }
            var num2  by remember {
                mutableStateOf("0")
            }
            var result by remember {
                mutableStateOf(0)
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Practica Calculadora",
                    fontSize = 32.sp,
                    lineHeight = 32.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                OutlinedTextField(
                    value = num1,
                    onValueChange ={ num1 = it; },
                    label = { Text("Numero 1")},
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = num2,
                    onValueChange ={ num2 = it; },
                    label = { Text("Numero 2")},
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    CalculatorButton(text = "+", color = Color.Green) {
                        calculateResult(applicationContext,num1, num2, '+', result) { newResult -> result = newResult }
                    }
                    CalculatorButton(text = "-", color = Color.Red) {
                        calculateResult(applicationContext,num1, num2, '-', result) { newResult -> result = newResult }
                    }
                    CalculatorButton(text = "/", color = Color.Magenta) {
                        calculateResult(applicationContext,num1, num2, '/', result) { newResult -> result = newResult }
                    }
                    CalculatorButton(text = "*", color = Color.Blue) {
                        calculateResult(applicationContext,num1, num2, '*', result) { newResult -> result = newResult }
                    }
                }
                Spacer(modifier = Modifier.padding(0.dp,0.dp,120.dp,20.dp))
                Row {
                    Text(
                        text = "Resultado: $result",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
                    )
                }
                Text(
                    text = "Hecho por Angel Eduardo Velazquez Morales",
                    fontSize = 12.sp
                )
            }

        }
    }
}
@Composable
fun CalculatorButton(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = text, fontSize = 18.sp)
    }
}


private fun calculateResult(
    context: Context,
    num1: String,
    num2: String,
    operator: Char,
    currentResult: Int,
    onResultCalculated: (Int) -> Unit
) {
    if (num1.isBlank() || num2.isBlank()) {
        Toast.makeText(
            context,
            "Escriba dos numeros",
            Toast.LENGTH_SHORT
        ).show()
        return
    }

    val number1 = num1.toIntOrNull() ?: 0
    val number2 = num2.toIntOrNull() ?: 0

    val newResult = when (operator) {
        '+' -> number1 + number2
        '-' -> number1 - number2
        '*' -> number1 * number2
        '/' -> if(number2 !=0) number1 / number2 else{
            Toast.makeText(
                context,
                "No se puede dividir entre Zero",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        else -> 0
    }
    onResultCalculated(newResult)
}




