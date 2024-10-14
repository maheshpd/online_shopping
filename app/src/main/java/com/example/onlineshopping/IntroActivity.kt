package com.example.onlineshopping

import android.content.Intent
import android.os.Bundle

import com.example.onlineshopping.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityIntroBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_intro)
        binding.apply {
            startBtn.setOnClickListener {
                startActivity(Intent(this@IntroActivity,MainActivity::class.java))
            }
        }
    }
}