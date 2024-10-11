package com.dnk.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dnk.app.theme.DungeonsNKotlinTheme

class AttributeDistributionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val character = intent.getSerializableExtra("character") as dnk.library.character.Character

        setContent {
            DungeonsNKotlinTheme {
                AttributeDistributionScreen(character = character, onBackClick = { finish() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttributeDistributionScreen(character: dnk.library.character.Character, onBackClick: () -> Unit) {
    var availablePoints by remember { mutableStateOf(27) }

    // Variáveis para armazenar os atributos do personagem, inicialmente todos em 8
    var strength by remember { mutableStateOf(8) }
    var dexterity by remember { mutableStateOf(8) }
    var constitution by remember { mutableStateOf(8) }
    var intelligence by remember { mutableStateOf(8) }
    var wisdom by remember { mutableStateOf(8) }
    var charisma by remember { mutableStateOf(8) }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Distribute Attributes") },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                // Adicionando a imagem de fundo
                Image(
                    painter = painterResource(id = R.drawable.creationbackground), // Certifique-se que o id da imagem está correto
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Caixa sobreposta com transparência
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Reintroduzindo o texto de "Available Points"
                        Text(
                            text = "Available Points: $availablePoints",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Exibir e ajustar atributos com controles de incremento/decremento
                        AttributeRow(
                            attributeName = "Strength",
                            attributeValue = strength,
                            onIncrement = {
                                if (availablePoints > 0 && strength < 15) {
                                    strength++
                                    availablePoints--
                                }
                            },
                            onDecrement = {
                                if (strength > 8) {
                                    strength--
                                    availablePoints++
                                }
                            }
                        )

                        AttributeRow(
                            attributeName = "Dexterity",
                            attributeValue = dexterity,
                            onIncrement = {
                                if (availablePoints > 0 && dexterity < 15) {
                                    dexterity++
                                    availablePoints--
                                }
                            },
                            onDecrement = {
                                if (dexterity > 8) {
                                    dexterity--
                                    availablePoints++
                                }
                            }
                        )

                        AttributeRow(
                            attributeName = "Constitution",
                            attributeValue = constitution,
                            onIncrement = {
                                if (availablePoints > 0 && constitution < 15) {
                                    constitution++
                                    availablePoints--
                                }
                            },
                            onDecrement = {
                                if (constitution > 8) {
                                    constitution--
                                    availablePoints++
                                }
                            }
                        )

                        AttributeRow(
                            attributeName = "Intelligence",
                            attributeValue = intelligence,
                            onIncrement = {
                                if (availablePoints > 0 && intelligence < 15) {
                                    intelligence++
                                    availablePoints--
                                }
                            },
                            onDecrement = {
                                if (intelligence > 8) {
                                    intelligence--
                                    availablePoints++
                                }
                            }
                        )

                        AttributeRow(
                            attributeName = "Wisdom",
                            attributeValue = wisdom,
                            onIncrement = {
                                if (availablePoints > 0 && wisdom < 15) {
                                    wisdom++
                                    availablePoints--
                                }
                            },
                            onDecrement = {
                                if (wisdom > 8) {
                                    wisdom--
                                    availablePoints++
                                }
                            }
                        )

                        AttributeRow(
                            attributeName = "Charisma",
                            attributeValue = charisma,
                            onIncrement = {
                                if (availablePoints > 0 && charisma < 15) {
                                    charisma++
                                    availablePoints--
                                }
                            },
                            onDecrement = {
                                if (charisma > 8) {
                                    charisma--
                                    availablePoints++
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // Botão para criar o personagem com os atributos definidos
                        Button(
                            onClick = {
                                // Atribui os valores selecionados aos atributos do personagem
                                character.attributes = dnk.library.attributes.Attributes(
                                    strength = strength,
                                    dexterity = dexterity,
                                    constitution = constitution,
                                    intelligence = intelligence,
                                    wisdom = wisdom,
                                    charisma = charisma
                                )

                                // Passa o personagem para a próxima activity (CharacterViewActivity)
                                val intent = Intent(context, CharacterViewActivity::class.java)
                                intent.putExtra("character", character)
                                context.startActivity(intent)
                            },
                            enabled = availablePoints == 0, // O botão só fica habilitado quando todos os pontos são distribuídos
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "Create Character")
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun AttributeRow(
    attributeName: String,
    attributeValue: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = attributeName, style = MaterialTheme.typography.bodyLarge)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = { onDecrement() },
                enabled = attributeValue > 8
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Decrement")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = attributeValue.toString(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .width(32.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(
                onClick = { onIncrement() },
                enabled = attributeValue < 15
            ) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Increment")
            }
        }
    }
}
