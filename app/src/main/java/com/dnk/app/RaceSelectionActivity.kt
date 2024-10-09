package com.dnk.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dnk.app.theme.DungeonsNKotlinTheme

class RaceSelectionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DungeonsNKotlinTheme {
                RaceSelectionScreen(onBackClick = { finish() }, onNextClick = {
                    val intent = Intent(this, AttributeDistributionActivity::class.java)
                    startActivity(intent)
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RaceSelectionScreen(onBackClick: () -> Unit, onNextClick: () -> Unit) {
    var selectedRace by remember { mutableStateOf("") }
    val races = listOf(
        "Dragonborn", "DwarfHill", "DwarfMountain", "ElfDrow", "ElfForest",
        "ElfHigh", "Gnome", "GnomeForest", "GnomeRock", "HalfElf", "Halfling",
        "HalflingLightfoot", "HalflingStout", "HalfOrc", "Human", "Tiefling"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Race") },
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Please select your character's race.",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    var expanded by remember { mutableStateOf(false) }

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedRace,
                            onValueChange = { },
                            label = { Text("Race") },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            colors = ExposedDropdownMenuDefaults.textFieldColors()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.heightIn(max = 200.dp) // Limitar a altura do menu para 4 itens
                        ) {
                            races.forEach { race ->
                                DropdownMenuItem(
                                    text = { Text(text = race) },
                                    onClick = {
                                        selectedRace = race
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { onNextClick() },
                    enabled = selectedRace.isNotEmpty(),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(horizontal = 24.dp)
                ) {
                    Text(text = "Next")
                }
            }
        }
    )
}

