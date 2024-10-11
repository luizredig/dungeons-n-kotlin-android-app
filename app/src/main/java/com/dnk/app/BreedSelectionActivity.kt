package com.dnk.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dnk.app.theme.DungeonsNKotlinTheme
import dnk.library.breeds.*

class BreedSelectionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val character = intent.getSerializableExtra("character") as dnk.library.character.Character

        setContent {
            DungeonsNKotlinTheme {
                BreedSelectionScreen(character = character, onBackClick = { finish() }, onNextClick = {
                    val intent = Intent(this, AttributeDistributionActivity::class.java)
                    intent.putExtra("character", character)
                    startActivity(intent)
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedSelectionScreen(character: dnk.library.character.Character, onBackClick: () -> Unit, onNextClick: () -> Unit) {
    var selectedBreed by remember { mutableStateOf("") }
    val breedMap = mapOf(
        "Dragonborn" to Dragonborn(),
        "DwarfHill" to DwarfHill(),
        "DwarfMountain" to DwarfMountain(),
        "ElfDrow" to ElfDrow(),
        "ElfForest" to ElfForest(),
        "ElfHigh" to ElfHigh(),
        "Gnome" to Gnome(),
        "GnomeForest" to GnomeForest(),
        "GnomeRock" to GnomeRock(),
        "HalfElf" to HalfElf(),
        "Halfling" to Halfling(),
        "HalflingLightfoot" to HalflingLightfoot(),
        "HalflingStout" to HalflingStout(),
        "HalfOrc" to HalfOrc(),
        "Human" to Human(),
        "Tiefling" to Tiefling()
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Breed") },
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
                    painter = painterResource(id = R.drawable.breedselectionbackgroud),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.Center)
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(20.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Please select your character's breed.",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        var expanded by remember { mutableStateOf(false) }

                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = { expanded = !expanded }
                        ) {
                            OutlinedTextField(
                                value = selectedBreed,
                                onValueChange = { },
                                label = { Text("Breed") },
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
                                modifier = Modifier.heightIn(max = 200.dp)
                            ) {
                                breedMap.keys.forEach { breed ->
                                    DropdownMenuItem(
                                        text = { Text(text = breed) },
                                        onClick = {
                                            selectedBreed = breed
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {
                                val selectedBreed = breedMap[selectedBreed]
                                if (selectedBreed != null) {
                                    character.breed = selectedBreed
                                    onNextClick()
                                }
                            },
                            enabled = selectedBreed.isNotEmpty(),
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text(text = "Next")
                        }
                    }
                }
            }
        }
    )
}
