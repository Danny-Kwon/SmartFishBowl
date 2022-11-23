package com.example.smartfishbowl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartfishbowl.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.googleLogin.setOnClickListener {
            val intent = Intent(this, SendWifiActivity::class.java)
            startActivity(intent)
        }
    }
}