package com.example.gdscdwp.authentication.signup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gdscdwp.R
import com.example.gdscdwp.authentication.login.LoginViewModel
import com.example.gdscdwp.databinding.FragmentLoginBinding
import com.example.gdscdwp.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var viewModel: SignupViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)

        viewModel =
            ViewModelProvider(this,).get(SignupViewModel::class.java)      //define instance of viewmodel using provider

        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        viewModel.goBack.observe(viewLifecycleOwner, {
            if (it) {
                goBack()
                viewModel.setEventGoBackToFalse()
            }
        })

        return binding.root
    }

    private fun goBack() {
        findNavController().navigate(R.id.action_signupFragment_to_welcomeFragment)
    }
}



