@file:Suppress("DEPRECATION")
@file:OptIn(ExperimentalFoundationApi::class, DelicateCoroutinesApi::class)

package com.example.aldawaa.view


import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.app.Dialog
import android.graphics.Color.parseColor
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.akexorcist.localizationactivity.core.LanguageSetting.setLanguage
import com.example.aldawaa.FacebookUtil
import com.example.aldawaa.R
import com.example.aldawaa.TwitterConstants
import com.example.aldawaa.TwitterWebViewClient
import com.example.aldawaa.ui.AuthScreen
import com.example.aldawaa.ui.SignInButton
import com.example.aldawaa.ui.SignInButtonPreview
import com.example.aldawaa.ui.theme.Purple500
import com.example.aldawaa.ui.theme.Shapes
import com.example.aldawaa.viewmodel.AuthViewModel
import com.facebook.*
import com.facebook.Profile.Companion.fetchProfileForCurrentAccessToken
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import kotlinx.coroutines.*
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder
import java.util.*


@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("ResourceType")
@Composable
fun Loginpage(navController: NavController) {
    
    val authViewModel: AuthViewModel = hiltViewModel()
    val mail= Text(text = stringResource(id = R.string.enteryouremail), color = Black)
    val pass= Text(text = stringResource(id = R.string.enteryourpass), color = Black)
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("English", "Arabic")
    var chosen by remember { mutableStateOf("English")  }
    val name = remember { mutableStateOf("") }
    val phonenum = remember { mutableStateOf("") }
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordvisibility = remember { mutableStateOf(false) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .background(color = "#002444".color)

        ) {


                Image(
                    modifier = Modifier
                        .width(100.dp)
                        .height(80.dp)
                        .background(color = "#002444".color),

                    painter = painterResource(R.drawable.ic_aldawaaa),
                    contentDescription = "login image",
                    contentScale = ContentScale.Crop,
                )

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = stringResource(id = R.string.wellcometoaldawaa),
                fontSize = 25.sp,
                color = White,
                fontWeight = FontWeight.Bold,
            )
            Box(modifier = Modifier.background(color = Transparent)) {

                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Transparent),
                    onClick = { expanded = !expanded }, modifier = Modifier.background(
                    color = Transparent)){
                    Text (chosen, color = White)
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        tint = White,
                        contentDescription = null,

                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(color = Transparent),
                    


                ) {
                    Color.DarkGray
                    suggestions.forEach { label ->
                        DropdownMenuItem(modifier = Modifier.background(Color.Transparent),onClick = {
                            expanded = true

                            when(label) {
                                   "English" -> {
                                       chosen =label
                                       setLanguage(context,Locale("en"))
                                (context as? Activity)?.recreate()

                       }
                                  "Arabic" -> {
                                      chosen =label
                                     setLanguage(context, Locale("ar"))
                                     (context as? Activity)?.recreate()

                                 }
                        }
                        }) {
                            Text(text = label)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.padding(5.dp))


            Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,

                    modifier = Modifier
                        .fillMaxSize()
                        .clip(
                            shape = RoundedCornerShape(50.dp).copy(
                                bottomStart = ZeroCornerSize,
                                bottomEnd = ZeroCornerSize
                            )
                        )
                        .background(color = White)
                        .padding(5.dp)
                        .paint(painterResource(id = R.drawable.background))


                ) {

                var selectedIndex by remember { mutableStateOf(0) }

                val list = listOf(stringResource(id = R.string.signintap),stringResource(id = R.string.signuptap))

                TabRow(selectedTabIndex = selectedIndex,
                    backgroundColor = Transparent,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .clip(RoundedCornerShape(50))
                        .padding(1.dp),
                    indicator = { tabPositions: List<TabPosition> ->

                     //   Box {                        }
                    }
                ) {
                    list.forEachIndexed { index, text ->
                        val selected = selectedIndex == index
                        Tab(
                            modifier = if (selected) Modifier
                                .clip(RoundedCornerShape(50))
                                .background(
                                    "#FFE7D619".color,
                                )
                            else Modifier
                                .clip(RoundedCornerShape(50))
                                .background(
                                    Color.Transparent
                                ),
                            selected = selected,
                            onClick = { selectedIndex = index},
                            text = { Text(text = text, color = "#002444".color) }
                        )

                    }


                }
                Spacer(modifier = Modifier.padding(5.dp))

                when(selectedIndex){
                    0 -> {

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.background(Transparent)
                        ) {
                            TextField(

                                value = email.value,
                                onValueChange = { email.value = it },
                                placeholder = { Text(text = stringResource(id = R.string.enteryouremail), color = Black) },

                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .clip(shape = RoundedCornerShape(40.dp))
                                    .height(49.dp)
                                    .background(color = "#FEFFFF".color),
                                textStyle = TextStyle(),

                                /*  colors = TextFieldDefaults.outlinedTextFieldColors(
                                    unfocusedBorderColor = Transparent,
                                    textColor = Black,

                                )*/

                            )

                            Spacer(modifier = Modifier.padding(5.dp))


                            TextField(

                                value = password.value,
                                onValueChange = { password.value = it },
                                placeholder = { Text(text = stringResource(id = R.string.enteryourpass), color = Black) },
                                // label = { Text(text = "Password", color = Black) },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .height(49.dp)
                                    .clip(shape = RoundedCornerShape(40.dp))
                                    .background(
                                        color = "#FEFFFF".color
                                    ),

                                /*colors = TextFieldDefaults.outlinedTextFieldColors(
                                    unfocusedBorderColor = Transparent,
                                    textColor = Black
                                ),*/
                                trailingIcon = {
                                    IconButton(onClick = {
                                        passwordvisibility.value = !passwordvisibility.value
                                    }) {

                                        Icon(
                                            painter = painterResource(R.drawable.ic_baseline_remove_red_eye_24),
                                            contentDescription = "password",
                                            tint = if (passwordvisibility.value) Purple500 else Gray
                                        )
                                    }

                                },
                                visualTransformation = if (passwordvisibility.value) VisualTransformation.None else PasswordVisualTransformation()

                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            
                            Row() {
                               // Checkbox(checked = , onCheckedChange =  )
                               // Text(text = stringResource(id = R.string.forgetmypassword), posit)
                                
                            }

                            OutlinedButton(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .clip(shape = RoundedCornerShape(50.dp))
                                    .height(49.dp)
                                    .background(color = Transparent, shape = Shapes.small),
                                colors = ButtonDefaults.outlinedButtonColors(Transparent),

                                onClick = {
                                    when {
                                        email.value.isEmpty() -> {
                                            Toast.makeText(
                                                context,
                                                "$mail",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        password.value.isEmpty() -> {
                                            Toast.makeText(
                                                context,
                                                "$pass",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        else ->
                                            navController.navigate("home")
                                    }
                                },


                                ) {
                                Text(
                                    text = stringResource(id = R.string.signinorg),
                                    fontSize = 20.sp,
                                    color = "#002444".color,
                                )

                            }
                            Spacer(modifier = Modifier.padding(7.dp))


                        }

                    }

                    1 -> {


                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.background(Transparent)
                        ) {

                            TextField(

                                value = name.value,
                                onValueChange = { name.value = it },
                                placeholder = { Text(text = stringResource(id = R.string.name), color = Black) },

                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .clip(shape = RoundedCornerShape(40.dp))
                                    .height(49.dp)
                                    .background(color = "#FEFFFF".color),
                                textStyle = TextStyle(),

                                /*  colors = TextFieldDefaults.outlinedTextFieldColors(
                                    unfocusedBorderColor = Transparent,
                                    textColor = Black,

                                )*/

                            )
                            Spacer(modifier = Modifier.padding(5.dp))


                            TextField(

                                value = email.value,
                                onValueChange = { email.value = it },
                                placeholder = { Text(text = stringResource(id = R.string.enteryouremail), color = Black) },

                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .clip(shape = RoundedCornerShape(40.dp))
                                    .height(49.dp)
                                    .background(color = "#FEFFFF".color),
                                textStyle = TextStyle(),

                                /*  colors = TextFieldDefaults.outlinedTextFieldColors(
                                    unfocusedBorderColor = Transparent,
                                    textColor = Black,

                                )*/

                            )
                            Spacer(modifier = Modifier.padding(5.dp))


                            TextField(

                                value = phonenum.value,
                                onValueChange = { phonenum.value = it },
                                placeholder = { Text(text = stringResource(id = R.string.phonenumber), color = Black) },

                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .clip(shape = RoundedCornerShape(40.dp))
                                    .height(49.dp)
                                    .background(color = "#FEFFFF".color),
                                textStyle = TextStyle(),

                                /*  colors = TextFieldDefaults.outlinedTextFieldColors(
                                    unfocusedBorderColor = Transparent,
                                    textColor = Black,

                                )*/

                            )

                            Spacer(modifier = Modifier.padding(5.dp))


                            TextField(

                                value = password.value,
                                onValueChange = { password.value = it },
                                placeholder = { Text(text = stringResource(id = R.string.enteryourpass), color = Black) },
                                // label = { Text(text = "Password", color = Black) },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .height(49.dp)
                                    .clip(shape = RoundedCornerShape(40.dp))
                                    .background(
                                        color = "#FEFFFF".color
                                    ),

                                /*colors = TextFieldDefaults.outlinedTextFieldColors(
                                    unfocusedBorderColor = Transparent,
                                    textColor = Black
                                ),*/
                                trailingIcon = {
                                    IconButton(onClick = {
                                        passwordvisibility.value = !passwordvisibility.value
                                    }) {

                                        Icon(
                                            painter = painterResource(R.drawable.ic_baseline_remove_red_eye_24),
                                            contentDescription = "password",
                                            tint = if (passwordvisibility.value) Purple500 else Gray
                                        )
                                    }

                                },
                                visualTransformation = if (passwordvisibility.value) VisualTransformation.None else PasswordVisualTransformation()

                            )
                            Spacer(modifier = Modifier.padding(10.dp))

                            OutlinedButton(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .clip(shape = RoundedCornerShape(50.dp))
                                    .height(49.dp)
                                    .background(color = Transparent, shape = Shapes.small),
                                colors = ButtonDefaults.outlinedButtonColors(Transparent),

                                onClick = {
                                    when {
                                        email.value.isEmpty() -> {
                                            Toast.makeText(
                                                context,
                                                "$mail",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        password.value.isEmpty() -> {
                                            Toast.makeText(
                                                context,
                                                "$pass",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        else ->
                                            navController.navigate("home")
                                    }
                                },


                                ) {
                                Text(
                                    text = stringResource(id = R.string.signuporg),
                                    fontSize = 20.sp,
                                    color = "#002444".color,
                                )

                            }
                            Spacer(modifier = Modifier.padding(7.dp))


                        }
                    }
                }



                /*   Row(
                       verticalAlignment = Alignment.CenterVertically,
                       modifier = Modifier
                           .padding(horizontal = 16.dp, vertical = 16.dp)
                           .clip(Shapes.medium)
                           .background(Transparent)
                           .fillMaxWidth()
                           .height(44.dp)
                   ) {

                       TabButton("Login", true, Modifier.weight(1f))
                       Spacer(modifier = Modifier.padding(5.dp))
                       TabButton("Sign up", false, Modifier.weight(1f))
                       *//* Button(
                           colors = ButtonDefaults.buttonColors(backgroundColor = White),
                           onClick = {
                               when {
                                   email.value.isEmpty() -> {
                                       Toast.makeText(
                                           context,
                                           "Please Enter Your Email ",
                                           Toast.LENGTH_SHORT
                                       ).show()
                                   }
                                   password.value.isEmpty() -> {
                                       Toast.makeText(
                                           context,
                                           "Please Enter Your Password ",
                                           Toast.LENGTH_SHORT
                                       ).show()
                                   }
                                   else ->
                                       navController.navigate("home")
                               }
                           },
                           modifier = Modifier
                               .fillMaxWidth(0.8f)
                               .height(50.dp)
                       ) {
                           Text(
                               text = "Sign In",
                               fontSize = 20.sp,
                               color = Black,
                           )

                       }*//*

                }*/
                Spacer(modifier = Modifier.padding(5.dp))


                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {


                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    modifier = Modifier.background(Transparent)) {


                        Text(
                            text = stringResource(id = R.string.orsigninwith),
                            fontSize = 15.sp,
                            color = "#002444".color,
                        )
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center) {

                          /*  //facebook

                          val callbackManager = FacebookUtil.callbackManager

                            AndroidView(
                                    modifier = Modifier
                                        .width(32.dp)
                                        .height(35.dp)
                                        .clip(
                                            CircleShape
                                        ),
                                    factory = ::LoginButton,
                                    update = { button ->
                                        button.setPermissions("email")
                                       button.setPermissions( listOf("public_profile"))
                                        button.isEnabled = true
                                             button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                                                 override fun onSuccess(result: LoginResult) {

                                             *//*   Log.e("facebook : ","${result.authenticationToken!!.claims.name}")
                                                Log.e("facebook : ","${result.authenticationToken!!.claims.email}")*//*


                                                Log.e("facebook : ", result.accessToken.token)
                                                Log.e("facebook : ",result.accessToken.applicationId)
                                                Log.e("facebook : ", result.accessToken.userId)
                                                Log.e("facebook result : ","${result.authenticationToken?.claims?.name}")
                                                Log.e("facebook result : ","${result.authenticationToken?.claims}")

                                                     Toast.makeText(context, "you are logged in ${result.accessToken}", Toast.LENGTH_SHORT).show()

                                            }

                                            override fun onCancel() {

                                                Log.e("on cancel :"," On Cancel")
                                                Toast.makeText(context, "on cancel", Toast.LENGTH_SHORT).show()

                                            }

                                            override fun onError(error: FacebookException) {

                                                Log.e("on error :"," ${error?.localizedMessage}")
                                                Toast.makeText(context, "on cancel", Toast.LENGTH_SHORT).show()

                                            }
                                        })
                                    }
                                )*/

                            //facebook

                            CustomFacebookButton(
                                onSuccess = {},
                                onCancel = {},
                                onError = {}
                            )


                             Spacer(modifier = Modifier.padding(6.dp))
                            //Google
                            GoogleButton(

                                onClicked = {
                                }
                            )
                            Spacer(modifier = Modifier.padding(6.dp))

                            AuthScreen(authViewModel)


                            }


                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(text = stringResource(id = R.string.continueasaguest),
                            color = "#002444".color,
                            modifier = Modifier.clickable {
                                // navController.navigate("")
                                Toast.makeText(context, "Wellcome to Home ", Toast.LENGTH_SHORT).show()


                            })
                        Spacer(modifier = Modifier.padding(10.dp))


                    }

                }

            }

        }



val String.color
 get() = Color(parseColor(this))



/*
@Composable
fun TabButton(text: String, active: Boolean, modifier: Modifier) {
    Button(
        onClick = {







                  *//*TODO*//* },
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.fillMaxHeight(),
        elevation = null,
        colors = if (active) ButtonDefaults.buttonColors(
            backgroundColor = "#fec201".color,
            contentColor = "#002444".color
        )
        else ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = "#002444".color
        )
    ) {
        Text(text)
    }
}*/





//Facebook
@Composable
fun CustomFacebookButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    //icon: Int,
    onSuccess: (LoginResult) -> Unit,
    onCancel: () -> Unit,
    onError: (FacebookException?) -> Unit,
) {

    val callbackManager = FacebookUtil.callbackManager

    AndroidView(
        modifier = Modifier
            .width(42.dp)
            .height(45.dp).background(White)
            .padding(5.dp)
            .clip(
                CircleShape
            ),
        factory = :: LoginButton,
        update = { button ->
            button.setPermissions("email")
            button.setReadPermissions(mutableListOf("public_profile"));
            button.isEnabled = enabled

            button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    onSuccess(result)
                    val profile = Profile.getCurrentProfile()
                    if (profile != null) {
                      val firstName = profile.firstName
                        val lastName = profile.lastName
                        val social_id = profile.linkUri
                        val profileURL =
                            java.lang.String.valueOf(profile.getProfilePictureUri(200, 200))
                        Log.e("face", "social Id: $firstName")
                        Log.e("face", "social Id: $lastName")
                        Log.e("face", "social Id: $social_id")
                        Log.e("face", "social Id: $profileURL")

                    }

                    val request = GraphRequest.newMeRequest(result.accessToken, callback = GraphRequest.GraphJSONObjectCallback { obj, response ->
                        val id: String = obj?.getString("id").toString()
                        val name: String = obj?.getString("name").toString()
                        val email: String = obj?.getString("email").toString()
                       // val pictureUri: String = obj?.getString("pictureUri").toString()
                        val picture: String = obj?.getString("picture").toString()



                        Log.e("facebookname1 : ", id)
                        Log.e("facebooklink1 : ", name)
                        Log.e("facebookmail1 : ", email)
                          Log.e("facebookpicture1 : ", picture)


                    })

                 val   parameters = Bundle()
                    parameters.putString("fields", "id,name,email,picture")
                    request.parameters = parameters
                    request.executeAsync()
                    Log.e("facebookparam : ", parameters.toString())
                    Log.e("facebook : ", result.accessToken.token)

                    Log.e("facebook : ", result.accessToken.expires.toString())

                    Log.e("facebook : ", result.accessToken.userId)

                }

                override fun onCancel() {
                    onCancel()
                    Log.e("on cancel :"," On Cancel")
                }

                override fun onError(error: FacebookException) {
                    onError(error)
                    Log.e("on error :"," ${error?.localizedMessage}")
                }
            })
        }
    )




}







//Google

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    //text: String = "Sign Up with Google",
    //loadingText: String = "Creating Account...",
     icon: Int = R.drawable.twitter,
    shape: Shape = Shapes.medium,
    borderColor: Color = Color.Transparent,
    backgroundColor: Color = Transparent,
    //progressIndicatorColor: Color = MaterialTheme.colors.primary,
    onClicked: () -> Unit
) {
    var clicked by remember { mutableStateOf(false) }


            Icon(
                 painter = painterResource(id = icon),
                contentDescription = "Google Button",
                modifier
                    .width(52.dp)
                    .height(53.dp)
                    .padding(5.dp)
                    .background(White)
                    .clickable { clicked = !clicked },
                tint = Color.Unspecified,

            )

           // Text(text = if (clicked) loadingText else text)
            if (clicked) {

getRequestToken()
               /* CircularProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp,
                    color = progressIndicatorColor
                )*/
                onClicked()
            }
        }



