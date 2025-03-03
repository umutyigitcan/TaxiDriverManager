package com.example.taxidrivercontrol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.taxidrivercontrol.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding:FragmentProfileBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentProfileBinding.inflate(inflater,container,false)

        binding.adminPanel.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_profileFragment_to_adminPanel2)
        }

        var vt=SavedUserSQLite(requireContext())
        var getUser=SavedUserSQLiteDao().getUser(vt)
        for(k in getUser){
            binding.userName.text=k.userName
            binding.mail.text=k.mail
        }



        return binding.root
    }


}