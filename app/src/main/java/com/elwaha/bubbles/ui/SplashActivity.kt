package com.elwaha.bubbles.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elwaha.bubbles.R
import com.elwaha.bubbles.utilies.Constants
import com.elwaha.bubbles.utilies.Injector

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Injector.getPreferenceHelper().isLoggedIn = true

        Injector.getPreferenceHelper().userType = Constants.UserType.DRIVER.value
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
