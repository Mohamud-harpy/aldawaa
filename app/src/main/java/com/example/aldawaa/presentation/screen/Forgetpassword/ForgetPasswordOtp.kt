@file:Suppress("UNUSED_EXPRESSION", "KotlinConstantConditions")

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aldawaa.R
import com.example.aldawaa.presentation.screen.Forgetpassword.component.OTPTextFields
import com.example.aldawaa.presentation.ui.theme.*
import com.example.aldawaa.utils.ValidationHelper

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ForgetPasswordOtp (navController: NavController) {

    val validationHelper: ValidationHelper = ValidationHelper()

    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    var isErrorOtp by rememberSaveable { mutableStateOf(false) }
    val otp = rememberSaveable { mutableStateOf("") }
    var otpVal: String? = null

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
                    .fillMaxSize()
                    .fillMaxWidth()
                    .clip(
                        shape = Shapes.large.copy(
                            bottomStart = ZeroCornerSize,
                            bottomEnd = ZeroCornerSize
                        )
                    )
                    .paint(
                        painterResource(id = R.drawable.group),
                        contentScale = ContentScale.FillBounds
                    )


            ) {
                Spacer(modifier = Modifier.padding(15.dp))


                OTPTextFields(length = 4) { getOpt ->
                    otpVal=getOpt

                }

                Spacer(modifier = Modifier.padding(20.dp))


                Text(
                    text = stringResource(id = R.string.forgetdontrec),
                    color = textcolor,
                    fontSize = 16.sp

                )
                Spacer(modifier = Modifier.padding(5.dp))


                Text(
                    text = stringResource(id = R.string.recendcode),
                    color = textcolor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp

                    )



                Spacer(modifier = Modifier.padding(70.dp))


                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(53.dp)
                        .clip(shape = Shapesaaldawaa.small)
                        .align(alignment = Alignment.CenterHorizontally)

                    ,
                    colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                    shape = Shapesaaldawaa.small,
                    border = BorderStroke(2.dp, textcolor),

                    onClick = {

                        if (otpVal.toString() == "1234") {
                            Toast.makeText(context, "Verefication done  ", Toast.LENGTH_SHORT)
                                .show()
                            navController.navigate("Forget_Change_Password")
                        }
                        /* else {
                        navController.navigate("otp")

                        Toast.makeText(context, "code sent ", Toast.LENGTH_SHORT).show()
                    }*/
                    },


                    ) {
                    Text(
                        text = stringResource(id = R.string.forgetsend),
                        fontSize = 20.sp,
                        color = textcolor,
                    )
                    Spacer(modifier = Modifier.padding(15.dp))

                }

            }
        }
    }

}

