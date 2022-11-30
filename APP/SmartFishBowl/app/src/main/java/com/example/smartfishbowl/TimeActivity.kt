package com.example.smartfishbowl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartfishbowl.databinding.ActivityTimeBinding

class TimeActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityTimeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}