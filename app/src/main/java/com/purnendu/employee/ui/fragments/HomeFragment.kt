package com.purnendu.employee.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.purnendu.employee.R
import com.purnendu.employee.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {


    private var _binding:FragmentHomeBinding?=null
    private val binding get() =_binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentHomeBinding.inflate(inflater, container, false)

        binding.create.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createEmployeeFragment)
        }

        binding.list.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_listEmployeeFragment)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}