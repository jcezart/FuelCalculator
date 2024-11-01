package com.example.fuelcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fuelcalculator.ui.theme.FuelCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FuelCalculatorTheme {
                ImagemFundo()
            }
        }
    }
}

@Composable
fun ImagemFundo() {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.car_fuel2),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()

        )
        ConteudoApp()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConteudoApp() {
    var distancia by remember { mutableStateOf("") }
    var consumo by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    val customColor = Color(0xFF013220)
    var resultado by remember { mutableDoubleStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Fuel Calculator",
            color = Color(0xFF013220),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 64.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextField(
            value = distancia,
            onValueChange = { input ->
                val sanitizedInput = input.replace(',', '.')
                if (sanitizedInput.isEmpty() || sanitizedInput.toDoubleOrNull() != null) {
                    distancia = sanitizedInput
                }
            },
            label = { Text("Distância Percorrida (km)") },
            placeholder = { Text("Digite a distância percorrida em km") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            textStyle = TextStyle(color = customColor),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedPlaceholderColor = Color.DarkGray,
                focusedLabelColor = customColor,
                unfocusedLabelColor = customColor,
                focusedBorderColor = customColor,
                unfocusedBorderColor = customColor,
                cursorColor = customColor
            )
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = consumo,
            onValueChange = { input ->
                val sanitizedInput = input.replace(',', '.')
                if (sanitizedInput.isEmpty() || sanitizedInput.toDoubleOrNull() != null) {
                    consumo = sanitizedInput
                }
            },
            label = { Text("Consumo (km/L)") },
            placeholder = { Text("Digite o consumo do veículo") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            textStyle = TextStyle(color = customColor),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedPlaceholderColor = Color.DarkGray,
                focusedLabelColor = customColor,
                unfocusedLabelColor = customColor,
                focusedBorderColor = customColor,
                unfocusedBorderColor = customColor,
                cursorColor = customColor
            )
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = valor,
            onValueChange = { input ->
                val sanitizedInput = input.replace(',', '.')
                if (sanitizedInput.isEmpty() || sanitizedInput.toDoubleOrNull() != null) {
                    valor = sanitizedInput
                }
            },
            label = { Text("Valor do combustível") },
            placeholder = { Text("Digite o valor do litro do combustível") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            textStyle = TextStyle(color = customColor),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedPlaceholderColor = Color.DarkGray,
                focusedLabelColor = customColor,
                unfocusedLabelColor = customColor,
                focusedBorderColor = customColor,
                unfocusedBorderColor = customColor,
                cursorColor = customColor
            )
        )

        Spacer(modifier = Modifier.height(258.dp))


        Button(
            onClick = {
                var distanciaInt = distancia.toDoubleOrNull() ?: 0.0
                var consumoInt = consumo.toDoubleOrNull() ?: 0.0
                var valorInt = valor.toDoubleOrNull() ?: 0.0


                if (consumoInt != 0.0) {
                    resultado = (distanciaInt / consumoInt) * valorInt
                } else {
                    resultado = 0.0
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = customColor,
                contentColor = Color.White
            )
        ) {
            Text(text = "Calcular")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Exibição do resultado
        if (resultado > 0.0) {
            Text(
                text = "Custo da Viagem: R$ %.2f".format(resultado),
                color = customColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


