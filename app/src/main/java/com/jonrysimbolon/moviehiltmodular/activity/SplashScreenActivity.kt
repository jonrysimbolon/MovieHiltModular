package com.jonrysimbolon.moviehiltmodular.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            delay(500)
            Intent(this@SplashScreenActivity, MainActivity::class.java)
                .also { intent ->
                    startActivity(
                        intent
                    )
                }.also {
                    finish()
                }
        }
    }
}