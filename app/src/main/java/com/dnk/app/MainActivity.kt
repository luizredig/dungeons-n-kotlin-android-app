package com.dnk.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dnk.app.theme.DungeonsNKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DungeonsNKotlinTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen {
                        val character = dnk.library.character.Character()
                        val intent = Intent(this, CharacterCreationActivity::class.java)
                        intent.putExtra("character", character)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(onStartClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Coloque aqui o ID correto da sua imagem
        Image(
            painter = painterResource(id = R.drawable.initialbackground), // Substitua por seu ID de imagem
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop, // Ajusta a imagem para preencher a tela
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surface, // Fundo branco (ou baseado no tema)
                shape = MaterialTheme.shapes.medium, // Formato arredondado
                tonalElevation = 8.dp // Elevação para efeito de sombra leve
            ) {
                Text(
                    text = "Dungeons N Kotlin",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(20.dp) // Padding de 20px
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = onStartClicked) {
                Text(text = "Start")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    DungeonsNKotlinTheme {
        MainScreen(onStartClicked = {})
    }
}
