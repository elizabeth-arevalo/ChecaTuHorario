package com.example.checatuhorario.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.checatuhorario.CalendarScreen
import com.example.checatuhorario.ContactListScreen
import com.example.checatuhorario.Forgot2Screen
import com.example.checatuhorario.Forgot3Screen
import com.example.checatuhorario.ForgotScreen
import com.example.checatuhorario.HomeScreen
import com.example.checatuhorario.LoginScreen
import com.example.checatuhorario.RegisterScreen
import com.example.checatuhorario.SplashScreen
import com.example.checatuhorario.WelcomeScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.SplashScreen.route){
        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(AppScreens.WelcomeScreen.route){
            WelcomeScreen(navController)
        }
        composable(AppScreens.HomeScreen.route){
            HomeScreen(navController)
        }
        composable(AppScreens.LoginScreen.route){
            LoginScreen(navController)
        }
        composable(AppScreens.ForgotScreen.route){
            ForgotScreen(navController)
        }
        composable(AppScreens.Forgot2Screen.route){
            Forgot2Screen(navController)
        }
        composable(AppScreens.Forgot3Screen.route){
            Forgot3Screen(navController)
        }
        composable(AppScreens.RegisterScreen.route){
            RegisterScreen(navController)
        }
        composable(AppScreens.CalendarScreen.route){
            CalendarScreen(navController)
        }
        composable(AppScreens.ContactsScreen.route){
            ContactListScreen(navController)
        }
    }
}