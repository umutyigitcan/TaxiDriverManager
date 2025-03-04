package com.example.taxidrivercontrol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.taxidrivercontrol.databinding.FragmentLoginPageBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginPage : Fragment() {

    private lateinit var binding: FragmentLoginPageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginPageBinding.inflate(inflater, container, false)

        val database = FirebaseDatabase.getInstance()
        val Users = database.getReference("Users")

        binding.login.setOnClickListener {
            val mail = binding.edittext.text
            val password = binding.edittext2.text

            Users.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(ds: DataSnapshot) {
                    for (p in ds.children) {
                        val user = p.getValue(UserData::class.java)

                        if (user != null) {
                            if (user.userMail == mail.toString() && user.userPassword == password.toString()) {

                                val vt = SavedUserSQLite(requireContext())
                                SavedUserSQLiteDao().changeUser(vt, user.userName.toString(), user.userMail.toString(), user.userPassword.toString())

                                if (isAdded) {
                                    Navigation.findNavController(requireView()).navigate(R.id.action_loginPage_to_homePage)
                                }
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }

        binding.register.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_loginPage_to_registerPage)
        }

        return binding.root
    }
}
