package edu.diseno_apps.emenulateral1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AcercaDeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Acerca de",
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("Aplicación: Mi Primera App")
            Text("Estudiante: Leo Cuc")
            Text("Grupo: 4B")
            Text("Versión: 1.0")

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Esta aplicación permite navegar entre diferentes pantallas mediante un menú lateral desplegable.",
                fontSize = 16.sp
            )
        }
    }
}