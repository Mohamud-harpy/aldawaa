package com.example.aldawaa.presentation.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import com.google.android.gms.common.api.ApiException
import com.example.aldawaa.presentation.screen.twitterscreen.AuthViewModel
import com.example.aldawaa.R
import com.example.aldawaa.u.HomeScreen
import com.example.aldawaa.utils.AuthResultContract
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun AuthScreen(
    authViewModel: AuthViewModel
) {

    val coroutineScope = rememberCoroutineScope()
    var text by remember { mutableStateOf<String?>(null) }
    val user by remember(authViewModel) { authViewModel.user }.collectAsState()
    val signInRequestCode = 1

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account == null) {
                    text = "Google sign in failed"
                } else {


                    coroutineScope.launch {
                        authViewModel.signIn(
                            email = account.email!!,
                            displayName = account.displayName!!,


                            )
                        val id = account.id!!
                        Log.e("Google id : ", id)
                        Log.e("Google id : ", task.result.idToken.toString())


                        val familyName = account.familyName!!
                        Log.e("Google familyName : ", familyName.toString())
                        val givenName = account.givenName!!
                        Log.e("Google givenName : ", givenName.toString())
                        val grantedScopes = account.grantedScopes
                        Log.e("Google grantedScopes : ", grantedScopes.toString())
                        val accountt = account.account!!
                        Log.e("Google accountt : ", accountt.toString())
                        val idToken = account.idToken.toString()
                        Log.e("Google idToken : ", idToken)
                        val isExpired = account.isExpired
                        Log.e("Google isExpired : ", isExpired.toString())
                        val photoUrl = account.photoUrl.toString()
                        Log.e("Google photoUrl : ", photoUrl)
                        val requestedScopes = account.requestedScopes
                        Log.e("Google-requested: ", requestedScopes.toString())
                        val serverAuthCode = account.serverAuthCode.toString()
                        Log.e("Google serverAuthCo : ", serverAuthCode)


                    }
                }

            } catch (e: ApiException) {
                text = "Google sign in failed"
            }
        }

    AuthView(
        errorText = text,
        onClick = {
            text = null
            authResultLauncher.launch(signInRequestCode)
        }
    )

    user?.let {
        HomeScreen(user = it)

    }


}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalMaterialApi
@Composable
fun AuthView(
    errorText: String?,
    onClick: () -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }


            SignInButton(

                icon = painterResource(id = R.drawable.google),
                onClick = {
                    isLoading = true
                    onClick()
                }
            )

            errorText?.let {
                isLoading = false
                Text(text = it)
            }
        }



