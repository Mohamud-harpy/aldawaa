package com.example.aldawaa.presentation.screen.Forgetpassword

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aldawaa.R
import com.example.aldawaa.presentation.ui.theme.*
import com.example.aldawaa.utils.ValidationHelper

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ForgetChangePassword (navController: NavController) {

    val validationHelper : ValidationHelper = ValidationHelper()

    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    var isErrorpassword by rememberSaveable { mutableStateOf(false) }
    var isErrorconfirmpassword by rememberSaveable { mutableStateOf(false) }
    val passwordvisibilityforget = remember { mutableStateOf(false) }
    val confirmpasswordvisibilityforget= remember { mutableStateOf(false) }

    val forgetpassword = rememberSaveable { mutableStateOf("") }
    val forgetconfirmpassword = rememberSaveable { mutableStateOf("") }


    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), scaffoldState = scaffoldState
    ) {


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .background(color = textcolor)
                .verticalScroll(rememberScrollState())

        ) {

            Spacer(modifier = Modifier.padding(15.dp))

            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(80.dp)
                    .background(color = textcolor),

                painter = painterResource(R.drawable.ic_aldawaaa),
                contentDescription = "login image",
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.padding(15.dp))

            Text(
                text = stringResource(id = R.string.forgetpassword),
                fontSize = 25.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.padding(15.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,

                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .clip(
                        shape = Shapes.large.copy(
                            bottomStart = ZeroCornerSize,
                            bottomEnd = ZeroCornerSize
                        )
                    )
                    // .background(color = White)
                    .paint(
                        painterResource(id = R.drawable.group),
                        contentScale = ContentScale.Fit,

                        )


            ) {
                Spacer(modifier = Modifier.padding(15.dp))




                OutlinedTextField(

                    value = forgetpassword.value,
                    onValueChange = {
                        forgetpassword.value =it
                        isErrorpassword = false

                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.enternewpass),
                            color = lablehint
                        )
                    },
                    isError = isErrorpassword,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

                    // label = { Text(text = "Password", color = Black) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(53.dp)
                        .clip(shape = Shapesaaldawaa.small)
                        .background(
                            color = labelcolor
                        ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),

                    shape = Shapesaaldawaa.small,
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordvisibilityforget.value = !passwordvisibilityforget.value
                        }) {

                            Icon(
                                painter = if (passwordvisibilityforget.value) painterResource(R.drawable.eye_1) else painterResource(
                                    R.drawable.eye_slash__5_
                                ),
                                contentDescription = "password",
                                //tint = if (passwordvisibilitysignup.value) Purple500 else Gray
                            )
                        }

                    },

                    visualTransformation = if (passwordvisibilityforget.value) VisualTransformation.None else PasswordVisualTransformation()

                )
                if (isErrorpassword) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .align(alignment = Alignment.Start)
                            .padding(start = 20.dp),
                        text = "password error empty or not match",
                        color = errormessege,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))


                OutlinedTextField(

                    value = forgetconfirmpassword.value,
                    onValueChange = {
                        forgetconfirmpassword.value = it
                        isErrorconfirmpassword = false
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.enternewpassagain),
                            color = lablehint
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

                    isError = isErrorconfirmpassword,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(53.dp)
                        .clip(shape = Shapesaaldawaa.small)
                        .background(
                            color = labelcolor
                        ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),

                    shape = Shapesaaldawaa.small,
                    trailingIcon = {
                        IconButton(onClick = {
                            confirmpasswordvisibilityforget.value =
                                !confirmpasswordvisibilityforget.value
                        }) {

                            Icon(
                                painter = if (confirmpasswordvisibilityforget.value) painterResource(R.drawable.eye_1) else painterResource(
                                    R.drawable.eye_slash__5_
                                ),
                                contentDescription = "password",
                                tint = if (confirmpasswordvisibilityforget.value) Purple500 else Color.Gray
                            )
                        }

                    },

                    visualTransformation = if (confirmpasswordvisibilityforget.value) VisualTransformation.None else PasswordVisualTransformation()

                )
                if (isErrorconfirmpassword) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .align(alignment = Alignment.Start)
                            .padding(start = 20.dp),
                        text = "empty confirm password",
                        color = errormessege,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 90.dp))

                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(51.dp)
                            .clip(shape = Shapesaaldawaa.small),
                        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                        shape = Shapesaaldawaa.small,
                        border = BorderStroke(2.dp, textcolor),

                        onClick = {
                            if (!validationHelper.confirmpasswordvalidation(forgetpassword.value,forgetconfirmpassword.value)){
                                isErrorconfirmpassword = true
                            }
                            if (!validationHelper.passwordlvalidation(forgetpassword.value)) {
                                isErrorpassword = true
                            }
                            if (!validationHelper.passwordlvalidation(forgetconfirmpassword.value)){
                                isErrorconfirmpassword = true
                            }

                            else {
                                 navController.navigate("Forget_Success")

                                Toast.makeText(context, "password changed ", Toast.LENGTH_SHORT)
                                    .show()
                            }

                        },


                        ) {
                        Text(
                            text = stringResource(id = R.string.forgetsend),
                            fontSize = 20.sp,
                            color = textcolor,
                        )


                    }
                }
            }
        }
    }



