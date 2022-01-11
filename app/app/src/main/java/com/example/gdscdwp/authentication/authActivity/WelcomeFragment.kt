package com.example.gdscdwp.authentication.authActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gdscdwp.R
import com.example.gdscdwp.authentication.login.LoginViewModelFactory
import com.example.gdscdwp.data.AuthRepository
import com.example.gdscdwp.databinding.FragmentWelcomeBinding
import com.example.gdscdwp.network.AuthApi


class WelcomeFragment : Fragment() {

    private lateinit var repository: AuthRepository
    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_welcome,container,false)

        binding.loginButton.setOnClickListener{goToLogInFragment()}
        binding.signupButton.setOnClickListener{goToSignUpFragment()}

        repository= AuthRepository(
            AuthApi.retrofitService,
            requireContext()
        )
        val viewModelFactory = WelcomeViewModelFactory(repository)


        viewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(WelcomeViewModel::class.java)



        viewModel.eventSkipAuth.observe(viewLifecycleOwner,{
            if(it){
                goToMainActivity()
                viewModel.setEventSkipAuthFalse()
            }
        })
        return binding.root
    }

    private fun goToSignUpFragment() {
        findNavController().navigate(R.id.action_welcomeFragment_to_signupFragment)
    }

    private fun goToLogInFragment() {
        findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
    }

    private fun goToMainActivity() {
        findNavController().navigate(R.id.action_welcomeFragment_to_mainActivity)
    }

}