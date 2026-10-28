package me.valeriaacosta.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.valeriaacosta.myapplication.ui.screens.FacebookLoginScreen
import me.valeriaacosta.myapplication.ui.screens.FacebookRegisterScreen
import me.valeriaacosta.myapplication.ui.screens.FeedScreen
import me.valeriaacosta.myapplication.ui.screens.ForgotPasswordScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val myNavController = rememberNavController()
            NavHost(
                navController = myNavController,
                startDestination = Routes.LOGIN,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = Routes.LOGIN) {
                    FacebookLoginScreen(
                        onClickRegister = {
                            myNavController.navigate(route = Routes.REGISTER)
                        },
                        onClickIniciarSesion = {
                            myNavController.navigate(route = Routes.FEED) {
                                popUpTo(route = Routes.LOGIN) { inclusive = true }
                            }
                        },
                        onClickForgotPassword = {
                            myNavController.navigate(route = Routes.FORGOT_PASSWORD)
                        }
                    )
                }
                composable(route = Routes.REGISTER) {
                    FacebookRegisterScreen(onClickBack = {
                        myNavController.popBackStack()
                    })
                }
                composable(route = Routes.FORGOT_PASSWORD) {
                    ForgotPasswordScreen(onClickBack = {
                        myNavController.popBackStack()
                    })
                }
                composable(route = Routes.FEED) {
                    FeedScreen()
                }
            }
        }
    }
}
