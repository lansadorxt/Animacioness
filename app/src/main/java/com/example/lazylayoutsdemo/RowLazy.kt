package com.example.lazylayoutsdemo

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RowLazy() {

    val dias = listOf(
        "Lunes",
        "Martes",
        "Miércoles",
        "Jueves",
        "Viernes",
        "Sábado",
        "Domingo"
    )

    var diaActual by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(
            text = "Selecciona un día",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(dias) { dia ->

                val seleccionado = dia == diaActual

                val colorTarjeta by animateColorAsState(
                    targetValue = if (seleccionado) {
                        Color(0xFF81D4FA)
                    } else {
                        Color(0xFFE1F5FE)
                    },
                    label = "AnimacionColor"
                )

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = colorTarjeta
                    ),

                    modifier = Modifier.clickable {
                        diaActual = dia
                    }
                ) {

                    Text(
                        text = dia,
                        modifier = Modifier.padding(
                            horizontal = 15.dp,
                            vertical = 9.dp
                        ),
                        color = if (seleccionado) {
                            Color(0xFF01579B)
                        } else {
                            Color.DarkGray
                        }
                    )
                }
            }
        }
    }
}