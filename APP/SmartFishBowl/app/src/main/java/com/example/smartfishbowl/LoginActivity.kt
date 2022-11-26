package com.example.smartfishbowl

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.smartfishbowl.api.APIS
import com.example.smartfishbowl.api.Jwt
import com.example.smartfishbowl.api.OkSign
import com.example.smartfishbowl.api.Token
import com.example.smartfishbowl.databinding.ActivityLoginBinding
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val apis = APIS.create()
    private val tokenAPI = com.example.smartfishbowl.api.tokenAPI.create()
    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initFirebase()

        binding.getTkn.setOnClickListener {
            tokenAPI.getJwtToken().enqueue(object :Callback<Jwt>{
                override fun onResponse(call: Call<Jwt>, response: Response<Jwt>) {
                    if (response.isSuccessful){
                        Log.d("JWT", "SUCCESS")
                        Toast.makeText(this@LoginActivity, "${response.body()}", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<Jwt>, t: Throwable) {
                    Log.d("JWT", "FAIL")
                }
            })
        }

        binding.googleLogin.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://jungwoo.bowl.p-e.kr:8080/oauth2/authorization/google"))
            startActivity(intent)
        }
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