//twitter
lateinit var twitter: Twitter
lateinit var twitterDialog: Dialog
@SuppressLint("CoroutineCreationDuringComposition", "SetJavaScriptEnabled", "ComposableNaming")
@Composable
private fun getRequestToken() {
    val context = LocalContext.current

    GlobalScope.launch(Dispatchers.Default) {

        val builder = ConfigurationBuilder()
            .setDebugEnabled(true)
            .setOAuthConsumerKey(TwitterConstants.CONSUMER_KEY)
            .setOAuthConsumerSecret(TwitterConstants.CONSUMER_SECRET)
            .setIncludeEmailEnabled(true)
        val config = builder.build()
        val factory = TwitterFactory(config)
        twitter = factory.instance
        try {

            val requestToken = twitter.oAuthRequestToken
            withContext(Dispatchers.Default) {
                twitterDialog = Dialog(context)
                val webView = WebView(context)
                webView.isVerticalScrollBarEnabled = false
                webView.isHorizontalScrollBarEnabled = false
                webView.webViewClient = TwitterWebViewClient()
                webView.settings.javaScriptEnabled = true
                webView.loadUrl(requestToken.authorizationURL)
                twitterDialog.setContentView(webView)
                twitterDialog.show()
            }
        } catch (e: IllegalStateException) {
            Log.e("ERROR: ", e.toString())
        }
    }
}


