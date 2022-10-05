/*
package com.example.aldawaa.presentation.screen.signupscreen

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.blue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.aldawaa.R
import com.example.aldawaa.presentation.screen.loginscreen.LoginViewModel
import com.example.aldawaa.presentation.ui.theme.*
import java.util.*

// signup box
@Composable
fun Signup(navController: NavController) {
    var expandedtitle by remember { mutableStateOf(false) }
    var expandedgender by remember { mutableStateOf(false) }
    var chosentitle by remember { mutableStateOf("Title") }
    var chosengender by remember { mutableStateOf("Gender") }
    val gender = listOf(stringResource(id = R.string.male), stringResource(id = R.string.female))
    val title = listOf(stringResource(id = R.string.title1), stringResource(id = R.string.title2))
    val checkboxsubscribe = remember { mutableStateOf(false) }
    val passwordvisibilitysignup = remember { mutableStateOf(false) }
    val confirmpasswordvisibilitysignup = remember { mutableStateOf(false) }
*/
/*

    val myBirthDate = remember { mutableStateOf("") }
    val name = rememberSaveable { mutableStateOf("") }
    val phonenum = remember { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val passwordsignup = remember { mutableStateOf("") }
    var isErrorsignupname by rememberSaveable { mutableStateOf(false) }
    var isErrorsignuptitle by rememberSaveable { mutableStateOf(false) }
    var isErrorsignupgender by rememberSaveable { mutableStateOf(false) }
    var isErrorsignupemail by rememberSaveable { mutableStateOf(false) }
    var isErrorsignupphone by rememberSaveable { mutableStateOf(false) }
    var isErrorsignupbirth by rememberSaveable { mutableStateOf(false) }
    var isErrorsignuppass by rememberSaveable { mutableStateOf(false) }
    var isErrorsignupconfirmpass by rememberSaveable { mutableStateOf(false) }
*//*

    val signupViewModel : SignupViewModel = hiltViewModel()
    val state = signupViewModel.state
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        signupViewModel.validationEvents.collect { event ->
            when (event) {
                is SignupViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Signup successful",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.background(Color.Transparent)
    ) {

        Spacer(modifier = Modifier.padding(15.dp))


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.Transparent)
        ) {

            OutlinedTextField(

                value = name.value,
                onValueChange = {
                    name.value = it
                    isErrorsignupname = false
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.name),
                        color = lablehint
                    )
                },
                isError = isErrorsignupname,
                //  keyboardActions = KeyboardActions { validate(name.value) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clip(shape = Shapesaaldawaa.small)
                    .height(51.dp)
                    .background(color = labelcolor),
                */
