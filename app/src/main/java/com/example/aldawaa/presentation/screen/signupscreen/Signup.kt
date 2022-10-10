
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
import androidx.compose.ui.graphics.Color.Companion.Transparent
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
import com.example.aldawaa.presentation.screen.loginscreen.component.LoginFormEvent
import com.example.aldawaa.presentation.screen.signupscreen.component.SignupFormEvent
import com.example.aldawaa.presentation.ui.theme.*
import com.example.aldawaa.utils.ValidationHelper
import java.util.*

// signup box
@Composable
fun Signup(navController: NavController) {
    val validationHelper : ValidationHelper = ValidationHelper()
    var expandedtitle by remember { mutableStateOf(false) }
    var expandedgender by remember { mutableStateOf(false) }
    val gender = listOf(stringResource(id = R.string.male).toString(), stringResource(id = R.string.female).toString())
    val title = listOf(stringResource(id = R.string.title1).toString(), stringResource(id = R.string.title2).toString())
    var chosentitle by remember { mutableStateOf(title[0]) }
    var chosengender by remember { mutableStateOf(gender[0]) }
    val checkboxsubscribe = rememberSaveable { mutableStateOf(false) }
    val passwordvisibilitysignup = remember { mutableStateOf(false) }
    val confirmpasswordvisibilitysignup = remember { mutableStateOf(false) }
    val signupmyBirthDate = rememberSaveable { mutableStateOf("") }
    val signuname = rememberSaveable { mutableStateOf("") }
    val signupphonenum = rememberSaveable { mutableStateOf("") }
    val signupemail = rememberSaveable { mutableStateOf("") }
    val signuppassword = remember { mutableStateOf("") }
    val signupconfirmpassword = remember { mutableStateOf("") }

    var isErrorsignupname by rememberSaveable { mutableStateOf(false) }
  //  var isErrorsignuptitle by rememberSaveable { mutableStateOf(false) }
   // var isErrorsignupgender by rememberSaveable { mutableStateOf(false) }
    var isErrorsignupemail by rememberSaveable { mutableStateOf(false) }
    var isErrorsignupphone by rememberSaveable { mutableStateOf(false) }
    var isErrorsignupbirth by rememberSaveable { mutableStateOf(false) }
    var isErrorsignuppass by rememberSaveable { mutableStateOf(false) }
    var isErrorsignupconfirmpass by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

   /* val signupViewModel : SignupViewModel = hiltViewModel()
    val state = signupViewModel.state

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
*/

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.background(Color.Transparent)
    ) {

        Spacer(modifier = Modifier.padding(15.dp))


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Transparent)
        ) {

            OutlinedTextField(

                value = signuname.value,
                onValueChange = {
                    signuname.value = it
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
                keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Text),

                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clip(shape = Shapesaaldawaa.small)
                    .height(53.dp)
                    .background(color = labelcolor),

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
                    text = "name error or empty",
                    color = errormessege,
                    fontSize = 12.sp
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
                        Text(chosentitle, color = textcolor)
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            tint = textcolor,
                            contentDescription = null,
                            modifier = Modifier.padding(start = 15.dp)

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
                                        title[1].toString() -> {
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
                        Text(chosengender, color = textcolor)
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
                                        gender[0] -> {
                                            chosengender = label


                                        }
                                        gender[1] -> {
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

                value = signupemail.value,
                onValueChange = {
                        signupemail.value= it
                    isErrorsignupemail= false

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
                    .height(53.dp)
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
                    text = "email error or empty",
                    color = errormessege,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))


            OutlinedTextField(

                value = signupphonenum.value,
                onValueChange = {
                    signupphonenum.value= it
                    isErrorsignupphone =false

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
                    .height(53.dp)
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
                    text = "phonenumber error or Empty",
                    color = errormessege,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))

            OutlinedTextField(

                value = signupmyBirthDate.value,
                onValueChange = {
                    signupmyBirthDate.value= it
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
                                signupmyBirthDate.value = "$mDayOfMonth - ${mMonth + 1} - $mYear"
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
                    fontSize = 12.sp
                )
            }


            Spacer(modifier = Modifier.padding(10.dp))


            OutlinedTextField(

                value = signuppassword.value,
                onValueChange = {
                    signuppassword.value =it
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

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),


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
                    text = "password error empty or not match",
                    color = errormessege,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))


            OutlinedTextField(

                value = signupconfirmpassword.value,
                onValueChange = {
                    signupconfirmpassword.value = it
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),


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
                    fontSize = 12.sp
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
                    .height(53.dp),
                colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                shape = Shapesaaldawaa.small,
                border = BorderStroke(2.dp, textcolor),
                onClick = {

                     if ( !validationHelper.emptyvalidation(signuname.value))  {
                            isErrorsignupname = true
                        }
                     if (!validationHelper.emailvalidation(signupemail.value)) {
                            isErrorsignupemail = true
                        }
                     if (!validationHelper.phonenumvalidation(signupphonenum.value)) {
                            isErrorsignupphone = true
                        }
                     if (!validationHelper.emptyvalidation(signupmyBirthDate.value)){
                            isErrorsignupbirth = true
                        }
                     if (!validationHelper.passwordlvalidation(signuppassword.value)){
                            isErrorsignuppass = true
                        }
                     if (!validationHelper.passwordlvalidation(signupconfirmpassword.value)) {
                            isErrorsignupconfirmpass = true
                        }
                    if (!validationHelper.confirmpasswordvalidation(signuppassword.value,signupconfirmpassword.value)) {
                        isErrorsignupconfirmpass = true
                        isErrorsignuppass=true
                    }
                     else {
                            //navController.navigate("home")
                            Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show()
                        }


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
}
