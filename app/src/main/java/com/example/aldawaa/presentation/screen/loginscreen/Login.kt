package com.example.aldawaa.presentation.screen.loginscreen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.aldawaa.R
import com.example.aldawaa.presentation.screen.loginscreen.component.LoginFormEvent
import com.example.aldawaa.presentation.ui.theme.*

//login box
@Composable
fun Login(navController: NavController) {
    val loginViewModel :LoginViewModel = hiltViewModel()
    val state = loginViewModel.state
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        loginViewModel.validationEvents.collect { event ->
            when (event) {
                is LoginViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Login successful",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    val passwordvisibilitylogin = remember { mutableStateOf(false) }
    val checkboxremember = remember { mutableStateOf(false) }
   /* val password = rememberSaveable { mutableStateOf("") }
    var isErrorloginmail by rememberSaveable { mutableStateOf(false) }
    var isErrorloginpass by rememberSaveable { mutableStateOf(false) }
    val emailorphone = rememberSaveable { mutableStateOf("") }*/

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.background(Color.Transparent)
    ) {


        Spacer(modifier = Modifier.padding(15.dp))


        OutlinedTextField(

            value = state.loginemailorphone,
            onValueChange = {
                loginViewModel.onEvent(LoginFormEvent.LoginEmailOrPhoneChanged(it))

            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.enteryouremail),
                    color = lablehint
                )
            },
            isError = state.loginemailorphoneError != null,
            // keyboardActions = KeyboardActions { validate(emailorphone.value) },

            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(shape = Shapesaaldawaa.small)
                .height(51.dp)
                .background(color = labelcolor),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ), shape = Shapesaaldawaa.small
        )
        if (state.loginemailorphoneError != null) Text(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(alignment = Alignment.Start)
                .padding(start = 20.dp),
            text = state.loginemailorphoneError,
            color = errormessege,

            )

        Spacer(modifier = Modifier.padding(10.dp))


        OutlinedTextField(

            value = state.loginpassword,
            onValueChange = {
                loginViewModel.onEvent(LoginFormEvent.LoginEmailOrPhoneChanged(it))
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.enteryourpass),
                    color = lablehint
                )
            },
            isError = state.loginpasswordError != null,
            // keyboardActions = KeyboardActions { validate(password.value) },

            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Password),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(51.dp)
                .clip(shape = Shapesaaldawaa.small)
                .background(
                    color = labelcolor
                ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                errorLabelColor = errormessege,
                errorBorderColor = errormessege,

                ),
            shape = Shapesaaldawaa.small,

            trailingIcon = {
                IconButton(onClick = {
                    passwordvisibilitylogin.value = !passwordvisibilitylogin.value
                }) {

                    Icon(

                        painter = if (passwordvisibilitylogin.value) painterResource(R.drawable.eye_1) else painterResource(
                            R.drawable.eye_slash__5_
                        ),
                        contentDescription = "password",
                        //tint = if (passwordvisibility.value) Purple500 else pain
                    )
                }

            },
            visualTransformation = if (passwordvisibilitylogin.value) VisualTransformation.None else PasswordVisualTransformation()

        )
        if (state.loginpasswordError != null) Text(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(alignment = Alignment.Start)
                .padding(start = 20.dp),
            text = state.loginpasswordError,
            color = errormessege,

            )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkboxremember.value,
                    onCheckedChange = {
                        checkboxremember.value = it
                        Toast.makeText(
                            context,
                            "user checked : $checkboxremember",
                            Toast.LENGTH_SHORT
                        ).show()

                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = checkboxcolor,
                        checkmarkColor = checkedmarkcolor,
                        uncheckedColor = textcolor,
                    )
                )
                Text(text = stringResource(id = R.string.rememberme), color = textcolor)
            }


            Text(text = stringResource(id = R.string.forgetmypassword), color = textcolor,
            modifier = Modifier.clickable {
                navController.navigate("Forget_Password")
            }
                )

        }
        Spacer(modifier = Modifier.padding(15.dp))

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(51.dp)
                .clip(shape = Shapesaaldawaa.small),
            colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
            shape = Shapesaaldawaa.small,
            border = BorderStroke(2.dp, textcolor),

            onClick = {
                loginViewModel.onEvent(LoginFormEvent.login)

                Toast.makeText(context, "user logged in ", Toast.LENGTH_SHORT).show()

            },


            ) {
            Text(
                text = stringResource(id = R.string.signinorg),
                fontSize = 20.sp,
                color = textcolor,
            )

        }
        Spacer(modifier = Modifier.padding(10.dp))


    }

}