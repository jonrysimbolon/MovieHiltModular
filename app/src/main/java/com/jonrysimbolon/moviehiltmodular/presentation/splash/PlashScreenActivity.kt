package com.jonrysimbolon.moviehiltmodular.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jonrysimbolon.moviehiltmodular.presentation.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            delay(500)
            Intent(this@PlashScreenActivity, MainActivity::class.java)
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