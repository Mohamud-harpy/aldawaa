@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.aldawaa.utils

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aldawaa.presentation.screen.Forgetpassword.ForgetChangePassword
import com.example.aldawaa.presentation.screen.Forgetpassword.ForgetPassword
import com.example.aldawaa.presentation.screen.Forgetpassword.ForgetPasswordOtp
import com.example.aldawaa.presentation.screen.Forgetpassword.ForgetSuccess
import com.example.aldawaa.presentation.screen.loginscreen.Loginpage
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi


@DelicateCoroutinesApi
@ExperimentalMaterialApi
@Preview
@Composable
fun Navigatpage(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "Login_page",
        builder = {
            composable("Login_page", content = { Loginpage(navController = navController) })
            composable("Forget_Password", content = { ForgetPassword(navController = navController)  })
            composable("Forget_Password_Otp", content = { ForgetPasswordOtp(navController = navController)  })
            composable("Forget_Change_Password", content = { ForgetChangePassword(navController = navController)  })
            composable("Forget_Success", content = { ForgetSuccess(navController = navController)  })



        }
    )
}
