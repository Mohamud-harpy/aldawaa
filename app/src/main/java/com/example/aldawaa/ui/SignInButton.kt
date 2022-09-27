package com.example.aldawaa.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aldawaa.R


@ExperimentalMaterialApi
@Composable
fun SignInButton(
    icon: Painter,
    isLoading: Boolean = false,
    progressIndicatorColor: Color = MaterialTheme.colors.primary,
    onClick: () -> Unit
) {

            Icon(
                painter = icon,
                contentDescription = "SignInButton",
                modifier = Modifier
                    .width(42.dp)
                    .height(43.dp)
                    .background(Color.White)
                    .padding(5.dp)
                    .clip(
                        CircleShape
                    ).clickable(
                    enabled = !isLoading,
                    onClick = onClick
                ),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    color = progressIndicatorColor
                )
            }
        }



@ExperimentalMaterialApi
@Composable
@Preview
fun SignInButtonPreview() {
    SignInButton(
        isLoading = false,
        icon = painterResource(id = R.drawable.google),
        onClick = { }
    )
}