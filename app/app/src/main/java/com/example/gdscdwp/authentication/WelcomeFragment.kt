package com.example.gdscdwp.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.gdscdwp.R
import com.example.gdscdwp.databinding.FragmentWelcomeBinding



class WelcomeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_welcome,container,false)

        binding.loginButton.setOnClickListener{goToLogInFragment()}
        binding.signupButton.setOnClickListener{goToSignUpFragment()}

        return binding.root
    }

    private fun goToSignUpFragment() {
        findNavController().navigate(R.id.action_welcomeFragment_to_signupFragment)
    }

    private fun goToLogInFragment() {
        findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
    }


}