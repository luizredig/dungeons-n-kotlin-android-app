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
import dnk.library.breeds.Dragonborn
import dnk.library.breeds.DwarfHill
import dnk.library.breeds.DwarfMountain
import dnk.library.breeds.ElfDrow
import dnk.library.breeds.ElfForest
import dnk.library.breeds.ElfHigh
import dnk.library.breeds.Gnome
import dnk.library.breeds.GnomeForest
import dnk.library.breeds.GnomeRock
import dnk.library.breeds.HalfElf
import dnk.library.breeds.HalfOrc
import dnk.library.breeds.Halfling
import dnk.library.breeds.HalflingLightfoot
import dnk.library.breeds.HalflingStout
import dnk.library.breeds.Human
import dnk.library.breeds.Tiefling

class RaceSelectionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Recupera o personagem da intent
        val character = intent.getSerializableExtra("character") as dnk.library.character.Character

        setContent {
            DungeonsNKotlinTheme {
                RaceSelectionScreen(character = character, onBackClick = { finish() }, onNextClick = {
                    val intent = Intent(this, AttributeDistributionActivity::class.java)
                    intent.putExtra("character", character) // Passa o personagem atualizado
                    startActivity(intent)
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RaceSelectionScreen(character: dnk.library.character.Character, onBackClick: () -> Unit, onNextClick: () -> Unit) {
    var selectedRace by remember { mutableStateOf("") }
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
                            breedMap.keys.forEach { race ->
                                DropdownMenuItem(
                                    text = { Text(text = race) },
                                    onClick = {
                                        selectedRace = race // Atualiza a raça selecionada com a chave do breedMap
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        val selectedBreed = breedMap[selectedRace] // Obtém o IBreed da raça selecionada
                        if (selectedBreed != null) {
                            character.breed = selectedBreed // Atribui a raça ao objeto Character
                            onNextClick()
                        }
                    },
                    enabled = selectedRace.isNotEmpty(),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "Next")
                }
            }
        }
    )
}

