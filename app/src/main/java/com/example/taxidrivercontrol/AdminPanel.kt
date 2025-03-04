package com.example.taxidrivercontrol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.taxidrivercontrol.databinding.FragmentAdminPanelBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AdminPanel : Fragment() {

    private lateinit var binding: FragmentAdminPanelBinding
    private lateinit var adapter: DeleteTaxiDriverRVAdapter
    private lateinit var dataList: ArrayList<SQLData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminPanelBinding.inflate(inflater, container, false)

        dataList = ArrayList()
        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        val database = FirebaseDatabase.getInstance()
        val Users = database.getReference("Users")


        binding.addTaxiDriver.setOnClickListener {
            val userName = binding.taxiDriverName.text.toString()
            val mail = binding.taxiDriverMail.text.toString()
            val password = binding.taxiDriverPassword.text.toString()
            val plate = binding.taxiDriverPlate.text.toString()
            val carModel = binding.taxiDriverCarModel.text.toString()
            val carColor = binding.taxiCarColor.text.toString()

            val vt = SavedUserSQLite(requireContext())
            val getUser = SavedUserSQLiteDao().getUser(vt)
            for (k in getUser) {
                Users.push().setValue(
                    UserData(
                        mail, userName, password, "Taksi Sürücüsü", plate, carModel, carColor, k.mail
                    )
                )
            }

            dataList.add(
                SQLData(
                    mail, userName, password, "Taksi Sürücüsü", plate, carModel, carColor, "" // adminName boş bırakılabilir
                )
            )
            adapter.notifyDataSetChanged()
            clearInputFields()
        }

        Users.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(ds: DataSnapshot) {

                if (!isAdded) return

                dataList.clear()
                for (p in ds.children) {
                    val getData = p.getValue(UserData::class.java)
                    if (getData != null) {
                        val vt = SavedUserSQLite(requireContext())
                        val list = SavedUserSQLiteDao().getUser(vt)
                        for (k in list) {
                            if (getData.adminName == k.mail) {
                                dataList.add(
                                    SQLData(
                                        getData.userMail.toString(),
                                        getData.userName.toString(),
                                        getData.userPassword.toString(),
                                        getData.role.toString(),
                                        getData.plate.toString(),
                                        getData.carModel.toString(),
                                        getData.carColor.toString(),
                                        getData.adminName.toString()
                                    )
                                )
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })


        adapter = DeleteTaxiDriverRVAdapter(requireContext(), dataList)
        binding.rv.adapter = adapter

        return binding.root
    }

    private fun clearInputFields() {
        binding.taxiDriverName.text.clear()
        binding.taxiCarColor.text.clear()
        binding.taxiDriverMail.text.clear()
        binding.taxiDriverPassword.text.clear()
        binding.taxiDriverCarModel.text.clear()
        binding.taxiDriverPlate.text.clear()
    }
}
