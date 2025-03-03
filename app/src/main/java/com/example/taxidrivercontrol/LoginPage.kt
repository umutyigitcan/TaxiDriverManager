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



    private lateinit var binding:FragmentLoginPageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentLoginPageBinding.inflate(inflater,container,false)

        var database=FirebaseDatabase.getInstance()
        var Users=database.getReference("Users")

        binding.login.setOnClickListener {
            var mail=binding.edittext.text
            var password=binding.edittext2.text
            Users.addValueEventListener(object :ValueEventListener{
                override fun onDataChange(ds: DataSnapshot) {
                    for(p in ds.children){
                        val user=p.getValue(UserData::class.java)

                        if(user!=null){
                            val key=p.key
                            if(user.userMail==mail.toString()&&user.userPassword==password.toString()){

                                var vt=SavedUserSQLite(requireContext())
                                SavedUserSQLiteDao().changeUser(vt,user.userName.toString(),user.userMail.toString(),user.userPassword.toString())

                                Navigation.findNavController(it).navigate(R.id.action_loginPage_to_homePage)
                            }

                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }

        binding.register.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginPage_to_registerPage)
        }




        return binding.root
    }
}