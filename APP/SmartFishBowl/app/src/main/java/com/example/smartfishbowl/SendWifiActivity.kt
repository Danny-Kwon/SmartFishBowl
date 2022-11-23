package com.example.smartfishbowl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartfishbowl.databinding.ActivitySendWifiBinding

class SendWifiActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySendWifiBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.wifiSendButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}