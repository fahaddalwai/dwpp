package com.example.gdscdwp.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.gdscdwp.R
import com.example.gdscdwp.authentication.authActivity.WelcomeViewModel
import com.example.gdscdwp.authentication.login.LoginViewModelFactory
import com.example.gdscdwp.data.AuthRepository
import com.example.gdscdwp.network.AuthApi


class AuthenticationActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)




    }
}