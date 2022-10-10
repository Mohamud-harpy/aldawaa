@file:OptIn(ExperimentalMaterialApi::class)

package com.example.aldawaa.presentation.screen.Forgetpassword.component

import android.renderscript.Sampler.Value
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aldawaa.presentation.ui.theme.Shapes
import com.example.aldawaa.presentation.ui.theme.errormessege
import com.example.aldawaa.presentation.ui.theme.labelcolor
import com.example.aldawaa.presentation.ui.theme.textcolor

@Composable
fun OTPTextFields(
    modifier: Modifier = Modifier,
    length: Int,
    onFilled: (code: String) -> Unit
) {
    var code: List<Char> by remember { mutableStateOf(listOf()) }
    val otpvisibility = remember{ mutableStateOf(false)}
    val focusRequesters: List<FocusRequester> = remember {
        val temp = mutableListOf<FocusRequester>()
        repeat(length) {
            temp.add(FocusRequester())
        }
        temp
    }

    Row(

        modifier = Modifier.height(60.dp)
    ) {

        (0 until length).forEach { index ->
            OutlinedTextField(
                modifier = Modifier
                    .width(56.dp)
                    .imePadding()
                    .paddingFromBaseline(top = 15.dp)
                    .height(56.dp)
                    .clip(shape = Shapes.medium)
                    .background(color = labelcolor)
                    .focusRequester(focusRequester = focusRequesters[index]),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = textcolor,
                    unfocusedBorderColor = Color.Transparent,
                    errorLabelColor = errormessege,
                    errorBorderColor = errormessege,


                    ),

                textStyle = TextStyle.Default.copy(fontSize = 22.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold),
                shape = Shapes.medium,
                singleLine = true,

                value = code.getOrNull(index = index)?.takeIf {
                    it.isDigit()
                }?.toString() ?: "",
                onValueChange = { value: String ->
                    if (focusRequesters[index].freeFocus()) {
                        val temp = code.toMutableList()
                        if (value == "") {
                            if (temp.size > index) {
                                temp.removeAt(index = index)
                                code = temp
                                focusRequesters.getOrNull(index - 1)?.requestFocus()
                            }
                        } else {
                            if (code.size > index) {
                                temp[index] = value.getOrNull(0)?: ' '
                            } else {
                                temp.add(value.getOrNull(0) ?: ' ')
                                code = temp
                                focusRequesters.getOrNull(index + 1)?.requestFocus() ?: onFilled(
                                    code.joinToString(separator = "")
                                )
                            }
                        }
                    }

                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),

                visualTransformation = if (otpvisibility.value)   PasswordVisualTransformation() else VisualTransformation.None,
                )


            Spacer(modifier = Modifier.width(15.dp))
        }
    }
}