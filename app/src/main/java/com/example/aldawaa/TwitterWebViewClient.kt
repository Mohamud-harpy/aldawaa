package com.example.aldawaa

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.aldawaa.view.twitter
import com.example.aldawaa.view.twitterDialog
import com.facebook.AccessToken
import kotlinx.coroutines.*

// A client to know about WebView navigations
// For API 21 and above
@OptIn(DelicateCoroutinesApi::class)
class TwitterWebViewClient : WebViewClient() {
    @SuppressLint("ObsoleteSdkInt")
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
    // Get the oauth_verifier
    lateinit var accToken: AccessToken
    lateinit var accToken1: String


    @SuppressLint("SuspiciousIndentation")
    private fun handleUrl(url: String) {

        val uri = Uri.parse(url)
        val oauthVerifier = uri.getQueryParameter("oauth_verifier") ?:""
        GlobalScope.launch(Dispatchers.IO) {
         //   accToken = twitter.getOAuthAccessToken(oauthVerifier).token
       accToken1  =    twitter.getOAuthAccessToken(oauthVerifier).tokenSecret

            getUserProfile()
        }
    }

    suspend fun getUserProfile() {
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
        Log.d("Twitter Access Token", accToken1)
       // Log.d("Twitter Access Token", accToken1)

       /* val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        sharedPref.edit().putString("oauth_token",accToken?.token ?: "").apply()
        sharedPref.edit().putString("oauth_token_secret",accToken?.tokenSecret ?: "").apply()*/


    }
}