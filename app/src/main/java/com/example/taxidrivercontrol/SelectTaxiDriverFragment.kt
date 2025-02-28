package com.example.taxidrivercontrol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.taxidrivercontrol.databinding.FragmentSelectTaxiDriverBinding


class SelectTaxiDriverFragment : Fragment() {

    private lateinit var binding:FragmentSelectTaxiDriverBinding
    private lateinit var adapter:SelectTaxiDriverRVAdapter
    private lateinit var dataList:ArrayList<SelectTaxiDriverData>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentSelectTaxiDriverBinding.inflate(inflater,container,false)
        dataList=ArrayList()
        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager=StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)

        var a1=SelectTaxiDriverData("Umut Yiğit")
        var a2=SelectTaxiDriverData("Enes Bayır")
        var a3=SelectTaxiDriverData("Can Atıl")
        dataList.add(a1)
        dataList.add(a2)
        dataList.add(a3)
        adapter=SelectTaxiDriverRVAdapter(requireContext(),dataList)
        binding.rv.adapter=adapter

        return binding.root
    }


}