package com.example.foodrecipeapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.adapters.RecipesAdapter
import com.example.foodrecipeapp.databinding.FragmentRecipesBinding
import com.example.foodrecipeapp.network.NetworkResult
import com.example.foodrecipeapp.viewmodels.MainViewModel

class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var rvAdapter: RecipesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.recyclerView.showShimmer()
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        rvAdapter = RecipesAdapter()

        setupRv()
        initViewModel()
    }

    private fun setupRv() = binding.recyclerView.apply {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = rvAdapter
    }

    private fun initViewModel() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}