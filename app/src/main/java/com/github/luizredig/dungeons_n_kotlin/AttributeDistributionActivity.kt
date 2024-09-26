package com.github.luizredig.dungeons_n_kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import com.github.luizredig.dungeons_n_kotlin.ui.theme.DungeonsNKotlinTheme

class AttributeDistributionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DungeonsNKotlinTheme {
                AttributeDistributionScreen(onBackClick = { finish() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttributeDistributionScreen(onBackClick: () -> Unit) {
    var availablePoints by remember { mutableStateOf(27) }
    var strength by remember { mutableStateOf(8) }
    var intelligence by remember { mutableStateOf(8) }
    var faith by remember { mutableStateOf(8) }
    var charisma by remember { mutableStateOf(8) }
    var dexterity by remember { mutableStateOf(8) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Attribute Distribution") },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Available Points: $availablePoints", style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(20.dp))

                AttributeRow(
                    attributeName = "Strength",
                    attributeValue = strength,
                    onIncrement = {
                        if (strength < 15 && availablePoints > 0) {
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
                    attributeName = "Intelligence",
                    attributeValue = intelligence,
                    onIncrement = {
                        if (intelligence < 15 && availablePoints > 0) {
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
                    attributeName = "Faith",
                    attributeValue = faith,
                    onIncrement = {
                        if (faith < 15 && availablePoints > 0) {
                            faith++
                            availablePoints--
                        }
                    },
                    onDecrement = {
                        if (faith > 8) {
                            faith--
                            availablePoints++
                        }
                    }
                )

                AttributeRow(
                    attributeName = "Charisma",
                    attributeValue = charisma,
                    onIncrement = {
                        if (charisma < 15 && availablePoints > 0) {
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

                AttributeRow(
                    attributeName = "Dexterity",
                    attributeValue = dexterity,
                    onIncrement = {
                        if (dexterity < 15 && availablePoints > 0) {
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

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        Toast.makeText(context, "Character Created!", Toast.LENGTH_SHORT).show()
                    },
                    enabled = availablePoints == 0,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Create Character")
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
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Text(text = attributeName, style = MaterialTheme.typography.bodyLarge)
        Row {
            Button(onClick = { onDecrement() }, enabled = attributeValue > 8) {
                Text("-")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = attributeValue.toString(), style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onIncrement() }, enabled = attributeValue < 15) {
                Text("+")
            }
        }
    }
}
