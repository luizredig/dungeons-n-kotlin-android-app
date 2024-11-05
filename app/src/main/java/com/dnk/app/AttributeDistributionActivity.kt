package com.dnk.app

import CharacterEntity
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
import com.dnk.app.data.AppApplication
import com.dnk.app.theme.DungeonsNKotlinTheme
import dungeons_n_kotlin.classes.modifiers.AttributesModifier

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
fun AttributeDistributionScreen(
    character: dnk.library.character.Character,
    onBackClick: () -> Unit
) {
    var availablePoints by remember { mutableStateOf(27) }

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
                Image(
                    painter = painterResource(id = R.drawable.initialbackground),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

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
                        Text(
                            text = "Available Points: $availablePoints",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        AttributeRow(
                            attributeName = "Strength",
                            attributeValue = strength,
                            onIncrement = {
                                val costToIncrease =
                                    AttributesModifier.calculateAttributeCost(strength + 1) - AttributesModifier.calculateAttributeCost(
                                        strength
                                    )
                                if (availablePoints >= costToIncrease && strength < 15) {
                                    strength++
                                    availablePoints -= costToIncrease
                                }
                            },
                            onDecrement = {
                                val costToDecrease =
                                    AttributesModifier.calculateAttributeCost(strength) - AttributesModifier.calculateAttributeCost(
                                        strength - 1
                                    )
                                if (strength > 8) {
                                    strength--
                                    availablePoints += costToDecrease
                                }
                            }
                        )

                        AttributeRow(
                            attributeName = "Dexterity",
                            attributeValue = dexterity,
                            onIncrement = {
                                val costToIncrease =
                                    AttributesModifier.calculateAttributeCost(dexterity + 1) - AttributesModifier.calculateAttributeCost(
                                        dexterity
                                    )
                                if (availablePoints >= costToIncrease && dexterity < 15) {
                                    dexterity++
                                    availablePoints -= costToIncrease
                                }
                            },
                            onDecrement = {
                                val costToDecrease =
                                    AttributesModifier.calculateAttributeCost(dexterity) - AttributesModifier.calculateAttributeCost(
                                        dexterity - 1
                                    )
                                if (dexterity > 8) {
                                    dexterity--
                                    availablePoints += costToDecrease
                                }
                            }
                        )

                        AttributeRow(
                            attributeName = "Constitution",
                            attributeValue = constitution,
                            onIncrement = {
                                val costToIncrease =
                                    AttributesModifier.calculateAttributeCost(constitution + 1) - AttributesModifier.calculateAttributeCost(
                                        constitution
                                    )
                                if (availablePoints >= costToIncrease && constitution < 15) {
                                    constitution++
                                    availablePoints -= costToIncrease
                                }
                            },
                            onDecrement = {
                                val costToDecrease =
                                    AttributesModifier.calculateAttributeCost(constitution) - AttributesModifier.calculateAttributeCost(
                                        constitution - 1
                                    )
                                if (constitution > 8) {
                                    constitution--
                                    availablePoints += costToDecrease
                                }
                            }
                        )

                        AttributeRow(
                            attributeName = "Intelligence",
                            attributeValue = intelligence,
                            onIncrement = {
                                val costToIncrease =
                                    AttributesModifier.calculateAttributeCost(intelligence + 1) - AttributesModifier.calculateAttributeCost(
                                        intelligence
                                    )
                                if (availablePoints >= costToIncrease && intelligence < 15) {
                                    intelligence++
                                    availablePoints -= costToIncrease
                                }
                            },
                            onDecrement = {
                                val costToDecrease =
                                    AttributesModifier.calculateAttributeCost(intelligence) - AttributesModifier.calculateAttributeCost(
                                        intelligence - 1
                                    )
                                if (intelligence > 8) {
                                    intelligence--
                                    availablePoints += costToDecrease
                                }
                            }
                        )

                        AttributeRow(
                            attributeName = "Wisdom",
                            attributeValue = wisdom,
                            onIncrement = {
                                val costToIncrease =
                                    AttributesModifier.calculateAttributeCost(wisdom + 1) - AttributesModifier.calculateAttributeCost(
                                        wisdom
                                    )
                                if (availablePoints >= costToIncrease && wisdom < 15) {
                                    wisdom++
                                    availablePoints -= costToIncrease
                                }
                            },
                            onDecrement = {
                                val costToDecrease =
                                    AttributesModifier.calculateAttributeCost(wisdom) - AttributesModifier.calculateAttributeCost(
                                        wisdom - 1
                                    )
                                if (wisdom > 8) {
                                    wisdom--
                                    availablePoints += costToDecrease
                                }
                            }
                        )

                        AttributeRow(
                            attributeName = "Charisma",
                            attributeValue = charisma,
                            onIncrement = {
                                val costToIncrease =
                                    AttributesModifier.calculateAttributeCost(charisma + 1) - AttributesModifier.calculateAttributeCost(
                                        charisma
                                    )
                                if (availablePoints >= costToIncrease && charisma < 15) {
                                    charisma++
                                    availablePoints -= costToIncrease
                                }
                            },
                            onDecrement = {
                                val costToDecrease =
                                    AttributesModifier.calculateAttributeCost(charisma) - AttributesModifier.calculateAttributeCost(
                                        charisma - 1
                                    )
                                if (charisma > 8) {
                                    charisma--
                                    availablePoints += costToDecrease
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {
                                val applicationContext =
                                    context.applicationContext as AppApplication
                                val characterDao = applicationContext.db.characterDao()

                                val characterEntity = CharacterEntity(
                                    name = "Nome do Personagem",
                                    strength = strength,
                                    dexterity = dexterity,
                                    constitution = constitution,
                                    intelligence = intelligence,
                                    wisdom = wisdom,
                                    charisma = charisma
                                )

                                characterDao.insert(characterEntity)

                                val intent = Intent(context, CharacterViewActivity::class.java)
                                intent.putExtra("character", characterEntity)
                                context.startActivity(intent)
                            },
                            enabled = availablePoints == 0,
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
