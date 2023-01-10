package com.purnendu.employee.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.purnendu.employee.R
import com.purnendu.employee.Repository
import com.purnendu.employee.databinding.FragmentHomeBinding
import com.purnendu.employee.databinding.FragmentUpdateBinding
import com.purnendu.employee.roomDb.EmployeeDb
import com.purnendu.employee.roomDb.EmployeeModel
import com.purnendu.employee.ui.viewmodels.ViewModel
import com.purnendu.employee.ui.viewmodels.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UpdateFragment : Fragment() {


    private var _binding: FragmentUpdateBinding?=null
    private val binding get() =_binding!!
    private lateinit var viewModel: ViewModel
    private val args: UpdateFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        val repository = Repository(EmployeeDb.getDataBase(requireContext()))
        viewModel = ViewModelProvider(this, ViewModelFactory(repository))[ViewModel::class.java]
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val name = args.employeeData.employeeName
        val no = args.employeeData.employeeNo
        val salary = args.employeeData.employeeSalary

        binding.updatedName.setText(name)
        binding.updatedSalary.setText(salary.toString())

        binding.updateEmployee.setOnClickListener {

            val employeeName=binding.updatedName.text.toString()
            val employeeSalary=binding.updatedSalary.text.toString()


            println(no)
            println(employeeName)
            println(employeeSalary)

            if(!viewModel.validateInput(no.toString(),employeeName,employeeSalary))
            {
                Toast.makeText( requireContext(),"Bad input", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.updateEmployee(EmployeeModel(no,employeeName,employeeSalary.toInt()))
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)

        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}