@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.aldawaa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate
import com.example.aldawaa.presentation.ui.theme.AlDawaaTheme
import com.example.aldawaa.utils.Navigatpage
import com.facebook.CallbackManager
import com.facebook.LoginStatusCallback
import com.facebook.login.LoginManager
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

        //facebook




//twitter
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
                Navigatpage()
            }
        }
    }

//twitter
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





























/*
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
        }*/
/*  // For API 19 and below
  @Deprecated("Deprecated in Java")
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
 var accToken: AccessToken?=null
  // Get the oauth_verifier
  private fun handleUrl(url: String) {

      val uri = Uri.parse(url)
      val oauthVerifier = uri.getQueryParameter("oauth_verifier") ?: ""
      GlobalScope.launch(Dispatchers.Main) {
      //    accToken = withContext(Dispatchers.IO) { twitter.getOAuthAccessToken(oauthVerifier) }
          getUserProfile()
      }
  }

  private suspend fun getUserProfile() {
      val usr = withContext(Dispatchers.IO) { twitter.verifyCredentials() }
      //Twitter Id
      val twitterId = usr.id.toString()
      Log.d("Twitter Id: ", twitterId)

      //Twitter Handle
      val twitterHandle = usr.screenName
      Log.d("Twitter Handle: ", twitterHandle)
      //Twitter Name
      val twitterName = usr.name
      Log.d("Twitter Name: ", twitterName)
      //Twitter Email
      val twitterEmail = usr.email
      Log.d("Twitter Email: ", twitterEmail ?: "'Request email address from users' on the Twitter dashboard is disabled")
      // Twitter Profile Pic URL
      val twitterProfilePic = usr.profileImageURLHttps.replace("_normal", "")
      Log.d("Twitter Profile URL: ", twitterProfilePic)
      // Twitter Access Token
    //  Log.d("Twitter Access Token", accToken1)
      // Log.d("Twitter Access Token", accToken1)

      *//* val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
             sharedPref.edit().putString("oauth_token",accToken?.token ?: "").apply()
             sharedPref.edit().putString("oauth_token_secret",accToken?.tokenSecret ?: "").apply()*//*


        }
    }

*/