/*

lateinit var twitter: Twitter
private fun getRequestToken() {
    GlobalScope.launch(Dispatchers.Default) {
        val builder = ConfigurationBuilder()
            .setDebugEnabled(true)
            .setOAuthConsumerKey(TwitterConstants.CONSUMER_KEY)
            .setOAuthConsumerSecret(TwitterConstants.CONSUMER_SECRET)
            .setIncludeEmailEnabled(true)
        val config = builder.build()
        val factory = TwitterFactory(config)
        twitter = factory.instance
        try {
            val requestToken = twitter.oAuthRequestToken
            withContext(Dispatchers.Main) {
                setupTwitterWebviewDialog(requestToken.authorizationURL)
            }
        } catch (e: IllegalStateException) {
            Log.e("ERROR: ", e.toString())
        }
    }
}


lateinit var twitterDialog: Dialog
var accToken: AccessToken? = null
// Show twitter login page in a dialog
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun setupTwitterWebviewDialog(url: String) {
    val context = LocalContext.current

    twitterDialog = Dialog(context)
    val webView = WebView(context)
    webView.isVerticalScrollBarEnabled = false
    webView.isHorizontalScrollBarEnabled = false
    webView.webViewClient = TwitterWebViewClient()
    webView.settings.javaScriptEnabled = true
    webView.loadUrl(url)
    twitterDialog.setContentView(webView)
    twitterDialog.show()
}
// A client to know about WebView navigations
// For API 21 and above
@Suppress("OverridingDeprecatedMember")
inner class TwitterWebViewClient : WebViewClient() {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        if (request?.url.toString().startsWith(TwitterConstants.CALLBACK_URL)) {
            Log.d("Authorization URL: ", request?.url.toString())
            handleUrl(request?.url.toString())
            // Close the dialog after getting the oauth_verifier
            if (request?.url.toString().contains(TwitterConstants.CALLBACK_URL)) {
                twitterDialog.dismiss()
            }
            return true
        }
        return false
    }
    // For API 19 and below
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        if (url.startsWith(TwitterConstants.CALLBACK_URL)) {
            Log.d("Authorization URL: ", url)
            handleUrl(url)
            // Close the dialog after getting the oauth_verifier
            if (url.contains(TwitterConstants.CALLBACK_URL)) {
                twitterDialog.dismiss()
            }
            return true
        }
        return false
    }
    // Get the oauth_verifier
    private fun handleUrl(url: String) {

        val uri = Uri.parse(url)
        val oauthVerifier = uri.getQueryParameter("oauth_verifier") ?: ""
        GlobalScope.launch(Dispatchers.Main) {
            accToken = withContext(Dispatchers.IO) { twitter.getOAuthAccessToken(oauthVerifier) }
            getUserProfile()
        }
    }
}
*/

