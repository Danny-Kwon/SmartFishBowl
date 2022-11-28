package com.example.smartfishbowl

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.smartfishbowl.api.APIS
import com.example.smartfishbowl.api.LoginRepository
import com.example.smartfishbowl.api.OkSign
import com.example.smartfishbowl.api.Token
import com.example.smartfishbowl.databinding.ActivityLoginBinding
import com.fasterxml.jackson.core.JsonFactory
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val GOOGLE_LOGIN_CODE = 9001
    private val API_KEY = "AIzaSyBOK_Q0ImTM529mQIB5BQddzJTryKiZAYQ"
    private val apis = APIS.create()
    private val repo = LoginRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initFirebase()
        binding.getTkn.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("607680018436-vh9n8arb3hnje3nt7pokp97d3seaphlj.apps.googleusercontent.com")
                .requestServerAuthCode("607680018436-vh9n8arb3hnje3nt7pokp97d3seaphlj.apps.googleusercontent.com")
                .requestEmail()
                .build()

            val googleSignInClient = GoogleSignIn.getClient(this, gso)
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent,GOOGLE_LOGIN_CODE)
        }

        binding.googleLogin.setOnClickListener {
            //val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://jungwoo.bowl.p-e.kr:8080/oauth2/authorization/google"))
            //val intent = Intent(this, MenuActivity::class.java)
            //startActivity(intent)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_LOGIN_CODE){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)!!
            repo.getAccessToken(account.serverAuthCode!!)
        }else{
            Log.d("requestcode", "Not available")
        }
    }

    private fun updateViewModelWithGoogleAccount(account: GoogleSignInAccount) {
        val transport = NetHttpTransport()
        val jsonFactory = JacksonFactory.getDefaultInstance()
        val response = GoogleAuthorizationCodeTokenRequest(
            transport,
            jsonFactory,
            getString(R.string.client_id),
            getString(R.string.client_secret),
            account.serverAuthCode,
            ""
        ).execute()
        Log.d("Response", response.toString())
        Toast.makeText(applicationContext, response.accessToken, Toast.LENGTH_SHORT).show()
        Log.d("AccessToken", response.accessToken)
    }
    private fun initFirebase(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("FirebaseToken", task.result)
                val tok = Token(task.result)
                binding.tokenValue.setText(task.result)
                apis.sendToken(tok).enqueue(object : Callback<OkSign> {
                    override fun onResponse(call: Call<OkSign>, response: Response<OkSign>) {
                        Log.d("log", response.toString())
                        Log.d("log", response.body()?.okSign.toString())
                        if (response.body().toString().isNotEmpty())
                            Log.d("log", response.toString())
                    }
                    override fun onFailure(call: Call<OkSign>, t: Throwable) {
                        // 실패
                        Log.d("log", t.message.toString())
                        Log.d("registerUser", "Fail")
                    }
                })
            }else{
                Toast.makeText(applicationContext, "응 안돼 돌아가 ㅋㅋ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}