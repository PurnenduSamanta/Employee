package com.purnendu.employee.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.purnendu.employee.EmployeeRecyclerViewAdapter
import com.purnendu.employee.Repository
import com.purnendu.employee.databinding.FragmentListEmployeeBinding
import com.purnendu.employee.roomDb.EmployeeDb
import com.purnendu.employee.ui.viewmodels.ViewModel
import com.purnendu.employee.ui.viewmodels.ViewModelFactory

class ListEmployeeFragment : Fragment() {


    private var _binding: FragmentListEmployeeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: EmployeeRecyclerViewAdapter
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = Repository(EmployeeDb.getDataBase(requireContext()))
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(repository)
        )[ViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter= EmployeeRecyclerViewAdapter()
        _binding = FragmentListEmployeeBinding.inflate(inflater, container, false)
        binding.employeeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.employeeRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getEmployeeData().observe(viewLifecycleOwner) {
            if (it.isEmpty())
                binding.noDataImage.visibility = View.VISIBLE
            else
                binding.noDataImage.visibility = View.GONE
            adapter.setEmployees(it)


        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}