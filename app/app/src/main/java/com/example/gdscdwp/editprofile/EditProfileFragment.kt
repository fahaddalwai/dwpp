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
import androidx.databinding.DataBindingUtil
import com.example.gdscdwp.R
import com.example.gdscdwp.databinding.FragmentEditProfileBinding
import com.example.gdscdwp.databinding.FragmentEditProfileBindingImpl


class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_edit_profile, container, false)


        binding.deleteAccountEditText.setOnClickListener {


            val builder = AlertDialog.Builder(activity,R.style.AlertDialogCustom)

            builder.setMessage("We have a message")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton(android.R.string.yes){ dialog, which ->
                Toast.makeText(
                    activity,
                    android.R.string.yes, Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(activity,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }


        return binding.root
    }


}