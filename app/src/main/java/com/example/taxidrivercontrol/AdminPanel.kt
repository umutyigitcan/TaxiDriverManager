package com.example.taxidrivercontrol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taxidrivercontrol.databinding.FragmentAdminPanelBinding
import com.google.firebase.database.FirebaseDatabase

class AdminPanel : Fragment() {




    private lateinit var binding:FragmentAdminPanelBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentAdminPanelBinding.inflate(inflater,container,false)

        var database=FirebaseDatabase.getInstance()
        var Users=database.getReference("Users")
        binding.addTaxiDriver.setOnClickListener {
            var userName=binding.taxiDriverName.text
            var mail=binding.taxiDriverMail.text
            var password=binding.taxiDriverPassword.text
            var plate=binding.taxiDriverPlate.text
            var carModel=binding.taxiDriverCarModel.text
            var carColor=binding.taxiCarColor.text
            Users.push().setValue(UserData(mail.toString(),userName.toString(),password.toString(),"Taksi Sürücüsü",plate.toString(),carModel.toString(),carColor.toString()))
            binding.taxiDriverName.text.clear()
            binding.taxiCarColor.text.clear()
            binding.taxiDriverMail.text.clear()
            binding.taxiDriverPassword.text.clear()
            binding.taxiDriverCarModel.text.clear()
            binding.taxiDriverPlate.text.clear()

        }


        return binding.root
    }
}