/*.semantics {
                // Provide localized description of the error
                if (isErrorsignupname) error(name.value)
            }*//*

                shape = Shapesaaldawaa.small,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    errorBorderColor = Color.Red,
                ),
            )
            //Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Start) {
            if (isErrorsignupname) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .align(alignment = Alignment.Start)
                        .padding(start = 20.dp),
                    text = "empty name",
                    color = errormessege,
                )
            }//}

            Spacer(modifier = Modifier.padding(10.dp))



            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {

                Column() {


                    OutlinedButton(

                        onClick = { expandedtitle = !expandedtitle },
                        colors = ButtonDefaults.outlinedButtonColors(titlegender),
                        shape = Shapesaaldawaa.small,
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .clip(shape = Shapesaaldawaa.small)
                    ) {
                        Text(stringResource(id = R.string.title), color = textcolor)
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            tint = textcolor,
                            contentDescription = null,

                            )
                    }
                    DropdownMenu(
                        expanded = expandedtitle,
                        onDismissRequest = { expandedtitle = false },
                        modifier = Modifier.background(color = Color.Transparent),


                        ) {
                        title.forEach { label ->
                            DropdownMenuItem(
                                modifier = Modifier.background(Color.Transparent),
                                onClick = {
                                    expandedtitle = false

                                    when (label) {
                                        title[0] -> {
                                            chosentitle = label

                                        }
                                        title[1] -> {
                                            chosentitle = label

                                        }
                                    }
                                }) {
                                Text(text = label, color = textcolor)
                            }
                        }
                    }
                }

                Column() {

                    OutlinedButton(
                        onClick = { expandedgender = !expandedgender },
                        colors = ButtonDefaults.outlinedButtonColors(titlegender),
                        shape = Shapesaaldawaa.small,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .clip(shape = Shapesaaldawaa.small)

                    ) {
                        Text(text = stringResource(id = R.string.gender), color = textcolor)
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            tint = textcolor,
                            contentDescription = null,

                            )
                    }
                    DropdownMenu(
                        expanded = expandedgender,
                        onDismissRequest = { expandedgender = false },
                        modifier = Modifier.background(color = Color.Transparent),


                        ) {
                        gender.forEach { label ->
                            DropdownMenuItem(
                                modifier = Modifier.background(Color.Transparent),
                                onClick = {
                                    expandedgender = false

                                    when (label) {
                                        "Male" -> {
                                            chosengender = label


                                        }
                                        "Female" -> {
                                            chosengender = label

                                        }
                                    }
                                }) {
                                Text(text = label, color = textcolor)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            OutlinedTextField(

                value = email.value,
                onValueChange = {
                    email.value = it
                    isErrorsignupemail = false
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.enteryouremail),
                        color = lablehint
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = isErrorsignupemail,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clip(shape = Shapesaaldawaa.small)
                    .height(49.dp)
                    .background(color = labelcolor),
                textStyle = TextStyle(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                shape = Shapesaaldawaa.small
            )
            if (isErrorsignupemail) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .align(alignment = Alignment.Start)
                        .padding(start = 20.dp),
                    text = "email error",
                    color = errormessege,
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))


            OutlinedTextField(

                value = phonenum.value,
                onValueChange = {
                    phonenum.value = it
                    isErrorsignupphone = false
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.phonenumber),
                        color = lablehint
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                isError = isErrorsignupphone,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clip(shape = Shapesaaldawaa.small)
                    .height(49.dp)
                    .background(color = labelcolor),
                textStyle = TextStyle(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                shape = Shapesaaldawaa.small
            )
            if (isErrorsignupphone) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .align(alignment = Alignment.Start)
                        .padding(start = 20.dp),
                    text = "empty phone",
                    color = errormessege,
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))

            OutlinedTextField(

                value = myBirthDate.value,
                onValueChange = {
                    myBirthDate.value = it
                    isErrorsignupbirth = false
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.birthdate),
                        color = lablehint
                    )
                },
                isError = isErrorsignupbirth,
                // label = { Text(text = "Password", color = Black) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(49.dp)
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


                        val mYear: Int
                        val mMonth: Int
                        val mDay: Int

                        val mCalendar = Calendar.getInstance()

                        mYear = mCalendar.get(Calendar.YEAR)
                        mMonth = mCalendar.get(Calendar.MONTH)
                        mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
                        val style = mCalendar.get(Calendar.ALL_STYLES.blue)

                        mCalendar.time = Date()
                        val mDatePickerDialog = DatePickerDialog(
                            context,
                            R.style.calendertheme,
                            { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                                myBirthDate.value = "$mDayOfMonth - ${mMonth + 1} - $mYear"
                            }, mYear, mMonth, mDay
                        )
                        mDatePickerDialog.show()
                    }) {

                        Icon(
                            painter = painterResource(R.drawable.calendar_icon),
                            contentDescription = "birthday",
                            //tint = if (passwordvisibilitysignup.value) Purple500 else Gray
                        )
                    }

                },

                )
            if (isErrorsignupbirth) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .align(alignment = Alignment.Start)
                        .padding(start = 20.dp),
                    text = "birthday is empty",
                    color = errormessege,
                )
            }


            Spacer(modifier = Modifier.padding(10.dp))


            OutlinedTextField(

                value = passwordsignup.value,
                onValueChange = {
                    passwordsignup.value = it
                    isErrorsignuppass = false
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.enteryourpass),
                        color = lablehint
                    )
                },
                isError = isErrorsignuppass,
                // label = { Text(text = "Password", color = Black) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(49.dp)
                    .clip(shape = Shapesaaldawaa.small)
                    .background(
                        color = labelcolor
                    ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),

                shape = Shapesaaldawaa.small,
                */
