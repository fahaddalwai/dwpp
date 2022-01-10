package com.example.gdscdwp.authentication.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gdscdwp.R
import com.example.gdscdwp.data.AuthRepository
import com.example.gdscdwp.databinding.FragmentLoginBinding
import com.example.gdscdwp.network.AuthApi


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var repository: AuthRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        repository= AuthRepository(
            AuthApi.retrofitService,
            requireContext()
        )
        val viewModelFactory = LoginViewModelFactory(repository)


        viewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(LoginViewModel::class.java)

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.goBack.observe(viewLifecycleOwner, {
            if (it) {
                goBack()
                viewModel.setEventGoBackToFalse()
            }
        })


        viewModel.eventLogin.observe(viewLifecycleOwner, {
            if(it){
                Log.i("checking","its working")
                viewModel.eventLoginInUser()
                viewModel.setEventLoginFalse()
            }
        })

        viewModel.eventMainActivity.observe(viewLifecycleOwner, {
            if(it){
                goToMainActivity()
                viewModel.setEventMainActivityFalse()
            }
        })



        return binding.root
    }

    private fun goBack() {

        findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)

    }

    private fun goToMainActivity(){
        Log.i("moved to next activity","true")
        findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
    }


}