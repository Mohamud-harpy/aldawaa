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
import com.example.aldawaa.presentation.ui.theme.*
import com.example.aldawaa.utils.ValidationHelper

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ForgetSuccess (navController: NavController) {



    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()


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
                .background(color = Color.White)
                .verticalScroll(rememberScrollState())

        ) {

            Spacer(modifier = Modifier.padding(60.dp))

            Image(
                modifier = Modifier
                    .width(105.dp)
                    .height(60.dp)
                   ,

                painter = painterResource(R.drawable.group_5),
                contentDescription = "login image",
            )

            Spacer(modifier = Modifier.padding(50.dp))

            Image(
                modifier = Modifier.wrapContentSize()
                    .width(55.dp)
                    .height(50.dp)
                    ,

                painter = painterResource(R.drawable.groupcheckmark),
                contentDescription = "login image",
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Text(text = stringResource(id = R.string.yourpasswordchanged),
                color = textcolor,
                modifier = Modifier,
                fontSize = 18.sp


                )
            Spacer(modifier = Modifier.padding(5.dp))


            Text(text = stringResource(id = R.string.yourpasswordchanged2),
                color = textcolor,
                modifier = Modifier,
                fontSize = 18.sp


            )



            Spacer(modifier = Modifier.padding(80.dp))




                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(53.dp)
                            .clip(shape = Shapesaaldawaa.small),
                        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                        shape = Shapesaaldawaa.small,
                        border = BorderStroke(2.dp, textcolor),

                        onClick = {



                                  //  navController.navigate("Forget_Password_Otp")

                                    Toast.makeText(context, "Backed to  Home ", Toast.LENGTH_SHORT).show()


                        },


                        ) {
                        Text(
                            text = stringResource(id = R.string.backtohome),
                            fontSize = 20.sp,
                            color = textcolor,
                        )


                    }
                }
            }
        }




