@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.aldawaa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate
import com.example.aldawaa.ui.theme.AlDawaaTheme
import com.example.aldawaa.view.Loginpage
import com.facebook.CallbackManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder



@OptIn(DelicateCoroutinesApi::class)
@ExperimentalMaterialApi
@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : ComponentActivity()  {

// base localization
/*
        override fun attachBaseContext(newBase: Context) {
            super.attachBaseContext(
                LocaleHelper.setLocale(newBase, MyApp.myLang)
            )

    }*/
private val localizationDelegate = LocalizationActivityDelegate(this)
    override fun attachBaseContext(newBase: Context) {
        applyOverrideConfiguration(localizationDelegate.updateConfigurationLocale(newBase))
        super.attachBaseContext(newBase)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        FacebookUtil.callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch {
            val results = GlobalScope.async { isLoggedIn() }
            val result = results.await()
            if (result) {
                // Show the Activity with the logged in user
                Log.d("LoggedIn?: ", "YES")
            } else {
                // Show the Home Activity
                Log.d("LoggedIn?: ", "NO")
            }
        }

        setContent {
            AlDawaaTheme {
                // A surface container using the 'background' color from the theme

               Navigatpage()
            }
        }
    }


    private suspend fun isLoggedIn(): Boolean {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        val accessToken = sharedPref.getString("oauth_token","")
        val accessTokenSecret = sharedPref.getString("oauth_token_secret", "")
        val builder = ConfigurationBuilder()
        builder.setOAuthConsumerKey(TwitterConstants.CONSUMER_KEY)
            .setOAuthConsumerSecret(TwitterConstants.CONSUMER_SECRET)
            .setOAuthAccessToken(accessToken)
            .setOAuthAccessTokenSecret(accessTokenSecret)
        val config = builder.build()
        val factory = TwitterFactory(config)
        val twitter = factory.instance
        return try {
            withContext(Dispatchers.IO) { twitter.verifyCredentials() }
            true
        } catch (e: Exception) {
            false
        }
    }





}

object FacebookUtil {
    val callbackManager by lazy {
        CallbackManager.Factory.create()
    }

}



@ExperimentalMaterialApi
@Preview
@Composable
fun Navigatpage(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "Login_page",
        builder = {
            composable("Login_page", content = { Loginpage(navController = navController)})
            composable("Home_Screen", content = {  })


        }
    )
}



