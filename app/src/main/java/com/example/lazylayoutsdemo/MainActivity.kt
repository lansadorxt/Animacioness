package com.example.lazylayoutsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
// Reemplaza LazyLayoutsDemoTheme por tu tema real si es diferente
import com.example.lazylayoutsdemo.ui.theme.LazyLayoutsDemoTheme

class MainActivity : ComponentActivity() {

    private val fotoGeneral = R.drawable.foto
    private val personas = arrayListOf(
        Data("Valeria Morales", "Diseñadora UX/UI", 134, fotoGeneral),
        Data("Mateo Gutiérrez", "Desarrollador Backend", 98, fotoGeneral),
        Data("Camila Fernández", "Especialista en QA", 112, fotoGeneral),
        Data("Santiago Herrera", "Ingeniero Cloud / DevOps", 85, fotoGeneral),
        Data("Lucía Benítez", "Scrum Master & PM", 145, fotoGeneral),
        Data("Gabriel Mendoza", "Analista de Seguridad", 67, fotoGeneral)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LazyLayoutsDemoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        RowLazy()
                        MyColumn(personas = personas)
                    }
                }
            }
        }
    }
}

@Composable
fun MyColumn(
    modifier: Modifier = Modifier,
    personas: List<Data>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {

        item {
            Text(
                text = "Equipo de trabajo",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 12.dp,
                    bottom = 10.dp
                )
            )
        }

        items(personas) { persona ->
            TarjetaAnimada(item = persona)
        }
    }
}

@Composable
fun TarjetaAnimada(item: Data) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 14.dp,
                vertical = 7.dp
            )
            .animateContentSize(),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF3E5F5)
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(15.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = item.imagen),
                    contentDescription = "Imagen del integrante",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                )

                Spacer(
                    modifier = Modifier.width(14.dp)
                )

                Column {

                    Text(
                        text = item.nombre,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6A1B9A)
                    )

                    Text(
                        text = item.description,
                        color = Color(0xFF555555)
                    )

                    Text(
                        text = "Me gusta: ${item.likes}",
                        color = Color(0xFFC62828),
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(9.dp)
            )

            Button(
                onClick = {
                    isExpanded = !isExpanded
                }
            ) {
                Text(
                    text = if (isExpanded)
                        "Ocultar información"
                    else
                        "Ver información"
                )
            }

            AnimatedVisibility(
                visible = isExpanded
            ) {

                Column {

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Estudiante relacionado con el área de Tecnologías de la Información y colaborador en proyectos de desarrollo.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF666666)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    val personasEjemplo = listOf(
        Data(
            "Valeria Morales",
            "Diseñadora UX/UI",
            134,
            R.drawable.foto
        )
    )

    LazyLayoutsDemoTheme {
        Column {
            RowLazy()
            MyColumn(personas = personasEjemplo)
        }
    }
}