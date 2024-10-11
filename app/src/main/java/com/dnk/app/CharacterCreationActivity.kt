package com.dnk.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.ExperimentalComposeUiApi
import com.dnk.app.theme.DungeonsNKotlinTheme


class CharacterCreationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val character = intent.getSerializableExtra("character") as dnk.library.character.Character

        setContent {
            DungeonsNKotlinTheme {
                CharacterCreationScreen(character = character, onBackClick = { finish() }, onNextClick = {
                    val intent = Intent(this, BreedSelectionActivity::class.java)
                    intent.putExtra("character", character)
                    startActivity(intent)
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CharacterCreationScreen(character: dnk.library.character.Character, onBackClick: () -> Unit, onNextClick: () -> Unit) {
    var characterName by remember { mutableStateOf(character.name) }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Creating Character") },
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
                    .clickable {
                        keyboardController?.hide()
                    }
                    .padding(padding)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.charactercreationbackground),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .align(Alignment.Center),
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                    tonalElevation = 8.dp,
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "First, let's define your new character's name.",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        OutlinedTextField(
                            value = characterName,
                            onValueChange = { newName -> characterName = newName },
                            label = { Text("Character Name") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    if (characterName.isNotBlank()) {
                                        character.name = characterName
                                        onNextClick()
                                        keyboardController?.hide()
                                    } else {
                                        Toast.makeText(context, "Please enter a character name", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            )
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = {
                                if (characterName.isNotBlank()) {
                                    character.name = characterName
                                    onNextClick()
                                } else {
                                    Toast.makeText(context, "Please enter a character name", Toast.LENGTH_SHORT).show()
                                }
                            },
                            enabled = characterName.isNotBlank(),
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
