package com.example.gdscdwp.editprofile

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.gdscdwp.R
import com.example.gdscdwp.authentication.login.LoginViewModel
import com.example.gdscdwp.authentication.signup.SignupViewModel
import com.example.gdscdwp.authentication.signup.SignupViewModelFactory
import com.example.gdscdwp.data.AuthRepository
import com.example.gdscdwp.databinding.FragmentEditProfileBinding
import com.example.gdscdwp.databinding.FragmentEditProfileBindingImpl
import com.example.gdscdwp.network.AuthApi


class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var viewModel:EditProfileViewModel
    private lateinit var repository: AuthRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container, false)


        repository= AuthRepository(
            AuthApi.retrofitService,
            requireContext()
        )
        val viewModelFactory = EditProfileViewModelFactory(repository)


        viewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(EditProfileViewModel::class.java)



        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        viewModel.showDialogDelete.observe(viewLifecycleOwner, {
            if(it){
                showDialogBox()
                viewModel.setShowDialogDeleteToFalse()
            }
        })


        viewModel.showDialogUpdate.observe(viewLifecycleOwner, {
            if(it){
                showDialogBox()
            }
        })



        viewModel.eventMainActivity.observe(viewLifecycleOwner,{
            if(it){

                goToAuthActivity()

            }
        })




        return binding.root
    }

    private fun showDialogBox() {
        val builder = AlertDialog.Builder(activity, R.style.AlertDialogCustom)
        builder.setMessage("Are you sure you want to delete your account? All your save data will be lost forever")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            viewModel.deleteUser()
            Toast.makeText(activity,"Your account has been deleted : (",Toast.LENGTH_SHORT).show()

            viewModel.setShowDialogUpdateToFalse()



        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(
                activity,
                android.R.string.no, Toast.LENGTH_SHORT
            ).show()
        }
        builder.show()
    }

    private fun goToAuthActivity(){
        findNavController().navigate(R.id.action_editProfileFragment3_to_authenticationActivity)
        Log.i("went to main activity","yes")
    }


}