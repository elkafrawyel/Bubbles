package com.elwaha.bubbles.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import com.elwaha.bubbles.R
import com.elwaha.bubbles.utilies.Injector
import com.elwaha.bubbles.utilies.changeLanguage
import androidx.navigation.Navigation


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeLanguage()
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.fragment)
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

        if (Injector.getPreferenceHelper().isLoggedIn){
            navGraph.startDestination = R.id.homeFragment
        }else{
            navGraph.startDestination = R.id.auth
        }

        navController.graph = navGraph

    }
}
