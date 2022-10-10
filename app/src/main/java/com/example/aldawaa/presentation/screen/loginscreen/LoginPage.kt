@file:Suppress("DEPRECATION")
@file:OptIn(ExperimentalFoundationApi::class, DelicateCoroutinesApi::class)

package com.example.aldawaa.presentation.screen.loginscreen


import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.graphics.Color.parseColor
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
import com.example.aldawaa.presentation.screen.signupscreen.Signup
import com.example.aldawaa.presentation.ui.AuthScreen
import com.example.aldawaa.presentation.ui.theme.Shapes
import com.example.aldawaa.presentation.ui.theme.Shapesaaldawaa
import com.example.aldawaa.presentation.ui.theme.logintabc
import com.example.aldawaa.presentation.ui.theme.textcolor
import com.example.aldawaa.presentation.screen.twitterscreen.AuthViewModel
import com.facebook.*
import com.facebook.FacebookSdk.getApplicationContext
import com.facebook.login.LoginManager
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
@SuppressLint("ResourceType", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Loginpage(navController: NavController) {
   // FacebookSdk.sdkInitialize(getApplicationContext());

    val authViewModel: AuthViewModel = hiltViewModel()
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val languages = listOf("English", "العربية")

    var chosenlanguage by rememberSaveable { mutableStateOf("English") }
    val errorMessage = "name format is invalid"

    /*fun validate(text: String) {
       isError = !text.contains('@')
    }*/

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


            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(80.dp)
                    .background(color = textcolor),

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

            Spacer(modifier = Modifier.padding(6.dp))


        Box() {


            OutlinedButton(
                colors = ButtonDefaults.buttonColors(backgroundColor = Transparent),
                onClick = { expanded = !expanded }, modifier = Modifier.background(
                    color = Transparent
                )
            ) {
                Text(chosenlanguage, color = White)
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
                languages.forEach { label ->
                    DropdownMenuItem(modifier = Modifier.background(Color.Transparent), onClick = {
                        // expanded = true

                        when (label) {
                            "English" -> {
                                chosenlanguage = label
                                setLanguage(context, Locale("en"))
                                (context as? Activity)?.recreate()
                                chosenlanguage = label

                            }
                            "العربية" -> {
                                chosenlanguage = label
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

            Spacer(modifier = Modifier.padding(10.dp))

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
                    // .background(color = White)
                    .paint(
                        painterResource(id = R.drawable.group_4),
                        contentScale = ContentScale.FillBounds
                    )


            ) {
                Spacer(modifier = Modifier.padding(15.dp))


                var selectedIndex by remember { mutableStateOf(0) }

                val list = listOf(
                    stringResource(id = R.string.signintap),
                    stringResource(id = R.string.signuptap)
                )

                TabRow(selectedTabIndex = selectedIndex,
                    backgroundColor = Transparent,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .clip(RoundedCornerShape(50))
                        ,
                    divider = { TabRowDefaults.Divider(color = Transparent) },
                    indicator = { tabPositions: List<TabPosition> ->

                        //   Box {          colors.primaryVariant.red              }
                    }
                ) {
                    list.forEachIndexed { index, text ->
                        val selected = selectedIndex == index
                        Tab(
                            modifier = if (selected) Modifier
                                .clip(shape = Shapesaaldawaa.large)
                                .background(
                                    logintabc,
                                )
                            else Modifier
                                .clip(shape = Shapesaaldawaa.large)
                                .background(
                                    Color.Transparent
                                ),
                            selected = selected,
                            onClick = { selectedIndex = index },
                            text = { Text(text = text, color = textcolor) }
                        )

                    }


                }

                when (selectedIndex) {
                    0 -> {
                        Login(navController = navController)
                    }

                    1 -> {
                         Signup(navController = navController)
                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.background(Transparent)
                ) {


                 /*   OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(53.dp)
                            .clip(shape = Shapesaaldawaa.small),
                        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                        shape = Shapesaaldawaa.small,
                        border = BorderStroke(2.dp, textcolor),

                        onClick = {
                            LoginManager.getInstance().logOut()


                        },


                        ) {
                        Text(
                            text = "Logout",
                            fontSize = 20.sp,
                            color = textcolor,
                        )
                    }*/


                        Text(
                        text = stringResource(id = R.string.orsigninwith),
                        fontSize = 15.sp,
                        color = textcolor,
                    )
                    Spacer(modifier = Modifier.padding(15.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Box(modifier = Modifier.clip(RoundedCornerShape(8.dp))) {
                            AuthScreen(authViewModel)
                        }


                        Spacer(modifier = Modifier.padding(4.dp))

                        //facebook
                        Box(modifier = Modifier.clip(RoundedCornerShape(8.dp))) {
                            CustomFacebookButton(
                                onSuccess = {},
                                onCancel = {},
                                onError = {}
                            )
                        }


                        Spacer(modifier = Modifier.padding(4.dp))
                        //Google

                        twitterButton(

                            onClicked = {
                            }
                        )

                    }

                }
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = stringResource(id = R.string.continueasaguest),
                    color = textcolor,
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
            .height(45.dp)
            .background(White)
            .padding(5.dp)
            .clip(
                RoundedCornerShape(8.dp)
            ),
        factory = ::LoginButton,
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

                    val request = GraphRequest.newMeRequest(
                        result.accessToken,
                        callback = GraphRequest.GraphJSONObjectCallback { obj, response ->
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

                    val parameters = Bundle()
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
                    Log.e("on cancel :", " On Cancel")
                }

                override fun onError(error: FacebookException) {
                    onError(error)
                    Log.e("on error :", " ${error?.localizedMessage}")
                }


            })

        }

    )

}

//Google

@Composable
fun twitterButton(
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
            .clip(RoundedCornerShape(8.dp))
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

@SuppressLint("CoroutineCreationDuringComposition", "ComposableNaming")
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
            withContext(Dispatchers.Main) {
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


/*

var text by rememberSaveable { mutableStateOf("") }
var isError by rememberSaveable { mutableStateOf(false) }

fun validate(text: String) {
    isError = */
/* .... *//*

}

Column {
    TextField(
        value = text,
        onValueChange = {
            text = it
            isError = false
        },
        trailingIcon = {
            if (isError)
                Icon(Icons.Filled.Info, "Error", tint = MaterialTheme.colorScheme.error)
        },
        singleLine = true,
        isError = isError,
        keyboardActions = KeyboardActions { validate(text) },
    )
    if (isError) {
        Text(
            text = "Error message",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}*/
