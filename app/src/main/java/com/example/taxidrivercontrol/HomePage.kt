package com.example.taxidrivercontrol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.taxidrivercontrol.databinding.FragmentHomePageBinding

class HomePage : Fragment() {


    private lateinit var binding:FragmentHomePageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentHomePageBinding.inflate(inflater,container,false)
        binding.profile.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homePage_to_profileFragment)
        }


        return binding.root
    }


}