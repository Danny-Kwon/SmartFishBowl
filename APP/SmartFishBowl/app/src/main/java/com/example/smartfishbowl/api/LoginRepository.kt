package com.example.smartfishbowl.api

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {
    private val getAccessTokenBaseUrl = "https://www.googleapis.com"
    private val sendAccessTokenBaseUrl = "server_base_url"

    fun getAccessToken(authCode:String) {
        LoginService.loginRetrofit(getAccessTokenBaseUrl).getAccessToken(
            request = LoginGoogleRequestModel(
                grant_type = "authorization_code",
                client_id = "607680018436-vh9n8arb3hnje3nt7pokp97d3seaphlj.apps.googleusercontent.com",
                client_secret = "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",
                code = authCode.orEmpty()
            )
        ).enqueue(object : Callback<LoginGoogleResponseModel> {
            override fun onResponse(call: Call<LoginGoogleResponseModel>, response: Response<LoginGoogleResponseModel>) {
                if(response.isSuccessful) {
                    val accessToken = response.body()?.access_token.orEmpty()
                    Log.d(TAG, "accessToken: $accessToken")
                    sendAccessToken(accessToken)
                }
            }
            override fun onFailure(call: Call<LoginGoogleResponseModel>, t: Throwable) {
                Log.e(TAG, "getOnFailure: ",t.fillInStackTrace() )
            }
        })
    }

    fun sendAccessToken(accessToken:String){
        LoginService.loginRetrofit(sendAccessTokenBaseUrl).sendAccessToken(
            accessToken = SendAccessTokenModel(accessToken)
        ).enqueue(object :Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    Log.d(TAG, "sendOnResponse: ${response.body()}")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "sendOnFailure: ${t.fillInStackTrace()}", )
            }
        })
    }

    companion object {
        const val TAG = "LoginRepository"
    }
}