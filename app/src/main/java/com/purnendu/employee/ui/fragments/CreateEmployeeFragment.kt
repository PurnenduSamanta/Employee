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
import com.purnendu.employee.Repository
import com.purnendu.employee.databinding.FragmentCreateEmployeeBinding
import com.purnendu.employee.roomDb.EmployeeDb
import com.purnendu.employee.roomDb.EmployeeModel
import com.purnendu.employee.ui.viewmodels.ViewModel
import com.purnendu.employee.ui.viewmodels.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CreateEmployeeFragment : Fragment() {


    private var _binding: FragmentCreateEmployeeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = Repository(EmployeeDb.getDataBase(requireContext()))
        viewModel = ViewModelProvider(this, ViewModelFactory(repository))[ViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateEmployeeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createEmployee.setOnClickListener {

            var employeeName = binding.name.text.toString()
            var employeeNo = binding.number.text.toString()
            var employeeSalary = binding.salary.text.toString()

            if (!viewModel.validateInput(employeeNo, employeeName, employeeSalary)) {
                Toast.makeText(requireContext(), "Bad input", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.isEmployeeNoExist(employeeNo)
            {
                    if (it) {
                        viewModel.insertEmployee(
                            EmployeeModel(
                                employeeNo.toLong(),
                                employeeName,
                                employeeSalary.toInt()
                            )
                        )
                        employeeName = ""
                        employeeNo = ""
                        employeeSalary = ""
                        findNavController().popBackStack()
                    } else
                        Toast.makeText(
                            requireContext(),
                            "Employee No exist already",
                            Toast.LENGTH_SHORT
                        ).show()

            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}