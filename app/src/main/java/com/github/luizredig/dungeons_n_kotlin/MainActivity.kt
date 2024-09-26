package com.github.luizredig.dungeons_n_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.luizredig.dungeons_n_kotlin.ui.theme.DungeonsNKotlinTheme

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
                        // Navega para a Activity de criação de personagem
                        val intent = Intent(this, CharacterCreationActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(onStartClicked: () -> Unit) {
    // Centraliza o conteúdo na tela
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Dungeons N Kotlin",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp)) // Espaço entre o título e o botão

        // Botão "Start"
        Button(onClick = onStartClicked) {
            Text(text = "Start")
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