/*colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Transparent,
                    textColor = Black
                ),*//*

                trailingIcon = {
                    IconButton(onClick = {
                        passwordvisibilitysignup.value = !passwordvisibilitysignup.value
                    }) {

                        Icon(
                            painter = if (passwordvisibilitysignup.value) painterResource(R.drawable.eye_1) else painterResource(
                                R.drawable.eye_slash__5_
                            ),
                            contentDescription = "password",
                            //tint = if (passwordvisibilitysignup.value) Purple500 else Gray
                        )
                    }

                },
                visualTransformation = if (passwordvisibilitysignup.value) VisualTransformation.None else PasswordVisualTransformation()

            )
            if (isErrorsignuppass) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .align(alignment = Alignment.Start)
                        .padding(start = 20.dp),
                    text = "password empty",
                    color = errormessege,
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))


            OutlinedTextField(

                value = confirmpassword.value,
                onValueChange = {
                    confirmpassword.value = it
                    isErrorsignupconfirmpass = false
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.confirmpassword),
                        color = lablehint
                    )
                },
                isError = isErrorsignupconfirmpass,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(49.dp)
                    .clip(shape = Shapesaaldawaa.small)
                    .background(
                        color = labelcolor
                    ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),

                shape = Shapesaaldawaa.small,
                */
/*colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Transparent,
                    textColor = Black
                ),*//*


                trailingIcon = {
                    IconButton(onClick = {
                        confirmpasswordvisibilitysignup.value =
                            !confirmpasswordvisibilitysignup.value
                    }) {

                        Icon(
                            painter = if (confirmpasswordvisibilitysignup.value) painterResource(R.drawable.eye_1) else painterResource(
                                R.drawable.eye_slash__5_
                            ),
                            contentDescription = "password",
                            tint = if (confirmpasswordvisibilitysignup.value) Purple500 else Color.Gray
                        )
                    }

                },
                visualTransformation = if (confirmpasswordvisibilitysignup.value) VisualTransformation.None else PasswordVisualTransformation()

            )
            if (isErrorsignupconfirmpass) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .align(alignment = Alignment.Start)
                        .padding(start = 20.dp),
                    text = "empty confirm password",
                    color = errormessege,
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Checkbox(
                    checked = checkboxsubscribe.value,
                    onCheckedChange = {
                        checkboxsubscribe.value = it

                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = checkboxcolor,
                        checkmarkColor = checkedmarkcolor,
                        uncheckedColor = textcolor
                    )

                )
                Text(text = stringResource(id = R.string.Subscribeto), color = subscribetextcolor)
            }

            Spacer(modifier = Modifier.padding(10.dp))

            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clip(shape = Shapesaaldawaa.small)
                    .height(49.dp),
                colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                shape = Shapesaaldawaa.small,
                border = BorderStroke(2.dp, textcolor),
                onClick = {
                    when {
                        name.value.isEmpty() -> {
                            isErrorsignupname = true
                        }
                        email.value.isEmpty() -> {
                            isErrorsignupemail = true
                        }
                        phonenum.value.isEmpty() -> {
                            isErrorsignupphone = true
                        }
                        myBirthDate.value.isEmpty() -> {
                            isErrorsignupbirth = true
                        }
                        passwordsignup.value.isEmpty() -> {
                            isErrorsignuppass = true
                        }
                        confirmpassword.value.isEmpty() -> {
                            isErrorsignupconfirmpass = true
                        }
                        */
/*email.value.isEmpty() -> {
                        isErrorsignupname = true
                    }
                    *//*

                        else ->
                            navController.navigate("home")
                    }
                    Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show()

                },


                ) {
                Text(
                    text = stringResource(id = R.string.signuporg),
                    fontSize = 20.sp,
                    color = textcolor,
                )

            }
        }
    }
}*/
