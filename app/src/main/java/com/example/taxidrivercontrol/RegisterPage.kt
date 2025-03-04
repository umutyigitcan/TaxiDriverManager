package com.example.taxidrivercontrol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.taxidrivercontrol.databinding.FragmentRegisterPageBinding
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase


class RegisterPage : Fragment() {


    private lateinit var binding:FragmentRegisterPageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentRegisterPageBinding.inflate(inflater,container,false)

        val database=FirebaseDatabase.getInstance()
        val Users=database.getReference("Users")

        binding.register.setOnClickListener {
            var mail=binding.edittext.text
            var userName=binding.edittextname.text
            var password=binding.edittext2.text
            var vt=SavedUserSQLite(requireContext())
            var user=UserData(mail.toString(),userName.toString(),password.toString(),"admin")
            Users.push().setValue(user)
            Navigation.findNavController(it).navigate(R.id.action_registerPage_to_loginPage)

        }

        return binding.root
    }
}