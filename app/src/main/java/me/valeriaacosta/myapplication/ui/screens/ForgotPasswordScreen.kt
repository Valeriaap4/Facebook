package me.valeriaacosta.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Colores de esta pantalla
private val MetaBlue = Color(0xFF0064E0)
private val SecondaryButtonGray = Color(0xFFF0F2F5)
private val BorderGray = Color(0xFFDADDE1)
private val DarkText = Color(0xFF1C1E21)
private val HintGray = Color(0xFF65676B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(onClickBack: () -> Unit) {
    var inputText by remember { mutableStateOf("") }
    var searchByEmail by remember { mutableStateOf(false) }
    var codeSent by remember { mutableStateOf(false) }

    // Textos que cambian según si busca por celular o por correo
    val subtitle = if (searchByEmail) "Ingresa tu correo electrónico." else "Ingresa tu número de celular."
    val placeholder = if (searchByEmail) "Correo electrónico" else "Número de celular"
    val switchText = if (searchByEmail) "Buscar por número de celular" else "Buscar por correo electrónico"

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { onClickBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Ir atrás",
                            tint = DarkText
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Título
            Text(
                text = "Encuentra tu cuenta",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtítulo
            Text(
                text = subtitle,
                fontSize = 17.sp,
                color = DarkText
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Campo de texto
            OutlinedTextField(
                value = inputText,
                onValueChange = {
                    inputText = it
                    codeSent = false
                },
                placeholder = {
                    Text(text = placeholder, color = HintGray, fontSize = 16.sp)
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = if (searchByEmail) KeyboardType.Email else KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MetaBlue,
                    unfocusedBorderColor = BorderGray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = MetaBlue
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón principal "Continuar"
            Button(
                onClick = { if (inputText.isNotBlank()) codeSent = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = MetaBlue)
            ) {
                Text(
                    text = "Continuar",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón secundario gris: alterna entre celular y correo
            Button(
                onClick = {
                    searchByEmail = !searchByEmail
                    inputText = ""
                    codeSent = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = SecondaryButtonGray),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Text(
                    text = switchText,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    color = DarkText
                )
            }

            // Mensaje de confirmación
            if (codeSent) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Te enviamos un código de recuperación a $inputText.",
                    fontSize = 14.sp,
                    color = Color(0xFF42B72A)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(onClickBack = {})
}