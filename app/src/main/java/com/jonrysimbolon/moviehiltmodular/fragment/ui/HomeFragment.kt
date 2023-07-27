package com.jonrysimbolon.moviehiltmodular.fragment.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.jonrysimbolon.core.base.BaseFragment
import com.jonrysimbolon.moviehiltmodular.R
import com.jonrysimbolon.moviehiltmodular.databinding.FragmentHomeBinding
import com.jonrysimbolon.moviehiltmodular.fragment.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override fun FragmentHomeBinding.initialize() {}
    override fun getViewModel() = HomeViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().showToast(R.string.movies)
        //val toDetailDirection = HomeFragmentDirections.actionHomeFragmentToDetailsFragment()
        binding.detail.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_detail)
        }
    }
}