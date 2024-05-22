package com.example.checatuhorario.navigation

sealed class AppScreens (val route: String){
    data object SplashScreen: AppScreens("splash_screen")
    data object WelcomeScreen: AppScreens("welcome_screen")
    data object HomeScreen: AppScreens("main_screen")
    data object LoginScreen: AppScreens("login_screen")
    data object ForgotScreen: AppScreens("forgot_screen")
    data object Forgot2Screen: AppScreens("forgot2_screen")
    data object Forgot3Screen: AppScreens("forgot3_screen")
    data object RegisterScreen: AppScreens("register_screen")
    data object CalendarScreen: AppScreens("calendar_screen")
    data object ContactsScreen: AppScreens("contacts_screen")
}